
// DO NOT GET RID OF
import "leaflet/dist/leaflet.css"
import React, { useEffect, useState, useRef } from "react";
import L from "leaflet";
import {
  TileLayer,
  MapContainer,
  LayersControl
} from "react-leaflet";

import RoutingControl from './RoutingControl'

const Map = () => {
  const [map, setMap] = useState(null);
  const [start, setStart] = useState([38.9072, -77.0369])
  const [end, setEnd] = useState([37.7749, -122.4194])

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
