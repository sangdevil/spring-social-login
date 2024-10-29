package com.oauth.domain.authcode;


import com.oauth.domain.OauthServerType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class AuthCodeRequestUrlProviderComposite {

    private final Map<OauthServerType, AuthCodeRequestUrlProvider> mapping;


    public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {
        mapping = providers.stream()
                .collect(Collectors.toMap(
                        AuthCodeRequestUrlProvider::supportServer,
                        identity()
                ));

    }

    public String provide(OauthServerType type) {
        return getProvider(type).provide();
    }

    private AuthCodeRequestUrlProvider getProvider(OauthServerType type) {
        return Optional.ofNullable(mapping.get(type)).orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인입니다."));
    }
}
