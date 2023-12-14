package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.PhieuDatHang_Dao;
import dao.PhieuNhapHang_Dao;
import entity.ChiTietPhieuNhapHang;
import entity.NhaCungCap;
import entity.PhieuNhapHang;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Panel_TimKiemPhieuNhapHang extends JPanel implements ActionListener{
	private JTextField txtTong;
	private JTable table_PhieuNhap;
	private JTable table_ChiTiet;
	private JTextField txtMaPhieuNhap;
	private JTextField txtMaNhanVien;
	private JTextField txtTenNhanVien;
	private JButton btnTimKiem, btnLamMoi, btnInPhieuNhap;
	private JComboBox cbNhaCungCap;
	private DefaultTableModel model_PhieuNhap, model_ChiTiet;
	private JDateChooser dcNgayLap;
	
	PhieuNhapHang_Dao phieuNhapHangDao = new PhieuNhapHang_Dao();

	/**
	 * Create the panel.
	 */
	public Panel_TimKiemPhieuNhapHang() {
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM PHIẾU NHẬP HÀNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(64, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 0, 1498, 52);
		add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(22, 131, 349, 552);
		add(panel_2);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setIcon(new ImageIcon(Panel_TimKiemPhieuNhapHang.class.getResource("/img/ui/search25.png")));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setBounds(31, 379, 136, 64);
		panel_2.add(btnTimKiem);
		
		JLabel lblNewLabel_2 = new JLabel("Mã Phiếu Nhập");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 27, 167, 30);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mã Nhân Viên");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(10, 89, 167, 30);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Nhà cung cấp");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_1.setBounds(10, 235, 146, 30);
		panel_2.add(lblNewLabel_2_1_1);
		
		txtMaPhieuNhap = new JTextField();
		txtMaPhieuNhap.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaPhieuNhap.setColumns(10);
		txtMaPhieuNhap.setBounds(163, 23, 157, 43);
		panel_2.add(txtMaPhieuNhap);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(163, 85, 157, 43);
		panel_2.add(txtMaNhanVien);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Ngày Tạo");
		lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_1_1.setBounds(10, 298, 117, 30);
		panel_2.add(lblNewLabel_2_1_1_1);
		
		cbNhaCungCap = new JComboBox();
		cbNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 15));
		cbNhaCungCap.setModel(new DefaultComboBoxModel(new String[] {"Tất cả"}));
		cbNhaCungCap.setBounds(166, 231, 154, 43);
		panel_2.add(cbNhaCungCap);
		
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<String> data = new ArrayList<String>();
		String sql = "select DISTINCT TenNhaCungCap from NhaCungCap ";
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				cbNhaCungCap.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dcNgayLap = new JDateChooser();
		dcNgayLap.setBounds(163, 298, 157, 43);
		panel_2.add(dcNgayLap);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Tên nhân viên");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_2.setBounds(10, 157, 167, 30);
		panel_2.add(lblNewLabel_2_1_2);
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(163, 153, 157, 43);
		panel_2.add(txtTenNhanVien);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_TimKiemPhieuNhapHang.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(193, 379, 146, 64);
		panel_2.add(btnLamMoi);
		
		btnInPhieuNhap = new JButton("In Phiếu");
		btnInPhieuNhap.setBackground(new Color(128, 128, 64));
		btnInPhieuNhap.setIcon(new ImageIcon(Panel_TimKiemPhieuNhapHang.class.getResource("/img/ui/print.png")));
		btnInPhieuNhap.setFont(new Font("Arial", Font.BOLD, 15));
		btnInPhieuNhap.setBounds(93, 465, 184, 64);
		panel_2.add(btnInPhieuNhap);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(389, 131, 1072, 260);
		add(scrollPane);
		
		table_PhieuNhap = new JTable();
		table_PhieuNhap.setModel(model_PhieuNhap = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã phiếu nhập","Ngày lập","Mã nhân viên","Tên nhân viên","Nhà cung cấp"
			}
		));
		table_PhieuNhap.setRowHeight(35);
		table_PhieuNhap.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setViewportView(table_PhieuNhap);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(389, 423, 1119, 260);
		add(scrollPane_1);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		table_ChiTiet = new JTable();
		table_ChiTiet.setModel(model_ChiTiet = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã phiếu nhập", "Mã sản phẩm","Tên sản phẩm","Loại sản phẩm","Giá nhập","Số lượng"
			}
		));
		table_ChiTiet.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_ChiTiet.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_ChiTiet.getColumnModel().getColumn(2).setPreferredWidth(250);
		table_ChiTiet.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table_ChiTiet.setRowHeight(35);
		table_ChiTiet.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane_1.setViewportView(table_ChiTiet);
		
		JLabel lblNewLabel_1 = new JLabel("TỔNG");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(624, 696, 119, 49);
		add(lblNewLabel_1);
		
		txtTong = new JTextField();
		txtTong.setFont(new Font("Arial", Font.BOLD, 30));
		txtTong.setBounds(728, 691, 376, 59);
		add(txtTong);
		txtTong.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("VND");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_3.setBounds(1116, 691, 106, 59);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CHI TIẾT PHIẾU NHẬP");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setBounds(834, 396, 204, 29);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("DANH SÁCH PHIẾU NHẬP");
		lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_1.setBounds(809, 104, 197, 29);
		add(lblNewLabel_4_1);
		
		layDuLieuPhieuNhap();
		
		table_PhieuNhap.addMouseListener(new MouseListener() {
			
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
				int row = table_PhieuNhap.getSelectedRow();
				txtMaPhieuNhap.setText(model_PhieuNhap.getValueAt(row, 0).toString());
				String dateStr = (String) model_PhieuNhap.getValueAt(row, 1); 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
                LocalDate selectedDate = LocalDate.parse(dateStr, formatter);
				dcNgayLap.setDate(Date.valueOf(selectedDate));
				txtMaNhanVien.setText(model_PhieuNhap.getValueAt(row, 2).toString());
				txtTenNhanVien.setText(model_PhieuNhap.getValueAt(row, 3).toString());
				cbNhaCungCap.setSelectedItem(model_PhieuNhap.getValueAt(row, 4).toString());
				layDuLieuChiTietPhieuNhap(model_PhieuNhap.getValueAt(row, 0).toString());
			}
		});
		btnTimKiem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnInPhieuNhap.addActionListener(this);
	}
	
	
	public void layDuLieuPhieuNhap() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			String sql = "SELECT PhieuNhapHang.MaPhieuNhapHang, PhieuNhapHang.NgayNhapHang, PhieuNhapHang.MaNhanVien, NhanVien.HoTenNhanVien, NhaCungCap.TenNhaCungCap "
					+ "FROM NhaCungCap INNER JOIN PhieuNhapHang ON NhaCungCap.MaNhaCungCap = PhieuNhapHang.MaNhaCungCap INNER JOIN NhanVien ON PhieuNhapHang.MaNhanVien = NhanVien.MaNhanVien";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				if(!rs.getString(1).equalsIgnoreCase(" ")) {
					Object[] rowData = {rs.getString(1),rs.getDate(2).toLocalDate().format(formatter),rs.getString(3),rs.getString(4),rs.getString(5)};
					model_PhieuNhap.addRow(rowData);
				}
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void layDuLieuChiTietPhieuNhap(String maPhieuNhap) {
		model_ChiTiet.setRowCount(0);
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		DecimalFormat df2 = new DecimalFormat("#,###");

		try {
			double tong = 0;
			String sql = "SELECT ChiTietPhieuNhapHang.MaPhieuNhapHang, SanPham.MaSanPham, SanPham.TenSanPham, SanPham.LoaiSanPham, ChiTietPhieuNhapHang.GiaNhap, ChiTietPhieuNhapHang.SoLuong "
					+ "FROM ChiTietPhieuNhapHang INNER JOIN SanPham ON ChiTietPhieuNhapHang.MaSanPham = SanPham.MaSanPham "
					+ "where MaPhieuNhapHang like '"+maPhieuNhap+"'";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				Object[] rowData = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),df.format(rs.getDouble(5)),rs.getInt(6)};
				tong+=rs.getDouble(5)*rs.getInt(6);
				model_ChiTiet.addRow(rowData);
			}
			txtTong.setText(df2.format(tong));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		if(o.equals(btnTimKiem)) {
			String maPD;
			if(txtMaPhieuNhap.getText().contentEquals("")) {
				maPD = "";
			} else {
				maPD = txtMaPhieuNhap.getText();
			}
			String maNhanVien;
			if(txtMaNhanVien.getText().equalsIgnoreCase(""))
				maNhanVien = "";
			else
				maNhanVien = txtMaNhanVien.getText();
			String tenNhanVien ;
			if(txtTenNhanVien.getText().equalsIgnoreCase(""))
				tenNhanVien = "";
			else
				tenNhanVien =  txtTenNhanVien.getText();
			String nhaCungCap;
			if(cbNhaCungCap.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
				nhaCungCap = "%%";
			}
			else {
				nhaCungCap = cbNhaCungCap.getSelectedItem().toString();
			}
			java.util.Date selectedDate = dcNgayLap.getDate();

			String ngayLapString;
			if(selectedDate == null) 
				ngayLapString = "is not null";
			else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("y-M-d");
				ngayLapString = " = '" +dateFormat.format(selectedDate) + "'";
			}
			model_PhieuNhap.setRowCount(0);
			for(PhieuNhapHang lh : phieuNhapHangDao.timKiemPhieuNhap(maPD, 	maNhanVien, tenNhanVien, nhaCungCap, ngayLapString)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
				try {
					String sql = "SELECT PhieuNhapHang.MaPhieuNhapHang, PhieuNhapHang.NgayNhapHang, PhieuNhapHang.MaNhanVien, NhanVien.HoTenNhanVien, NhaCungCap.TenNhaCungCap "
							+ "FROM NhaCungCap INNER JOIN PhieuNhapHang ON NhaCungCap.MaNhaCungCap = PhieuNhapHang.MaNhaCungCap INNER JOIN NhanVien ON PhieuNhapHang.MaNhanVien = NhanVien.MaNhanVien "
							+ "where MaPhieuNhapHang = '" + lh.getMaPhieuNhapHang() + "'";
					java.sql.Statement statement =  con.createStatement();
					ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
					while(rs.next()) {
						if(!rs.getString(1).equalsIgnoreCase(" ")) {
							Object[] rowData = {rs.getString(1),rs.getDate(2).toLocalDate().format(formatter),rs.getString(3),rs.getString(4),rs.getString(5)};
							model_PhieuNhap.addRow(rowData);
						}		
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				System.out.println(lh);
			}
		}
		if(o.equals(btnLamMoi)) {
			model_PhieuNhap.setRowCount(0);
			layDuLieuPhieuNhap();
			txtMaPhieuNhap.setText("");
			txtMaNhanVien.setText("");
			txtTenNhanVien.setText("");
			txtTong.setText("");
			dcNgayLap.setDate(null);
			cbNhaCungCap.setSelectedIndex(0);
			model_ChiTiet.setRowCount(0);
		}
		if(o.equals(btnInPhieuNhap)) {
			int row = table_PhieuNhap.getSelectedRow();
			if(row == -1)
				JOptionPane.showMessageDialog(null, "Chọn hóa đơn để in");
			else {
				phieuNhapHangDao.xuatPhieuNhap(model_PhieuNhap.getValueAt(row, 0).toString());
			}	
		}
		
	}
	
	
	
}
