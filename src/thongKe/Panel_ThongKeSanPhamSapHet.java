package thongKe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JPanel;

import connectDB.ConnectDB;
import entity.HoaDon;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Panel_ThongKeSanPhamSapHet extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table_Sach;
	private JTable table_VPP;
	private DefaultTableModel model_Sach, model_VPP;

	/**
	 * Create the panel.
	 */
	public Panel_ThongKeSanPhamSapHet() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THỐNG KÊ SẢN PHẨM SẮP HẾT HÀNG");
		lblNewLabel.setForeground(new Color(128, 128, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(422, 10, 607, 59);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 1500, 250);
		add(scrollPane);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		table_Sach = new JTable();
		table_Sach.setFont(new Font("Arial", Font.PLAIN, 15));
		table_Sach.setModel(model_Sach = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã sách","Tên sách","Thể loại","Nhà xuất bản","Giá bán","Số trang","Tác giả","Số lượng còn"
			}
		));
		table_Sach.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_Sach.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_Sach.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_Sach.getColumnModel().getColumn(3).setPreferredWidth(130);
		table_Sach.getColumnModel().getColumn(6).setPreferredWidth(130);
		table_Sach.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table_Sach.setRowHeight(35);
		scrollPane.setViewportView(table_Sach);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 440, 1500, 250);
		add(scrollPane_1);
		
		table_VPP = new JTable();
		table_VPP.setFont(new Font("Arial", Font.PLAIN, 15));
		table_VPP.setModel(model_VPP = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã sản phẩm","Tên sản phẩm","Nhà sản xuất","Giá bán","Loại văn phòng phẩm","Số lượng còn"
			}
		));
		table_VPP.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_VPP.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_VPP.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table_VPP.setRowHeight(35);
		scrollPane_1.setViewportView(table_VPP);
		
		JLabel lblNewLabel_1 = new JLabel("Sách");
		lblNewLabel_1.setForeground(new Color(128, 64, 0));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 77, 72, 37);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Văn phòng phẩm");
		lblNewLabel_1_1.setForeground(new Color(128, 64, 0));
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(10, 395, 244, 37);
		add(lblNewLabel_1_1);
		
		JButton btnInThongKe = new JButton("IN THỐNG KÊ");
		btnInThongKe.setBackground(new Color(0, 128, 0));
		btnInThongKe.setIcon(new ImageIcon(Panel_ThongKeSanPhamSapHet.class.getResource("/img/ui/print.png")));
		btnInThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnInThongKe.setBounds(49, 700, 205, 59);
		add(btnInThongKe);
		
		thongKeSanPhamSapHet();
		
		btnInThongKe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				InThongKeSanPhamSapHet inThongKeSanPhamSapHet = new InThongKeSanPhamSapHet();
//				inThongKeSanPhamSapHet.setVisible(true);
				xuatThongKeSanPhamSapHet();
			}
		});
	}
	
	public void xuatThongKeSanPhamSapHet() {
		try {	
			Hashtable data = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/inXuat/InThongKeSanPhamSapHet.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, data, ConnectDB.getConnection());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("Error Message: " + e.getMessage());
		}
	}
	
	public void thongKeSanPhamSapHet(){
		Connection con = ConnectDB.getInstance().getConnection();
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		model_Sach.setRowCount(0);
		try {			
			String sqlSach = "SELECT MaSanPham, TenSanPham, TheLoaiSach, NhaSanXuat, GiaBan, SoTrang, TacGia, SoLuong "
					+ "FROM SanPham "
					+ "where SoLuong <= 50 and LoaiSanPham = N'Sách' ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sqlSach);
			while(rs.next()) {
				String row[] = {rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),df.format(rs.getDouble(5)),rs.getString(6),rs.getString(7),rs.getString(8)};
				model_Sach.addRow(row);
		}
			} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		model_VPP.setRowCount(0);
		try {			
			String sqlVPP = "SELECT MaSanPham, TenSanPham, NhaSanXuat, GiaBan, LoaiVanPhongPham, SoLuong "
					+ "FROM SanPham "
					+ "where SoLuong <= 50 and LoaiSanPham = N'Văn phòng phẩm'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sqlVPP);
			while(rs.next()) {
				String row[] = {rs.getString(1), rs.getString(2),rs.getString(3),df.format(rs.getDouble(4)),rs.getString(5),rs.getString(6)};
				model_VPP.addRow(row);
		}
			} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
