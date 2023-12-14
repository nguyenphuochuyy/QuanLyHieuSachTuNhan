package entity;

public class ChiTietPhieuDatHang {
	private String maPhieuDatHang; 
	private String maSanPham;
	private String tenSanPham;
	private Double giaBan;
	private int soLuong;
	public ChiTietPhieuDatHang(String maPhieuDatHang, String maSanPham, String tenSanPham, Double giaBan, int soLuong) {
		super();
		this.maPhieuDatHang = maPhieuDatHang;
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.giaBan = giaBan;
		this.soLuong = soLuong;
	}
	public ChiTietPhieuDatHang() {
		super();
	}
	public String getMaPhieuDatHang() {
		return maPhieuDatHang;
	}
	public void setMaPhieuDatHang(String maPhieuDatHang) {
		this.maPhieuDatHang = maPhieuDatHang;
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
		return "ChiTietPhieuDatHang [maPhieuDatHang=" + maPhieuDatHang + ", maSanPham=" + maSanPham + ", tenSanPham="
				+ tenSanPham + ", giaBan=" + giaBan + ", soLuong=" + soLuong + "]";
	}

	
	
	
}
