import { useState } from "react";
import { useUser } from '../../contexts/UserContext';

const GenerateRoutes = ({showTodayRoutes}) => {
    const { user } = useUser();
    const [localDate, setLocalDate] = useState(new Date());



    const formatDate = (date) => {
        return date.toLocaleDateString();
    };

    const handleDateChange = (date) => {
        setLocalDate(date);
    };

    const handleGenerateRoutes = async () => {
        showTodayRoutes(true);
        try {
            const formattedDate = formatDate(localDate);

            const response = await fetch(`http://localhost:8080/routes/new-routes/${user.distributionCentre.id}?localDate=${formattedDate}`, {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({}),
            });

            const data = await response.text();
                alert(data);


        } catch (error) {
            console.error('Error generating routes:', error);
        }
    };

    return (
        <>
            <h2>Generate Routes</h2>
            <label>
                Date:
                <input
                    type="date"
                    value={localDate.toISOString().split('T')[0]}
                    onChange={(e) => handleDateChange(new Date(e.target.value))} />
            </label>

            <button onClick={handleGenerateRoutes}>Generate Routes</button>

           

        </>
    );
}

export default GenerateRoutes;
