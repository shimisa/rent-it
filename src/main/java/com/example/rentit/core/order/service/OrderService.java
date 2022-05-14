package com.example.rentit.core.order.service;

import com.example.rentit.core.order.domain.*;

import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 5/8/2022
 */
public interface OrderService {
    List<OrderResponse> getUserOrders(String email, int page);
    List<OfferResponse> getUserOffers(String email, int page);
    Order placeOrder(OrderRequest orderRequest);
    Order updateOrderStatus(Long orderId, OrderStatus orderStatus);
//    Order cancelOrder(Long orderId);
//    Order confirmOrder(Long orderId);

}
