package com.example.rentit.core.order.repo;

import com.example.rentit.core.order.domain.Order;
import com.example.rentit.core.post.domain.Post;
import com.example.rentit.userservice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 5/8/2022
 */
public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findOrderByOrderId(Long orderId);
    Page<Order> findAll(Pageable pageable);
    Page<Order> findOrderByOrderedByEmail(String email, Pageable pageable);
    Page<Order> findOrderByPostVehicleOwnerEmail(String email, Pageable pageable);
    Page<Order> findOrderByPostPostId(Long postId, Pageable pageable);
}
