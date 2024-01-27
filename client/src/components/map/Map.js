
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

const Map = ({ currentOrder, previousOrder, data, distCentre }) => {


  const [start, setStart] = useState([previousOrder.latitude, previousOrder.longitude]);
  const [end, setEnd] = useState([currentOrder.latitude, currentOrder.longitude]);
  const [key, setKey] = useState(0);
  const [allOrders, setAllOrders] = useState([]);
  const [orderWayPoints, setOrderWayPoints] = useState([])

  //const orderPoints = [];

  // orderPoints.push([[distCentre.latitude,distCentre.longitude]])

  // for(let i=0;i<data.length;i++){
  //   orderPoints.push([data[i].longitude,data[i].longitude])
  // }

  // orderPoints.push([[distCentre.latitude,distCentre.longitude]])



  const fetchAllOrders = async () => {

    try {
      const orderPoints = [];
      const response = await fetch(`http://localhost:8080/routes/${data.id}/all-orders`);
      const orderData = await response.json();

      orderPoints.push([distCentre.latitude, distCentre.longitude])

      for (let i = 0; i < orderData.length; i++) {
        orderPoints.push([orderData[i].latitude, orderData[i].longitude])
      }

      orderPoints.push([distCentre.latitude, distCentre.longitude])

      setOrderWayPoints(orderPoints)

      //console.log(orderPoints);




    } catch (error) {
      console.error("Error fetching all orders:", error);
    }
  };





  // setOrderWayPoints([orderPoints,[distCentre.latitude,distCentre.longitude]])



  useEffect(() => {
    fetchAllOrders();
  }, []);

  const maps = {
    base: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
  };

  return (
    <MapContainer center={[currentOrder.latitude, currentOrder.longitude]} zoom={13} style={{ height: "100vh" }}>
      <TileLayer
        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <Routing orderWayPoints={orderWayPoints}/>
    </MapContainer>)

};

export default Map;
