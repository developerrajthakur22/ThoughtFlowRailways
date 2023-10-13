package com.flow.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flow.Entity.User;
import com.flow.Repository.UserRepository;

@Service
public class ManualUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if(userOpt.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		
		User user = userOpt.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + user.getRole());
		authorities.add(auth);
		
		org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
		
		// TODO Auto-generated method stub
		return secUser;
	}

}
