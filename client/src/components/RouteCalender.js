import { useState } from 'react';
import '../styles/RouteCalendar.css'; 


const RouteCalendar = () => {

    const [selectedDate, setSelectedDate] = useState(new Date());

    const dummyData = [
        { date: new Date(2024, 0, 10), driver: 'driver1', route: 'Route 1' },
        { date: new Date(2024, 0, 15), driver: 'driver1', route: 'Route 2' },
        { date: new Date(2024, 0, 20), driver: 'driver2', route: 'Route 3' },
    ];

    const routesOnSelectedDate = dummyData.filter(item => item.date.toDateString() === selectedDate.toDateString());

    const onChange = date => {
        setSelectedDate(date);
    };

    const handleNextDay = () => {
        const nextDay = new Date(selectedDate);
        nextDay.setDate(selectedDate.getDate() + 1);
        setSelectedDate(nextDay);
    };

    return (
        <div className="route-calendar">
            <h2>Route Calendar</h2>
            <div className="calendar">
                <div>
                    <label>Select Date:</label>
                    <input type="date" 
                    value={selectedDate.toISOString().split('T')[0]}
                    onChange={(e) => onChange(new Date(e.target.value))} />
                </div>
                <button onClick={handleNextDay}>Next Day</button>

            </div>

            <div className="routes">
                <h3>Routes on {selectedDate.toDateString()}:</h3>
                {routesOnSelectedDate.length === 0 ? (
                    <p>No routes on this date.</p>
                ) : (
                    <ul>
                        {routesOnSelectedDate.map((route, index) => (
                            <li key={index}>
                                Driver: {route.driver}, Route: {route.route}
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
};

export default RouteCalendar;
