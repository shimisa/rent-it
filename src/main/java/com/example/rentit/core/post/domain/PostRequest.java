package com.example.rentit.core.post.domain;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/7/2022
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostRequest {
    private String header;
    private String description;
    private LocalDateTime fromDate;
    private LocalDateTime tillDate;
    private Long licenseNo;
}
