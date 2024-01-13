import NavBar from "../components/NavBar";
import Profile from "../components/Profile";
import { useUser } from "../contexts/UserContext";


const ManagerContainer = () => {

    const { user } = useUser();

    return (
        <>
            <NavBar />
            <Profile />
            {user ? <p>name: {user.name}</p> : <p>Loading</p>}
        </>

    );
}

export default ManagerContainer;