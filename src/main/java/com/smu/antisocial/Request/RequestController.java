package com.smu.antisocial.Request;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/request")
@AllArgsConstructor
public class RequestController {
    @Autowired
    private final RequestService requestService;

    @GetMapping
	public List<Request> getRequests(){
        return requestService.getRequests();
	}

    @PostMapping
    public Request createRequest(@RequestBody Request request){
        return requestService.createRequest(request);
    }

    @GetMapping(path="/{userid}")
    public List<Request> getRequestByUserId(@PathVariable("userid") Integer userid){
        return requestService.getRequestByUserId(userid);
    }

    @DeleteMapping(path="{requestid}")
    public void deleteRequest(@PathVariable("requestid") Integer requestID){
        requestService.deleteRequest(requestID);
    }

    @GetMapping(path="/breakdown/{requestid}")
    public String getBreakdown(@PathVariable("requestid") Integer requestID) throws JsonProcessingException {
        Map<String, Object> breakdown = requestService.getBreakdown(requestID);
        ObjectMapper mapper = new ObjectMapper();
        
        //Convert Map to JSON
        String json = mapper.writeValueAsString(breakdown);
             
        return json;
    }
}
