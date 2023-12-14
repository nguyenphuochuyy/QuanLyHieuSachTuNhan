package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_Dao {
	public List<KhachHang> getAllKhachHang(){
		List<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhachHang";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				if(!rs.getString(1).equalsIgnoreCase(" "))
					dsKhachHang.add(new KhachHang(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getInt(5)  , rs.getString(6)  , rs.getString(7) ,rs.getDate(8).toLocalDate()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}
	
	public void themKhachHang(KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into KhachHang values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getGioiTinh());
			stmt.setString(4, kh.getSdt());
			stmt.setInt(5, kh.getDiemTichLuy());
			stmt.setDate(8, java.sql.Date.valueOf(kh.getNgaySinh()));
			stmt.setString(6, kh.getEmail());
			stmt.setString(7, kh.getDiaChi());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}			
	}
	
	
	public void xoaKhachHang(String ma){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sqlTimHoaDon = "select MaHoaDon from HoaDon where MaKhachHang = '" + ma + "'" ;
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs1 = ((java.sql.Statement) statement).executeQuery(sqlTimHoaDon);
			while(rs1.next()) {
				String sqlCTHoaDon = "delete ChiTietHoaDon where MaHoaDon = ?";
				try {
					stmt = con.prepareStatement(sqlCTHoaDon);
					stmt.setString(1, rs1.getString(1));
					stmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String sqlHoaDon = "delete HoaDon where MaHoaDon = ?";
				try {
					stmt = con.prepareStatement(sqlHoaDon);
					stmt.setString(1, rs1.getString(1));
					stmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}	
			
			String sqlTimPhieuDatHang = "select MaPhieuDatHang from PhieuDatHang where MaKhachHang = '" + ma + "'" ;
			ResultSet rs2 = ((java.sql.Statement) statement).executeQuery(sqlTimPhieuDatHang);
			while(rs2.next()) {
				String sqlCTPhieuDatHang = "delete ChiTietPhieuDatHang where MaPhieuDatHang = ?";
				try {
					stmt = con.prepareStatement(sqlCTPhieuDatHang);
					stmt.setString(1, rs2.getString(1));
					stmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String sqlPhieuDatHang = "delete PhieuDatHang where MaPhieuDatHang = ?";
				try {
					stmt = con.prepareStatement(sqlPhieuDatHang);
					stmt.setString(1, rs2.getString(1));
					stmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception

				}
			}
			String sqlKhachHang = "delete KhachHang where MaKhachHang = ?";
			try {
				stmt = con.prepareStatement(sqlKhachHang);
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
	
	public void suaKhachHang(KhachHang kh){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update KhachHang set HoTenKhachHang = ?, GioiTinh = ?, Sdt = ?, DiemTichLuy = ?, NgaySinh = ?, Email = ?, DiaChi = ? where MaKhachHang = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getHoTen());
			stmt.setString(2, kh.getGioiTinh());
			stmt.setString(3, kh.getSdt());
			stmt.setInt(4, kh.getDiemTichLuy());
			stmt.setDate(5, java.sql.Date.valueOf(kh.getNgaySinh()));
			stmt.setString(6, kh.getEmail());
			stmt.setString(7, kh.getDiaChi());
			stmt.setString(8, kh.getMaKhachHang());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	
	
	public List<KhachHang> timKiemKhachHang(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		List<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		try {
			String sql = "select * from [dbo].[KhachHang] where HoTenKhachHang like N'%" + ten +"%'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				if(!rs.getString(1).equalsIgnoreCase(" ")) {
					dsKhachHang.add(new KhachHang(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getInt(5)  , rs.getString(6)  , rs.getString(7) ,rs.getDate(8).toLocalDate()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhachHang;
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
	// Lấy dữ liệu giới tính 
	public ArrayList<String> dataComboBox_GioiTinh(){
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<String> ds = new ArrayList<String>();
		try {
			String sql = "select DISTINCT GioiTinh from KhachHang";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				if(!rs.getString(1).equalsIgnoreCase(" "))
					ds.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	// tìm kiếm khách hàng
	public ArrayList<KhachHang> timKiemKhachHang(String ma , String ten , String ngaySinh , String sdt , String gioiTinh , String diaChi , String email){
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		try {
		
			String sql = "select * from KhachHang where MaKhachHang like N'%"+ma+"%' and HoTenKhachHang like N'%"+ten+"%' and  CAST(NgaySinh AS DATE) "+ngaySinh+" and Sdt like '%"+sdt+"%' and GioiTinh like N'%"+gioiTinh+"%' and DiaChi like N'%"+diaChi+"%' and Email like N'%"+email+"%'";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				if(!rs.getString(1).equalsIgnoreCase(" "))
					ds.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getDate(8).toLocalDate()));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return ds;
	}
	
	public void capNhatDiem(String ma, int diem) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update KhachHang set  DiemTichLuy = ? where MaKhachHang = ?";
		try {
			String sql1 = "select DiemTichLuy from KhachHang where MaKhachHang = '" + ma + "'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql1);
			while(rs.next()) {
				int diemCapNhat = rs.getInt(1) + diem;
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, diemCapNhat);
				stmt.setString(2, ma);
				stmt.executeUpdate();
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	
	
}
