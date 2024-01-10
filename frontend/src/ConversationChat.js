import { useEffect, useState, useContext, useRef } from 'react';
import { Form, Row, Col, Button, Alert } from 'react-bootstrap';
import moment from 'moment';
import { useSearchParams } from 'react-router-dom';
import { UserContext } from './UserContext';
import { Client } from '@stomp/stompjs';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const getConversations = (id) => {
	return fetch(`/api/v1/conversations?page=1&size=20&userId=${id}`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	});
};

const getConversation = (id) => {
	return fetch(`/api/v1/conversations/${id}`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	});
};

const getMessages = (id) => {
	return fetch(`/api/v1/conversations/${id}/messages`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	});
};

const writeMessage = (id, userId, content) => {
	return fetch(`/api/v1/conversations/${id}/messages`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			userId,
			content,
		}),
	});
};

let websocketClient = null;

function ConversationChat(props) {
	const scrollContainerRef = useRef(null);

	const [conversation, setConversation] = useState(null);
	const [conversations, setConversations] = useState([]);

	const [messages, setMessages] = useState([]);
	const [message, setMessage] = useState('');

	const [searchParams, setSearchParams] = useSearchParams();

	const { user, setUser } = useContext(UserContext);

	const navigate = useNavigate();

	let isCustomer = false;
	let userId = null;
	if (user) {
		userId = user.id;
	}

	if (searchParams.has('userId')) {
		isCustomer = true;
		userId = searchParams.get('userId');
	}

	const conversationId = searchParams.has('conversationId')
		? searchParams.get('conversationId')
		: null;

	const loadConversation = (id) =>
		getConversation(id)
			.then((response) => response.json())
			.then((response) => {
				if (!response.error) {
					setConversation(response);
				}
			});

	useEffect(() => {
		if (conversationId == null) {
			navigate('/');
			return;
		}

		loadConversation(conversationId);
	}, [conversationId]);

	const loadConversations = (id) =>
		getConversations(id)
			.then((response) => response.json())
			.then((response) => {
				if (!response.error) {
					setConversations(response);
				}
			});

	useEffect(() => {
		loadConversations(userId);
	}, [userId]);

	const loadMessages = (id) =>
		getMessages(id)
			.then((response) => response.json())
			.then((response) => {
				if (!response.error) {
					setMessages(response.sort((a, b) => a.wroteOn - b.wroteOn));
				}
			});

	const createWebSocketClient = () => {
		return new Client({
			brokerURL: 'ws://frontend.local/websocket',
		});
	};

	useEffect(() => {
		if (conversationId == null) {
			navigate('/');
			return;
		}

		loadMessages(conversationId);

		websocketClient = createWebSocketClient();
		websocketClient.onConnect = () => {
			websocketClient.subscribe(
				`/topic/conversations/${conversationId}/messages`,
				(message) => {
					loadConversations(userId);
					loadMessages(conversationId);
				}
			);
		};
		websocketClient.activate();

		return () => {
			if (websocketClient) {
				websocketClient.deactivate();
			}
		};
	}, [conversationId]);

	useEffect(() => {
		if (scrollContainerRef.current) {
			const scrollContainer = scrollContainerRef.current;
			scrollContainer.scrollTop = scrollContainer.scrollHeight;
		}
	}, [messages]);

	const onChange = (e) => {
		setMessage(e.target.value);
	};

	const handleSubmit = (e) => {
		e.preventDefault();

		writeMessage(conversationId, userId, message).then((response) => {
			setMessage('');
		});
	};

	return (
		<div className="container py-5 px-4">
			{!isCustomer && conversation && (
				<div className="row">
					<div className="col-md-6 px-0">
						<div className="text-left mb-2 mt-2 card">
							<div className="card-body">
								<div className="font-weight-bold card-title h5">
									{conversation.subject} (
									{conversation.currentStatus.state})
								</div>
								<p className="small mb-1 card-text">
									{conversation.customer.name} (
									{conversation.customer.email})
								</p>
							</div>
						</div>
					</div>
				</div>
			)}

			<div className="row overflow-hidden shadow border">
				{/* <!-- Users box--> */}
				{isCustomer && (
					<div className="col-4 px-0">
						<div className="bg-white">
							<div className="messages-box">
								<div className="list-group rounded-0">
									{conversations.map((c) => (
										<Link
											key={c.id}
											to={`/conversations?conversationId=${
												c.id
											}${
												isCustomer
													? `&userId=${userId}`
													: ''
											}`}
											className={`list-group-item list-group-item-action py-3 ${
												conversation &&
												c.id === conversation.id
													? 'active text-white'
													: ''
											}   rounded-0`}
										>
											<div className="media">
												<div className="row">
													<div className="col-5 d-flex justify-content-start align-items-center">
														<img
															src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
															alt="user"
															width="50"
															className="rounded-circle"
														/>
														<p className="m-0 ms-2">
															{isCustomer
																? c
																		.assignedAgent
																		.name
																: c.customer
																		.name}
														</p>
													</div>
													<div className="col d-flex justify-content-end align-items-center">
														{moment(
															c.lastMessage
																.wroteOn
														)
															.utc()
															.fromNow()}
													</div>
												</div>

												<div className="media-body ml-4">
													<p className="small mt-1 mb-3">
														{c.subject}
													</p>
													<p className="mb-0 small">
														{c.lastMessage
															.userId ===
														userId ? (
															<b>You: </b>
														) : (
															''
														)}
														{c.lastMessage.content}
													</p>
												</div>
											</div>
										</Link>
									))}
								</div>
							</div>
						</div>
					</div>
				)}
				{/* <!-- Chat Box--> */}
				<div className={`${isCustomer ? 'col-8' : 'col-12'} px-0`}>
					<div
						className="px-4 py-5 chat-box bg-white"
						ref={scrollContainerRef}
					>
						{/* <!-- Sender Message--> */}
						{messages.map((m) => (
							<div
								key={m.id}
								className={`media w-50 mb-3 ${
									m.userId != userId ? '' : 'ms-auto'
								}`}
							>
								{m.userId != userId && isCustomer && (
									<div className="d-flex align-items-center">
										<div>
											<img
												src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
												alt="user"
												width="50"
												className="rounded-circle"
											/>
										</div>
									</div>
								)}

								<div
									className={`media-body ${
										m.userId != userId ? 'ml-3' : ''
									}`}
								>
									<div
										className={`${
											m.userId != userId
												? 'chat-text-background-other mt-2'
												: 'bg-primary'
										} rounded py-2 px-3 mb-2`}
									>
										<p
											className={`text-small mb-0 ${
												m.userId != userId
													? 'text-muted'
													: 'text-white'
											} `}
										>
											{m.content}
										</p>
									</div>
									<p className="small text-muted">
										{moment(m.wroteOn)
											.local()
											.format('LT | MMM DD')}
									</p>
								</div>
							</div>
						))}
					</div>

					{/* <!-- Typing area --> */}
					<form
						action="#"
						noValidate
						onSubmit={handleSubmit}
						className="bg-light typing-area"
					>
						<div className="input-group">
							<input
								name="message"
								type="text"
								placeholder={
									conversation &&
									conversation.currentStatus.state ===
										'CLOSED'
										? 'Conversation closed, typing messages disabled'
										: 'Type a message'
								}
								aria-describedby="button-addon2"
								className="form-control rounded-0 bg-light typing-area-input py-4 shadow-none"
								onChange={onChange}
								disabled={
									conversation &&
									conversation.currentStatus.state ===
										'CLOSED'
								}
								value={message}
							/>
							<div className="input-group-append">
								<button
									id="button-addon2"
									type="submit"
									className="btn btn-link"
								>
									<i className="fa fa-paper-plane"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	);
}

export default ConversationChat;
