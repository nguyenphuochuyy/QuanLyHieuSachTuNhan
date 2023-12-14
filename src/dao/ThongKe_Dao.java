package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;

public class ThongKe_Dao {
	public ArrayList<HoaDon> getAllHoaDon() {
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		try {
			
			String sql = "select * from HoaDon ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ds.add(new HoaDon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate() ,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9)));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ds;
	}
	// thống kê hóa đơn theo ngày 
	
	

}
