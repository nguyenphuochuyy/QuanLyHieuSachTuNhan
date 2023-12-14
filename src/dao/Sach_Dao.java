package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.Sach;

public class Sach_Dao {
	public List<Sach> getAllSach(){
		List<Sach> dsSach = new ArrayList<Sach>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select MaSanPham,TenSanPham,TheLoaiSach,NhaSanXuat,GiaBan,ViTriKeSach,SoLuong,HinhAnh,SoTrang,TacGia from SanPham where LoaiSanPham = N'Sách'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsSach.add(new Sach(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getDouble(5)  ,rs.getString(6),  rs.getInt(7) , rs.getBytes(8), rs.getInt(9), rs.getString(10) ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSach;
	}
	
	
	
	
	public void themSanPham(Sach sach) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into SanPham(MaSanPham,TenSanPham,TheLoaiSach,NhaSanXuat,GiaBan,ViTriKeSach,SoLuong,HinhAnh,SoTrang,TacGia,LoaiSanPham) values(?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, sach.getMaSach());
			stmt.setString(2, sach.getTenSach());
			stmt.setString(3, sach.getTheLoai());
			stmt.setString(4, sach.getNhaXuatBan());
			stmt.setDouble(5, sach.getGiaBan());
			stmt.setString(6, sach.getViTriKeSach());
			stmt.setInt(7, sach.getSoLuong());
			stmt.setBytes(8, sach.getHinhAnh());
			stmt.setInt(9, sach.getSoTrang());
			stmt.setString(10, sach.getTacGia());
			stmt.setString(11, "Sách");
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}		
	}
	
	
	public void xoaSanPham(String ma){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
//			String sqlCTHoaDon = "delete ChiTietHoaDon where MaSanPham = ?";
//			try {
//				stmt = con.prepareStatement(sqlCTHoaDon);
//				stmt.setString(1,ma);
//				stmt.executeUpdate();
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			String sqlCTPhieuDatHang = "delete ChiTietPhieuDatHang where MaSanPham = ?";
			try {
				stmt = con.prepareStatement(sqlCTPhieuDatHang);
				stmt.setString(1, ma);
				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String sqlCTPhieuNhapHang = "delete ChiTietPhieuNhapHang where MaSanPham = ?";
			try {
				stmt = con.prepareStatement(sqlCTPhieuNhapHang);
				stmt.setString(1, ma);
				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String sqlSach = "delete SanPham where MaSanPham = ?";
			try {
				stmt = con.prepareStatement(sqlSach);
				stmt.setString(1, ma);
				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(stmt);
		}
	}
	
	
//	public void xoaKhachHang(String ma){
//		Connection con = ConnectDB.getInstance().getConnection();
//		PreparedStatement stmt = null;
		
//	}
	
	
	public void suaSanPham(Sach sach){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update SanPham set TenSanPham = ?, TheLoaiSach = ?, NhaSanXuat = ?, GiaBan = ?, ViTriKeSach = ?, SoLuong = ?, HinhAnh = ?, SoTrang = ?, TacGia = ? where MaSanPham = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sach.getTenSach());
			stmt.setString(2, sach.getTheLoai());
			stmt.setString(3, sach.getNhaXuatBan());
			stmt.setDouble(4, sach.getGiaBan());
			stmt.setString(5, sach.getViTriKeSach());
			stmt.setInt(6, sach.getSoLuong());
			stmt.setBytes(7, sach.getHinhAnh());
			stmt.setInt(8, sach.getSoTrang());
			stmt.setString(9, sach.getTacGia());
			stmt.setString(10, sach.getMaSach());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
//	
//	public boolean kiemTraSoLuongSanPham(String maSP, int soLuongBan) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		try {
//			String sql = "select SoLuong from SanPham where MaSanPham = '" + maSP + "'";
//			java.sql.Statement statement =  con.createStatement();
//			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
//			while(rs.next()) {
//				int soLuongCon = rs.getInt(1);
//				if(soLuongCon >= soLuongBan)
//					return true;
//				else
//					return false;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
	public void capNhatSoLuongSanPham(int soLuong,String maSP) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql1 = "select SoLuong from SanPham where MaSanPham like '" + maSP + "'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql1);
			while(rs.next()) {
				int soLuongCon = rs.getInt(1);
				int soLuongCapNhat = soLuongCon - soLuong;
				String sql2 = "update SanPham set SoLuong = " +soLuongCapNhat + " where MaSanPham = '" + maSP + "'";
				try {
					stmt = con.prepareStatement(sql2);
					stmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					close(stmt);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
//	
	// Tìm Kiếm Sách 
		public List<Sach> timKiemSach(String maSach ,String tenSach , String theLoaiSach , String tacGia)
		{
			Connection con = ConnectDB.getInstance().getConnection();
			List<Sach> ds = new ArrayList<Sach>();
			try {
				String sql = "select MaSanPham,TenSanPham,TheLoaiSach,NhaSanXuat,GiaBan,ViTriKeSach,SoLuong,HinhAnh,SoTrang,TacGia  from SanPham where MaSanPham like '%"+maSach+"%' and TenSanPham like N'%"+tenSach+"%' and TheLoaiSach like N'%"+theLoaiSach+"%' and TacGia like N'%"+tacGia+"%'";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					ds.add(new Sach(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getDouble(5)  ,rs.getString(6),  rs.getInt(7) , rs.getBytes(8), rs.getInt(9), rs.getString(10) ));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ds;
		}
		
		
		// Lấy danh sách combobox TheLoai  
		public ArrayList<String> getDataComboBox(){
			Connection con = ConnectDB.getInstance().getConnection();
			ArrayList<String> data = new ArrayList<String>();
			String sql = "select DISTINCT TheLoaiSach from SanPham where LoaiSanPham = N'Sách' ";
			Statement statement;
			try {
				statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					data.add(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return data;
			
		}
		// Lấy danh sách combox TacGia
		public ArrayList<String> getDataComboBoxTacGia(){
			Connection con = ConnectDB.getInstance().getConnection();
			ArrayList<String> data = new ArrayList<String>();
			String sql = "select DISTINCT TacGia  from SanPham where LoaiSanPham = N'Sách' ";
			Statement statement;
			try {
				statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					data.add(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return data;
			
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
