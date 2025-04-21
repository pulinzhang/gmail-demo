package com.pubing.gmaildemo.controller;

import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.pubing.gmaildemo.config.GoogleOAuthProperties;
import com.pubing.gmaildemo.service.GmailOAuthService;
import com.pubing.gmaildemo.service.GmailService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GmailController {
    @Resource
    GmailService gmailService;
    @Autowired
    GmailOAuthService gmailOAuthService;
    @Autowired
    GoogleOAuthProperties oauthProperties;
    @GetMapping("/getMyMailList")
    public List<String> getMyMailList(){
       return gmailService.getMyMailList();
    }

    @GetMapping("/auth/gmail")
    public String initiateAuth() throws Exception {

        GoogleAuthorizationCodeFlow flow = gmailOAuthService.buildFlow();

        String url = flow.newAuthorizationUrl()
                .setRedirectUri(oauthProperties.getRedirectUris().get(0))
                .build();
        return "redirect:" + url;
    }

    @GetMapping("/login/oauth2/code/google")
    public String handleCallback(@RequestParam String code) throws Exception {
        GoogleAuthorizationCodeFlow flow = gmailOAuthService.buildFlow();
        GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(code)
                .setRedirectUri(flow.newAuthorizationUrl()
                        .setRedirectUri(oauthProperties.getRedirectUris().get(0))
                        .build());
        GoogleTokenResponse tokenResponse = tokenRequest.execute();

        // 保存tokenResponse.getRefreshToken()到数据库或缓存
        return "OAuth成功！刷新令牌已保存。";
    }

}
