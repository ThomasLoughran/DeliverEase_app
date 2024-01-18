
// DO NOT GET RID OF
import "leaflet/dist/leaflet.css"
import React, { useEffect, useState, useRef } from "react";
import L from "leaflet";
import {
  TileLayer,
  MapContainer,
  LayersControl
} from "react-leaflet";

import { useUser } from "../../contexts/UserContext";
import Routing from "./Routing";




import RoutingControl from './RoutingControl'

const Map = ({currentOrder, previousOrder,data,distCentre}) => {
  const [map, setMap] = useState(null);
  
  const [start, setStart] = useState([previousOrder.latitude, previousOrder.longitude]);
  const [end, setEnd] = useState([currentOrder.latitude, currentOrder.longitude]);
  const [key, setKey] = useState(0);
  const [allOrders,setAllOrders]=useState([]);

  const fetchAllOrders = async () => {
    try {
      const response = await fetch(`http://localhost:8080/routes/${data.id}/all-orders`);
      const orderData = await response.json();
      setAllOrders(orderData);

    } catch (error) {
      console.error("Error fetching all orders:", error);
    }
  };

  

  useEffect(() => {
    fetchAllOrders();
  }, []); 

  const maps = {
    base: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
  };

  return(
  <MapContainer center={[currentOrder.latitude, currentOrder.longitude]} zoom={13} style={{ height: "100vh" }}>
    <TileLayer
      attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
      url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
    />
    <Routing key={key} currentOrder={currentOrder} previousOrder={previousOrder}/>
  </MapContainer>)
  
};

export default Map;
