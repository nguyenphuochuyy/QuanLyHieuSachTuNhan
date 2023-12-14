package thongKe;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HoaDon_Dao;
import dao.NhanVien_Dao;
import dao.ThongKe_Dao;
import entity.HoaDon;
import entity.NhanVien;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class Panel_ThongKeDoanhThu extends JPanel {
	private JTextField txtTongSoHoaDon;
	private JTextField txtTongDoanhThu;
	private JTable table;
	private JComboBox cbMaNhanVien;
	ThongKe_Dao daoDoanhThu = new ThongKe_Dao();
	DefaultTableModel model  = new DefaultTableModel();
	HoaDon_Dao daoHoaDon = new HoaDon_Dao();
	NhanVien_Dao daoNhanVien = new NhanVien_Dao();
	/**
	 * Create the panel.
	 */
	public Panel_ThongKeDoanhThu() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		JLabel lblThng = new JLabel("THỐNG KÊ DOANH THU");
		lblThng.setHorizontalAlignment(SwingConstants.CENTER);
		lblThng.setBounds(0, 5, 1510, 37);
		lblThng.setForeground(new Color(128, 128, 255));
		lblThng.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblThng);
		
		JDateChooser dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.setBounds(206, 171, 243, 50);
		add(dcNgayBatDau);
		
		JLabel lblnNgy = new JLabel("Đến ngày");
		lblnNgy.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblnNgy.setBounds(10, 247, 147, 31);
		add(lblnNgy);
		
		JDateChooser dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.setBounds(206, 247, 243, 50);
		add(dcNgayKetThuc);
		
		
		JButton btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.setBackground(new Color(0, 128, 192));
		btnThongKe.setIcon(new ImageIcon(Panel_ThongKeDoanhThu.class.getResource("/img/ui/stock.png")));
		
		btnThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnThongKe.setBounds(10, 369, 168, 57);
		add(btnThongKe);
		
		JButton btnInThongKe = new JButton("IN THỐNG KÊ");
		btnInThongKe.setBackground(new Color(0, 128, 0));
		btnInThongKe.setIcon(new ImageIcon(Panel_ThongKeDoanhThu.class.getResource("/img/ui/print.png")));
		btnInThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnInThongKe.setBounds(316, 369, 168, 57);
		add(btnInThongKe);
		
		cbMaNhanVien = new JComboBox();
		cbMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 21));
		cbMaNhanVien.setModel(new DefaultComboBoxModel(new String[] {"Tất cả"}));
		cbMaNhanVien.setBounds(206, 96, 147, 52);
		add(cbMaNhanVien);
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select MaNhanVien from NhanVien where TrangThai =0 and ChucVu = 0";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				cbMaNhanVien.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(26, 436, 443, 297);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("TỔNG DOANH THU");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(56, 10, 349, 52);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SỐ HÓA ĐƠN ");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 104, 134, 33);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("DOANH THU");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(10, 205, 134, 33);
		panel.add(lblNewLabel_2_1);
		
		txtTongSoHoaDon = new JTextField();
		txtTongSoHoaDon.setForeground(new Color(128, 64, 0));
		txtTongSoHoaDon.setFont(new Font("Arial", Font.BOLD, 20));
		txtTongSoHoaDon.setEditable(false);
		txtTongSoHoaDon.setBounds(154, 101, 68, 38);
		panel.add(txtTongSoHoaDon);
		txtTongSoHoaDon.setColumns(10);
		
		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setFont(new Font("Arial", Font.BOLD, 25));
		txtTongDoanhThu.setForeground(new Color(128, 64, 0));
		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setColumns(10);
		txtTongDoanhThu.setBounds(154, 201, 241, 38);
		panel.add(txtTongDoanhThu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(494, 94, 1016, 639);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 H\u00F3a \u0110\u01A1n", "T\u00EAn Kh\u00E1ch H\u00E0ng", "T\u00EAn Nh\u00E2n Vi\u00EAn", "Ng\u00E0y T\u1EA1o", "T\u1ED5ng Ti\u1EC1n H\u00F3a \u0110\u01A1n"
			}
		));
		scrollPane.setViewportView(table);
		table.setRowHeight(35);
		JButton btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_ThongKeDoanhThu.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat df = new DecimalFormat("#,### VNĐ");
				model.setRowCount(0);
				txtTongDoanhThu.setText(String.valueOf(df.format(tongDoanhThu())));
				txtTongSoHoaDon.setText(String.valueOf(tongHoaDon()));
				dcNgayBatDau.setDate(null);
				dcNgayKetThuc.setDate(null);
				cbMaNhanVien.setSelectedIndex(0);			
				layDuLieu();
			}
		});
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(180, 369, 129, 57);
		add(btnLamMoi);
		
		JLabel lblNewLabel = new JLabel("Từ ngày");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 177, 110, 31);
		add(lblNewLabel);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên");
		lblMNhnVin.setFont(new Font("Arial", Font.BOLD, 25));
		lblMNhnVin.setBounds(10, 107, 156, 31);
		add(lblMNhnVin);
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		txtTongSoHoaDon.setText(String.valueOf(tongHoaDon())) ;
		txtTongDoanhThu.setText(String.valueOf(df.format(tongDoanhThu())));
		
		
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat df = new DecimalFormat("#,### VNĐ");
				String ngayBatDauString, ngayKetThucString; 
				String maNhanVien;
				double tong = 0;
				txtTongDoanhThu.setText("");
				int tongHoaDon = 0;
				java.util.Date ngayLapDate = dcNgayBatDau.getDate();
				if(dcNgayBatDau.getDate() == null || dcNgayKetThuc.getDate() == null) {
					ngayBatDauString = "'2000-1-1'";
					ngayKetThucString = "'3000-1-1'";
				}
				else
				{
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					 ngayBatDauString = "'" + sdf.format(dcNgayBatDau.getDate()) + "'";
					 ngayKetThucString = "'" + sdf.format(dcNgayKetThuc.getDate()) + "'";
				}		
				if(cbMaNhanVien.getSelectedItem().toString().equalsIgnoreCase("Tất cả"))
					maNhanVien = "";
				else
					maNhanVien = cbMaNhanVien .getSelectedItem().toString();
				model.setRowCount(0);
				ArrayList<HoaDon> ds = new  ArrayList<HoaDon>();
				ds = thongKeTheoNgay(maNhanVien,ngayBatDauString,ngayKetThucString);
				for (HoaDon hoaDon : ds) {
					
					double TongTienTheoNgay = daoHoaDon.layTongTienHoaDon(hoaDon.getMaHoaDon());
					String tenNhanvien = daoNhanVien.layTenTheoMa(hoaDon.getMaNhanVien());
					tongHoaDon++;
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/y");
					Object[] rowData = {hoaDon.getMaHoaDon(),hoaDon.getTenKhachHang(),tenNhanvien,hoaDon.getNgayLapHoaDon().format(dtf),df.format(TongTienTheoNgay)};
					model.addRow(rowData);
					tong += TongTienTheoNgay;
				}
				txtTongDoanhThu.setText(df.format(tong));
				txtTongSoHoaDon.setText(String.valueOf(tongHoaDon));
			}
				
			
		});
		
		btnInThongKe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String ngayLap; 
				String maNhanVien;	
				if(cbMaNhanVien.getSelectedItem().toString().equalsIgnoreCase("Tất cả"))
					maNhanVien = "%%";
				else
					maNhanVien = cbMaNhanVien .getSelectedItem().toString();
				if(maNhanVien.equalsIgnoreCase("%%")) {
					if(dcNgayBatDau.getDate() == null || dcNgayKetThuc.getDate() == null)
						try {
							xuatThongKeDoanhThu(maNhanVien,"Tất cả" ,new JDateChooser(dateFormat.parse("2000-1-1")),new JDateChooser(dateFormat.parse("3000-1-1")), txtTongSoHoaDon.getText(), txtTongDoanhThu.getText());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else
						xuatThongKeDoanhThu(maNhanVien,"Tất cả" ,dcNgayBatDau,dcNgayKetThuc, txtTongSoHoaDon.getText(), txtTongDoanhThu.getText());
				}
				else {
					if(dcNgayBatDau.getDate() == null || dcNgayKetThuc.getDate() == null)
						try {
							xuatThongKeDoanhThu(maNhanVien,maNhanVien ,new JDateChooser(dateFormat.parse("2000-1-1")),new JDateChooser(dateFormat.parse("3000-1-1")), txtTongSoHoaDon.getText(), txtTongDoanhThu.getText());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else
						xuatThongKeDoanhThu(maNhanVien,maNhanVien ,dcNgayBatDau,dcNgayKetThuc, txtTongSoHoaDon.getText(), txtTongDoanhThu.getText());
				}		
			}
		});
		
		
		layDuLieu();
	}
	
	public void xuatThongKeDoanhThu(String maNhanVien,String nhanVienHienThi,JDateChooser ngayBatDau,JDateChooser ngayKetThuc, String soHoaDon, String doanhThu) {
		try {	
			Hashtable data = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/inXuat/InThongKeDoanhThu.jrxml");
			Timestamp ngayBatDauTimestamp = new Timestamp(ngayBatDau.getDate().getTime());
			Timestamp ngayKetThucTimestamp = new Timestamp(ngayKetThuc.getDate().getTime());

			data.put("MaNhanVien", maNhanVien);
			data.put("MaNhanVien1", nhanVienHienThi);
			data.put("NgayBatDau", ngayBatDauTimestamp);
			data.put("NgayKetThuc", ngayKetThucTimestamp);
			data.put("TongSoHoaDon", soHoaDon);
			data.put("TongDoanhThu", doanhThu);
			JasperPrint print = JasperFillManager.fillReport(report, data, ConnectDB.getConnection());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("Error Message: " + e.getMessage());
		}
	}
	
	
	
	// lẩy dữ liệu 
	public void layDuLieu() {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		ds = daoDoanhThu.getAllHoaDon();
		for (HoaDon hoaDon : ds) {
			double TongTien = daoHoaDon.layTongTienHoaDon(hoaDon.getMaHoaDon());
			String tenNhanvien = daoNhanVien.layTenTheoMa(hoaDon.getMaNhanVien());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/y");
			Object[] rowData = {hoaDon.getMaHoaDon(),hoaDon.getTenKhachHang(),tenNhanvien,hoaDon.getNgayLapHoaDon().format(dtf),df.format(TongTien)};
			model.addRow(rowData);
 		}
		
		
	}
	// tổng số hóa đơn 
	public int tongHoaDon() {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ds = daoDoanhThu.getAllHoaDon();
		int tong = 0;
		for (HoaDon hoaDon : ds) {
			tong++;
		}
		return tong;
//		String tongHd = String.valueOf(tong);
//		txtTongSoHoaDon.setText(tongHd);
	}
	// tổng doanh thu
	public double tongDoanhThu() {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		ds = daoDoanhThu.getAllHoaDon();
		double tongDoanhThu = 0 ;
		for (HoaDon hoaDon : ds) {
			double TongTienMoiHoaDon = daoHoaDon.layTongTienHoaDon(hoaDon.getMaHoaDon());
			tongDoanhThu+= TongTienMoiHoaDon;
		}
		return tongDoanhThu;
//		String tongDoanhThutxt = String.valueOf(tongDoanhThu);
//		txtTongDoanhThu.setText(df.format(tongDoanhThu));
	}
	
	public ArrayList<HoaDon> thongKeTheoNgay(String maNhanVien ,String ngayBatDau, String ngayKetThuc){
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		try {
		
			String sql = "select * from HoaDon where CAST(NgayLapHoaDon AS DATE) BETWEEN " + ngayBatDau + "and " + ngayKetThuc + " and MaNhanVien like '%"+ maNhanVien + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
			ds.add(new HoaDon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate() ,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9)));
		}
			} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ds;
	}
}
