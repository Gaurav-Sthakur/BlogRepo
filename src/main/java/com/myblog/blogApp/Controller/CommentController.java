package com.myblog.blogApp.Controller;

import com.myblog.blogApp.payload.CommentDto;
import com.myblog.blogApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    @Autowired
    private CommentService cmService;

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable("id") long postId, @RequestBody CommentDto cmDto) {
        return new ResponseEntity<CommentDto>(cmService.createComment(postId, cmDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDto> getCommentsByPost(
            @PathVariable("postId") long postId) {
        return cmService.getCommentsByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable("id") long postId, @PathVariable("id") long cmId) {
        return new ResponseEntity<CommentDto>(cmService.getCommentById(postId, cmId), HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable("id") long postId, @PathVariable("id") long cmId, CommentDto cmDto) {

        return new ResponseEntity<CommentDto>(cmService.updateComment(postId, cmId, cmDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(   @PathVariable("id") long postId, @PathVariable("id") long cmId){
       return new ResponseEntity<>("Deleted Succesfully",HttpStatus.OK);
    }
}