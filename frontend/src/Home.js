import Layout from './Layout';
import ContactSupport from './ContactSupport';
import SearchSupport from './SearchSupport';

function Home(props) {
	return (
		<Layout>
			<h1 className="mb-5 text-primary-color text-center">
				How can we help you?
			</h1>

			<SearchSupport />
			<ContactSupport />
		</Layout>
	);
}

export default Home;
