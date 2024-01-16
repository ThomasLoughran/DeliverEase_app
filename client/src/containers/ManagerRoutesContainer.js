import { useEffect, useState } from "react";
import RouteCalendar from "../components/ManagerDashboardComponents/RouteCalendar";
import GenerateRoutes from "../components/ManagerDashboardComponents/GenerateRoutes";

const ManagerRoutesContainer = () => {

    const [date, setDate] = useState(new Date());


    useEffect(() => {
    }, [date])

    const moveForwardOneDay = () => {
        date.setDate(date.getDate() + 1);
    }



    return (
        <>
            <GenerateRoutes />
            <RouteCalendar date={date} moveForwardOneDay={moveForwardOneDay} />
        </>
    );
}

export default ManagerRoutesContainer;