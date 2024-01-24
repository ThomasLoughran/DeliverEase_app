import DistributionCentresList from "../components/ManagerDashboardComponents/DistributionCentresList.js";
import { useEffect, useState } from "react";

const DistributionCentreListContainer = () => {

    const [distributionCentres, setDistributionCentres] = useState([]);

    useEffect(() => {
        fetchDistributionCentres();
    }, [])

    const fetchDistributionCentres = async () => {

        try {
            const response = await fetch(`http://localhost:8080/distribution-centres/all`, {
                method: "GET"
            });

            if (!response.ok) {
                throw new Error(`Failed to get distribution centres: ${response.status} ${response.statusText} `);
            }

            const data = await response.json();

            if (!data) {
                throw new Error("Empty response received"); // 
            }

            setDistributionCentres(data);

        } catch (error) {
            console.error('Error getting distribution centres:', error);
        }
    }

    return (
        <>
            <h1 style={{ 'margin-top': '40px' }}>Distribution Centres:</h1>
            <DistributionCentresList distributionCentres={distributionCentres} />
        </>
    );

}

export default DistributionCentreListContainer;