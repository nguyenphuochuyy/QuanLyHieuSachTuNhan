package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatHang;

public class ChiTietPhieuDatHang_Dao {
	public List<ChiTietPhieuDatHang> getAllChiTietPhieuDatHang(String maPhieuDat){
		List<ChiTietPhieuDatHang> dsChiTietPhieuDatHang = new ArrayList<ChiTietPhieuDatHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietPhieuDatHang where MaPhieuDatHang like '"+maPhieuDat+"'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsChiTietPhieuDatHang.add(new ChiTietPhieuDatHang(rs.getString(1) , rs.getString(2),rs.getString(4),rs.getDouble(5) , rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietPhieuDatHang;
	}
	
	public void themChiTietPhieuDatHang(ChiTietPhieuDatHang ctpdh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into ChiTietPhieuDatHang values(?,?,?,?,?)");
			stmt.setString(1, ctpdh.getMaPhieuDatHang());
			stmt.setString(2, ctpdh.getMaSanPham());
			stmt.setInt(3, ctpdh.getSoLuong());
			stmt.setString(4, ctpdh.getTenSanPham());
			stmt.setDouble(5, ctpdh.getGiaBan());

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
