import React, { useState } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Home from './Home';
import Login from './Login';
import Dashboard from './Dashboard';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import { createHashRouter, RouterProvider } from 'react-router-dom';
import Conversations from './Conversations';
import ProtectedRoute from './ProtectedRoute';
import { UserContext } from './UserContext';
import { jwtDecode } from 'jwt-decode';

const router = createHashRouter([
	{
		path: '/',
		element: <Home />,
		children: [],
	},
	{
		path: '/login',
		element: <Login />,
		children: [],
	},
	{
		path: '/conversations',
		element: <Conversations />,
		children: [],
	},
	{
		element: <ProtectedRoute />,
		children: [
			{
				path: '/dashboard',
				element: <Dashboard />,
			},
		],
	},
]);

const me = (accessToken) => {
	return fetch('/api/v1/auth/me', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Authorization: `Bearer ${accessToken}`,
		},
	});
};

const root = ReactDOM.createRoot(document.getElementById('root'));

function App() {
	let initialUser = null;
	let accessToken = localStorage.getItem('access_token');

	if (accessToken) {
		try {
			initialUser = jwtDecode(accessToken);
		} catch (e) {
			initialUser = null;
		}

		me(accessToken).then((response) => {
			if (response.status !== 200) {
				setUser(null);
				localStorage.removeItem('access_token');
			}
		});
	}

	const [user, setUser] = useState(initialUser);

	return (
		<React.StrictMode>
			<UserContext.Provider value={{ user, setUser }}>
				<RouterProvider router={router} />
			</UserContext.Provider>
		</React.StrictMode>
	);
}

root.render(<App />);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
