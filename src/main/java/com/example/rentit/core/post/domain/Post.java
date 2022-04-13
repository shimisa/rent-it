package com.example.rentit.core.post.domain;

import com.example.rentit.core.vehicle.domain.Vehicle;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 */
@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long postId;
    private LocalDateTime postedAt = LocalDateTime.now();

    @Column(length = 20)
    private String header;
    @Column(length = 100)
    private String description;

    private LocalDateTime fromDate;
    private LocalDateTime tillDate;


    @OneToOne
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    public Post(String header, String description, LocalDateTime fromDate, LocalDateTime tillDate, Vehicle vehicle) {
        this.header = header;
        this.description = description;
        this.fromDate = fromDate;
        this.tillDate = tillDate;
        this.vehicle = vehicle;
    }
}
