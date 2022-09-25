package com.insuranceapp.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.insuranceapp.service.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
		// this class is used for checking that request have a validate token or not
		
		@Autowired
		private CustomUserDetailsService userDetailsService;	//to get user detatils
		
		@Autowired
		private JwtUtil jwtUtil;	// do things about token like generate token, validate token etc.
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			final String requestTokenHeader = request.getHeader("Authorization");
			System.out.println(requestTokenHeader);
			String username  = null;
			String jwtToken = null;
			if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				//yes
				jwtToken=requestTokenHeader.substring(7);
				try {
				// this will give username 
				username = this.jwtUtil.extractUsername(jwtToken);
				}
				catch(ExpiredJwtException e) {
					e.printStackTrace();
					System.out.println("jwt token has expired");
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("error");
				}
			}else {
				System.out.println("Invalid token, not starts with bearer string");
			}
			
			//once we get the token we will now validate the token
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				
				if(this.jwtUtil.isValidToken(jwtToken, userDetails)) {
					//when token is valid
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
					= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			else {
				System.out.println("Token is not valid");
			}
			filterChain.doFilter(request, response);
		
	}

}
