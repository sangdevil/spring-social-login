package com.oauth.infra.oauth.kakao.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoToken (
        String tokenType,
        String accessToken,
        String idToken,
        Integer expiresIn,
        String refreshToken,
        Integer refreshTokenExpiresIn,
        String scope
) {

    @Override
    public String toString() {
        return String.format("tokenType : %s, accessToken : %s, idToken : %s", tokenType, accessToken, idToken);
    }

}
