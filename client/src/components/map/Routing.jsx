import { useEffect } from "react";
import L from "leaflet";
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";
import { useMap } from "react-leaflet";
L.Marker.prototype.options.icon = L.icon({
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png"
});
export default function Routing({currentOrder, previousOrder}) {

  const map = useMap();

  useEffect(() => {

    if (!map) return;

    const routingControl = L.Routing.control({
      waypoints: [L.latLng(previousOrder.latitude, previousOrder.longitude), L.latLng(currentOrder.latitude, currentOrder.longitude)],
      routeWhileDragging: true
    }).addTo(map);

    

  }, [map]);

  return null;
}