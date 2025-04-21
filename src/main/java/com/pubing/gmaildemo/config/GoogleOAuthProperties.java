package com.pubing.gmaildemo.config;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "google.oauth.client")
@Data
public class GoogleOAuthProperties {
    private String clientId;
    private String projectId;
    private String authUri;
    private String tokenUri;
    private String  authProviderX509CertUrl;
    private String clientSecret;
    private List<String> redirectUris;

    public String getClientAuthString(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("client_id", this.getClientId());
        map.put("project_id", this.getProjectId());
        map.put("auth_uri", this.getAuthUri());
        map.put("token_uri", this.getTokenUri());
        map.put("auth_provider_x509_cert_url", this.getAuthProviderX509CertUrl());

        map.put("client_secret", this.getClientSecret());
        map.put("redirect_uris", this.getRedirectUris());
        HashMap<String, Object> mapRoot = new HashMap<>();
        // 使用 Gson 转换为 JSON 字符串
        Gson gson = new Gson();
        mapRoot.put("web",gson.toJson(map));

        return gson.toJson(mapRoot);
    }
}