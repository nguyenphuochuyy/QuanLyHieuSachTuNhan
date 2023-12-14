package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_Dao;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Panel_ThongKeSanPham extends JPanel {
	private JTable table;
	private JTable table_SanPhamSapHet;
	DefaultTableModel model_SanPhamSapHet = new DefaultTableModel();
	HoaDon_Dao daoHoaDon = new HoaDon_Dao();
	ChiTietHoaDon_Dao daoCTHD = new ChiTietHoaDon_Dao();
	DefaultTableModel model  = new DefaultTableModel();
	/**
	 * Create the panel.
	 */
	public Panel_ThongKeSanPham() {
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JLabel lblThngKSn = new JLabel("THỐNG KÊ SẢN PHẨM");
		lblThngKSn.setBounds(595, 5, 330, 37);
		lblThngKSn.setForeground(new Color(128, 128, 192));
		lblThngKSn.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblThngKSn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(35, 162, 395, 495);
		add(panel);
		panel.setLayout(null);
		
		JButton btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.setIcon(new ImageIcon(Panel_ThongKeSanPham.class.getResource("/img/ui/stock.png")));
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sanPhamDaBan();
			}
		});
		btnThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnThongKe.setBounds(92, 100, 199, 57);
		panel.add(btnThongKe);
		
		JButton btnInThongKe = new JButton("IN THỐNG KÊ");
		btnInThongKe.setIcon(new ImageIcon(Panel_ThongKeSanPham.class.getResource("/img/ui/print.png")));
		btnInThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		btnInThongKe.setBounds(92, 399, 199, 57);
		panel.add(btnInThongKe);
		
		JButton btnSanPhamSapHet = new JButton("SẮP HẾT");
		btnSanPhamSapHet.setIcon(new ImageIcon(Panel_ThongKeSanPham.class.getResource("/img/ui/out25.png")));
		btnSanPhamSapHet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model_SanPhamSapHet.setRowCount(0);
				Connection con = ConnectDB.getInstance().getConnection();
				try {
					String sql = "select * from SanPham where SoLuong < 20";
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) {
						Object[] data = {rs.getString(1),rs.getString(2),rs.getString(7),rs.getInt(8),rs.getDouble(5)}; 
						model_SanPhamSapHet.addRow(data);
					}
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		
		btnSanPhamSapHet.setFont(new Font("Arial", Font.BOLD, 15));
		btnSanPhamSapHet.setBounds(93, 195, 198, 57);
		panel.add(btnSanPhamSapHet);
		
		JButton btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setIcon(new ImageIcon(Panel_ThongKeSanPham.class.getResource("/img/ui/refresh25.png")));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model_SanPhamSapHet.setRowCount(0);
				model.setRowCount(0);
			}
		});
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(92, 299, 199, 57);
		panel.add(btnLamMoi);
		
		JLabel lblNewLabel = new JLabel("CHỨC NĂNG");
		lblNewLabel.setForeground(new Color(128, 64, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(107, 10, 163, 49);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(457, 104, 1030, 288);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "\u0110\u00E3 B\u00E1n", "Gi\u00E1 B\u00E1n", "Th\u00E0nh Ti\u1EC1n"
			}
		));
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(457, 431, 1030, 306);
		add(scrollPane_1);
		table_SanPhamSapHet = new JTable();
		table_SanPhamSapHet.setFont(new Font("Arial", Font.PLAIN, 15));
		table_SanPhamSapHet.setModel(model_SanPhamSapHet = new  DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 B\u00E1n"
				}
			));
		scrollPane_1.setViewportView(table_SanPhamSapHet);
		
	}
	
	// lấy dữ liệu sản phẩm đã bán
	public void sanPhamDaBan(){
		model.setRowCount(0);
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			String sql = "SELECT SanPham.MaSanPham, SanPham.TenSanPham, SanPham.LoaiSanPham,SanPham.GiaBan,  SUM(ChiTietHoaDon.SoLuong) AS TongSoLuong , (sum(ChiTietHoaDon.SoLuong)* SanPham.GiaBan) as  ThanhTien FROM SanPham LEFT JOIN ChiTietHoaDon ON  SanPham.MaSanPham = ChiTietHoaDon.MaSanPham GROUP BY SanPham.MaSanPham, SanPham.TenSanPham, SanPham.LoaiSanPham,SanPham.GiaBan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				if(rs.getInt(5) != 0 ) {
				Object[] dataRow = {rs.getString(1),rs.getString(2),rs.getInt(5),rs.getDouble(4),rs.getDouble(6)};
				model.addRow(dataRow);
			}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
