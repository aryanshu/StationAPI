package com.example.stationAPIs.station;
import javax.persistence.*;

@Entity
@Table(
    name = "StationDetails"
)
public class Station {


    @SequenceGenerator(
            name = "station_sequence",
            sequenceName = "station_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "station_sequence"
    )
    @Column(
            name = "station_id",
            nullable = false,
            unique = true
    )
    @Id
    private Long stationId;
    @Column(
            name = "station_name",
            nullable = false,
            unique = true
    )
    private String stationName;
    @Column(
            name = "station_image",
            nullable = false
    )
    private String stationImage;
    @Column(
            name = "station_price",
            nullable = false
    )
    private Long stationPrice;

    @Column(
            name = "station_address",
            nullable = false,
            unique = true
    )
    private String stationAddress;

    public Station() {
    }

    public Station(String stationName, String stationImage, Long stationPrice, String stationAddress) {
        this.stationName = stationName;
        this.stationImage = stationImage;
        this.stationPrice = stationPrice;
        this.stationAddress = stationAddress;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationImage() {
        return stationImage;
    }

    public void setStationImage(String stationImage) {
        this.stationImage = stationImage;
    }

    public Long getStationPrice() {
        return stationPrice;
    }

    public void setStationPrice(Long stationPrice) {
        this.stationPrice = stationPrice;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", stationImage='" + stationImage + '\'' +
                ", stationPrice=" + stationPrice +
                ", stationAddress='" + stationAddress + '\'' +
                '}';
    }
}
