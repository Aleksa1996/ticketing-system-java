import { useEffect, useState } from 'react';
import { Form, Row, Col, Button, Alert } from 'react-bootstrap';
import moment from 'moment';

const validateFormData = (formData) => {
	const errors = {};

	if (!formData.name.trim()) {
		errors.name = 'Name is required';
	} else if (
		formData.name.trim().length < 2 ||
		formData.name.trim().length > 255
	) {
		errors.name = 'Name should be between 2 and 255 characters';
	}

	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	if (!formData.email.trim() || !emailRegex.test(formData.email)) {
		errors.email = 'Valid email is required';
	}

	if (!formData.subject.trim()) {
		errors.subject = 'Subject is required';
	} else if (
		formData.subject.trim().length < 2 ||
		formData.subject.trim().length > 255
	) {
		errors.subject = 'Subject should be between 2 and 255 characters';
	}

	if (!formData.message.trim()) {
		errors.message = 'Message is required';
	} else if (
		formData.message.trim().length < 2 ||
		formData.message.trim().length > 255
	) {
		errors.message = 'Message should be between 2 and 255 characters';
	}

	return errors;
};

const openNewConversation = async (name, email, subject, message) => {
	return fetch('/api/v1/conversations', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			name,
			email,
			subject,
			message,
		}),
	});
};

const messages = [
	{
		id: 4,
		user: 'Aleksa Jovanovic',
		content: 'Test',
		wroteOn: 1704230907047,
		isAgent: false,
	},
	{
		id: 2,
		user: 'Aleksa Jovanovic',
		content: 'Test',
		wroteOn: 1704230907047,
		isAgent: true,
	},
	{
		id: 1,
		user: 'Aleksa Jovanovic',
		content: 'Test',
		wroteOn: 1704230907047,
		isAgent: false,
	},
	{
		id: 56,
		user: 'Aleksa Jovanovic',
		content: 'Test',
		wroteOn: 1704230907047,
		isAgent: true,
	},
	{
		id: 23,
		user: 'Aleksa Jovanovic',
		content: 'Test',
		wroteOn: 1704230907100,
		isAgent: false,
	},
];

function ConversationChat(props) {
	const [message, setMessage] = useState('');

	const handleSubmit = (e) => {};
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
									m.isAgent ? '' : 'ms-auto'
								}`}
							>
								{m.isAgent && (
									<img
										src="https://bootstrapious.com/i/snippets/sn-chat/avatar.svg"
										alt="user"
										width="50"
										className="rounded-circle"
									/>
								)}

								<div
									className={`media-body ${
										m.isAgent ? 'ml-3' : ''
									}`}
								>
									<div
										className={`${
											m.isAgent
												? 'bg-light'
												: 'bg-primary'
										} rounded py-2 px-3 mb-2`}
									>
										<p
											className={`text-small mb-0 ${
												m.isAgent
													? 'text-muted'
													: 'text-white'
											} `}
										>
											{m.content}
										</p>
									</div>
									<p className="small text-muted">
										{moment
											.unix(m.wroteOn)
											.format('LT | MMM MM')}
									</p>
								</div>
							</div>
						))}
					</div>

					{/* <!-- Typing area --> */}
					<form action="#" className="bg-light typing-area">
						<div className="input-group">
							<input
								type="text"
								placeholder="Type a message"
								aria-describedby="button-addon2"
								className="form-control rounded-0 bg-light typing-area-input py-4 shadow-none"
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
