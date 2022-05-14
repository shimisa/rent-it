package com.example.rentit.core.post.service;

import com.example.rentit.core.post.domain.Post;
import com.example.rentit.core.post.domain.PostRequest;
import com.example.rentit.core.post.domain.PostResponse;

import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/7/2022
 */
public interface PostService {
    Post savePost(PostRequest postRequest);
    void deletePost(Long postId);
    Post getPostByPostId(long postId);
    Post getPostByVehicleId(long vehicleId);
    Post getPostByVehicleOwnerId(long vehicleOwnerId);
    List<PostResponse>  getPostsByVehicleOwnerUsername(String vehicleOwnerUsername, int page);
    List<PostResponse> getAllPosts(int page);
    PostResponse postToPostResponse(Post post);
}
