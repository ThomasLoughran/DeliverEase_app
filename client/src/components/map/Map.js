// DO NOT GET RID OF
import "leaflet/dist/leaflet.css"
import React, { useEffect, useState, useRef } from "react";
import L from "leaflet";
import {
  TileLayer,
  MapContainer,
} from "react-leaflet";

import { useUser } from "../../contexts/UserContext";
import Routing from "./Routing";
import RoutingMachine from "./RoutingMachine";

const Map = ({currentOrder, previousOrder}) => {
  const [map, setMap] = useState(null);
  
  // const [start, setStart] = useState([previousOrder.latitude, previousOrder.longitude]);
  // const [end, setEnd] = useState([currentOrder.latitude, currentOrder.longitude]);


  // useEffect(() => {
  // console.log(currentOrder, "current order changed")
  // }, [currentOrder])

  const maps = {
    base: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
  };



// console.log("test")

  return(
  <MapContainer center={[currentOrder.latitude, currentOrder.longitude]} zoom={13} style={{ height: "100vh" }}>
    <TileLayer
      attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
      url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
    />

    <RoutingMachine key={`${previousOrder}-${currentOrder}`} start={[currentOrder.latitude, currentOrder.longitude]} end={[previousOrder.latitude, previousOrder.longitude]}/>
  </MapContainer>
  )

};

export default Map;