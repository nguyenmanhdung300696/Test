package connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class AppConnectionDB {
	private Connection mConnection;
	private Statement mStatement;
	List<Integer> list;
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
	public void show() throws SQLException {
		list= getSource();
		for (Integer integer : list) {
			System.out.println("rs: "+ integer);
		}
	}
	
}
