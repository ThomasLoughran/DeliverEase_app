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
  
  const [start, setStart] = useState([previousOrder.latitude, previousOrder.longitude]);
  const [end, setEnd] = useState([currentOrder.latitude, currentOrder.longitude]);


  useEffect(() => {
  // console.log(currentOrder)


  // console.log(start);
  // console.log(end);

  // setStart([previousOrder.latitude, previousOrder.longitude]);
  // setEnd([currentOrder.latitude, currentOrder.longitude]);


  }, [])

  const maps = {
    base: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
  };

  if (start == null && end == null) {
    console.log("was null at one point")
    return <p>Loading...</p>

  }


  return(
  <MapContainer center={[currentOrder.latitude, currentOrder.longitude]} zoom={13} style={{ height: "100vh" }}>
    <TileLayer
      attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
      url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
    />
    {/* <Routing currentOrder={currentOrder} previousOrder={previousOrder}/> */}
    <RoutingMachine key={`${previousOrder}-${currentOrder}`} start={[currentOrder.latitude, currentOrder.longitude]} end={[previousOrder.latitude, previousOrder.longitude]}/>
  </MapContainer>)
  
};

export default Map;