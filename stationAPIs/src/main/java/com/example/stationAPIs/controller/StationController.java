package com.example.stationAPIs.controller;

import com.example.stationAPIs.service.StationService;
import com.example.stationAPIs.station.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
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
    public Optional<Station> getStationById(@PathVariable Long StationId) throws IOException {
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
    public String deleteStation(@PathVariable Long StationId) throws Exception {
        return stationService.deleteById(StationId);
    }




}
