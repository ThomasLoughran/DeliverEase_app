import { useUser } from '../contexts/UserContext';
import '../styles/Profile.css'; 


const Profile = () => {
    const { user } = useUser();

    return (
        <div className="profile-container">
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
        </div>
    );
}

export default Profile;
