package com.jerryboot.springbootdemo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(response instanceof HttpServletResponse) {
			HttpServletResponse altResponse = (HttpServletResponse)response;
			addCORSHeader(altResponse);
		}
		chain.doFilter(request, response);
		
	}

	private void addCORSHeader(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin,X-Requested-With,Content-Type,Accept");
		response.addHeader("Access-Control-Allow-Max-Age", "1728000");
	}

}
