package com.smu.antisocial.Rental;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/rental")
@AllArgsConstructor
public class RentalController {
    @Autowired
    private final RentalService rentalService;

    @GetMapping
	public List<Rental> getRentals(){
        return rentalService.getRentals();
	}

    @PostMapping
    public Rental createRental(@RequestBody Rental rental){
        return rentalService.createRental(rental);
    }

    @DeleteMapping(path="{requestid}")
    public void deleteRental(@PathVariable("requestid") Integer rentalid){
        rentalService.deleteRental(rentalid);
    }

    @GetMapping(path="/request/{requestid}")
    public Rental getRentalByRequestId(@PathVariable("requestid") Integer requestid){
        return rentalService.getRentalByRequestId(requestid);
    }

    @GetMapping(path="/get/user/{userid}")
    public List<Rental> getRentalsByUserID(@PathVariable("userid") Integer userid){
        return rentalService.getRentalsByUserID(userid);
    }

    @PutMapping(path="{rentalid}")
    public void updateRentalStatus(@PathVariable("rentalid") Integer rentalid, @RequestParam String status){
        try {
            rentalService.updateRentalStatus(rentalid, status);
        } catch (IllegalStateException e){
            throw new IllegalStateException("An error occurred while updating the rental status");
        }
    }

    @PutMapping(path="/user-status/{rentalid}")
    public void updateUserStatus(@PathVariable("rentalid") Integer rentalid, @RequestParam String status){
        try {
            rentalService.updateUserStatus(rentalid, status);
        } catch (IllegalStateException e){
            throw new IllegalStateException("An error occurred while updating the rental status");
        }
    }

    @PutMapping(path="/partner-status/{rentalid}")
    public void updatePartnerStatus(@PathVariable("rentalid") Integer rentalid, @RequestParam String status){
        try {
            rentalService.updatePartnerStatus(rentalid, status);
        } catch (IllegalStateException e){
            throw new IllegalStateException("An error occurred while updating the rental status");
        }
    }
}
