import { useEffect, useState } from "react";
import L from "leaflet";
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";
import { useMap } from "react-leaflet";
L.Marker.prototype.options.icon = L.icon({
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png"
});
export default function Routing({currentOrder, previousOrder,orderWayPoints}) {

  const [count,setCount] = useState(0);

  const map = useMap();

  useEffect(() => {
    console.log("previous", previousOrder.latitude, previousOrder.longitude)
    console.log("current", currentOrder.latitude, currentOrder.longitude)
    setCount(count+1)
    if (count<2){
    
    const coords = orderWayPoints.map((waypoint)=>{
        return L.latLng(waypoint[0],waypoint[1])
      }) 

    const routingControl = L.Routing.control({
      waypoints: coords,
      fitSelectedRoutes: true,
      draggableWaypoints: false,
      routeWhileDragging: false,
      lineOptions: {
          addWaypoints: false,
          styles: [{ color: '#242c81', weight: 2 }]
      }
     }).addTo(map);
  
   }    

  }, [orderWayPoints]);

  return null;
}