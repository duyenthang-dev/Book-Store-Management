package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;

public class TroGiup_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel pnContent;
	private JPanel pnRight;
	public JButton btnTroVe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TroGiup_GUI frame = new TroGiup_GUI();
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
	public TroGiup_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 700);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {300, 1050};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		panel.setLayout(gbl_panel);
		
		JPanel pnLeft = new JPanel();
		pnLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnLeft.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnLeft = new GridBagConstraints();
		gbc_pnLeft.insets = new Insets(0, 0, 0, 5);
		gbc_pnLeft.fill = GridBagConstraints.BOTH;
		gbc_pnLeft.gridx = 0;
		gbc_pnLeft.gridy = 0;
		panel.add(pnLeft, gbc_pnLeft);
		
		JPanel pnDanhSachCauHoi = new JPanel();
		pnDanhSachCauHoi.setBackground(Color.WHITE);
		pnDanhSachCauHoi.setPreferredSize(new Dimension(280, 200));
		pnLeft.add(pnDanhSachCauHoi);
		pnDanhSachCauHoi.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTroGiup = new JPanel();
		pnTroGiup.setBackground(Color.WHITE);
		pnDanhSachCauHoi.add(pnTroGiup, BorderLayout.NORTH);
		
		JLabel lblTroGiup = new JLabel("Tr??? gi??p");
		lblTroGiup.setHorizontalAlignment(SwingConstants.CENTER);
		lblTroGiup.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pnTroGiup.add(lblTroGiup);
		
		JPanel pnCauHoi = new JPanel();
		pnCauHoi.setBorder(new EmptyBorder(0, 10, 0, 0));
		pnCauHoi.setBackground(Color.WHITE);
		pnDanhSachCauHoi.add(pnCauHoi);
		pnCauHoi.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnTroVe = new JButton("Tr??? v???");
		btnTroVe.setPreferredSize(new Dimension(200, 23));
		btnTroVe.setBackground(Color.WHITE);
		pnDanhSachCauHoi.add(btnTroVe, BorderLayout.SOUTH);
		
		List<JLabel> lblDanhSachCauHoi = new ArrayList<JLabel>();
		
		lblDanhSachCauHoi.add(new JLabel("1. C??ch mua h??ng nh?? th??? n??o ?"));
		lblDanhSachCauHoi.add(new JLabel("2. C??ch xem gi??? h??ng nh?? th??? n??o ?"));
		lblDanhSachCauHoi.add(new JLabel("3. C??ch ?????t h??ng nh?? th??? n??o ?"));
		lblDanhSachCauHoi.add(new JLabel("4. C??ch ??i v??o trang qu???n l?? ?"));
		
		lblDanhSachCauHoi.forEach(lbl -> {
			pnCauHoi.add(lbl);
		});
		
		pnRight = new JPanel();
		GridBagConstraints gbc_pnRight = new GridBagConstraints();
		gbc_pnRight.fill = GridBagConstraints.BOTH;
		gbc_pnRight.gridx = 1;
		gbc_pnRight.gridy = 0;
		panel.add(pnRight, gbc_pnRight);
		pnRight.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		pnContent = this.troGiup4();
		pnRight.add(pnContent);
		
		List<JPanel> pnTraLoi = new ArrayList<JPanel>();
		pnTraLoi.add(troGiup1());
		pnTraLoi.add(troGiup2());
		pnTraLoi.add(troGiup3());
		pnTraLoi.add(troGiup4());
		
		lblDanhSachCauHoi.forEach(lbl -> {
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			});
			
			lbl.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					
					pnRight.remove(pnContent);
					pnRight.revalidate();
					pnRight.repaint();
					int id = Integer.parseInt(((JLabel)e.getSource()).getText().substring(0, 1)) - 1;
					System.out.println(id);
					pnContent = pnTraLoi.get(id);
					pnRight.add(pnContent);
					pnRight.revalidate();
					pnRight.repaint();
				}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
			});
		});
	}
	
	private JPanel troGiup1() {
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("C??ch mua h??ng nh?? th??? n??o ?");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("1. B???m v??o n??t \"Th??m v??o gi??? h??ng\" t???i s???n ph???m mong mu???n.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2. Nh???p s??? l?????ng s???n ph???m c???n mua (l???n h??n 0).");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_7 = new JLabel("3. S???n ph???m ???? ???????c th??m v??o gi??? h??ng, c?? th??? ti???p t???c mua s???m ho???c v??o gi??? h??ng ????? ?????t h??ng.");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_7);
		
		return panel_6;
	}
	
	private JPanel troGiup2() {
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("C??ch xem gi??? h??ng nh?? th??? n??o ?");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("1. B???m v??o n??t \"Xin ch??o: <T??n b???n>\" tr??n thanh menu.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2. B???m n??t gi??? h??ng ????? ??i t???i gi??? h??ng.");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1);
		
		return panel_6;
	}

	private JPanel troGiup3() {
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("C??ch ?????t h??ng nh?? th??? n??o ?");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("1. B???m v??o n??t \"Xin ch??o: <T??n b???n>\" tr??n thanh menu.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2. B???m n??t \"gi??? h??ng\" ????? ??i t???i gi??? h??ng.");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("3. ??i???n th??ng tin c?? nh??n.");
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1_1);
		
		JLabel lblNewLabel_6_1_1_1 = new JLabel("4. B???m \"?????t h??ng\".");
		lblNewLabel_6_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1_1_1);
		
		JLabel lblNewLabel_6_1_1_1_1 = new JLabel("5. B???m \"Yes\" ????? x??c nh???n ?????t h??ng.");
		lblNewLabel_6_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(lblNewLabel_6_1_1_1_1);
		
		return panel_6;
	}

	private JPanel troGiup4() {
		JPanel pnNoiDung = new JPanel();
		pnNoiDung.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnNoiDung.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDe = new JPanel();
		pnNoiDung.add(pnTieuDe, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("C??ch ??i t???i trang qu???n l?? ?");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pnTieuDe.add(lblTieuDe);
		
		JPanel pnCauTraLoi = new JPanel();
		pnCauTraLoi.setBorder(new EmptyBorder(0, 10, 0, 0));
		pnNoiDung.add(pnCauTraLoi, BorderLayout.CENTER);
		pnCauTraLoi.setLayout(new BoxLayout(pnCauTraLoi, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("1. B???m v??o n??t \"Xin ch??o: <T??n b???n>\" tr??n thanh menu.");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnCauTraLoi.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2. B???m n??t \"??i t???i trang qu???n l??\".");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnCauTraLoi.add(lblNewLabel_6_1);
		
		return pnNoiDung;
	}
	
	public JPanel getContenPane() {
		return this.contentPane;
	}
}
