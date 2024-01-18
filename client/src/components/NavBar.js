import { Link } from 'react-router-dom';
import { useUser } from '../contexts/UserContext';
import '../styles/NavBar.css';
import darkLogo from '../assets/adjusted-size-logos/dark-mode-logo.png'; //altered the dark-logo and light logo sizes.
import { useEffect, useState } from 'react';
import ProfileModal from './ProfileModal';

import profileIcon from '../assets/profile-icon-white.png';
import messageIcon from '../assets/message-icon-white.png';
import activeMessageIcon  from '../assets/message-icon-white-active.png'
import MessageModal from './MessageModal';
import NavLogo from '../assets/menu-icon.png';
import CloseNav from '../assets/menu-close-icon.png';


const NavBar = () => {
    const { user, logoutUser } = useUser();

    const [openProfileModal, setOpenProfileModal] = useState(false);
    const [openMessageListModal, setOpenMessageListModal] = useState(false);
    const [orders , setOrders] = useState([])
    const [notificationRefresh, setNotificationRefresh] = useState(true);
    const [navOpen, setNavOpen] = useState(false)
    // const [ordersLoaded, setOrdersLoaded] = useState(false)

    const handleLogout = () => {
        logoutUser();
    }



    useEffect(() => {

        console.log(notificationRefresh)



        if (user.role == 'MANAGER') {
            fetchIssues()
        }



    }, [user.role, openMessageListModal, notificationRefresh])


    const fetchIssues = async () => {
        try {
            const response = await fetch(`http://localhost:8080/orders/issue/all?distCentreId=${1}&isManagerReviewed=${false}`, {
                method: "GET",
            
            });
    
            if (!response.ok) {
                throw new Error(`Failed to receive messages: ${response.status} ${response.statusText}`);
            }
    
            const data = await response.json();
    
            if (!data) {
                throw new Error("Empty response received");
            }
    
            setOrders(data);
            console.log("This is data", data);
    
        } catch (error) {
            console.error('Error during receiving messages:', error);
        }

        // setOrdersLoaded(true);
    }







    return (
        <>
        {navOpen && (
        <div className= "navBar">
            <div className="profile-message-container" >
                    <img id="profile-icon" src={profileIcon} onClick={() => setOpenProfileModal(true)}
                    className="profile-button"/>
            {openProfileModal && <ProfileModal closeModal={setOpenProfileModal}/>}

            {user?.role === 'MANAGER' && (
            <img
                id="message-icon"
                src={orders.length > 0 && notificationRefresh ? activeMessageIcon : messageIcon} 
                alt={orders.length > 0 && notificationRefresh ? "New messages icon" : "message Icon"}
                onClick={() => {
                    setOpenMessageListModal(true)
                    console.log("triggered")
                    setNotificationRefresh(false)
                    setOrders([])
                    }
                }
                className='message-button'
            />
            )}
            {openMessageListModal && <MessageModal closeModal={setOpenMessageListModal}/>}
            </div>

            

       
            {user?.role === 'MANAGER' && (
                <>
                    <Link id="routes-calendar" to='manager/routes'>
                        Routes Calendar
                    </Link>
                    <Link id="drivers" to="manager/drivers">
                        Drivers
                    </Link>
                    <Link id="dist-cent" to="manager/distribution-centres">
                        Distribution Centres
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
        )}
            <button className="open-close-nav" 
                onClick={() => setNavOpen(!navOpen)}>
                {navOpen ? 
                <img src={CloseNav} 
                style={{ width: '35px', height: 'auto' }}
                /> : <img src={NavLogo}/>}
            </button>
            </>
    );
};

export default NavBar;
