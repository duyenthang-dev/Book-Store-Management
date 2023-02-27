package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;

public class SanPhamDAO extends ConnectDB{
	
	public SanPhamDAO() throws SQLException {
		super();
		
	} 

    public ArrayList<SanPham> getAllSanPham() {
    	ArrayList<SanPham> dataList = new ArrayList<SanPham>();
     
        Statement stmt = null;
        try {

            String sql = "SELECT * "
            		+ "FROM SanPham sp "
            		+ "    JOIN LoaiSanPham lsp "
            		+ "        ON sp.MaLoai = lsp.MaLoai "
            		+ "    JOIN NhaCungCap ncc "
            		+ "        ON sp.MaNCC = ncc.MaNCC";
            stmt = this.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	int maSp = rs.getInt(1);
            	String tenSp = rs.getNString(4);
            	String donViTinh = rs.getString(7);
            	int soLuong = rs.getInt(6);
            	double giaSp = rs.getDouble(5);
            	double giaNhap = rs.getDouble(8);
            	LoaiSanPham loaiSanPham = new LoaiSanPham(rs.getInt(3), rs.getString(10), null);
            	NhaCungCap nhaCungCap = new NhaCungCap(rs.getInt(2), rs.getNString(12), rs.getNString(13), rs.getNString(14));
                SanPham sanPham = new SanPham(maSp, tenSp, donViTinh, soLuong, giaSp, giaNhap, loaiSanPham, nhaCungCap);
                dataList.add(sanPham);
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
    
    public ArrayList<SanPham> getListSanPhamByMaLoai(int maLoai) {
ArrayList<SanPham> dataList = new ArrayList<SanPham>();
	    
		PreparedStatement stmt = null;
        try { 
        	
            String sql = 	"SELECT * "
            		+ 		"FROM SanPham sp "
            		+ 		"	JOIN LoaiSanPham lsp "
            		+ 		"		ON sp.MaLoai = lsp.MaLoai "
            		+ 		"	JOIN NhaCungCap ncc "
            		+ 		"		ON sp.MaNCC = ncc.MaNCC "
            		+ 		"WHERE sp.MaLoai = ?";
            stmt = this.conn.prepareStatement(sql);
    		stmt.setInt(1, maLoai);
            ResultSet rs = stmt.executeQuery();
       
            while (rs.next()) {
          
            	int maSp = rs.getInt(1);
            	String tenSp = rs.getNString(4);
            	String donViTinh = rs.getString(7);
            	int soLuong = rs.getInt(6);
            	double giaSp = rs.getDouble(5);
            	double giaNhap = rs.getDouble(8);
            	LoaiSanPham loaiSanPham = new LoaiSanPham(rs.getInt(3), rs.getString(10), null);
            	NhaCungCap nhaCungCap = new NhaCungCap(rs.getInt(2), rs.getNString(12), rs.getNString(13), rs.getNString(14));
                SanPham sanPham = new SanPham(maSp, tenSp, donViTinh, soLuong, giaSp, giaNhap, loaiSanPham, nhaCungCap);
                dataList.add(sanPham);
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
    
    public SanPham getSanPham(int maSP) {
        PreparedStatement stmt = null;
        try {

        	String sql = 	"SELECT * "
            		+ 		"FROM SanPham sp "
            		+ 		"	JOIN LoaiSanPham lsp "
            		+ 		"		ON sp.MaLoai = lsp.MaLoai "
            		+ 		"	JOIN NhaCungCap ncc "
            		+ 		"		ON sp.MaNCC = ncc.MaNCC "
            		+ 		"WHERE sp.MaSP = ? ";
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, maSP);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            	return null;
            
            int maSp = rs.getInt(1);
        	String tenSp = rs.getNString(4);
        	String donViTinh = rs.getString(7);
        	int soLuong = rs.getInt(6);
        	double giaSp = rs.getDouble(5);
        	double giaNhap = rs.getDouble(8);
        	LoaiSanPham loaiSanPham = new LoaiSanPham(rs.getInt(3), rs.getString(10), null);
        	NhaCungCap nhaCungCap = new NhaCungCap(rs.getInt(2), rs.getNString(12), rs.getNString(13), rs.getNString(14));
            SanPham sanPham = new SanPham(maSp, tenSp, donViTinh, soLuong, giaSp, giaNhap, loaiSanPham, nhaCungCap);
            return sanPham;
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
    
    public int addSanPham(SanPham sp) throws SQLException {
    	PreparedStatement stmt = null;
    	try {
    		String sql = 	" INSERT INTO SanPham (MaNCC, MaLoai, TenSp, GiaSp, SoLuong, DonViTinh, GiaNhap) "
    				+ 		" VALUES "
    				+ 		" (?, ?, ?, ?, ?, ?, ?)";
    		stmt = this.conn.prepareStatement(sql);
    		stmt.setInt(1, sp.getNhaCungCap().getMaNCC());
    		stmt.setInt(2, sp.getLoaiSanPham().getMaLoai());
    		stmt.setNString(3, sp.getTenSp());
    		stmt.setDouble(4, sp.getGiaSp());
    		stmt.setInt(5, sp.getSoLuong());
    		stmt.setString(6, sp.getDonViTinh());
    		stmt.setDouble(7, sp.getGiaNhap());
    		
    		return stmt.executeUpdate();
    		
    	}finally {
			stmt.close();
		}
    }

	public int removeByID(int id) throws SQLException {
		
		PreparedStatement stmt = null;
    	try {
    		String sql = 	"DELETE FROM SanPham "
    				+ 		"WHERE MaSP = ?";
    		stmt = this.conn.prepareStatement(sql);
    		stmt.setInt(1, id);
    		return stmt.executeUpdate();
    		
    	}finally {
			stmt.close();
		}
	}

	public List<SanPham> findByProductName(String text) {
		ArrayList<SanPham> dataList = new ArrayList<SanPham>();
	    
		PreparedStatement stmt = null;
        try {
        	
            String sql = 	"SELECT * "
            		+ 		"FROM SanPham sp "
            		+ 		"	JOIN LoaiSanPham lsp "
            		+ 		"		ON sp.MaLoai = lsp.MaLoai "
            		+ 		"	JOIN NhaCungCap ncc "
            		+ 		"		ON sp.MaNCC = ncc.MaNCC "
            		+ 		"WHERE sp.TenSp LIKE CONCAT( '%',?,'%') ";
            stmt = this.conn.prepareStatement(sql);
    		stmt.setNString(1, text);
            ResultSet rs = stmt.executeQuery();
       
            while (rs.next()) {
          
            	int maSp = rs.getInt(1);
            	String tenSp = rs.getNString(4);
            	String donViTinh = rs.getString(7);
            	int soLuong = rs.getInt(6);
            	double giaSp = rs.getDouble(5);
            	double giaNhap = rs.getDouble(8);
            	LoaiSanPham loaiSanPham = new LoaiSanPham(rs.getInt(3), rs.getString(10), null);
            	NhaCungCap nhaCungCap = new NhaCungCap(rs.getInt(2), rs.getNString(12), rs.getNString(13), rs.getNString(14));
                SanPham sanPham = new SanPham(maSp, tenSp, donViTinh, soLuong, giaSp, giaNhap, loaiSanPham, nhaCungCap);
                dataList.add(sanPham);
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


}
