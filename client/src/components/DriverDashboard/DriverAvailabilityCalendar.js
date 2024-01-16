import { useState } from "react";
import { useUser } from '../../contexts/UserContext';


const DriverAvailabilityCalendar = () => {
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [availability, setAvailability] = useState({});
    const { user } = useUser();


    const formatDate = (date) => {
        return date.toLocaleDateString();
    };

    const handleDateChange = (date) => {
        setSelectedDate(date);
    };

    const handleAvailabilityChange = async () => {
        try {
            const formattedDate = formatDate(selectedDate);
            const currentAvailability = availability[formattedDate] || false;

            const response = await fetch(`http://localhost:8080/drivers/change-available/${user.id}?date=${formattedDate}`, {
                method: "PATCH",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    available: !currentAvailability,

                }),
            });

            setAvailability((previousAvailability) => ({
                ...previousAvailability,
                [formattedDate]: !currentAvailability,
            }));
            console.log(user)
            alert('Availability updated successfully!');

        } catch (error) {
            console.error('Error updating driver availability:', error);
            alert('Error updating availability, contact your manager!');
        }
    };

    const filteredDates = Object.fromEntries(
        Object.entries(availability)
            .filter(([date, isAvailable]) => isAvailable)
    );

    return (
        <>
            <h2>Driver Availability Calendar</h2>

            <input
                type="date"
                value={selectedDate.toISOString().split('T')[0]}
                onChange={(e) => handleDateChange(new Date(e.target.value))} />

            <button onClick={handleAvailabilityChange}>
                {availability[formatDate(selectedDate)] ? 'Make Unavailable' : 'Make Available'}
            </button>

            <ul>
                {Object.keys(filteredDates).map((date) => (
                    <li key={date}>
                        {date}: {filteredDates[date] ? 'Available' : 'Unavailable'}
                    </li>
                ))}
            </ul>
        </>
    );
};

export default DriverAvailabilityCalendar;
