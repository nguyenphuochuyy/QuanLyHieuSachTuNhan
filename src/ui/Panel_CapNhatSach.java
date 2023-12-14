package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Sach_Dao;
import entity.Sach;
import groovyjarjarcommonscli.ParseException;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class Panel_CapNhatSach extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtGiaBan;
	private JTextField txtNhaXuatBan;
	private JTextField txtViTri;
	private JTextField txtTheLoai;
	private JTextField txtSoLuong;
	private DefaultTableModel model;
	private JLabel lblAnh;
	private JButton btnThem, btnXoa, btnSua, btnXoaTrang, btnLayAnh, btnHuy;
	private byte[] selectedImage;
	Sach_Dao sachDao = new Sach_Dao();
	private JTextField txtSoTrang;
	private JTextField txtTacGia;
	/**
	 * Create the panel.
	 */
	public Panel_CapNhatSach() {
		
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBounds(0, 41, 1520, 777);
		 setLayout(null);
		 
		 JPanel panel_BangSp = new JPanel();
		 panel_BangSp.setBounds(10, 412, 1464, 326);
		 add(panel_BangSp);
		 panel_BangSp.setLayout(null);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(0, 0, 1460, 326);
		 panel_BangSp.add(scrollPane);
		 
		 DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	      rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		 
		 table = new JTable();
		 table.setFont(new Font("Arial", Font.PLAIN, 15));
		 table.setModel(model = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Mã sách", "Tên sách", "Thể loại", "Nhà xuất bản", "Giá bán", "Vị trí kệ", "Số lượng còn", "Số trang", "Tác giả"
					}
				) {
				});
		 table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
		 table.getColumnModel().getColumn(1).setPreferredWidth(270);
		 table.getColumnModel().getColumn(2).setPreferredWidth(70);
		 table.getColumnModel().getColumn(3).setPreferredWidth(70);
		 table.getColumnModel().getColumn(4).setPreferredWidth(50);
		 table.getColumnModel().getColumn(5).setPreferredWidth(50);
		 table.getColumnModel().getColumn(6).setPreferredWidth(30);
		 table.getColumnModel().getColumn(7).setPreferredWidth(20);
		 table.getColumnModel().getColumn(8).setPreferredWidth(80);
		 table.setRowHeight(35);
		 scrollPane.setViewportView(table);
		 JPanel panel_TieuDe = new JPanel();
		 panel_TieuDe.setBounds(10, 10, 1500, 44);
		 add(panel_TieuDe);
		 panel_TieuDe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 
		 JLabel lblNewLabel = new JLabel("CẬP NHẬT SÁCH");
		 lblNewLabel.setForeground(new Color(255, 0, 0));
		 lblNewLabel.setBackground(new Color(255, 255, 255));
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		 lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 panel_TieuDe.add(lblNewLabel);
		 
		 lblAnh = new JLabel("");
		 lblAnh.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		 lblAnh.setBounds(1241, 81, 233, 253);
		 add(lblAnh);
		 layDuLeu();
		 
		 JPanel panel_ChucNang = new JPanel();
		 panel_ChucNang.setBounds(25, 64, 179, 332);
		 add(panel_ChucNang);
		 panel_ChucNang.setLayout(null);
		 
		 btnThem = new JButton("THÊM");
		 btnThem.setBackground(new Color(128, 255, 0));
		 btnThem.setIcon(new ImageIcon(Panel_CapNhatSach.class.getResource("/img/ui/add25.png")));
		 btnThem.setFont(new Font("Arial", Font.BOLD, 13));
		 btnThem.setBounds(10, 11, 147, 44);
		 panel_ChucNang.add(btnThem);
		 
		 btnSua = new JButton("SỬA");
		 btnSua.setBackground(new Color(255, 128, 64));
		 btnSua.setIcon(new ImageIcon(Panel_CapNhatSach.class.getResource("/img/ui/fix25.png")));
		 btnSua.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnSua.setFont(new Font("Arial", Font.BOLD, 13));
		 btnSua.setBounds(10, 67, 147, 44);
		 panel_ChucNang.add(btnSua);
		 
		 btnXoa = new JButton("XÓA");
		 btnXoa.setBackground(new Color(255, 0, 0));
		 btnXoa.setIcon(new ImageIcon(Panel_CapNhatSach.class.getResource("/img/ui/delete.png")));
		 btnXoa.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnXoa.setFont(new Font("Arial", Font.BOLD, 13));
		 btnXoa.setBounds(10, 121, 147, 44);
		 panel_ChucNang.add(btnXoa);
		 
		 btnXoaTrang = new JButton("LÀM MỚI");
		 btnXoaTrang.setBackground(new Color(255, 255, 128));
		 btnXoaTrang.setIcon(new ImageIcon(Panel_CapNhatSach.class.getResource("/img/ui/refresh25.png")));
		 btnXoaTrang.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 13));
		 btnXoaTrang.setBounds(10, 176, 147, 44);
		 panel_ChucNang.add(btnXoaTrang);
		 
		 btnHuy = new JButton("HỦY");
		 btnHuy.setBackground(new Color(141, 114, 131));
		 btnHuy.setIcon(new ImageIcon(Panel_CapNhatSach.class.getResource("/img/ui/cancel25.png")));
		 btnHuy.setFont(new Font("Arial", Font.BOLD, 13));
		 btnHuy.setBounds(10, 231, 147, 44);
		 panel_ChucNang.add(btnHuy);
		 
		 
		 JPanel panel_ThongTinsp = new JPanel();
		 panel_ThongTinsp.setBounds(237, 81, 949, 321);
		 add(panel_ThongTinsp);
		 panel_ThongTinsp.setBorder(new LineBorder(new Color(0, 128, 64)));
		 panel_ThongTinsp.setLayout(null);
		 
		 JLabel lblNewLabel_1 = new JLabel("Mã Sách");
		 lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1.setBounds(10, 34, 113, 27);
		 panel_ThongTinsp.add(lblNewLabel_1);
		 
		 txtMaSach = new JTextField();
		 txtMaSach.setFont(new Font("Arial", Font.BOLD, 15));
		 txtMaSach.setBounds(133, 34, 248, 35);
		 panel_ThongTinsp.add(txtMaSach);
		 txtMaSach.setColumns(10);
		 
		 JLabel lblNewLabel_1_1 = new JLabel("Tên Sách");
		 lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1_1.setBounds(10, 90, 113, 27);
		 panel_ThongTinsp.add(lblNewLabel_1_1);
		 
		 JLabel lblNewLabel_1_3 = new JLabel("Giá Bán ");
		 lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1_3.setBounds(10, 264, 113, 27);
		 panel_ThongTinsp.add(lblNewLabel_1_3);
		 
		 JLabel lblNewLabel_1_4 = new JLabel("Nhà Xuất Bản ");
		 lblNewLabel_1_4.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1_4.setBounds(10, 207, 128, 27);
		 panel_ThongTinsp.add(lblNewLabel_1_4);
		 
		 JLabel lblNewLabel_1_6 = new JLabel("Thể Loại ");
		 lblNewLabel_1_6.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1_6.setBounds(10, 145, 128, 27);
		 panel_ThongTinsp.add(lblNewLabel_1_6);
		 
		 JLabel lblNewLabel_1_7 = new JLabel("Số Lượng ");
		 lblNewLabel_1_7.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1_7.setBounds(426, 90, 128, 27);
		 panel_ThongTinsp.add(lblNewLabel_1_7);
		 
		 txtTenSach = new JTextField();
		 txtTenSach.setFont(new Font("Arial", Font.PLAIN, 15));
		 txtTenSach.setColumns(10);
		 txtTenSach.setBounds(133, 88, 248, 35);
		 panel_ThongTinsp.add(txtTenSach);
		 
		 txtGiaBan = new JTextField();
		 txtGiaBan.setFont(new Font("Arial", Font.PLAIN, 15));
		 txtGiaBan.setColumns(10);
		 txtGiaBan.setBounds(133, 263, 248, 35);
		 panel_ThongTinsp.add(txtGiaBan);
		 
		 txtNhaXuatBan = new JTextField();
		 txtNhaXuatBan.setFont(new Font("Arial", Font.PLAIN, 15));
		 txtNhaXuatBan.setColumns(10);
		 txtNhaXuatBan.setBounds(133, 206, 248, 35);
		 panel_ThongTinsp.add(txtNhaXuatBan);
		 
		 txtViTri = new JTextField();
		 txtViTri.setFont(new Font("Arial", Font.PLAIN, 15));
		 txtViTri.setColumns(10);
		 txtViTri.setBounds(564, 33, 248, 35);
		 panel_ThongTinsp.add(txtViTri);
		 
		 txtTheLoai = new JTextField();
		 txtTheLoai.setFont(new Font("Arial", Font.PLAIN, 15));
		 txtTheLoai.setColumns(10);
		 txtTheLoai.setBounds(133, 144, 248, 35);
		 panel_ThongTinsp.add(txtTheLoai);
		 
		 txtSoLuong = new JTextField();
		 txtSoLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		 txtSoLuong.setColumns(10);
		 txtSoLuong.setBounds(564, 89, 248, 35);
		 panel_ThongTinsp.add(txtSoLuong);
		 
		 JLabel lblNewLabel_1_6_1 = new JLabel("Vị Trí ");
		 lblNewLabel_1_6_1.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1_6_1.setBounds(426, 34, 128, 27);
		 panel_ThongTinsp.add(lblNewLabel_1_6_1);
		 
		 JLabel lblNewLabel_1_7_1 = new JLabel("Số Trang");
		 lblNewLabel_1_7_1.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1_7_1.setBounds(426, 152, 128, 27);
		 panel_ThongTinsp.add(lblNewLabel_1_7_1);
		 
		 JLabel lblNewLabel_1_7_2 = new JLabel("Tác Giả");
		 lblNewLabel_1_7_2.setFont(new Font("Arial", Font.BOLD, 15));
		 lblNewLabel_1_7_2.setBounds(426, 214, 128, 27);
		 panel_ThongTinsp.add(lblNewLabel_1_7_2);
		 
		 txtSoTrang = new JTextField();
		 txtSoTrang.setFont(new Font("Arial", Font.PLAIN, 15));
		 txtSoTrang.setColumns(10);
		 txtSoTrang.setBounds(564, 156, 248, 35);
		 panel_ThongTinsp.add(txtSoTrang);
		 
		 txtTacGia = new JTextField();
		 txtTacGia.setFont(new Font("Arial", Font.PLAIN, 15));
		 txtTacGia.setColumns(10);
		 txtTacGia.setBounds(564, 218, 248, 35);
		 panel_ThongTinsp.add(txtTacGia);
		 
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
					txtMaSach.setText(model.getValueAt(row, 0).toString());
					txtTenSach.setText(model.getValueAt(row, 1).toString());
					txtTheLoai.setText(model.getValueAt(row, 2).toString());
					txtNhaXuatBan.setText(model.getValueAt(row, 3).toString());
					DecimalFormat df = new DecimalFormat("#,### VNĐ");
			        Number parsedNumber;
					try {
						parsedNumber = df.parse(model.getValueAt(row, 4).toString());
						double giaBan = parsedNumber.doubleValue();
						txtGiaBan.setText(String.valueOf(giaBan));
					} catch (java.text.ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					txtViTri.setText(model.getValueAt(row, 5).toString());
					txtSoLuong.setText(model.getValueAt(row, 6).toString());

					txtSoTrang.setText(model.getValueAt(row, 7).toString());
					txtTacGia.setText(model.getValueAt(row, 8).toString());	
						ConnectDB.getInstance();
						Connection con = ConnectDB.getConnection();
						try {
							String sql = "select HinhAnh from SanPham where MaSanPham = '"+ model.getValueAt(row, 0).toString() +"'";
							java.sql.Statement statement =  con.createStatement();
							ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
							while(rs.next()) {
								byte[] imageData = rs.getBytes("HinhAnh");
								if (imageData != null) {
					                ImageIcon imageIcon = new ImageIcon(imageData);
					                Image scaledImage = imageIcon.getImage().getScaledInstance(233, 253, Image.SCALE_SMOOTH);
					                imageIcon = new ImageIcon(scaledImage);
					                lblAnh.setIcon(imageIcon);
					            } else {
					                lblAnh.setIcon(null);
					            }
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}

			});
		 btnThem.addActionListener(this);
		 btnXoa.addActionListener(this);
		 btnSua.addActionListener(this);
		 btnXoaTrang.addActionListener(this);
		 btnHuy.addActionListener(this);
		 
		 btnLayAnh = new JButton("LẤY ẢNH");
		 btnLayAnh.setBackground(new Color(128, 128, 0));
		 btnLayAnh.setIcon(new ImageIcon(Panel_CapNhatSach.class.getResource("/img/ui/import25.png")));
		 btnLayAnh.setBounds(1287, 357, 149, 44);
		 add(btnLayAnh);
		 btnLayAnh.setFont(new Font("Arial", Font.BOLD, 13));
		 
		 btnLayAnh.addActionListener(this);
	}
	
	public void xoaTrang() {
		txtMaSach.setText("");
		txtTenSach.setText("");
		txtSoLuong.setText("");
		txtNhaXuatBan.setText("");
		txtGiaBan.setText("");
		txtViTri.setText("");
		txtTheLoai.setText("");
		txtSoTrang.setText("");
		lblAnh.setIcon(null);
		txtTacGia.setText("");
	}
	
	public void huy() {
		btnThem.setEnabled(true);
		btnXoa.setEnabled(true);
		btnXoaTrang.setEnabled(true);
		btnSua.setEnabled(true);
		btnHuy.setEnabled(false);
		btnThem.setText("Thêm");
		btnSua.setText("Sửa");
		btnXoa.setText("Xóa");
		xoaTrang();
	}
	
	
	public void layDuLeu() {
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		for (Sach sp : sachDao.getAllSach()) {
//			ImageIcon imageIcon;
//			if(sp.getHinhAnh() != null) {
//				imageIcon = new ImageIcon(sp.getHinhAnh());
//			    Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//			    imageIcon = new ImageIcon(scaledImage);
//			}
//			else {
//				imageIcon = null;
//			}
			Object[] rowData = {sp.getMaSach(),sp.getTenSach(),sp.getTheLoai() , sp.getNhaXuatBan() , df.format(sp.getGiaBan()) , sp.getViTriKeSach()  , sp.getSoLuong() , sp.getSoTrang() , sp.getTacGia()};
			model.addRow(rowData);

		}
	}
	
	public boolean kiemTra() {
		String maSach = txtMaSach.getText();
		String tenSach = txtTenSach.getText();
		String theLoai = txtTheLoai.getText();
		String nhaSanXuat = txtNhaXuatBan.getText();
		String donGia = txtGiaBan.getText();
		String viTri = txtViTri.getText();
		String soLuong = txtSoLuong.getText();
		String soTrang = txtSoTrang.getText();
		String tacGia = txtTacGia.getText();
		if(maSach.equalsIgnoreCase("") || tenSach.equalsIgnoreCase("") || theLoai.equalsIgnoreCase("") || nhaSanXuat.equalsIgnoreCase("") || donGia.equalsIgnoreCase("") || viTri.equalsIgnoreCase("") || soLuong.equalsIgnoreCase("") || soTrang.equalsIgnoreCase("") || tacGia.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin sách");
			return false;
		}
		else {
				if(donGia.length()>0) {
					try {
						Double donGiaDouble = Double.parseDouble(donGia);
						if(donGiaDouble < 0){
							JOptionPane.showMessageDialog(this, "Giá bán là số nguyên dương");
							return false;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, "Giá bán phải là kí tự số");
						return false;
					}
				}
				if(soLuong.length()>0) {
					try {
						int soLuongInt = Integer.parseInt(soLuong);
						if(soLuongInt < 0){
							JOptionPane.showMessageDialog(this, "Số lượng là số nguyên dương");
							return false;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, "Số lượng phải là kí tự số");
						return false;
					}
				}
			   if(soTrang.length()>0) {
					try {
						int soTrangInt = Integer.parseInt(soTrang);
						if(soTrangInt < 0){
							JOptionPane.showMessageDialog(this, "Số trang là số nguyên dương");
							return false;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, "Số trang phải là kí tự số");
						return false;
					}
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
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		if(o.equals(btnThem)) {
			if(btnThem.getText().equalsIgnoreCase("Thêm")) {
				btnXoa.setEnabled(false);
				btnXoaTrang.setEnabled(false);
				btnSua.setEnabled(false);
				btnHuy.setEnabled(true);
				btnThem.setText("Lưu");
				xoaTrang();
				try {
					String sql = "IF ((SELECT MAX(CAST(RIGHT(MaSanPham, LEN(MaSanPham) - 2) AS int)) + 1 FROM SanPham) < 10) "
							+ "SELECT CONCAT('SP0', MAX(CAST(RIGHT(MaSanPham, LEN(MaSanPham) - 2) AS int)) + 1) FROM SanPham "
							+ "ELSE SELECT CONCAT('SP', MAX(CAST(RIGHT(MaSanPham, LEN(MaSanPham) - 2) AS int)) + 1) FROM SanPham ";
					java.sql.Statement statement =  con.createStatement();
					ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
					while(rs.next()) {
						txtMaSach.setText(rs.getString(1));
					}
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
			else {
				if(kiemTra()) {
					Sach sach = new Sach(txtMaSach.getText(), txtTenSach.getText(), txtTheLoai.getText(), txtNhaXuatBan.getText(), Double.parseDouble(txtGiaBan.getText()), txtViTri.getText(),  Integer.parseInt(txtSoLuong.getText()), selectedImage, Integer.parseInt(txtSoTrang.getText()),txtTacGia.getText());
					sachDao.themSanPham(sach);
//					ImageIcon imageIcon;
//					if(sach.getHinhAnh() != null) {
//						imageIcon = new ImageIcon(sach.getHinhAnh());
//					    Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//					    imageIcon = new ImageIcon(scaledImage);
//					}
//					else {
//						imageIcon = null;
//					}
					Object[] rowData = {sach.getMaSach(),sach.getTenSach(),sach.getTheLoai() , sach.getNhaXuatBan() , df.format(sach.getGiaBan()) , sach.getViTriKeSach()  , sach.getSoLuong() , sach.getSoTrang() , sach.getTacGia()};
					model.addRow(rowData);
					xoaTrang();
					huy();
					JOptionPane.showMessageDialog(this, "Thêm Thành công");
				}
			}				
		}
		if(o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Chọn sách để sửa");
			} else {
				if(btnSua.getText().equalsIgnoreCase("Sửa")) {
					btnXoa.setEnabled(false);
					btnXoaTrang.setEnabled(false);
					btnThem.setEnabled(false);
					btnHuy.setEnabled(true);
					btnSua.setText("Lưu");
				} else {
					if(kiemTra()) {
						String tenSach = txtTenSach.getText();
						String theLoai = txtTheLoai.getText();
						String nhaXuatBan = txtNhaXuatBan.getText();
						Double donGia = Double.parseDouble(txtGiaBan.getText());
						String viTri = txtViTri.getText();
						int soLuong = Integer.parseInt(txtSoLuong.getText());
						int soTrang = Integer.parseInt(txtSoTrang.getText());
						String tacGia = txtTacGia.getText();
						Sach sach = new Sach(txtMaSach.getText(), txtTenSach.getText(), txtTheLoai.getText(), txtNhaXuatBan.getText(), Double.parseDouble(txtGiaBan.getText()), txtViTri.getText(),  Integer.parseInt(txtSoLuong.getText()), selectedImage, Integer.parseInt(txtSoTrang.getText()),txtTacGia.getText());
						sachDao.suaSanPham(sach);
						ImageIcon imageIcon;
						if(sach.getHinhAnh() != null) {
							imageIcon = new ImageIcon(sach.getHinhAnh());
						    Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
						    imageIcon = new ImageIcon(scaledImage);
						}
						else {
							imageIcon = null;
						}
						model.setValueAt(tenSach, row, 1);
						model.setValueAt(theLoai, row, 2);
						model.setValueAt(nhaXuatBan, row, 3);
						model.setValueAt(df.format(donGia), row, 4);
						model.setValueAt(viTri, row, 5);
						model.setValueAt(soLuong, row, 6);
//						model.setValueAt(imageIcon, row, 7);
						model.setValueAt(soTrang, row, 7);
						model.setValueAt(tacGia, row, 8);
						JOptionPane.showMessageDialog(this, "Sửa thành công");
						huy();
					}
				}
			}
		}
		if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Chọn sách để xóa");
			} else {
				if(btnSua.getText().equalsIgnoreCase("Xóa")) {
					btnSua.setEnabled(false);
					btnXoaTrang.setEnabled(false);
					btnThem.setEnabled(false);
					btnHuy.setEnabled(true);
					btnXoa.setText("Lưu");
				} else {
					if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
						String ma = model.getValueAt(row, 0).toString();
						sachDao.xoaSanPham(ma);
						model.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}		
				}
			}
		}

		if(o.equals(btnLayAnh)) {
			JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
            	File file = fileChooser.getSelectedFile();
                File selectedFile = fileChooser.getSelectedFile();
                
		        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());	        
		        Image image = imageIcon.getImage();
		        Image scaledImage = image.getScaledInstance(233, 253, Image.SCALE_SMOOTH);
		        imageIcon = new ImageIcon(scaledImage);
		        
		        lblAnh.setIcon(imageIcon);
		        
		        String anh = file.getAbsolutePath().replace("/", "//");
                try (FileInputStream fis = new FileInputStream(selectedFile)) {
                    selectedImage = new byte[(int) selectedFile.length()];
                    fis.read(selectedImage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
		}
		
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		if(o.equals(btnHuy)) {
			huy();
		}
	}
}
