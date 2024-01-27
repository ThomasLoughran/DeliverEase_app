import { useEffect, useState } from "react";
import L, { latLng } from "leaflet";
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";
import { useMap } from "react-leaflet";
L.Marker.prototype.options.icon = L.icon({
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png"
});
export default function Routing({ orderWayPoints }) {

  const [count, setCount] = useState(0);

  const map = useMap();

  useEffect(() => {
    // console.log("previous", previousOrder.latitude, previousOrder.longitude)
    // console.log("current", currentOrder.latitude, currentOrder.longitude)
    setCount(count + 1)
    if (count < 2) {


      console.log(orderWayPoints)
      const coords = orderWayPoints.map((waypoint) => {
        return L.latLng(waypoint[0], waypoint[1])
      })

      //console.log(coords);


      // const routingControl = L.Routing.control({
      //   waypoints: coords,
      //   lineOptions : {
      //     draggableWaypoints:false
      //   }
      //   //routeWhileDragging: true
      // }).addTo(map);

      const routingControl = L.Routing.control({
        waypoints: L.latLng(),
        fitSelectedRoutes: true,
        draggableWaypoints: false,
        routeWhileDragging: false,
        collapsible: true,
        //createMarker: function() { return null; },
        lineOptions: {
          addWaypoints: false,
          styles: [{ color: '#242c81', weight: 2 }]
        }
      }).addTo(map);


      // return () =>{
      //   //console.log("this is the map property", routingControl)
      //   //if (!map.routingControl){
      //   //map.removeControl(routingControl);
      //   //}
      // }
    }


  }, [orderWayPoints]);



  return null;
}