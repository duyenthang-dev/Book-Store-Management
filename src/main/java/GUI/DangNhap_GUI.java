package GUI;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import DAO.TaiKhoanDAO;
import util.Placeholder;

import javax.swing.SwingConstants;

public class DangNhap_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	public JButton btnDangNhap;
	public JButton btnDangKy;
	private JLabel lblMsg;
	private JCheckBox chcHienThiMatKhau;

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap_GUI frame = new DangNhap_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhap_GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		
		contentPane = 
//				new JPanel(); 
				TrangChu_GUI.panelBackgroundImage("/images/bg3.jpg");
		
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(424, 176, 479, 265);
		panel.setBackground(new Color(0, 0, 0, 150));
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblDangNhap = new JLabel("\u0110\u0103ng Nh\u1EADp");
		lblDangNhap.setBounds(144, 11, 198, 49);
		panel.add(lblDangNhap);
		lblDangNhap.setForeground(new Color(0, 206, 209));
		lblDangNhap.setBackground(new Color(255, 255, 255));
		lblDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		txtUserName = new JTextField("T??i kho???n");
		txtUserName.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtUserName.setBounds(41, 77, 398, 41);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		new Placeholder().placeholder(txtUserName, "T??i kho???n");
		
		txtPassword = new JPasswordField("M???t kh???u");
		txtPassword.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtPassword.setEchoChar((char)0);
		txtPassword.setBounds(41, 129, 398, 41);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		new Placeholder().placeholder(txtPassword, "M???t kh???u");
		
		btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDangNhap.setBackground(new Color(0, 206, 209));
		btnDangNhap.setBounds(105, 207, 130, 41);
		panel.add(btnDangNhap);
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnDangKy = new JButton("????ng k??");
		btnDangKy.setBackground(Color.WHITE);
		btnDangKy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDangKy.setBounds(259, 207, 130, 41);
		panel.add(btnDangKy);
		
		chcHienThiMatKhau = new JCheckBox("Hi???n th??? m???t kh???u");
		chcHienThiMatKhau.setBounds(315, 177, 124, 23);
		chcHienThiMatKhau.setForeground(Color.WHITE);
		chcHienThiMatKhau.setBackground(Color.BLACK);
		panel.add(chcHienThiMatKhau);
		
		chcHienThiMatKhau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtPassword.getText().equals("M???t kh???u")) {
					txtPassword.setEchoChar((char)0);	
					chcHienThiMatKhau.setSelected(false);
					return ;
				}
				
				if(chcHienThiMatKhau.isSelected()) {
		        	txtPassword.setEchoChar((char)0);
				}else {
					txtPassword.setEchoChar('*');
				}
			}
			
		});
	}
	
	public boolean checkPassword() throws SQLException {
		TaiKhoanDAO taiKhoanDao = new TaiKhoanDAO();
		String matKhau = taiKhoanDao.getMatKhau(txtUserName.getText());
		if(txtPassword.getText().equals(matKhau)) {
			
			return true;
		}
		
		renderError("T??i kho???n ho???c m???t kh???u kh??ng ch??nh x??c");	
		return false;
	}
	
	public void renderError(String message){
        JOptionPane.showMessageDialog(contentPane, message);
    }
	
	
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
	
	public void requestFocus() {
		this.txtUserName.requestFocus();
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}
	
	
}
