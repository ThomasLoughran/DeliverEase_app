import Map from "../map/Map";
import { useUser } from "../../contexts/UserContext";
import { useEffect, useState } from "react";
import '../../styles/OrderDetails.css'

const OrderDetails = ({
    currentOrder,
    handleSuccessfulDelivery,
    handleUnsuccessfulDelivery,
    unsuccessfulClicked,
    selectedIssue,
    setSelectedIssue,
    handleIssueSubmit,
    issueSubmitted,
    setIssueSubmitted,
    data,

}) => {

    const { user } = useUser();
    const [previousOrder, setPreviousOrder] = useState(user.distributionCentre);
    const [mapKey, setMapKey] = useState(0);


    const incrementPreviousOrder = () => {
        setPreviousOrder(currentOrder);
        setIssueSubmitted(false);
        setMapKey((prevKey) => prevKey + 1);
    }


    useEffect(() => {
        // Update the map key whenever currentOrder changes
        // setMapKey((prevKey) => prevKey + 1);
    }, [currentOrder, previousOrder]);

    // console.log("order details test")

    return (
        <section>
            <header>
                <h2>Current Order:</h2>
            </header>

            <article>
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

                {unsuccessfulClicked && !issueSubmitted && (
                    <div>
                        <label>Select Issue:</label>
                        <select
                            className='issue-dropdown'
                            onChange={(e) => setSelectedIssue(parseInt(e.target.value))}
                            value={selectedIssue || ''}>
                            <optgroup>
                                <option value="" disabled>Select an issue</option>
                                <option value="1">No Access</option>
                                <option value="2">Refusal by Recipient</option>
                                <option value="3">Poor Weather</option>
                                <option value="4">Lost in Transit</option>
                                <option value="5">Security Issues</option>
                                <option value="6">Parcel Damage</option>
                                <option value="7">Lost Parcel</option>
                                <option value="8">Other</option>
                            </optgroup>
                        </select>
                        <button className='issue-button' onClick={handleIssueSubmit}>Submit Issue</button>
                    </div>
                )}
            </article>

            <Map key={mapKey} currentOrder={currentOrder} previousOrder={previousOrder} data={data} distCentre={user.distributionCentre} />

        </section>
    );
}

export default OrderDetails;
