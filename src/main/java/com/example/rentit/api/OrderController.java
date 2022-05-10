package com.example.rentit.api;

import com.example.rentit.core.order.domain.OfferResponse;
import com.example.rentit.core.order.domain.Order;
import com.example.rentit.core.order.domain.OrderRequest;
import com.example.rentit.core.order.domain.OrderResponse;
import com.example.rentit.core.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 5/9/2022
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/getuserorders")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@RequestParam String email, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok().body(orderService.getUserOrders(email, page));
    }

    @GetMapping("/getuseroffers")
    public ResponseEntity<List<OfferResponse>> getUserOffers(@RequestParam String email, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok().body(orderService.getUserOffers(email, page));
    }


    @PostMapping("/placeorder")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/order/placeorder").toString());
        return ResponseEntity.created(uri).body(orderService.placeOrder(orderRequest));
    }


    @PutMapping("/declineorder")
    public ResponseEntity<Order> declineOrder(@RequestBody Long orderId) {
        return ResponseEntity.ok().body(orderService.declineOrder(orderId));
    }

    @PutMapping("/confirmorder")
    public ResponseEntity<Order> confirmOrder(@RequestBody Long orderId) {
        return ResponseEntity.ok().body(orderService.confirmOrder(orderId));
    }

}
