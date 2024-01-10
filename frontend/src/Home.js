import Layout from './Layout';
import ContactSupport from './ContactSupport';
import SearchSupport from './SearchSupport';
import { useEffect } from 'react';

function Home(props) {
	useEffect(() => {
		document.title = 'Home | Ticketing system';
	}, []);

	return (
		<Layout>
			<h1 className="mt-5 mb-5 text-primary-color text-center">
				How can we help you?
			</h1>

			<SearchSupport />
			<ContactSupport />
		</Layout>
	);
}

export default Home;
