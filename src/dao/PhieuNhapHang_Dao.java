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
import entity.HoaDon;
import entity.KhuyenMai;
import entity.NhaCungCap;
import entity.PhieuDatHang;
import entity.PhieuNhapHang;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class PhieuNhapHang_Dao {
	NhaCungCap_Dao nhaCungCapDao = new NhaCungCap_Dao();
	public List<PhieuNhapHang> getAllPhieuNhapHang(){
		List<PhieuNhapHang> dsPhieuNhapHang = new ArrayList<PhieuNhapHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuNhapHang";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				if(!rs.getString(1).equalsIgnoreCase(" "))
					dsPhieuNhapHang.add(new PhieuNhapHang(rs.getString(1) , rs.getDate(2).toLocalDate() , rs.getString(3) , rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhieuNhapHang;
	}
	
	public void themPhieuNhapHang(PhieuNhapHang nh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into PhieuNhapHang values(?,?,?,?)");
			stmt.setString(1, nh.getMaPhieuNhapHang());
			stmt.setDate(2, java.sql.Date.valueOf(nh.getNgayNhapHang()));
			stmt.setString(3, nh.getMaNhanVien());
			stmt.setString(4, nh.getMaNhaCungCap());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}			
	}
	
	public double layTongTienPhieuNhap(String maPhieuNhap) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    double tong = 0;
	    try {
	        String sql = "select Tong = sum(SoLuong*GiaNhap) from ChiTietPhieuNhapHang where MaPhieuNhapHang = '" + maPhieuNhap + "'";
	        java.sql.Statement statement = con.createStatement();
	        ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
	        while (rs.next()) {
	            tong = rs.getDouble(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tong;
	}
	
	
	public List<PhieuNhapHang> timKiemPhieuNhap(String maPNH, String maNV, String tenNhanVien , String nhaCungCap , String ngayLap){
		List<PhieuNhapHang> dsPhieuNhapHang = new ArrayList<PhieuNhapHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT PhieuNhapHang.MaPhieuNhapHang, PhieuNhapHang.NgayNhapHang, PhieuNhapHang.MaNhanVien, NhanVien.HoTenNhanVien, NhaCungCap.TenNhaCungCap  "
					+ "FROM NhaCungCap INNER JOIN PhieuNhapHang ON NhaCungCap.MaNhaCungCap = PhieuNhapHang.MaNhaCungCap INNER JOIN NhanVien ON PhieuNhapHang.MaNhanVien = NhanVien.MaNhanVien "
					+ "where MaPhieuNhapHang like '%" + maPNH + "%' "
							+ "and NhanVien.MaNhanVien like '%" + maNV + "%' "
									+ "and HoTenNhanVien like N'%" + tenNhanVien + "%' "
											+ "and TenNhaCungCap like N'" + nhaCungCap + "' "
													+ "and NgayNhapHang " + ngayLap + "";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				dsPhieuNhapHang.add(new PhieuNhapHang(rs.getString(1),rs.getDate(2).toLocalDate(),rs.getString(3),rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhieuNhapHang;
	}
	
	public void xuatPhieuNhap(String MaNH) {
		try {	
			List<PhieuNhapHang> dh = new ArrayList<PhieuNhapHang>();
			dh = timKiemPhieuNhap(MaNH, "", "", "%%", "is not null");
			List<NhaCungCap> ncc = new ArrayList<NhaCungCap>();
			ncc = nhaCungCapDao.timKiemNhaCungCap("",dh.get(0).getMaNhaCungCap());

			DecimalFormat df = new DecimalFormat("#,### VNĐ");
			Hashtable data = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/inXuat/InPhieuNhap.jrxml");
			double tongTien = layTongTienPhieuNhap(MaNH);
			data.put("MaPhieuNhap", MaNH);
			data.put("TongTien", df.format(tongTien));			
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
