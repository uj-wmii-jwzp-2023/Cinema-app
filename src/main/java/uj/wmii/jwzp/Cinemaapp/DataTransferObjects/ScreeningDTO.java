package uj.wmii.jwzp.Cinemaapp.DataTransferObjects;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ScreeningDTO {
    private String name;

    private Long cinemaHallId;

    private List<Long> movieIds;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal ticketPrice;
    public ScreeningDTO() {}

    public ScreeningDTO(String name, Long cinemaHallId, List<Long> movieIds, LocalDateTime startTime, LocalDateTime endTime,BigDecimal ticketPrice) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cinemaHallId = cinemaHallId;
        this.movieIds = movieIds;
        this.ticketPrice = ticketPrice;
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

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreeningDTO that = (ScreeningDTO) o;
        return name.equals(that.name) && cinemaHallId.equals(that.cinemaHallId) && movieIds.equals(that.movieIds) && startTime.equals(that.startTime) && endTime.equals(that.endTime) && ticketPrice.equals(that.ticketPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cinemaHallId, movieIds, startTime, endTime, ticketPrice);
    }

    @Override
    public String toString() {
        return "ScreeningDTO{" +
                "name='" + name + '\'' +
                ", cinemaHallId=" + cinemaHallId +
                ", movieIds=" + movieIds +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
