package com.jerryboot.springbootdemo.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter{

	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
		 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		setCORS(response);
		String header = request.getHeader("Authorization");
		System.out.println("JWTAuth Header: "+header); 
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		System.out.println("After authentication: " + authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("After setAuthentication");
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		chain.doFilter(request, response);
		 
	}
		 
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			System.out.println("Token != null : "+token);
			// parse the token.
			String user = Jwts.parser()
							.setSigningKey("MyJwtSecret")
							.parseClaimsJws(token.replace("Bearer ", ""))
							.getBody()
							.getSubject();
			if (user != null) {
				System.out.println("user!= null"+user.toString());
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}else{
				System.out.println("JWTAuthenticationFilter:未找到該使用者");
			}
			
		  return null;
		  
		}
		
		return null;
	
	} 
	
	
	
	private void setCORS(HttpServletResponse res) {
		if(res.getHeader("Access-Control-Allow-Origin")==null) {
			System.out.println("未設定CORS，加入設定");
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.addHeader("Access-Control-Allow-Methods", "*");
			res.addHeader("Access-Control-Allow-Headers", "*");
			res.addHeader("Access-Control-Allow-Max-Age", "1728000");
		}
	}
	
}
