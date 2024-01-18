import '../../styles/DriversList.css'


const DriversList = ({ drivers, distributionCentres, selectedCentreId, setSelectedCentreId, setOpenModal, openModal }) => {

    const driversListComponents = drivers.map((driver) => {
        return (

            <li className="driver-card">
                <p>Employee Id: {driver.id}</p>
                <p>Name: {driver.name}</p>
                <p>Van Capacity: {driver.vanCapacity}</p>
                <p>Van Max Weight: {driver.vanMaxWeight}</p>
                <p>Van name: {driver.vanName}</p>
            </li>
        )
    })

    const handleDropDownChange = (e) => {
        setSelectedCentreId(e.target.value);
    }

    const dropDownComponents = distributionCentres.map((distributionCentre) => {
        return (
            <>
                <option value={distributionCentre.id}>
                    {distributionCentre.location}
                </option>
            </>
        )

    })


    return ( 

        <>

            <select id="distribution-centre-drop-down" onChange={handleDropDownChange} value={selectedCentreId || ''} > 
            <option value="" disabled> Please select centre</option>
                {dropDownComponents}

            </select>

            {!openModal && 
            <button onClick={() => setOpenModal(true)} className="open-modal-button">Add Driver</button>}

            <ul className='drivers-list'>
            {drivers ? driversListComponents : <p>Loading</p>}
            </ul>

        </>
    );
}
 
export default DriversList;