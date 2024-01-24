import { Outlet, Navigate } from "react-router-dom";
import { useUser } from "../contexts/UserContext"
import { useEffect } from "react";

export const DriverProtectedRoute = () => {

    const { user } = useUser();

    useEffect(() => {
    }, [user])

    if (user.role !== 'DRIVER') {
        return <Navigate to="/manager/routes" />;
    }

    return <Outlet />

}