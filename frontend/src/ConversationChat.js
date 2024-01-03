import { useEffect, useState, useContext } from 'react';
import { Form, Row, Col, Button, Alert } from 'react-bootstrap';
import moment from 'moment';
import { useParams, useSearchParams } from 'react-router-dom';
import { UserContext } from './UserContext';

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

function ConversationChat(props) {
	const [messages, setMessages] = useState([]);
	const [message, setMessage] = useState('');
	const [searchParams, setSearchParams] = useSearchParams();
	const { id } = useParams();
	const { user, setUser } = useContext(UserContext);

	const customerId = searchParams.get('customerId');

	let userId = null;
	if (user) {
		userId = user.id;
	}

	if (customerId) {
		userId = customerId;
	}

	const loadMessages = (id) =>
		getMessages(id)
			.then((response) => response.json())
			.then((response) => {
				console.log(response);
				setMessages(response.sort((a, b) => a.wroteOn - b.wroteOn));
			});

	useEffect(() => {
		loadMessages(id);
	}, []);

	const onChange = (e) => {
		setMessage(e.target.value);
	};

	const handleSubmit = (e) => {
		e.preventDefault();

		writeMessage(id, userId, message).then((response) => {
			setMessage('');
		});
	};

	return (
		<div className="container py-5 px-4">
			<div className="row overflow-hidden shadow border">
				{/* <!-- Users box--> */}
				<div className="col-5 px-0">
					<div className="bg-white">
						<div className="messages-box">
							<div className="list-group rounded-0">
								<a className="list-group-item list-group-item-action py-3 active text-white rounded-0">
									<div className="media">
										<img
											src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
											alt="user"
											width="50"
											className="rounded-circle"
										/>
										<div className="media-body ml-4">
											<div className="d-flex align-items-center justify-content-between mb-1">
												<h6 className="mb-0">
													Jason Doe
												</h6>
												<small className="small font-weight-bold">
													25 Dec
												</small>
											</div>
											<p className="font-italic mb-0 text-small">
												Lorem ipsum dolor sit amet,
												consectetur adipisicing elit,
												sed do eiusmod tempor incididunt
												ut labore.
											</p>
										</div>
									</div>
								</a>

								<a
									href="#"
									className="list-group-item list-group-item-action py-3 list-group-item-light rounded-0"
								>
									<div className="media">
										<img
											src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
											alt="user"
											width="50"
											className="rounded-circle"
										/>
										<div className="media-body ml-4">
											<div className="d-flex align-items-center justify-content-between mb-1">
												<h6 className="mb-0">
													Jason Doe
												</h6>
												<small className="small font-weight-bold">
													14 Dec
												</small>
											</div>
											<p className="font-italic text-muted mb-0 text-small">
												Lorem ipsum dolor sit amet,
												consectetur. incididunt ut
												labore.
											</p>
										</div>
									</div>
								</a>

								<a
									href="#"
									className="list-group-item list-group-item-action py-3 list-group-item-light rounded-0"
								>
									<div className="media">
										<img
											src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
											alt="user"
											width="50"
											className="rounded-circle"
										/>
										<div className="media-body ml-4">
											<div className="d-flex align-items-center justify-content-between mb-1">
												<h6 className="mb-0">
													Jason Doe
												</h6>
												<small className="small font-weight-bold">
													9 Nov
												</small>
											</div>
											<p className="font-italic text-muted mb-0 text-small">
												consectetur adipisicing elit,
												sed do eiusmod tempor incididunt
												ut labore.
											</p>
										</div>
									</div>
								</a>

								<a
									href="#"
									className="list-group-item list-group-item-action py-3 list-group-item-light rounded-0"
								>
									<div className="media">
										<img
											src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
											alt="user"
											width="50"
											className="rounded-circle"
										/>
										<div className="media-body ml-4">
											<div className="d-flex align-items-center justify-content-between mb-1">
												<h6 className="mb-0">
													Jason Doe
												</h6>
												<small className="small font-weight-bold">
													18 Oct
												</small>
											</div>
											<p className="font-italic text-muted mb-0 text-small">
												Lorem ipsum dolor sit amet,
												consectetur adipisicing elit,
												sed do eiusmod tempor incididunt
												ut labore.
											</p>
										</div>
									</div>
								</a>

								<a
									href="#"
									className="list-group-item list-group-item-action py-3 list-group-item-light rounded-0"
								>
									<div className="media">
										<img
											src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
											alt="user"
											width="50"
											className="rounded-circle"
										/>
										<div className="media-body ml-4">
											<div className="d-flex align-items-center justify-content-between mb-1">
												<h6 className="mb-0">
													Jason Doe
												</h6>
												<small className="small font-weight-bold">
													17 Oct
												</small>
											</div>
											<p className="font-italic text-muted mb-0 text-small">
												consectetur adipisicing elit,
												sed do eiusmod tempor incididunt
												ut labore.
											</p>
										</div>
									</div>
								</a>

								<a
									href="#"
									className="list-group-item list-group-item-action py-3 list-group-item-light rounded-0"
								>
									<div className="media">
										<img
											src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
											alt="user"
											width="50"
											className="rounded-circle"
										/>
										<div className="media-body ml-4">
											<div className="d-flex align-items-center justify-content-between mb-1">
												<h6 className="mb-0">
													Jason Doe
												</h6>
												<small className="small font-weight-bold">
													2 Sep
												</small>
											</div>
											<p className="font-italic text-muted mb-0 text-small">
												Quis nostrud exercitation
												ullamco laboris nisi ut aliquip
												ex ea commodo consequat.
											</p>
										</div>
									</div>
								</a>

								<a
									href="#"
									className="list-group-item list-group-item-action py-3 list-group-item-light rounded-0"
								>
									<div className="media">
										<img
											src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
											alt="user"
											width="50"
											className="rounded-circle"
										/>
										<div className="media-body ml-4">
											<div className="d-flex align-items-center justify-content-between mb-1">
												<h6 className="mb-0">
													Jason Doe
												</h6>
												<small className="small font-weight-bold">
													30 Aug
												</small>
											</div>
											<p className="font-italic text-muted mb-0 text-small">
												Lorem ipsum dolor sit amet,
												consectetur adipisicing elit,
												sed do eiusmod tempor incididunt
												ut labore.
											</p>
										</div>
									</div>
								</a>

								<a
									href="#"
									className="list-group-item list-group-item-action py-3 list-group-item-light rounded-0"
								>
									<div className="media">
										<img
											src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
											alt="user"
											width="50"
											className="rounded-circle"
										/>
										<div className="media-body ml-4">
											<div className="d-flex align-items-center justify-content-between mb-3">
												<h6 className="mb-0">
													Jason Doe
												</h6>
												<small className="small font-weight-bold">
													21 Aug
												</small>
											</div>
											<p className="font-italic text-muted mb-0 text-small">
												Lorem ipsum dolor sit amet,
												consectetur adipisicing elit,
												sed do eiusmod tempor incididunt
												ut labore.
											</p>
										</div>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				{/* <!-- Chat Box--> */}
				<div className="col-7 px-0">
					<div className="px-4 py-5 chat-box bg-white">
						{/* <!-- Sender Message--> */}
						{messages.map((m) => (
							<div
								key={m.id}
								className={`media w-50 mb-3 ${
									m.userId != userId ? '' : 'ms-auto'
								}`}
							>
								{m.userId != userId && (
									<img
										src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
										alt="user"
										width="50"
										className="rounded-circle"
									/>
								)}

								<div
									className={`media-body ${
										m.userId != userId ? 'ml-3' : ''
									}`}
								>
									<div
										className={`${
											m.userId != userId
												? 'bg-light'
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
											.utc()
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
								placeholder="Type a message"
								aria-describedby="button-addon2"
								className="form-control rounded-0 bg-light typing-area-input py-4 shadow-none"
								onChange={onChange}
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
