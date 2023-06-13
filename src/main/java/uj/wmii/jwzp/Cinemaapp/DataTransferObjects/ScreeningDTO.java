package uj.wmii.jwzp.Cinemaapp.DataTransferObjects;

import java.time.LocalDateTime;
import java.util.List;

public class ScreeningDTO {
    private String name;
    private Long cinemaHallId;
    private List<Long> movieIds;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ScreeningDTO() {}

    public ScreeningDTO(String name, Long cinemaHallId, List<Long> movieIds, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cinemaHallId = cinemaHallId;
        this.movieIds = movieIds;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDateTime getStartTime() { return startTime; }

    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }

    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
}
