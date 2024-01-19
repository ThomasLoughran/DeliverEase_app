import { useEffect } from "react";
import L from "leaflet";
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";
import { useMap } from "react-leaflet";

import { useState } from "react";
L.Marker.prototype.options.icon = L.icon({
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png"
});


export default function Routing({currentOrder, previousOrder}) {

  const [count, setCount] = useState(0);

  const map = useMap();

  useEffect(() => {
    console.log("previous", previousOrder.latitude, previousOrder.longitude)
    console.log("current", currentOrder.latitude, currentOrder.longitude)
    setCount(count+1)

    // if (count > 4){
      if (!map) return;


      const layerGroup = L.layerGroup().addTo(map);

      

    const routingControl = L.Routing.control({
      waypoints: [L.latLng(previousOrder.latitude, previousOrder.longitude), L.latLng(currentOrder.latitude, currentOrder.longitude)],
      // routeWhileDragging: true,
    }).addTo(map);

    console.log(layerGroup)




    return () => {
      console.log("Removing routing control");
      if (routingControl) {
        try {
          map.removeControl(routingControl);
        } catch (error) {
          console.error("Error removing routing control:", error);
        }
      }
    };
    }
  // }


    

, [map, currentOrder, previousOrder, map.routingControl]);

  

  return null;
}