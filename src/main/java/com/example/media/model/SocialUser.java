package com.example.media.model;

import jakarta.persistence.*;

@Entity
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_profile")
    private SocialProfile socialProfile;
}
