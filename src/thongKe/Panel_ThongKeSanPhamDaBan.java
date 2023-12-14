package thongKe;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_Dao;
import entity.HoaDon;
import entity.KhuyenMai;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Panel_ThongKeSanPhamDaBan extends JPanel {
	private JTable table;
	HoaDon_Dao daoHoaDon = new HoaDon_Dao();
	ChiTietHoaDon_Dao daoCTHD = new ChiTietHoaDon_Dao();
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Create the panel.
	 */
	public Panel_ThongKeSanPhamDaBan() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setBounds(0, 41, 1520, 777);
		setLayout(null);
		JLabel lblThngKSn = new JLabel("THỐNG KÊ SẢN PHẨM ĐÃ BÁN");
		lblThngKSn.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngKSn.setBackground(new Color(128, 128, 255));
		lblThngKSn.setBounds(0, 10, 1510, 50);
		lblThngKSn.setForeground(new Color(128, 128, 255));
		lblThngKSn.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblThngKSn);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(25, 100, 303, 633);
		add(panel);
		panel.setLayout(null);

		JButton btnInThongKe = new JButton("IN THỐNG KÊ");
		btnInThongKe.setBackground(new Color(0, 128, 0));
		btnInThongKe.setIcon(new ImageIcon(Panel_ThongKeSanPhamDaBan.class.getResource("/img/ui/print.png")));
		btnInThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnInThongKe.setBounds(55, 552, 178, 57);
		panel.add(btnInThongKe);

		JLabel lblNewLabel = new JLabel("CHỨC NĂNG");
		lblNewLabel.setForeground(new Color(128, 64, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(70, 26, 163, 49);
		panel.add(lblNewLabel);

		JDateChooser dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.setBounds(10, 140, 283, 49);
		panel.add(dcNgayBatDau);

		JButton btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_ThongKeSanPhamDaBan.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(55, 450, 178, 57);
		panel.add(btnLamMoi);

		JButton btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.setBackground(new Color(0, 128, 192));
		btnThongKe.setIcon(new ImageIcon(Panel_ThongKeSanPhamDaBan.class.getResource("/img/ui/stock.png")));
		btnThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnThongKe.setBounds(55, 355, 178, 57);
		panel.add(btnThongKe);

		JLabel lblNewLabel_1 = new JLabel("Từ ngày");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 94, 124, 36);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Đến ngày");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 218, 124, 36);
		panel.add(lblNewLabel_1_1);

		JDateChooser dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.setBounds(10, 264, 283, 49);
		panel.add(dcNgayKetThuc);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(338, 100, 1172, 633);
		add(scrollPane);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(model = new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 S\u1EA3n Ph\u1EA9m",
				"T\u00EAn S\u1EA3n Ph\u1EA9m", "\u0110\u00E3 B\u00E1n", "Gi\u00E1 B\u00E1n" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.setRowHeight(35);
		scrollPane.setViewportView(table);

		thongKeSanPhamDaBan("'2000-1-1'", "'3000-2-2'");
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dcNgayBatDau.setDate(null);
				dcNgayKetThuc.setDate(null);
				thongKeSanPhamDaBan("'2000-1-1'", "'3000-2-2'");
			}
		});

		btnThongKe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (dcNgayBatDau.getDate() == null || dcNgayKetThuc.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Chọn ngày để thống kê");
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					String ngayBatDauString = "'" + sdf.format(dcNgayBatDau.getDate()) + "'";
					String ngayKetThucString = "'" + sdf.format(dcNgayKetThuc.getDate()) + "'";
					thongKeSanPhamDaBan(ngayBatDauString, ngayKetThucString);
				}
			}
		});

		btnInThongKe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dcNgayBatDau.getDate() == null || dcNgayKetThuc.getDate() == null) {
					try {
						xuatThongKeSanPhamDaBan(new JDateChooser(dateFormat.parse("2000-1-1")),new JDateChooser(dateFormat.parse("3000-1-1")));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					xuatThongKeSanPhamDaBan(dcNgayBatDau,dcNgayKetThuc);
				}
			}
		});

	}

	public void xuatThongKeSanPhamDaBan(JDateChooser ngayBatDau, JDateChooser ngayKetThuc) {
		try {
			Hashtable data = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/inXuat/InThongKeSanPhamDaBan.jrxml");
			Timestamp ngayBatDauTimestamp = new Timestamp(ngayBatDau.getDate().getTime());
			Timestamp ngayKetThucTimestamp = new Timestamp(ngayKetThuc.getDate().getTime());
			data.put("NgayBatDau", ngayBatDauTimestamp);
			data.put("NgayKetThuc", ngayKetThucTimestamp);
			JasperPrint print = JasperFillManager.fillReport(report, data, ConnectDB.getConnection());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Message: " + e.getMessage());
		}
	}

//	public void xuatThongKeSanPhamDaBanALL() {
//		try {
//			Hashtable data = new Hashtable();
//			JasperReport report = JasperCompileManager.compileReport("src/inXuat/InThongKeSanPhamDaBanALL.jrxml");
//			JasperPrint print = JasperFillManager.fillReport(report, data, ConnectDB.getConnection());
//			JasperViewer.viewReport(print, false);
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Error Message: " + e.getMessage());
//		}
//	}

	public ArrayList<HoaDon> thongKeSanPhamDaBan(String ngayBatDau, String ngayKetThuc) {
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		model.setRowCount(0);
		try {
			String sql = "SELECT SanPham.MaSanPham, ChiTietHoaDon.TenSanPham, tong = sum(ChiTietHoaDon.SoLuong), SanPham.GiaBan "
					+ "FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.MaHoaDon = ChiTietHoaDon.MaHoaDon INNER JOIN SanPham ON ChiTietHoaDon.MaSanPham = SanPham.MaSanPham "
					+ "where NgayLapHoaDon BETWEEN " + ngayBatDau + "and " + ngayKetThuc
					+ " group by ChiTietHoaDon.TenSanPham, SanPham.GiaBan,SanPham.MaSanPham";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String row[] = { rs.getString(1), rs.getString(2), rs.getString(3), df.format(rs.getDouble(4)) };
				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ds;
	}
}
