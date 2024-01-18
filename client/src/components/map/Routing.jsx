import { useEffect } from "react";
import L from "leaflet";
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";
import { useMap } from "react-leaflet";
L.Marker.prototype.options.icon = L.icon({
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png"
});
export default function Routing({currentOrder, previousOrder,orderWayPoints}) {

  const map = useMap();

  useEffect(() => {
    console.log("previous", previousOrder.latitude, previousOrder.longitude)
    console.log("current", currentOrder.latitude, currentOrder.longitude)
    
    console.log(orderWayPoints,"All waypoints in map");
    if (previousOrder) {

      if (!map) return;

    
    const coords = orderWayPoints.map((waypoint)=>{
        return [L.latLng(waypoint)]
      }) 

    console.log(coords);
    const routingControl = L.Routing.control({
      waypoints: [coords]
      // routeWhileDragging: true,
    }).addTo(map);


    return () =>{
      console.log("this is the map property", routingControl)
      map.removeControl(routingControl);
    }
    }

    

  }, [currentOrder, previousOrder,orderWayPoints]);

  

  return null;
}