package com.smu.antisocial.Admin;

import java.util.List;
import com.smu.antisocial.Partner.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/admin")
@AllArgsConstructor
public class AdminController {
    @Autowired
    private final AdminService adminService;

    @GetMapping
	public List<Admin> getAdmins(){
        return adminService.getAdmins();
	}

    @GetMapping(path="{adminid}")
    public Admin getAdmin(@PathVariable("adminid") Integer adminid){
        return adminService.getAdmin(adminid);
    }

    @PostMapping
    public void createAdmin(@RequestBody Admin admin){
        adminService.createAdmin(admin);
    }

    @DeleteMapping(path="{adminid}")
    public void deleteAdmin(@PathVariable("adminid") Integer adminid){
        adminService.deleteAdmin(adminid);
    }

    @GetMapping(path="/admin/{admin}")
    public Admin verifyPassword(@PathVariable("admin") String username, @RequestParam(required = true) String password){
        return adminService.verifyPassword(username, password);
    }

    @PostMapping(path="/approvepartner")
    public Partner approvePartner(@RequestParam Integer userid){
        return adminService.approvePartner(userid);
    }

    @PutMapping(path="/payment")
    public void approvePayment(@RequestParam Integer rentalID){
        adminService.approvePayment(rentalID);
    }    
}
