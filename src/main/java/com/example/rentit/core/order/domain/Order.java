package com.example.rentit.core.order.domain;

import com.example.rentit.core.post.domain.Post;
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
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long orderId;

    LocalDateTime orderRequestDate = LocalDateTime.now();
    boolean valid = true;
    boolean confirmed = false;
    boolean cancelledByRenter = false;
    boolean cancelledByRental = false;
    boolean declined = false;
    boolean executed = false;

    @ManyToOne
    @JoinColumn(name = "post_post_id")
    Post post;
}
