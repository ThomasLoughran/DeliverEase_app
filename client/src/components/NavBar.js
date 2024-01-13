import { Link } from 'react-router-dom';
import { useUser } from '../contexts/UserContext';
import '../styles/NavBar.css';
import darkLogo from '../assets/dark-mode-logo.png';



const NavBar = () => {
    const { user } = useUser();

    const logoStyle =
        user?.role === 'MANAGER'
            ? { width: '200px', height: 'auto', marginTop: '200px' }
            : { width: '200px', height: 'auto', marginTop: '300px' };

    return (
        <div className={user?.role === 'MANAGER' ? 'manager-navigation' : 'driver-navigation'}>
            <Link id="home" to="/home">
                Home
            </Link>
            {user?.role === 'MANAGER' && (
                <>
                    <Link id="drivers" to="/drivers/all">
                        Drivers
                    </Link>
                    <Link id="dist-cent" to="/distribution-centre">
                        Distribution Centres
                    </Link>
                </>
            )}
            {user?.role === 'DRIVER' && (
                <Link id="routes" to="/routes">
                    Routes
                </Link>
            )}
            <Link id="logout" to="/logout">
                Logout
            </Link>
            <img src={darkLogo} alt="Logo" className="logo" style={logoStyle} />

        </div>
    );
};

export default NavBar;
