package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;

public class KhuyenMai_Dao {
	public List<KhuyenMai> getAllKhuyenMai(){
		List<KhuyenMai> dsKhuyenMai = new ArrayList<KhuyenMai>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		capNhatTrangThaiKhuyenMai();
		try {
			String sql = "select * from KhuyenMai ";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				if(!rs.getString(1).equalsIgnoreCase("KM00")) {
					String trangThai;
					if(rs.getInt(6) == 0)
						trangThai = "Đang diễn ra";
					else if(rs.getInt(6) == 1)
						trangThai = "Đã kết thúc";
					else
						trangThai = "Chưa diễn ra";
					dsKhuyenMai.add(new KhuyenMai(rs.getString(1) , rs.getString(2),rs.getInt(3) ,rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate() , trangThai ));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhuyenMai;
	}
	
	public List<KhuyenMai> getAllKhuyenMaiDangDienRa(){
		List<KhuyenMai> dsKhuyenMai = new ArrayList<KhuyenMai>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		capNhatTrangThaiKhuyenMai();
		try {
			String sql = "select * from KhuyenMai ";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String trangThai;
				if(rs.getInt(6) == 0) {
					trangThai = "Đang diễn ra";
					dsKhuyenMai.add(new KhuyenMai(rs.getString(1) , rs.getString(2),rs.getInt(3) ,rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate() , trangThai ));
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhuyenMai;
	} 
	
	public void capNhatTrangThaiKhuyenMai() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhuyenMai ";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				LocalDate ngayBatDau = rs.getDate(4).toLocalDate();
				LocalDate ngayKetThuc = rs.getDate(5).toLocalDate();
				LocalDate ngayHienTai = LocalDate.now();

				PreparedStatement stmt = null;
				sql = "update KhuyenMai set TrangThai = ? where MaKhuyenMai = ?";
				if ((ngayHienTai.isAfter(ngayBatDau) && ngayHienTai.isBefore(ngayKetThuc)) || ngayHienTai.isEqual(ngayBatDau) || ngayHienTai.isEqual(ngayKetThuc)) {
					try {
						stmt = con.prepareStatement(sql);
						stmt.setInt(1, 0);
						stmt.setString(2, rs.getString(1));
						stmt.executeUpdate();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
		        }
				else if(ngayHienTai.isAfter(ngayKetThuc))
					try {
						stmt = con.prepareStatement(sql);
						stmt.setInt(1, 1);
						stmt.setString(2, rs.getString(1));
						stmt.executeUpdate();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				else if(ngayHienTai.isBefore(ngayBatDau)){
					try {
						stmt = con.prepareStatement(sql);
						stmt.setInt(1, 2);
						stmt.setString(2, rs.getString(1));
						stmt.executeUpdate();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void themKhuyenMai(KhuyenMai km) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into KhuyenMai values(?,?,?,?,?,?)");
			stmt.setString(1, km.getMaKhuyenMai());
			stmt.setString(2, km.getTenKhuyenMai());
			stmt.setInt(3, km.getTiLeGiam());
			stmt.setDate(4, java.sql.Date.valueOf(km.getNgayBatDau()));
			stmt.setDate(5, java.sql.Date.valueOf(km.getNgayKetThuc()));
			int trangThai;
			if(km.getTrangThai().equalsIgnoreCase("Đang diễn ra"))
				trangThai = 0;
			else if(km.getTrangThai().equalsIgnoreCase("Đã kết thúc"))
				trangThai = 1;
			else
				trangThai = 2;
			stmt.setInt(6,trangThai);

			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}			
	}
	
	public void suaKhuyenMai(KhuyenMai km){
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update KhuyenMai set TenKhuyenMai = ?, TiLeGiam = ?, NgayBatDau = ?, NgayKetThuc = ?, TrangThai = ? where MaKhuyenMai = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, km.getTenKhuyenMai());
			stmt.setInt(2, km.getTiLeGiam());
			stmt.setDate(3, java.sql.Date.valueOf(km.getNgayBatDau()));
			stmt.setDate(4, java.sql.Date.valueOf(km.getNgayKetThuc()));
			int trangThai;
			if(km.getTrangThai().equalsIgnoreCase("Đang diễn ra"))
				trangThai = 0;
			else if(km.getTrangThai().equalsIgnoreCase("Đã kết thúc"))
				trangThai = 1;
			else
				trangThai = 2;
			stmt.setInt(5,trangThai);
			stmt.setString(6, km.getMaKhuyenMai());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	
	public List<KhuyenMai> timKiemKhuyenMai(String maKM, String ngayBatDau,  String ngayKetThuc){
		List<KhuyenMai> dsKhuyenMai = new ArrayList<KhuyenMai>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select * from KhuyenMai where MaKhuyenMai like '%" + maKM + "%'  and CAST(NgayBatDau AS DATE) " + ngayBatDau + "" + " and CAST(NgayKetThuc AS DATE) " + ngayKetThuc + "";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsKhuyenMai.add(new KhuyenMai(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate(),rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhuyenMai;
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
