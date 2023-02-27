package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import util.NotificationUtils;

import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class HoaDon_GUI extends JFrame implements ActionListener, MouseListener {
	private DefaultTableModel modelHD;
	String[] colsHD = { "Mã hoá đơn", "Tên nhân viên","Tên khách hàng", "Tổng tiền", "Ngày lập"};
	public JPanel pnMain;
	private JTable tableHD;

	private JTextField txtTongTien;
	private JButton btnThem;
	private JComboBox<String> cboMaKH;
	private JPanel panel_1;
	private JTextField txtTimMaHDDV;

	private JTextField txtGia;
	private JTextField txtTen;
	private JButton btnTimMaHDDV;
	private JButton btnXem;
	private JButton btnBoChon;
	private JButton btnThemMatHang;
	private JTextField txtTenSP;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnChiTiet;
	
	private List<KhachHang> listKhachHang;
	private KhachHangDAO khachHangDAO;
	private HoaDonDAO hoaDonDAO;
	private ChiTietHoaDonDAO chiTietHoaDonDAO;
	private List<List<String>> listMatHang;
	private NhanVien nhanVien;
	private List<HoaDon> listHoaDon;

	public HoaDon_GUI(NhanVien nhanVien) throws SQLException {
		this.nhanVien = nhanVien;
		khachHangDAO = new KhachHangDAO();
		hoaDonDAO = new HoaDonDAO();
		chiTietHoaDonDAO = new ChiTietHoaDonDAO();
		listKhachHang = khachHangDAO.getListKhachHang();
		listMatHang = new ArrayList<List<String>>();
		listHoaDon = hoaDonDAO.getAllHoaDon();
		
		setTitle("Quản lý hóa đơn");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
	
		
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		setContentPane(pnMain);

		JLabel lbTitle = new JLabel("Quản Lý Hóa Đơn");
		lbTitle.setBounds(585, 11, 348, 30);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnMain.add(lbTitle);

		modelHD = new DefaultTableModel(colsHD, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				// Không cho chỉnh sửa trên table
			}
		};

		pnMain.setLayout(null);

		JPanel pn = new JPanel();
		pn.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chi tiết", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pn.setBounds(10, 65, 347, 300);
		pnMain.add(pn);
		pn.setLayout(null);

		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMaKH.setBounds(10, 25, 93, 25);
		pn.add(lbMaKH);

		cboMaKH = new JComboBox<String>();
		cboMaKH.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboMaKH.setBounds(122, 25, 205, 25);
		cboMaKH.addItem("");
		List<String> tenKhachHang = new ArrayList<String>();
		tenKhachHang.add("Nhấn để chọn mã");
		for(KhachHang kh: listKhachHang) {
			tenKhachHang.add(kh.getMaKh() + "");
		}
		cboMaKH.setModel(new DefaultComboBoxModel<>(tenKhachHang.toArray(String[]::new)));
		pn.add(cboMaKH);

		JLabel lbTen = new JLabel("Tên khách hàng");
		lbTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTen.setBounds(10, 61, 93, 25);
		pn.add(lbTen);

		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(122, 61, 205, 25);
		pn.add(txtTen);
		
//		JLabel lbMaSP = new JLabel("Mã sản phẩm:");
//		lbMaSP.setFont(new Font("Tahoma", Font.BOLD, 11));
//		lbMaSP.setBounds(10, 94, 93, 25);
//		pn.add(lbMaSP);
		
		btnThemMatHang = new JButton("Điều chỉnh đơn hàng");
		btnThemMatHang.setBounds(122, 94, 205, 25);
		pn.add(btnThemMatHang);

		JLabel lbTongTien = new JLabel("Thành tiền:");
		lbTongTien.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTongTien.setBounds(10, 133, 93, 25);
		pn.add(lbTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTongTien.setBounds(122, 133, 205, 25);
		pn.add(txtTongTien);
		txtTongTien.setColumns(10);

		btnSua = new JButton("Sửa hóa đơn");
		btnSua.setIcon(new ImageIcon("data/images/edit2_16.png"));
		btnSua.setBounds(10, 183, 317, 35);
		pn.add(btnSua);

		btnThem = new JButton("Tạo hoá đơn");
		btnThem.setBounds(10, 227, 317, 35);
		pn.add(btnThem);
		btnThem.setIcon(new ImageIcon("data/images/check.png"));


		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(370, 65, 900, 575);
		pnMain.add(panel_1);
		panel_1.setLayout(null);

		tableHD = new JTable(modelHD);
		JScrollPane scHD = new JScrollPane(tableHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scHD.setBounds(10, 67, 870, 500);
		panel_1.add(scHD);

		JLabel lbTimMaHDDV = new JLabel("Mã hoá đơn:");
		lbTimMaHDDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTimMaHDDV.setBounds(20, 25, 110, 30);
		panel_1.add(lbTimMaHDDV);

		txtTimMaHDDV = new JTextField();
		txtTimMaHDDV.setBounds(100, 29, 120, 25);
		panel_1.add(txtTimMaHDDV);
		txtTimMaHDDV.setColumns(10);

		btnTimMaHDDV = new JButton("Tìm");
		btnTimMaHDDV.setIcon(new ImageIcon("data/images/search_16.png"));
		btnTimMaHDDV.setBounds(235, 28, 115, 25);
		panel_1.add(btnTimMaHDDV);

		btnXem = new JButton("Xem chi tiết");
		btnXem.setIcon(new ImageIcon("data/images/blueAdd_16.png"));
		btnXem.setBounds(360, 28, 115, 25);
		panel_1.add(btnXem);
		
		btnBoChon = new JButton("Bỏ chọn");
		btnBoChon.setBounds(485, 28, 115, 25);
		btnBoChon.setIcon(new ImageIcon("data/images/check2_16.png"));
		panel_1.add(btnBoChon);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(610, 28, 115, 25);
		btnXoa.setIcon(new ImageIcon("data/images/cancel_16.png"));
		panel_1.add(btnXoa);
		
		btnSua.addActionListener(this);

		btnThem.addActionListener(this);
		btnTimMaHDDV.addActionListener(this);
		btnXem.addActionListener(this);
		btnBoChon.addActionListener(this);
		cboMaKH.addActionListener(this);
		tableHD.addMouseListener(this);
		pnMain.addMouseListener(this);
		renderHoaDon(hoaDonDAO.getAllHoaDon());
		

		// ========================== EVENT HANDLER ===============================
		btnThemMatHang.addActionListener(e -> {
			try {
				ThemMatHang_GUI themMatHang = new ThemMatHang_GUI(this);
				themMatHang.run();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		cboMaKH.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
	
					int maKhachHang = cboMaKH.getSelectedIndex();
					if (maKhachHang > 0) {
						txtTen.setText(listKhachHang.get(maKhachHang - 1).getHoTen());
					}
					else {
						txtTen.setText("");
					}
			    }
			}
		});
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double tongTien = Double.parseDouble(txtTongTien.getText());
					java.sql.Date ngayMua = new java.sql.Date(new java.util.Date().getTime());
					int idx = cboMaKH.getSelectedIndex() - 1;
					KhachHang khachHang = listKhachHang.get(idx);
					
					HoaDon hoaDon = new HoaDon(-1, tongTien, (Date) ngayMua, nhanVien, khachHang);
					int id = hoaDonDAO.addHoaDon(hoaDon);
					boolean success = chiTietHoaDonDAO.addMatHang(id, listMatHang);
					if (success) {
						NotificationUtils.createNotification("Success", "Đã tạo hoá đơn"); 
						listHoaDon = hoaDonDAO.getAllHoaDon();
						renderHoaDon(listHoaDon);
//						resetInput();
					}
				}catch (NumberFormatException e1) {
					NotificationUtils.createNotification("Warning", "Vui lòng không bỏ trống trường dữ liệu hoặc nhập"
							+ "sai định dạng"); 
					return;
				} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					NotificationUtils.createNotification("Error", "Đã có lỗi trong quá trình truy vấn dữ liệu");
				}
			}
		});
		
		// xem chi tiết danh sách hàng đã mua của hoá đơn
		btnXem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tableHD.getSelectedRow();
				if (row < 0) {
					NotificationUtils.createNotification("Warning", "Vui lòng chọn hoá đơn cần xem");
					return;
				}
				
				int maHD = listHoaDon.get(row).getMaHD();
				try {
					ChiTietHoaDon_GUI chiTiet = new ChiTietHoaDon_GUI(maHD);
					chiTiet.run();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableHD.getSelectedRow();
				if (row < 0) {
					NotificationUtils.createNotification("Warning", "Vui lòng chọn hoá đơn cần xoá");
					return;
				}
				int maHD = listHoaDon.get(row).getMaHD();
				try {
					int r = hoaDonDAO.deleteHoaDon(maHD);
					if (r > 0) {
						NotificationUtils.createNotification("Success", "Đã xoá hoá đơn");
					}
					else {
						NotificationUtils.createNotification("Error", "Xoá hoá đơn thất bại");
					}
					listHoaDon = hoaDonDAO.getAllHoaDon();
					renderHoaDon(listHoaDon);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnTimMaHDDV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<HoaDon> l = new ArrayList<HoaDon>();
					int maHD = Integer.parseInt(txtTimMaHDDV.getText());
					HoaDon hd = hoaDonDAO.getHoaDonById(maHD);
					if (hd != null)
						l.add(hd);
					if (l == null || l.size() == 0) {
						NotificationUtils.createNotification("Error", "Không tìm thấy hoá đơn");
						return;
					}
					renderHoaDon(l);

				}catch (NumberFormatException e1) {
					NotificationUtils.createNotification("Warning", "Mã hoá đơn không được để trống và phải đúng định dạng");
					return;
				}catch (SQLException e2) {
					NotificationUtils.createNotification("Error", "Đã có lỗi trong quá trình truy vấn dữ liệu");
					return;
				}
				
			}
		});
	}
	
	private void renderHoaDon(List<HoaDon> list) {
		
		modelHD.setRowCount(0);
		for(HoaDon hd: list) {
			Object[] temp = {hd.getMaHD(), hd.getNhanVien().getTenNv (), hd.getKhachHang().getHoTen(), hd.getTongTien(), hd.getNgayMua() };
			modelHD.addRow(temp);
		}

	}
	
	public void init() throws SQLException {
		listKhachHang = khachHangDAO.getListKhachHang();
	
	}
	
	public void tinhTongTien(List<List<String>> listMatHang) {
		double tongTien = 0;
		for (List<String> mh: listMatHang) {
			tongTien += Double.parseDouble(mh.get(4));
		}

		txtTongTien.setText("" + tongTien);
		this.listMatHang = listMatHang;
	}

	public JPanel getContentPane() {
		return this.pnMain;
	}

	

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}}