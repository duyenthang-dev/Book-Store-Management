package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.LoaiSanPham;
import entity.SanPham;

public class LoaiSanPhamDAO extends ConnectDB{
	
	public LoaiSanPhamDAO() throws SQLException {
		super();
		
	}
	
	public ArrayList<LoaiSanPham> getDanhSachLoaiSanPham() throws SQLException {
		ArrayList<LoaiSanPham> dataList = new ArrayList<LoaiSanPham>();
        Statement stmt = this.conn.createStatement();
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        
        try {

            String sql = "SELECT * FROM dbo.LoaiSanPham";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.println(rs);
//            	printResultSet(rs);
            	LoaiSanPham loaiSp = new LoaiSanPham(rs);
            	loaiSp.setSanPhams(sanPhamDao.getListSanPhamByMaLoai(rs.getInt("maLoai")));
                dataList.add(loaiSp);
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
	
	public ArrayList<String> getAllTenLoaiSanPham() throws SQLException {
		ArrayList<String> dataList = new ArrayList<String>();
        Statement stmt = this.conn.createStatement();
   
        try {

            String sql = "SELECT TenLoai FROM LoaiSanPham";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dataList.add(rs.getNString(1));
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
