package entity;

import java.sql.Date;
import java.time.LocalDate;

public class PhieuDatHang { 
	private String maPhieuDat;
	private String maNhanVien;
	private String maKhachHang;
	private LocalDate ngayDatHang;
	private String tenKhachHang;
	private String sdt;
	private String diaChi;
	private String maKhuyenMai;
	private int dungDiem;
	private String trangThai;
	public PhieuDatHang(String maPhieuDat, String maNhanVien, String maKhachHang, LocalDate ngayDatHang,
			String tenKhachHang, String sdt, String diaChi, String maKhuyenMai, int dungDiem, String trangThai) {
		super();
		this.maPhieuDat = maPhieuDat;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.ngayDatHang = ngayDatHang;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.maKhuyenMai = maKhuyenMai;
		this.dungDiem = dungDiem;
		this.trangThai = trangThai;
	}
	public PhieuDatHang() {
		super();
	}
	public String getMaPhieuDat() {
		return maPhieuDat;
	}
	public void setMaPhieuDat(String maPhieuDat) {
		this.maPhieuDat = maPhieuDat;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public LocalDate getNgayDatHang() {
		return ngayDatHang;
	}
	public void setNgayDatHang(LocalDate ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}
	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}
	public int getDungDiem() {
		return dungDiem;
	}
	public void setDungDiem(int dungDiem) {
		this.dungDiem = dungDiem;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "PhieuDatHang [maPhieuDat=" + maPhieuDat + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang
				+ ", ngayDatHang=" + ngayDatHang + ", tenKhachHang=" + tenKhachHang + ", sdt=" + sdt + ", diaChi="
				+ diaChi + ", maKhuyenMai=" + maKhuyenMai + ", dungDiem=" + dungDiem + ", trangThai=" + trangThai + "]";
	}
	
	
	
	
}
