import { useEffect, useState } from "react";
import DriversList from "../components/ManagerDashboardComponents/DriversList";
import AddDriverForm from "../components/ManagerDashboardComponents/AddDriverForm";

const DriversListContainer = () => {


    const [drivers, setDrivers] = useState([]);
    const [selectedCentreId, setSelectedCentreId] = useState(null);

    const [distributionCentres, setDistributionCentres] = useState([]);

    useEffect(() => {

        fetchDistributionCentres();
        if (selectedCentreId !== null) {    
            fetchDrivers(selectedCentreId);
        }
        

    }, [selectedCentreId])

    const fetchDistributionCentres = async () => {

        try {
            const response = await fetch(`http://localhost:8080/distribution-centres`, {
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
            console.log(data);

        } catch (error) {
            console.error('Error getting distribution centres:', error);
        }
    }

    const fetchDrivers = async (distributionCentreId) => {

        try {
            const response = await fetch(`http://localhost:8080/drivers?distCentId=${distributionCentreId}`, {
                method: "GET"
            });

            if (!response.ok) {
                throw new Error(`Failed to get users by distribution centre id: ${response.status} ${response.statusText} `);
            }

            const data = await response.json();

            if (!data) {
                throw new Error("Empty response received"); // maybe change how we handle this, could be possible that there are no drivers at the centre which wouldnt be an error.
            }

            setDrivers(data);
            console.log(data);

        } catch (error) {
            console.error('Error getting drivers:', error);
        }

    }




    return ( 

        <>
        <div className="drivers-list-container">
            {/* <p>Hello from driversListContainer</p> */}
            <DriversList drivers={drivers} distributionCentres={distributionCentres} selectedCentreId={selectedCentreId} setSelectedCentreId={setSelectedCentreId}/>
            <AddDriverForm />
        </div>
        </>


    );
}

export default DriversListContainer;