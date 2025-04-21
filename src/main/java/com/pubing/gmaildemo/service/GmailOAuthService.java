package com.pubing.gmaildemo.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.pubing.gmaildemo.config.GoogleOAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GmailOAuthService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Autowired
    private GoogleOAuthProperties oauthProperties;

    public GoogleClientSecrets loadClientSecrets() {
        GoogleClientSecrets.Details webDetails = new GoogleClientSecrets.Details();
        webDetails.setClientId(oauthProperties.getClientId());
        webDetails.setClientSecret(oauthProperties.getClientSecret());
        webDetails.setRedirectUris(Collections.singletonList(oauthProperties.getRedirectUris().get(0)));

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
        clientSecrets.setWeb(webDetails);
        return clientSecrets;
    }

    public GoogleAuthorizationCodeFlow buildFlow() throws IOException, GeneralSecurityException {
        GoogleClientSecrets clientSecrets = loadClientSecrets();
        return new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                clientSecrets,
                Collections.singleton("https://www.googleapis.com/auth/gmail.send"))
                .build();
    }

}