package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import dao.HoaDon_Dao;
import dao.NhanVien_Dao;
import dao.ThongKeDoanhThu_Dao;
import entity.HoaDon;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;

public class Panel_ThongKeDoanhThu extends JPanel {
	private JTextField txtTongSoHoaDon;
	private JTextField txtTongDoanhThu;
	private JTable table;
	ThongKeDoanhThu_Dao daoDoanhThu = new ThongKeDoanhThu_Dao();
	DefaultTableModel model  = new DefaultTableModel();
	HoaDon_Dao daoHoaDon = new HoaDon_Dao();
	NhanVien_Dao daoNhanVien = new NhanVien_Dao();
	/**
	 * Create the panel.
	 */
	public Panel_ThongKeDoanhThu() {
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		JLabel lblThng = new JLabel("THỐNG KÊ DOANH THU");
		lblThng.setBounds(577, 5, 365, 37);
		lblThng.setForeground(new Color(128, 128, 192));
		lblThng.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblThng);
		
		JDateChooser dateChooser_NgayLap = new JDateChooser();
		dateChooser_NgayLap.setBounds(78, 150, 306, 50);
		add(dateChooser_NgayLap);
		
		JButton btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				String ngayLap; 
				double tong = 0;
				txtTongDoanhThu.setText("");
				int tongHoaDon = 0;
				java.util.Date ngayLapDate = dateChooser_NgayLap.getDate();
				if(ngayLapDate == null ) {
					ngayLap = "is not null";
					
				}
				else
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					ngayLap = "= ' " + dateFormat.format(ngayLapDate)+ "'";
				}
				model.setRowCount(0);
				ArrayList<HoaDon> ds = new  ArrayList<HoaDon>();
				ds = daoDoanhThu.thongKeTheoNgay(ngayLap);
				for (HoaDon hoaDon : ds) {
					
					double TongTienTheoNgay = daoHoaDon.layTongTienHoaDon(hoaDon.getMaHoaDon());
					String tenNhanvien = daoNhanVien.layTenTheoMa(hoaDon.getMaNhanVien());
					tongHoaDon++;
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/y");
					Object[] rowData = {hoaDon.getMaHoaDon(),hoaDon.getTenKhachHang(),tenNhanvien,hoaDon.getNgayLapHoaDon().format(dtf),TongTienTheoNgay};
					model.addRow(rowData);
					tong += TongTienTheoNgay;
				}
				String tongDoanhThutxt = String.valueOf(tong);
				txtTongDoanhThu.setText(String.valueOf(tongDoanhThutxt));
				txtTongSoHoaDon.setText(String.valueOf(tongHoaDon));
				
			}
				
			
		});
		btnThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnThongKe.setBounds(39, 271, 147, 57);
		add(btnThongKe);
		
		JButton btnInThongKe = new JButton("IN THỐNG KÊ");
		btnInThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnInThongKe.setBounds(253, 271, 147, 57);
		add(btnInThongKe);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(26, 436, 443, 297);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("TỔNG DOANH THU");
		lblNewLabel_1.setForeground(new Color(0, 255, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(56, 10, 349, 52);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Số Hóa Đơn ");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 104, 121, 33);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Doanh Thu ");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(10, 205, 116, 33);
		panel.add(lblNewLabel_2_1);
		
		txtTongSoHoaDon = new JTextField();
		txtTongSoHoaDon.setBounds(139, 105, 68, 38);
		panel.add(txtTongSoHoaDon);
		txtTongSoHoaDon.setColumns(10);
		
		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setColumns(10);
		txtTongDoanhThu.setBounds(136, 205, 241, 38);
		panel.add(txtTongDoanhThu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(494, 94, 1016, 639);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 H\u00F3a \u0110\u01A1n", "T\u00EAn Kh\u00E1ch H\u00E0ng", "T\u00EAn Nh\u00E2n Vi\u00EAn", "Ng\u00E0y T\u1EA1o", "T\u1ED5ng Ti\u1EC1n H\u00F3a \u0110\u01A1n"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				txtTongDoanhThu.setText("");
				txtTongSoHoaDon.setText("");
				layDuLieu();
				tongDoanhThu();
				tongHoaDon();
			}
		});
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(39, 361, 147, 57);
		add(btnLamMoi);
		layDuLieu();
		tongHoaDon();
		tongDoanhThu();
	}
	
	// lẩy dữ liệu 
	public void layDuLieu() {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ds = daoDoanhThu.getAllHoaDon();
		for (HoaDon hoaDon : ds) {
			double TongTien = daoHoaDon.layTongTienHoaDon(hoaDon.getMaHoaDon());
			String tenNhanvien = daoNhanVien.layTenTheoMa(hoaDon.getMaNhanVien());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/y");
			Object[] rowData = {hoaDon.getMaHoaDon(),hoaDon.getTenKhachHang(),tenNhanvien,hoaDon.getNgayLapHoaDon().format(dtf),TongTien};
			model.addRow(rowData);
 		}
		
		
	}
	// tổng số hóa đơn 
	public void tongHoaDon() {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ds = daoDoanhThu.getAllHoaDon();
		int tong = 0;
		for (HoaDon hoaDon : ds) {
			tong++;
		}
		String tongHd = String.valueOf(tong);
		txtTongSoHoaDon.setText(tongHd);
	}
	// tổng doanh thu
	public void tongDoanhThu() {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		ds = daoDoanhThu.getAllHoaDon();
		double tongDoanhThu = 0 ;
		for (HoaDon hoaDon : ds) {
			double TongTienMoiHoaDon = daoHoaDon.layTongTienHoaDon(hoaDon.getMaHoaDon());
			tongDoanhThu+= TongTienMoiHoaDon;
		}
		String tongDoanhThutxt = String.valueOf(tongDoanhThu);
		txtTongDoanhThu.setText(tongDoanhThutxt + " VND");
	}
}
