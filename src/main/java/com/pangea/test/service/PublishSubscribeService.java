package com.pangea.test.service;

import com.pangea.test.dao.MessageDao;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PublishSubscribeService {
    private final MessageDao messageDao;

    @Autowired
    public PublishSubscribeService(@Qualifier("PubSubDao")MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<String> publish(String t, JSONObject data) throws Exception {
        return messageDao.publish(t,data);
    }

    public HttpStatus subscribe(String t, JSONObject data) throws IOException, JSONException {
        return messageDao.subscribe(t,data);
    }
    public String getEvents(String data) throws ParseException {
        return messageDao.getEvents(data);
    }

}
