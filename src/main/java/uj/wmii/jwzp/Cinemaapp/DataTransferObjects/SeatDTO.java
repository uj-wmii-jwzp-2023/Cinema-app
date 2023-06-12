package uj.wmii.jwzp.Cinemaapp.DataTransferObjects;

import uj.wmii.jwzp.Cinemaapp.models.Availability;

public class SeatDTO {
    private Long cinemaHallId;
    private Availability availability;


    public SeatDTO() {}

    public SeatDTO(Long cinemaHallId, Availability availability) {
        this.cinemaHallId = cinemaHallId;
        this.availability = availability;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
