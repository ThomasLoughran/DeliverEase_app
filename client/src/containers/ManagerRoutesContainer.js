import { useEffect, useState } from "react";
import RouteCalendar from "../components/ManagerDashboardComponents/RouteCalendar";

const ManagerRoutesContainer = () => {

    const [date, setDate] = useState (new Date());
    
    
    useEffect(() => {
        // console.log(date);
    }, [date])

    return ( 

            <RouteCalendar date={date} />

     );
}
 
export default ManagerRoutesContainer;