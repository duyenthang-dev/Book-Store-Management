package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale.Category;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import entity.*;
import util.NotificationUtils;
import DAO.LoaiSanPhamDAO;
import DAO.NhaCungCapDAO;
import DAO.SanPhamDAO;

public class SanPham_GUI extends JFrame {
	private static final int INPUT_HEIGHT = 30;
	private static final int BUTTON_HEIGHT = 30;
	private static final int INPUT_WIDTH = 100;
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua;
	String[] cols = { "Mã sản phẩm", "Tên sản phẩm", "Nhà cung cấp", "Loại", "Số lượng", "Gía nhập", "Gía bán", "Đơn vị"};
	private DefaultTableModel model;
	private JTable table;
	private JButton btnTaoMoi, btnDangxuat, btnThoat;
	private JTextField txtmaSP;
	private JTextField txttenSP;
	private JTextField txtSoluong;
	private JTextField txtDonvi;
	private JTextField txtGianhap;
	private JTextField txtGiaban;
	private JLabel lblTimKiem;
	private JTextField txtTimKiem;
	private JRadioButton radMaLoai; 
	private JRadioButton radMaSanPham;
	private JRadioButton radTenSanPham;
	private static JComboBox<String> cboListMaloai;
	private static JComboBox<String> cbolistNhaCungCap;
	
	private NhaCungCapDAO nhaCungCapDAO;
	private LoaiSanPhamDAO loaiSanPhamDAO;
	private SanPhamDAO sanPhamDAO;
	private List<SanPham> listSanPham;
	private List<NhaCungCap> listNhaCungCap;
	private List<LoaiSanPham> listLoaiSanPham;
	
	public void renderSanPham(List<SanPham> list) {
		model.setRowCount(0);
		for(SanPham sp: list) {
			Object[] temp = {sp.getMaSp(), sp.getTenSp(),sp.getNhaCungCap().getTenNCC(), sp.getLoaiSanPham().getTenLoai(),
					sp.getSoLuong(), sp.getGiaNhap(), sp.getGiaSp(), sp.getDonViTinh()};
			model.addRow(temp);
		}
	 
	}
	
	@SuppressWarnings("serial")
	public SanPham_GUI() throws SQLException {
		sanPhamDAO = new SanPhamDAO();
		nhaCungCapDAO = new NhaCungCapDAO();
		listSanPham = sanPhamDAO.getAllSanPham();
		listNhaCungCap = nhaCungCapDAO.getAllNhaCungCap();
		loaiSanPhamDAO = new LoaiSanPhamDAO();
		listLoaiSanPham = loaiSanPhamDAO.getDanhSachLoaiSanPham();
		
		setTitle("Quản Lý Sản Phẩm");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 720);

		ImageIcon icon1 = new ImageIcon("data/images/sanpham.png");
		ImageIcon icon2 = new ImageIcon("data/images/blueAdd_16.png");
		ImageIcon icon3 = new ImageIcon("data/images/trash.png");
		ImageIcon icon4 = new ImageIcon("data/images/repairing-service.png");
		ImageIcon icon5 = new ImageIcon("data/images/refresh.png");
		ImageIcon icon6 = new ImageIcon("data/images/timkiem.png");
		ImageIcon icon7 = new ImageIcon("data/images/search_16.png");

		JLabel jLabel1 = new JLabel();
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.SystemColor.activeCaption);
        jLabel1.setIcon(icon1); // NOI18N
        jLabel1.setText("THÔNG TIN SẢN PHẨM");

		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBounds(0, 0, 1300, 720);
		

		JLabel lbTitle = new JLabel("Quản Lý Sản Phẩm");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbTitle.setForeground(Color.BLACK);
		lbTitle.setBounds(500, 10, 500, 35);

		JLabel lblmaSP = new JLabel("Mã sản phẩm: ");
		JLabel lbltenSP = new JLabel("Tên sản phẩm: ");
		JLabel lblSoluong = new JLabel("Số lượng:");
		JLabel lblDonvi = new JLabel("Đơn vị tính: ");
		JLabel lblGianhap = new JLabel("Giá nhập: ");
		JLabel lblGiaban = new JLabel("Giá bán: ");
		JLabel lblMaloai = new JLabel("Mã loại: ");
		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		btnThem = new JButton();
		btnThem.setText("Thêm");
		btnThem.setIcon(icon2);
        btnXoa = new JButton();
        btnXoa.setIcon(icon3); // NOI18N
        btnXoa.setText("Xóa");
        btnSua = new JButton();
        btnSua.setIcon(icon4); // NOI18N
        btnSua.setText("Sửa");
        btnTaoMoi = new JButton();
        btnTaoMoi.setIcon(icon5); // NOI18N
        btnTaoMoi.setText("Tạo Mới");
        lblTimKiem = new JLabel();
        lblTimKiem.setIcon(icon6); // NOI18N
        lblTimKiem.setText("Search: ");
        txtTimKiem = new JTextField();
        btnTim = new JButton();
        btnTim.setText("Tìm");
		txtmaSP = new JTextField();
		txttenSP = new JTextField();
		txtSoluong = new JTextField();
		txtDonvi = new JTextField();
		txtGianhap = new JTextField();
		txtGiaban = new JTextField();
		cboListMaloai = new JComboBox<String>();
		cbolistNhaCungCap = new JComboBox<String>();

		JPanel pnThongtin = new JPanel();
		pnThongtin.setLayout(null);
		pnThongtin.setBounds(220, 50, 800, 320);
		pnThongtin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
//		pnThongtin.setBorder(new TitledBorder(
//				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
//				"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		//pnThongtin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jLabel1.setBounds(20,10,500,30);
		lblmaSP.setBounds(40, 50, 100, INPUT_HEIGHT);
		txtmaSP.setBounds(130, 50, 220, INPUT_HEIGHT);
		txtmaSP.setEditable(false);
		txtmaSP.setBackground(new Color(216, 220, 220));
		txtmaSP.setCursor(null);
		lbltenSP.setBounds(430, 50, 100, INPUT_HEIGHT);
		txttenSP.setBounds(530, 50, 220, INPUT_HEIGHT);
		lblSoluong.setBounds(40, 85, 100, INPUT_HEIGHT);
		txtSoluong.setBounds(130, 85, 220, INPUT_HEIGHT);
		lblDonvi.setBounds(430, 85, 100, INPUT_HEIGHT);
		txtDonvi.setBounds(530, 85, 220, INPUT_HEIGHT);
		lblGianhap.setBounds(40, 120, 110, INPUT_HEIGHT);
		txtGianhap.setBounds(130, 120, 220, INPUT_HEIGHT);
		lblGiaban.setBounds(430, 120, 100, INPUT_HEIGHT);
		txtGiaban.setBounds(530, 120, 220, INPUT_HEIGHT);
		lblMaloai.setBounds(430, 155, 100, INPUT_HEIGHT);
		lblNhaCungCap.setBounds(40, 155, 100, INPUT_HEIGHT);
		cbolistNhaCungCap.setBounds(130, 155, 220, INPUT_HEIGHT);
		
		
		cbolistNhaCungCap.setModel(new DefaultComboBoxModel<>(nhaCungCapDAO.getAllTenNhaCungCap().toArray(String[]::new)));
		cboListMaloai.setBounds(530, 155, 220, INPUT_HEIGHT);
		cboListMaloai.setModel(new DefaultComboBoxModel<>(loaiSanPhamDAO.getAllTenLoaiSanPham().toArray(String[]::new)));
		
		radMaSanPham = new JRadioButton("Mã Sản Phẩm");
        radMaSanPham.setFont(new Font("Arial", Font.BOLD, 14));
        radTenSanPham = new JRadioButton("Tên Sản Phẩm");
        radTenSanPham.setFont(new Font("Arial", Font.BOLD, 14)); 
		radMaLoai = new JRadioButton("Mã Loại", true);
		radMaLoai.setFont(new Font("Arial", Font.BOLD, 14));
        

        ButtonGroup btnGr = new ButtonGroup();
        btnGr.add(radMaSanPham);
        btnGr.add(radTenSanPham);
        btnGr.add(radMaLoai);
		
		btnThem.setBounds(250,200,120,BUTTON_HEIGHT);
		btnXoa.setBounds(380,200,120,BUTTON_HEIGHT);
		btnSua.setBounds(250,235,120,BUTTON_HEIGHT);
		btnTaoMoi.setBounds(380,235,120,BUTTON_HEIGHT);
		lblTimKiem.setBounds(30, 270, 150, 40);
		txtTimKiem.setBounds(120, 270, 200, BUTTON_HEIGHT);
		radMaSanPham.setBounds(330,270,120,BUTTON_HEIGHT);
		radTenSanPham.setBounds(450,270,130,BUTTON_HEIGHT);
		radMaLoai.setBounds(580,270,90,BUTTON_HEIGHT);
		
		btnTim.setBounds(670, 270, 80, BUTTON_HEIGHT);
		btnTim.setIcon(icon7);
		
		pnThongtin.add(jLabel1);
		pnThongtin.add(lblmaSP);
		pnThongtin.add(txtmaSP);
		pnThongtin.add(lbltenSP);
		pnThongtin.add(txttenSP);
		pnThongtin.add(lblSoluong);
		pnThongtin.add(txtSoluong);
		pnThongtin.add(lblDonvi);
		pnThongtin.add(txtDonvi);
		pnThongtin.add(lblGianhap);
		pnThongtin.add(txtGianhap);
		pnThongtin.add(lblGiaban);
		pnThongtin.add(txtGiaban);
		pnThongtin.add(lblMaloai);
		pnThongtin.add(cboListMaloai);
		pnThongtin.add(lblNhaCungCap);
		pnThongtin.add(cbolistNhaCungCap);
		pnThongtin.add(btnThem);
		pnThongtin.add(btnXoa);
		pnThongtin.add(btnSua);
		pnThongtin.add(btnTaoMoi);
		pnThongtin.add(lblTimKiem);
		pnThongtin.add(txtTimKiem);
		pnThongtin.add(radMaSanPham);
		pnThongtin.add(radTenSanPham);
		pnThongtin.add(radMaLoai);
		pnThongtin.add(btnTim);
//		pnThongtin.add(txtgiaXe);

		// int widthLb = 85, widthPn = 700, widthBtn = 70, h = 25, hTxt = 25, xTxt =
		// 105;

		JPanel pnTable = new JPanel();
		pnTable.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnTable.setLayout(null);
		model = new DefaultTableModel(cols, 0) {
			// khóa không cho người dùng nhập trên table
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		table = new JTable(model);
		
		JScrollPane sql = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTable.add(sql);
		pnTable.setBounds(20, 380, 1255, 270);
		sql.setBounds(10, 20, 1230, 245);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		pnMain.add(lbTitle);
		pnMain.add(pnThongtin);
		pnMain.add(pnTable);
		//pnMain.add(pnChucnang);

		getContentPane().add(pnMain);
//		table.addMouseListener(this);
		
		/*================================= EVENT HANDLER ==================================*/
		btnThem.addActionListener((e) -> {
        	try {
        		int maSp = Integer.parseInt(txtmaSP.getText());
            	String tenSp = txttenSP.getText();
            	String donViTinh = txtDonvi.getText();
            	int soLuong = Integer.parseInt(txtSoluong.getText());
            	double giaSp = Double.parseDouble(txtGiaban.getText());
            	double giaNhap = Double.parseDouble(txtGianhap.getText());
            	NhaCungCap ncc = listNhaCungCap.get(cbolistNhaCungCap.getSelectedIndex());
            	LoaiSanPham lsp = listLoaiSanPham.get(cboListMaloai.getSelectedIndex());
				int row = sanPhamDAO.addSanPham(new SanPham(maSp, tenSp, donViTinh, soLuong, giaSp, giaNhap, lsp, ncc));
				if (row == 1) {
					NotificationUtils.createNotification("Success", "Đã thêm Sản phẩm");
					renderSanPham(sanPhamDAO.getAllSanPham());
					resetInput();
					
				}
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				NotificationUtils.createNotification("Error", "Dữ liệu nhập vào không hợp lệ! Hãy đảm bảo các trường dữ liệu"
						+ " là không null và đúng kiểu dữ liệu");
			}
        	catch (SQLException e2) {
        		e2.printStackTrace();
				NotificationUtils.createNotification("Error", "Có lỗi trong quá trình truy vấn dữ liệu");
			}
		});
		
		btnTaoMoi.addActionListener((e) -> {
			resetInput();
		});
		
		btnXoa.addActionListener(e -> {
			try {
				int row = table.getSelectedRow();
				if (row == -1) {
					NotificationUtils.createNotification("Warning", "Vui lòng chọn hàng cần xoá");
					return;
				}
				else {
					int id = (int) table.getValueAt(row, 0);
					int rowEffected = sanPhamDAO.removeByID(id);
					if (rowEffected != 0) {
						NotificationUtils.createNotification("Success", "Đã Xoá Sản phẩm");
						renderSanPham(sanPhamDAO.getAllSanPham());
						resetInput();
					}
					else {
						NotificationUtils.createNotification("Error", "Xoá sản phẩm không thành công");
					}
				}
			}catch (SQLException e2) {
				e2.printStackTrace();
				NotificationUtils.createNotification("Error", "Có lỗi trong quá trình truy vấn dữ liệu");
			}
			
		});
		
		// chọn 1 hàng trong table -> đổ dữ liệu về form (dùng cho chức năng chỉnh sửa)
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Xử lý hành động chọn 1 hàng
				int viewRow = table.getSelectedRow();
				if (!e.getValueIsAdjusting() && viewRow != -1) {
					txtmaSP.setText(table.getValueAt(viewRow, 0).toString());
					txttenSP.setText((String)table.getValueAt(viewRow, 1));
					cbolistNhaCungCap.setSelectedItem(table.getValueAt(viewRow, 2));
					cboListMaloai.setSelectedItem(table.getValueAt(viewRow, 3));
					txtSoluong.setText(table.getValueAt(viewRow, 4).toString());
					txtGianhap.setText(table.getValueAt(viewRow, 5).toString());
					txtGiaban.setText(table.getValueAt(viewRow, 5).toString());
					txtDonvi.setText((String)table.getValueAt(viewRow, 7));
				}
				
			}
		});
		
		btnTim.addActionListener(e -> {
			String text = txtTimKiem.getText();
			if (text == null || text.length() == 0) {
				NotificationUtils.createNotification("Warning", "Chưa nhập từ khoá cần tìm");
				return;
			}
			List<SanPham> result = new ArrayList<SanPham>();
			try {
				if (radTenSanPham.isSelected()) {
					result = sanPhamDAO.findByProductName(text);
				}
				else if (radMaSanPham.isSelected()) {
					result.add(sanPhamDAO.getSanPham(Integer.parseInt(text)));
					
					
				}
				else if (radMaLoai.isSelected()) {
					result = sanPhamDAO.getListSanPhamByMaLoai(Integer.parseInt(text));
				}
				renderSanPham(result);
			}catch(NumberFormatException e1) {
				e1.printStackTrace();
				NotificationUtils.createNotification("Error", "Dữ liệu nhập vào không hợp lệ! Hãy đảm bảo các trường dữ liệu"
						+ " là không null và đúng kiểu dữ liệu");
			}
			
		});

	}
 
	private void resetInput() {
		txtmaSP.setText("");
		txtDonvi.setText("");
		txtGiaban.setText("");
		txtGianhap.setText("");
		txtSoluong.setText("");
//		txtTim.setText("");
		txtTimKiem.setText("");
		txttenSP.setText("");
		table.getSelectionModel().clearSelection();
		cboListMaloai.setSelectedIndex(0);
		cbolistNhaCungCap.setSelectedIndex(0);
	}

	public void init() {
		renderSanPham(listSanPham);
	}
}
