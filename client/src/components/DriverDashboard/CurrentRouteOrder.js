import { useState, useEffect } from "react";
import { useUser } from "../../contexts/UserContext";
import OrderDetails from "./OrderDetails";

const CurrentRouteOrder = () => {
    const { user } = useUser();
    const [currentOrder, setCurrentOrder] = useState(null);
    const [unsuccessfulClicked, setUnsuccessfulClicked] = useState(false);
    const [selectedIssue, setSelectedIssue] = useState(null);
    const [data, setData] = useState(null);
    const [showNextOrder, setShowNextOrder] = useState(false);
    const [showIndex, setShowIndex] = useState(0);
    const [issueSubmitted, setIssueSubmitted] = useState(false);

    useEffect(() => {

        fetchCurrentOrder()

    })

    const fetchCurrentOrder = async () => {
        try {
            const currentDate = new Date();
            const formattedDate = currentDate.toISOString().split('T')[0];
            const response = await fetch(`http://localhost:8080/routes/driver/${user.id}?localDate=${formattedDate}`);

            if (!response.ok) {
                throw new Error(`Failed to fetch routes data: ${response.status} ${response.statusText}`);
            }

            const responseData = await response.json();

            if (responseData.orderId && responseData.orderId.length > 0) {

                const orderResponse = await fetch(`http://localhost:8080/routes/driver/${user.id}/currentOrder?localDate=${formattedDate}`);

                if (orderResponse.ok) {
                    const orderData = await orderResponse.json();

                    setShowIndex(orderResponse.currentPositionInRoute)
                    setCurrentOrder(orderData);
                    setShowNextOrder(false)
                    setData(responseData);
                } else {
                    setCurrentOrder(null);
                    setData(null);
                    setShowNextOrder(false)
                }
            } else {
                setCurrentOrder(null);
                setData(null);
                setShowNextOrder(false)
            }
        } catch (error) {
            console.error('Error fetching routes data:', error);
        }
    };

    useEffect(() => {
        fetchCurrentOrder();
    }, [user.id, unsuccessfulClicked, showNextOrder]);

    const handleUnsuccessfulDelivery = () => {
        setUnsuccessfulClicked(true);
        setSelectedIssue(null);
    };

    const markOrderAsCompleted = async (orderId) => {
        try {
            const response = await fetch(`http://localhost:8080/orders/complete/${orderId}?isComplete=true`, {
                method: 'PATCH',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                }),
            });

            if (!response.ok) {
                throw new Error(`Failed to mark order as completed: ${response.status} ${response.statusText}`);
            }
            await fetchNextOrder(orderId);
        } catch (error) {
            console.error('Error marking order as completed:', error);
        }
    };

    const fetchNextOrder = async (currentOrderId) => {
        try {
            if (currentOrder.currentPositionInRoute < data.orderId.length) {

                const currentDate = new Date();
                const formattedDate = currentDate.toISOString().split('T')[0];
                const nextOrderResponse = await fetch(`http://localhost:8080/routes/driver/${user.id}/currentOrder?localDate=${formattedDate}`);

                if (nextOrderResponse.ok) {
                    const nextOrderData = await nextOrderResponse.json();
                    setCurrentOrder(nextOrderData);
                    setShowNextOrder(true);

                    const nextOrderIndex = nextOrderData;
                    setShowIndex(showIndex + 1);
                } else {
                    setCurrentOrder(null);
                    setShowNextOrder(false);
                }
            } else {
                setCurrentOrder(null);
                setShowNextOrder(false);
            }
        } catch (error) {
            console.error('Error fetching next order:', error);
        }
    };

    const handleSuccessfulDelivery = () => {
        if (currentOrder) {
            markOrderAsCompleted(currentOrder.id);
        }
    };

    const handleIssueSubmit = async () => {
        if (selectedIssue !== null && currentOrder) {
            try {
                const response = await fetch(`http://localhost:8080/orders/issue`, {
                    method: 'PATCH',
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                        id: currentOrder.id,
                        issue: selectedIssue,
                        timeIssuePosted: new Date().toISOString(),
                    }),
                });

                if (!response.ok) {
                    throw new Error(`Failed to submit issue: ${response.status} ${response.statusText}`);
                }
                alert("Issue Submitted")

                fetchNextOrder();
                setIssueSubmitted(true);
            } catch (error) {
                console.error('Error submitting issue:', error);
            }
        }
    };

    return (
        <>
            {currentOrder ? (
                <div>
                    <OrderDetails
                        currentOrder={currentOrder}
                        handleSuccessfulDelivery={handleSuccessfulDelivery}
                        handleUnsuccessfulDelivery={handleUnsuccessfulDelivery}
                        unsuccessfulClicked={unsuccessfulClicked}
                        selectedIssue={selectedIssue}
                        setSelectedIssue={setSelectedIssue}
                        handleIssueSubmit={handleIssueSubmit}
                        issueSubmitted={issueSubmitted}
                        setIssueSubmitted={setIssueSubmitted}
                        data={data}
                    />
                </div>
            ) : (
                <p>No orders for the current date.</p>
            )}
        </>
    );
}

export default CurrentRouteOrder;