import { Link } from 'react-router-dom';
import React from 'react';
import { useUser } from '../contexts/UserContext';
import '../css/NavBar.css';


const NavBar = () => {
    const { user } = useUser();

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
        </div>
    );
};

export default NavBar;
