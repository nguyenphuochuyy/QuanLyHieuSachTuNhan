package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;

public class NhanVien_Dao {
	public List<NhanVien> getAllNhanVien(){
		List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhanVien";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String trangThai;
				if(rs.getInt(8) == 0) 
					trangThai = "Đang làm";
				else 
					trangThai = "Đã nghỉ";
				
				String chucVu;
				if(rs.getInt(10) == 0)
					chucVu = "Nhân viên bán hàng";
				else
					chucVu = "Nhân viên quản lý";

				dsNhanVien.add(new NhanVien(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5)  , rs.getString(6)  , rs.getString(7) ,trangThai, rs.getDate(9).toLocalDate(), chucVu, rs.getString(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	
	public void themNhanVien(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getHoTenNhanVien());
			stmt.setString(3, nv.getGioiTinh());
			stmt.setString(4, nv.getSdt());
			stmt.setString(5, nv.getEmail());
			stmt.setString(6, nv.getTaiKhoan());
			stmt.setString(7, nv.getMatKhau());
			int trangThai;
			if(nv.getChucVu().equalsIgnoreCase("Đang làm"))
				trangThai = 0;
			else
				trangThai = 1;
			stmt.setInt(8, trangThai);
			stmt.setDate(9, java.sql.Date.valueOf(nv.getNgaySinh()));
			int chucVu;
			if(nv.getChucVu().equalsIgnoreCase("Nhân viên Quản lý"))
				chucVu = 1;
			else
				chucVu = 0;
			stmt.setInt(10, chucVu);
			stmt.setString(11,nv.getDiaChi());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}			
	}
	
	public void suaNhanVien(NhanVien nv){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update NhanVien set HoTenNhanVien = ?, GioiTinh = ?, Sdt = ?, Email = ?, TaiKhoan = ?, MatKhau = ?,TrangThai = ? , NgaySinh = ?, ChucVu = ?, DiaChi = ? where MaNhanVien = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getHoTenNhanVien());
			stmt.setString(2, nv.getGioiTinh());
			stmt.setString(3, nv.getSdt());
			stmt.setString(4, nv.getEmail());
			stmt.setString(5, nv.getTaiKhoan());
			stmt.setString(6, nv.getMatKhau());
			int trangThai;
			if(nv.getTrangThai().equalsIgnoreCase("Đang làm"))
				trangThai = 0;
			else
				trangThai = 1;
			stmt.setInt(7, trangThai);
			stmt.setDate(8, java.sql.Date.valueOf(nv.getNgaySinh()));
			int chucVu;
			if(nv.getChucVu().equalsIgnoreCase("Nhân viên Quản lý"))
				chucVu = 1;
			else
				chucVu = 0;
			stmt.setInt(9, chucVu);
			stmt.setString(10, nv.getDiaChi());
			stmt.setString(11, nv.getMaNhanVien());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	
	// tìm kiếm nhân viên 
	public ArrayList<NhanVien> timKiemNhanVien(String ma , String ten , String taiKhoan ,String ngaySinh , String email , String sdt , String diaChi,String trangThai,String gioiTinh , String chucVu) {
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			String sql = "select * from NhanVien where MaNhanVien like '%"+ma+"%'"
					+ " and HoTenNhanVien like N'%"+ten+"%' "
							+ "and TaiKhoan like '%"+taiKhoan+"%' "
									+ "and CAST(NgaySinh AS DATE) "+ ngaySinh+" "
											+ "and Email like N'%"+email+"%' "
													+ "and Sdt like '%"+sdt+"%' "
															+ "and DiaChi like N'%"+diaChi+"%' "
																	+ "and TrangThai "+trangThai +" "
																			+ "and GioiTinh like N'%"+gioiTinh+"%'"
																					+ "and ChucVu "+chucVu;
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					String trangThaii;
					if(rs.getInt(8) == 0) 
						trangThaii = "Đang làm";
					else 
						trangThaii = "Đã nghỉ";
					
					String chucVuu;
					if(rs.getInt(10) == 0)
						chucVuu = "Nhân viên bán hàng";
					else
						chucVuu = "Nhân viên quản lý";
					ds.add(new NhanVien(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5)  , rs.getString(6)  , rs.getString(7) ,trangThaii, rs.getDate(9).toLocalDate(), chucVuu, rs.getString(11)));
				}
		} catch (SQLException e) {
			// TODO: handle exception
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
	
	// lấy dữ liệu lên combobox_TrangThai
	public ArrayList<String> dataComboBox_TrangThai(){
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<String> ds = new ArrayList<String>();
		try {
			String sql = "select DISTINCT TrangThai from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String trangThai; 
				if(rs.getInt(1) == 0 ) {
					trangThai = "Đang Làm";
				}
				else 
				{
					trangThai = "Đã Nghỉ";
				}
				ds.add(trangThai);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	// lấy dữ liệu combobox_GioiTinh
	public ArrayList<String> dataComboBox_GioiTinh(){
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<String> ds = new ArrayList<String>();
		try {
			String sql = "select DISTINCT GioiTinh from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				ds.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	// lấy dữ liệu combobox_VaiTro
	public ArrayList<String> dataComboBox_VaiTro(){
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<String> ds = new ArrayList<String>();
		try {
			String sql = "select DISTINCT Chucvu from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String chucVu ; 
				if(rs.getInt(1) == 0) {
					chucVu = "Nhân viên bán hàng";
				}
				else
				{
					chucVu = "Nhân viên quản lí";
				}
				ds.add(chucVu);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	// lấy tên nhân viên theo mã 
	public String layTenTheoMa(String ma) {
		Connection con = ConnectDB.getInstance().getConnection();
		String ten = null;
		try {
			Statement statement = con.createStatement();
			String sql = "select HoTenNhanVien from NhanVien where MaNhanVien like N'%"+ma+"%'";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ten = rs.getString(1);
			}
		} catch (SQLException  e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ten;
	}
	// lấy dữ liệu combobox_MaNhanVien
		public ArrayList<String> dataCombobox_MaNhanvien() {
			Connection con = ConnectDB.getInstance().getConnection();
			ArrayList<String> ds = new ArrayList<String>();
			try {
				String sql = "select MaNhanVien from NhanVien";
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
	
	public boolean doiMatKhau(String maNhanVien, String matKhauCu, String matKhauMoi) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    try {
	        String sql = "select MatKhau from NhanVien where MaNhanVien = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, maNhanVien);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            if (!rs.getString(1).equalsIgnoreCase(matKhauCu)) {
	                JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác");
	                return false;
	            } else {
	                String sql2 = "update NhanVien set MatKhau = ? where MaNhanVien = ?";
	                PreparedStatement updateStmt = con.prepareStatement(sql2);
	                updateStmt.setString(1, matKhauMoi);
	                updateStmt.setString(2, maNhanVien);
	                updateStmt.executeUpdate();
	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
//	public double layDoanhThuNhanVien(String maNhanVien, String ngay) {
//		double tong = 0;
//		ConnectDB.getInstance();
//	    Connection con = ConnectDB.getConnection();
//	    try {
//	        String sql = "SELECT HoaDon.MaHoaDon, HoaDon.MaNhanVien, NhanVien.HoTenNhanVien,NgayLapHoaDon "
//	        		+ "FROM HoaDon INNER JOIN NhanVien ON HoaDon.MaNhanVien = NhanVien.MaNhanVien "
//	        		+ "where HoaDon.MaNhanVien = '" + maNhanVien + "' " 
//	        		+ " and NgayLapHoaDon " + ngay;
//	       
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//		return tong;
//	}
}
