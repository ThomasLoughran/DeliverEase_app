
import { useUser } from "../contexts/UserContext";

const DriverContainer = () => {

    const { user } = useUser();



    return ( 
        <>
        <p>Hello from Driver Container</p>
        {user ? <p>name: {user.name}</p> : <p>Loading</p>}
        </>
     );
}
 
export default DriverContainer;