import { useEffect, useState } from 'react';
import { Form, Row, Col, Button, Alert } from 'react-bootstrap';

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

function ContactSupport(props) {
	const [formFieldTouched, setFormFieldTouched] = useState({
		name: false,
		email: false,
		subject: false,
		message: false,
	});

	const [formData, setFormData] = useState({
		name: '',
		email: '',
		subject: '',
		message: '',
	});

	const [errors, setErrors] = useState({});

	const [submitMessage, setSubmitMessage] = useState({
		success: false,
		message: '',
	});

	const onChange = (e) => {
		setFormData({ ...formData, [e.target.name]: e.target.value });
		setFormFieldTouched({ ...formFieldTouched, [e.target.name]: true });
	};

	const resetForm = () => {
		setFormFieldTouched({
			name: false,
			email: false,
			subject: false,
			message: false,
		});
		setFormData({ name: '', email: '', subject: '', message: '' });
	};

	useEffect(() => {
		setErrors(validateFormData(formData));
	}, [formData]);

	const handleSubmit = async (e) => {
		e.preventDefault();

		const response = await openNewConversation(
			formData.name,
			formData.email,
			formData.subject,
			formData.message
		);

		if (response.status === 200) {
			setSubmitMessage({
				success: true,
				message: 'Form submitted successfully.',
			});
			resetForm();
			return;
		}

		if (response.status >= 500) {
			setSubmitMessage({
				success: false,
				message: 'Server side error, please try again later.',
			});
			return;
		}

		if (response.status >= 400 && response.status < 500) {
			setSubmitMessage({
				success: false,
				message: 'Client side error, please try again later.',
			});
			return;
		}
	};

	return (
		<Form className="mt-5 mb-5" noValidate onSubmit={handleSubmit}>
			<h2 className="mb-5 text-primary-color text-center">
				Contact Support
			</h2>
			<Row>
				<Col>
					<Form.Floating className="mb-4">
						<Form.Control
							id="name"
							placeholder="Name*"
							name="name"
							onChange={onChange}
							isInvalid={formFieldTouched.name && errors.name}
							value={formData.name}
						/>
						<label htmlFor="name">Name</label>
						<Form.Control.Feedback type="invalid">
							{errors.name}
						</Form.Control.Feedback>
					</Form.Floating>
					<Form.Floating className="mb-4">
						<Form.Control
							id="email"
							placeholder="Email*"
							name="email"
							onChange={onChange}
							isInvalid={formFieldTouched.email && errors.email}
							value={formData.email}
						/>
						<label htmlFor="email">Email</label>
						<Form.Control.Feedback type="invalid">
							{errors.email}
						</Form.Control.Feedback>
					</Form.Floating>
					<Form.Floating className="mb-4">
						<Form.Control
							id="subject"
							placeholder="Subject*"
							name="subject"
							onChange={onChange}
							isInvalid={
								formFieldTouched.subject && errors.subject
							}
							value={formData.subject}
						/>
						<label htmlFor="subject">Subject</label>
						<Form.Control.Feedback type="invalid">
							{errors.subject}
						</Form.Control.Feedback>
					</Form.Floating>
				</Col>
				<Col>
					<Form.Floating className="mb-4">
						<Form.Control
							id="message"
							as="textarea"
							placeholder="Message*"
							name="message"
							onChange={onChange}
							style={{ height: '223px' }}
							isInvalid={
								formFieldTouched.message && errors.message
							}
							value={formData.message}
						/>
						<label htmlFor="message">Message</label>
						<Form.Control.Feedback type="invalid">
							{errors.message}
						</Form.Control.Feedback>
					</Form.Floating>
				</Col>
			</Row>
			<Row>
				<Col>
					{submitMessage.message != '' && (
						<Alert
							variant={
								submitMessage.success ? 'success' : 'danger'
							}
						>
							{submitMessage.message}
						</Alert>
					)}
				</Col>
			</Row>
			<Row>
				<Col className="text-center">
					<Button
						className="mt-3 btn-background-color px-5"
						variant="primary"
						type="submit"
						size="md"
						disabled={Object.keys(errors).length !== 0}
					>
						Submit
					</Button>
				</Col>
			</Row>
		</Form>
	);
}

export default ContactSupport;
