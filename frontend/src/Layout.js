import { Navbar, Container, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { UserContext } from './UserContext';
import { useContext, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function Layout(props) {
	const navigate = useNavigate();
	const { user, setUser } = useContext(UserContext);

	const handleLogout = (e) => {
		e.preventDefault();
		setUser(null);
		localStorage.removeItem('access_token');
		navigate('/');
	};

	return (
		<>
			<Navbar bg="light" data-bs-theme="light">
				<Container>
					<Navbar.Brand
						as={Link}
						to="/"
						className="text-primary-color"
					>
						Ticketing System
					</Navbar.Brand>
					<Navbar.Collapse className="justify-content-end">
						{user && (
							<Link
								to="/dashboard"
								className="btn btn-background-color me-3"
							>
								Dashboard
							</Link>
						)}
						{user ? (
							<Button
								className="btn btn-background-color"
								type="button"
								onClick={handleLogout}
							>
								Logout
							</Button>
						) : (
							<Link
								to="/login"
								className="btn btn-background-color"
							>
								Login
							</Link>
						)}
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
