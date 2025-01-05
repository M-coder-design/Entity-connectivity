package com.example.media.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "social_user")
    private SocialUser socialUser;

    private String description;


public void setUser(SocialUser socialUser){
    this.socialUser = socialUser;
    if (socialUser.getSocialProfile() != this)
        socialUser.setSocialProfile(this);
}
}
