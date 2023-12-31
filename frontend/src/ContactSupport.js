import { Form, Row, Col } from 'react-bootstrap';

function ContactSupport(props) {
	return (
		<Form className="mt-5 mb-5">
			<h2 className="mb-5 text-primary-color text-center">Contact Support</h2>
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
		</Form>
	);
}

export default ContactSupport;
