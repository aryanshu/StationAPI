package com.example.stationAPIs.service;

import com.example.stationAPIs.repository.StationRepository;
import com.example.stationAPIs.station.Station;
import com.example.stationAPIs.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StationService {

    @Autowired
    StationRepository stationRepository;
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public List<Station> getStation(Long limit, String sortOrder, String field) throws Exception {

        if(limit==null && sortOrder==null && field==null)
            return getAllStations();

        else if(limit!=null && sortOrder==null && field==null)
            return getLimitedStation(limit);

        return sorting(sortOrder,field,limit);
    }

    public Station getStationById(Long stationId) throws RecordNotFoundException {
        Station station=null;

        try {
            station = stationRepository.findById(stationId).orElseThrow(() -> new NoSuchElementException("Record not found"));
        } catch (NoSuchElementException e) {
            throw new RecordNotFoundException("Record with stationID " + stationId + " not found");
        }

        return station;
    }

    public Station addStation(Station station) throws Exception{
        Station stationByName = stationRepository.findStationByStationName(station.getStationName());
        Station stationByAddress = stationRepository.findStationByStationAddress(station.getStationAddress());

        if(stationByAddress!=null)
            throw new Exception("station at this address is already present, use some other Address");


        if(stationByName!=null)
            throw new Exception(String.format("Station with %s Name is Already present, Use some other Name", station.getStationName()));

        stationRepository.save(station);
        return station;

    }

    public Station updateStation(Long stationId, Station station) throws Exception {
        boolean stationByIdExist = stationRepository.existsById(stationId);

        if(stationByIdExist==false) {
            throw new Exception(String.format("Station Id :%d doesn't exist, Please provide correct Station Id",stationId));
        }

        Optional<Station> stationById = stationRepository.findById(stationId);
        Station stationByName = stationRepository.findStationByStationName(station.getStationName());
        Station stationByAddress = stationRepository.findStationByStationAddress(station.getStationAddress());



        if(stationByAddress!=null && stationByAddress.getStationId() != stationById.get().getStationId()) {
            throw new Exception("station at this address is already present, use some other Address");
        }


        if(stationByName!=null && stationByName.getStationId() != stationById.get().getStationId())
            throw new Exception(String.format("Station with :%s Name is Already present, Use some other Name", station.getStationName()));

        return stationRepository.save(station);
    }

    public String deleteById(Long stationId) throws Exception {
        Optional<Station> station = stationRepository.findById(stationId);
        if (station.isPresent()) {
            stationRepository.deleteById(stationId);
            return "Record with stationID " + stationId + " deleted successfully";
        } else {
            throw new RecordNotFoundException("Record with stationID " + stationId + " not found");
        }

    }

    public List<Station> getLimitedStation(Long limit) {
        return stationRepository.findAll().subList(0, Math.toIntExact(limit));
    }

    public List<Station> sorting(String sortOrder, String field, Long limit) throws Exception {

        if(field==null || !field.equals("station_pricing")){
            throw new Exception("Invalid param to sort the data, Please provide \"station_pricing\"");
        }

        Sort sort = null;

        if(sortOrder.equals("desc"))
            sort= Sort.by(Sort.Direction.DESC, "stationPrice");

        else if(sortOrder.equals("asc") || sortOrder==null)
            sort= Sort.by(Sort.Direction.ASC, "stationPrice");

        if(limit==null)
            return stationRepository.findAll(sort);
        return stationRepository.findAll(sort).subList(0, Math.toIntExact(limit));
    }


}
