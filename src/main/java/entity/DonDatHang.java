package entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonDatHang {
	private int maDDH;
	private double tongTien;
	private Date ngayDat;
	private int tinhTrang;
	public KhachHang khachHang;
	public ArrayList<ChiTietDonDatHang> chiTietDonDatHangs = new ArrayList<ChiTietDonDatHang>();
	
	public DonDatHang(int maDDH, double tongTien, Date ngayDat, int tinhTrang, KhachHang khachHang,
			ArrayList<ChiTietDonDatHang> chiTietDonDatHangs) {
		super();
		this.maDDH = maDDH;
		this.tongTien = tongTien;
		this.ngayDat = ngayDat;
		this.tinhTrang = tinhTrang;
		this.khachHang = khachHang;
		this.chiTietDonDatHangs = chiTietDonDatHangs;
	}
	
	public DonDatHang(ResultSet rs) throws SQLException {
		this.maDDH = rs.getInt("maDDH");
		this.tinhTrang = rs.getInt("tinhTrang");
		try {
			this.khachHang = new KhachHang(rs);
		}catch (Exception e) {
			// TODO: handle exception
		}
//		this.chiTietDonDatHangs = chiTietDonDatHangs;
	}

	public int getMaDDH() {
		return maDDH;
	}

	public void setMaDDH(int maDDH) {
		this.maDDH = maDDH;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public ArrayList<ChiTietDonDatHang> getChiTietDonDatHangs() {
		return chiTietDonDatHangs;
	}

	public void setChiTietDonDatHangs(ArrayList<ChiTietDonDatHang> chiTietDonDatHangs) {
		this.chiTietDonDatHangs = chiTietDonDatHangs;
	}

	

	@Override
	public String toString() {
		return "DonDatHang [maDDH=" + maDDH + ", tongTien=" + tongTien + ", ngayDat=" + ngayDat + ", tinhTrang="
				+ tinhTrang + ", khachHang=" + khachHang + ", chiTietDonDatHangs=" + chiTietDonDatHangs + "]";
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	
}