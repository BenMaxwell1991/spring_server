package com.maxwell;

import exceptions.InvalidHeaderException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Stream;

@RestController
public class MyRestController {

    @RequestMapping(value = "/resultstring", method = RequestMethod.POST)
    public String getResultString(@RequestHeader Map<String, String> headers, @RequestBody String config) {
        String contentType = headers.get("content-type");
        if (!("application/json".equals(contentType))) {
            throw new InvalidHeaderException();
        }
        String output = EntryPoint.getResultString(config);
        return "results: " + output;
    }

    @RequestMapping(value = "/resultstream", method = RequestMethod.POST)
    public Stream<String> getResultStream(@RequestHeader Map<String, String> headers, @RequestBody String config) {
        String contentType = headers.get("content-type");
        if (!("application/json".equals(contentType))) {
            throw new InvalidHeaderException();
        }
        return EntryPoint.getResultStream(config);
    }
}
