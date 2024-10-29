package com.oauth.infra.oauth.kakao;


import com.oauth.domain.OauthMember;
import com.oauth.domain.OauthServerType;
import com.oauth.domain.client.OauthMemberClient;
import com.oauth.infra.oauth.kakao.client.KakaoApiClient;
import com.oauth.infra.oauth.kakao.dto.KakaoMemberResponse;
import com.oauth.infra.oauth.kakao.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public OauthMember fetch(String authCode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode));
        System.out.println(String.format("token info is\n%s", tokenInfo.toString()));
        KakaoMemberResponse kakaoMemberResponse =
                kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());

        return kakaoMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", authCode);
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("client_secret", kakaoOauthConfig.clientSecret());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());

        return params;
    }
}
