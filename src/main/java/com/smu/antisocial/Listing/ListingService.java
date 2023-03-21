package com.smu.antisocial.Listing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.smu.antisocial.Equipment.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ListingService {
    @Autowired
    private final ListingRepository listingRepository;

    @Autowired
    private final EquipmentRepository equipmentRepository;

    public List<Listing> getListings(){
        return listingRepository.findAll();
	}

    public Listing createListing(Listing listing){
		return listingRepository.save(listing);
	}

    public void deleteListing(Integer listingid){
		listingRepository.deleteById(listingid);
	}

    public List<Integer> getListingbyPartnerIdEquipmentId(Integer partnerid, Integer equipmentid) {
        List<Listing> lList = listingRepository.getListingbyPartnerIdEquipmentId(partnerid, equipmentid);
        List<Integer> partnerlisting = new ArrayList<>();
        for (Listing listing : lList) {
            partnerlisting.add(listing.getListingid());
        }
        return partnerlisting;
    }

    public Integer getPartnerIdByListingId(Integer listingid){
		Listing listing = listingRepository.findPartnerbyListingID(listingid);
        return listing.getPartnerid();
	}

    public List<Optional<Equipment>> getListingByPartnerId(Integer partnerid){

        List<Optional<Equipment>> eList = new ArrayList<>();
        List<Listing> lList = listingRepository.getListingByPartnerId(partnerid);
        for(Listing listing : lList){
            Optional<Equipment> equipment = equipmentRepository.findById(listing.getEquipmentid());
            if(equipment.isPresent()){
                eList.add(equipment);
            }
        }

        if (lList.size() == 0){
            throw new IllegalStateException("This partner does not have any listings up at the moment");
        }
        return eList;
    }
}
