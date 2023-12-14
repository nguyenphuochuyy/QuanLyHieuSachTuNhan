package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.KhachHang_Dao;
import entity.KhachHang;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Panel_TimKiemKhachHang extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtSdt;
	private JTextField txtEmail;
	private JTable table;
	private DefaultTableModel model;
	JComboBox comboBox_GioiTinh ;
	
	KhachHang_Dao khachHangDao = new KhachHang_Dao();
	private JTextField txtDiaChi;
	private JTextField txtHoTen;
	private JTextField txtMaKH;
	JDateChooser dateChooser_NgaySinh ; 
	/**
	 * Create the panel.
	 */
	public Panel_TimKiemKhachHang() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(540, 19, 1, 1);
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 24, 80, 16);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(100, 24, 185, 19);
		panel_1.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ và tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 63, 97, 29);
		panel_1.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(100, 69, 185, 19);
		panel_1.add(textField_1);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(419, 23, 157, 69);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM KHÁCH HÀNG");
		lblNewLabel.setForeground(new Color(128, 0, 255));
		lblNewLabel.setBounds(612, 0, 407, 52);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("THÔNG TIN TÌM KIẾM");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 33, 266, 34);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3_1.setBounds(10, 355, 319, 34);
		add(lblNewLabel_3_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(157, 77, 1235, 277);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Ngày sinh");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setBounds(514, 170, 80, 13);
		panel_2.add(lblNewLabel_4);
		
		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSdt.setColumns(10);
		txtSdt.setBounds(634, 13, 291, 42);
		panel_2.add(txtSdt);
		
		dateChooser_NgaySinh = new JDateChooser();
		dateChooser_NgaySinh.setBounds(634, 163, 291, 42);
		panel_2.add(dateChooser_NgaySinh);
		
		JLabel lblNewLabel_4_1 = new JLabel("Số điện thoại");
		lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_1.setBounds(510, 11, 114, 42);
		panel_2.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Email");
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_1_1_1.setBounds(10, 173, 80, 42);
		panel_2.add(lblNewLabel_4_1_1_1);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(154, 175, 246, 42);
		panel_2.add(txtEmail);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Giới tính");
		lblNewLabel_4_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_1_1_1_1.setBounds(514, 222, 80, 34);
		panel_2.add(lblNewLabel_4_1_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_4_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_1_1_1_1_1.setBounds(514, 91, 80, 35);
		panel_2.add(lblNewLabel_4_1_1_1_1_1);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(634, 93, 291, 42);
		panel_2.add(txtDiaChi);
		
		comboBox_GioiTinh = new JComboBox();
		comboBox_GioiTinh.setFont(new Font("Arial", Font.BOLD, 15));
		comboBox_GioiTinh.setBounds(634, 219, 90, 42);
		panel_2.add(comboBox_GioiTinh);
		
		JLabel lblNewLabel_2 = new JLabel("Mã khách hàng");
		lblNewLabel_2.setBounds(10, 12, 145, 40);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		
		txtMaKH = new JTextField();
		txtMaKH.setBounds(154, 15, 246, 40);
		panel_2.add(txtMaKH);
		txtMaKH.setFont(new Font("Arial", Font.BOLD, 20));
		txtMaKH.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Họ và tên");
		lblNewLabel_2_1.setBounds(10, 88, 90, 40);
		panel_2.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		
		txtHoTen = new JTextField();
		txtHoTen.setBounds(154, 88, 246, 40);
		panel_2.add(txtHoTen);
		txtHoTen.setFont(new Font("Arial", Font.PLAIN, 15));
		txtHoTen.setColumns(10);
		
		JButton btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setBounds(1010, 35, 160, 59);
		panel_2.add(btnTimKiem);
		btnTimKiem.setIcon(new ImageIcon(Panel_TimKiemKhachHang.class.getResource("/img/ui/search25.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ma;
				String hoTen;
				String ngaySinh;
				String gioiTinh;
				String sdt;
				String diaChi; 
				String Email;
				if(txtMaKH.getText().toString().equalsIgnoreCase("")) {
					ma = "";
				}
				else
				{
					ma = txtMaKH.getText().toString();
				}
				if(txtHoTen.getText().toString().equalsIgnoreCase("")) {
					hoTen = "";
				}
				else
				{
					hoTen  = txtHoTen.getText().toString();
				}
				if(txtSdt.getText().toString().equalsIgnoreCase("")) {
					sdt = "";
				}
				else
				{
					sdt = txtSdt.getText().toString();
				}
				if(txtDiaChi.getText().toString().equalsIgnoreCase("")) {
					diaChi = "";
				}
				else
				{
					diaChi = txtDiaChi.getText().toString();
				}
				if(txtEmail.getText().toString().equalsIgnoreCase("")) {
					Email = "";
				}
				else
				{
					Email = txtEmail.getText().toString();
				}
				Date ngaySinhDate = dateChooser_NgaySinh.getDate();
				if(ngaySinhDate == null) {
					ngaySinh = "is not null";
				}
				else
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					ngaySinh = " = '" +dateFormat.format(ngaySinhDate) + "'";
				}
				
				if(comboBox_GioiTinh.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
					gioiTinh = "";
				}
				else {
					gioiTinh = comboBox_GioiTinh.getSelectedItem().toString();
				}
				model.setRowCount(0);
				ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
				ds = khachHangDao.timKiemKhachHang(ma, hoTen, ngaySinh, sdt, gioiTinh, diaChi, Email);
				System.out.println(ds);
				for (KhachHang lh : ds) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
					Object[] rowData = {lh.getMaKhachHang(),lh.getHoTen(),lh.getGioiTinh() , lh.getSdt() , lh.getDiemTichLuy() , lh.getNgaySinh().format(formatter), lh.getEmail() ,  lh.getDiaChi()};
					model.addRow(rowData);
				}
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setBounds(1010, 138, 160, 59);
		panel_2.add(btnLamMoi);
		btnLamMoi.setIcon(new ImageIcon(Panel_TimKiemKhachHang.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoi();
			}
		});
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 394, 1500, 354);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"MaKhachHang", "HoTen", "GioiTinh", "SDT", "DiemTichLuy" , "NgaySinh", "Email" , "DiaChi"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.setRowHeight(35);
		scrollPane.setViewportView(table);
		layDuLieu();
		dataCombo_GioiTinh();
	}
	
	public void layDuLieu() {
		model.setRowCount(0);
		for (KhachHang lh : khachHangDao.getAllKhachHang()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
			Object[] rowData = {lh.getMaKhachHang(),lh.getHoTen(),lh.getGioiTinh() , lh.getSdt() , lh.getDiemTichLuy() , lh.getNgaySinh().format(formatter), lh.getEmail() ,  lh.getDiaChi()};
			model.addRow(rowData);
		}
	}
	public void dataCombo_GioiTinh() {
		ArrayList<String> ds = new ArrayList<String>();
		ds = khachHangDao.dataComboBox_GioiTinh();
		comboBox_GioiTinh.addItem("Tất cả");
		for (String string : ds) {
			comboBox_GioiTinh.addItem(string);
		}
	}
	public void lamMoi() {
		txtMaKH.setText("");
		txtHoTen.setText("");
		//dateChooser_NgaySinh.removeAll();
		comboBox_GioiTinh.setSelectedIndex(0);
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtMaKH.requestFocus();
		model.setRowCount(0);
		layDuLieu();
	}
	
}
