import { Navbar, Container } from 'react-bootstrap';
import { Link } from "react-router-dom";

function Layout(props) {
	return (
		<>
			<Navbar bg="light" data-bs-theme="light">
				<Container>
					<Navbar.Brand as={Link} to="/" className="text-primary-color">
						Ticketing System
					</Navbar.Brand>
					<Navbar.Collapse className="justify-content-end">
						<Link to="/login" className="btn btn-background-color">
							Login
						</Link>
					</Navbar.Collapse>
				</Container>
			</Navbar>
			<Container className="main-content-container">
				{props.children}
			</Container>
			<footer className="mt-5 bg-light text-center">
				<Container>
					<p className="text-secondary-color">
						Ticketing system &copy; {new Date().getFullYear()}. All
						rights reserved.
					</p>
					<p className="text-secondary-color">
						Contact: ticketing-system@email.com
					</p>
				</Container>
			</footer>
		</>
	);
}

export default Layout;
