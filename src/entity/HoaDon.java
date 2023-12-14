package entity;

import java.sql.Date;
import java.time.LocalDate;

public class HoaDon {
	private String maHoaDon;
	private String maNhanVien;
	private String maKhachHang;
	private LocalDate ngayLapHoaDon;
	private String tenKhachHang;
	private String sdt;
	private String diaChi;
	private String maKhuyenMai;
	private int dungDiem;
	public HoaDon(String maHoaDon, String maNhanVien, String maKhachHang, LocalDate ngayLapHoaDon, String tenKhachHang,
			String sdt, String diaChi, String maKhuyenMai, int dungDiem) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.maKhuyenMai = maKhuyenMai;
		this.dungDiem = dungDiem;
	}
	public HoaDon() {
		super();
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
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
	public LocalDate getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}
	public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
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
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang
				+ ", ngayLapHoaDon=" + ngayLapHoaDon + ", tenKhachHang=" + tenKhachHang + ", sdt=" + sdt + ", diaChi="
				+ diaChi + ", maKhuyenMai=" + maKhuyenMai + ", dungDiem=" + dungDiem + "]";
	}
	
	
	
	
	
}
