package ui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.KhachHang_Dao;
import entity.KhachHang;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Panel_CapNhatKhachHang extends JPanel implements ActionListener{
	private JTextField txtTimKiem;
	private JTable table;
	private JTextField txtTenKhachHang;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtMaKhachHang;
	private JButton btnThem, btnXoa, btnSua, btnXoaTrang, btnTimKiem, btnHuy;
	private JRadioButton rdbNam, rdbNu;
	private JDateChooser dcNgaySinh;
	private DefaultTableModel model;
	
	KhachHang_Dao khachHangDao = new KhachHang_Dao();
	private JTextField txtDiemTichLuy;

	/**
	 * Create the panel.
	 */
	public Panel_CapNhatKhachHang() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		JLabel lblNewLabel = new JLabel("QUẢN LÍ KHÁCH HÀNG");
		lblNewLabel.setForeground(new Color(128, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 10, 1500, 45);
		add(lblNewLabel);
		JPanel panel = new JPanel();
		panel.setBounds(727, 27, 1, 1);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 76, 1101, 666);
		add(panel_1);
		panel_1.setLayout(null);
		
		btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setIcon(new ImageIcon(Panel_CapNhatKhachHang.class.getResource("/img/ui/search25.png")));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 13));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setBounds(219, 52, 141, 41);
		panel_1.add(btnTimKiem);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTimKiem.setBounds(10, 56, 199, 30);
		panel_1.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm khách hàng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 33, 199, 19);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 121, 1091, 545);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"MaKhachHang", "HoTen", "GioiTinh", "SDT", "DiemTichLuy", "NgaySinh", "Email", "DiaChi"
			}
		));
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.setRowHeight(35);
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(1121, 76, 370, 666);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setBounds(4, 10, 45, 33);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tên");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_3.setBounds(4, 65, 77, 30);
		panel_2.add(lblNewLabel_3);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setFont(new Font("Arial", Font.BOLD, 15));
		txtTenKhachHang.setBounds(112, 64, 238, 33);
		panel_2.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Ngày sinh");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setBounds(4, 119, 109, 33);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Giới tính");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5.setBounds(4, 162, 103, 31);
		panel_2.add(lblNewLabel_5);
		
		rdbNam = new JRadioButton("Nam");
		rdbNam.setSelected(true);
		rdbNam.setFont(new Font("Arial", Font.BOLD, 15));
		rdbNam.setBounds(112, 167, 103, 21);
		panel_2.add(rdbNam);
		
		rdbNu = new JRadioButton("Nữ");
		rdbNu.setFont(new Font("Arial", Font.BOLD, 15));
		rdbNu.setBounds(246, 167, 103, 21);
		panel_2.add(rdbNu);
		
		ButtonGroup gt = new ButtonGroup();
		gt.add(rdbNam);
		gt.add(rdbNu);
		
		JLabel lblNewLabel_6 = new JLabel("SĐT");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_6.setBounds(4, 203, 45, 33);
		panel_2.add(lblNewLabel_6);
		
		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setFont(new Font("Arial", Font.BOLD, 15));
		txtSDT.setColumns(10);
		txtSDT.setBounds(111, 203, 239, 33);
		panel_2.add(txtSDT);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7.setBounds(4, 312, 71, 33);
		panel_2.add(lblNewLabel_7);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setFont(new Font("Arial", Font.BOLD, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(112, 312, 238, 33);
		panel_2.add(txtEmail);
		
		JLabel lblNewLabel_8 = new JLabel("Địa chỉ");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_8.setBounds(4, 371, 83, 33);
		panel_2.add(lblNewLabel_8);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setFont(new Font("Arial", Font.BOLD, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(112, 371, 238, 33);
		panel_2.add(txtDiaChi);
		
		btnThem = new JButton("THÊM");
		btnThem.setBackground(new Color(0, 255, 0));
		btnThem.setIcon(new ImageIcon(Panel_CapNhatKhachHang.class.getResource("/img/ui/add25.png")));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnThem.setBounds(25, 428, 128, 52);
		panel_2.add(btnThem);
		
		btnXoa = new JButton("XÓA");
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setIcon(new ImageIcon(Panel_CapNhatKhachHang.class.getResource("/img/ui/delete.png")));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setBounds(202, 428, 128, 52);
		panel_2.add(btnXoa);
		
		btnSua = new JButton("SỬA");
		btnSua.setBackground(new Color(255, 128, 64));
		btnSua.setIcon(new ImageIcon(Panel_CapNhatKhachHang.class.getResource("/img/ui/fix25.png")));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));
		btnSua.setBounds(25, 511, 128, 52);
		panel_2.add(btnSua);
		
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.setBounds(112, 119, 238, 33);
		panel_2.add(dcNgaySinh);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setFont(new Font("Arial", Font.BOLD, 15));
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(113, 10, 238, 33);
		panel_2.add(txtMaKhachHang);
		
		btnXoaTrang = new JButton("LÀM MỚI");
		btnXoaTrang.setBackground(new Color(255, 255, 128));
		btnXoaTrang.setIcon(new ImageIcon(Panel_CapNhatKhachHang.class.getResource("/img/ui/refresh25.png")));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoaTrang.setBounds(202, 512, 128, 52);
		panel_2.add(btnXoaTrang);
		
		JLabel lblNewLabel_7_1 = new JLabel("Điểm tích lũy");
		lblNewLabel_7_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_1.setBounds(4, 261, 128, 30);
		panel_2.add(lblNewLabel_7_1);
		
		txtDiemTichLuy = new JTextField();
		txtDiemTichLuy.setFont(new Font("Arial", Font.BOLD, 15));
		txtDiemTichLuy.setEditable(false);
		txtDiemTichLuy.setColumns(10);
		txtDiemTichLuy.setBounds(112, 257, 238, 33);
		panel_2.add(txtDiemTichLuy);
		
		btnHuy = new JButton("HỦY");
		btnHuy.setBackground(new Color(141, 114, 131));
		btnHuy.setIcon(new ImageIcon(Panel_CapNhatKhachHang.class.getResource("/img/ui/cancel25.png")));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.setBounds(112, 590, 128, 52);
		panel_2.add(btnHuy);
		btnHuy.setEnabled(false);
		
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
				txtMaKhachHang.setText(model.getValueAt(row, 0).toString());
				txtTenKhachHang.setText(model.getValueAt(row, 1).toString());
				if(model.getValueAt(row, 2).toString().equalsIgnoreCase("Nam"))
					rdbNam.setSelected(true);
				else
					rdbNu.setSelected(true);
				txtSDT.setText(model.getValueAt(row, 3).toString());
				txtDiemTichLuy.setText(model.getValueAt(row, 4).toString());
				String dateStr = (String) model.getValueAt(row, 5); 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
                LocalDate selectedDate = LocalDate.parse(dateStr, formatter);
				dcNgaySinh.setDate(Date.valueOf(selectedDate));
				txtEmail.setText(model.getValueAt(row, 6).toString());
				txtDiaChi.setText(model.getValueAt(row, 7).toString());	
				
			}
		});
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnHuy.addActionListener(this);
		btnTimKiem.addActionListener(this);
		
		
	}
	
	public void layDuLieu() {
		model.setRowCount(0);
		for (KhachHang lh : khachHangDao.getAllKhachHang()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
			Object[] rowData = {lh.getMaKhachHang(),lh.getHoTen(),lh.getGioiTinh() , lh.getSdt() , lh.getDiemTichLuy() , lh.getNgaySinh().format(formatter), lh.getEmail() ,  lh.getDiaChi()};
			model.addRow(rowData);
		}
	}
	
	public void xoaTrang() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		rdbNam.setSelected(true);
		txtSDT.setText("");
		txtDiemTichLuy.setText("");
		dcNgaySinh.setDate(null);
		txtEmail.setText("");
		txtDiaChi.setText("");
		txtTimKiem.setText("");
		model.setRowCount(0);
		layDuLieu();
	}
	
	public boolean kiemTra() {
		String tenKH = txtTenKhachHang.getText();
		String sdt = txtSDT.getText();
		String diem = txtDiemTichLuy.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("d-M-y");
        
        java.util.Date selectedDate =  dcNgaySinh.getDate();
        
		
		if(tenKH.equalsIgnoreCase("") || sdt.equalsIgnoreCase("") || diem.equalsIgnoreCase("") || selectedDate == null || email.equalsIgnoreCase("") || diaChi.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this,"Nhập đầy đủ thông tin");
			return false;
		}
		else {
			// Kiểm tra ràng buộc với ngày sinh: phải lớn hơn hoặc bằng 18 tuổi
	        String dateString = sdf.format(dcNgaySinh.getDate());
			if(dateString.length() > 0) {
				try {
					LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate ngayHienTai = LocalDate.now();
			        if (localDate.plusYears(5).isAfter(ngayHienTai)) {
			           JOptionPane.showMessageDialog(this,"Khách hàng phải lớn hơn 5 tuổi");
			            return false;
			        }
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "Ngày sinh phải theo dạng dd-mm-yyyy");
					return false;
				}
			}  

	        // Kiểm tra ràng buộc với số điện thoại (SDT): phải có 10 chữ số
	        if (!(sdt.length() > 0 && sdt.matches("\\d{10}"))) {
	        	JOptionPane.showMessageDialog(this,"Lỗi: SDT không đúng định dạng.");
	            return false;
	        }

	        // Kiểm tra ràng buộc với điểm tích lũy: phải là một con số
	        if(diem.length()>0) {
				try {
					int diemTL = Integer.parseInt(diem);
					if(diemTL < 0){
						JOptionPane.showMessageDialog(this, "Điểm là số nguyên dương");
						return false;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Điểm phải là kí tự số");
					return false;
				}
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
	        
	     // Nếu tất cả các kiểm tra đều thành công, trả về true
	        return true;
		}
    }

	
	public void huy() {
		 btnXoa.setEnabled(true);
		 btnXoaTrang.setEnabled(true);
		 btnSua.setEnabled(true);
		 btnThem.setEnabled(true);
		 btnHuy.setEnabled(false);
		 txtMaKhachHang.setEditable(false);
		 txtTenKhachHang.setEditable(false);
		 txtSDT.setEditable(false);
		 txtDiemTichLuy.setEditable(false);
		 txtEmail.setEditable(false);
		 txtDiaChi.setEditable(false);
		 xoaTrang();
		 btnThem.setText("Thêm");
		 btnSua.setText("Sửa");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		if(o.equals(btnHuy)) {
			huy();
		}
		if(o.equals(btnThem)) {
			 if(btnThem.getText().equalsIgnoreCase("Thêm")) {
				 btnThem.setText("Lưu");
				 btnXoa.setEnabled(false);
				 btnXoaTrang.setEnabled(false);
				 btnSua.setEnabled(false);
				 btnHuy.setEnabled(true);
				 txtTenKhachHang.setEditable(true);
				 txtSDT.setEditable(true);
				 txtDiemTichLuy.setEditable(true);
				 txtEmail.setEditable(true);
				 txtDiaChi.setEditable(true);
				 btnThem.setText("Lưu");
				 try {
						String sql = "IF ((SELECT MAX(CAST(RIGHT(MaKhachHang, LEN(MaKhachHang) - 2) AS int)) + 1 FROM KhachHang WHERE MaKhachHang LIKE 'KH%') < 10)"
								+ "SELECT CONCAT('KH0', MAX(CAST(RIGHT(MaKhachHang, LEN(MaKhachHang) - 2) AS int)) + 1) FROM KhachHang WHERE MaKhachHang LIKE 'KH%'"
								+ "ELSE SELECT CONCAT('KH', MAX(CAST(RIGHT(MaKhachHang, LEN(MaKhachHang) - 2) AS int)) + 1) FROM KhachHang WHERE MaKhachHang LIKE 'KH%'";
						java.sql.Statement statement =  con.createStatement();
						ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
						while(rs.next()) {
							txtMaKhachHang.setText(rs.getString(1));
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
						SimpleDateFormat sdf = new SimpleDateFormat("d-M-y");
			            String DateString = sdf.format(dcNgaySinh.getDate());
			            java.util.Date selectedDate =  dcNgaySinh.getDate();
			            LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						KhachHang kh = new KhachHang(txtMaKhachHang.getText(), txtTenKhachHang.getText(),gt,txtSDT.getText(),Integer.parseInt(txtDiemTichLuy.getText()),txtEmail.getText(),txtDiaChi.getText(),localDate);
						khachHangDao.themKhachHang(kh);
						String row[] = {txtMaKhachHang.getText(), txtTenKhachHang.getText(),gt,txtSDT.getText(),txtDiemTichLuy.getText(), DateString,txtEmail.getText(),txtDiaChi.getText()};
						model.addRow(row);
						JOptionPane.showMessageDialog(null, "Thêm");
						btnThem.setText("Thêm");
						huy();
						xoaTrang();
					}
			 }
		}
		if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(this, "Chọn Khách hàng để xóa");
			}
			else {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					khachHangDao.xoaKhachHang(model.getValueAt(row, 0).toString());
					model.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công");
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
					 btnXoa.setEnabled(false);
					 btnXoaTrang.setEnabled(false);
					 btnThem.setEnabled(false);
					 btnHuy.setEnabled(true);
					 txtTenKhachHang.setEditable(true);
					 txtSDT.setEditable(true);
					 txtDiemTichLuy.setEditable(true);
					 txtEmail.setEditable(true);
					 txtDiaChi.setEditable(true);	 
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
						txtMaKhachHang.setEditable(true);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
						KhachHang kh = new KhachHang(txtMaKhachHang.getText(), txtTenKhachHang.getText(),gt,txtSDT.getText(),Integer.parseInt(txtDiemTichLuy.getText()),txtEmail.getText(),txtDiaChi.getText(),localDate);
						khachHangDao.suaKhachHang(kh);
						model.setValueAt(txtTenKhachHang.getText(), row, 1);
						model.setValueAt(gt, row, 2);
						model.setValueAt(txtSDT.getText(), row, 3);
						model.setValueAt(txtDiemTichLuy.getText(), row, 4);
						model.setValueAt(DateString, row, 5);
						model.setValueAt(txtEmail.getText(), row, 6);
						model.setValueAt(txtDiaChi.getText(), row, 7);
						
						JOptionPane.showMessageDialog(this, "Sửa thành công");
						huy();
						xoaTrang();
						btnSua.setText("Sửa");
					}
				}
			}	
		}
		if(o.equals(btnTimKiem)) {
			String ten = txtTimKiem.getText();
			model.setRowCount(0);
			for (KhachHang lh : khachHangDao.timKiemKhachHang(ten)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
				Object[] rowData = {lh.getMaKhachHang(),lh.getHoTen(),lh.getGioiTinh() , lh.getSdt() , lh.getDiemTichLuy() , lh.getNgaySinh().format(formatter), lh.getEmail() ,  lh.getDiaChi()};
				model.addRow(rowData);
			}
		}
	}
}
