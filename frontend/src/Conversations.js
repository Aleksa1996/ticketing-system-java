import Layout from './Layout';
import ConversationChat from './ConversationChat';
import { useEffect } from 'react';

function Conversations(props) {
	useEffect(() => {
		document.title = 'Conversations | Ticketing system';
	}, []);

	return (
		<Layout>
			<h1 className="mt-5 mb-3 text-primary-color text-center">
				Conversations
			</h1>

			<ConversationChat />
		</Layout>
	);
}

export default Conversations;
