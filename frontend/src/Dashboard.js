import { useEffect, useState, useContext } from 'react';
import Layout from './Layout';
import { UserContext } from './UserContext';
import moment from 'moment';
import Dropdown from 'react-bootstrap/Dropdown';
import { Link } from 'react-router-dom';

const getConversations = () => {
	return fetch(`/api/v1/conversations?page=1&size=20`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	});
};

const selfAssign = (id, agentId) => {
	return fetch(`/api/v1/conversations/${id}/assign-agent`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			agentId,
		}),
	});
};

function Dashboard(props) {
	const { user, setUser } = useContext(UserContext);
	const [conversations, setConversations] = useState([]);

	const loadConversations = (id) =>
		getConversations(id)
			.then((response) => response.json())
			.then((response) => {
				if (!response.error) {
					setConversations(response);
				}
			});

	useEffect(() => {
		loadConversations();
	}, []);

	const handleSelfAssign = (id) => {
		console.log(id);
		selfAssign(id)
			.then((response) => response.json())
			.then((response) => console.log(response));
	};

	const handleClose = (id) => {
		console.log(id);
	};

	return (
		<Layout>
			<h1 className="mt-5 mb-3 text-primary-color text-center">
				Conversations
			</h1>
			<div className="bd-example">
				<table className="table table-hover align-middle">
					<thead className="table-light">
						<tr>
							<th scope="col">Subject</th>
							<th scope="col">Name</th>
							<th scope="col">Assigned agent</th>
							<th scope="col">Status</th>
							<th scope="col">Date</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						{conversations.map((c) => (
							<tr key={c.id}>
								<th scope="row">
									<Link
										to={`/conversations?conversationId=${c.id}`}
									>
										{c.subject}
									</Link>
								</th>
								<td>{c.customer.name}</td>
								<td>{c.assignedAgent.name}</td>
								<td>{c.currentStatus.state}</td>
								<td>
									{moment(c.createdOn)
										.local()
										.format('MMM DD, LT')}
								</td>
								<td>
									<Dropdown>
										<Dropdown.Toggle
											variant="primary"
											id="dropdown-basic"
											className="btn-background-color px-4"
										>
											Actions
										</Dropdown.Toggle>
										<Dropdown.Menu>
											<Dropdown.Item
												onClick={(e) => {
													e.preventDefault();
													handleSelfAssign(c.id);
												}}
											>
												Self assign
											</Dropdown.Item>
											<Dropdown.Item
												onClick={(e) => {
													e.preventDefault();
													handleClose(c.id);
												}}
											>
												Close
											</Dropdown.Item>
										</Dropdown.Menu>
									</Dropdown>
								</td>
							</tr>
						))}
					</tbody>
				</table>
			</div>
		</Layout>
	);
}

export default Dashboard;
