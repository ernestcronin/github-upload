package recipes.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import recipes.app.dto.JwtDto;
import recipes.app.dto.UserDto;
import recipes.app.model.User;
import recipes.app.security.CustomUserDetailsService;
import recipes.app.security.util.JWTUtil;
import recipes.app.service.IUserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping("/retrieveUser/{userName}")
	public ResponseEntity<User> getUser(@PathVariable String userName){
		User user = userService.retrieveUser(userName);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtDto> createUserAuthenticationToken(@RequestBody UserDto userDto) throws Exception{
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
//		}catch(BadCredentialsException e) {
//			throw new Exception("Incorrect username and password.", e);
//		}
		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userDto.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<JwtDto>(new JwtDto(jwt), HttpStatus.OK);
	}
}
