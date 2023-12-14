package entity;

import java.sql.Date;
import java.time.LocalDate;

public class PhieuNhapHang {
	private String maPhieuNhapHang;
	private LocalDate ngayNhapHang;
	private String maNhanVien;
	private String maNhaCungCap;
	public PhieuNhapHang(String maPhieuNhapHang, LocalDate ngayNhapHang, String maNhanVien, String maNhaCungCap) {
		super();
		this.maPhieuNhapHang = maPhieuNhapHang;
		this.ngayNhapHang = ngayNhapHang;
		this.maNhanVien = maNhanVien;
		this.maNhaCungCap = maNhaCungCap;
	}
	public PhieuNhapHang() {
		super();
	}
	public String getMaPhieuNhapHang() {
		return maPhieuNhapHang;
	}
	public void setMaPhieuNhapHang(String maPhieuNhapHang) {
		this.maPhieuNhapHang = maPhieuNhapHang;
	}
	public LocalDate getNgayNhapHang() {
		return ngayNhapHang;
	}
	public void setNgayNhapHang(LocalDate ngayNhapHang) {
		this.ngayNhapHang = ngayNhapHang;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	@Override
	public String toString() {
		return "PhieuNhapHang [maPhieuNhapHang=" + maPhieuNhapHang + ", ngayNhapHang=" + ngayNhapHang + ", maNhanVien="
				+ maNhanVien + ", maNhaCungCap=" + maNhaCungCap + "]";
	}
	
	
	
	
}
