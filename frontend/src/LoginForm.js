import { Form, Row, Col, Button, Alert } from 'react-bootstrap';
import { useEffect, useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from './UserContext';
import { jwtDecode } from 'jwt-decode';

const validateFormData = (formData) => {
	const errors = {};

	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	if (!formData.email.trim() || !emailRegex.test(formData.email)) {
		errors.email = 'Valid email is required';
	}

	if (!formData.password.trim()) {
		errors.password = 'Password is required';
	}

	return errors;
};

const login = (email, password) => {
	return fetch('/api/v1/auth/login', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			email,
			password,
		}),
	});
};

function LoginForm(props) {
	const navigate = useNavigate();
	const { user, setUser } = useContext(UserContext);

	const [formFieldTouched, setFormFieldTouched] = useState({
		email: false,
		password: false,
	});

	const [formData, setFormData] = useState({
		email: '',
		password: '',
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

	useEffect(() => {
		setErrors(validateFormData(formData));
	}, [formData]);

	useEffect(() => {
		if (user) {
			navigate('/dashboard');
		}
	}, [user]);

	const handleSubmit = async (e) => {
		e.preventDefault();

		const response = await login(formData.email, formData.password);

		if (response.status === 200) {
			const responseJson = await response.json();
			localStorage.setItem('access_token', responseJson.access_token);
			try {
				setUser(jwtDecode(responseJson.access_token));
			} catch (e) {}
		}

		if (response.status >= 500) {
			setSubmitMessage({
				success: false,
				message: 'Server side error, please try again later.',
			});
		}

		if (response.status >= 400 && response.status < 500) {
			let message = 'Client side error, please try again later.';
			try {
				const responseJson = await response.json();
				message = responseJson.message;
			} catch (e) {}

			setSubmitMessage({
				success: false,
				message,
			});
		}
	};

	return (
		<Form className="mt-5 mb-5" noValidate onSubmit={handleSubmit}>
			<h1 className="mb-5 text-primary-color text-center">Login</h1>
			<Row>
				<Col className="mx-auto" md="4">
					<Form.Floating className="mb-4">
						<Form.Control
							id="email"
							placeholder="Email*"
							name="email"
							onChange={onChange}
							type="email"
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
							id="password"
							placeholder="Password*"
							name="password"
							onChange={onChange}
							type="password"
							isInvalid={
								formFieldTouched.password && errors.password
							}
							value={formData.password}
						/>
						<label htmlFor="password">Password</label>
						<Form.Control.Feedback type="invalid">
							{errors.password}
						</Form.Control.Feedback>
					</Form.Floating>
					{submitMessage.message != '' && (
						<Alert
							variant={
								submitMessage.success ? 'success' : 'danger'
							}
						>
							{submitMessage.message}
						</Alert>
					)}
					<div className="d-grid gap-2">
						<Button
							className="mt-3 btn-background-color"
							variant="primary"
							type="submit"
							size="md"
							disabled={Object.keys(errors).length !== 0}
						>
							Login
						</Button>
					</div>
				</Col>
			</Row>
		</Form>
	);
}

export default LoginForm;
