import { Outlet, Navigate } from "react-router-dom";
import { UserContext } from "../contexts/UserContext"




export const ProtectedRoute = () => {
const { user } = UserContext();

if (!user) {
    return  <Navigate to="/login" />;
}

return <Outlet/>

}