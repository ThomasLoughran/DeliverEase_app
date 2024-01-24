import { useNavigate } from "react-router-dom";
import { useUser } from "../contexts/UserContext";

const LogoutPage = () => {

    const { logoutUser } = useUser();

    const navigate = useNavigate();

    const handleLogout = () => {
        logoutUser();
        navigate("/", { replace: true });
    }

    return (
        <button onClick={handleLogout}>Logout</button>
    );
}

export default LogoutPage;