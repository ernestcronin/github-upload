package recipes.app.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import recipes.app.model.User;

/*
 * Apply decorative pattern of User class
 */
public class CustomUser implements UserDetails{

	private User user;
	private static final long serialVersionUID = 1L;
	
	public CustomUser(User user) {
		this.user = user;
	}

	@Override //ROLE prefix is required by Spring
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_" + this.user.getRole());
		return Arrays.asList(sga);
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.user.isActive() == 1 ? true:false;
	}

}
