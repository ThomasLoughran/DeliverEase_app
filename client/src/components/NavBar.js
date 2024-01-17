import { Link } from 'react-router-dom';
import { useUser } from '../contexts/UserContext';
import '../styles/NavBar.css';
import darkLogo from '../assets/adjusted-size-logos/dark-mode-logo.png'; //altered the dark-logo and light logo sizes.
import { useState } from 'react';
import ProfileModal from './ProfileModal';

import profileIcon from '../assets/profile-icon-white.png';
import messageIcon from '../assets/message-icon-white.png';
import MessageModal from './MessageModal';



const NavBar = () => {
    const { user, logoutUser } = useUser();

    const [openProfileModal, setOpenProfileModal] = useState(false);
    const [openMessageListModal, setOpenMessageListModal] = useState(false);

const handleLogout = () => {
    logoutUser();
}

    // const logoStyle =
    //     user?.role === 'MANAGER'
    //         ? { width: '200px', height: 'auto', marginTop: '200px' }
    //         : { width: '200px', height: 'auto', marginTop: '300px' };

    return (
        <div className= "navBar">

            <div className="profile-message-container" >
                {!openModal && 
                    <img className="profile-button" 
                    onClick={() => setOpenModal(true)} src={profileIcon} > 
                    </img>
                }

                {openModal && <ProfileModal closeModal={setOpenModal}/>}

                <img className="message-button"  src={messageIcon} >
                </img>
                
            </div>


            )}

            {openMessageListModal && <MessageModal closeModal={setOpenMessageListModal}/>}



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
