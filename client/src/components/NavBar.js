import { Link } from 'react-router-dom';
import { useUser } from '../contexts/UserContext';
import '../styles/NavBar.css';
import darkLogo from '../assets/adjusted-size-logos/dark-mode-logo.png'; //altered the dark-logo and light logo sizes.
import { useState } from 'react';
import ProfileModal from './ProfileModal';
import profileIcon from '../assets/icon-location.png';
import messageIcon from '../assets/icons8-message.png';


const NavBar = () => {
    const { user, logoutUser } = useUser();

    const [openModal, setOpenModal] = useState(false);

    const handleLogout = () => {
        logoutUser();
    }

    return (
        <div className="navBar">

            {!openModal &&
                <button onClick={() => setOpenModal(true)}
                    className="open-modal-button">
                    <img id="profile-icon" src={profileIcon} />
                </button>
            }

            {openModal && <ProfileModal closeModal={setOpenModal} />}

            <button>
                <img id="message-icon" src={messageIcon} />
            </button>

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
                <>
                    <Link id="routes" to="driver/routes">
                        Routes
                    </Link>
                    <Link id="driver-availability" to="driver/driver-availability">
                        Driver Availability
                    </Link>
                </>
            )}
            <Link id="logout" to="/" onClick={handleLogout}>
                Logout
            </Link>
            <img src={darkLogo} alt="Logo" className="logo" />
        </div>
    );
};

export default NavBar;
