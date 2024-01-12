import { useState } from "react";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {


    // const navigate = useNavigate();

    const [userLoginInformation, setUserLoginInformation] = useState({
        id: '',
        password: '',
    })

    const handleUserLoginInformationChange = (event) => {
        const propertyName = event.target.name;
        const copiedUserLoginInformation = { ...userLoginInformation };
        copiedUserLoginInformation[propertyName] = event.target.value;
        setUserLoginInformation(copiedUserLoginInformation);
    };

    const handleLogin = (event) => {
        event.preventDefault()

        console.log(userLoginInformation)
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