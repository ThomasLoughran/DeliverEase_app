import { useEffect, useState } from "react";
import RouteCalendar from "../components/ManagerDashboardComponents/RouteCalendar";
import GenerateRoutes from "../components/ManagerDashboardComponents/GenerateRoutes";

const ManagerRoutesContainer = () => {

    const [date, setDate] = useState(new Date());
    const [loadRoutes,setLoadRoutes] = useState(true);


    useEffect(() => {
    }, [date])

    const moveForwardOneDay = () => {
        date.setDate(date.getDate() + 1);
    }

    const showTodayRoutes = (showtoggle) => {
        setLoadRoutes(showtoggle);
    }

    return (
        <>
            <GenerateRoutes showTodayRoutes={showTodayRoutes} />
            <RouteCalendar date={date} loadRoute={loadRoutes} moveForwardOneDay={moveForwardOneDay} showTodayRoutes={showTodayRoutes}/>
        </>
    );
}


export default ManagerRoutesContainer;