package com.myblog.blogApp.service;

import com.myblog.blogApp.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto post);
    List<PostDto> getAllPost();
    PostDto getPostById(long id);
    PostDto updatePostById(PostDto postDto,long id);
    void deletePostById(long id);

}
