package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatHang;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.PhieuDatHang;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class PhieuDatHang_Dao {
	KhuyenMai_Dao khuyenMaiDao = new KhuyenMai_Dao();
	ChiTietPhieuDatHang_Dao chiTietPhieuDatHangDao = new ChiTietPhieuDatHang_Dao();
	public List<PhieuDatHang> getAllPhieuDatHang(){
		List<PhieuDatHang> dsPhieuDatHang= new ArrayList<PhieuDatHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatHang";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String trangThai;
				if(rs.getInt(10) == 0)
					trangThai = "Chưa thanh toán";
				else
					trangThai = "Đã thanh toán";
				dsPhieuDatHang.add(new PhieuDatHang(rs.getString(1),rs.getString(4),rs.getString(3),rs.getDate(2).toLocalDate(),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),trangThai));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhieuDatHang;
	}
	
	public List<PhieuDatHang> getAllPhieuDatHangTimKiem(){
		List<PhieuDatHang> dsPhieuDatHang= new ArrayList<PhieuDatHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT PDH.MaPhieuDatHang, PDH.MaNhanVien, PDH.MaKhachHang, PDH.TenKhachHang, PDH.Sdt, PDH.DiaChi, PDH.NgayDatHang, KM.TiLeGiam, PDH.DungDiem, PDH.TrangThai FROM KhuyenMai AS KM INNER JOIN PhieuDatHang AS PDH ON KM.MaKhuyenMai = PDH.MaKhuyenMai";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String trangThai;
				if(rs.getInt(10) == 0)
					trangThai = "Chưa thanh toán";
				else
					trangThai = "Đã thanh toán";
				String tiLe = String.valueOf(rs.getInt(8));
				dsPhieuDatHang.add(new PhieuDatHang(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(7).toLocalDate(),rs.getString(4),rs.getString(5),rs.getString(6),tiLe,rs.getInt(9),trangThai));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhieuDatHang;
	}
	
	public void themPhieuDatHang(PhieuDatHang pdh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into PhieuDatHang values(?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, pdh.getMaPhieuDat());
			stmt.setDate(2, java.sql.Date.valueOf(pdh.getNgayDatHang()));
			stmt.setString(3, pdh.getMaKhachHang());
			stmt.setString(4, pdh.getMaNhanVien());
			stmt.setString(5, pdh.getTenKhachHang());
			stmt.setString(6, pdh.getSdt());
			stmt.setString(7, pdh.getDiaChi());
			stmt.setString(8, pdh.getMaKhuyenMai());
			stmt.setInt(9, pdh.getDungDiem());
			int trangThai;
			if(pdh.getTrangThai().equalsIgnoreCase("Chưa thanh toán"))
				trangThai = 0;
			else
				trangThai = 1;
			stmt.setInt(10, trangThai);
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "B");
		} finally {
			close(stmt);
		}			
	}
	
	public void capNhatTrangThai(String maPhieuDat) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update PhieuDatHang set TrangThai = 1 where MaPhieuDatHang = '" + maPhieuDat + "'");
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "B");
		} finally {
			close(stmt);
		}			
	}
	
	public void xoaPhieuDat(String maPhieuDat) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			java.sql.Statement statement =  con.createStatement();
			String sqlCTPhieuDatHang = "delete ChiTietPhieuDatHang where MaPhieuDatHang = ?";
			try {
				stmt = con.prepareStatement(sqlCTPhieuDatHang);
				stmt.setString(1, maPhieuDat);
				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String sqlPhieuDatHang = "delete PhieuDatHang where MaPhieuDatHang = ?";
			try {
				stmt = con.prepareStatement(sqlPhieuDatHang);
				stmt.setString(1, maPhieuDat);
				stmt.executeUpdate();
			} catch (Exception e) {
					// TODO: handle exception

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List<PhieuDatHang> timKiemPhieuDatHang(String maPDH, String maKH, String maNV, String tenKH , String trangThai){
		List<PhieuDatHang> dsPhieuDatHang = new ArrayList<PhieuDatHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select * from PhieuDatHang where MaPhieuDatHang like '%" + maPDH + "%' and MaNhanVien like '%" + maNV + "%' and MaKhachHang like '%" + maKH + "%' and TenKhachHang like '%" + tenKH + "%' and TrangThai " + trangThai + "";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String trangThaii;
				if(rs.getInt(10) == 0)
					trangThaii = "Chưa thanh toán";
				else
					trangThaii = "Đã thanh toán";
				dsPhieuDatHang.add(new PhieuDatHang(rs.getString(1),rs.getString(4),rs.getString(3),rs.getDate(2).toLocalDate(),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),trangThaii));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhieuDatHang;
	}
	
	public double layTongThanhTienPhieuDat(String maPhieuDat) {
		 ConnectDB.getInstance();
		    Connection con = ConnectDB.getConnection();
		    double tong = 0;
		    try {
		        String sql = "select Tong = sum(SoLuong*GiaBan) from ChiTietPhieuDatHang where MaPhieuDatHang = '" + maPhieuDat + "'";
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
	
	
	public double layTongTienPhieuDat(String maPhieuDat) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    double tong = 0;
	    try {
	        String sql = "select Tong = sum(SoLuong*GiaBan) from ChiTietPhieuDatHang where MaPhieuDatHang = '" + maPhieuDat + "'";
	        java.sql.Statement statement = con.createStatement();
	        ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
	        double tongChiTiet = 0;

	        while (rs.next()) {
	            tongChiTiet = rs.getDouble(1);
	        }

	        try {
	            String sql2 = "SELECT DungDiem,TiLeGiam FROM KhuyenMai INNER JOIN PhieuDatHang ON KhuyenMai.MaKhuyenMai = PhieuDatHang.MaKhuyenMai where MaPhieuDatHang = '" + maPhieuDat + "'";
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
	
	
	public void xuatPhieuDat(String MaDH) {
		try {	
			List<PhieuDatHang> dh = new ArrayList<PhieuDatHang>();
			dh = timKiemPhieuDatHang(MaDH, "", "", "", "is not null");
			List<KhuyenMai> km = new ArrayList<KhuyenMai>();
			km = khuyenMaiDao.timKiemKhuyenMai(dh.get(0).getMaKhuyenMai(), "is not null", "is not null");
			DecimalFormat df = new DecimalFormat("#,### VNĐ");
			Hashtable data = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/inXuat/InPhieuDat.jrxml");
			double tongTien = layTongThanhTienPhieuDat(MaDH);
			double tienKM = layTongThanhTienPhieuDat(MaDH)*km.get(0).getTiLeGiam()/100;
			double VAT = tongTien*10/100;
			double diemDung = dh.get(0).getDungDiem()*1000;
			double phaiTra = tongTien+VAT-tienKM-diemDung;
			data.put("MaPhieuDat", MaDH);
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
