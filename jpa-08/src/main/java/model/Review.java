package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hotel_review")
public class Review {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    private int rate;
    private String comment;
    @Column(name = "rating_date")
    private Date ratingDate;

    public Review() {
    }

    public Review(Long id, Hotel hotel, int rate, String comment, Date ratingDate) {
        this.id = id;
        this.hotel = hotel;
        this.rate = rate;
        this.comment = comment;
        this.ratingDate = ratingDate;
    }

    public Long getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }

    public Date getRatingDate() {
        return ratingDate;
    }
}
