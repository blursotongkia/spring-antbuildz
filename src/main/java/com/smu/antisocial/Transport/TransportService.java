package com.smu.antisocial.Transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransportService {
    
  @Autowired
  private final TransportRepository transportRepository;

    public List<Transport> getAllTransports(){
        return transportRepository.findAll();
	}

    public Transport getTransport(Integer transportid){
        return transportRepository.findById(transportid).orElseThrow(
            () -> new IllegalStateException("Transport with ID " + transportid + " does not exist"));
    }

    public List<Transport> getTransportsByCapacity(Integer capacity){
        List<Transport> transportOptional = transportRepository.findTransportsByCapacity(capacity);
        if(transportOptional.isEmpty()){
            throw new IllegalStateException("No transport with the capacity of " + capacity + " or more exist");
        }
        return transportOptional;
    }

    public Transport createTransport(Transport transport){
		return transportRepository.save(transport);
	}

    public void deleteTransport(Integer transportid){
        boolean exists = transportRepository.existsById(transportid);
		if(!exists){
			throw new IllegalStateException("Transport with ID " + transportid + " does not exist" );
		}
		transportRepository.deleteById(transportid);
	}
}
