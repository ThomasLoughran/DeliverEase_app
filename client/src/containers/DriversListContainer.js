import { useEffect, useState } from "react";
import DriversList from "../components/ManagerDashboardComponents/DriversList";
import Modal from "../components/Modal";

const DriversListContainer = () => {


    const [drivers, setDrivers] = useState([]);
    const [selectedCentreId, setSelectedCentreId] = useState(null);
    const [openModal, setOpenModal] = useState(false);
    const [distributionCentres, setDistributionCentres] = useState([]);

    // const handleOpenModal = () => {
    //     setOpenModal(true);
    // }

    // const handleCloseModal = () => {
    //     setOpenModal(false);
    // }    

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
            <DriversList drivers={drivers} distributionCentres={distributionCentres} selectedCentreId={selectedCentreId} setSelectedCentreId={setSelectedCentreId} setOpenModal={setOpenModal} openModal={openModal}/>
            {/* {!openModal && <button onClick={() => setOpenModal(true)} className="open-modal-button">Add Driver</button>} moved to driversList */}
            {openModal && <Modal closeModal={setOpenModal}/>}

            
        </div>
        </>


    );
}

export default DriversListContainer;