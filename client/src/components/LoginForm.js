import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useUser } from "../contexts/UserContext";
import '../styles/LoginPage.css';

import lightLogo from '../assets/adjusted-size-logos/light-mode-logo.png';


const LoginForm = () => {


    const navigate = useNavigate();

    const [userLoginInformation, setUserLoginInformation] = useState({
        id: '',
        password: '',
    })


    const { loginUser } = useUser();

    const handleUserLoginInformationChange = (event) => {
        const propertyName = event.target.name;
        const copiedUserLoginInformation = { ...userLoginInformation };
        copiedUserLoginInformation[propertyName] = event.target.value;
        setUserLoginInformation(copiedUserLoginInformation);
    };

    const handleLogin = async (event) => {
        if (!userLoginInformation.id || !userLoginInformation.password) {
            alert('Please enter both ID and Password.');
            return;
        }
    
        event.preventDefault()
        await fetchUser(userLoginInformation);
        
    }

    const fetchUser = async (userDetails) => {
        try {
            const response = await fetch('http://localhost:8080/employees/login', {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(userDetails)
            });

            if (!response.ok) {
                throw new Error(`Failed to log in: ${response.status} ${response.statusText}`);
            }

            const data = await response.json();

            if (!data) {
                throw new Error("Empty response received");
            }

            loginUser(data);
           
            navigate("/", { replace: true });
        } catch (error) {
            console.error('Error during login:', error);
        }
    }


    return (

        <>

        <div className="login-page">
            
            <img
                src={lightLogo}
                alt="Logo"
                className="logo"
                style={{ width: '400px', height: 'auto' }}
            />

            <form id="login-form" onSubmit={handleLogin}>
                <label htmlFor="login-form">Please enter your details:</label>
                <input
                    id="userId"
                    name="id"
                    type="id"
                    placeholder="Please enter your id"
                    value={userLoginInformation.id}
                    onChange={handleUserLoginInformationChange}
                />

                <input
                    id="userPassword"
                    name="password"
                    type="password"
                    placeholder="Please enter your Password"
                    value={userLoginInformation.password}
                    onChange={handleUserLoginInformationChange}
                />
                <button type="submit">Login</button>

            </form>
        </div>


        </>



    );
}

export default LoginForm;









