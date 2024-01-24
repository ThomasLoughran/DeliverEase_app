import { Outlet, Navigate } from "react-router-dom";
import { useUser } from "../contexts/UserContext"
import { useEffect } from "react";

export const ManagerProtectedRoute = () => {

    const { user } = useUser();

    useEffect(() => {
    }, [user])

    if (user.role !== 'MANAGER') {
        return <Navigate to="/driver/routes" />;
    }

    return <Outlet />

}