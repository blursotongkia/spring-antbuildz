package com.smu.antisocial.Listing;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Integer> {

    @Query("SELECT l FROM Listing l WHERE l.partnerid = ?1")
    List<Listing> getListingByPartnerId(Integer partnerid);

    @Query("SELECT l FROM Listing l WHERE l.partnerid = ?1 and l.equipmentid = ?2")
    List<Listing> getListingbyPartnerIdEquipmentId(Integer partnerid, Integer equipmentid);

    @Query("SELECT l FROM Listing l WHERE l.listingid = ?1")
    Listing findPartnerbyListingID(Integer listingid);

    
}
