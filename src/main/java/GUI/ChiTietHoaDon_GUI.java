package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.ChiTietHoaDonDAO;
import entity.ChiTietHoaDon;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class ChiTietHoaDon_GUI extends JDialog {
	private JTable table;
	private DefaultTableModel model;
	String[] cols = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá" };
	ChiTietHoaDonDAO chiTietHoaDonDAO;
	private final JPanel contentPanel = new JPanel();
	private int maHD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChiTietHoaDon_GUI dialog = new ChiTietHoaDon_GUI(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ChiTietHoaDon_GUI(int maHD) throws SQLException {
		chiTietHoaDonDAO = new ChiTietHoaDonDAO();
		this.maHD = maHD;
		setBounds(100, 100, 632, 395);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 618, 327);
		contentPanel.setBorder(UIManager.getBorder("CheckBox.border"));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Danh sách sản phẩm của hoá đơn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 618, 40);
		contentPanel.add(lblNewLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 327, 618, 31);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(null);

		JButton okButton = new JButton("OK");
		
		okButton.setBounds(540, 5, 70, 21);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		
		model = new DefaultTableModel(cols, 0) {
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
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(5, 40, 610, 287);
		contentPanel.add(scrollPane);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	

	public void run() throws SQLException {
		setVisible(true);
		List<ChiTietHoaDon> listMatHang = chiTietHoaDonDAO.getByMaHD(maHD);
		model.setRowCount(0);
		for(ChiTietHoaDon cthd: listMatHang) {
			Object[] temp = {cthd.getMaSanPham(), cthd.getTenSanPham(), cthd.getSoLuong(), cthd.getDonGia()};
			model.addRow(temp);
		}
	}

}