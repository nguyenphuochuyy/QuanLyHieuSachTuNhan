package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhanVien_Dao;
import entity.NhanVien;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Panel_TimKiemNhanVien extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;
	private JTextField txtMaNV;
	private JTextField txtTen;
	private JTextField txtEmail;
	private JTextField txtSdt;
	private JTable table_1;
	private JTextField txtTaiKhoan;
	private DefaultTableModel model;
	JDateChooser dateChooser_NgaySinh = new JDateChooser();
	NhanVien_Dao nhanVienDao = new NhanVien_Dao();
	private JTextField txtDiaChi;
	JComboBox comboBox_TrangThai = new JComboBox();
	JComboBox comboBox_GioiTinh = new JComboBox();
	JComboBox comboBox_VaiTro = new JComboBox();

	/**
	 * Create the panel.
	 */
	
	
	public Panel_TimKiemNhanVien() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(16, 101, 477, 616);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Mã nhân viên");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(26, 40, 115, 16);
		panel.add(lblNewLabel_2);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaNV.setBounds(151, 36, 265, 26);
		panel.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Họ và tên");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(26, 88, 87, 16);
		panel.add(lblNewLabel_2_1);
		
		txtTen = new JTextField();
		txtTen.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTen.setColumns(10);
		txtTen.setBounds(151, 83, 265, 26);
		panel.add(txtTen);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(26, 284, 107, 21);
		panel.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3.setBounds(26, 231, 67, 21);
		panel.add(lblNewLabel_3);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(151, 230, 265, 26);
		panel.add(txtEmail);
		
		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSdt.setColumns(10);
		txtSdt.setBounds(151, 279, 265, 26);
		panel.add(txtSdt);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Ngày sinh");
		lblNewLabel_3_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3_1_1.setBounds(26, 186, 107, 26);
		panel.add(lblNewLabel_3_1_1);
		dateChooser_NgaySinh.setDateFormatString("d/MMM/y");
		
		//dateChooser_NgaySinh = new JDateChooser();
		dateChooser_NgaySinh.setBounds(151, 186, 265, 26);
		panel.add(dateChooser_NgaySinh);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Giới tính");
		lblNewLabel_3_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3_1_1_1.setBounds(26, 440, 87, 28);
		panel.add(lblNewLabel_3_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Vai trò");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3_1_1_1_1.setBounds(26, 492, 67, 36);
		panel.add(lblNewLabel_3_1_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_2 = new JLabel("Trạng thái");
		lblNewLabel_3_1_1_1_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3_1_1_1_2.setBounds(26, 384, 87, 27);
		panel.add(lblNewLabel_3_1_1_1_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Địa chỉ");
		lblNewLabel_3_2_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3_2_1.setBounds(26, 323, 67, 27);
		panel.add(lblNewLabel_3_2_1);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setIcon(new ImageIcon(Panel_TimKiemNhanVien.class.getResource("/img/ui/search25.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String ma; 
				String hoTen;
				String taiKhoan;
				String ngaySinh;
				String email;
				String sdt;
				String diaChi;
				String gioiTinh; 
				Date ngaySinhDate = dateChooser_NgaySinh.getDate();
				if(txtMaNV.getText().equals("")) {
					ma = "";
				}
				else
				{
					ma = txtMaNV.getText();
				}
				if(txtTen.getText().equals("")) {
					hoTen = "";
				}
				else 
				{
					hoTen = txtTen.getText();
				}
				if(txtTaiKhoan.getText().equals("")) {
					taiKhoan = "";
				}
				else 
				{
					taiKhoan = txtTaiKhoan.getText();
				}
				ngaySinh = "is not null";
				if(ngaySinhDate == null) {
					ngaySinh = "is not null";
				}
				else
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					ngaySinh = " = '" +dateFormat.format(ngaySinhDate) + "'";
				}
				if(txtEmail.getText().equals("")) {
					email = "";
				}
				else
				{
					email = txtEmail.getText();
				}
				
				if(txtSdt.getText().equals("")) {
					sdt = "";
				}
				else 
				{
					sdt = txtSdt.getText();
				}
				if(txtDiaChi.getText().equals("")) {
					diaChi = "";
				}
				else
				{
					diaChi = txtDiaChi.getText();
				}
				if(comboBox_GioiTinh.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
					gioiTinh =  "";
				}
				else
				{
					gioiTinh = comboBox_GioiTinh.getSelectedItem().toString();
				}
				
				
				String trangThai;
				if(comboBox_TrangThai.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
					trangThai =  " is not null";
				}
				else if(comboBox_TrangThai.getSelectedItem().toString().equalsIgnoreCase("Đang làm"))
				{
					trangThai = " = " + 0;
				}
				else
					trangThai = " = " + 1;
				String vaitro;
				if(comboBox_VaiTro.getSelectedItem().toString().equalsIgnoreCase("Tất cả"))
				{
					vaitro = "is not null";
				}
				else if(comboBox_VaiTro.getSelectedItem().toString().equalsIgnoreCase("Nhân viên bán hàng")){
					vaitro = "=" + 0;
				}
				else
					vaitro = "=" + 1;
				
			
				ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
				ds = nhanVienDao.timKiemNhanVien(ma, hoTen, taiKhoan, ngaySinh, email, sdt, diaChi, trangThai, gioiTinh, vaitro);
				
				for (NhanVien lh : ds) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
					Object[] rowData = {lh.getMaNhanVien(),lh.getHoTenNhanVien(),lh.getGioiTinh() , lh.getSdt() , lh.getEmail() , lh.getTaiKhoan(), lh.getMatKhau(), lh.getTrangThai() ,lh.getNgaySinh().format(formatter), lh.getChucVu(), lh.getDiaChi()};
					model.addRow(rowData);
				}
			}
		});
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 14));
		btnTimKiem.setBounds(45, 553, 145, 48);
		panel.add(btnTimKiem);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Tài khoản");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_1_2.setBounds(26, 139, 87, 16);
		panel.add(lblNewLabel_2_1_2);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBounds(151, 134, 265, 26);
		panel.add(txtTaiKhoan);
		comboBox_TrangThai.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBox_TrangThai.setBounds(151, 375, 265, 36);
		panel.add(comboBox_TrangThai);
		comboBox_GioiTinh.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBox_GioiTinh.setBounds(151, 432, 265, 36);
		panel.add(comboBox_GioiTinh);
		comboBox_VaiTro.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBox_VaiTro.setBounds(151, 493, 265, 36);
		panel.add(comboBox_VaiTro);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_TimKiemNhanVien.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LamMoi();
			}
		});
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 14));
		btnLamMoi.setBounds(272, 553, 145, 48);
		panel.add(btnLamMoi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(151, 325, 265, 26);
		panel.add(txtDiaChi);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin tìm kiếm");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, 70, 493, 32);
		add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(513, 101, 1395, 616);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1003, 609);
		panel_1.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Arial", Font.PLAIN, 15));
		table_1.setModel(model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID" , "Tên nhân viên", "Giới tính" , "SDT" ,"Email", "Tài khoản", "Mật khẩu", "Trạng thái", "Ngày sinh", "Chúc vụ","Địa chỉ"
				}
			));
		scrollPane.setViewportView(table_1);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_1.setRowHeight(35);
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(257, 0, 1253, 52);
		add(panel_2);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM NHÂN VIÊN");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		layDuLieu();
		dataCombo_Trangthai();
		dataCombo_GioiTinh();
		dataCombo_ChucVu();
	}
	
	public void layDuLieu() {
		model.setRowCount(0);
		for (NhanVien lh : nhanVienDao.getAllNhanVien()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
			Object[] rowData = {lh.getMaNhanVien(),lh.getHoTenNhanVien(),lh.getGioiTinh() , lh.getSdt() , lh.getEmail() , lh.getTaiKhoan(), lh.getMatKhau(), lh.getTrangThai() , lh.getNgaySinh().format(formatter) , lh.getChucVu(), lh.getDiaChi()};
			model.addRow(rowData);
			
		}
	}
	public void LamMoi() {
		txtMaNV.setText("");
		txtTen.setText("");
		txtTaiKhoan.setText("");
	//	LocalDate now = LocalDate.now();
		dateChooser_NgaySinh.setDate(null);
		txtEmail.setText("");
		txtSdt.setText("");
		txtDiaChi.setText("");
		comboBox_TrangThai.setSelectedIndex(0);
		comboBox_GioiTinh.setSelectedIndex(0);
		comboBox_VaiTro.setSelectedIndex(0);
		txtMaNV.requestFocus();
		model.setRowCount(0);
		layDuLieu();
	}
	// Đẩy dữ liệu vào combobox_TrangThai
	public void dataCombo_Trangthai() {
		ArrayList<String> ds = new ArrayList<String>();
		ds = nhanVienDao.dataComboBox_TrangThai();
		comboBox_TrangThai.addItem("Tất cả");
		for (String string : ds) {
			comboBox_TrangThai.addItem(string);
		}
		
	}
	//Đẩy dữ liệu vào combobox_VaiTro
	public void dataCombo_GioiTinh() {
		ArrayList<String> ds = new ArrayList<String>();
		ds = nhanVienDao.dataComboBox_GioiTinh();
		comboBox_GioiTinh.addItem("Tất cả");
		for (String string : ds) {
			comboBox_GioiTinh.addItem(string);
		}
		
	}
	// Đẩy dữ liệu vào combobox_GioiTinh
	public void dataCombo_ChucVu() {
		ArrayList<String> ds = new ArrayList<String>();
		ds = nhanVienDao.dataComboBox_VaiTro();
		comboBox_VaiTro.addItem("Tất cả");
		for (String string : ds) {
			comboBox_VaiTro.addItem(string);
		}
		
	}
	
	
}
 