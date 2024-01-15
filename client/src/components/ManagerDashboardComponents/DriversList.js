import '../../styles/DriversList.css'


const DriversList = ({ drivers, distributionCentres }) => {

    // console.log(drivers);

    const driversListComponents = drivers.map((driver) => {
        console.log(driver);
        return (

            <li className="driver-card">
                <p>Id: {driver.id}</p>
                <p>Name: {driver.name}</p>
                <p>Van Capacity: {driver.vanCapacity}</p>
                <p>Van Max Weight: {driver.vanMaxWeight}</p>
                <p>Van name: {driver.vanName}</p>
                {/* <p>Available Dates: {driver.availableDates}</p> */}
            </li>
        )
    })

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

            <select> 
                {dropDownComponents}
            </select>


            <ul className='drivers-list'>
            {drivers ? driversListComponents : <p>Loading</p>}
            </ul>

        </>
    );
}
 
export default DriversList;