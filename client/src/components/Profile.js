import { useUser } from '../contexts/UserContext';
import '../styles/Profile.css'; 
import { Link } from 'react-router-dom';

const Profile = () => {
    const { user } = useUser();

    return (
        <div className="profile">
            <h2>Your Profile:</h2>
            {user.role === 'MANAGER' && (
                <>
                    <p>Name: {user.name}</p>
                    <p>ID: {user.id}</p>
                </>
            )}
            {user.role === 'DRIVER' && (
                <>
                    <p>Name: {user.name}</p>
                    <p>ID: {user.id}</p>
                    <p>Van name: {user.vanName}</p>
                </>
            )}

        <Link to="" ><button id="update-details-button"> <b>Update details</b> </button></Link>     
        </div>
        

    );
}

export default Profile;
