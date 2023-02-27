package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhaCungCap {
	private int maNCC;
	private String tenNCC;
	private String diaChi;
	private String soDienThoai;
	
	public NhaCungCap(int maNCC, String tenNCC, String diaChi, String soDienThoai) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}
	
	public NhaCungCap(ResultSet rs) throws SQLException {
		this.maNCC = rs.getInt("maNCC");
		this.tenNCC = rs.getString("tenNCC");
		this.diaChi = rs.getString("diaChi");
		this.soDienThoai = rs.getString("soDienThoai");
//		this.sanPhams = sanPhams;
	}

	public int getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", soDienThoai="
				+ soDienThoai + ""
						+ "]";
	}
	
	
}