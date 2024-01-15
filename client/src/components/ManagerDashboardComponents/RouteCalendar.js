const RouteCalendar = ({date}) => {

    console.log(date);


    return ( 
        <>
        <h1>Hello from route calendar</h1>
        <p>Day Selected: {date.toLocaleDateString()}</p>
        </>
     );
}
 
export default RouteCalendar;