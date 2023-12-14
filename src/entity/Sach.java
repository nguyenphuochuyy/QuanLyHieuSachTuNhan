package entity;

import java.util.Arrays;

public class Sach {
	private String maSach;
	private String tenSach ;
	private String theLoai;
	private String nhaXuatBan;
	private Double giaBan;
	private String viTriKeSach;
	private int soLuong;
	private byte[] hinhAnh;
	private int soTrang;
	private String tacGia;
	public Sach(String maSach, String tenSach, String theLoai, String nhaXuatBan, Double giaBan, String viTriKeSach,
			 int soLuong, byte[] hinhAnh, int soTrang, String tacGia) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.theLoai = theLoai;
		this.nhaXuatBan = nhaXuatBan;
		this.giaBan = giaBan;
		this.viTriKeSach = viTriKeSach;
		this.soLuong = soLuong;
		this.hinhAnh = hinhAnh;
		this.soTrang = soTrang;
		this.tacGia = tacGia;
	}
	public Sach() {
		super();
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public String getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}
	public String getNhaXuatBan() {
		return nhaXuatBan;
	}
	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}
	public Double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}
	public String getViTriKeSach() {
		return viTriKeSach;
	}
	public void setViTriKeSach(String viTriKeSach) {
		this.viTriKeSach = viTriKeSach;
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
	public int getSoTrang() {
		return soTrang;
	}
	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	@Override
	public String toString() {
		return "Sach [maSach=" + maSach + ", tenSach=" + tenSach + ", theLoai=" + theLoai + ", nhaXuatBan=" + nhaXuatBan
				+ ", giaBan=" + giaBan + ", viTriKeSach=" + viTriKeSach + ", soLuong="
				+ soLuong + ", hinhAnh=" + Arrays.toString(hinhAnh) + ", soTrang=" + soTrang + ", tacGia=" + tacGia
				+ "]";
	}
	
	
	
	
	
	
	
	
	
}
