package com.myblog.blogApp.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(
        name="posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}

)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title" ,nullable=false)
    private String title;
    @Column(name="description" ,nullable=false)
    private String description;
    @Column(name="content" ,nullable=false)
    private String content;

    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Comment>  comments=new HashSet<>();

}
