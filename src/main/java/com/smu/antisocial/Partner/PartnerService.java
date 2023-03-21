package com.smu.antisocial.Partner;
import java.util.List;
import java.util.Optional;

import com.smu.antisocial.User.User;
import com.smu.antisocial.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PartnerService {

	@Autowired
	private final PartnerRepository partnerRepository;

    @Autowired
	private final UserRepository userRepository;

	public List<Partner> getPartners(){ 
		return partnerRepository.findAll();
	}
    
	public Partner getPartner(Integer partnerid){
		return partnerRepository.findById(partnerid).orElseThrow(
			() -> new IllegalStateException("Partner with " + partnerid + " does not exist"));
	}

	public Partner getPartnerByUserId(Integer userid){
		return partnerRepository.findPartnerbyUserId(userid).orElseThrow(() -> new IllegalStateException("Userid does not exist / Specified userid is not a partner"));
	}

	public Partner createPartner(Partner partner){
        Integer userid = partner.getUserid();
        userRepository.findById(userid).orElseThrow(() -> new IllegalStateException("user with ID " + userid + " does not exist"));

		Optional<Partner> partnerOptional = partnerRepository.findPartnerbyUserId(partner.getUserid());

		if(partnerOptional.isPresent()){
			throw new IllegalStateException("This user is already a partner");
		}

		return partnerRepository.save(partner);
	}

	public void deletePartner(Integer partnerid){
		boolean exists = partnerRepository.existsById(partnerid);
		if(!exists){
			throw new IllegalStateException("Partner with ID " + partnerid + " does not exist" );
		}
		partnerRepository.deleteById(partnerid);
	}

	public String getPartnerEmail(Integer partnerid) {
		Partner partner = partnerRepository.getOne(partnerid);
		Integer userid = partner.getUserid();
		User user = userRepository.findById(userid).orElseThrow(() -> new IllegalStateException("User ID " + userid + "does not exist"));
		return user.getEmail();
	}

}
