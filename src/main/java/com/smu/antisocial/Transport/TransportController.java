package com.smu.antisocial.Transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/transport")
public class TransportController {
    
    @Autowired
    private final TransportService transportService;

    @GetMapping
	public List<Transport> getAllTransports(){
        return transportService.getAllTransports();
	}

    @GetMapping(path="transportid/{transportid}")
    public Transport getTransport(@PathVariable("transportid") Integer transportid){
        return transportService.getTransport(transportid);
    }

    @GetMapping(path="capacity/{capacity}")
    public List<Transport> getTransportsByCapacity(@PathVariable("capacity") Integer capacity){
        return transportService.getTransportsByCapacity(capacity);
    }

    @PostMapping
    public Transport createTransport(@RequestBody Transport transport){
        return transportService.createTransport(transport);
    }

    @DeleteMapping(path="{transportid}")
    public void deleteTransport(@PathVariable("transportid") Integer transportid){
        transportService.deleteTransport(transportid);
    }

}
