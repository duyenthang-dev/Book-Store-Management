package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.*;

import ConnectDB.ConnectDB;

public class NhaCungCapDAO extends ConnectDB {

	public NhaCungCapDAO() throws SQLException {
		super();
	}
	
	public List<NhaCungCap> getAllNhaCungCap() throws SQLException {
		List<NhaCungCap> list = new ArrayList<NhaCungCap>();
		 Statement stmt = null;
		 try {
			 String sql = "SELECT * FROM NhaCungCap";
			 stmt = this.conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next()) {
				 int maNCC = rs.getInt(1);
				 String tenNCC = rs.getNString(2);
				 String diaChi = rs.getNString(3);
				 String soDienThoai = rs.getString(4);
				 list.add(new NhaCungCap(maNCC, tenNCC, diaChi, soDienThoai));
			 }
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
			 throw e;
		 } finally {
			 stmt.close();
		}
		return list;
	}
	
	public List<String> getAllTenNhaCungCap() throws SQLException {
		List<String> list = new ArrayList<String>();
		 Statement stmt = null;
		 try {
			 String sql = "SELECT TenNCC FROM NhaCungCap";
			 stmt = this.conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next()) {
				 list.add(rs.getNString(1));
			 }
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
			 throw e;
		 } finally {
			 stmt.close();
		}
		return list;
	}

}
