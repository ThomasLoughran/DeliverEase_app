import React from "react";
import Map from "../map/Map";
import { useUser } from "../../contexts/UserContext";
import { useState } from "react";
import '../../styles/OrderDetails.css'

const OrderDetails = ({
    currentOrder,
    handleSuccessfulDelivery,
    handleUnsuccessfulDelivery,
    unsuccessfulClicked,
    selectedIssue,
    setSelectedIssue,
    handleIssueSubmit,
}) => {



    const { user } = useUser();
    const [previousOrder, setPreviousOrder] = useState(user.distributionCentre);

    const incrementPreviousOrder = () => {
        setPreviousOrder(currentOrder);
    }

    return (
        <div>
            <h2>Current Order:</h2>
            <p>Order ID: {currentOrder.id}</p>
            <p>Address: {currentOrder.address}</p>

            <button className='success-button' onClick={() => {
                incrementPreviousOrder();
                handleSuccessfulDelivery(); 
                }}>Success!</button>
            <button onClick={() => {
                incrementPreviousOrder();
                handleUnsuccessfulDelivery(); 
                }}>Problem...</button>

            {unsuccessfulClicked && (
                <div>
                    <label>Select Issue:</label>
                    <select onChange={(e) => setSelectedIssue(parseInt(e.target.value))} value={selectedIssue || ''}>
                        <option className='issue-dropdown' value="" disabled>Select an issue</option>
                        <option value="1">No Access</option>
                        <option value="2">Refusal by Recipient</option>
                        <option value="3">Poor Weather</option>
                        <option value="4">Lost in Transit</option>
                        <option value="5">Security Issues</option>
                        <option value="6">Parcel Damage</option>
                        <option value="7">Lost Parcel</option>
                        <option value="8">Other</option>
                    </select>
                    <button className='issue-button' onClick={handleIssueSubmit}>Submit Issue</button>
                </div>
            )}
            <Map className='map' currentOrder={currentOrder} previousOrder={previousOrder}/>
        </div>
    );
}

export default OrderDetails;
