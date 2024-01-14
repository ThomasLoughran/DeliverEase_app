const DriversList = ({ drivers }) => {

    console.log(drivers);

    const driversListComponents = drivers.map((driver) => {
        console.log(driver);
        return (

            <li className="driver-card">
                <p>Id: {driver.id}</p>
                <p>Name: {driver.name}</p>
                <p>Van Capacity: {driver.vanCapacity}</p>
                <p>Van Max Weight: {driver.vanMaxWeight}</p>
                <p>Van name: {driver.vanName}</p>
                <p>Available Dates: {driver.availableDates}</p>
            </li>
        )
    })



    return ( 
        <>
            <p>Hello from DriversList</p>
            <ul>
            {drivers ? driversListComponents : <p>Loading</p>}
            </ul>
        </>
        

    );
}
 
export default DriversList;