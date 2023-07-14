package com.jerryboot.springbootdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//繼承 WebSecurityConfigurerAdapter才可自訂登入邏輯
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService service;
	

	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {


			
		try {
			//不需要被認證的頁面
		http.authorizeHttpRequests()
//			.antMatchers("/","/Backstage/checkAccount","/addForm",
//					"/Agency/selectAgencyData","/Backstage/downloadImageAgency/**",
//					"/Backstage/downloadImageRealAssistiveDevice/**","/goAddForm",
//					"/Auxiliary/**","/Agency/**","/ProducePage/*",
//					"/Backstage/downloadImageCommodities/*","/Commodities","/messageNavbar",
//					"/aboutimedical","/ShoppingCart","/frontMemberLogin","/gosubsidy","/subsidy",
//					"/agencyData","/assistiveDevice","/relatedLink","/getNewsForIndexPage",
//					"/newsInfo","/newsInfoPage","/login","/Backstage/logout",
//					"/Backstage/adminLogin","/Backstage/login","/Backstage/loginSuccess",
//					"/Backstage/loginFail","/goEnroll","/enroll","/Employee/PDF",
//					"Employee/Excel").permitAll()
//			.antMatchers(HttpMethod.POST,"/Backstage/logout").permitAll()
//			//前面指定的url必須要有後面的角色才可以訪問
//			.antMatchers(HttpMethod.GET,"/Backstage/").authenticated()
//			.antMatchers(HttpMethod.POST,"/Backstage/").authenticated()
//			.antMatchers(HttpMethod.GET,"/Backstage/**").hasAnyAuthority("ROLE_manager")
//			.antMatchers(HttpMethod.POST,"/Backstage/**").hasAnyAuthority("ROLE_manager")
//			//剩下的全都必須要通過驗證
//			.anyRequest().authenticated()
//			.and()
//			.formLogin()
//			.loginPage("/Backstage/adminLogin")
//			.loginProcessingUrl("/Backstage/login")
//			.successForwardUrl("/Backstage/loginSuccess")
//			.failureForwardUrl("/Backstage/adminLogin?error=true")
//			.and()
//			.logout()
//			.invalidateHttpSession(true)
//			.clearAuthentication(true)
//			.deleteCookies("JSESSIONID")
//			.logoutUrl("/Backstage/logout")
//			
//			.logoutSuccessUrl("/Backstage/adminLogin?logout=true")
//			
//			.and()
//			//.csrf().disable()
//			.exceptionHandling()
//			
//			
//			.and()
//			.rememberMe()
//			.userDetailsService(service)
//			.tokenValiditySeconds(60*60*24*7)
			
			//關閉Security時打開這兩行註解 註解上方
			.anyRequest().permitAll()
			.and().cors().disable()
			.csrf().disable()
			;
		
			
				
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		
		//登出	
//		http.logout()
//			.deleteCookies("JSESSIONID")
//			.logoutSuccessUrl("/Backstage/adminLogin")
//			
//			//可以使用任何方法登出(不建議
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		
//		這邊使用 authorizeRequests 方法開始自訂授權規則。
//		使用 antMatchers 方法，傳入 HTTP 請求方法與 API 路徑，
//		後面接著授權方式，這樣就定義好一個規則了。
//		傳入的 API 路徑可利用「萬用字元」，以同時適用多個路徑，
//		以下是萬用字元的用法。
//
//		「*」：代表0到多個字元。如「/products/*」適用於「/products」、「/products/123」，但不適用「/products/123/draft」
//		「**」：代表0到多個路徑。如「/products/**」適用於「/products」底下任何路徑。
//		「?」：代表一個字元。如「/products/?*」適用於「/products/1」、「/products/123」，但不適用「/products」。
		
		
	}
	
	
	
	
	
	

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/css/**/**","/images/**/**","/js/**","/default/**","/layout/**","/img/**","/Message/**");
	}







	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		try {
			auth.userDetailsService(service)
			.passwordEncoder(PasswordEncoder());
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}




	//必須建立此方法將密碼加密 建立密碼演算的instance
	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
