package com.example.rentit.core.order.service;

import com.example.rentit.core.order.domain.OfferResponse;
import com.example.rentit.core.order.domain.Order;
import com.example.rentit.core.order.domain.OrderRequest;
import com.example.rentit.core.order.domain.OrderResponse;
import com.example.rentit.core.order.repo.OrderRepo;
import com.example.rentit.core.post.domain.Post;
import com.example.rentit.core.post.repo.PostRepo;
import com.example.rentit.core.post.service.PostService;
import com.example.rentit.userservice.domain.User;
import com.example.rentit.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 5/8/2022
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepo orderRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final PostService postService;
    private static final int MAX_ORDERS_PER_PAGE = 20;

    @Override
    public List<OrderResponse> getUserOrders(String email, int page) {
        log.info("Fetching orders of renter by username: {}", email);
        Pageable pageable = PageRequest.of(page, MAX_ORDERS_PER_PAGE);
        return orderRepo.findOrderByOrderedByEmail(email,pageable).stream().map(this::toOrderResponse).collect(toList());
    }

    @Override
    public List<OfferResponse> getUserOffers(String email, int page) {
        log.info("Fetching orders of rental by username: {}", email);
        Pageable pageable = PageRequest.of(page, MAX_ORDERS_PER_PAGE);
        return orderRepo.findOrderByPostVehicleOwnerEmail(email,pageable).stream().map(this::toOfferResponse).collect(toList());
    }

    private OfferResponse toOfferResponse(Order order) {
        return new OfferResponse(
                order.getOrderId(),
                order.getOrderRequestDate(),
                order.isValid(),
                order.isConfirmed(),
                order.isDeclined(),
                order.getPost().getVehicle().getLicenseNo(),
                postService.postToPostResponse(order.getPost())
        );
    }

    private OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getOrderId(),
                order.getOrderRequestDate(),
                order.isValid(),
                order.isConfirmed(),
                order.isDeclined(),
                postService.postToPostResponse(order.getPost())
        );
    }

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        log.info("Placing an order: {}", orderRequest);
        User orderedBy = userRepo.findByEmail(orderRequest.getOrderedByEmail()).orElseThrow();
        Post post = postRepo.findPostByPostId(orderRequest.getPostId()).orElseThrow();
        Order order = new Order(orderedBy, post);
        return orderRepo.save(order);
    }

    @Override
    public Order declineOrder(Long orderId) {
        log.info("Declining order: {}", orderId);
        Order order = orderRepo.findOrderByOrderId(orderId).orElseThrow();
        order.setDeclined(true);
        return orderRepo.save(order);
    }

    @Override
    public Order confirmOrder(Long orderId) {
        log.info("Confirming order: {}", orderId);
        Order order = orderRepo.findOrderByOrderId(orderId).orElseThrow();
        order.setConfirmed(true);
        return orderRepo.save(order);
    }


}
