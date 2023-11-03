package com.myblog.blogApp.service.impl;

import com.myblog.blogApp.entities.Comment;
import com.myblog.blogApp.entities.Post;
import com.myblog.blogApp.exception.BlogApiException;
import com.myblog.blogApp.exception.ResourceNotFoundException;
import com.myblog.blogApp.payload.CommentDto;
import com.myblog.blogApp.repository.CommentRepository;
import com.myblog.blogApp.repository.PostRepository;
import com.myblog.blogApp.service.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(long postId,CommentDto cmDto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = mapToCommentEntity(cmDto);
        comment.setPost(post);
        Comment save = commentRepo.save(comment);
        return mapToCommentDto(save);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
         Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Comment comment=new Comment();
       // comment.setPost(post);
        List<Comment> byPostId = commentRepo.findByPostId(postId);
        return byPostId.stream().map(this::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long cmId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepo.findById(cmId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", cmId));
        if(comment.getPost().getId()!=(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment not found for this post");
        }
        return mapToCommentDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long cmId, CommentDto cmDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepo.findById(cmId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", cmId));

        comment.setName(cmDto.getName());
        comment.setEmail(cmDto.getEmail());
        comment.setBody(cmDto.getBody());

        Comment save = commentRepo.save(comment);
        return mapToCommentDto(save);
    }

    @Override
    public void deleteComment(long postId, long cmId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepo.findById(cmId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", cmId));
        commentRepo.delete(comment);
    }

    public Comment mapToCommentEntity(CommentDto cmDto){

        Comment comment=modelMapper.map(cmDto,Comment.class);
        return comment;
    }

    public CommentDto mapToCommentDto(Comment comment){
        CommentDto cmDto=modelMapper.map(comment,CommentDto.class);
        return cmDto;
    }
}