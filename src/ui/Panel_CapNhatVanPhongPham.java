package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.VanPhongPham_Dao;
import entity.VanPhongPham;
import javax.swing.border.MatteBorder;

public class Panel_CapNhatVanPhongPham extends JPanel implements ActionListener{
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JTextField txtNhaSanXuat;
	private JTextField txtGiaBan;
	private JTextField txtViTri;
	private JTextField txtSoLuong;
	private JTextField txtLoaiVPP;
	private JTable table_VanPhongPham;
	private byte[] selectedImage;
	private DefaultTableModel model;
	private JButton btnThem, btnXoa, btnSua, btnXoaTrang, btnLayAnh, btnHuy;
	private JLabel lblAnh;
	
	VanPhongPham_Dao vppDao = new VanPhongPham_Dao();

	/**
	 * Create the panel.
	 */
	public Panel_CapNhatVanPhongPham() {
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JPanel panel_ThongTinsp = new JPanel();
		panel_ThongTinsp.setLayout(null);
		panel_ThongTinsp.setBorder(new LineBorder(new Color(0, 128, 64)));
		panel_ThongTinsp.setBounds(211, 115, 1078, 326);
		add(panel_ThongTinsp);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Sản Phẩm");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 17, 150, 27);
		panel_ThongTinsp.add(lblNewLabel_1);
		
		txtMaSanPham = new JTextField();
		txtMaSanPham.setFont(new Font("Arial", Font.BOLD, 15));
		txtMaSanPham.setColumns(10);
		txtMaSanPham.setBounds(194, 10, 326, 45);
		panel_ThongTinsp.add(txtMaSanPham);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên Sản Phẩm");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 100, 150, 27);
		panel_ThongTinsp.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nhà sản xuất");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(10, 185, 128, 27);
		panel_ThongTinsp.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Giá Bán");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(10, 267, 103, 27);
		panel_ThongTinsp.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Vị trí");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_4.setBounds(568, 17, 128, 27);
		panel_ThongTinsp.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_6 = new JLabel("Loại VPP ");
		lblNewLabel_1_6.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_6.setBounds(568, 185, 117, 27);
		panel_ThongTinsp.add(lblNewLabel_1_6);
		
		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenSanPham.setColumns(10);
		txtTenSanPham.setBounds(195, 93, 325, 45);
		panel_ThongTinsp.add(txtTenSanPham);
		
		txtNhaSanXuat = new JTextField();
		txtNhaSanXuat.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNhaSanXuat.setColumns(10);
		txtNhaSanXuat.setBounds(195, 178, 325, 45);
		panel_ThongTinsp.add(txtNhaSanXuat);
		
		txtGiaBan = new JTextField();
		txtGiaBan.setFont(new Font("Arial", Font.PLAIN, 15));
		txtGiaBan.setColumns(10);
		txtGiaBan.setBounds(194, 260, 326, 45);
		panel_ThongTinsp.add(txtGiaBan);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Số lượng");
		lblNewLabel_1_6_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_6_1.setBounds(568, 100, 128, 27);
		panel_ThongTinsp.add(lblNewLabel_1_6_1);
		
		txtViTri = new JTextField();
		txtViTri.setFont(new Font("Arial", Font.PLAIN, 15));
		txtViTri.setColumns(10);
		txtViTri.setBounds(705, 10, 326, 45);
		panel_ThongTinsp.add(txtViTri);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(705, 93, 326, 45);
		panel_ThongTinsp.add(txtSoLuong);
		
		txtLoaiVPP = new JTextField();
		txtLoaiVPP.setFont(new Font("Arial", Font.PLAIN, 15));
		txtLoaiVPP.setColumns(10);
		txtLoaiVPP.setBounds(705, 178, 326, 45);
		panel_ThongTinsp.add(txtLoaiVPP);
		
		btnLayAnh = new JButton("LẤY ẢNH");
		btnLayAnh.setBackground(new Color(164, 159, 106));
		btnLayAnh.setBounds(1339, 391, 133, 50);
		add(btnLayAnh);
		btnLayAnh.setFont(new Font("Arial", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 451, 1486, 276);
		add(scrollPane);
		
		table_VanPhongPham = new JTable();
		table_VanPhongPham.setFont(new Font("Arial", Font.PLAIN, 15));
		table_VanPhongPham.setModel(model = new DefaultTableModel(
			 	new Object[][] {
			 	},
			 	new String[] {
			 			"Mã sản phẩm","Tên sản phẩm","Nhà sản xuất","Giá bán","Vị trí kệ","Số lượng còn","Loại văn phòng phẩm"
			 	}
			 ) {
			 });
		table_VanPhongPham.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_VanPhongPham.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_VanPhongPham.setRowHeight(35);
		scrollPane.setViewportView(table_VanPhongPham);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 1487, 50);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CẬP NHẬT VĂN PHÒNG PHẨM");
		lblNewLabel.setBounds(0, 5, 1501, 45);
		lblNewLabel.setForeground(new Color(128, 128, 0));
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		
		lblAnh = new JLabel("");
		lblAnh.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAnh.setBounds(1299, 115, 211, 267);
		add(lblAnh);
		
		layDuLeu();
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
				txtMaSanPham.setText(model.getValueAt(row, 0).toString());
				txtTenSanPham.setText(model.getValueAt(row, 1).toString());
				txtNhaSanXuat.setText(model.getValueAt(row, 2).toString());
				DecimalFormat df = new DecimalFormat("#,### VNĐ");
		        Number parsedNumber;
				try {
					parsedNumber = df.parse(model.getValueAt(row, 3).toString());
					double giaBan = parsedNumber.doubleValue();
					txtGiaBan.setText(String.valueOf(giaBan));
				} catch (java.text.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtViTri.setText(model.getValueAt(row, 4).toString());
				txtSoLuong.setText(model.getValueAt(row, 5).toString());
				txtLoaiVPP.setText(model.getValueAt(row, 6).toString());
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
			                Image scaledImage = imageIcon.getImage().getScaledInstance(221, 267, Image.SCALE_SMOOTH);
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
		btnLayAnh.addActionListener(this);
		
		btnThem = new JButton("THÊM");
		btnThem.setBackground(new Color(128, 255, 0));
		btnThem.setIcon(new ImageIcon(Panel_CapNhatVanPhongPham.class.getResource("/img/ui/add25.png")));
		btnThem.setBounds(37, 105, 140, 51);
		add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		
		btnThem.addActionListener(this);
		
		btnSua = new JButton("SỬA");
		btnSua.setBackground(new Color(255, 128, 64));
		btnSua.setIcon(new ImageIcon(Panel_CapNhatVanPhongPham.class.getResource("/img/ui/fix25.png")));
		btnSua.setBounds(37, 175, 140, 51);
		add(btnSua);
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));
		btnSua.addActionListener(this);
		
		btnXoa = new JButton("XÓA");
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setIcon(new ImageIcon(Panel_CapNhatVanPhongPham.class.getResource("/img/ui/icons8-delete-30.png")));
		btnXoa.setBounds(37, 247, 140, 51);
		add(btnXoa);
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.addActionListener(this);
		
		btnXoaTrang = new JButton("LÀM MỚI");
		btnXoaTrang.setBackground(new Color(255, 255, 128));
		btnXoaTrang.setIcon(new ImageIcon(Panel_CapNhatVanPhongPham.class.getResource("/img/ui/refresh25.png")));
		btnXoaTrang.setBounds(37, 313, 140, 51);
		add(btnXoaTrang);
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoaTrang.addActionListener(this);
		
		btnHuy = new JButton("HỦY");
		btnHuy.setBackground(new Color(141, 114, 131));
		btnHuy.setIcon(new ImageIcon(Panel_CapNhatVanPhongPham.class.getResource("/img/ui/cancel25.png")));
		btnHuy.setBounds(37, 390, 140, 51);
		add(btnHuy);
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.addActionListener(this);


	}
	
	public void layDuLeu() {
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		for (VanPhongPham sp : vppDao.getAllVanPhongPham()) {
//			ImageIcon imageIcon;
//			if(sp.getHinhAnh() != null) {
//				imageIcon = new ImageIcon(sp.getHinhAnh());
//			    Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//			    imageIcon = new ImageIcon(scaledImage);
//			}
//			else {
//				imageIcon = null;
//			}
			Object[] rowData = {sp.getMaSanPham(),sp.getTenSanPham(),sp.getNhaSanXuat() , df.format(sp.getGiaBan()) , sp.getViTri()  , sp.getSoLuong() , sp.getLoaiVanPhongPham()};
			model.addRow(rowData);
		}
	}
	
	public void xoaTrang() {
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		txtSoLuong.setText("");
		txtNhaSanXuat.setText("");
		txtGiaBan.setText("");
		txtViTri.setText("");
		txtLoaiVPP.setText("");
		lblAnh.setIcon(null);
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
	
	public boolean kiemTra() {
		String maSach = txtMaSanPham.getText();
		String tenSach = txtTenSanPham.getText();
		String nhaSanXuat = txtNhaSanXuat.getText();
		String donGia = txtGiaBan.getText();
		String viTri = txtViTri.getText();
		String soLuong = txtSoLuong.getText();
		String loaiVPP = txtLoaiVPP.getText();
		if(maSach.equalsIgnoreCase("") || tenSach.equalsIgnoreCase("") ||  nhaSanXuat.equalsIgnoreCase("") || donGia.equalsIgnoreCase("") || viTri.equalsIgnoreCase("") || soLuong.equalsIgnoreCase("") || loaiVPP.equalsIgnoreCase("")) {
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
				try {
					String sql = "IF ((SELECT MAX(CAST(RIGHT(MaSanPham, LEN(MaSanPham) - 2) AS int)) + 1 FROM SanPham) < 10) "
							+ "SELECT CONCAT('SP0', MAX(CAST(RIGHT(MaSanPham, LEN(MaSanPham) - 2) AS int)) + 1) FROM SanPham "
							+ "ELSE SELECT CONCAT('SP', MAX(CAST(RIGHT(MaSanPham, LEN(MaSanPham) - 2) AS int)) + 1) FROM SanPham ";
					java.sql.Statement statement =  con.createStatement();
					ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
					while(rs.next()) {
						txtMaSanPham.setText(rs.getString(1));
					}
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			} else {
				if(kiemTra()) {
					VanPhongPham vpp = new VanPhongPham(txtMaSanPham.getText(), txtTenSanPham.getText(), txtNhaSanXuat.getText(), Double.parseDouble(txtGiaBan.getText()), txtViTri.getText(),  Integer.parseInt(txtSoLuong.getText()), selectedImage, txtLoaiVPP.getText());
					vppDao.themVanPhongPham(vpp);
//					ImageIcon imageIcon;
//					if(vpp.getHinhAnh() != null) {
//						imageIcon = new ImageIcon(vpp.getHinhAnh());
//					    Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//					    imageIcon = new ImageIcon(scaledImage);
//					}
//					else {
//						imageIcon = null;
//					}
					Object[] rowData = {vpp.getMaSanPham(),vpp.getTenSanPham() , vpp.getNhaSanXuat() , df.format(vpp.getGiaBan()) , vpp.getViTri()  , vpp.getSoLuong() , vpp.getLoaiVanPhongPham()};
					model.addRow(rowData);
					xoaTrang();
					JOptionPane.showMessageDialog(this, "Thêm Thành công");
					huy();
				}
			}			
		}
		if(o.equals(btnSua)) {
			int row = table_VanPhongPham.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm để sửa");
			} else {
				if(btnSua.getText().equalsIgnoreCase("Sửa")) {
					btnXoa.setEnabled(false);
					btnXoaTrang.setEnabled(false);
					btnThem.setEnabled(false);
					btnHuy.setEnabled(true);
					btnSua.setText("Lưu");
				} else {
					if(kiemTra()) {
						String tenSanPham = txtTenSanPham.getText();
						String nhaSanXuat = txtNhaSanXuat.getText();
						Double donGia = Double.parseDouble(txtGiaBan.getText());
						String viTri = txtViTri.getText();
						int soLuong = Integer.parseInt(txtSoLuong.getText());
						String loaiVPP = txtLoaiVPP.getText();
						VanPhongPham vpp = new VanPhongPham(txtMaSanPham.getText(), tenSanPham, nhaSanXuat, donGia, viTri,  soLuong, selectedImage, loaiVPP);
						vppDao.suaVanPhongPham(vpp);
//						ImageIcon imageIcon;
//						if(vpp.getHinhAnh() != null) {
//							imageIcon = new ImageIcon(vpp.getHinhAnh());
//						    Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//						    imageIcon = new ImageIcon(scaledImage);
//						}
//						else {
//							imageIcon = null;
//						}
						model.setValueAt(tenSanPham, row, 1);
						model.setValueAt(nhaSanXuat, row, 2);
						model.setValueAt(df.format(donGia), row, 3);
						model.setValueAt(viTri, row, 4);
						model.setValueAt(soLuong, row, 5);
//						model.setValueAt(imageIcon, row, 6);
						model.setValueAt(loaiVPP, row, 6);
						JOptionPane.showMessageDialog(this, "Sửa thành công");
					}
				}
			}
		}
		if(o.equals(btnXoa)) {
			int row = table_VanPhongPham.getSelectedRow();
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
						vppDao.xoaSanPham(ma);
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
		if(o.equals(btnHuy)) {
			huy();
		}
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		
	}
}
