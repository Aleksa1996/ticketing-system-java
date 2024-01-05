import { useContext, useEffect } from 'react';
import { useNavigate, Outlet } from 'react-router-dom';
import { UserContext } from './UserContext';

function ProtectedRoute(props) {
	const navigate = useNavigate();
	const { user, setUser } = useContext(UserContext);

	useEffect(() => {
		if (!user) {
			navigate('/login');
		}
	}, [user]);

	return <Outlet />;
}

export default ProtectedRoute;
