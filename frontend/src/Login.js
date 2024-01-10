import { useEffect } from 'react';
import Layout from './Layout';
import LoginForm from './LoginForm';

function Login(props) {
	useEffect(() => {
		document.title = 'Login | Ticketing system';
	}, []);

	return (
		<Layout>
			<LoginForm />
		</Layout>
	);
}

export default Login;
