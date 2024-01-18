import L from "leaflet";
import { createControlComponent } from "@react-leaflet/core";
import "leaflet-routing-machine";
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import { useMap } from "react-leaflet";
import { useEffect } from "react";

// const createRoutineMachineLayer = ({ position, start, end, color }) => {
//   const instance = L.Routing.control({
//     position, 
//     collapsible: true,
//     waypoints: [
//       start,
//       end
//     ],
//     lineOptions: {
//       styles: [
//         {
//           color,
//         },
//       ],
//     },
//   });

//   return instance;
// };

// const RoutingMachine = createControlComponent(createRoutineMachineLayer);

const RoutingMachine = ({position, start, end, color}) => {
  const map = useMap();

    useEffect(() => {
      if (!map) return;
      const routingControl = L.Routing.control({
        position, 
        collapsible: true,
        waypoints: [start, end],
        lineOptions: {
        styles: [
            {
              color,
            },
          ],
        }
      }).addTo(map);
  
      return routingControl;
    }, [map])

    return null;

    

}

export default RoutingMachine;