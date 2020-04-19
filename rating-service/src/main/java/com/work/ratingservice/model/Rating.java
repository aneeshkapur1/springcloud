package com.work.ratingservice.model;

import javax.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Rating(Long id,String bookId, String stars) {
        this.id = id;
        this.bookId = bookId;
        this.stars = stars;
    }

    public Rating() {
    }

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "stars")
    private String stars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
