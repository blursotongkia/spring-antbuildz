package com.smu.antisocial.Listing;

import java.util.List;
import java.util.Optional;

import com.smu.antisocial.Equipment.*;

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
@RequestMapping(path = "api/v1/listing")
@AllArgsConstructor
public class ListingController {
    @Autowired
    private final ListingService listingService;

    @GetMapping
	public List<Listing> getListings(){
        return listingService.getListings();
	}

    @PostMapping
    public Listing createListing(@RequestBody Listing listing){
        return listingService.createListing(listing);
    }

    @DeleteMapping(path="{listingid}")
    public void deleteListing(@PathVariable("listingid") Integer listingid){
        listingService.deleteListing(listingid);
    }

    @GetMapping(path="{partnerid}/{equipmentid}")
    public List<Integer> getListingbyPartnerIdEquipmentId(@PathVariable("partnerid") Integer partnerid, @PathVariable("equipmentid") Integer equipmentid){
        return listingService.getListingbyPartnerIdEquipmentId(partnerid, equipmentid);
    }

    @GetMapping(path="{partnerid}")
    public List<Optional<Equipment>> getListingByPartnerId(@PathVariable("partnerid") Integer partnerid){
        return listingService.getListingByPartnerId(partnerid);
    }

    @GetMapping(path="/partner/{listingid}")
    public Integer getPartnerByListingId(@PathVariable("listingid") Integer listingid){
        return listingService.getPartnerIdByListingId(listingid);
    }
}
