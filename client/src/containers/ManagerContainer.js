import NavBar from "../components/NavBar";
import { useUser } from "../contexts/UserContext";


const ManagerContainer = () => {

    const { user } = useUser();

    return (
        <>
            <NavBar />
            <p>Hello from manager container</p>
            {user ? <p>name: {user.name}</p> : <p>Loading</p>}
        </>

    );
}

export default ManagerContainer;