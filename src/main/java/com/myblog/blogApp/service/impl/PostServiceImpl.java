package com.myblog.blogApp.service.impl;

import com.myblog.blogApp.entities.Post;
import com.myblog.blogApp.exception.ResourceNotFoundException;
import com.myblog.blogApp.payload.PostDto;
import com.myblog.blogApp.repository.PostRepository;
import com.myblog.blogApp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

     @Autowired
     private PostRepository postRepo;
     @Autowired
     private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto posts) {


        Post post=mapToEntity(posts);

        Post postEntity = postRepo.save(post);
        PostDto Dto = mapToDto(postEntity);

        return Dto;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map( p ->mapToDto(p)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {

        Post existingPost = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        existingPost.setTitle(postDto.getTitle());
        existingPost.setDescription(postDto.getDescription());
        existingPost.setContent(postDto.getContent());
        Post post = postRepo.save(existingPost);
        return mapToDto(post);

    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepo.delete(post);
    }

    public PostDto mapToDto(Post postEntity) {
        PostDto postDto=modelMapper.map(postEntity,PostDto.class);

//        postDto.setId(postEntity.getId());
//        postDto.setTitle(postEntity.getTitle());
//        postDto.setDescription(postEntity.getDescription());
//        postEntity.setContent(postEntity.getContent());

        return postDto;
    }

    public Post mapToEntity(PostDto posts) {
        Post post=modelMapper.map(posts,Post.class);

//        post.setTitle(posts.getTitle());
//        post.setDescription(posts.getDescription());
//        post.setContent(posts.getContent());
        return post;
    }
}
