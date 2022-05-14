package com.example.rentit.core.order.domain;

import com.example.rentit.core.post.domain.Post;
import com.example.rentit.core.post.domain.PostResponse;
import com.example.rentit.userservice.domain.User;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 5/9/2022
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderResponse {
    private Long orderId;
    LocalDateTime orderRequestDate;
    boolean valid;
    OrderStatus orderStatus;

    PostResponse post;
}
