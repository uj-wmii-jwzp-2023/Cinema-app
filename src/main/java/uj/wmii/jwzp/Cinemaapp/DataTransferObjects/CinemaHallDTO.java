package uj.wmii.jwzp.Cinemaapp.DataTransferObjects;

public class CinemaHallDTO {
    private Long cinemaId;

    public CinemaHallDTO() {}

    public CinemaHallDTO(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaHallId) {
        this.cinemaId = cinemaHallId;
    }
}
