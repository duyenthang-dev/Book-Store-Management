package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO.ChiTietDonDatHangDAO;
import DAO.DonDatHangDao;
import DAO.KhachHangDAO;
import entity.ChiTietDonDatHang;
import entity.DonDatHang;
import entity.KhachHang;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GioHang_GUI extends JFrame {

	private KhachHang khachHang = null;
	private DonDatHang donDatHang = null;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private int numberOfItem = 5;
	public JMenuItem mntmQuanLy;
	public JMenuItem mntmGioHang;
	public JMenuItem mntmDangXuat;
	public JLabel lblHelp;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;

	protected JButton btnDatHang;

	protected JButton btnTroVe;

	private JPanel pnItems;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHang kh = new KhachHangDAO().getKhachHang(5);
					GioHang_GUI frame = new GioHang_GUI(kh);
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
	public GioHang_GUI() {
		GUI();
	}
	
	public GioHang_GUI(KhachHang khachHang) {
		this.khachHang = khachHang;
		GUI();
	}
	
	public void GUI() {
//		Giỏ hàng
		setTitle("Giỏ hàng");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		header
		contentPane.add(this.headerGUI(), BorderLayout.NORTH);
		
//		content
		JPanel panelContent = new JPanel();
		
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.X_AXIS));
		
		JScrollPane pane = new JScrollPane(panelContent);
		
		Component horizontalStrut = Box.createHorizontalStrut(30);
		panelContent.add(horizontalStrut);
		
		JPanel pnThongTinKhachHang = new JPanel();
		pnThongTinKhachHang.setBorder(new EmptyBorder(50, 0, 0, 0));
		panelContent.add(pnThongTinKhachHang);
		
		JPanel boxThongTin = new JPanel();
		pnThongTinKhachHang.add(boxThongTin);
		boxThongTin.setLayout(new BoxLayout(boxThongTin, BoxLayout.Y_AXIS));
		
		JPanel pnThongTinKH = new JPanel();
		boxThongTin.add(pnThongTinKH);
		
		JLabel lblThongTinKH = new JLabel("Thông tin khách hàng");
		lblThongTinKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnThongTinKH.add(lblThongTinKH);
		
		JPanel pnKH = new JPanel();
		FlowLayout fl_pnKH = (FlowLayout) pnKH.getLayout();
		fl_pnKH.setAlignment(FlowLayout.LEFT);
		boxThongTin.add(pnKH);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaKH.setPreferredSize(new Dimension(150, 20));
		pnKH.add(lblMaKH);
		
		txtMaKH = new JTextField(String.valueOf(this.khachHang.getMaKh()));
		txtMaKH.setEditable(false);
		txtMaKH.setPreferredSize(new Dimension(30, 20));
		pnKH.add(txtMaKH);
		txtMaKH.setColumns(20);
		
		JPanel pnTenKH = new JPanel();
		FlowLayout fl_pnTenKH = (FlowLayout) pnTenKH.getLayout();
		fl_pnTenKH.setAlignment(FlowLayout.LEFT);
		boxThongTin.add(pnTenKH);
		
		JLabel lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setPreferredSize(new Dimension(150, 20));
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenKH.add(lblTenKH);
		
		txtTenKH = new JTextField(this.khachHang.getHoTen());
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(20);
		pnTenKH.add(txtTenKH);
		
		JPanel pnSDT = new JPanel();
		FlowLayout fl_pnSDT = (FlowLayout) pnSDT.getLayout();
		fl_pnSDT.setAlignment(FlowLayout.LEFT);
		boxThongTin.add(pnSDT);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setPreferredSize(new Dimension(150, 20));
		lblSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSDT.add(lblSoDienThoai);
		
		txtSoDienThoai = new JTextField(this.khachHang.getSoDienThoai());
		txtSoDienThoai.setEditable(false);
		txtSoDienThoai.setColumns(20);
		pnSDT.add(txtSoDienThoai);
		
		JPanel pnDiaChi = new JPanel();
		FlowLayout fl_pnDiaChi = (FlowLayout) pnDiaChi.getLayout();
		fl_pnDiaChi.setAlignment(FlowLayout.LEFT);
		boxThongTin.add(pnDiaChi);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(new Dimension(150, 20));
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnDiaChi.add(lblDiaChi);
		
		txtDiaChi = new JTextField(this.khachHang.getDiaChi());
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(20);
		pnDiaChi.add(txtDiaChi);
		
		JPanel pnThongTin = new JPanel();
		boxThongTin.add(pnThongTin);
		pnThongTin.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnDatHang = new JButton("Đặt hàng", null);
		btnDatHang.setPreferredSize(new Dimension(150, 30));
		btnDatHang.setIconTextGap(8);
		btnDatHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatHang.setBackground(Color.WHITE);
		pnThongTin.add(btnDatHang);
		
		btnTroVe = new JButton("Tiếp tục mua hàng", null);
		btnTroVe.setPreferredSize(new Dimension(200, 30));
		btnTroVe.setIconTextGap(8);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTroVe.setBackground(Color.WHITE);
		pnThongTin.add(btnTroVe);
		
		JPanel panel = new JPanel();
		panelContent.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblGioHang = new JLabel("Giỏ hàng");
		lblGioHang.setBorder(new EmptyBorder(10, 0, 0, 0));
		lblGioHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblGioHang.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblGioHang.setForeground(new Color(0, 206, 209));
		panel.add(lblGioHang, BorderLayout.NORTH);
		
		JPanel boxGioHang = new JPanel();
		panel.add(boxGioHang, BorderLayout.CENTER);
		boxGioHang.setLayout(new BoxLayout(boxGioHang, BoxLayout.Y_AXIS));
		
		JPanel pnGioHang = new JPanel();
		pnGioHang.setPreferredSize(new Dimension(10, -500 + numberOfItem*200));
		boxGioHang.add(pnGioHang);
		
		pnItems = new JPanel();
		pnGioHang.add(pnItems);
		pnItems.setLayout(new GridLayout(0, 1, 0, 5));
		
		
				

		ImageIcon icon_dathang = new ImageIcon("data/images/shopping-cart32.png");
		

		ImageIcon icon_tieptucmuahang = new ImageIcon("data/images/cart.png");
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(30);
		panelContent.add(horizontalStrut_1);
		contentPane.add(pane);
	}
	
	public void renderData() {
		pnItems.removeAll();
		donDatHang = null;
		try {
			donDatHang = new DonDatHangDao().getDonDatHang(this.khachHang.getMaKh());
			if(donDatHang != null) {
				ArrayList<ChiTietDonDatHang> chiTietDDH = donDatHang.getChiTietDonDatHangs();
				chiTietDDH.forEach(chiTiet -> {
					pnItems.add(this.itemGUI(chiTiet));
				});
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JPanel headerGUI() {
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(255, 255, 255));
		panelHeader.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panelHeader.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Hiệu Sách NPB");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2.setAlignmentX(1.0f);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(20);
		
		JButton btnNewButton = new JButton("T\u00ECm ki\u1EBFm");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setPreferredSize(new Dimension(100, 19));
		panel_1.add(btnNewButton);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_3.setBackground(Color.WHITE);
		panelHeader.add(panel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_3.add(panel_2);
		
		ImageIcon icon_help = new ImageIcon("data/images/question.png");
		lblHelp = new JLabel("trợ giúp        ");
		lblHelp.setIcon(icon_help);
		panel_2.add(lblHelp);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_3.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Xin chào: Trần Văn Nhân");
		menuBar.add(mnNewMenu);
		
		mntmGioHang = new JMenuItem("Giỏ hàng");
		mnNewMenu.add(mntmGioHang);
		
		mntmDangXuat = new JMenuItem("Đăng xuất");
		mnNewMenu.add(mntmDangXuat);
		
		return panelHeader;
	}
	
	public JPanel itemGUI(ChiTietDonDatHang chiTietDDH) {
		JPanel pnItem = new JPanel();
		pnItem.setPreferredSize(new Dimension(700, 100));
		pnItem.setBackground(Color.WHITE);
		pnItem.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		pnItem.add(panel_2_1);
		panel_2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbIcon = new JLabel();
		lbIcon.setAlignmentX(0.5f);
		panel_2_1.add(lbIcon);
		ImageIcon imageProduct = new ImageIcon("data/product/default.png");
		imageProduct = TrangChu_GUI.resizeIcon(imageProduct, new Dimension(150, 90));
		lbIcon.setIcon(imageProduct);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		pnItem.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel(chiTietDDH.getSanPham().getTenSp());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_1);
		
		JPanel pnDonGia = new JPanel();
		pnDonGia.setBackground(Color.WHITE);
		panel_3.add(pnDonGia);
		pnDonGia.setLayout(new BoxLayout(pnDonGia, BoxLayout.X_AXIS));
		
		JLabel lblDonGia = new JLabel("Đơn giá:    ");
		lblDonGia.setBackground(Color.WHITE);
		pnDonGia.add(lblDonGia);
		
		JLabel donGia = new JLabel(String.valueOf(chiTietDDH.getDonGia()));
//		lblTongTien.setForeground(new Color(0, 206, 209));
		donGia.setBackground(Color.WHITE);
		pnDonGia.add(donGia);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel_3.add(panel_1_1);
		panel_1_1.setLayout(new BoxLayout(panel_1_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_3 = new JLabel("Số lượng:  ");
		panel_1_1.add(lblNewLabel_3);
		
		JTextField txtSoLuong = new JTextField();
		txtSoLuong.setText(String.valueOf(chiTietDDH.getSoLuong()));
		txtSoLuong.setColumns(10);
		panel_1_1.add(txtSoLuong);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4 = new JLabel("Tổng tiền:  ");
		lblNewLabel_4.setBackground(Color.WHITE);
		panel_4.add(lblNewLabel_4);
		
		JLabel lblTongTien = new JLabel(String.valueOf(chiTietDDH.getDonGia()*chiTietDDH.getSoLuong()));
		lblTongTien.setForeground(new Color(0, 206, 209));
		lblTongTien.setBackground(Color.WHITE);
		panel_4.add(lblTongTien);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		pnItem.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		pnItem.add(panel_6);
		


		ImageIcon icon_delete = new ImageIcon("data/images/trash.png");
		JButton btnXoa = new JButton("Xóa", icon_delete);
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnXoa.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_6.add(btnXoa);
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn xóa ?");
				System.out.println(choose);
				
				if(choose == 0) {
					try {
						boolean kq = new ChiTietDonDatHangDAO().xoaChiTietDonDatHang(chiTietDDH.getSanPham().getMaSp(), donDatHang.getMaDDH());
						if(kq == false) {
							JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
							return;
						}
						
						pnItems.remove(pnItem);
						pnItems.revalidate();
						pnItems.repaint();
	//					pnItem.setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		txtSoLuong.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(txtSoLuong.getText());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return pnItem;
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
}
