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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.KhuyenMai_Dao;
import entity.KhuyenMai;
import entity.NhaCungCap;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class Panel_KhuyenMai extends JPanel implements ActionListener{
	private JTextField txtMaKhuyenMai;
	private JTextField txtTenKhuyenMai;
	private JTable table;
	private JTextField txtMucGiam;
	private JButton btnThem,btnSua,btnLamMoi,btnHuy;
	private JDateChooser dcNgayKetThuc,dcNgayBatDau;
	private JComboBox cbTrangThai;
	private DefaultTableModel model;

	
	KhuyenMai_Dao khuyenMaiDao = new KhuyenMai_Dao();
	/**
	 * Create the panel.
	 */
	public Panel_KhuyenMai() {
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel("CHƯƠNG TRÌNH KHUYẾN MÃI");
		lblNewLabel.setBounds(571, 0, 442, 52);
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 86, 527, 655);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Khuyến Mãi");
		lblNewLabel_1.setBounds(10, 18, 124, 31);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		
		txtMaKhuyenMai = new JTextField();
		txtMaKhuyenMai.setFont(new Font("Arial", Font.BOLD, 15));
		txtMaKhuyenMai.setColumns(10);
		txtMaKhuyenMai.setBounds(204, 10, 281, 45);
		panel.add(txtMaKhuyenMai);
		txtMaKhuyenMai.setEditable(false);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên Chương Trình");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 116, 166, 31);
		panel.add(lblNewLabel_1_1);
		
		txtTenKhuyenMai = new JTextField();
		txtTenKhuyenMai.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenKhuyenMai.setColumns(10);
		txtTenKhuyenMai.setBounds(204, 108, 281, 45);
		panel.add(txtTenKhuyenMai);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Mức Khuyến Mãi");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 209, 166, 31);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("%");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(453, 204, 32, 35);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ngày Bắt Đầu");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(10, 301, 124, 45);
		panel.add(lblNewLabel_1_3);
		
		dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.setBounds(204, 301, 281, 45);
		panel.add(dcNgayBatDau);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Ngày Kết Thúc");
		lblNewLabel_1_3_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1.setBounds(10, 397, 124, 45);
		panel.add(lblNewLabel_1_3_1);
		
		dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.setBounds(204, 397, 281, 45);
		panel.add(dcNgayKetThuc);
		
		btnThem = new JButton("THÊM");
		btnThem.setBackground(new Color(0, 255, 0));
		btnThem.setIcon(new ImageIcon(Panel_KhuyenMai.class.getResource("/img/ui/add25.png")));
		btnThem.setFont(new Font("Arial", Font.BOLD, 13));
		btnThem.setBounds(10, 582, 114, 50);
		panel.add(btnThem);
		
		btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_KhuyenMai.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(261, 582, 137, 50);
		panel.add(btnLamMoi);
		
		btnSua = new JButton("SỬA");
		btnSua.setBackground(new Color(255, 128, 64));
		btnSua.setIcon(new ImageIcon(Panel_KhuyenMai.class.getResource("/img/ui/fix25.png")));
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));
		btnSua.setBounds(134, 581, 117, 50);
		panel.add(btnSua);
		
		cbTrangThai = new JComboBox();
		cbTrangThai.setFont(new Font("Arial", Font.BOLD, 15));
		cbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Chưa diễn ra", "Đang diễn ra", "Đã kết thúc"}));
		cbTrangThai.setBounds(204, 488, 144, 37);
		panel.add(cbTrangThai);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Trạng Thái");
		lblNewLabel_1_3_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1_1.setBounds(10, 483, 124, 45);
		panel.add(lblNewLabel_1_3_1_1);
		
		txtMucGiam = new JTextField();
		txtMucGiam.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMucGiam.setColumns(10);
		txtMucGiam.setBounds(204, 195, 239, 45);
		panel.add(txtMucGiam);
		
		btnHuy = new JButton("HỦY");
		btnHuy.setBackground(new Color(141, 114, 131));
		btnHuy.setIcon(new ImageIcon(Panel_KhuyenMai.class.getResource("/img/ui/cancel25.png")));
		btnHuy.setEnabled(false);
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.setBounds(408, 582, 109, 50);
		panel.add(btnHuy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(547, 86, 963, 655);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Ch\u01B0\u01A1ng Tr\u00ECnh", "T\u00EAn Ch\u01B0\u01A1ng Tr\u00ECnh", "M\u1EE9c Khuy\u1EBFn M\u00E3i (%)", "T\u1EEB Ng\u00E0y", "\u0110\u1EBFn Ng\u00E0y", "Tr\u1EA1ng Th\u00E1i"
			}
		));
		table.setRowHeight(35);
		scrollPane.setViewportView(table);
		dongInput();
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
				txtMaKhuyenMai.setText(model.getValueAt(row, 0).toString());
				txtTenKhuyenMai.setText(model.getValueAt(row, 1).toString());
				txtMucGiam.setText(model.getValueAt(row, 2).toString());
				String bd = (String) model.getValueAt(row, 3); 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate selectedDateBD = LocalDate.parse(bd, formatter);
				dcNgayBatDau.setDate(Date.valueOf(selectedDateBD));
				
				String kt = (String) model.getValueAt(row, 4); 
                LocalDate selectedDateKT = LocalDate.parse(kt, formatter);
				dcNgayKetThuc.setDate(Date.valueOf(selectedDateKT));
				if(model.getValueAt(row, 5).toString().equalsIgnoreCase("Chưa diễn ra"))
					cbTrangThai.setSelectedIndex(0);
				else if(model.getValueAt(row, 5).toString().equalsIgnoreCase("Đang diễn ra"))
					cbTrangThai.setSelectedIndex(1);
				else
					cbTrangThai.setSelectedIndex(2);


			}
		});
		layDuLieu();
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnHuy.addActionListener(this);

	}
	
	public void moInput() {
		txtTenKhuyenMai.setEditable(true);
		txtMucGiam.setEditable(true);
		cbTrangThai.setEditable(true);
	}
	
	public void dongInput() {
		txtTenKhuyenMai.setEditable(false);
		txtMucGiam.setEditable(false);
		cbTrangThai.setEditable(false);
	}
	
	public void layDuLieu() {
		model.setRowCount(0);
		khuyenMaiDao.capNhatTrangThaiKhuyenMai();
		for (KhuyenMai lh : khuyenMaiDao.getAllKhuyenMai()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			Object[] rowData = {lh.getMaKhuyenMai(),lh.getTenKhuyenMai(), lh.getTiLeGiam() ,  lh.getNgayBatDau().format(formatter) ,  lh.getNgayKetThuc().format(formatter) , lh.getTrangThai()};
			model.addRow(rowData);
		}
	}
	
	public void xoaTrang() {
		txtMaKhuyenMai.setText("");
		txtTenKhuyenMai.setText("");
		txtMucGiam.setText("");
		dcNgayBatDau.setDate(null);
		dcNgayKetThuc.setDate(null);
	}
	
	public void huy() {
		btnThem.setEnabled(true);
		btnSua.setEnabled(true);
		btnLamMoi.setEnabled(true);
		btnHuy.setEnabled(false); 
		btnSua.setText("Sửa");
		btnThem.setText("Thêm");
		xoaTrang();
		dongInput();
	}
	
	public String layMaKhuyenMai() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "IF ((SELECT MAX(CAST(RIGHT(MaKhuyenMai, LEN(MaKhuyenMai) - 2) AS int)) + 1 FROM KhuyenMai) < 10)  "
					+ "SELECT CONCAT('KM0', MAX(CAST(RIGHT(MaKhuyenMai, LEN(MaKhuyenMai) - 2) AS int)) + 1) FROM KhuyenMai  "
					+ "ELSE SELECT CONCAT('KM', MAX(CAST(RIGHT(MaKhuyenMai, LEN(MaKhuyenMai) - 2) AS int)) + 1) FROM KhuyenMai  ";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maHd = rs.getString(1);
				return maHd;
			}
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return null;
	}
	
	public boolean kiemTra() {
		String maKM = txtMaKhuyenMai.getText();
		String tenKM = txtTenKhuyenMai.getText();
		String tiLe = txtMucGiam.getText();
		if(maKM.equalsIgnoreCase("") || tenKM.equalsIgnoreCase("") || tiLe.equalsIgnoreCase("") || dcNgayBatDau.getDate() == null || dcNgayKetThuc.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin");
			return false;
		} else {
			try {
				int mucGiam = Integer.parseInt(txtMucGiam.getText());
				if(mucGiam < 0) {
					JOptionPane.showMessageDialog(this, "Mức giảm phải là số dương");
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Mức giảm phải là số nguyên");
				return false;
			}
			
			if(dcNgayBatDau.getDate().after(dcNgayKetThuc.getDate())) {
				JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
				return false;
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		if(o.equals(btnThem)) {
			if(btnThem.getText().equalsIgnoreCase("Thêm")) {
				xoaTrang();
				moInput();
				txtMaKhuyenMai.setText(layMaKhuyenMai());
				btnThem.setText("Lưu");
				btnSua.setEnabled(false);
				btnLamMoi.setEnabled(false);
				btnHuy.setEnabled(true);		
			} else {
				if(kiemTra()) {
					String maKM = txtMaKhuyenMai.getText();
					String tenKM = txtTenKhuyenMai.getText();
					int mucKM = Integer.parseInt(txtMucGiam.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("d-M-y");
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					String batDauString = sdf.format(dcNgayBatDau.getDate());
					java.util.Date batDauDate =  dcNgayBatDau.getDate();
					LocalDate batDaulocalDate = batDauDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					
					String ketThucString = sdf.format(dcNgayKetThuc.getDate());
					java.util.Date ketThucDate =  dcNgayKetThuc.getDate();
					LocalDate ketThuclocalDate = ketThucDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	
					String trangThai;
					if ((LocalDate.now().isAfter(batDaulocalDate) && LocalDate.now().isBefore(ketThuclocalDate)) || LocalDate.now().isEqual(batDaulocalDate) || LocalDate.now().isEqual(ketThuclocalDate))
						trangThai = "Đang diễn ra";
					else if(LocalDate.now().isBefore(batDaulocalDate))
						trangThai = "Chưa diễn ra";
					else
						trangThai = "Đã kết thúc";
					KhuyenMai km = new KhuyenMai(maKM, tenKM, mucKM, batDaulocalDate, ketThuclocalDate, trangThai);
					khuyenMaiDao.themKhuyenMai(km);
					xoaTrang();
					layDuLieu();
					huy();
					btnThem.setText("Thêm");
					dongInput();
				}
			}
		}
		if(o.equals(btnHuy)) {
			huy();
		}
		if(o.equals(btnLamMoi)) {
			xoaTrang();
		}
		if(o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Chọn khuyến mãi để sửa");
			} else {
				if(btnSua.getText().equalsIgnoreCase("Sửa")) {
					moInput();
					btnSua.setText("Lưu");
					btnThem.setEnabled(false);
					btnLamMoi.setEnabled(false);
					btnHuy.setEnabled(true);
				}
				else {
					if(kiemTra()) {
						String maKM = txtMaKhuyenMai.getText();
						String tenKM = txtTenKhuyenMai.getText();
						int mucKM = Integer.parseInt(txtMucGiam.getText());
						SimpleDateFormat sdf = new SimpleDateFormat("d-M-y");
						String batDauString = sdf.format(dcNgayBatDau.getDate());
						java.util.Date batDauDate =  dcNgayBatDau.getDate();
						LocalDate batDaulocalDate = batDauDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						
						String ketThucString = sdf.format(dcNgayKetThuc.getDate());
						java.util.Date ketThucDate =  dcNgayKetThuc.getDate();
						LocalDate ketThuclocalDate = ketThucDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	
						String trangThai;
						if ((LocalDate.now().isAfter(batDaulocalDate) && LocalDate.now().isBefore(ketThuclocalDate)) || LocalDate.now().isEqual(batDaulocalDate) || LocalDate.now().isEqual(ketThuclocalDate))
							trangThai = "Đang diễn ra";
						else if(LocalDate.now().isBefore(batDaulocalDate))
							trangThai = "Chưa diễn ra";
						else
							trangThai = "Đã kết thúc";
						KhuyenMai km = new KhuyenMai(maKM, tenKM, mucKM, batDaulocalDate, ketThuclocalDate, trangThai);
						khuyenMaiDao.suaKhuyenMai(km);
						JOptionPane.showMessageDialog(this, "Sửa thành công");
						xoaTrang();
						layDuLieu();
						huy();
						btnSua.setText("Sửa");
						dongInput();
					}
				}
			}
		}
		
	}
}
