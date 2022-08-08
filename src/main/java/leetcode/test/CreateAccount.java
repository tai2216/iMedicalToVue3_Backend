package leetcode.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreateAccount {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String password = new String("0000");
		
		String encoded = bCryptPasswordEncoder.encode(password);
		System.out.println(encoded);
		
	}

}
