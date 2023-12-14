package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhaCungCap_Dao;
import entity.KhachHang;
import entity.NhaCungCap;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
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
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Panel_CapNhapNhaCungCap extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnThem, btnXoa, btnSua, btnLamMoi, btnHuy;
	
	NhaCungCap_Dao nccDao = new NhaCungCap_Dao();
	private JTextField txtTimKiem;
	private JButton btnTimKiem;

	/**
	 * Create the panel.
	 */
	public Panel_CapNhapNhaCungCap() {
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(20, 132, 518, 621);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã nhà cung cấp");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 86, 262, 27);
		panel.add(lblNewLabel);
		
		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaNCC.setBounds(214, 78, 294, 44);
		panel.add(txtMaNCC);
		txtMaNCC.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên nhà cung cấp");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 160, 262, 27);
		panel.add(lblNewLabel_1);
		
		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(214, 151, 294, 44);
		panel.add(txtTenNCC);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 239, 120, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Địa chỉ");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(10, 319, 89, 27);
		panel.add(lblNewLabel_1_2);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSDT.setColumns(10);
		txtSDT.setBounds(214, 231, 294, 44);
		panel.add(txtSDT);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(214, 311, 294, 44);
		panel.add(txtDiaChi);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(1088, 93, 199, 30);
		add(txtTimKiem);
		
		btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 13));
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setBounds(1299, 82, 141, 41);
		add(btnTimKiem);
		
		btnThem = new JButton("THÊM");
		btnThem.setBackground(new Color(0, 255, 0));
		btnThem.setIcon(new ImageIcon(Panel_CapNhapNhaCungCap.class.getResource("/img/ui/add25.png")));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnThem.setBounds(84, 405, 134, 56);
		panel.add(btnThem);
		
		btnXoa = new JButton("XÓA");
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setIcon(new ImageIcon(Panel_CapNhapNhaCungCap.class.getResource("/img/ui/delete.png")));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setBounds(84, 484, 134, 56);
		panel.add(btnXoa);
		
		btnSua = new JButton("SỬA");
		btnSua.setBackground(new Color(255, 128, 64));
		btnSua.setIcon(new ImageIcon(Panel_CapNhapNhaCungCap.class.getResource("/img/ui/fix25.png")));
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));
		btnSua.setBounds(288, 405, 140, 56);
		panel.add(btnSua);
		
		btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_CapNhapNhaCungCap.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(288, 484, 140, 56);
		panel.add(btnLamMoi);
		
		JLabel lblNewLabel_3 = new JLabel("THÔNG TIN NHÀ CUNG CẤP");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(64, 128, 128));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 10, 498, 44);
		panel.add(lblNewLabel_3);
		
		btnHuy = new JButton("HỦY");
		btnHuy.setBackground(new Color(137, 118, 139));
		btnHuy.setIcon(new ImageIcon(Panel_CapNhapNhaCungCap.class.getResource("/img/ui/cancel25.png")));
		btnHuy.setEnabled(false);
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.setBounds(198, 555, 140, 56);
		panel.add(btnHuy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(575, 132, 917, 621);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 18));
		table.setModel(model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã nhà cung cấp" , "Tên nhà cung cấp", "Số điện thoại" , "Địa chỉ"
				}
			));
		table.setRowHeight(35);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("CẬP NHẬT NHÀ CUNG CẤP");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(128, 64, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_2.setBounds(0, 28, 1520, 44);
		add(lblNewLabel_2);
		
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
				txtMaNCC.setText(model.getValueAt(row, 0).toString());
				txtTenNCC.setText(model.getValueAt(row, 1).toString());
				txtSDT.setText(model.getValueAt(row, 2).toString());
				txtDiaChi.setText(model.getValueAt(row, 3).toString());			
			}
		});
		layDuLieu();
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnHuy.addActionListener(this);
		btnTimKiem.addActionListener(this);

		
		
		
		
	}
	
	public void layDuLieu() {
		model.setRowCount(0);
		for (NhaCungCap lh : nccDao.getAllNhaCungCap()) {
			Object[] rowData = {lh.getMaNCC(),lh.getTenNCC(), lh.getSdt() ,  lh.getDiaChi()};
			model.addRow(rowData);
		}
	}
	
	public void layDanhSachTimKiem() {
		model.setRowCount(0);
		String ten = txtTimKiem.getText();
		for (NhaCungCap lh : nccDao.timKiemNhaCungCap(ten, "")) {
			Object[] rowData = {lh.getMaNCC(),lh.getTenNCC(), lh.getSdt() ,  lh.getDiaChi()};
			model.addRow(rowData);
		}
	}
	
	public void xoaTrang() {
		txtMaNCC.setText("");
		txtTenNCC.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtTimKiem.setText("");
		model.setRowCount(0);
		layDuLieu();
	}
	
	public void huy() {
		 btnXoa.setEnabled(true);
		 btnLamMoi.setEnabled(true);
		 btnSua.setEnabled(true);
		 btnThem.setEnabled(true);
		 btnHuy.setEnabled(false);
//		 txtTenKhachHang.setEditable(false);
//		 txtSDT.setEditable(false);
//		 txtDiaChi.setEditable(false);
		 btnThem.setText("Thêm");
		 btnXoa.setText("Xóa");
		 btnSua.setText("Sửa");
		 xoaTrang();

	}
	
	public boolean kiemTra() {
		String tenNCC = txtTenNCC.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();

        
		
		if(tenNCC.equalsIgnoreCase("") || sdt.equalsIgnoreCase("") ||  diaChi.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this,"Nhập đầy đủ thông tin");
			return false;
		}
		else {

	        // Kiểm tra ràng buộc với số điện thoại (SDT): phải có 10 chữ số
	        if (!(sdt.length() > 0 && sdt.matches("\\d{10}"))) {
	        	JOptionPane.showMessageDialog(this,"Lỗi: SDT không đúng định dạng.");
	            return false;
	        }
	     // Nếu tất cả các kiểm tra đều thành công, trả về true
	        return true;
		}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		if(o.equals(btnLamMoi)) {
			xoaTrang();
		}
		if(o.equals(btnHuy)) {
			huy();
		}
		if(o.equals(btnThem)) {
			 if(btnThem.getText().equalsIgnoreCase("Thêm")) {
				 btnThem.setText("Lưu");
				 btnXoa.setEnabled(false);
				 btnLamMoi.setEnabled(false);
				 btnSua.setEnabled(false);
				 btnHuy.setEnabled(true);
//				 txtTenKhachHang.setEditable(true);
//				 txtSDT.setEditable(true);
//				 txtDiemTichLuy.setEditable(true);
//				 txtEmail.setEditable(true);
//				 txtDiaChi.setEditable(true);
				 try {
						String sql = "IF ((SELECT MAX(CAST(RIGHT(MaNhaCungCap, LEN(MaNhaCungCap) - 3) AS int)) + 1 FROM NhaCungCap WHERE MaNhaCungCap LIKE 'NCC%') < 10)"
								+ "SELECT CONCAT('NCC0', MAX(CAST(RIGHT(MaNhaCungCap, LEN(MaNhaCungCap) - 3) AS int)) + 1) FROM NhaCungCap WHERE MaNhaCungCap LIKE 'NCC%'"
								+ "ELSE SELECT CONCAT('NCC', MAX(CAST(RIGHT(MaNhaCungCap, LEN(MaNhaCungCap) - 3) AS int)) + 1) FROM NhaCungCap WHERE MaNhaCungCap LIKE 'NCC%'";
						java.sql.Statement statement =  con.createStatement();
						ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
						while(rs.next()) {
							txtMaNCC.setText(rs.getString(1));
						}
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
			 }
			 else {
				 if(kiemTra() == true) {
						NhaCungCap ncc = new NhaCungCap(txtMaNCC.getText(), txtTenNCC.getText(),txtDiaChi.getText(),txtSDT.getText());
						nccDao.themNhaCungCap(ncc);
						String row[] = {txtMaNCC.getText(), txtTenNCC.getText(),txtSDT.getText(),txtDiaChi.getText(),};
						model.addRow(row);
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						btnThem.setText("Lưu");
						huy();
						xoaTrang();
					}
			 }
		}
		if(o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(this, "Chọn nhà cung cấp để sửa");
			}
			else {
				if(btnSua.getText().equalsIgnoreCase("Sửa")) {
					 btnSua.setText("Lưu");
					 btnXoa.setEnabled(false);
					 btnLamMoi.setEnabled(false);
					 btnThem.setEnabled(false);
					 btnHuy.setEnabled(true);
//					 txtTenKhachHang.setEditable(true);
//					 txtSDT.setEditable(true);
//					 txtDiemTichLuy.setEditable(true);
//					 txtEmail.setEditable(true);
//					 txtDiaChi.setEditable(true);	 
				 }
				else {
					if(kiemTra()) {
						NhaCungCap ncc = new NhaCungCap(txtMaNCC.getText(), txtTenNCC.getText(),txtDiaChi.getText(),txtSDT.getText());
						nccDao.suaNhaCungCap(ncc);
						model.setValueAt(txtTenNCC.getText(), row, 1);
						model.setValueAt(txtSDT.getText(), row, 2);
						model.setValueAt(txtDiaChi.getText(), row, 3);
						
						JOptionPane.showMessageDialog(this, "Sửa thành công");
						huy();
						xoaTrang();
						btnSua.setText("Sửa");
					}
				}
			}	
		}
		if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(this, "Chọn nhà cung cấp để xóa");
			}
			else {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					nccDao.xoaNhaCungCap(model.getValueAt(row, 0).toString());
					model.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công");
				}		
			}
		}
		if(o.equals(btnTimKiem)) {
			layDanhSachTimKiem();
		}
	}
}
