import React from "react";

const OrderDetails = ({
    currentOrder,
    handleSuccessfulDelivery,
    handleUnsuccessfulDelivery,
    unsuccessfulClicked,
    selectedIssue,
    setSelectedIssue,
    handleIssueSubmit,
}) => {
    return (
        <div>
            <h2>Current Order</h2>
            <p>Order ID: {currentOrder.id}</p>
            <p>Address: {currentOrder.address}</p>

            <button onClick={handleSuccessfulDelivery}>Successfully Delivered</button>
            <button onClick={handleUnsuccessfulDelivery}>Unsuccessfully Delivered</button>

            {unsuccessfulClicked && (
                <div>
                    <label>Select Issue:</label>
                    <select onChange={(e) => setSelectedIssue(parseInt(e.target.value))} value={selectedIssue || ''}>
                        <option value="" disabled>Select an issue</option>
                        <option value="1">No Access</option>
                        <option value="2">Refusal by Recipient</option>
                        <option value="3">Poor Weather</option>
                        <option value="4">Lost in Transit</option>
                        <option value="5">Security Issues</option>
                        <option value="6">Parcel Damage</option>
                        <option value="7">Lost Parcel</option>
                        <option value="8">Other</option>
                    </select>
                    <button onClick={handleIssueSubmit}>Submit Issue</button>
                </div>
            )}
        </div>
    );
}

export default OrderDetails;
