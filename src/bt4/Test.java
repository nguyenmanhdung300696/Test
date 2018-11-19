package bt4;

import java.sql.SQLException;

import connectDB.AppConnectionDB;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppConnectionDB appConnectionDB= new AppConnectionDB();
		appConnectionDB.show();
	}

}
