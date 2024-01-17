package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.Order;
import com.deliverease_group.webapp.models.Route;
import com.deliverease_group.webapp.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    RouteService routeService;

    @PostMapping(value = "/new-routes/{distCentreId}")
    public ResponseEntity<String> generateRoutes (@PathVariable Long distCentreId, @RequestParam LocalDate localDate){
        String routeStatus = routeService.generateRoutes(distCentreId,localDate);
        if (routeStatus.equals("Routes successfully created")) {
            return new ResponseEntity<>(routeStatus, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(routeStatus,HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping(value = "/all/{distCentreId}")
    public ResponseEntity<List<Route>> getRoutesByDistCentreAndDate(@PathVariable Long distCentreId, @RequestParam LocalDate localDate){
        return new ResponseEntity<>(routeService.findAllRoutesByDistCentreIdAndDate(distCentreId, localDate), HttpStatus.OK);
    }

    @GetMapping(value = "/complete/{distCentreId}")
    public ResponseEntity<List<Route>> getRoutesByCompletionStatus(@PathVariable Long distCentreId, @RequestParam boolean isComplete){
        return new ResponseEntity<>(routeService.findAllRoutesByDistCentreIdAndIsComplete(distCentreId, isComplete), HttpStatus.OK);
    }

    @GetMapping(value = "/{routeId}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long routeId){
        Route route = routeService.findRouteById(routeId);
        if (route != null){
            return new ResponseEntity<>(route, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(route, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/driver/{driverId}")
    public ResponseEntity<Route> getRouteByDriverIdAndDate(@PathVariable Long driverId, @RequestParam LocalDate localDate){
        Route route = routeService.findRouteByDriverIdAndDate(driverId, localDate);
        if (route != null){
            return new ResponseEntity<>(route, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(route, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{routeId}")
    public ResponseEntity<Route> updateRouteCompletion(@PathVariable Long routeId, @RequestParam boolean isComplete){
        Route route = routeService.updateRouteCompletion(routeId, isComplete);
        if (route != null){
            return new ResponseEntity<>(route, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(route, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{routeId}")
    public ResponseEntity<Route> deleteRouteById(@PathVariable Long routeId){
        routeService.deleteRouteById(routeId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
