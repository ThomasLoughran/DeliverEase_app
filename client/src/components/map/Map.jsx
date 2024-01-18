
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




import RoutingControl from './RoutingControl'

const Map = ({currentOrder}) => {
  const { user } = useUser();
  const [map, setMap] = useState(null);
  const [start, setStart] = useState([user.distributionCentre.latitude, user.distributionCentre.longitude])
  const [end, setEnd] = useState([currentOrder.latitude, currentOrder.longitude])
  

  const maps = {
    base: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
  };

  return (
    <>
      <MapContainer
        center={[37.0902, -95.7129]}
        zoom={3}
        zoomControl={true}
        style={{ height: "80vh", width: "100%", padding: 0 }}
        whenCreated={map => setMap(map)}
      >
        
        
        <RoutingControl 
          position={'topright'} 
          start={start} 
          end={end} 
          color={'#757de8'} 
        />
            <TileLayer
              attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
              url={maps.base}
            />
      </MapContainer>
    </>
  );
};

export default Map;
