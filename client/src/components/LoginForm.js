import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useUser } from "../contexts/UserContext";

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
        event.preventDefault()

        await fetchUser(userLoginInformation);


        console.log(userLoginInformation)
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
            console.log("This is data", data);
            navigate("/", { replace: true });
        } catch (error) {
            console.error('Error during login:', error);
            
    }
}





    return ( 

        <>
        <form id="login-form" onSubmit={handleLogin}>
            <p>Login page</p>
            <label htmlFor="login-form">Please enter your details</label>
            <input
                id="userId"
                name="id"
                type="id"
                placeholder="Please enter your id"
                value={userLoginInformation.id}
                onChange={handleUserLoginInformationChange}
            />

            <input
                id="useePassword"
                name="password"
                type="password"
                placeholder="Please enter your Password"
                value={userLoginInformation.password}
                onChange={handleUserLoginInformationChange}
            />


            <button type="submit">Login</button>

        </form>
        
        
        
        </>



    );
}

export default LoginForm;