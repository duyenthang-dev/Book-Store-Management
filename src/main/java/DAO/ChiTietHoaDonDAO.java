package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.ChiTietHoaDon;

public class ChiTietHoaDonDAO extends ConnectDB{

	public ChiTietHoaDonDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ChiTietHoaDon> getByMaHD(int maHD) throws SQLException {
		PreparedStatement stmt = null;
		List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		try {
			String sql = 	"SELECT ct.ID, ct.MaSP, sp.TenSp, ct.SoLuong, ct.DonGia "
					+ 		"FROM ChiTietHoaDon ct "
			 		+ 		"    JOIN SanPham sp "
					+ 		"        ON ct.MaSP = sp.MaSP "
					+ 		"WHERE ct.MaHD = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				int maSanPham = rs.getInt(2);
				String tenSanPham = rs.getNString(3);
				int soLuong = rs.getInt(4);
				double donGia = rs.getDouble(5);
				ChiTietHoaDon cthd = new ChiTietHoaDon(id, maSanPham, tenSanPham, soLuong, donGia);
				list.add(cthd);
			}
		}
		finally {
			stmt.close();
		}
		return list;
	}
	// ListMatHang = {MaSanPham, TenSanPham, DonGia, Soluong, ThanhTien}
	public boolean addMatHang(int maHD, List<List<String>> listMatHang) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = 	"INSERT INTO ChiTietHoaDon (MaSP, MaHD, SoLuong, DonGia) "
					+ 		 "VALUES ";
			int i = 1; 
			
			for(List<String> matHang: listMatHang) {
				sql = sql + "(?, " + maHD + ", ?, ?), ";
			}
			sql = sql.substring(0, sql.length() - 2);
			System.out.println(sql);
			stmt = this.conn.prepareStatement(sql);
			for(List<String> matHang: listMatHang) {
				stmt.setInt(i,  Integer.parseInt(matHang.get(0)));
				stmt.setInt(i + 1,  Integer.parseInt(matHang.get(3)));
				stmt.setDouble(i + 2,  Double.parseDouble(matHang.get(2)));
				i += 3;
			}
			int row = stmt.executeUpdate();
			if (row <= 0)	
				return false;
		}finally {
			stmt.close();
		}
		return true;
	}

}
