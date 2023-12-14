package ui;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.VanPhongPham_Dao;
import entity.VanPhongPham;

import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Panel_TimKiemVanPhongPham extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table_VanPhongPham;
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JComboBox comboBox_LoaiVanPhongPham;
	private JComboBox cbNhaSanXuat;
	VanPhongPham_Dao vanPhongPham_Dao = new VanPhongPham_Dao();
	private JComponent btnTimKiem;
	private DefaultTableModel model_VPP;

	/**
	 * Create the panel.
	 */
	public Panel_TimKiemVanPhongPham() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBounds(0, 41, 1920, 777);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(32, 121, 427, 551);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(6, 33, 132, 47);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên sản phẩm");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(6, 124, 132, 47);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nhà sản xuất");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(6, 227, 132, 47);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Loại VPP");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(6, 330, 109, 47);
		panel.add(lblNewLabel_1_1_1_1);
		
		comboBox_LoaiVanPhongPham = new JComboBox();
		comboBox_LoaiVanPhongPham.setFont(new Font("Arial", Font.BOLD, 15));
		comboBox_LoaiVanPhongPham.setModel(new DefaultComboBoxModel(new String[] {"Tất cả"}));
		comboBox_LoaiVanPhongPham.setBounds(148, 330, 256, 47);
		panel.add(comboBox_LoaiVanPhongPham);
		
		JButton btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_TimKiemVanPhongPham.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lamMoi();
			}
		});
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(232, 457, 153, 64);
		panel.add(btnLamMoi);
		
		txtMaSanPham = new JTextField();
		txtMaSanPham.setFont(new Font("Arial", Font.BOLD, 15));
		txtMaSanPham.setColumns(10);
		txtMaSanPham.setBounds(148, 35, 256, 47);
		panel.add(txtMaSanPham);
		
		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenSanPham.setColumns(10);
		txtTenSanPham.setBounds(148, 126, 256, 47);
		panel.add(txtTenSanPham);
		
		
		JButton btn_TimKiem = new JButton("TÌM KIẾM");
		btn_TimKiem.setBackground(new Color(64, 128, 128));
		btn_TimKiem.setIcon(new ImageIcon(Panel_TimKiemVanPhongPham.class.getResource("/img/ui/search25.png")));
		
		btn_TimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		btn_TimKiem.setBounds(36, 457, 153, 64);
		panel.add(btn_TimKiem);
		
		cbNhaSanXuat = new JComboBox();
		cbNhaSanXuat.setFont(new Font("Arial", Font.BOLD, 15));
		cbNhaSanXuat.setModel(new DefaultComboBoxModel(new String[] {"Tất cả"}));
		cbNhaSanXuat.setBounds(148, 229, 256, 47);
		panel.add(cbNhaSanXuat);
		
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			String sql = "select DISTINCT NhaSanXuat from SanPham where LoaiSanPham = N'Văn Phòng Phẩm'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				cbNhaSanXuat.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm văn phòng phẩm");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_2.setBounds(32, 79, 427, 32);
		add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(471, 121, 1004, 587);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 1004, 587);
		panel_1.add(scrollPane);
		
		table_VanPhongPham = new JTable();
		table_VanPhongPham.setFont(new Font("Arial", Font.PLAIN, 15));
//		table_VanPhongPham.getColumnModel().getColumn(0).setPreferredWidth(30);
//		table_VanPhongPham.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_VanPhongPham.setRowHeight(35);
		table_VanPhongPham.setModel(model_VPP =  new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "Nh\u00E0 s\u1EA3n xu\u1EA5t", "Lo\u1EA1i", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u00E1 b\u00E1n"
			}
		));
		
		scrollPane.setViewportView(table_VanPhongPham);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 0, 1500, 49);
		add(panel_2);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM VĂN PHÒNG PHẨM");
		lblNewLabel.setForeground(new Color(128, 128, 0));
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		JButton btnTimKiem = new JButton("Tìm kiếm");
		table_VanPhongPham.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table_VanPhongPham.getSelectedRow();
				txtMaSanPham.setText(model_VPP.getValueAt(row, 0).toString());
				txtTenSanPham.setText(model_VPP.getValueAt(row, 1).toString());
				cbNhaSanXuat.setSelectedItem(model_VPP.getValueAt(row, 2).toString());
				comboBox_LoaiVanPhongPham.setSelectedItem(model_VPP.getValueAt(row, 3).toString());
			}
		});
		layDuLieu();
		themDataComBo_TheLoaiVPP();
		
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model_VPP.setRowCount(0);
				String ma; 
				String ten; 
				String nhaSanXuat;
				String soLuong;
				String loai;
				String gia;
				if(txtMaSanPham.getText().equals("")) {
					ma = "";
				}
				else
				{
					ma = txtMaSanPham.getText().toString();
				}
				
				if(txtTenSanPham.getText().equals("")) {
					ten = "";
				}
				else
				{
					ten = txtTenSanPham.getText().toString();
				}
				if(cbNhaSanXuat.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
					nhaSanXuat = "";		
				}
				else
				{
					nhaSanXuat = cbNhaSanXuat.getSelectedItem().toString();
				}			
				
				if(comboBox_LoaiVanPhongPham.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
					loai = "";
				}
				else
				{
					loai = comboBox_LoaiVanPhongPham.getSelectedItem().toString();
				}	
				ArrayList<VanPhongPham> ds = new ArrayList<VanPhongPham>();
				ds = vanPhongPham_Dao.timKiemVanPhongPham(ma, ten, nhaSanXuat, loai);
				System.out.println(ds);
				for (VanPhongPham vanPhongPham : ds) {
					Object[] rowData = {vanPhongPham.getMaSanPham(),vanPhongPham.getTenSanPham(),vanPhongPham.getNhaSanXuat(),vanPhongPham.getLoaiVanPhongPham(),vanPhongPham.getSoLuong() ,vanPhongPham.getGiaBan()};
					model_VPP.addRow(rowData);
				}
				
			}
		});

	}
	// lấy dữ liệu vào table 
	public void layDuLieu() {
		model_VPP.setRowCount(0);
		List<VanPhongPham> ds = new ArrayList<VanPhongPham>();
		ds = vanPhongPham_Dao.getAllVanPhongPham();
		for (VanPhongPham vanPhongPham : ds) {
			Object[] rowData = {vanPhongPham.getMaSanPham(),vanPhongPham.getTenSanPham(),vanPhongPham.getNhaSanXuat(),vanPhongPham.getLoaiVanPhongPham(),vanPhongPham.getSoLuong(),vanPhongPham.getGiaBan()};
			model_VPP.addRow(rowData);
		}
	}
	// Đẩy dữ liệu vào combobox; 
	public void themDataComBo_TheLoaiVPP() {
		ArrayList<String> dsData = new ArrayList<String>();
		dsData = vanPhongPham_Dao.dataComboBox_LoaiVanPhongPham();
		for (String string : dsData) {
			comboBox_LoaiVanPhongPham.addItem(string);
		}
		
	}
	
	// Làm mới dữ liệu 
	public void lamMoi() {
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		txtMaSanPham.requestFocus();
		model_VPP.setRowCount(0);
		comboBox_LoaiVanPhongPham.setSelectedIndex(0);
		cbNhaSanXuat.setSelectedIndex(0);
		layDuLieu();
	}
	
}
