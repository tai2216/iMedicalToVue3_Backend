package test.jaseper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.jerryboot.springbootdemo.dao.EmployeeDao;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Test implements testInterface{
	
	public static void main(String[] args) {
		//String abc = "20230210";
		//System.out.println(abc.substring(0,4)+"/"+abc.substring(4,6)+"/"+abc.substring(6,8));
		DecimalFormat df = new DecimalFormat("#,###.###");
		
		System.out.println("$"+df.format(Double.parseDouble("1000.5")));
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormat decimalFormat = (DecimalFormat) currencyFormat;
        //decimalFormat.applyPattern("¤#,##0.00");
		System.out.println(decimalFormat.format(Double.parseDouble("1000.5")));
	}

	@Override
	public String testInterfaceAndShowSuccess() {
		// TODO Auto-generated method stub
		return "Rock You";
	}

}
