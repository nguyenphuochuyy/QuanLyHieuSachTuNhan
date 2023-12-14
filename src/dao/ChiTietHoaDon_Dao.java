package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.KhachHang;

public class ChiTietHoaDon_Dao {
	public List<ChiTietHoaDon> getAllChiTietHoaDon(String maHoaDon){
		List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietHoaDon where MaHoaDon like '"+maHoaDon+"'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsChiTietHoaDon.add(new ChiTietHoaDon(rs.getString(1) , rs.getString(2),rs.getString(4),rs.getDouble(5) , rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}
	
	public void themChiTietHoaDon(ChiTietHoaDon cthd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?,?)");
			stmt.setString(1, cthd.getMaHoaDon());
			stmt.setString(2, cthd.getMaSanPham());
			stmt.setInt(3, cthd.getSoLuong());
			stmt.setString(4, cthd.getTenSanPham());
			stmt.setDouble(5, cthd.getGiaBan());


			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}			
	}
	
	public void close(PreparedStatement stmt){
		if(stmt != null){
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}
