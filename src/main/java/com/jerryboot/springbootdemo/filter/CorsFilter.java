//package com.jerryboot.springbootdemo.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CorsFilter implements Filter{
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		System.out.println("經過CorsFilter");
//		if(response instanceof HttpServletResponse) {
//			HttpServletResponse altResponse = (HttpServletResponse)response;
//			HttpServletRequest altRequest = (HttpServletRequest)request;
//			addCORSHeader(altRequest,altResponse);
//			System.out.println("--------------------------------------");
//		}
//		chain.doFilter(request, response);
//		
//	}
//
//	private void addCORSHeader(HttpServletRequest request,HttpServletResponse response) {
//		if(response.getHeader("Access-Control-Allow-Origin")==null) {
//			System.out.println("加入CORS Header");
//			response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
//			response.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
//			response.addHeader("Access-Control-Allow-Headers", "Authorization");
//			response.setHeader("Access-Control-Allow-Credentials", "true");
//			response.addHeader("Access-Control-Allow-Max-Age", "1728000");
//			response.addHeader("Access-Control-Expose-Headers", "Access-Control-Request-Headers");
//			response.addHeader("Access-Control-Expose-Headers", "Authorization");
//			response.addHeader("Access-Control-Expose-Headers", "authorization");
//			System.out.println("Filter_ Auth: "+request.getHeader("Authorization"));
//			System.out.println("Filter_ Method: "+request.getMethod());
//			System.out.println("Filter_ uri: "+request.getRequestURI());
//			System.out.println("當前status code: "+ response.getStatus());
//		}
//		
//	}
//
//}
