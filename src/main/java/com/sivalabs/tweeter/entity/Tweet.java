package com.sivalabs.tweeter.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="tweets")
@Data
public class Tweet implements Serializable {
    @Id
    @SequenceGenerator(name = "tweet_id_generator", sequenceName = "tweet_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "tweet_id_generator")
    private Long id;

    @Column(nullable=false)
    @NotEmpty
    private String content;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @JsonProperty("created_at")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
