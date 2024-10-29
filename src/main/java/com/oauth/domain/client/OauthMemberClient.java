package com.oauth.domain.client;

import com.oauth.domain.OauthMember;
import com.oauth.domain.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}
