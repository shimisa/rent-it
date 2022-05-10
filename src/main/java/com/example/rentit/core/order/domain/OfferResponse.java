package com.example.rentit.core.order.domain;

import com.example.rentit.core.post.domain.PostResponse;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 5/10/2022
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OfferResponse {
    private Long orderId;
    LocalDateTime orderRequestDate;
    boolean valid;
    boolean confirmed;
    boolean declined;

    Long vehicleLicenseNo;
    PostResponse post;
}
