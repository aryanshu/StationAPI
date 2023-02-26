package com.example.stationAPIs.controller;

import com.example.stationAPIs.service.StationService;
import com.example.stationAPIs.station.Station;
import com.example.stationAPIs.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/stations/")
public class StationController {

    @Autowired
    StationService stationService;

    @GetMapping("/")
    public List<Station> getStations(@RequestParam(required = false) Long limit,@RequestParam(name = "sort",required = false) String sortOrder, @RequestParam(name = "param",required = false) String field) throws Exception {
        return stationService.getStation(limit,sortOrder,field);
    }

    @GetMapping("/show/{StationId}")
    public Station getStationById(@PathVariable Long StationId) throws RecordNotFoundException {
        return stationService.getStationById(StationId);
    }

    @PostMapping("/addStation")
    public Station addStation(@RequestBody Station station) throws Exception {
        return stationService.addStation(station);
    }

    @PutMapping("/{StationId}/edit")
    public Station updateStation(@RequestBody Station station, @PathVariable Long StationId) throws Exception {
        return stationService.updateStation(StationId,station);
    }

    @DeleteMapping("/delete/{StationId}")
    public Map<String, Object> deleteStation(@PathVariable Long StationId) throws Exception {
        String result = stationService.deleteById(StationId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        return response;
    }




}
