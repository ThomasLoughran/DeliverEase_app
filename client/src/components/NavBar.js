import { Link } from 'react-router-dom';
import { useUser } from '../contexts/UserContext';
import '../styles/NavBar.css';
import darkLogo from '../assets/adjusted-size-logos/dark-mode-logo.png'; //altered the dark-logo and light logo sizes.



const NavBar = () => {
    const { user, logoutUser } = useUser();

const handleLogout = () => {
    logoutUser();
}

    // const logoStyle =
    //     user?.role === 'MANAGER'
    //         ? { width: '200px', height: 'auto', marginTop: '200px' }
    //         : { width: '200px', height: 'auto', marginTop: '300px' };

    return (
        <div className= "NavBar">
            <Link id="home" to="/">
                Home
            </Link>
            {user?.role === 'MANAGER' && (
                <>
                    <Link id="drivers" to="manager/drivers">
                        Drivers
                    </Link>
                    <Link id="dist-cent" to="manager/distribution-centres">
                        Distribution Centres
                    </Link>
                    <Link id="routes-calendar" to='manager/routes'>
                        Routes Calendar
                    </Link>
                </>
            )}
            {user?.role === 'DRIVER' && (
                <Link id="routes" to="driver/routes">
                    Routes
                </Link>
            )}
            <Link id="logout" to="/" onClick={handleLogout}>
                Logout
            </Link>
            <img src={darkLogo} alt="Logo" className="logo"/>
        </div>
    );
};

export default NavBar;
