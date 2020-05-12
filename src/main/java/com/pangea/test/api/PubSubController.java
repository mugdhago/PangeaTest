package com.pangea.test.api;

import com.pangea.test.service.PublishSubscribeService;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
public class PubSubController {
    private final PublishSubscribeService service;

    @Autowired
    public PubSubController(PublishSubscribeService service) {
        this.service = service;
    }

    @RequestMapping("/publish/{topic}")
    @PostMapping(path = "{topic}")
    public List<String> publish(@NotNull @PathVariable("topic")String t, @NotNull @Valid @RequestBody String json) throws Exception {
        JSONObject data=new JSONObject(json);
        return service.publish(t,data);
    }

    @RequestMapping("/subscribe/{topic}")
    @PostMapping(path = "{topic}")
    public HttpStatus subscribe(@NotNull @PathVariable("topic")String t,@NotNull @Valid @RequestBody String json) throws IOException, JSONException {
        JSONObject data=new JSONObject(json);
        return service.subscribe(t,data);
    }

    @RequestMapping("/event")
    @PostMapping
    public String getEvents(@NotNull @Valid @RequestBody String json) throws ParseException {
        return service.getEvents(json);
    }
}
