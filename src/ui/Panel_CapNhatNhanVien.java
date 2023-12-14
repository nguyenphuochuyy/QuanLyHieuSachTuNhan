package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhanVien_Dao;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class Panel_CapNhatNhanVien extends JPanel implements ActionListener{
	private JTextField txtHoTenNhanVien;
	private JTextField txtSDT;
	private JTextField txtTaiKhoan;
	private JTextField txtMatKhau;
	private JTextField txtEmail;
	private JTable table;
	private JTextField txtTenTimKiem;
	private JTextField txtMaNhanVien;
	private DefaultTableModel model;
	private JButton btnTimKiem, btnThem , btnSua, btnLamMoi, btnHuy;
	private JRadioButton rdbNam , rdbNu , rdbQuanLy, rdbBanHang , rdbTrangThai;
	private JDateChooser dcNgaySinh;
	JComboBox comboBox_MaNV = new JComboBox<>();
	NhanVien_Dao nhanVienDao = new NhanVien_Dao();
	private JTextField txaDiaChi;
	
	/**
	 * Create the panel.
	 */
	public Panel_CapNhatNhanVien() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		JLabel lblNewLabel = new JLabel("QUẢN LÍ NHÂN VIÊN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 1500, 36);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 87, 1085, 668);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 24, 156, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Họ và tên");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2.setBounds(567, 24, 103, 26);
		panel.add(lblNewLabel_2);
		
		txtHoTenNhanVien = new JTextField();
		txtHoTenNhanVien.setFont(new Font("Arial", Font.PLAIN, 18));
		txtHoTenNhanVien.setBounds(669, 20, 229, 38);
		panel.add(txtHoTenNhanVien);
		txtHoTenNhanVien.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Số điện thoại");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_4.setBounds(10, 73, 139, 24);
		panel.add(lblNewLabel_4);
		
		rdbTrangThai = new JRadioButton("Đang làm");
		rdbTrangThai.setFont(new Font("Arial", Font.BOLD, 20));
		rdbTrangThai.setBounds(923, 26, 127, 21);
		panel.add(rdbTrangThai);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Arial", Font.PLAIN, 18));
		txtSDT.setColumns(10);
		txtSDT.setBounds(176, 68, 262, 38);
		panel.add(txtSDT);
		
		JLabel lblNewLabel_5 = new JLabel("Tài khoản");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_5.setBounds(10, 124, 103, 33);
		panel.add(lblNewLabel_5);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 18));
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBounds(176, 116, 262, 38);
		panel.add(txtTaiKhoan);
		
		JLabel lblNewLabel_4_1 = new JLabel("Mật khẩu");
		lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(10, 167, 87, 35);
		panel.add(lblNewLabel_4_1);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 18));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(176, 164, 262, 38);
		panel.add(txtMatKhau);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Ngày sinh");
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_4_1_1.setBounds(10, 219, 123, 28);
		panel.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Giới tính");
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_4_1_1_1.setBounds(10, 280, 87, 18);
		panel.add(lblNewLabel_4_1_1_1);
		
		rdbNam = new JRadioButton("Nam");
		rdbNam.setSelected(true);
		rdbNam.setFont(new Font("Arial", Font.BOLD, 20));
		rdbNam.setBounds(190, 278, 87, 21);
		panel.add(rdbNam);
		
		rdbNu = new JRadioButton("Nữ");
		rdbNu.setFont(new Font("Arial", Font.BOLD, 20));
		rdbNu.setBounds(342, 272, 103, 33);
		panel.add(rdbNu);
		
		ButtonGroup gt = new ButtonGroup();
		gt.add(rdbNam);
		gt.add(rdbNu);
		
		JLabel lblNewLabel_6 = new JLabel("Vai trò");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_6.setBounds(567, 98, 87, 24);
		panel.add(lblNewLabel_6);
		
		rdbQuanLy = new JRadioButton("Quản lý");
		rdbQuanLy.setFont(new Font("Arial", Font.BOLD, 20));
		rdbQuanLy.setBounds(670, 96, 146, 26);
		panel.add(rdbQuanLy);
		
		rdbBanHang = new JRadioButton("Bán Hàng");
		rdbBanHang.setSelected(true);
		rdbBanHang.setFont(new Font("Arial", Font.BOLD, 20));
		rdbBanHang.setBounds(883, 100, 146, 22);
		panel.add(rdbBanHang);
		
		ButtonGroup vaiTro = new ButtonGroup();
		vaiTro.add(rdbBanHang);
		vaiTro.add(rdbQuanLy);
		
		JLabel lblNewLabel_4_1_1_2 = new JLabel("Email");
		lblNewLabel_4_1_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_4_1_1_2.setBounds(567, 165, 73, 38);
		panel.add(lblNewLabel_4_1_1_2);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setBounds(669, 169, 229, 38);
		panel.add(txtEmail);
		
		JLabel lblNewLabel_4_1_1_2_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_4_1_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_4_1_1_2_1_1.setBounds(567, 242, 68, 38);
		panel.add(lblNewLabel_4_1_1_2_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 341, 1065, 317);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID" , "Tên nhân viên", "Giới tính" , "SDT" ,"Email", "Tài khoản", "Mật khẩu", "Trạng thái", "Ngày sinh", "Chúc vụ","Địa chỉ"
			}
		));
		
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.setRowHeight(35);
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.setBounds(176, 212, 262, 35);
		panel.add(dcNgaySinh);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Arial", Font.BOLD, 20));
		txtMaNhanVien.setBounds(176, 20, 262, 38);
		panel.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		
		txaDiaChi = new JTextField();
		txaDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txaDiaChi.setEditable(false);
		txaDiaChi.setBounds(669, 242, 229, 43);
		panel.add(txaDiaChi);
		txaDiaChi.setColumns(10);
		
		layDuLieu();
		table.addMouseListener(new MouseListener() {
			
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
				int row = table.getSelectedRow();
				txtMaNhanVien.setText(model.getValueAt(row, 0).toString());
				txtHoTenNhanVien.setText(model.getValueAt(row, 1).toString());
				if(model.getValueAt(row, 2).toString().equalsIgnoreCase("Nam"))
					rdbNam.setSelected(true);
				else
					rdbNu.setSelected(true);
				txtSDT.setText(model.getValueAt(row, 3).toString());
				txtEmail.setText(model.getValueAt(row, 4).toString());
				txtTaiKhoan.setText(model.getValueAt(row, 5).toString());
				txtMatKhau.setText(model.getValueAt(row, 6).toString());
				if((model.getValueAt(row, 7).toString().equalsIgnoreCase("Đang làm")))
					rdbTrangThai.setSelected(true);
				else
					rdbTrangThai.setSelected(false);		
				String dateStr = (String) model.getValueAt(row, 8);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
                LocalDate selectedDate = LocalDate.parse(dateStr, formatter);
				dcNgaySinh.setDate(Date.valueOf(selectedDate));
				if(model.getValueAt(row, 9).toString().equalsIgnoreCase("Nhân viên quản lý"))
					rdbQuanLy.setSelected(true);
				else
					rdbBanHang.setSelected(true);
				txaDiaChi.setText(model.getValueAt(row, 10).toString());
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1115, 87, 360, 277);
		add(panel_2);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Tìm kiếm nhân viên");
		lblNewLabel_7.setForeground(new Color(0, 128, 192));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(10, 10, 340, 32);
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 25));
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Họ tên");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_8.setBounds(10, 135, 86, 32);
		panel_2.add(lblNewLabel_8);
		
		txtTenTimKiem = new JTextField();
		txtTenTimKiem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtTenTimKiem.setBounds(126, 132, 224, 40);
		panel_2.add(txtTenTimKiem);
		txtTenTimKiem.setColumns(10);
		
		btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setIcon(new ImageIcon(Panel_CapNhatNhanVien.class.getResource("/img/ui/search25.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemNhanVien();
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTimKiem.setBounds(114, 201, 126, 48);
		panel_2.add(btnTimKiem);
		
		JLabel lblNewLabel_8_1 = new JLabel("Mã Nhân Viên");
		lblNewLabel_8_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_8_1.setBounds(10, 68, 145, 32);
		panel_2.add(lblNewLabel_8_1);
		comboBox_MaNV.setFont(new Font("Arial", Font.BOLD, 20));
		
	
		comboBox_MaNV.setBounds(201, 63, 97, 40);
		panel_2.add(comboBox_MaNV);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(1115, 390, 360, 365);
		add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("CHỨC NĂNG");
		lblNewLabel_9.setForeground(new Color(0, 128, 192));
		lblNewLabel_9.setBounds(128, 25, 149, 24);
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 20));
		panel_3.add(lblNewLabel_9);
		
		btnThem = new JButton("THÊM");
		btnThem.setBackground(new Color(0, 255, 0));
		btnThem.setIcon(new ImageIcon(Panel_CapNhatNhanVien.class.getResource("/img/ui/add25.png")));
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnThem.setBounds(82, 74, 215, 48);
		panel_3.add(btnThem);
		
		btnSua = new JButton("SỬA");
		btnSua.setBackground(new Color(255, 128, 64));
		btnSua.setIcon(new ImageIcon(Panel_CapNhatNhanVien.class.getResource("/img/ui/fix25.png")));
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));
		btnSua.setBounds(82, 149, 215, 48);
		panel_3.add(btnSua);
		
		btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_CapNhatNhanVien.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(82, 226, 215, 50);
		panel_3.add(btnLamMoi);
		
		btnHuy = new JButton("HỦY");
		btnHuy.setBackground(new Color(137, 118, 139));
		btnHuy.setIcon(new ImageIcon(Panel_CapNhatNhanVien.class.getResource("/img/ui/cancel25.png")));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.setBounds(82, 305, 215, 50);
		panel_3.add(btnHuy);
		btnHuy.setEnabled(false);
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnHuy.addActionListener(this);
		dataComboBoxMaNhanVien();
		huy();
	}
	// tìm kiếm nhân viên theo mã hoặc tên
	public void timKiemNhanVien() {
		String ma; 
		String ten;
		String taiKhoan = "";
		String ngaySinh = "is not null";
		String email = "";
		String sdt = "";
		String diaChi = "";
		String trangThai = " is not null";
		String gioiTinh  = "";
		String chucVu = "is not null";
		
		if(comboBox_MaNV.getSelectedItem().toString().equalsIgnoreCase("none")) {
			ma = "";
		}
		else
		{
			ma = comboBox_MaNV.getSelectedItem().toString();
		}
		if(txtTenTimKiem.getText().equalsIgnoreCase("")) {
			ten = "";
		}
		else
		{
			ten = txtTenTimKiem.getText();
		}
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		ds = nhanVienDao.timKiemNhanVien(ma, ten, taiKhoan, ngaySinh, email, sdt, diaChi, trangThai, gioiTinh, chucVu);
		model.setRowCount(0);
		for (NhanVien lh : ds) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
			Object[] rowdata = {lh.getMaNhanVien(),lh.getHoTenNhanVien(),lh.getGioiTinh() , lh.getSdt() , lh.getEmail() , lh.getTaiKhoan(), lh.getMatKhau(), lh.getTrangThai() , lh.getNgaySinh().format(formatter) , lh.getChucVu(), lh.getDiaChi()};
			model.addRow(rowdata);
 		}
	}
	
	
	public void dataComboBoxMaNhanVien() {
		ArrayList<String> ds = new ArrayList<String>();
		ds = nhanVienDao.dataCombobox_MaNhanvien();
		comboBox_MaNV.addItem("None");
		for (String string : ds) {
			comboBox_MaNV.addItem(string);
		}
	}
	public void huy() {
		btnThem.setEnabled(true);
		btnSua.setEnabled(true);
		btnLamMoi.setEnabled(true);
		btnHuy.setEnabled(false);
		txtMaNhanVien.setEditable(false);
		txtHoTenNhanVien.setEditable(false);
		txtSDT.setEditable(false);
		txtTaiKhoan.setEditable(false);
		txtMatKhau.setEditable(false);
		txtEmail.setEditable(false);
		btnThem.setText("Thêm");
		btnSua.setText("Sửa");		
	}
	
	public void xoaTrang() {
		txtMaNhanVien.setText("");
		txtHoTenNhanVien.setText("");
		txtSDT.setText("");
		txtTaiKhoan.setText("");
		txtMatKhau.setText("");
		txtEmail.setText("");
		txaDiaChi.setText("");
		dcNgaySinh.setDate(null);
		rdbNam.setEnabled(true);
		rdbBanHang.setEnabled(true);
		
	}
	
	public void layDuLieu() {
		model.setRowCount(0);
		for (NhanVien lh : nhanVienDao.getAllNhanVien()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
			Object[] rowData = {lh.getMaNhanVien(),lh.getHoTenNhanVien(),lh.getGioiTinh() , lh.getSdt() , lh.getEmail() , lh.getTaiKhoan(), lh.getMatKhau(), lh.getTrangThai() , lh.getNgaySinh().format(formatter) , lh.getChucVu(), lh.getDiaChi()};
			model.addRow(rowData);
		}
	}
	
	public boolean kiemTra() {
		String tenNV = txtHoTenNhanVien.getText();
		String sdt = txtSDT.getText();
		String taiKhoan = txtTaiKhoan.getText();
		String matKhau = txtMatKhau.getText();
		String email = txtEmail.getText();
		String diaChi = txaDiaChi.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("d-M-y");
        
        java.util.Date selectedDate =  dcNgaySinh.getDate();
        
		
		if(tenNV.equalsIgnoreCase("") || sdt.equalsIgnoreCase("") || taiKhoan.equalsIgnoreCase("") || matKhau.equalsIgnoreCase("") || selectedDate == null || email.equalsIgnoreCase("") || diaChi.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this,"Nhập đầy đủ thông tin");
			return false;
		} else {
			// Kiểm tra ràng buộc với ngày sinh: phải lớn hơn hoặc bằng 18 tuổi
	        String dateString = sdf.format(dcNgaySinh.getDate());
			if(dateString.length() > 0) {
				try {
					LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate ngayHienTai = LocalDate.now();
			        if (localDate.plusYears(18).isAfter(ngayHienTai)) {
			           JOptionPane.showMessageDialog(this,"Nhân viên phải lớn hơn 18 tuổi");
			            return false;
			        }
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "Ngày sinh phải theo dạng dd-mm-yyyy");
					return false;
				}
			}
			if(!(matKhau.length() > 0 && matKhau.matches("^.{8,}$"))) {
				JOptionPane.showMessageDialog(this,"Mật khẩu phải từ 8 kí tự trở lên");
	            return false;
			}

	        // Kiểm tra ràng buộc với số điện thoại (SDT): phải có 10 chữ số
	        if (!(sdt.length() > 0 && sdt.matches("\\d{10}"))) {
	        	JOptionPane.showMessageDialog(this,"Lỗi: SDT phải có 10 kí tự.");
	            return false;
	        }

	        // Kiểm tra ràng buộc với email: phải có đuôi là @gmail.com
	        if (!( email.length() > 0 && email.matches("^[A-Za-z0-9+_.-]+@gmail.com$"))) {
	            JOptionPane.showMessageDialog(this, "Lỗi: Email không đúng định dạng.");
	            return false;
	        }
	        
	        // Kiểm tra địa chỉ không được để trống
	        if (!(diaChi.length() > 0)) {
	            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
	            return false;
	        }
		}

		
		return true;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		if(o.equals(btnLamMoi)) {
			xoaTrang();
			txtTenTimKiem.setText("");
			comboBox_MaNV.setSelectedIndex(0);
			model.setRowCount(0);
			layDuLieu();
		}
		if(o.equals(btnHuy)) {
			huy();
			xoaTrang();
		}
		if(o.equals(btnThem)) {
			if(btnThem.getText().equalsIgnoreCase("Thêm")) {
				btnThem.setText("Lưu");
				btnSua.setEnabled(false);
				btnLamMoi.setEnabled(false);
				btnHuy.setEnabled(true);
				txtHoTenNhanVien.setEditable(true);
				txtSDT.setEditable(true);
				txtTaiKhoan.setEditable(true);
				txtMatKhau.setEditable(true);
				txtEmail.setEditable(true);
				txaDiaChi.setEditable(true);
				xoaTrang();
				try {
					String sql = "IF ((SELECT MAX(CAST(RIGHT(MaNhanVien, LEN(MaNhanVien) - 2) AS int)) + 1 FROM NhanVien) < 10) "
							+ "SELECT CONCAT('NV0', MAX(CAST(RIGHT(MaNhanVien, LEN(MaNhanVien) - 2) AS int)) + 1) FROM NhanVien "
							+ "ELSE SELECT CONCAT('NV', MAX(CAST(RIGHT(MaNhanVien, LEN(MaNhanVien) - 2) AS int)) + 1) FROM NhanVien ";
					java.sql.Statement statement =  con.createStatement();
					ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
					while(rs.next()) {
						txtMaNhanVien.setText(rs.getString(1));
					}
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			} 
			else {
				if(kiemTra() == true) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
					String gt;
					if(rdbNam.isSelected() == true) {
						gt = "Nam";
					}
					else {
						gt = "Nữ";
					}
					String trangThai;
					if(rdbTrangThai.isSelected()==true)
						trangThai = "Đang làm";
					else
						trangThai = "Đã nghỉ";
					String chucVu;
					if(rdbBanHang.isSelected()==true)
						chucVu = "Nhân viên bán hàng";
					else
						chucVu = "Nhân viên quản lý";
					SimpleDateFormat sdf = new SimpleDateFormat("d-M-y");
					String DateString = sdf.format(dcNgaySinh.getDate());
					java.util.Date selectedDate =  dcNgaySinh.getDate();
					LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					NhanVien nv = new NhanVien(txtMaNhanVien.getText(), txtHoTenNhanVien.getText(),gt,txtSDT.getText(),txtEmail.getText(),txtTaiKhoan.getText(),txtMatKhau.getText(),trangThai,localDate,chucVu,txaDiaChi.getText());
					nhanVienDao.themNhanVien(nv);
					String row[] = {txtMaNhanVien.getText(), txtHoTenNhanVien.getText(),gt,txtSDT.getText(),txtEmail.getText(),txtTaiKhoan.getText(),txtMatKhau.getText(),trangThai,DateString,chucVu,txaDiaChi.getText()};
					model.addRow(row);
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					huy();
					xoaTrang();
				}
			}
		}	
		if(o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(this, "Chọn Khách hàng để sửa");
			}
			else {
				if(btnSua.getText().equalsIgnoreCase("Sửa")) {
					 btnSua.setText("Lưu");
					 btnLamMoi.setEnabled(false);
					 btnThem.setEnabled(false);
					 btnHuy.setEnabled(true);
					 txtHoTenNhanVien.setEditable(true);
					 txtSDT.setEditable(true);
					 txtTaiKhoan.setEditable(true);
					 txtMatKhau.setEditable(true);
					 txtEmail.setEditable(true);
					 txaDiaChi.setEditable(true);	 
				 }
				else {
					if(kiemTra()) {
						SimpleDateFormat sdf = new SimpleDateFormat("d-M-y");
			            String DateString = sdf.format(dcNgaySinh.getDate());
			            java.util.Date selectedDate =  dcNgaySinh.getDate();
			            LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			            String gt;
			            if(rdbNam.isSelected() == true) {
							gt = "Nam";
						}
						else {
							gt = "Nữ";
						}
			            String trangThai;
						if(rdbTrangThai.isSelected())
							trangThai = "Đang làm";
						else
							trangThai = "Đã nghỉ";
						String chucVu;
						if(rdbBanHang.isSelected()==true)
							chucVu = "Nhân viên bán hàng";
						else
							chucVu = "Nhân viên quản lý";
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
						NhanVien nv = new NhanVien(txtMaNhanVien.getText(), txtHoTenNhanVien.getText(),gt,txtSDT.getText(),txtEmail.getText(),txtTaiKhoan.getText(),txtMatKhau.getText(),trangThai,localDate, chucVu ,txaDiaChi.getText());
						nhanVienDao.suaNhanVien(nv);
						model.setValueAt(txtHoTenNhanVien.getText(), row, 1);
						model.setValueAt(gt, row, 2);
						model.setValueAt(txtSDT.getText(), row, 3);
						model.setValueAt(txtEmail.getText(), row, 4);
						model.setValueAt(txtTaiKhoan.getText(), row, 5);
						model.setValueAt(txtMatKhau.getText(), row, 6);
						model.setValueAt(trangThai , row, 7);
						model.setValueAt(DateString, row, 8);
						model.setValueAt(chucVu, row, 9);
						model.setValueAt(txaDiaChi.getText(), row, 10);						
						JOptionPane.showMessageDialog(this, "Sửa thành công");
						huy();
						xoaTrang();
						btnSua.setText("Sửa");
					}
				}
			}
			
		}
	}
}
