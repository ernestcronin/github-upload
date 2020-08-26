package recipes.app.security.fllters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import recipes.app.security.CustomUserDetailsService;
import recipes.app.security.util.JWTUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String userName = null;
		String jwt = null;
		
		if(!StringUtils.isAllEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(jwt);
		}
		
		if(!StringUtils.isAllEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				userPasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);					
			}
		}
		filterChain.doFilter(request, response);
	}

}
