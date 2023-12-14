package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class HoaDon_Dao {
	KhuyenMai_Dao khuyenMaiDao = new KhuyenMai_Dao();
	ChiTietHoaDon_Dao chiTietHoaDonDao = new ChiTietHoaDon_Dao();
	public List<HoaDon> getAllHoaDon(){
		List<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDon";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsHoaDon.add(new HoaDon(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4).toLocalDate(),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}
	
	public void themHoaDon(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into HoaDon values(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setString(2, hd.getMaNhanVien());
			stmt.setString(3, hd.getMaKhachHang());
			stmt.setDate(4, java.sql.Date.valueOf(hd.getNgayLapHoaDon()));
			stmt.setString(5, hd.getTenKhachHang());
			stmt.setString(6, hd.getSdt());
			stmt.setString(7, hd.getDiaChi());
			stmt.setString(8, hd.getMaKhuyenMai());
			stmt.setInt(9, hd.getDungDiem());

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
	
	public List<HoaDon> timKiemHoaDon(String maHD, String maNV, String maKH, String tenKhach , String ngayLap){
		List<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select * from HoaDon where MaHoaDon like '%" + maHD + "%' and MaNhanVien like '%" + maNV + "%' and MaKhachHang like '%" + maKH + "%' and TenKhachHang like N'%" + tenKhach + "%' and CAST(NgayLapHoaDon AS DATE) " + ngayLap + "";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsHoaDon.add(new HoaDon(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4).toLocalDate(),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}
	
	public double layTongThanhTienHoaDon(String maHoaDon) {
		 ConnectDB.getInstance();
		    Connection con = ConnectDB.getConnection();
		    double tong = 0;
		    try {
		        String sql = "select Tong = sum(SoLuong*GiaBan) from ChiTietHoaDon where MaHoaDon = '" + maHoaDon + "'";
		        java.sql.Statement statement = con.createStatement();
		        ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
		        while (rs.next()) {
		            tong = rs.getDouble(1);
		        }
		    } catch (Exception e) {
	        	e.printStackTrace();
	        }
		    return tong;
	}
	
	
	
	public double layTongTienHoaDon(String maHoaDon) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    double tong = 0;
	    try {
	        String sql = "select Tong = sum(SoLuong*GiaBan) from ChiTietHoaDon where MaHoaDon = '" + maHoaDon + "'";
	        java.sql.Statement statement = con.createStatement();
	        ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
	        double tongChiTiet = 0;

	        while (rs.next()) {
	            tongChiTiet = rs.getDouble(1);
	        }

	        try {
	            String sql2 = "SELECT DungDiem,TiLeGiam FROM KhuyenMai INNER JOIN HoaDon ON KhuyenMai.MaKhuyenMai = HoaDon.MaKhuyenMai where MaHoaDon = '" + maHoaDon + "'";
	            ResultSet rs2 = ((java.sql.Statement) statement).executeQuery(sql2);

	            while (rs2.next()) {
	            	double tienGiamDungDiem = (double) (rs2.getInt(1) * 1000);
	            	 double tienGiamKhuyenMai = tongChiTiet*rs2.getInt(2)/100;
	            	 int vat = (int) (tongChiTiet*0.1);
	                tong = tongChiTiet - tienGiamDungDiem -  tienGiamKhuyenMai;
	 	            tong+=vat;
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tong;
	}
	
	
	public void xuatHoaDon(String MaHD) {
		try {	
			List<HoaDon> hd = new ArrayList<HoaDon>();
			hd = timKiemHoaDon(MaHD, "", "", "", "is not null");
			List<KhuyenMai> km = new ArrayList<KhuyenMai>();
			km = khuyenMaiDao.timKiemKhuyenMai(hd.get(0).getMaKhuyenMai(), "is not null", "is not null");

			DecimalFormat df = new DecimalFormat("#,### VNĐ");
			Hashtable data = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/inXuat/InHoaDon.jrxml");
			double tongTien = layTongThanhTienHoaDon(MaHD);
			double tienKM = layTongThanhTienHoaDon(MaHD)*km.get(0).getTiLeGiam()/100;
			double VAT = tongTien*10/100;
			double diemDung = hd.get(0).getDungDiem()*1000;
			double phaiTra = tongTien+VAT-tienKM-diemDung;
			data.put("MaHoaDon", MaHD);
			data.put("TongTien", df.format(tongTien));			
			data.put("TienKhuyenMai", df.format(tienKM));
			data.put("VAT", df.format(VAT));
			data.put("DiemDung", df.format(diemDung));
			data.put("PhaiTra", df.format(phaiTra));
			JasperPrint print = JasperFillManager.fillReport(report, data, ConnectDB.getConnection());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("Error Message: " + e.getMessage());
		}
	}

}
