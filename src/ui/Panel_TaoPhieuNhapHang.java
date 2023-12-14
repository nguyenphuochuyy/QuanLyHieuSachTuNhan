package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import connectDB.ConnectDB;
import dao.ChiTietPhieuNhapHang_Dao;
import dao.PhieuNhapHang_Dao;
import dao.Sach_Dao;
import dao.VanPhongPham_Dao;
import entity.ChiTietPhieuNhapHang;
import entity.HoaDon;
import entity.NhaCungCap;
import entity.PhieuDatHang;
import entity.PhieuNhapHang;
import entity.Sach;
import entity.VanPhongPham;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class Panel_TaoPhieuNhapHang extends JPanel implements ActionListener{
	private JTextField txtMaPhieuNhap;
	private JTable table_Sach;
	private JTable table_Vpp;
	private JTable table_CTPhieuNhap;
	private JTextField txtTong;
	private DefaultTableModel model_Sach, model_Vpp,model_ChiTiet;
	private JButton btnThem, btnXoa, btnSua, btnLamMoi, btnHuy, btnTaoPhieuNhap, btnTimKiem;
	private JComboBox cbNhaCungCap;
	private String maNhanVien;

	
	Sach_Dao sachDao = new Sach_Dao();
	VanPhongPham_Dao vppDao = new VanPhongPham_Dao();
	PhieuNhapHang_Dao phieuNhapHangDao = new PhieuNhapHang_Dao();
	ChiTietPhieuNhapHang_Dao ctpnhDao = new ChiTietPhieuNhapHang_Dao();
	private JTextField txtTimKiem;
	/**
	 * Create the panel.
	 */
	public Panel_TaoPhieuNhapHang(String ma) {
		maNhanVien = ma;
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel = new JLabel("PHIẾU NHẬP HÀNG");
		lblNewLabel.setForeground(new Color(64, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(660, 0, 307, 52);
		add(lblNewLabel);
		
		txtMaPhieuNhap = new JTextField();
		txtMaPhieuNhap.setFont(new Font("Arial", Font.BOLD, 15));
		txtMaPhieuNhap.setEditable(false);
		txtMaPhieuNhap.setColumns(10);
		txtMaPhieuNhap.setBounds(10, 12, 127, 39);
		add(txtMaPhieuNhap);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 80, 1323, 213);
		add(scrollPane);
		
		table_Sach = new JTable();
		table_Sach.setFont(new Font("Arial", Font.PLAIN, 15));
		table_Sach.setModel(model_Sach = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mã sách", "Tên sách", "Thể loại sách", "Nhà xuất bản", "Giá nhập", "Số lượng còn", "Số trang", "Tác giả"
			}
		));
		table_Sach.setRowHeight(35);
		table_Sach.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table_Sach.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table_Sach.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		table_Sach.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_Sach.getColumnModel().getColumn(1).setPreferredWidth(280);
		table_Sach.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_Sach.getColumnModel().getColumn(3).setPreferredWidth(150);
//		table_Sach.getColumnModel().getColumn(4).setPreferredWidth(30);
//		table_Sach.getColumnModel().getColumn(5).setPreferredWidth(20);
		table_Sach.getColumnModel().getColumn(7).setPreferredWidth(150);
		

		scrollPane.setViewportView(table_Sach);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(172, 299, 1323, 181);
		add(scrollPane_1);
		
		table_Vpp = new JTable();
		table_Vpp.setFont(new Font("Arial", Font.PLAIN, 15));
		table_Vpp.setModel(model_Vpp = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mã sản phẩm","Tên sản phẩm","Nhà sản xuất","Giá nhập","Số lượng còn","Loại văn phòng phẩm"
			}
		));
		table_Vpp.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table_Vpp.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table_Vpp.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table_Vpp.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_Vpp.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_Vpp.setRowHeight(35);

		scrollPane_1.setViewportView(table_Vpp);
		
		btnThem = new JButton("THÊM");
		btnThem.setBackground(new Color(128, 255, 0));
		btnThem.setIcon(new ImageIcon(Panel_TaoPhieuNhapHang.class.getResource("/img/ui/add25.png")));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnThem.setBounds(10, 145, 138, 63);
		add(btnThem);
		
		btnXoa = new JButton("XÓA");
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setIcon(new ImageIcon(Panel_TaoPhieuNhapHang.class.getResource("/img/ui/delete.png")));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setBounds(10, 251, 138, 63);
		add(btnXoa);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(172, 490, 1323, 181);
		add(scrollPane_2);
		
		table_CTPhieuNhap = new JTable();
		table_CTPhieuNhap.setModel(model_ChiTiet = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "Gi\u00E1 B\u00E1n", "S\u1ED1 L\u01B0\u1EE3ng", "Th\u00E0nh Ti\u1EC1n"
			}
		));
		table_CTPhieuNhap.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table_CTPhieuNhap.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table_CTPhieuNhap.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table_CTPhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_CTPhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_CTPhieuNhap.setRowHeight(35);
		scrollPane_2.setViewportView(table_CTPhieuNhap);
		JLabel lblNewLabel_3 = new JLabel("TỔNG");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_3.setBounds(922, 702, 122, 47);
		add(lblNewLabel_3);
		
		txtTong = new JTextField();
		txtTong.setText("0");
		txtTong.setEditable(false);
		txtTong.setFont(new Font("Arial", Font.BOLD, 35));
		txtTong.setBounds(1027, 700, 307, 47);
		add(txtTong);
		txtTong.setColumns(10);
		txtTong.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_2 = new JLabel("VND");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_2.setBounds(1345, 699, 109, 52);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Nhà Cung Cấp");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setBounds(172, 28, 139, 42);
		add(lblNewLabel_4);
		
		cbNhaCungCap = new JComboBox();
		cbNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 15));
		cbNhaCungCap.setBounds(280, 28, 190, 42);
		add(cbNhaCungCap);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(979, 43, 366, 27);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(1345, 40, 127, 32);
		add(btnTimKiem);
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select TenNhaCungCap from NhaCungCap";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				cbNhaCungCap.addItem(rs.getString(1).toString());			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_TaoPhieuNhapHang.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(10, 358, 141, 63);
		add(btnLamMoi);
		
		btnTaoPhieuNhap = new JButton("TẠO");
		btnTaoPhieuNhap.setBackground(new Color(128, 128, 64));
		btnTaoPhieuNhap.setIcon(new ImageIcon(Panel_TaoPhieuNhapHang.class.getResource("/img/ui/create30.png")));
		btnTaoPhieuNhap.setFont(new Font("Arial", Font.BOLD, 15));
		btnTaoPhieuNhap.setBounds(10, 466, 138, 63);
		add(btnTaoPhieuNhap);
		
		table_Sach.addMouseListener(new MouseListener() {
					
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
						int rowSach = table_Sach.getSelectedRow();
						if(rowSach != -1) {
							table_Vpp.clearSelection();
						} 	
					}
				});
				
			table_Vpp.addMouseListener(new MouseListener() {
				
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
					int rowVpp = table_Vpp.getSelectedRow();
					if(rowVpp != -1) {
						table_Sach.clearSelection();
					}
				}
			});
				
		layDuLieuSach();
		layDuLieuVpp();
		txtMaPhieuNhap.setText(layMaPhieuNhap());
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
//		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTaoPhieuNhap.addActionListener(this);
		btnTimKiem.addActionListener(this);
		
		
	}
	
	public void layDuLieuSach() {
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		for (Sach sp : sachDao.getAllSach()) {
			Object[] rowData = {sp.getMaSach(),sp.getTenSach(),sp.getTheLoai() , sp.getNhaXuatBan() , df.format(sp.getGiaBan()*90/100) ,  sp.getSoLuong() , sp.getSoTrang() , sp.getTacGia()};
			model_Sach.addRow(rowData);
			
		}
	}
	
	public void layDuLieuVpp() {
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		for (VanPhongPham sp : vppDao.getAllVanPhongPham()) {
			Object[] rowData = {sp.getMaSanPham(),sp.getTenSanPham(),sp.getNhaSanXuat() , df.format(sp.getGiaBan()*90/100) ,  sp.getSoLuong() , sp.getLoaiVanPhongPham()};
			model_Vpp.addRow(rowData);
			
		}
	}
	
	public String layMaPhieuNhap() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "IF ((SELECT MAX(CAST(RIGHT(MaPhieuNhapHang, LEN(MaPhieuNhapHang) - 2) AS int)) + 1 FROM PhieuNhapHang) < 10)  "
					+ "SELECT CONCAT('NH0', MAX(CAST(RIGHT(MaPhieuNhapHang, LEN(MaPhieuNhapHang) - 2) AS int)) + 1) FROM PhieuNhapHang  "
					+ "ELSE SELECT CONCAT('NH', MAX(CAST(RIGHT(MaPhieuNhapHang, LEN(MaPhieuNhapHang) - 2) AS int)) + 1) FROM PhieuNhapHang  ";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maPhieuNhap = rs.getString(1);
				return maPhieuNhap;
			}
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return null;
	}
	
	public void layDanhSachTimKiem() {
		String tenSanPham ;
		DecimalFormat df = new DecimalFormat("#,###");
		DecimalFormat df2 = new DecimalFormat("#,###VNĐ");
		if(txtTimKiem.getText().equalsIgnoreCase("")) {
			tenSanPham = "";
		}
		else {
			tenSanPham = txtTimKiem.getText();
		}
		model_Sach.setRowCount(0);
		model_Vpp.setRowCount(0);
		for(Sach sach : sachDao.timKiemSach("", tenSanPham, "", "")) {			 
			 Object[] rowData = {sach.getMaSach(),sach.getTenSach(),sach.getTheLoai(),sach.getNhaXuatBan(),df2.format(sach.getGiaBan()*90/100),sach.getSoLuong(),sach.getSoTrang(),sach.getTacGia()};
			 model_Sach.addRow(rowData);
		}
		
		for (VanPhongPham vanPhongPham : vppDao.timKiemVanPhongPham("", tenSanPham, "", "")) {
			Object[] rowData = {vanPhongPham.getMaSanPham(),vanPhongPham.getTenSanPham(),df2.format(vanPhongPham.getGiaBan()*90/100),vanPhongPham.getSoLuong(),vanPhongPham.getLoaiVanPhongPham() };
			model_Vpp.addRow(rowData);
		}
		
	}
	
	
	
	public void xoaTrang() {
		txtMaPhieuNhap.setText(layMaPhieuNhap());
//		model_ChiTiet.setRowCount(0);
		txtTong.setText("0");
		txtTimKiem.setText("");
		model_Sach.setRowCount(0);
		layDuLieuSach();
		model_Vpp.setRowCount(0);
		layDuLieuVpp();
	}
	
	
	public int kiemTraTrungCTPhieuNhapSach(String ma) {
		int rowSach = table_Sach.getSelectedRow();
		for(int j=0;j<table_CTPhieuNhap.getRowCount();j++) {
			if(model_ChiTiet.getValueAt(j, 0).toString().equalsIgnoreCase(ma))
				return j;
		}
		return -1;
	}
	
	public int kiemTraTrungCTPhieuNhapVpp(String ma) {
		int rowVpp = table_Vpp.getSelectedRow();
		for(int j=0;j<table_CTPhieuNhap.getRowCount();j++) {
			if(model_ChiTiet.getValueAt(j, 0).toString().equalsIgnoreCase(ma))
				return j;
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
//		Thêm sản phẩm vào giỏ hàng
		DecimalFormat df = new DecimalFormat("#,###");
		DecimalFormat df2 = new DecimalFormat("#,### VNĐ");
		if(o.equals(btnThem)) { 
			int rowSach = table_Sach.getSelectedRow();
			int rowVpp = table_Vpp.getSelectedRow();

			if(rowSach == -1 && rowVpp == -1) {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm để thêm");
			} 
			else if(rowSach != -1) {
				String soLuongNhap = JOptionPane.showInputDialog(this,"Nhập số lượng:");
				if (soLuongNhap != null) {
				    try {
				        int soLuong = Integer.parseInt(soLuongNhap);
				        if(soLuong <= 0)
				        	JOptionPane.showMessageDialog(this, "Số lượng phải lón hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        else {
				        	Number number ;
				        	if(kiemTraTrungCTPhieuNhapSach(model_Sach.getValueAt(rowSach,0).toString()) != -1) {
				        		int dongTrung = kiemTraTrungCTPhieuNhapSach(model_Sach.getValueAt(rowSach, 0).toString());
				        		int capNhatSoLuong = soLuong + Integer.parseInt(model_ChiTiet.getValueAt(dongTrung, 4).toString());
				        		model_ChiTiet.setValueAt(capNhatSoLuong,dongTrung, 4);
				        		number = df.parse(model_Sach.getValueAt(rowSach, 4).toString());
					            double thanhTien = number.doubleValue() * capNhatSoLuong;
					        	String dfThanhTien = df.format(thanhTien);
					        	model_ChiTiet.setValueAt(dfThanhTien , dongTrung, 5);
				        	} else {
				        		number = df.parse(model_Sach.getValueAt(rowSach, 4).toString());
					            double thanhTien = number.doubleValue() * soLuong;
					        	String dfThanhTien = df2.format(thanhTien);
				        		Object[] rowData = {model_Sach.getValueAt(rowSach, 0).toString(),model_Sach.getValueAt(rowSach, 1).toString(),"Sách",model_Sach.getValueAt(rowSach, 4).toString(),soLuongNhap, dfThanhTien};
				        		model_ChiTiet.addRow(rowData);
				        	}
				        }
				    } catch (NumberFormatException e1) {
				        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ. Vui lòng nhập một số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				    	} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			}
			else if(rowVpp != -1) {
				String soLuongNhap = JOptionPane.showInputDialog(this,"Nhập số lượng:");
				if (soLuongNhap != null) {
				    try {
				        int soLuong = Integer.parseInt(soLuongNhap);
				        if(soLuong <= 0)
				        	JOptionPane.showMessageDialog(this, "Số lượng phải lón hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        else {
				        	Number number ;
				        	if(kiemTraTrungCTPhieuNhapVpp(model_Vpp.getValueAt(rowVpp,0).toString()) != -1) {
				        		int dongTrung = kiemTraTrungCTPhieuNhapVpp(model_Vpp.getValueAt(rowVpp, 0).toString());
				        		int capNhatSoLuong = soLuong + Integer.parseInt(model_ChiTiet.getValueAt(dongTrung, 4).toString());
				        		model_ChiTiet.setValueAt(capNhatSoLuong,dongTrung, 4);
				        		number = df.parse(model_Vpp.getValueAt(rowVpp, 3).toString());
					            double thanhTien = number.doubleValue() * capNhatSoLuong;
					        	String dfThanhTien = df.format(thanhTien);
					        	model_ChiTiet.setValueAt(dfThanhTien , dongTrung, 5);
				        	} else {
				        		number = df.parse(model_Vpp.getValueAt(rowVpp, 3).toString());
					            double thanhTien = number.doubleValue() * soLuong;
					        	String dfThanhTien = df2.format(thanhTien);
				        		Object[] rowData = {model_Vpp.getValueAt(rowVpp, 0).toString(),model_Vpp.getValueAt(rowVpp, 1).toString(),"Văn phòng phẩm",model_Vpp.getValueAt(rowVpp, 3).toString(),soLuongNhap, dfThanhTien};
				        		model_ChiTiet.addRow(rowData);
				        	}
				        }
				    } catch (NumberFormatException e1) {
				        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ. Vui lòng nhập một số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				    	} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			}
			try {
				Double tongTien = (double) 0;
				for(int i=0;i<table_CTPhieuNhap.getRowCount();i++) {
					Number number = df.parse(model_ChiTiet.getValueAt(i, 5).toString());
					tongTien+= number.doubleValue();
				}
				txtTong.setText(df.format(tongTien));
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(btnXoa)) {
			int row = table_CTPhieuNhap.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xóa");
			} else {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					model_ChiTiet.removeRow(row);
					Double tongTien = (double) 0;
					try {
						for(int i=0;i<table_CTPhieuNhap.getRowCount();i++) {
							Number number = df.parse(model_ChiTiet.getValueAt(i, 5).toString());
							tongTien+= number.doubleValue();
						}
						txtTong.setText(df.format(tongTien));
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}	
			}
		}
		if(o.equals(btnTaoPhieuNhap)) {
			if(table_CTPhieuNhap.getRowCount() != 0) {
				String maPhieuNhapHang = txtMaPhieuNhap.getText();
				String nhaCungCap;
				try {
					String sql = "select MaNhaCungCap from NhaCungCap where TenNhaCungCap = N'" + cbNhaCungCap.getSelectedItem().toString() + "'" ;
					java.sql.Statement statement =  con.createStatement();
					ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
					while(rs.next()) {
						nhaCungCap = rs.getString(1);	
						PhieuNhapHang dh = new PhieuNhapHang(maPhieuNhapHang, LocalDate.now(), maNhanVien, nhaCungCap);
						phieuNhapHangDao.themPhieuNhapHang(dh);
						
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
				for(int i=0;i<table_CTPhieuNhap.getRowCount();i++) {
					String maSP = model_ChiTiet.getValueAt(i, 0).toString();
					int soLuong = Integer.parseInt(model_ChiTiet.getValueAt(i, 4).toString());
//					DecimalFormat df = new DecimalFormat("#,###");		
					try {
						NumberFormatter formatter = new NumberFormatter(df);
		            	Double giaNhap = df.parse(model_ChiTiet.getValueAt(i, 3).toString()).doubleValue();	
						ChiTietPhieuNhapHang cthd = new ChiTietPhieuNhapHang(maPhieuNhapHang, maSP, soLuong, giaNhap);
						ctpnhDao.themChiTietPhieuNhapHang(cthd);
						sachDao.capNhatSoLuongSanPham(-soLuong, maSP);
					} catch (Exception e2) {
						// TODO: handle exception
					}			
				}
				JOptionPane.showMessageDialog(this, "Lập phiếu nhập hàng thành công");
				xoaTrang();
				phieuNhapHangDao.xuatPhieuNhap(maPhieuNhapHang);
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm");
			}
		}
		if(o.equals(btnLamMoi)) {
			xoaTrang();	
		} 
		if(o.equals(btnTimKiem)) {
			layDanhSachTimKiem();
		}
	}
}
