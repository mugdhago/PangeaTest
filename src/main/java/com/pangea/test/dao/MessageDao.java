package com.pangea.test.dao;


import com.pangea.test.model.Subscriber;
import net.minidev.json.JSONValue;

import org.apache.tomcat.util.json.ParseException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Repository("PubSubDao")
public class MessageDao {
    private static HashMap<String, List<Subscriber>> topics = new HashMap<>();

    public List<JSONObject> publish(String t, JSONObject data) throws Exception {
        List<JSONObject> response = new ArrayList<>();
        if (topics.get(t) != null) {
            List<Subscriber> subscribers = topics.get(t);
            for (Subscriber s : subscribers) {
               response.add( s.receivedMessage(t, data));

            }
            return response;
        }else {
            throw new Exception("Topic Name does not exist.First Subscribe to a topic.");
        }
    }

    public HttpStatus subscribe(String t,JSONObject data) throws JSONException {
         Subscriber s= new Subscriber(UUID.randomUUID(),t,URI.create((String) data.get("url")));
         if(topics.containsKey(t)) {
             topics.get(t).add(s);
        }else{
             // There is an assumption here that the topic will be created the first time it is subscribed to.
             List<Subscriber> subs = new ArrayList<>();
             subs.add(s);
             topics.put(t,subs);
         }
        return HttpStatus.OK;
    }

    public String getEvents(String data) throws JSONException {
        return data;
    }
}
