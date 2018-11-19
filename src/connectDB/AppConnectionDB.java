package connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class AppConnectionDB {
	private Connection mConnection;
	private Statement mStatement;
	private PreparedStatement mPreparedStatement = null;
	List<Integer> list;
	
	// Hàm này sẽ khởi tao connect vào db của e
	public AppConnectionDB() throws ClassNotFoundException, SQLException {
		mConnection = SqlConnectionDb.getMySQLConnection();
		list= new ArrayList<>();
		if(mConnection!=null) {
			try {
				mStatement=  mConnection.createStatement();
				System.out.println("connected");
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// cái này đê get source từ db. cần get gi thì viết câu query tương ướng thui
	public List<Integer> getSource() throws SQLException {
		String query="Select * from demo";
		ResultSet rs = null;
		try {
			 rs= mStatement.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void  InsertInformation() {
		String account, firstname, lastname,email;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Nhap account: ");
		account = scanner.nextLine();
		System.out.println("Nhap ho cua ban: ");
		firstname = scanner.nextLine();
		System.out.println("Nhap ten cua ban: ");
		lastname = scanner.nextLine();
		System.out.println("Nhap email: ");
		email = scanner.nextLine();
		
		String sql = " insert into demo (account, firstname, lastname, email) value(?, ?, ?, ?)";

		try {
			mPreparedStatement = mConnection.prepareStatement(sql);
			mPreparedStatement.setString(1, account);
			mPreparedStatement.setString(2, firstname);
			mPreparedStatement.setString(3, lastname);
			mPreparedStatement.setString(4, email);
			
			int kt = mPreparedStatement.executeUpdate();
			if(kt!=0)
			{
				System.out.println("Them thanh cong !");
			}
			else {
				System.out.println("Them khong thanh cong !");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Loi: " + e.getMessage());
		}
	}
	public void show() {
		String sql = "select * from demo" ;
		
		try {
			mPreparedStatement = mConnection.prepareStatement(sql);
			
			ResultSet rs = mPreparedStatement.executeQuery();
			System.out.println("Account  Firstname    Lastname     Email");
			while(rs.next())
			{
				String account = rs.getString("account");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				
				System.out.println(account + "        "+firstname + "      "+lastname + "      "+email);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Loi: "+ e.getMessage());
		}
		
	}
	public void DeleteInformation() {
		String account;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhap Account can xoa: ");
		account = scanner.nextLine();
		
		String sql = "delete from demo where account = ?" ;
		
		 try {
			mPreparedStatement = mConnection.prepareStatement(sql);
			mPreparedStatement.setString(1,account);
			
			int kt = mPreparedStatement.executeUpdate();
			if(kt !=0){
				System.out.println("Xoa thanh cong !");
			}else {
				System.out.println("Xoa khong thanh cong !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Loi: "+ e.getMessage());
		}
		
	}
	
	/*
	public void show() throws SQLException {
		list= getSource();
		for (Integer integer : list) {
			System.out.println("rs: "+ integer);
		}
	}
	*/
}
