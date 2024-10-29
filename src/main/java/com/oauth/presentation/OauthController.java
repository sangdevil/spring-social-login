package com.oauth.presentation;

import com.oauth.application.OauthService;
import com.oauth.domain.OauthServerType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OauthController {

    private final OauthService oauthService;

    /**
     * /kakao, /google 등 소셜 로그인 요청이 오면, 각 소셜 플랫폼에 걸맞는 client_id, secret 등을 담아서
     * 유저를 해당 플랫폼으로 리다이렉트 시킴.
     * @param oauthServerType
     * @return
     */
    @GetMapping("/{oauthServerType}")
    public ResponseEntity<Void> redirectAuthCodeRequestUrl(@PathVariable("oauthServerType") OauthServerType oauthServerType) {

        System.out.println("redirectAuthCodeRequestUrl called");
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        System.out.println("User is being redirected to " + redirectUrl);

        return ResponseEntity.status(HttpStatus.FOUND) // HTTP 302
                .location(URI.create(redirectUrl))
                .build();
    }


    @GetMapping("/login/{oauthServerType}")
    ResponseEntity<Long> login(
            @PathVariable OauthServerType oauthServerType,
            @RequestParam("code") String code
    ) {
        Long login = oauthService.login(oauthServerType, code);
        return ResponseEntity.ok(login);
    }
}
