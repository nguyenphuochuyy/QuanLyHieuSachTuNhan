package entity;

import java.sql.Date;
import java.time.LocalDate;

public class NhanVien {
	private String maNhanVien;
	private String hoTenNhanVien;
	private String gioiTinh;
	private String sdt ;
	private String email;
	private String taiKhoan;
	private String matKhau;
	private String trangThai;
	private LocalDate ngaySinh;
	private String chucVu;
	private String diaChi;
	public NhanVien(String maNhanVien, String hoTenNhanVien, String gioiTinh, String sdt, String email, String taiKhoan,
			String matKhau, String trangThai, LocalDate ngaySinh, String chucVu, String diaChi) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTenNhanVien = hoTenNhanVien;
		this.gioiTinh = gioiTinh;
		this.sdt = sdt;
		this.email = email;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.trangThai = trangThai;
		this.ngaySinh = ngaySinh;
		this.chucVu = chucVu;
		this.diaChi = diaChi;
	}
	public NhanVien() {
		super();
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getHoTenNhanVien() {
		return hoTenNhanVien;
	}
	public void setHoTenNhanVien(String hoTenNhanVien) {
		this.hoTenNhanVien = hoTenNhanVien;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTenNhanVien=" + hoTenNhanVien + ", gioiTinh=" + gioiTinh
				+ ", sdt=" + sdt + ", email=" + email + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau
				+ ", trangThai=" + trangThai + ", ngaySinh=" + ngaySinh + ", chucVu=" + chucVu + ", diaChi=" + diaChi
				+ "]";
	}
	
}
