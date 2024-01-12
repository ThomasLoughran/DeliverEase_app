import { Outlet, Navigate } from "react-router-dom";
import { useUser }  from "../contexts/UserContext"




export const ProtectedRoute = () => {
const { user } = useUser();

if (!user) {
    {console.log("User not logged in")}
    return  <Navigate to="/login" />;
}

return <Outlet/>

}