import { useState } from "react";
import { useUser } from '../../contexts/UserContext';
import '../../styles/GenerateRoutes.css'

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
        
        try {
            const formattedDate = formatDate(localDate);

            const response = await fetch(`http://localhost:8080/routes/new-routes/${user.distributionCentre.id}?localDate=${formattedDate}`, {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({}),
            });

            const data = await response.text();
                alert(data);
                if (data === "Routes successfully created"){
                    showTodayRoutes(true);
                }


        } catch (error) {
            console.error('Error generating routes:', error);
        }
    };

    return (
        <>
        <div className="generate-routes-component">
            <h2>Generate Routes</h2>
            <p>Select Date:</p>
            <label>
                Date:
                <input
                    type="date"
                    value={localDate.toISOString().split('T')[0]}
                    onChange={(e) => handleDateChange(new Date(e.target.value))} />
            </label>

            <button onClick={handleGenerateRoutes}>Generate Routes</button>
            </div>
        </>
    );
}

export default GenerateRoutes;
