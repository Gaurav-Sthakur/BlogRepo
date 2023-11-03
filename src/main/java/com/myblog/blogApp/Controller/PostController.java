package com.myblog.blogApp.Controller;


import com.myblog.blogApp.payload.PostDto;
import com.myblog.blogApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> savePost(@Valid @RequestBody PostDto postDto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPo(){

        return postService.getAllPost();
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostBy(@PathVariable("id") long id){
        return new ResponseEntity<PostDto>(postService.getPostById(id),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
   @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePostBy(@PathVariable("id") long id,@RequestBody PostDto postDto){
       return new ResponseEntity<PostDto>(postService.updatePostById(postDto, id),HttpStatus.OK);
   }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
   public ResponseEntity<String> deletePostBy(@PathVariable("id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
    }
}
