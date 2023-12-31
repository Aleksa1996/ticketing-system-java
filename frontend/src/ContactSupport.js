import { Form, Row, Col, Button } from 'react-bootstrap';

function ContactSupport(props) {
	return (
		<Form className="mt-5 mb-5">
			<h2 className="mb-5 text-primary-color text-center">
				Contact Support
			</h2>
			<Row>
				<Col>
					<Form.Control className="mb-4" placeholder="Name" />
					<Form.Control className="mb-4" placeholder="Email" />
					<Form.Control className="mb-4" placeholder="Subject" />
				</Col>
				<Col>
					<Form.Control
						as="textarea"
						rows={6}
						placeholder="Message"
					/>
				</Col>
			</Row>
			<Row>
				<Col className="text-center">
					<Button
						className="mt-3 btn-background-color px-5"
						variant="primary"
						type="button"
						size="md"
					>
						Submit
					</Button>
				</Col>
			</Row>
		</Form>
	);
}

export default ContactSupport;
