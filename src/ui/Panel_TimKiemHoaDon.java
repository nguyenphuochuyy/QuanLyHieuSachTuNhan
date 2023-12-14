package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_Dao;
import dao.NhanVien_Dao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Panel_TimKiemHoaDon extends JPanel {
	private JTextField txtMaHD;
	private JTextField txtMaNhanVien;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTable table_HoaDon;
	private JTable table_CTHoaDon;
	private JDateChooser dcNgayLap;
	private JButton btnTimKiem;
	private DefaultTableModel model_HoaDon, model_CTHoaDon;
	public static Connection con = null;
	
	
	HoaDon_Dao hoaDonDao = new HoaDon_Dao();
	ChiTietHoaDon_Dao chiTietHoaDonDao = new ChiTietHoaDon_Dao();
	NhanVien_Dao nhanVienDao = new NhanVien_Dao();
	/**
	 * Create the panel.
	 */
	public Panel_TimKiemHoaDon() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM HÓA ĐƠN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setBounds(0, 0, 1492, 52);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(20, 96, 980, 289);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 979, 289);
		panel.add(scrollPane);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		table_HoaDon = new JTable();
		table_HoaDon.setFont(new Font("Arial", Font.PLAIN, 15));
		table_HoaDon.setModel(model_HoaDon = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã hóa đơn" , "Mã nhân viên", "Tên nhân viên" ,"Mã khách hàng" , "Tên khách hàng" , "Số điện thoại" , "Địa chỉ" , "Ngày lập"
			}
		));
		table_HoaDon.getColumnModel().getColumn(0).setPreferredWidth(70);
		table_HoaDon.getColumnModel().getColumn(1).setPreferredWidth(70);
		table_HoaDon.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_HoaDon.getColumnModel().getColumn(3).setPreferredWidth(70);
		table_HoaDon.getColumnModel().getColumn(4).setPreferredWidth(150);
		table_HoaDon.setRowHeight(35);
		scrollPane.setViewportView(table_HoaDon);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(20, 409, 975, 298);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 0, 975, 298);
		panel_1.add(scrollPane_1);
		
		table_CTHoaDon = new JTable();
		table_CTHoaDon.setFont(new Font("Arial", Font.PLAIN, 15));
		table_CTHoaDon.setModel(model_CTHoaDon = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã hóa đơn" , "Mã sản phẩm" , "Tên sản phẩm" , "Giá bán" , "Số lượng" , "Thành tiền"
			}
		));
        table_CTHoaDon.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table_CTHoaDon.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table_CTHoaDon.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_CTHoaDon.getColumnModel().getColumn(1).setPreferredWidth(10);
		table_CTHoaDon.getColumnModel().getColumn(2).setPreferredWidth(250);
		table_CTHoaDon.setRowHeight(35);
		scrollPane_1.setViewportView(table_CTHoaDon);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(1031, 129, 461, 562);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Mã hóa đơn");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setBounds(37, 26, 122, 34);
		panel_2.add(lblNewLabel_2);
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Arial", Font.BOLD, 20));
		txtMaHD.setBounds(182, 22, 248, 39);
		panel_2.add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mã nhân viên");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(37, 119, 122, 34);
		panel_2.add(lblNewLabel_2_1);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(182, 117, 248, 39);
		panel_2.add(txtMaNhanVien);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Mã khách hàng");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1.setBounds(37, 215, 122, 34);
		panel_2.add(lblNewLabel_2_1_1);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(182, 213, 248, 39);
		panel_2.add(txtMaKhachHang);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Tên khách hàng");
		lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1_1.setBounds(37, 307, 122, 39);
		panel_2.add(lblNewLabel_2_1_1_1);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(182, 307, 248, 39);
		panel_2.add(txtTenKhachHang);
		
		btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setIcon(new ImageIcon(Panel_TimKiemHoaDon.class.getResource("/img/ui/search30.png")));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 12));
		btnTimKiem.setBounds(10, 470, 132, 56);
		panel_2.add(btnTimKiem);
		
		JLabel lblNewLabel_2_1_1_1_1_2 = new JLabel("Ngày lập");
		lblNewLabel_2_1_1_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1_1_1_2.setBounds(37, 385, 122, 39);
		panel_2.add(lblNewLabel_2_1_1_1_1_2);
		
		JDateChooser dcNgayLap = new JDateChooser();
		dcNgayLap.setForeground(new Color(64, 128, 128));
		dcNgayLap.setBounds(182, 385, 248, 39);
		panel_2.add(dcNgayLap);
		
		JButton btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_TimKiemHoaDon.class.getResource("/img/ui/refresh.png")));
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 12));
		btnLamMoi.setBounds(152, 470, 122, 56);
		panel_2.add(btnLamMoi);
		
		JButton btnInHoaDon = new JButton("IN HÓA ĐƠN");
		btnInHoaDon.setBackground(new Color(0, 128, 0));
		btnInHoaDon.setIcon(new ImageIcon(Panel_TimKiemHoaDon.class.getResource("/img/ui/print.png")));
		btnInHoaDon.setFont(new Font("Arial", Font.BOLD, 12));
		btnInHoaDon.setBounds(284, 470, 146, 56);
		panel_2.add(btnInHoaDon);
		
		JLabel lblNewLabel_1 = new JLabel("THÔNG TIN ");
		lblNewLabel_1.setForeground(new Color(128, 128, 0));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(1218, 81, 255, 38);
		add(lblNewLabel_1);
		
		layDanhSachHoaDon();
		
		table_HoaDon.addMouseListener(new MouseListener() {
			
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
				int row = table_HoaDon.getSelectedRow();
				String maHoaDon = model_HoaDon.getValueAt(row, 0).toString();
				layChiTietHoaDon(maHoaDon);
				txtMaHD.setText(maHoaDon);
				txtMaNhanVien.setText(model_HoaDon.getValueAt(row, 1).toString());
				txtMaKhachHang.setText(model_HoaDon.getValueAt(row,3).toString());
				txtTenKhachHang.setText(model_HoaDon.getValueAt(row, 4).toString());
				String ngayLapHd = (String) model_HoaDon.getValueAt(row, 7);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-M-y");
				LocalDate lcd = LocalDate.parse(ngayLapHd, dtf);
				dcNgayLap.setDate(java.sql.Date.valueOf(lcd));
			}
		});
		
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String maHD;
				if(txtMaHD.getText().contentEquals("")) {
					maHD = "";
				} else {
					maHD = txtMaHD.getText();
				}
				String maNhanVien;
				if(txtMaNhanVien.getText().equalsIgnoreCase(""))
					maNhanVien = "";
				else
					maNhanVien = txtMaNhanVien.getText();
				String maKhachHang;
				if(txtMaKhachHang.getText().equalsIgnoreCase(""))
					maKhachHang = "";
				else
					maKhachHang = txtMaKhachHang.getText();
				String tenKhachHang ;
				if(txtTenKhachHang.getText().equalsIgnoreCase(""))
					tenKhachHang = "";
				else
					tenKhachHang =  txtTenKhachHang.getText();
				Date selectedDate = dcNgayLap.getDate();

				String ngayLapString;
				if(selectedDate == null) 
					ngayLapString = "is not null";
				else {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					ngayLapString = " = '" +dateFormat.format(selectedDate) + "'";
					
				}
		
				
	
				
			
			
					model_HoaDon.setRowCount(0);
					for (HoaDon lh : hoaDonDao.timKiemHoaDon(maHD, maNhanVien, maKhachHang, tenKhachHang, ngayLapString)) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						Object[] rowData = {lh.getMaHoaDon(),lh.getMaNhanVien(),nhanVienDao.layTenTheoMa(lh.getMaNhanVien()) ,lh.getMaKhachHang() ,lh.getTenKhachHang(), lh.getSdt() , lh.getDiaChi() , lh.getNgayLapHoaDon().format(formatter)};
						model_HoaDon.addRow(rowData);
					}
					model_CTHoaDon.setRowCount(0);
				
				
				
			}
		});
		
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model_HoaDon.setRowCount(0);
				layDanhSachHoaDon();
				model_CTHoaDon.setRowCount(0);
				txtMaHD.setText("");
				txtMaKhachHang.setText("");
				txtMaNhanVien.setText("");
				dcNgayLap.setDate(null);
				txtTenKhachHang.setText("");
			}
		});
		
		btnInHoaDon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table_HoaDon.getSelectedRow();
				if(row == -1)
					JOptionPane.showMessageDialog(null, "Chọn hóa đơn để in");
				else {
					hoaDonDao.xuatHoaDon(model_HoaDon.getValueAt(row, 0).toString());
				}	
			}
		});
	}
	
	


	
	
	public void layDanhSachHoaDon() {
		model_HoaDon.setRowCount(0);
		for (HoaDon lh : hoaDonDao.getAllHoaDon()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			Object[] rowData = {lh.getMaHoaDon(),lh.getMaNhanVien(),nhanVienDao.layTenTheoMa(lh.getMaNhanVien()),lh.getMaKhachHang() ,lh.getTenKhachHang(), lh.getSdt() , lh.getDiaChi() , lh.getNgayLapHoaDon().format(formatter)};
			model_HoaDon.addRow(rowData);
		}
	}
	
	public void layChiTietHoaDon(String maHoaDon) {
		model_CTHoaDon.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#,### VNĐ");
		for (ChiTietHoaDon lh : chiTietHoaDonDao.getAllChiTietHoaDon(maHoaDon)) {			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			double thanhTien = lh.getSoLuong() * lh.getGiaBan();
			Object[] rowData = {lh.getMaHoaDon(),lh.getMaSanPham(),lh.getTenSanPham(),df.format(lh.getGiaBan()),lh.getSoLuong(),df.format(thanhTien)};
			model_CTHoaDon.addRow(rowData);
		}
	}
}
