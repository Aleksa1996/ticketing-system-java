import { Form, Row, Col, Button } from 'react-bootstrap';

function LoginForm(props) {
	return (
		<Form className="mt-5 mb-5">
			<h1 className="mb-5 text-primary-color text-center">Login</h1>
			<Row>
				<Col className="mx-auto" md="4">
					<Form.Control className="mb-4" placeholder="Email" />
					<Form.Control type="password" placeholder="Password" />
					<div className="d-grid gap-2">
						<Button
							className="mt-3 btn-background-color"
							variant="primary"
							type="button"
							size="md"
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
