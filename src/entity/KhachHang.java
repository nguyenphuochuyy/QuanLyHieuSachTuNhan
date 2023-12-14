package entity;

import java.time.LocalDate;

public class KhachHang {
	private String maKhachHang;
	private String hoTen;
	private String gioiTinh;
	private String sdt; 
	private int diemTichLuy;
	private String email ;
	private String diaChi;
	private LocalDate ngaySinh;

	
	public KhachHang(String maKhachHang, String hoTen, String gioiTinh, String sdt, int diemTichLuy, String email,
			String diaChi, LocalDate ngaySinh) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.sdt = sdt;
		this.diemTichLuy = diemTichLuy;
		this.email = email;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
	}
	public KhachHang() {
		super();
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public int getDiemTichLuy() {
		return diemTichLuy;
	}
	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", sdt=" + sdt
				+ ", diemTichLuy=" + diemTichLuy + ", ngaySinh=" + ngaySinh + ", email=" + email + ", diaChi=" + diaChi
				+ "]";
	}
	
	
	
	
	
	
	
	
}
