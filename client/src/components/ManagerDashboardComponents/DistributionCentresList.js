import '../../styles/DistributionCentresList.css'

const DistributionCentresList = ({ distributionCentres }) => {

    // console.log(distributionCentres);

    const DistributionCentresListComponents = distributionCentres.map((distributionCentre) => {
        // console.log(distributionCentre);
        return (

            <li className="distribution-centre-card">
                <p>Name: {distributionCentre.location}</p>
                <p>Phone number: {distributionCentre.phoneNumber}</p>
            </li>
        )
    })



    return ( 
            <ul className='distribution-centres-list'>
            {distributionCentres ? DistributionCentresListComponents : <p>Loading</p>}
            </ul>
    );
}

export default DistributionCentresList;