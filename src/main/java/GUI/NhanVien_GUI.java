package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

import DAO.NhanVienDAO;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import util.NotificationUtils;
import util.Placeholder;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.ScrollPaneConstants;

public class NhanVien_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	String[] cols_dskh = { "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Địa chỉ" };
	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable table;
	private JTextField txtMaNv;
	private JTextField txtTenNv;
	private JTextField txtEmail;
	private JTextField txtSdt;
	private JTextField txtDiaChi;
	private JTextField txtTaiKhoan;
	private JTextField txtMatKhau;
	private JButton btnThemNhanVien;
	private List<NhanVien> listNhanVien;
	private NhanVienDAO nhanVienDAO;
	private DefaultTableModel modelNhanVien;
	private static DefaultComboBoxModel<String> cboLoaiTimKiem;
	private static JComboBox<String> cmbLoaiTimKiem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien_GUI frame = new NhanVien_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public NhanVien_GUI() throws SQLException {

		nhanVienDAO = new NhanVienDAO();
		listNhanVien = nhanVienDAO.getListNhanVien();

		setTitle("Nhân viên");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		out = new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		setContentPane(out);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("QUẢN LÝ NHÂN VIÊN");
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		top.add(title);
		// title.setHorizontalAlignment(ABORT);
		out.add(top);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		out.add(bottom);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		bottom.add(contentPane, BorderLayout.CENTER);
		JPanel pnLeft = new JPanel();
		// pnLeft.setBorder();
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		pnLeft.setBorder(compound);
		contentPane.add(pnLeft);

		JPanel pnThongTinKh = new JPanel();
		pnLeft.add(pnThongTinKh);
		pnThongTinKh.setLayout(new BoxLayout(pnThongTinKh, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(35);
		pnThongTinKh.add(verticalStrut_2);

		JPanel pnTieuDe = new JPanel();
		pnThongTinKh.add(pnTieuDe);

		JLabel lblTieuDe = new JLabel("Thông tin Nhân viên");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnTieuDe.add(lblTieuDe);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTinKh.add(verticalStrut_1);

		JPanel pnMaKh = new JPanel();
		FlowLayout fl_pnMaKh = (FlowLayout) pnMaKh.getLayout();
		fl_pnMaKh.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnMaKh);

		JLabel lblMaKh = new JLabel("Mã NV             ");
		lblMaKh.setPreferredSize(new Dimension(100, 14));
		pnMaKh.add(lblMaKh);

		txtMaNv = new JTextField();
		txtMaNv.setEnabled(false);
		txtMaNv.setPreferredSize(new Dimension(7, 30));
		pnMaKh.add(txtMaNv);
		txtMaNv.setColumns(20);

		JPanel pnTenKh = new JPanel();
		FlowLayout fl_pnTenKh = (FlowLayout) pnTenKh.getLayout();
		fl_pnTenKh.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnTenKh);

		JLabel lblTenKh = new JLabel("Tên NV");
		lblTenKh.setPreferredSize(new Dimension(100, 14));
		pnTenKh.add(lblTenKh);

		txtTenNv = new JTextField();
		txtTenNv.setPreferredSize(new Dimension(7, 30));
		txtTenNv.setColumns(20);
		// PromptSupport.setPrompt("tên nhân viên", txtTenNv);
		new Placeholder().placeholder(txtTenNv, "Họ và tên");
		pnTenKh.add(txtTenNv);

		JPanel pnEmail = new JPanel();
		FlowLayout fl_pnEmail = (FlowLayout) pnEmail.getLayout();
		fl_pnEmail.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnEmail);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setPreferredSize(new Dimension(100, 14));
		pnEmail.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(7, 30));
		txtEmail.setColumns(20);
		// PromptSupport.setPrompt("Example@gmail.com", txtEmail);
		new Placeholder().placeholder(txtEmail, "Example@gmail.com");
		pnEmail.add(txtEmail);

		JPanel pnSoDienThoai = new JPanel();
		FlowLayout fl_pnSoDienThoai = (FlowLayout) pnSoDienThoai.getLayout();
		fl_pnSoDienThoai.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnSoDienThoai);

		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setPreferredSize(new Dimension(100, 14));
		pnSoDienThoai.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(7, 30));
		txtSdt.setColumns(20);
		// PromptSupport.setPrompt("09xx xxx xxx ", txtSdt);
		new Placeholder().placeholder(txtSdt, "09xx xxx xxx");
		pnSoDienThoai.add(txtSdt);

		JPanel pnDiaChi = new JPanel();
		FlowLayout fl_pnDiaChi = (FlowLayout) pnDiaChi.getLayout();
		fl_pnDiaChi.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnDiaChi);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(new Dimension(100, 14));
		pnDiaChi.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(7, 30));
		txtDiaChi.setColumns(20);
		new Placeholder().placeholder(txtDiaChi, "Số nhà, tên đường, tỉnh thành");
		pnDiaChi.add(txtDiaChi);

		JPanel pnTaiKhoan = new JPanel();
		FlowLayout fl_pnTaiKhoan = (FlowLayout) pnTaiKhoan.getLayout();
		fl_pnTaiKhoan.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnTaiKhoan);

		JLabel lblTaiKhoan = new JLabel("Tài khoản");
		lblTaiKhoan.setPreferredSize(new Dimension(100, 14));
		pnTaiKhoan.add(lblTaiKhoan);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setPreferredSize(new Dimension(7, 30));
		txtTaiKhoan.setColumns(20);
		pnTaiKhoan.add(txtTaiKhoan);
		// PromptSupport.setPrompt("Số nhà, tên đường, tỉnh thành", txtDiaChi);

		JPanel pnMatKhau = new JPanel();
		FlowLayout fl_pnMatKhau = (FlowLayout) pnMatKhau.getLayout();
		fl_pnMatKhau.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnMatKhau);

		JLabel lblMatKhau = new JLabel("Mật Khẩu");
		lblMatKhau.setPreferredSize(new Dimension(100, 14));
		pnMatKhau.add(lblMatKhau);

		txtMatKhau = new JTextField();
		txtMatKhau.setPreferredSize(new Dimension(7, 30));
		txtMatKhau.setColumns(20);
		pnMatKhau.add(txtMatKhau);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnThongTinKh.add(verticalStrut);

		JPanel pnChucNang = new JPanel();
		pnThongTinKh.add(pnChucNang);
		pnChucNang.setLayout(new GridLayout(2, 0, 5, 5));

		btnThemNhanVien = new JButton("Thêm");
		btnThemNhanVien.setBackground(Color.WHITE);
		btnThemNhanVien.setPreferredSize(new Dimension(70, 35));
		btnThemNhanVien.setIcon(new ImageIcon("data\\images\\blueAdd_16.png"));
		btnThemNhanVien.setIconTextGap(10);
		out.getRootPane().setDefaultButton(btnThemNhanVien);
		pnChucNang.add(btnThemNhanVien);

		JButton btnSuaKh = new JButton("Sửa");
		btnSuaKh.setBackground(Color.WHITE);
		btnSuaKh.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
		btnSuaKh.setIconTextGap(30);
		pnChucNang.add(btnSuaKh);

		JButton btnXoaKh = new JButton("Xóa");
		btnXoaKh.setBackground(Color.WHITE);
		btnXoaKh.setIcon(new ImageIcon("data\\images\\trash.png"));
		btnXoaKh.setIconTextGap(10);
		pnChucNang.add(btnXoaKh);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setIcon(new ImageIcon("data\\images\\refresh.png"));
		btnLamMoi.setIconTextGap(10);
		pnChucNang.add(btnLamMoi);

		JPanel pnRight = new JPanel();
		contentPane.add(pnRight);
		pnRight.setLayout(new BorderLayout(0, 0));

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		pnRight.add(pnTimKiem, BorderLayout.NORTH);

		cboLoaiTimKiem = new DefaultComboBoxModel<String>();
		cmbLoaiTimKiem = new JComboBox<String>(cboLoaiTimKiem);
		cmbLoaiTimKiem.setToolTipText("tìm kiếm theo");
		cmbLoaiTimKiem.setBackground(Color.WHITE);
		cmbLoaiTimKiem.setPreferredSize(new Dimension(130, 22));
		pnTimKiem.add(cmbLoaiTimKiem);
		cboLoaiTimKiem.addElement((String) "Mã NV");
		cboLoaiTimKiem.addElement((String) "Tên NV");
		cboLoaiTimKiem.addElement((String) "Số điện thoại");

		txtNhapLieu = new JTextField();
		txtNhapLieu.setPreferredSize(new Dimension(7, 25));
		pnTimKiem.add(txtNhapLieu);
		// PromptSupport.setPrompt("nhập liệu tìm kiếm", txtNhapLieu);
		new Placeholder().placeholder(txtNhapLieu, "nhập liệu tìm kiếm");
		txtNhapLieu.setColumns(30);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setPreferredSize(new Dimension(130, 25));
		btnTimKiem.setBackground(Color.WHITE);
		btnTimKiem.setIcon(new ImageIcon("data\\images\\search_16.png"));
		pnTimKiem.add(btnTimKiem);

		JPanel pnTableKh = new JPanel();
		pnRight.add(pnTableKh, BorderLayout.CENTER);
		pnTableKh.setLayout(new BorderLayout(0, 0));

		modelNhanVien = new DefaultTableModel(cols_dskh, 0);
		table = new JTable(modelNhanVien);
		JScrollPane scrTableKhachhang = new JScrollPane(table);
		scrTableKhachhang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableKhachhang.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableKh.add(scrTableKhachhang);

		renderListNhanVien(listNhanVien);

		btnThemNhanVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tenNV = txtTenNv.getText();
				String email = txtEmail.getText();
				String phone = txtSdt.getText();
				String diachi = txtDiaChi.getText();
				String taiKhoan = txtTaiKhoan.getText();
				String matkhau = txtMatKhau.getText();

				try {
					int row = nhanVienDAO.addNhanVien(
							new NhanVien(-1, tenNV, phone, diachi, new TaiKhoan(taiKhoan, matkhau, 1), email));

					if (row <= 0) {
						NotificationUtils.createNotification("Error", "Thêm nhân viên thất bại");
						return;
					}
					NotificationUtils.createNotification("Success", "Đã thêm nhân viên");
					listNhanVien = nhanVienDAO.getListNhanVien();
					renderListNhanVien(listNhanVien);
					clearInput();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					// mã lỗi tạo tài khoản trùng username đã có trong hệ thống
					if (e1.getErrorCode() == 2627) {
						NotificationUtils.createNotification("Error", "Tên tài khoản đã được sử dụng");
						return;
					}
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Xử lý hành động chọn 1 hàng
				int viewRow = table.getSelectedRow();
				if (!e.getValueIsAdjusting() && viewRow != -1) {
					txtMaNv.setText(table.getValueAt(viewRow, 0).toString());
					txtTenNv.setText((String) table.getValueAt(viewRow, 1));
					txtSdt.setText((String) table.getValueAt(viewRow, 2));
					txtEmail.setText((String) table.getValueAt(viewRow, 3));
					txtDiaChi.setText((String) table.getValueAt(viewRow, 4));
					String taiKhoan = listNhanVien.get(viewRow).getTaiKhoan().getTaiKhoan();
					String matkhau = listNhanVien.get(viewRow).getTaiKhoan().getMatKhau();
					txtTaiKhoan.setText(taiKhoan);
					txtMatKhau.setText(matkhau);
				}

			}
		});

		btnXoaKh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0) {
					NotificationUtils.createNotification("Warning", "Vui lòng chọn hoá đơn cần xoá");
					return;
				}
				int taikhoanId = listNhanVien.get(row).getTaiKhoan().getId();
				System.out.println(taikhoanId);
				try {
					int r = nhanVienDAO.deleteNhanVien(taikhoanId);
					if (r > 0) {
						NotificationUtils.createNotification("Success", "Đã xoá nhân viên");
					} else {
						NotificationUtils.createNotification("Error", "Xoá nhân viên thất bại");
					}
					listNhanVien = nhanVienDAO.getListNhanVien();
					renderListNhanVien(listNhanVien);
					clearInput();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String text = txtNhapLieu.getText();
					int typeSearch = cmbLoaiTimKiem.getSelectedIndex();
					List<NhanVien> l = new ArrayList<NhanVien>();
					switch (typeSearch) {
					case 0:
						NhanVien nv = nhanVienDAO.getNhanVienByID(Integer.parseInt(text));
						if (nv != null)
							l.add(nv);
						break;
					case 1:
						l = nhanVienDAO.findNhanVienByName(text);
						break;
					}

					if (l == null || l.size() == 0) {
						NotificationUtils.createNotification("Warning", "Không tìm thấy nhân viên");
						return;
					}
					renderListNhanVien(l);

				} catch (NumberFormatException e1) {
					NotificationUtils.createNotification("Warning",
							"Id nhân viên phải là số nguyên" + "và không được để trống");

					e1.printStackTrace();
					return;
				} catch (SQLException e1) {
					NotificationUtils.createNotification("Warning", "Đã có lỗi trong quá trình truy vấn dữ liệu");
					e1.printStackTrace();
					return;
				}
			}
		});
		
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput();
				renderListNhanVien(listNhanVien);
			}
		});

	}

	public void init() {
		renderListNhanVien(listNhanVien);
	}

	/**
	 * Xoá dữ liệu của form thông tim nhân viên
	 */
	private void clearInput() {
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtMaNv.setText("");
		txtMatKhau.setText("");
		txtTaiKhoan.setText("");
		txtSdt.setText("");
		txtTenNv.setText("");

	}

	private void renderListNhanVien(List<NhanVien> listNhanVien) {
		modelNhanVien.setRowCount(0);
		for (NhanVien nv : listNhanVien) {
			Object[] temp = { nv.getMaNv(), nv.getTenNv(), nv.getSoDienThoai(), nv.getEmail(), nv.getDiaChi() };
			modelNhanVien.addRow(temp);
		}
	}

	public JPanel getContentPane() {
		return this.out;
	}
}
