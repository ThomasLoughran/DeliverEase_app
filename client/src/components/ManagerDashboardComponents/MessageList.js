import { useEffect, useState } from "react";
import { useUser } from "../../contexts/UserContext";
import '../../styles/MessageList.css'

const MessageList = () => {

    const { user } = useUser();
    const [orders, setOrders] = useState([]);
    const [distCentreId, setDistCentreId] = useState(user.distributionCentre.id);


// In this file a "message" is an order. 
// fetch all orders that have an issue.

useEffect(() => {

    console.log(user);
    // console.log(distCentreId);

    fetchIssues();




}, [])


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
        console.log("This is data", data);

    } catch (error) {
        console.error('Error during receiving messages:', error);
    }
}

    const messageListComponents = orders.map((order) => {

        return (
            <>
                <li className="order-message">
                    <p>Order Id: {order.id}</p>
                    <p>{order.issue}</p>
                    <p>{order.timeIssuePosted}</p>
                    <button>Confirm Manager Review</button>
                </li>


            </>
        )

    })



    // const dropDownComponents = distributionCentres.map((distributionCentre) => {
    //     return (
    //         <>
    //             <option value={distributionCentre.id}>
    //                 {distributionCentre.location}
    //             </option>
    //         </>
    //     )

    // })






    return (
        <>
            <h2 className="message-list-title">Message List</h2>
            <ul className="order-message-list">
                {messageListComponents}
            </ul>
        </>
    );
}

export default MessageList;