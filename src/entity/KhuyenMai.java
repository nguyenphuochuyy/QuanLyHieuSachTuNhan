package entity;

import java.time.LocalDate;

public class KhuyenMai {
	private String maKhuyenMai;
	private String tenKhuyenMai;
	private int tiLeGiam;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private String trangThai;
	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, int tiLeGiam, LocalDate ngayBatDau, LocalDate ngayKetThuc,
			String trangThai) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.tiLeGiam = tiLeGiam;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.trangThai = trangThai;
	}
	public KhuyenMai() {
		super();
	}
	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}
	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}
	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}
	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}
	public int getTiLeGiam() {
		return tiLeGiam;
	}
	public void setTiLeGiam(int tiLeGiam) {
		this.tiLeGiam = tiLeGiam;
	}
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "KhuyenMai [maKhuyenMai=" + maKhuyenMai + ", tenKhuyenMai=" + tenKhuyenMai + ", tiLeGiam=" + tiLeGiam
				+ ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", trangThai=" + trangThai + "]";
	}
	
	
}
