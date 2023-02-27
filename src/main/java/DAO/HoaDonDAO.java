package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.SanPham;

public class HoaDonDAO extends ConnectDB{

	public HoaDonDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public synchronized int addHoaDon(HoaDon hoaDon) {
    	PreparedStatement stmt = null;
    	String generatedColumns[] = { "MaHD" };
        try {
            String sql = 	"INSERT INTO HoaDon (MaNV, MaKH, TongTien, NgayMua) "
            		+ 		"VALUES (?, ?, ?, ?)"; 
            stmt = this.conn.prepareStatement(sql, generatedColumns);
            stmt.setInt(1, hoaDon.getNhanVien().getMaNv());
            stmt.setInt(2, hoaDon.getKhachHang().getMaKh());
            stmt.setDouble(3, hoaDon.getTongTien());
            stmt.setDate(4, hoaDon.getNgayMua());
            int affectedRows = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
//            
            if (rs.next())
            	return rs.getInt(1);
//                
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

	public List<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> dataList = new ArrayList<HoaDon>();
	     
        Statement stmt = null;
        try {

            String sql = 	"SELECT * "
            		+ 		"FROM HoaDon hd "
            		+ 		"	JOIN KhachHang kh ON hd.MaKH = kh.MaKH "
            		+ 		"	JOIN NhanVien nv ON nv.MaNv = hd.MaNV";
            stmt = this.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	int maHD = rs.getInt(1);
            	int maNV = rs.getInt(2);
            	int maKH = rs.getInt(3);
            	double tongTien = rs.getDouble(4);
            	java.sql.Date ngayMua = rs.getDate(5);
            	
            	String hotenKH = rs.getNString(7);
            	String diachiKH = rs.getNString(8);
            	int tkKhachHangId = rs.getInt(9);
            	String phoneKH = rs.getString(10);
            	
            	int tkNhanVienId = rs.getInt(12);
            	String tenNV = rs.getNString(13);
            	String phoneNV = rs.getString(14);
            	String diachiNV = rs.getString(15);
            	String email = rs.getString(16);
            	
            	NhanVien nv = new NhanVien(maNV, tenNV, phoneNV, diachiNV, tkNhanVienId, email);
            	KhachHang kh = new KhachHang(maKH, hotenKH, phoneKH, diachiKH);
            	dataList.add(new HoaDon(maHD, tongTien, ngayMua, nv, kh));
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
	}

	public int getMaHd() throws SQLException {
		Statement stmt = null;
		try { 
			String sql = 	"SELECT TOP (1) MaHD "
					+ 		"FROM HoaDon "
					+ 		"ORDER BY MaHD DESC";
			stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.getInt(1);
		}finally {
			stmt.close();
		}	
	}

	public int deleteHoaDon(int maHD) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = 	"DELETE FROM HoaDon "
					+ 		"WHERE MaHD = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, maHD);
			return stmt.executeUpdate();
			
		}
		finally {
			stmt.close();
		}
	}

	public HoaDon getHoaDonById(int maHD) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = 	"SELECT * "
					+ 		"FROM HoaDon hd "
					+ 		"    JOIN KhachHang kh "
					+ 		"        ON hd.MaKH = kh.MaKH "
					+ 		"    JOIN NhanVien nv "
					+ 		"        ON nv.MaNv = hd.MaNV "
					+ 		"WHERE hd.MaHD = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, maHD);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	
            	int maNV = rs.getInt(2);
            	int maKH = rs.getInt(3);
            	double tongTien = rs.getDouble(4);
            	java.sql.Date ngayMua = rs.getDate(5);
            	
            	String hotenKH = rs.getNString(7);
            	String diachiKH = rs.getNString(8);
            	String phoneKH = rs.getString(10);
            	
            	int tkNhanVienId = rs.getInt(12);
            	String tenNV = rs.getNString(13);
            	String phoneNV = rs.getString(14);
            	String diachiNV = rs.getString(15);
            	String email = rs.getString(16);
            	
            	NhanVien nv = new NhanVien(maNV, tenNV, phoneNV, diachiNV, tkNhanVienId, email);
            	KhachHang kh = new KhachHang(maKH, hotenKH, phoneKH, diachiKH);
            	return new HoaDon(maHD, tongTien, ngayMua, nv, kh);
            }
		}finally {
			stmt.close();
		}
		return null;
	}

}
