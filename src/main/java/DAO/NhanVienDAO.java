package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import ConnectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVienDAO extends ConnectDB{

	public NhanVienDAO() throws SQLException {
		super();
	}
	
//	lấy thông tin nhân viên bằng mã tài khoản
	public NhanVien getNhanVienByMaTK(int taiKhoanID) {
        PreparedStatement stmt = null;
        try { 

            String sql = "SELECT * FROM dbo.NhanVien where taiKhoanID = ?";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, taiKhoanID);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            	return null;
            int maNV = rs.getInt(1);
            int taiKhoanId = rs.getInt(2);
            String tenNV = rs.getNString(3);
            String sdt = rs.getString(4);
            String diachi = rs.getNString(5);
            String email = rs.getString(6);
            
            NhanVien nv = new NhanVien(maNV, tenNV, sdt, diachi, taiKhoanId, email);
            return nv;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

	/**
	 * Lấy danh sách nhân viên từ DB
	 * @return danh sách nhân viên
	 * @throws SQLException 
	 * */
	public List<NhanVien> getListNhanVien() throws SQLException {
		List<NhanVien> dataList = new ArrayList<NhanVien>();
		Statement stmt = null;
		try {
			String sql = "SELECT * "
					+ 		"FROM NhanVien nv "
					+ 		"    JOIN TaiKhoan tk "
					+ 		"        ON nv.TaiKhoanID = tk.ID ";
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int maNV = rs.getInt(1);
				String tenNV = rs.getNString(3);
				String phone = rs.getString(4);
				String diachi = rs.getNString(5);
				String email = rs.getString(6);
				int tkId = rs.getInt(7);
				String taikhoan = rs.getString(8);
				String matkhau = rs.getString(9);
				dataList.add(new NhanVien(maNV, tenNV, phone, diachi, new TaiKhoan(tkId, taikhoan, matkhau, 0), email));
			}
			
		}finally {
			stmt.close();
		}
		return dataList;
	}

	/**
	 * Thêm mới nhân viên, đồng thời tạo tài khoản cho nhân viên
	 * @return nothing
	 * @param nhanVien: thông tin nhân viên lấy từ form giao diện thêm nhân viên
	 * @throws SQLException 
	 * */
	public int addNhanVien(NhanVien nhanVien) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = 	"INSERT INTO TaiKhoan (TaiKhoan, MatKhau, Role) "
					+ 		"VALUES (?, ?, 1); "
					+ 		"DECLARE @taiKhoanID INT = SCOPE_IDENTITY(); "
					+ 		"INSERT INTO NhanVien (TaiKhoanID, TenNv, SoDienThoai, DiaChi, Email) "
					+ 		"VALUES (@taiKhoanID, ?, ?, ?, ?);";
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, nhanVien.getTaiKhoan().getTaiKhoan());
			stmt.setString(2, nhanVien.getTaiKhoan().getMatKhau());
			stmt.setNString(3, nhanVien.getTenNv());
			stmt.setString(4, nhanVien.getSoDienThoai());
			stmt.setNString(5, nhanVien.getDiaChi());
			stmt.setString(6, nhanVien.getEmail());
			return stmt.executeUpdate();
			
		}finally {
			stmt.close();
		}
		
	}
	
	/**
	 * Lấy thông tin nhân viên theo mã
	 * @return Thông tin nhân viên, bao gồm cả tài khoản mật khẩu
	 * @param id mã số nhân viên
	 * @throws SQLException 
	 * */
	public NhanVien getNhanVienByID(int id) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = 	"SELECT * "
					+ 		"FROM NhanVien nv "
					+ 		"    JOIN TaiKhoan tk "
					+ 		"        ON nv.TaiKhoanID = tk.ID "
					+ 		"WHERE nv.MaNv = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int maNV = rs.getInt(1);
				int maTK = rs.getInt(2);
				String tenNV = rs.getNString(3);
				String phone = rs.getString(4);
				String diachi = rs.getNString(5);
				String email = rs.getString(6);
				String taikhoan = rs.getString(8);
				String matkhau = rs.getString(9);
				int role = rs.getInt(10);
				return new NhanVien(maNV, tenNV, phone, diachi, new TaiKhoan(taikhoan, matkhau, role), email);
			}
			
			
		}finally {
			stmt.close();
		}
		return null;
		
	}

	/**
	 * Xoá nhân viên và tài khoản tương ứng
	 * @return số nhân viên bị xoá
	 * @param id của tài khoản cần xoá
	 * @throws SQLException 
	 * */
	public int deleteNhanVien(int taikhoanId) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM TaiKhoan WHERE ID = ?";
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, taikhoanId);
			return stmt.executeUpdate();
			
		}finally {
			stmt.close();
		}
	}

	/**
	 * Tìm kiếm thông tin theo tên
	 * @return Thông tin những nhân viên có tên chứa từ khoá cần tìm
	 * @param text: Từ khoá cần tìm
	 * @throws SQLException 
	 * */
	public List<NhanVien> findNhanVienByName(String text) throws SQLException {
		
		PreparedStatement stmt = null;
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			String sql = 	"SELECT * "
					+ 		"FROM NhanVien nv "
					+ 		"    JOIN TaiKhoan tk "
					+ 		"        ON tk.ID = nv.TaiKhoanID "
					+ 		"WHERE nv.TenNv LIKE CONCAT( '%',?,'%')";
			stmt = this.conn.prepareStatement(sql);
			stmt.setNString(1, text);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maNV = rs.getInt(1);
				int maTK = rs.getInt(2);
				String tenNV = rs.getNString(3);
				String phone = rs.getString(4);
				String diachi = rs.getNString(5);
				String email = rs.getString(6);
				String taikhoan = rs.getString(8);
				String matkhau = rs.getString(9);
				int role = rs.getInt(10);
				list.add(new NhanVien(maNV, tenNV, phone, diachi, new TaiKhoan(taikhoan, matkhau, role), email));
			}
		}finally {
			stmt.close();
		}
		return list;
		
	}


}
