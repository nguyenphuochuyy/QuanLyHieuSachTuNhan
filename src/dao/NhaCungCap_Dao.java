package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhaCungCap;

public class NhaCungCap_Dao {
	public List<NhaCungCap> getAllNhaCungCap(){
		List<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhaCungCap";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				if(!rs.getString(1).equalsIgnoreCase(" "))
					dsNhaCungCap.add(new NhaCungCap(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNhaCungCap;
	}
	
	public void themNhaCungCap(NhaCungCap ncc) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into NhaCungCap values(?,?,?,?)");
			stmt.setString(1, ncc.getMaNCC());
			stmt.setString(2, ncc.getTenNCC());
			stmt.setString(3, ncc.getDiaChi());
			stmt.setString(4, ncc.getSdt());
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
	
	public void suaNhaCungCap(NhaCungCap ncc){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update NhaCungCap set TenNhaCungCap = ?, Sdt = ?, ĐiaChi = ? where MaNhaCungCap = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ncc.getTenNCC());
			stmt.setString(2, ncc.getSdt());
			stmt.setString(3, ncc.getDiaChi());
			stmt.setString(4, ncc.getMaNCC());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	
	public void xoaNhaCungCap(String ma){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sqlTimPhieuNhap = "select MaPhieuNhapHang from PhieuNhapHang where MaNhaCungCap = '" + ma + "'" ;
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs1 = ((java.sql.Statement) statement).executeQuery(sqlTimPhieuNhap);
			while(rs1.next()) {
				String sqlCTPhieuNhap = "delete ChiTietPhieuNhapHang where MaPhieuNhapHang = ?";
				try {
					stmt = con.prepareStatement(sqlCTPhieuNhap);
					stmt.setString(1, rs1.getString(1));
					stmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String sqlPhieuNhap = "delete PhieuNhapHang where MaPhieuNhapHang = ?";
				try {
					stmt = con.prepareStatement(sqlPhieuNhap);
					stmt.setString(1, rs1.getString(1));
					stmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}	
			
			String sqlNCC = "delete NhaCungCap where MaNhaCungCap = ?";
			try {
				stmt = con.prepareStatement(sqlNCC);
				stmt.setString(1, ma);
				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	
	public List<NhaCungCap> timKiemNhaCungCap(String ten, String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		List<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
		try {
			String sql = "select * from NhaCungCap where TenNhaCungCap like N'%" + ten +"%' and MaNhaCungCap like '%" + ma + "%'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				if(!rs.getString(1).equalsIgnoreCase(" ")) {
					dsNhaCungCap.add(new NhaCungCap(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNhaCungCap;
	}
}
