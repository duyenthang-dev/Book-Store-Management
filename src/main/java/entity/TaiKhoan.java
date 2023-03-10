package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoan {
	private int id;
	private String taiKhoan;
	private String matKhau;
	public KhachHang khachHang;
	public NhanVien nhanVien;
	public int role;
	// Role: quyền hạn của tài khoản: -1: khách hàng, 0: nhân viên, 1: admin
	
	public TaiKhoan(int id, String taiKhoan, String matKhau, KhachHang khachHang, NhanVien nhanVien) {
		super();
		this.id = id;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	
	public TaiKhoan(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.taiKhoan = rs.getString("taiKhoan");
	}
	
	public TaiKhoan(String taiKhoan, String matKhau, int role) {
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.role = role;
	}

	public TaiKhoan(int tkId, String taiKhoan, String matKhau, int role) {
		this.id = tkId;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "TaiKhoan [id=" + id + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", khachHang=" + khachHang
				+ ", nhanVien=" + nhanVien + "]";
	}
	
	
}