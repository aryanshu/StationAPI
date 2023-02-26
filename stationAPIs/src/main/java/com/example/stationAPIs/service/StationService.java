package com.example.stationAPIs.service;

import com.example.stationAPIs.repository.StationRepository;
import com.example.stationAPIs.station.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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

    public Optional<Station> getStationById(Long stationId) throws IOException {
        boolean exists = stationRepository.existsById(stationId);

        if(!exists)
        {
            throw new IOException("station Id doesn't exist");
        }

        return stationRepository.findById(stationId);
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
        boolean stationById = stationRepository.existsById(stationId);

        if(stationById==false) {
            throw new Exception(String.format("Station with stationId :{} doesn't exist",stationId));
        }

        stationRepository.deleteById(stationId);
        return String.format("Deleted StationId :%d Successfully",stationId);
    }

    public List<Station> getLimitedStation(Long limit) {
        return stationRepository.findAll().subList(0, Math.toIntExact(limit));
    }

    public List<Station> sorting(String sortOrder, String field, Long limit) throws Exception {

        if(!field.equals("station_pricing")){
            throw new Exception("Invalid param to sort the data, Please provide \"station_pricing\"");
        }

        Sort sort= Sort.by(Sort.Direction.ASC, "stationPrice");
        if(limit==null)
            return stationRepository.findAll(sort);
        return stationRepository.findAll(sort).subList(0, Math.toIntExact(limit));
    }


}
