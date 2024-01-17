import { useState, useEffect } from "react";
import { useUser } from '../../contexts/UserContext';


const RouteCalendar = ({loadRoute,showTodayRoutes}) => {
    const { user } = useUser();
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [routesData, setRoutesData] = useState([]);
    const [loadRoutes, setLoadRoutes] = useState(loadRoute)

    useEffect(() => {
        setLoadRoutes(loadRoute);
      }, [loadRoute]);

    const formatDate = (date) => {
        return date.toISOString().split('T')[0];
    };

    const fetchRoutesData = async () => {
        try {
            const formattedDate = formatDate(selectedDate);
            const response = await fetch(`http://localhost:8080/routes/all/${user.id}?localDate=${formattedDate}`);

            if (!response.ok) {
                throw new Error(`Failed to fetch routes data: ${response.status} ${response.statusText}`);
            }

            const data = await response.json();
            setRoutesData(data);
        } catch (error) {
            console.error('Error fetching routes data:', error);
        }
    };

    useEffect(() => {
        fetchRoutesData();
    }, [selectedDate, user.id]);

    const onChange = (date) => {
        setSelectedDate(date);
    };

    const handlePreviousDay = () => {
        const previousDay = new Date(selectedDate);
        previousDay.setDate(selectedDate.getDate() - 1);
        setSelectedDate(previousDay);
    };

    const handleNextDay = () => {
        const nextDay = new Date(selectedDate);
        nextDay.setDate(selectedDate.getDate() + 1);
        setSelectedDate(nextDay);
    };

    const handleCurrentDay = () => {
        
          fetchRoutesData();
          console.log(routesData);
          showTodayRoutes(false);
          setLoadRoutes(false);
        
      };

    return (
        <div className="route-calendar">
            <h2>Route Log</h2>
            <div className="calendar">
                <div>
                    <label>Select Date:</label>
                    <input
                        value={selectedDate.toISOString().split('T')[0]}
                        type="date"
                        onChange={(e) => onChange(new Date(e.target.value))}
                    />
                </div>
            </div>

            <button onClick={handlePreviousDay}>Previous day</button>
            <button onClick={handleNextDay}>Next day</button>

            {loadRoutes && (
                <div>
                    {handleCurrentDay()}
                </div>
            )}

            <div className="routes">
                <h3>Routes on {selectedDate.toDateString()}:</h3>
                {routesData.length === 0 ? (
                    <p>No routes on this date.</p>
                ) : (
                    <ul>
                        {routesData.map((route, index) => (
                            <li key={index}>
                                Route Id: {route.id}
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
};

export default RouteCalendar;
