package com.oauth.presentation;

import com.oauth.domain.OauthServerType;
import org.springframework.core.convert.converter.Converter;

public class OauthServerConverter implements Converter<String, OauthServerType> {
    @Override
    public OauthServerType convert(String source) {
        return OauthServerType.fromName(source);
    }
}
