package com.vi5hnu.blogapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull private String name;
    @NonNull private String email;
    @NonNull private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "post_id",referencedColumnName = "id")
    private Post post;
}
