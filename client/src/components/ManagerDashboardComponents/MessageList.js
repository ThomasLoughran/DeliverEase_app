import { useEffect, useState } from "react";
import { useUser } from "../../contexts/UserContext";
import '../../styles/MessageList.css'
import refreshButton from '../../assets/refresh-button.png';

const MessageList = ({ setNotificationRefresh }) => {

    const { user } = useUser();
    const [orders, setOrders] = useState([]);
    const [distCentreId, setDistCentreId] = useState(user.distributionCentre.id);
    const [orderId, setOrderId] = useState(null);

    useEffect(() => {
        fetchIssues();
    }, [orderId])

    const handlePatchManagerReviewed = async (orderId) => {
        try {
            await patchManagerReviewed(orderId);
            setOrderId(orderId);
        } catch (error) {
            console.error('Error handling manager review:', error);
        }
    };

    const fetchIssues = async () => {
        try {
            const response = await fetch(`http://localhost:8080/orders/issue/all?distCentreId=${distCentreId}&isManagerReviewed=${false}`, {
                method: "GET",
            });
            if (!response.ok) {
                throw new Error(`Failed to receive messages: ${response.status} ${response.statusText}`);
            }

            const data = await response.json();
            if (!data) {
                throw new Error("Empty response received");
            }
            setOrders(data);
        } catch (error) {
            console.error('Error during receiving messages:', error);
        }
    }

    const patchManagerReviewed = async (orderId) => {
        try {
            const response = await fetch(`http://localhost:8080/orders/manager-review/${orderId}?isManagerReviewed=true`, {
                method: "PATCH",
            });

            if (!response.ok) {
                throw new Error(`Failed to send patched message: ${response.status} ${response.statusText}`);
            }

            const data = await response.json();

            if (!data) {
                throw new Error("Empty response received");
            }

            setOrders(data);

        } catch (error) {
            console.error('Error sending patched message:', error);
        }
    }

    const messageListComponents = orders.length > 0 ? (
        orders.map((order) => (
            <li key={order.id} className="order-message">
                <p className="message-order-id">Order Id: {order.id}</p>
                <p>Issue: {order.issue}</p>
                <p>Time Posted: {order.timeIssuePosted}</p>
                <p>Address: {order.address}</p>
                <button
                    className="confirm-manager-review-button"
                    onClick={() => handlePatchManagerReviewed(order.id)}
                    value={order.id}
                >
                    Confirm Manager Review
                </button>
            </li>
        ))
    ) : (
        <p>No orders have issues.</p>
    );

    return (
        <>
            <div className="title-and-refresh-button-container">
                <h2 className="message-list-title">Message List</h2>
                <button className="message-list-refresh-button" onClick={() => {
                    fetchIssues()
                }}>
                    <img className="refresh-symbol" src={refreshButton} alt="Refresh"></img>

                </button>
            </div>
            <ul className="order-message-list">
                {messageListComponents}
            </ul>
        </>
    );
}

export default MessageList;