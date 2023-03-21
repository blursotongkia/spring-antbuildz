package com.smu.antisocial.User;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	public List<User> getUsers(){ 
		return userRepository.findAll();
	}

	public User getUser(Integer userid){
		return userRepository.findById(userid).orElseThrow(
			() -> new IllegalStateException("user with ID " + userid + " does not exist"));
	}

	public User getUserByUsername(String username){
		return userRepository.findUserbyUsername(username).orElseThrow(() -> new IllegalStateException("username does not exist"));
	}

	public User createUser(User user){
		Optional<User> emailOptional = userRepository.findUserbyEmail(user.getEmail());
		Optional<User> usernameOptional = userRepository.findUserbyUsername(user.getUsername());

		if(emailOptional.isPresent()){
			throw new IllegalStateException("Email Taken");
		}
		else if(usernameOptional.isPresent()){
			throw new IllegalStateException("Username Taken");
		}

        user.setToken(UUID.randomUUID().toString());

		return userRepository.save(user);
	}

	@Transactional
	public User updateUser(Integer userid, String username, String name, String email){
		User user = userRepository.findById(userid).orElseThrow(
			() -> new IllegalStateException("user with ID " + userid + " does not exist"));

		if (name != null && name.length() > 0){
			user.setName(name);
		}

		if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)){
			Optional<User> userOptional = userRepository.findUserbyEmail(email);
			if (userOptional.isPresent()){
				throw new IllegalStateException("Email Taken");
			}
			user.setEmail(email);
		}

		if (username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username)){
			user.setUsername(username);
		}

        return user;
	}

    @Transactional
    public void verifyUser(Integer userid, String token){
        
        User user = userRepository.findById(userid).orElseThrow(
			() -> new IllegalStateException("user with ID " + userid + " does not exist"));

        if (token != null && token.length() > 0 && token.equals(user.getToken())){
            user.setActivated(true);
        }
        else{
            throw new IllegalStateException("token is invalid");
        }
    }

	@Transactional
    public boolean requestPartnership(Integer userid){
        
        User user = userRepository.findById(userid).orElseThrow(
			() -> new IllegalStateException("user with ID " + userid + " does not exist"));
		
		user.setPartnerRequest(true);
		return true;
		
    }

	public void deleteUser(Integer userid){
		boolean exists = userRepository.existsById(userid);
		if(!exists){
			throw new IllegalStateException("User with ID " + userid + " does not exist" );
		}
		userRepository.deleteById(userid);
	}

	public User verifyPassword(String username, String password){
		User user = this.getUserByUsername(username);
		String checkPassword = user.getPassword();

		if (password.equals(checkPassword)){
			return user;
		}
		else {
			throw new IllegalStateException("Invalid Password");
		}
	}
}
