import React, { useState } from "react";
import Map from "../map/Map";
import { useUser } from "../../contexts/UserContext";

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
    const [mapKey, setMapKey] = useState(0); // Introduce a state variable for re-rendering the map

    const incrementPreviousOrder = () => {
        setPreviousOrder(currentOrder);
        setMapKey(mapKey + 1); // Change the key to trigger re-render
    };

    return (
        <div>
            <h2>Current Order</h2>
            <p>Order ID: {currentOrder.id}</p>
            <p>Address: {currentOrder.address}</p>

            <button
                onClick={() => {
                    incrementPreviousOrder();
                    handleSuccessfulDelivery();
                }}
            >
                Successfully Delivered
            </button>
            <button
                onClick={() => {
                    incrementPreviousOrder();
                    handleUnsuccessfulDelivery();
                }}
            >
                Unsuccessfully Delivered
            </button>

            {unsuccessfulClicked && (
                <div>
                    <label>Select Issue:</label>
                    <select
                        onChange={(e) => setSelectedIssue(parseInt(e.target.value))}
                        value={selectedIssue || ""}
                    >
                        {/* Options for issues */}
                    </select>
                    <button onClick={handleIssueSubmit}>Submit Issue</button>
                </div>
            )}
            {/* Pass the mapKey as a key prop to trigger re-render */}
            <Map key={mapKey} currentOrder={currentOrder} previousOrder={previousOrder} />
        </div>
    );
};

export default OrderDetails;
