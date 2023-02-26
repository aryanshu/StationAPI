package com.example.stationAPIs.repository;

import com.example.stationAPIs.station.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station,Long> {
    Station findStationByStationName(String stationName);
    Station findStationByStationAddress(String stationAddress);
}
