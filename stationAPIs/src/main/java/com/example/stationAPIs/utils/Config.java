package com.example.stationAPIs.utils;

import com.example.stationAPIs.repository.StationRepository;
import com.example.stationAPIs.station.Station;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(StationRepository repository){
        return args -> {
            Station arsenal=new Station("London Arsenal","url_1",5L,"Underground Ltd, Highbury Hill, London N5 1LP, United Kingdom" );
            repository.save(arsenal);
            Station bakerStreet=new Station("Baker Street","url_2",6L,"Marylebone Rd, London NW1 5LJ, United Kingdom");
            repository.save(bakerStreet);
            Station balham=new Station("Balham","url_3",10L, "London Borough of Wandsworth, south London, England");
            repository.save(balham);
        };
    }
}
