package leetcode.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test2  {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder BCryptEncoder = new BCryptPasswordEncoder();
		//這是預設要當作登入頁面輸入的密碼
		String password = "0000";
		//這是已經用0000並用BCryptPasswordEncoder加密後儲存進資料庫的密碼
		String encodedPassword = new String("$2a$10$IkHGOZgww.en./ntc16fuOlRxeme2qJiU8EhbfXeZ9LBD/9DCp5Fq");
		//使用BCryptPasswordEncoder再度把0000加密
		String encode0000 = BCryptEncoder.encode(password);
		//比對兩者是否相等 結果為false
			
		System.out.println(": "+BCryptEncoder.matches(password, encodedPassword));
		

		
		
		
		
		
		
		
		
	     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String password2 =  "0000";

	        String encodedPassword2 = "$2a$10$zGFA1g85yJ8BFUTXUmhkvemvH2D0uuwjIISpMJrNaaLzzouukZnB2";
	    System.out.println("pekora可愛嗎??? : "+encoder.matches(password2,encodedPassword2));
		
		
		
	}





	

	
	

	
	

}
