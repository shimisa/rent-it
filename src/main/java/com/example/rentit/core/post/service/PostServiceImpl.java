package com.example.rentit.core.post.service;

import com.example.rentit.core.post.domain.Post;
import com.example.rentit.core.post.domain.PostRequest;
import com.example.rentit.core.post.repo.PostRepo;
import com.example.rentit.core.vehicle.domain.Vehicle;
import com.example.rentit.core.vehicle.repo.VehicleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/7/2022
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final VehicleRepo vehicleRepo;
    private static final int MAX_POSTS_PER_PAGE = 20;
    private static final int POST_PERIOD_DAYS = 30;

    /**
     * save the post into the database
     * firs check if exists a post of this car that posted in the last POST_PERIOD_DAYS days
     * @param postRequest
     * @return
     */
    @Override
    public Post savePost(PostRequest postRequest) {
        postRepo.findPostByVehicleLicenseNo(postRequest.getLicenseNo()).ifPresent(
                post -> {
                    if (post.getPostedAt().plusDays(POST_PERIOD_DAYS).isAfter(LocalDateTime.now())) {
                        log.error("This car already posted at: {}", post.getPostedAt());
                        throw new IllegalStateException("This car already posted" );
                    }
                }

        );
        log.info("Saving Post into the database: {}", postRequest);
        Vehicle postedVehicle = vehicleRepo.findByLicenseNo(postRequest.getLicenseNo()).orElseThrow();
        Post post = new Post(
                postRequest.getHeader(),
                postRequest.getDescription(),
                postRequest.getFromDate(),
                postRequest.getTillDate(),
                postedVehicle
        );
        return postRepo.save(post);
    }

    @Override
    public Post getPostByPostId(long postId) {
        log.info("Fetching post id: {}", postId);
        return postRepo.findPostByPostId(postId).orElseThrow();
    }

    @Override
    public Post getPostByVehicleId(long vehicleId) {
        log.info("Fetching post by vehicle id: {}", vehicleId);
        return postRepo.findPostByVehicleId(vehicleId).orElseThrow();
    }

    @Override
    public Post getPostByVehicleOwnerId(long vehicleOwnerId) {
        log.info("Fetching post by vehicle owner id: {}", vehicleOwnerId);
        return postRepo.findPostByVehicleOwnerId(vehicleOwnerId).orElseThrow();
    }

    @Override
    public Post getPostByVehicleOwnerUsername(String vehicleOwnerUsername) {
        log.info("Fetching post by vehicle owner username: {}", vehicleOwnerUsername);
        return postRepo.findPostByVehicleOwnerEmail(vehicleOwnerUsername).orElseThrow();
    }

    @Override
    public List<Post> getAllPosts(int page) {
        log.info("Fetching all posts from page {}", page);
        Pageable pageable = PageRequest.of(page, MAX_POSTS_PER_PAGE);
        return postRepo.findAll(pageable).getContent();
    }
}
