package com.smu.antisocial.Partner;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/partner")
@AllArgsConstructor
public class PartnerController {

    @Autowired
    private final PartnerService partnerService;

    @GetMapping
	public List<Partner> getPartners(){
        return partnerService.getPartners();
	}

    @GetMapping(path="{partnerid}")
    public Partner getPartner(@PathVariable("partnerid") Integer partnerid){
        return partnerService.getPartner(partnerid);
    }

    @GetMapping(path="/user/{userid}")
    public Partner getPartnerByUserId(@PathVariable("userid") Integer userid) {
        return partnerService.getPartnerByUserId(userid);
    }

    @PostMapping
    public Partner createPartner(@RequestBody Partner partner){
        return partnerService.createPartner(partner);
    }

    @DeleteMapping(path="{partnerid}")
    public void deletePartner(@PathVariable("partnerid") Integer partnerid){
        partnerService.deletePartner(partnerid);
    }

    @GetMapping(path="/email/{partnerid}")
    public String getPartnerEmail(@PathVariable("partnerid") Integer partnerid){
        return partnerService.getPartnerEmail(partnerid);
    }

}
