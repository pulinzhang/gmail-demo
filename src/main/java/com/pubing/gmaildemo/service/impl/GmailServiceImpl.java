package com.pubing.gmaildemo.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.gson.Gson;
import com.pubing.gmaildemo.config.GoogleOAuthProperties;
import com.pubing.gmaildemo.service.GmailOAuthService;
import com.pubing.gmaildemo.service.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class GmailServiceImpl implements GmailService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @Autowired
    private GmailOAuthService gmailOAuthService;

    @Autowired
    private GoogleOAuthProperties oauthProperties;

    @Override
    public List<String> getMyMailList() {


        return null;
    }

    public void sendEmai() throws Exception {
        // 构建 Google 凭据
        GoogleCredentials credentials = GoogleCredentials.fromStream(
                new ByteArrayInputStream(oauthProperties.getClientAuthString().getBytes())
        ).createScoped(Collections.singleton(GmailScopes.GMAIL_SEND));
        HttpCredentialsAdapter credentialsAdapter = new HttpCredentialsAdapter(credentials);
        Gmail gmail = new Gmail.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY, credentialsAdapter
        )
                .setApplicationName("Your App")
                .build();





    }

}
