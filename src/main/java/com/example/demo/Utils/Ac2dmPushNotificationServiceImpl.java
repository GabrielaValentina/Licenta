package com.example.demo.Utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpClient;
/*
@Service
@Qualifier("Ac2dm")
public class Ac2dmPushNotificationServiceImpl { //implements PushNotificationService {
    private String sendingRoleAccount;
    private String sendingRolePassword;

    public void pushNotification(Notification notification) {
        List<Notification> notList = new ArrayList<Notification<();
        notList.add(notification);
        pushNotifications(notList);
    }

    public void pushNotifications(List<Notification> notifications) {
        try {
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(
                    "https://www.google.com/accounts/ClientLogin");
            method.addParameter("Email", sendingRoleAccount);
            method.addParameter("Passwd", sendingRolePassword);
            method.addParameter("accountType", "HOSTED_OR_GOOGLE");
            method.addParameter("source", "unit-test");
            method.addParameter("service", "ac2dm");
            client.executeMethod(method);
            byte[] responseBody = method.getResponseBody();
            String response = new String(responseBody);
            String auth = response.split("\n")[2];
            String token = auth.split("=")[1];
            for (Notification notification : notifications) {
                doSendNotification(notification, token, client);
            }
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private void doSendNotification(Notification notification, String token, HttpClient client) throws HttpException, IOException {
        PostMethod method = new PostMethod("https://android.apis.google.com/c2dm/send");
        method.addParameter("registration_id", notification.getDeviceToken());
        method.addParameter("collapse_key", "collapse");
        method.addParameter("data.payload", "mesaj");//notification.getBadge().toString());
        Header header = new Header("Authorization", "GoogleLogin auth=" + token);
        method.addRequestHeader(header);
        client.executeMethod(method);
        byte[] responseBody = method.getResponseBody();
        String response = new String(responseBody);
    }

    public void setSendingRoleAccount(String sendingRoleAccount) {
        this.sendingRoleAccount = sendingRoleAccount;
    }

    public void setSendingRolePassword(String sendingRolePassword) {
        this.sendingRolePassword = sendingRolePassword;
    }

*/