package com.maxwell;

import com.maxwell.data.Results;
import exceptions.InvalidHeaderException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import static com.maxwell.EntryPoint.getResults;

@RestController
public class MyRestController {

    /*
    * when accessing address/resultstream, passes in the model config file
    * and returns a stream of the results object in JSon format
    */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/resultstream", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getResultStream(@RequestHeader Map<String, String> headers, @RequestBody String config, HttpServletResponse response) {
        String contentType = headers.get("content-type");
        if (!("application/json".equals(contentType))) {
            throw new InvalidHeaderException();
        }
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EntryPoint.getResultStream(config, outputStream);
    }

    /*
     * when accessing address/resultstream, passes in the model config file
     * and returns a stream of the results object in JSon format
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/result", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Results getResult(@RequestHeader Map<String, String> headers, @RequestBody String config, HttpServletResponse response) {
        String contentType = headers.get("content-type");
        if (!("application/json".equals(contentType))) {
            throw new InvalidHeaderException();
        }
        return getResults(config);
    }
}
