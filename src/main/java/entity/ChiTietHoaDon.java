package entity;

public class ChiTietHoaDon {
	private int id;
	private int soLuong;
	private double donGia;
	public HoaDon hoaDon;
	public SanPham sanPham;
	private int maSanPham;
	private String tenSanPham;
	
	public ChiTietHoaDon(int id, int soLuong, double donGia, HoaDon hoaDon, SanPham sanPham) {
		super();
		this.id = id;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
	}
	
	public ChiTietHoaDon(int id, int maSanPham, String tenSanPham, int soLuong, double donGia ) {
		super();
		this.id = id;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
	}

	public int getId() {
		return id;
	} 

	public void setId(int id) {
		this.id = id;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	

	public int getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [id=" + id + ", soLuong=" + soLuong + ", donGia=" + donGia + ", hoaDon=" + hoaDon
				+ ", sanPham=" + sanPham + "]";
	}
	
	
}