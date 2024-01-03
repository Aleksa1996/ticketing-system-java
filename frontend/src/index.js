import React, { useState } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Home from './Home';
import Login from './Login';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import { createHashRouter, RouterProvider } from 'react-router-dom';
import Conversations from './Conversations';
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
		path: '/conversations/:id',
		element: <Conversations />,
		children: [],
	},
]);

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
