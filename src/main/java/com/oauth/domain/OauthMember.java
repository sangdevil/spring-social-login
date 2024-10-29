package com.oauth.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "oauth_member",
uniqueConstraints = {
        @UniqueConstraint(
                name =  "oauth_id_unique",
                columnNames = {
                        "oauth_server_id",
                        "oauth_server"
                }
        )
})
public class OauthMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OauthId oauthId;

    private String nickname;

    private String profileImageUrl;

}
