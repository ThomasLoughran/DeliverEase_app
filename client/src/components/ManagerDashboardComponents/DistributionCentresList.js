import '../../styles/DistributionCentresList.css'

const DistributionCentresList = ({ distributionCentres }) => {

    // console.log(distributionCentres);

    const DistributionCentresListComponents = distributionCentres.map((distributionCentre) => {
        // console.log(distributionCentre);
        return (

            <li className="distribution-centre-card">
                <p>Id: {distributionCentre.id}</p>
                <p>Name: {distributionCentre.location}</p>
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