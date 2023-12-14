package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Sach;
import entity.VanPhongPham;

public class VanPhongPham_Dao {
	public List<VanPhongPham> getAllVanPhongPham(){
		List<VanPhongPham> dsVanPhongPham = new ArrayList<VanPhongPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select MaSanPham,TenSanPham,NhaSanXuat,GiaBan,ViTriKeSach,SoLuong,HinhAnh,LoaiVanPhongPham from SanPham where LoaiSanPham = N'Văn phòng phẩm'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsVanPhongPham.add(new VanPhongPham(rs.getString(1) , rs.getString(2) , rs.getString(3) ,  rs.getDouble(4)  ,rs.getString(5),  rs.getInt(6) , rs.getBytes(7), rs.getString(8) ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVanPhongPham;
	}
	
	
	public void themVanPhongPham(VanPhongPham vpp) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into SanPham(MaSanPham,TenSanPham,NhaSanXuat,GiaBan,ViTriKeSach,SoLuong,HinhAnh,LoaiVanPhongPham,LoaiSanPham) values(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, vpp.getMaSanPham());
			stmt.setString(2, vpp.getTenSanPham());
			stmt.setString(3, vpp.getNhaSanXuat());
			stmt.setDouble(4, vpp.getGiaBan());
			stmt.setString(5, vpp.getViTri());
			stmt.setInt(6, vpp.getSoLuong());
			stmt.setBytes(7, vpp.getHinhAnh());
			stmt.setString(8, vpp.getLoaiVanPhongPham());
			stmt.setString(9, "Văn phòng phẩm");
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}		
	}
	
	public void suaVanPhongPham(VanPhongPham vpp){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update SanPham set TenSanPham = ?, NhaSanXuat = ?, GiaBan = ?, ViTriKeSach = ?, SoLuong = ?, HinhAnh = ?, LoaiVanPhongPham = ?  where MaSanPham = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vpp.getTenSanPham());
			stmt.setString(2, vpp.getNhaSanXuat());
			stmt.setDouble(3, vpp.getGiaBan());
			stmt.setString(4, vpp.getViTri());
			stmt.setInt(5, vpp.getSoLuong());
			stmt.setBytes(6, vpp.getHinhAnh());
			stmt.setString(7, vpp.getLoaiVanPhongPham());
			stmt.setString(8, vpp.getMaSanPham());
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
			String sqlCTHoaDon = "delete ChiTietHoaDon where MaSanPham = ?";
			try {
				stmt = con.prepareStatement(sqlCTHoaDon);
				stmt.setString(1,ma);
				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
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
	
	// Lấy dữ liệu combobox_LoaiVanPhongPham
		public ArrayList<String> dataComboBox_LoaiVanPhongPham(){
			Connection con = ConnectDB.getInstance().getConnection();
			ArrayList<String> ds = new ArrayList<String>();
			try {
				String sql = "select DISTINCT LoaiVanPhongPham from SanPham where LoaiSanPham = N'Văn Phòng Phẩm'";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					ds.add(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return ds;
		}
		
		// tìm kiếm văn phòng phẩm 
		public ArrayList<VanPhongPham> timKiemVanPhongPham(String ma , String ten , String nhasanxuat, String loaivpp){
			Connection con = ConnectDB.getInstance().getConnection();
			ArrayList<VanPhongPham> ds = new ArrayList<VanPhongPham>();
			try {
			String sql = "Select MaSanPham,TenSanPham,NhaSanXuat,GiaBan,ViTriKeSach,SoLuong,HinhAnh,LoaiVanPhongPham from SanPham where MaSanPham like '%"+ma+"%' and TenSanPham like N'%"+ten+"%' and NhaSanXuat like N'%"+nhasanxuat+"%'  and LoaiVanPhongPham like N'%"+loaivpp+"%' and LoaiSanPham = N'Văn Phòng Phẩm'"; 
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ds.add(new VanPhongPham(rs.getString(1) , rs.getString(2) , rs.getString(3) ,  rs.getDouble(4)  ,rs.getString(5),  rs.getInt(6) , rs.getBytes(7), rs.getString(8) ));
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ds;
			
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
