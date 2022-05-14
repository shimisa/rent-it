package com.example.rentit.core.order.domain;

import com.example.rentit.core.post.domain.Post;
import com.example.rentit.userservice.domain.User;
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
    OrderStatus orderStatus = OrderStatus.PENDING;
    boolean valid = true;

    @ManyToOne
    @JoinColumn(name = "ordered_by_id")
    User orderedBy;

    @ManyToOne
    @JoinColumn(name = "post_post_id")
    Post post;

    public Order(User orderedBy, Post post) {
        this.orderedBy = orderedBy;
        this.post = post;
    }
}
