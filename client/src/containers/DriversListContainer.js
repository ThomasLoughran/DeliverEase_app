import { useEffect, useState } from "react";
import DriversList from "../components/ManagerDashboardComponents/DriversList";

const DriversListContainer = () => {


    const [drivers, setDrivers] = useState([])
    const distributionCentreId = 1; // hardcoded for test purposes. 
    //Should be coded so that there is a context that tracks the current distcent selected.


    useEffect(() => {

        fetchDrivers(distributionCentreId);

    }, [])

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
        <div className="drivers-list-container">
            <p>Hello from driversListContainer</p>
            <DriversList drivers={drivers}/>
        </div>



    );
}

export default DriversListContainer;