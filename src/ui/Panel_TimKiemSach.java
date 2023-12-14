package ui;

import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Sach_Dao;
import entity.Sach;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class Panel_TimKiemSach extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox comboBox_TheLoai;
	private JComboBox comboBox_TacGia;
	private JLabel lblAnh;
	Sach_Dao sachDao = new Sach_Dao();
	/**
	 * Create the panel.
	 */
	public Panel_TimKiemSach() {
		setBackground(new Color(240, 240, 240));
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 372, 1430, 365);
		add(scrollPane);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		table = new JTable();
		table.setFont(new Font("Arial", Font.BOLD, 15));
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
		
		JButton btnTimKiem = new JButton("TÌM KIẾM ");
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setIcon(new ImageIcon(Panel_TimKiemSach.class.getResource("/img/ui/search25.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//					if(txtMaSach.getText().equals("") && txtTenSach.getText().equals("")) {
//						JOptionPane.showMessageDialog(null,"Không được bỏ trống");
//					}
//					else {
//						if(!txtMaSach.getText().equals("") && txtTenSach.getText().equals("")) {
//							String  ma = txtMaSach.getText();
//							ArrayList<Sach> dsSach; 
//							dsSach = sachDao.timKiemTheoMa(ma);
//							if(dsSach == null) {
//								JOptionPane.showMessageDialog(null, "Không Tìm Thấy !");
//							}
//							model.setRowCount(0);
//								 for(Sach sach : dsSach) {
//									 Object[] rowData = {sach.getMaSach(),sach.getTenSach(),sach.getTheLoai() , sach.getSoTrang() , sach.getTacGia() , sach.getTheLoai()  , sach.getViTriKeSach(), sach.getNhaXuatBan() ,sach.getGiaBan() + " vnd", sach.getSoLuong()};
//										model.addRow(rowData);
//								    }
//						}
//						else {
//							String  ten = txtTenSach.getText();
//							model.setRowCount(0);
//							ArrayList<Sach> dsSach; 
//							dsSach = sachDao.timKiemTheoTen(ten) ;
//								for(Sach sach : dsSach) {
//									 Object[] rowData = {sach.getMaSach(),sach.getTenSach(),sach.getTheLoai() , sach.getSoTrang() , sach.getTacGia() , sach.getTheLoai()  , sach.getViTriKeSach(), sach.getNhaXuatBan() ,sach.getGiaBan() + " vnd", sach.getSoLuong()};
//										model.addRow(rowData);
//								    }	
//						}
//					
//					}
//					
				String maSach;
				String tenSach ;
				String theLoaiSach = "";
				String tacGia = "" ;
				
				if(txtMaSach.getText().equalsIgnoreCase("")) {
					maSach = "";
				}
				else {
					maSach = txtMaSach.getText();
				}
				if(txtTenSach.getText().equalsIgnoreCase("")) {
					tenSach = "";
				}
				else {
					tenSach = txtTenSach.getText();
				}
				
				if(comboBox_TacGia.getSelectedItem().toString().equalsIgnoreCase("None")) {
					 tacGia = "" ;
				 }
				 else {
					 tacGia = comboBox_TacGia.getSelectedItem().toString();
				 }
				 if(comboBox_TheLoai.getSelectedItem().toString().equalsIgnoreCase("None")) {
					 theLoaiSach = "";
				 }
				 else {
					 theLoaiSach = comboBox_TheLoai.getSelectedItem().toString();
				 }
				
				model.setRowCount(0);
				DecimalFormat df = new DecimalFormat("#,### VNĐ");
//				System.out.println(sachDao.timKiemSach(maSach, tenSach, theLoaiSach, tacGia));
				 for(Sach sp : sachDao.timKiemSach(maSach, tenSach, theLoaiSach, tacGia)) {			 
						Object[] rowData = {sp.getMaSach(),sp.getTenSach(),sp.getTheLoai() , sp.getNhaXuatBan() , df.format(sp.getGiaBan()) , sp.getViTriKeSach()  , sp.getSoLuong() , sp.getSoTrang() , sp.getTacGia()};
						model.addRow(rowData);
				    }
				
					
			}
			
		});
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setBounds(178, 94, 163, 57);
		add(btnTimKiem);
		JButton btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_TimKiemSach.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoi();
				model.setRowCount(0);
				layDuLeu();
			}
		});
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(178, 215, 163, 57);
		add(btnLamMoi);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(409, 67, 805, 280);
		add(panel);
		panel.setLayout(null);
		
		txtMaSach = new JTextField();
		txtMaSach.setFont(new Font("Arial", Font.BOLD, 15));
		txtMaSach.setBounds(341, 10, 278, 39);
		panel.add(txtMaSach);
		txtMaSach.setColumns(10);
		
		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Arial", Font.BOLD, 15));
		txtTenSach.setBounds(341, 74, 278, 39);
		panel.add(txtTenSach);
		txtTenSach.setColumns(10);
		
		comboBox_TheLoai = new JComboBox();
		comboBox_TheLoai.setFont(new Font("Arial", Font.BOLD, 15));
		comboBox_TheLoai.setBounds(341, 145, 278, 39);
		panel.add(comboBox_TheLoai);
		
		comboBox_TacGia = new JComboBox();
		comboBox_TacGia.setFont(new Font("Arial", Font.BOLD, 15));
		comboBox_TacGia.setBounds(341, 214, 278, 39);
		panel.add(comboBox_TacGia);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Sách ");
		lblNewLabel_1.setBounds(146, 10, 83, 39);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên Sách ");
		lblNewLabel_1_1.setBounds(146, 80, 93, 33);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Thể Loại ");
		lblNewLabel_1_1_1.setBounds(146, 144, 93, 39);
		panel.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tác Giả ");
		lblNewLabel_1_1_1_1.setBounds(146, 214, 93, 39);
		panel.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		
		 lblAnh = new JLabel("");
		lblAnh.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAnh.setBounds(1240, 67, 218, 280);
		add(lblAnh);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM SÁCH");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 0, 1520, 51);
		add(lblNewLabel);
		layDuLeu();
		dataComboBoxTheLoaiSach();
		dataComboBoxTacGia();
		
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
//				lblAnh.setIcon((Icon) model.getValueAt(row, 7));
				comboBox_TacGia.setSelectedItem(model.getValueAt(row, 4).toString());
				comboBox_TheLoai.setSelectedItem(model.getValueAt(row, 2).toString());

				
				ConnectDB.getInstance();
				Connection con = ConnectDB.getConnection();
				try {
					String sql = "select HinhAnh from SanPham where MaSanPham = '"+ txtMaSach.getText() +"'";
					java.sql.Statement statement =  con.createStatement();
					ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
					while(rs.next()) {
						byte[] imageData = rs.getBytes("HinhAnh");
						if (imageData != null) {
			                ImageIcon imageIcon = new ImageIcon(imageData);
			                Image scaledImage = imageIcon.getImage().getScaledInstance(189, 196, Image.SCALE_SMOOTH);
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
	
	
	//LoadData comboboxLoaiSach 
	public void dataComboBoxTheLoaiSach() {
		
		ArrayList<String> dataComboBox = new ArrayList<String>();
		dataComboBox = sachDao.getDataComboBox();
		comboBox_TheLoai.addItem("None");
		for (String string : dataComboBox) {
			comboBox_TheLoai.addItem(string);
		}
		
		
	}
	
	// LoadData comboxTacGia 
	public void dataComboBoxTacGia() {
		
		ArrayList<String> dataComboBox = new ArrayList<String>();
		dataComboBox = sachDao.getDataComboBoxTacGia();
		comboBox_TacGia.addItem("None");
		for (String string : dataComboBox) {
			comboBox_TacGia.addItem(string);
		}
		
	}
	// Hàm Làm Mới dữ liệu
	public void lamMoi() {
		txtMaSach.setText("");
		txtTenSach.setText("");
		lblAnh.setIcon(null);
		comboBox_TacGia.setSelectedIndex(0);
		comboBox_TheLoai.setSelectedIndex(0);
		
	}
}
