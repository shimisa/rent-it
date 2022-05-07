package com.example.rentit.core.post.repo;

import com.example.rentit.core.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/7/2022
 */
public interface PostRepo extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);
    Optional<Post> findPostByPostId(long post_id);
    Optional<Post> findPostByVehicleId(long vehicle_id);
    Optional<Post> findPostByVehicleLicenseNo(long vehicle_licenseNo);
    Optional<Post> findPostByVehicleOwnerId(long owner_id);
    Page<Post> findPostByVehicleOwnerEmail(String vehicle_owner_email, Pageable pageable);
}
