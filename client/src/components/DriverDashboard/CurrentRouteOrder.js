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
                    const orderId = responseData.orderId[showIndex];
                    const orderResponse = await fetch(`http://localhost:8080/orders/${orderId}`);

                    if (orderResponse.ok) {
                        const orderData = await orderResponse.json();
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
        setUnsuccessfulClicked(!unsuccessfulClicked);
        setSelectedIssue(null);
    };

    const markOrderAsCompleted = async (orderId) => {
        try {
            console.log(orderId, 'this is order id');
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
            const nextOrderIndex = data.orderId.indexOf(currentOrderId) + 1;
            setShowIndex(showIndex + 1);
            console.log(nextOrderIndex, "this is next order id");
    
            if (nextOrderIndex < data.orderId.length) {
                const nextOrderId = data.orderId[nextOrderIndex];
                const nextOrderResponse = await fetch(`http://localhost:8080/orders/${nextOrderId}`);
    
                if (nextOrderResponse.ok) {
                    const nextOrderData = await nextOrderResponse.json();
                    setCurrentOrder(nextOrderData);
                    setShowNextOrder(true);
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
                console.log('Submitting issue:', selectedIssue);
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
                console.log('Issue submitted!');

                setUnsuccessfulClicked(false);
                fetchNextOrder();
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
                        data = {data}
                    />
                </div>
            ) : (
                <p>No orders for the current date.</p>
            )}
        </>
    );
}

export default CurrentRouteOrder;
