import { Outlet, Navigate } from "react-router-dom";
import { useUser }  from "../contexts/UserContext"
import { useEffect } from "react";





export const ProtectedRoute = () => {

    const { user } = useUser();

    useEffect(() => {
        

    }, [user])


if (!user) {
    {console.log("User not logged in", user)}
    return  <Navigate to="/login" />;
}

return <Outlet/>

}