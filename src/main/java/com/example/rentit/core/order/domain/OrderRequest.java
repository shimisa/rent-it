package com.example.rentit.core.order.domain;

import lombok.*;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 5/8/2022
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderRequest {
    String orderedByEmail;
    Long postId;
}
