package com.oauth.infra.oauth.kakao.client;

import com.oauth.infra.oauth.kakao.dto.KakaoMemberResponse;
import com.oauth.infra.oauth.kakao.dto.KakaoToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
public interface KakaoApiClient {

    /**
     * https://kauth.kakao.com/oauth/token에 Post 요청을 보냄.
     * auth_code, client_id, secret 등을 담아서 URL내에 해당 정보를 전부 담아서 카카오로 전달.
     * 카카오는 Token의 정보를 담아 응답해주고, 여기서 해당 HTTP 응답을 KakaoToken Class로 변환
     * 특이하게도 redirect_uri가 다시 parameter에 포함되어 들어가는데, 302 응답을 기대해서 그런 것이 아니라
     * auth_code, access_token에서의 redirect_uri가 동일한 지 체크를 위한 보안상 이유.
     * @param params
     * @return
     */
    @PostExchange(url = "https://kauth.kakao.com/oauth/token", contentType = APPLICATION_FORM_URLENCODED_VALUE)
    KakaoToken fetchToken(@RequestParam(value = "params") MultiValueMap<String, String> params);

    /**
     * https://kapi.kakao.com/v2/user/me로 Get 요청을 보냄.
     * 유저의 id, email 등을 포함한 개인정보를 카카오에서 HTTP 응답으로 주고, 해당 응답을 KakaoMemberResponse로 변환.
     * @param bearerToken
     * @return
     */
    @GetExchange("https://kapi.kakao.com/v2/user/me")
    KakaoMemberResponse fetchMember(@RequestHeader(name = AUTHORIZATION) String bearerToken);

}
