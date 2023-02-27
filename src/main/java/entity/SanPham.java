package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SanPham {
	private int maSp;
	private String tenSp;
	private String donViTinh;
	private int soLuong;
	private double giaSp;
	private double giaNhap;
	public LoaiSanPham loaiSanPham;
	public NhaCungCap nhaCungCap;
	
	
	
	public SanPham(int maSp, String tenSp, String donViTinh, int soLuong, double giaSp, double giaNhap,
			LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap) {
		this.maSp = maSp;
		this.tenSp = tenSp;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.giaSp = giaSp;
		this.giaNhap = giaNhap;
		this.loaiSanPham = loaiSanPham;
		this.nhaCungCap = nhaCungCap;
	}

	public SanPham(ResultSet rs) throws SQLException {
		this.maSp = rs.getInt("maSP");
		this.tenSp = rs.getString("tenSP");
		this.giaSp = rs.getDouble("giaSp");
		try {
			this.loaiSanPham = new LoaiSanPham(rs);
		}catch (Exception e) {
			
		}
			
		try {
			this.nhaCungCap = new NhaCungCap(rs);
		}catch (Exception e) {
			
		}	
	}

	public int getMaSp() {
		return maSp;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public void setMaSp(int maSp) {
		this.maSp = maSp;
	}

	public String getTenSp() {
		return tenSp;
	}

	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}

	public double getGiaSp() {
		return giaSp;
	}

	public void setGiaSp(double giaSp) {
		this.giaSp = giaSp;
	}

	public LoaiSanPham getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}


	@Override
	public String toString() {
		return "SanPham [maSp=" + maSp + ", tenSp=" + tenSp + ", giaSp=" + giaSp + ", loaiSanPham=" + loaiSanPham
				+ ", nhaCungCap=" + nhaCungCap + "]";
	}
	
	
}