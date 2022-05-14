package com.example.rentit.api;

import com.example.rentit.core.post.domain.Post;
import com.example.rentit.core.post.domain.PostRequest;
import com.example.rentit.core.post.domain.PostResponse;
import com.example.rentit.core.post.service.PostService;
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
 * @since 4/7/2022
 */
@Slf4j
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getPosts(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok().body(postService.getAllPosts(page));
    }
    @GetMapping("/postsbyuser")
    public ResponseEntity<List<PostResponse>> getPostsByUser(@RequestParam String vehicleOwnerUsername, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok().body(postService.getPostsByVehicleOwnerUsername(vehicleOwnerUsername, page));
    }

    @GetMapping("/postbyvehicleid")
    public ResponseEntity<Post> getPostByVehicleId(@RequestParam long vehicleId) {
        return ResponseEntity.ok().body(postService.getPostByVehicleId(vehicleId));
    }

    @PostMapping("/post")
    public ResponseEntity<Post> post(@RequestBody PostRequest postRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/post").toString());
        return ResponseEntity.created(uri).body(postService.savePost(postRequest));
    }

    @DeleteMapping("/deletepost")
    public ResponseEntity<?> deletePost(@RequestParam String postId) {
        postService.deletePost(Long.parseLong(postId));
        return ResponseEntity.ok().build();
    }
}
