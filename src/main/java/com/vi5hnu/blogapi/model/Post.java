package com.vi5hnu.blogapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "posts",
        uniqueConstraints = {
                @UniqueConstraint(name = "post_unique_title",columnNames = "title")
        }
)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="title",nullable = false) private String title;
    @Column(name="description",nullable = false) private String description;
    @Column(name="content",nullable = false) private String content;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)//owning side
    private List<Comment> comments;
}
