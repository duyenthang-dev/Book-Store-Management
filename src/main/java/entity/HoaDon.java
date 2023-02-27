package entity;

import java.sql.Date;
import java.util.ArrayList;

public class HoaDon {
	private int maHD;
	private double tongTien;
	private Date ngayMua;
	public NhanVien nhanVien;
	public KhachHang khachHang;
	
	public HoaDon(int maHD, double tongTien, Date ngayMua, NhanVien nhanVien, KhachHang khachHang) {
		super();
		this.maHD = maHD;
		this.tongTien = tongTien;
		this.ngayMua = ngayMua;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
	}

	public int getMaHD() {
		return maHD;
	}

	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public Date getNgayMua() {
		return ngayMua;
	}

	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}


	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", tongTien=" + tongTien + ", ngayMua=" + ngayMua + ", nhanVienBanHang="
				 + ", khachHang="  + "]";
	}
	
	
	
}