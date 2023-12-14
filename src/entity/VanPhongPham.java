package entity;

import java.util.Arrays;

public class VanPhongPham {
	private String maSanPham;
	private String tenSanPham;
	private String nhaSanXuat;
	private Double giaBan;
	private String viTri;
	private int soLuong;
	private byte[] hinhAnh;
	private String loaiVanPhongPham;
	public VanPhongPham(String maSanPham, String tenSanPham, String nhaSanXuat, Double giaBan, String viTri,
			int soLuong, byte[] hinhAnh, String loaiVanPhongPham) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.nhaSanXuat = nhaSanXuat;
		this.giaBan = giaBan;
		this.viTri = viTri;
		this.soLuong = soLuong;
		this.hinhAnh = hinhAnh;
		this.loaiVanPhongPham = loaiVanPhongPham;
	}
	public VanPhongPham() {
		super();
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getNhaSanXuat() {
		return nhaSanXuat;
	}
	public void setNhaSanXuat(String nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}
	public Double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}
	public String getViTri() {
		return viTri;
	}
	public void setViTri(String viTri) {
		this.viTri = viTri;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public byte[] getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public String getLoaiVanPhongPham() {
		return loaiVanPhongPham;
	}
	public void setLoaiVanPhongPham(String loaiVanPhongPham) {
		this.loaiVanPhongPham = loaiVanPhongPham;
	}
	@Override
	public String toString() {
		return "VanPhongPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", nhaSanXuat=" + nhaSanXuat
				+ ", giaBan=" + giaBan + ", viTri=" + viTri + ", soLuong=" + soLuong + ", hinhAnh="
				+ Arrays.toString(hinhAnh) + ", loaiVanPhongPham=" + loaiVanPhongPham + "]";
	}
	
	
}
