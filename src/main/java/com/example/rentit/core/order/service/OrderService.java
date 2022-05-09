package com.example.rentit.core.order.service;

import com.example.rentit.core.order.domain.Order;
import com.example.rentit.core.order.domain.OrderRequest;

import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 5/8/2022
 */
public interface OrderService {
    List<Order> getUserOrders(String email, int page);
    List<Order> getUserOffers(String email, int page);
    Order placeOrder(OrderRequest orderRequest);
    Order declineOrder(Long orderId);
    Order confirmOrder(Long orderId);

}
