import L from "leaflet";
import { createControlComponent } from "@react-leaflet/core";
import "leaflet-routing-machine";
import { useEffect } from "react";


const createRoutineMachineLayer = ({ start = [0, 0], end = [0, 0] }) => {

console.log(start, "this is the start");
console.log(end, "this is the end");


  const instance = L.Routing.control({
    waypoints: [
      L.latLng(start[0], start[1]),
      L.latLng(end[0], end[1])
    ],
    collapsible: true,
  });


  return instance

};

const RoutingMachine = createControlComponent(createRoutineMachineLayer);

export default RoutingMachine;