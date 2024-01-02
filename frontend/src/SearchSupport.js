import { Form, FormControl, Card, Row, Col } from 'react-bootstrap';
import { useState } from 'react';

var faqs = [
	{
		subject: 'Account Management',
		question: 'How do I create an account on your e-commerce platform?',
		answer: "You can create an account by clicking on the 'Sign Up' button and providing the required information.",
	},
	{
		subject: 'Account Management',
		question: 'Can I change the email address associated with my account?',
		answer: "Yes, you can update your email address in the 'Account Settings' section after logging into your account.",
	},
	{
		subject: 'Order Process',
		question: 'How can I track my order?',
		answer: "You can track your order by logging into your account and checking the 'Order History' section for real-time updates.",
	},
	{
		subject: 'Order Process',
		question: 'What should I do if I receive a damaged product?',
		answer: 'Please contact our customer support with photos of the damaged item, and we will assist you in the return or replacement process.',
	},
	{
		subject: 'Payment Options',
		question: 'What payment methods do you accept?',
		answer: 'We accept credit cards, debit cards, PayPal, and other secure payment methods. Check our payment options during the checkout process.',
	},
	{
		subject: 'Payment Options',
		question: 'Do you offer cash on delivery (COD)?',
		answer: "Yes, we offer cash on delivery as a payment option for eligible orders. Please check if it's available in your location during checkout.",
	},
	{
		subject: 'Shipping',
		question: 'How much is the shipping cost?',
		answer: 'Shipping costs vary based on your location and the chosen shipping method. You can view the shipping cost during the checkout process before making a payment.',
	},
	{
		subject: 'Shipping',
		question: 'Do you offer international shipping?',
		answer: 'Yes, we offer international shipping to select countries. You can check if we deliver to your country during the checkout process.',
	},
	{
		subject: 'Returns and Refunds',
		question: 'What is your return policy?',
		answer: "Our return policy allows you to return items within 30 days of purchase. Visit our 'Returns' page for detailed instructions.",
	},
	{
		subject: 'Returns and Refunds',
		question: 'How long does it take to process a refund?',
		answer: 'Refunds are typically processed within 5-7 business days after we receive the returned item. The time may vary depending on your payment method.',
	},
	{
		subject: 'Product Information',
		question: "Can I cancel an order after I've placed it?",
		answer: "You can cancel your order within a specific timeframe after placing it. Visit the 'Order History' section in your account to check if cancellation is still possible.",
	},
	{
		subject: 'Product Information',
		question: 'Are the product colors accurate on the website?',
		answer: 'We strive to display accurate colors on our website, but variations may occur. Refer to the product descriptions and customer reviews for additional insights.',
	},
	{
		subject: 'Account Security',
		question: 'How do I reset my password?',
		answer: "You can reset your password by clicking on the 'Forgot Password' link on the login page and following the instructions sent to your registered email address.",
	},
	{
		subject: 'Account Security',
		question: 'Is my personal information secure?',
		answer: 'Yes, we prioritize the security of your personal information. We use encryption and follow industry best practices to protect your data.',
	},
	{
		subject: 'Promotions and Discounts',
		question: 'How do I apply a discount code?',
		answer: 'During the checkout process, you can enter the discount code in the designated field. The discount will be applied to your order if the code is valid.',
	},
	{
		subject: 'Promotions and Discounts',
		question: 'Can I use multiple discount codes on a single order?',
		answer: 'Generally, only one discount code can be applied per order. Check the terms and conditions of each promotion for specific details.',
	},
	{
		subject: 'Account Notifications',
		question: 'How can I subscribe or unsubscribe from email newsletters?',
		answer: "You can manage your email preferences in the 'Notifications' or 'Preferences' section of your account settings. Choose your communication preferences accordingly.",
	},
	{
		subject: 'Account Notifications',
		question: 'Why am I not receiving order confirmation emails?',
		answer: "Check your spam or junk folder to ensure the emails aren't filtered there. If the issue persists, contact our customer support for assistance.",
	},
	{
		subject: 'Technical Support',
		question: "I'm having trouble placing an order. What should I do?",
		answer: "If you're experiencing technical difficulties, try clearing your browser cache or using a different browser. If the problem persists, contact our technical support for assistance.",
	},
	{
		subject: 'Technical Support',
		question: 'Are there any browser requirements for using your website?',
		answer: 'Our website is optimized for modern browsers. Ensure you are using an up-to-date version of popular browsers such as Chrome, Firefox, Safari, or Edge for the best experience.',
	},
	{
		subject: 'Product Availability',
		question: 'How can I check if a product is in stock?',
		answer: "On the product page, you'll find the current stock status. If a product is out of stock, you may have the option to receive notifications when it becomes available again.",
	},
	{
		subject: 'Product Availability',
		question: 'Can I pre-order items that are currently out of stock?',
		answer: 'Yes, we may offer pre-orders for certain products that are out of stock. Check the product page for pre-order availability and details.',
	},
	{
		subject: 'Account Termination',
		question: 'How can I close my account?',
		answer: 'To close your account, contact our customer support with your request. Keep in mind that closing your account will permanently delete your order history and personal information.',
	},
	{
		subject: 'Account Termination',
		question: 'What happens to my data if I close my account?',
		answer: 'Closing your account will result in the permanent deletion of your personal information. We adhere to privacy regulations to ensure the secure handling of your data.',
	},
	{
		subject: 'Mobile App',
		question: 'Do you have a mobile app?',
		answer: 'Yes, we have a mobile app available for download on both iOS and Android platforms. You can find it in the respective app stores.',
	},
	{
		subject: 'Mobile App',
		question: 'Can I use the same account on the website and mobile app?',
		answer: 'Yes, you can use the same account credentials to log in on both the website and the mobile app. Your account information and order history will be synchronized.',
	},
];

function SearchSupport(props) {
	const [searchTerm, setSearchTerm] = useState('');

	let showFaqs = [...faqs];
	if (searchTerm != '') {
		showFaqs = showFaqs.filter(
			(item) =>
				item.subject.toLowerCase().includes(searchTerm.toLowerCase()) ||
				item.question
					.toLowerCase()
					.includes(searchTerm.toLowerCase()) ||
				item.answer.toLowerCase().includes(searchTerm.toLowerCase())
		);
	}

	return (
		<>
			<Form className="mt-4">
				<Form.Group controlId="searchForm">
					<FormControl
						className="form-control-lg"
						type="text"
						placeholder="I need help with..."
						value={searchTerm}
						onChange={(e) => setSearchTerm(e.target.value)}
					/>
				</Form.Group>
			</Form>

			<Row className="mt-4">
				{showFaqs.slice(0, 5).map((item) => (
					<Col md="6" key={item.answer}>
						<Card className="text-left mb-2 mt-2">
							<Card.Body>
								<Card.Title className="font-weight-bold">
									{item.question}
								</Card.Title>
								<Card.Text className="small mb-1 ps-1">
									{item.subject}
								</Card.Text>
								<Card.Text className="small ps-1">
									{item.answer}
								</Card.Text>
							</Card.Body>
						</Card>
					</Col>
				))}
			</Row>
		</>
	);
}

export default SearchSupport;
