import { Outlet, Navigate } from "react-router-dom";
import { useUser } from "../contexts/UserContext"
import { useEffect } from "react";

export const ProtectedRoute = () => {

    const { user } = useUser();

    useEffect(() => {
    }, [user])

    if (!user) {
        return <Navigate to="/login" />;
    }

    return <Outlet />

}