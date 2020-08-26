package recipes.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import recipes.app.model.User;
import recipes.app.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		User user = userRepository.findByName(username);
		CustomUser customUser = new CustomUser(user);
		return customUser;
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode("pass");
		//return new User("user", "$2a$10$O1vzALoXubYVyXhL2WTm1OLLrkgxLtIvfTqKDa3Yzl3VZ.WfDjWVS", new ArrayList<>() );
	}

}
