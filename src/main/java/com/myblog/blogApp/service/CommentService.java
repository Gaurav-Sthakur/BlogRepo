package com.myblog.blogApp.service;

import com.myblog.blogApp.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto cmDto);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto getCommentById(long postId,long cmId);
    CommentDto updateComment(long postId,long cmId,CommentDto cmDto);

    void deleteComment(long postId,long cmId);
}
