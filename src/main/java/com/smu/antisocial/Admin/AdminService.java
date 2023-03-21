package com.smu.antisocial.Admin;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.smu.antisocial.Partner.PartnerRepository;
import com.smu.antisocial.Rental.Rental;
import com.smu.antisocial.Rental.RentalRepository;
import com.smu.antisocial.Partner.*;
import com.smu.antisocial.User.User;
import com.smu.antisocial.User.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {
    @Autowired
    private final AdminRepository adminRepository;

	@Autowired
    private final PartnerRepository partnerRepository;

	@Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RentalRepository rentalRepository;

    public List<Admin> getAdmins(){
        return adminRepository.findAll();
	}

    public Admin getAdmin(Integer adminid){
		return adminRepository.findById(adminid).orElseThrow(
            () -> new IllegalStateException("Admin with ID" + adminid + " does not exist"));
	}

    public Admin getAdminByUsername(String username){
		return adminRepository.findAdminbyUsername(username).orElseThrow(
            () -> new IllegalStateException("username does not exist"));
	}

    public Admin createAdmin(Admin admin){
		return adminRepository.save(admin);
	}

    public void deleteAdmin(Integer adminid){
        boolean exists = adminRepository.existsById(adminid);
		if(!exists){
			throw new IllegalStateException("Admin with ID " + adminid + " does not exist" );
		}
		adminRepository.deleteById(adminid);
	}

	public Admin verifyPassword(String username, String password){
		Admin admin = this.getAdminByUsername(username);
		String checkPassword = admin.getPassword();

		if (password.equals(checkPassword)){
			return admin;
		}
		else {
			throw new IllegalStateException("Invalid Password");
		}
	}

	public Partner approvePartner(Integer userid){
        userRepository.findById(userid).orElseThrow(() -> new IllegalStateException("User with ID " + userid + " does not exist"));

		Optional<Partner> partnerOptional = partnerRepository.findPartnerbyUserId(userid);

		if(partnerOptional.isPresent()){
			throw new IllegalStateException("This user is already a partner");
		}

        Partner partner = new Partner();
        partner.setUserid(userid);

        User user = userRepository.getOne(userid);
        user.setPartnerRequest(false);

		return partnerRepository.save(partner);
	}

    @Transactional
    public void approvePayment(Integer rentalID){

        rentalRepository.findById(rentalID).orElseThrow(() -> new IllegalStateException("Rental with ID " + rentalID + " does not exist"));

        Rental rental = rentalRepository.getOne(rentalID);
        rental.setStatus("Paid");
	}
}
