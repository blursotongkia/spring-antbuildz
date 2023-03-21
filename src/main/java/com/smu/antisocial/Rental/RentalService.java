package com.smu.antisocial.Rental;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.smu.antisocial.Request.Request;
import com.smu.antisocial.Request.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalService {
    @Autowired
    private final RentalRepository rentalRepository;

    @Autowired
    private final RequestRepository requestRepository;

    public List<Rental> getRentals(){
        return rentalRepository.findAll();
	}

    public Rental getRentalByRequestId(Integer requestid){
        return rentalRepository.getRentalByRequestId(requestid);
    }

    public Rental createRental(Rental rental){
        rental.setCreationDate(java.time.LocalDateTime.now());
		return rentalRepository.save(rental);
	}

    public List<Rental> getRentalsByUserID(Integer userid){
        List<Request> requestList = requestRepository.getRequestByUserId(userid);
        ArrayList<Rental> rentalList = new ArrayList<Rental>();
        for(Request request : requestList){
            Rental r = this.getRentalByRequestId(request.getRequestid());
            rentalList.add(r);
        }
        return rentalList;
	}

    public void deleteRental(Integer rentalid){
        boolean exists = rentalRepository.existsById(rentalid);
		if(!exists){
			throw new IllegalStateException("Rental with ID " + rentalid + " does not exist" );
		}
		rentalRepository.deleteById(rentalid);
	}

    @Transactional
    public void updateRentalStatus(Integer rentalID, String status){
        boolean exists = rentalRepository.existsById(rentalID);
		if(!exists){
			throw new IllegalStateException("Rental with ID " + rentalID + " does not exist" );
		}
        Rental rental = rentalRepository.getOne(rentalID);
        rental.setStatus(status);
    }

    @Transactional
    public void updateUserStatus(Integer rentalID, String status){
        boolean exists = rentalRepository.existsById(rentalID);
		if(!exists){
			throw new IllegalStateException("Rental with ID " + rentalID + " does not exist" );
		}
        Rental rental = rentalRepository.getOne(rentalID);
        rental.setUserStatus(status);
    }

    @Transactional
    public void updatePartnerStatus(Integer rentalID, String status){
        boolean exists = rentalRepository.existsById(rentalID);
		if(!exists){
			throw new IllegalStateException("Rental with ID " + rentalID + " does not exist" );
		}
        Rental rental = rentalRepository.getOne(rentalID);
        rental.setPartnerStatus(status);
    }
}
