import { useUser } from "../contexts/UserContext";


const ManagerContainer = () => {

    const { user } = useUser();

    return ( 
        <>
        <p>Hello from manager container</p>
        {user ? <p>name: {user.name}</p> : <p>Loading</p>}
        </>

    );
}

export default ManagerContainer;