package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuNhapHang;

public class ChiTietPhieuNhapHang_Dao {
	public List<ChiTietPhieuNhapHang> getAllChiTietPhieuNhapHang(String maPhieuNhap){
		List<ChiTietPhieuNhapHang> dsChiTietPhieuNhapHang = new ArrayList<ChiTietPhieuNhapHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietPhieuNhapHang where MaPhieuNhapHang like '"+maPhieuNhap+"'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsChiTietPhieuNhapHang.add(new ChiTietPhieuNhapHang(rs.getString(1) , rs.getString(2), rs.getInt(3) , rs.getDouble(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietPhieuNhapHang;
	}
	
	public void themChiTietPhieuNhapHang(ChiTietPhieuNhapHang ctpnh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into ChiTietPhieuNhapHang values(?,?,?,?)");
			stmt.setString(1, ctpnh.getMaPhieuNhap());
			stmt.setString(2, ctpnh.getMaSanPham());
			stmt.setInt(3, ctpnh.getSoLuong());
			stmt.setDouble(4, ctpnh.getGiaNhap());
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
