package inhatc.group.mojuk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//참조 : https://koogood.tistory.com/23
public class TestDBConnection {
	
	private static final String driver = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
	private static final String url = "jdbc:log4jdbc:mariadb://localhost:3306/testspace?serverTimezone=Asia/Seoul&useSSL=false&characterEncoding=utf-8";
	private static final String user = "root";
	private static final String password = "1234";
	
    public void testConnection() {
    	try {
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, user, password);

	    	int res = 0;
	    	String query = "SELECT COUNT(1) AS CNT FROM TESTSPACE";

    		PreparedStatement pstmt = con.prepareStatement(query);
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			res = rs.getInt(1);
    		}
    		
    		System.out.println(res > 0 ? "success" : "fail");
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}