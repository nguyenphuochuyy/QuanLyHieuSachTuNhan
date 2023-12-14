package entity;

public class ChiTietHoaDon {
	private String maHoaDon; 
	private String maSanPham;
	private String tenSanPham;
	private Double giaBan;
	private int soLuong;
	public ChiTietHoaDon(String maHoaDon, String maSanPham, String tenSanPham, Double giaBan, int soLuong) {
		super();
		this.maHoaDon = maHoaDon;
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.giaBan = giaBan;
		this.soLuong = soLuong;
	}
	public ChiTietHoaDon() {
		super();
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
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
	public Double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [maHoaDon=" + maHoaDon + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham
				+ ", giaBan=" + giaBan + ", soLuong=" + soLuong + ", giamGia="  + "]";
	}
	
	
	
	
	
}
