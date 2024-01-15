import { useEffect, useState } from "react";
import RouteCalendar from "../components/ManagerDashboardComponents/RouteCalendar";

const ManagerRoutesContainer = () => {

    const [date, setDate] = useState (new Date());
    
    
    useEffect(() => {
        // console.log(date);
    }, [date])

    const moveForwardOneDay = () => {
        date.setDate(date.getDate()+1);
    }



    return ( 

            <RouteCalendar date={date} moveForwardOneDay={moveForwardOneDay} />

     );
}
 
export default ManagerRoutesContainer;