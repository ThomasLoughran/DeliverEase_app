import { Outlet, Navigate } from "react-router-dom";
import { useUser }  from "../contexts/UserContext"
import { useEffect } from "react";


export const DriverProtectedRoute = () => {

    const { user } = useUser();


if (user.role !== 'DRIVER') {
    {console.log("Cannot access page due to invalid user type", user.role)}
    return  <Navigate to="/" />;
}

return <Outlet/>

}