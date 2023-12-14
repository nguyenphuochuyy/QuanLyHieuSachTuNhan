package entity;

public class ChiTietPhieuNhapHang {
	private String maPhieuNhap;
	private String maSanPham;
	private int soLuong ;
	private Double giaNhap;
	public ChiTietPhieuNhapHang(String maPhieuNhap, String maSanPham, int soLuong, Double giaNhap) {
		super();
		this.maPhieuNhap = maPhieuNhap;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
	}
	public ChiTietPhieuNhapHang() {
		super();
	}
	public String getMaPhieuNhap() {
		return maPhieuNhap;
	}
	public void setMaPhieuNhap(String maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public Double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(Double giaNhap) {
		this.giaNhap = giaNhap;
	}
	@Override
	public String toString() {
		return "ChiTietPhieuNhapHang [maPhieuNhap=" + maPhieuNhap + ", maSanPham=" + maSanPham + ", soLuong=" + soLuong
				+ ", giaNhap=" + giaNhap + "]";
	}

	
}
