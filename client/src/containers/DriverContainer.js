import NavBar from "../components/NavBar";
import Profile from "../components/Profile";
import { useUser } from "../contexts/UserContext";

const DriverContainer = () => {

    const { user } = useUser();



    return (
        <>
            <NavBar />
            <Profile />
            {user ? <p>name: {user.name}</p> : <p>Loading</p>}
        </>
    );
}

export default DriverContainer;