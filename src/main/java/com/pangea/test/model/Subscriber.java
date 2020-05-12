package com.pangea.test.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

public class Subscriber {
    private final UUID id;
    private final String topic;
    private final URI url;

    public Subscriber(UUID id, String topic, URI url) {
        this.id = id;
        this.topic = topic;
        this.url = url;
    }

    public JSONObject receivedMessage(String topic, JSONObject message) throws IOException, JSONException {
        if (url != null) {
              CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpPost request = new HttpPost(url);
                message.put("topic name",topic);
                request.setEntity(new ByteArrayEntity(message.toString().getBytes("UTF8")));
                // add request headers
                request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

                CloseableHttpResponse response = httpClient.execute(request);
                String retSrc = EntityUtils.toString(response.getEntity());
                // parsing JSON
                 JSONObject result = new JSONObject(retSrc);
                 return result;

        }
        return null;
    }
}