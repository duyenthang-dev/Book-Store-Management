package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import DAO.SanPhamDAO;
import entity.SanPham;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ThemMatHang_GUI extends JDialog{
	
	private HoaDon_GUI hoaDonGUI;
	private SanPhamDAO sanPhamDAO;
	private List<SanPham> listSanPham;
	private List<List<String>> listMatHang;
	private JFrame frame;
	private JTextField txtTenSanPham;
	private JTextField txtSoluong;
	private JTextField txtDongia;
	private JTable table;
	private JComboBox<String> cboMaSanPham;
	private DefaultTableModel model;
	String[] colsMatHang = { "Mã sản phẩm" ,"Tên sản phẩm","Đơn giá","Số lượng", "Thành tiền" };
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						try {
							ThemMatHang_GUI window = new ThemMatHang_GUI(null);
							window.run();
//							window.frame.setVisible(true);
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

	/**
	 * Create the application.
	 */
	
	private void renderMatHang(List<List<String>> listMatHang) {
		model.setRowCount(0);
		for(List<String> mh: listMatHang) {
			Object[] temp = {mh.get(0), mh.get(1), mh.get(2), mh.get(3), mh.get(4) };
			model.addRow(temp);
		}
	}
	
	public void run() {
		this.frame.setVisible(true); 
		// khi ng dùng chọn cbo box mã sản phẩm, tự động update tên sản phẩm tương ứng vào ô txtTenSanPham
		cboMaSanPham.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
	
					int maSanPham = cboMaSanPham.getSelectedIndex() - 1;
					if (maSanPham >= 0) {
						txtTenSanPham.setText(listSanPham.get(maSanPham).getTenSp());
						txtDongia.setText(listSanPham.get(maSanPham).getGiaSp() + "");
					}
					else {
						txtTenSanPham.setText("");
						txtDongia.setText("");
					}
			    }
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String maMatHang = cboMaSanPham.getSelectedItem().toString();
				int soLuong = Integer.parseInt(txtSoluong.getText());
				//kiểm tra mặt hàng được thêm mới đã tồn tại chưa
				for (List<String> matHang: listMatHang) {
					if (matHang.get(0).equals(maMatHang)) {
						// nếu mặt hàng đã được thêm => cập nhật lại số lượng = sl cũ + sl mới
						int newSoLuong = Integer.parseInt(matHang.get(3)) + soLuong;
						matHang.set(3, "" + newSoLuong);
						double newThanhTien = newSoLuong * Double.parseDouble(txtDongia.getText());
						matHang.set(4, "" + newThanhTien);
						renderMatHang(listMatHang);
						return;
					}
				}
				
				// nếu mặt hàng chưa tồn tại, tạo mới và thêm vào list
				List<String> matHang = new ArrayList<String>();
				matHang.add(maMatHang); // id mặt hàng
				matHang.add(txtTenSanPham.getText()); // tên
				matHang.add(txtDongia.getText()); // đơn giá
				matHang.add(txtSoluong.getText()); // số lượng
				matHang.add(""+ Double.parseDouble(txtDongia.getText()) * 
								Integer.parseInt(txtSoluong.getText())); // thành tiền = đơn giá * số lượng
				listMatHang.add(matHang);
				renderMatHang(listMatHang);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = table.getSelectedRow();
				listMatHang.remove(selectedRow);
				renderMatHang(listMatHang);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hoaDonGUI.tinhTongTien(listMatHang);
				frame.dispose();
			}
		});
		 
		
		
	}
	public ThemMatHang_GUI(JFrame parent) throws SQLException {
//		this.listMatHang = listMatHang;
		super(parent);
		hoaDonGUI = (HoaDon_GUI) parent;
		initialize();
//		run();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		sanPhamDAO = new SanPhamDAO();
		listSanPham = sanPhamDAO.getAllSanPham();
		listMatHang = new ArrayList<List<String>>();
		frame = new JFrame();
		frame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setBounds(100, 100, 1100, 380);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 318, 342);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblChonSP = new JLabel("Mã sản phẩm");
		lblChonSP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChonSP.setBounds(10, 10, 105, 25);
		panel.add(lblChonSP);
		
		cboMaSanPham = new JComboBox<String>();
		cboMaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboMaSanPham.setBounds(126, 10, 180, 25);
		List<String> tenSanPham = new ArrayList<String>();
		tenSanPham.add("Nhấn để chọn mã");
		for(SanPham sp: listSanPham) {
			tenSanPham.add(sp.getMaSp() + "");
		}
		cboMaSanPham.setModel(new DefaultComboBoxModel<>(tenSanPham.toArray(String[]::new)));
		panel.add(cboMaSanPham);
		
		JLabel lblNewLabel = new JLabel("Tên sản phẩm");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 45, 105, 25);
		panel.add(lblNewLabel);
		
		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenSanPham.setBounds(126, 48, 180, 25);
		txtTenSanPham.setEditable(false);
		panel.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);
		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSLng.setBounds(10, 80, 105, 25);
		panel.add(lblSLng);
		
		txtSoluong = new JTextField();
		txtSoluong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoluong.setColumns(10);
		txtSoluong.setBounds(126, 80, 180, 25);
		panel.add(txtSoluong);
		
		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDonGia.setBounds(10, 116, 105, 25);
		panel.add(lblDonGia);
		
		txtDongia = new JTextField();
		txtDongia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDongia.setColumns(10);
		txtDongia.setBounds(126, 116, 180, 25);
		txtDongia.setEditable(false);
		panel.add(txtDongia);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(221, 175, 85, 25);
		panel.add(btnAdd);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setBounds(126, 175, 85, 25);
		panel.add(btnDelete);
		
		btnBack = new JButton("Quay về");
		btnBack.setBounds(27, 175, 85, 25);
		panel.add(btnBack);
		
		model = new DefaultTableModel(colsMatHang, 0) { 
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
		
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
//		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(317, 0, 800, 330);
		frame.getContentPane().add(scrollPane);
		
		
	}
	
}

