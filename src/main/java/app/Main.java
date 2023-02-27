package app;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import DAO.KhachHangDAO;
import GUI.QuanLy_GUI;

public class Main{
	
	public static void main(String[] args) throws SQLException {
		
		EventQueue.invokeLater(new Runnable() {
			@Override 
			public void run() {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						try {
							UIManager.setLookAndFeel(info.getClassName());
							QuanLy_GUI quanLyHieuSach = new QuanLy_GUI();
							KhachHangDAO khachHangDao = new KhachHangDAO();
							khachHangDao.getListKhachHang();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}

				
			}
		});
		
		 
	}
}
