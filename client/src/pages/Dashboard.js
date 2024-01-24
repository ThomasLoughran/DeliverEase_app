import DriverContainer from "../containers/DriverContainer";
import ManagerContainer from "../containers/ManagerContainer";
import { useUser } from "../contexts/UserContext";


const Dashboard = () => {
    const { user } = useUser();

    if (user.role === "MANAGER") {
        return (
            <>
                <ManagerContainer />
            </>
        )
    }

    if (user.role === "DRIVER") {
        return (
            <>
                <DriverContainer />
            </>
        )
    }

    return (
        <>
            <p>Hello from dashboard</p>
        </>
    );
}

export default Dashboard;