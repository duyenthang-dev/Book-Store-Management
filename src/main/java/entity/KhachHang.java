package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHang {
	private int maKh;
	private String hoTen;
	private String soDienThoai;
	private String diaChi;
	public TaiKhoan taiKhoan;
	
	public KhachHang(int maKh, String hoTen, String soDienThoai, String diaChi,
			TaiKhoan taiKhoan) {
		super();
		this.maKh = maKh;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.taiKhoan = taiKhoan;
	}
	
	public KhachHang(int maKH, String hoTen, String soDienThoai, String diaChi) {
		this.hoTen = hoTen; 
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.maKh = maKH;
	}
	
	public KhachHang(ResultSet rs) throws SQLException {
		try {
			this.maKh = rs.getInt("maKH");
		}catch (Exception e) {
			
		}
		this.hoTen = rs.getString("hoTen");
		this.soDienThoai = rs.getString("soDienThoai");
		this.diaChi = rs.getString("diaChi");
	}

	public int getMaKh() {
		return maKh;
	}

	public void setMaKh(int maKh) {
		this.maKh = maKh;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	@Override
	public String toString() {
		return "KhachHang [maKh=" + maKh + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi 
				+ ", taiKhoan=" + taiKhoan  + "]";
	}
	
	
}