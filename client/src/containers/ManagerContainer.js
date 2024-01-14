import NavBar from "../components/NavBar";
import Profile from "../components/Profile";
import { useUser } from "../contexts/UserContext";


const ManagerContainer = () => {

    const { user } = useUser();


    
    return (
        <>
            <p>Hello from manager container</p>
        </>

    );
}

export default ManagerContainer;