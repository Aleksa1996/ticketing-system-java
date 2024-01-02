import Layout from './Layout';
import ConversationChat from './ConversationChat';

function Conversations(props) {
	return (
		<Layout>
			<h1 className="mt-5 mb-3 text-primary-color text-center">
				Conversation
			</h1>

			<ConversationChat />
		</Layout>
	);
}

export default Conversations;
