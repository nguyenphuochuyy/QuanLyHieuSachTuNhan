package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_Dao;
import dao.ChiTietPhieuDatHang_Dao;
import dao.HoaDon_Dao;
import dao.KhachHang_Dao;
import dao.KhuyenMai_Dao;
import dao.PhieuDatHang_Dao;
import dao.PhieuNhapHang_Dao;
import dao.Sach_Dao;
import dao.VanPhongPham_Dao;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatHang;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.PhieuDatHang;
import entity.Sach;
import entity.VanPhongPham;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;

public class Panel_TaoPhieuDatHang extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtTenKhachHang;
	private JTextField txtMaKhachHang;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTable table_Sach;
	private JTextField txtTongTien;
	private JTextField txtVat;
	private JTable table_CTDatHang;
	private DefaultTableModel model_Sach,model_Vpp,model_CTPhieuDat;
	private JButton btnThemVaoGioHang;
	private JButton btnXoaKhoiGioHang;
	private JButton btnTaoVoiThanhVien;
	private JButton btnXacNhan;
	private JButton btnTaoPhieuDat;
	private JButton btnXoaTrang;
	private JButton btnTimKiem;
	private JComboBox cbKhuyenMai;
	private Double tienGiamDungDiem, tienGiamKhuyenMai;
	private String maKhuyenMai = "KM00";
	
	
	private JTextField txtMaPhieuDat;
	private JPanel panelBanHang;
	private JTextField txtTimKiem;
	private JTextField txtPhaiTra;
	private JTable table_Vpp;
	private JTextField txtDiemDung;
	private JTextField txtGiamDungDiem;
	private JTextField txtDiemCo;
	private JTextField txtTienGiamKhuyenMai;
	private String maNhanVien;
	
	
	
	
	
	Sach_Dao sachDao = new Sach_Dao();
	KhachHang_Dao khachHangDao = new KhachHang_Dao();
	HoaDon_Dao hoaDonDao = new HoaDon_Dao();
	ChiTietHoaDon_Dao chiTietHoaDon_Dao = new ChiTietHoaDon_Dao();
	VanPhongPham_Dao vppDao = new VanPhongPham_Dao();
	KhuyenMai_Dao khuyenMai_Dao = new KhuyenMai_Dao();
	PhieuDatHang_Dao pdhDao = new PhieuDatHang_Dao();
	ChiTietPhieuDatHang_Dao ctpdhDao = new ChiTietPhieuDatHang_Dao();

	/**
	 * Create the panel.
	 */
	public Panel_TaoPhieuDatHang(String ma) {
		maNhanVien = ma;
		setBackground(new Color(240, 240, 240));
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tienGiamDungDiem = (double) 0;
		tienGiamKhuyenMai = (double) 0;
		
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtMaPhieuDat = new JTextField();
		txtMaPhieuDat.setEditable(false);
		txtMaPhieuDat.setBounds(120, 41, 86, 32);
		add(txtMaPhieuDat);
		txtMaPhieuDat.setColumns(10);
		
		
		JPanel panel_PhieuMuaHang = new JPanel();
		panel_PhieuMuaHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_PhieuMuaHang.setBounds(10, 444, 439, 300);
		add(panel_PhieuMuaHang);
		panel_PhieuMuaHang.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("TỔNG");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_3.setBounds(6, 15, 68, 22);
		panel_PhieuMuaHang.add(lblNewLabel_3);
		
		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Arial", Font.BOLD, 15));
		txtTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTongTien.setText("0");
		txtTongTien.setEditable(false);
		txtTongTien.setBounds(99, 13, 275, 29);
		panel_PhieuMuaHang.add(txtTongTien);
		txtTongTien.setColumns(10);
		
		txtVat = new JTextField();
		txtVat.setFont(new Font("Arial", Font.BOLD, 15));
		txtVat.setEditable(false);
		txtVat.setText("0");
		txtVat.setHorizontalAlignment(SwingConstants.RIGHT);
		txtVat.setColumns(10);
		txtVat.setBounds(99, 52, 275, 29);
		panel_PhieuMuaHang.add(txtVat);
		
		btnTaoPhieuDat = new JButton("TẠO PHIẾU");
		btnTaoPhieuDat.setFont(new Font("Arial", Font.BOLD, 12));
		btnTaoPhieuDat.setBackground(new Color(64, 128, 128));
		btnTaoPhieuDat.setIcon(new ImageIcon("D:\\BTPTUD\\QuanLiCuaHangSach\\src\\img\\pay.png"));
		btnTaoPhieuDat.setBounds(149, 234, 142, 44);
		panel_PhieuMuaHang.add(btnTaoPhieuDat);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("VAT(10%)");
		lblNewLabel_3_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_3_2_1.setBounds(6, 54, 68, 22);
		panel_PhieuMuaHang.add(lblNewLabel_3_2_1);
		
		txtPhaiTra = new JTextField();
		txtPhaiTra.setFont(new Font("Arial", Font.BOLD, 15));
		txtPhaiTra.setEditable(false);
		txtPhaiTra.setText("0");
		txtPhaiTra.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPhaiTra.setColumns(10);
		txtPhaiTra.setBounds(99, 180, 131, 29);
		panel_PhieuMuaHang.add(txtPhaiTra);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("PHẢI TRẢ");
		lblNewLabel_3_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_3_1_1.setBounds(6, 182, 75, 22);
		panel_PhieuMuaHang.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("VND");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_4.setBounds(384, 16, 45, 22);
		panel_PhieuMuaHang.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("VND");
		lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_4_1.setBounds(384, 55, 45, 22);
		panel_PhieuMuaHang.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("VND");
		lblNewLabel_4_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_4_2.setBounds(234, 183, 45, 22);
		panel_PhieuMuaHang.add(lblNewLabel_4_2);
		
		JLabel lblKhuynMi = new JLabel("KHUYẾN MÃI");
		lblKhuynMi.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhuynMi.setFont(new Font("Arial", Font.BOLD, 13));
		lblKhuynMi.setBounds(0, 139, 93, 28);
		panel_PhieuMuaHang.add(lblKhuynMi);
		
		List<KhuyenMai> dsKhuyenMai = new ArrayList<KhuyenMai>();
		cbKhuyenMai = new JComboBox();
		cbKhuyenMai.setFont(new Font("Arial", Font.BOLD, 15));
		cbKhuyenMai.setModel(new DefaultComboBoxModel(new String[] {"None"}));
		cbKhuyenMai.setBounds(99, 136, 131, 33);
		panel_PhieuMuaHang.add(cbKhuyenMai);
		for (KhuyenMai km : khuyenMai_Dao.getAllKhuyenMaiDangDienRa()) {
			dsKhuyenMai.add(km);
			String khuyenMai = km.getTenKhuyenMai() + " - " + String.valueOf(km.getTiLeGiam()) + "%";
			cbKhuyenMai.addItem(khuyenMai.toString());
		}
		
		
		

		
		JLabel lblimTchLy_1 = new JLabel("DÙNG ĐIỂM");
		lblimTchLy_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblimTchLy_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblimTchLy_1.setBounds(6, 91, 75, 28);
		panel_PhieuMuaHang.add(lblimTchLy_1);
		
		txtGiamDungDiem = new JTextField();
		txtGiamDungDiem.setFont(new Font("Arial", Font.BOLD, 15));
		txtGiamDungDiem.setText("0");
		txtGiamDungDiem.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGiamDungDiem.setEditable(false);
		txtGiamDungDiem.setColumns(10);
		txtGiamDungDiem.setBounds(99, 91, 275, 29);
		panel_PhieuMuaHang.add(txtGiamDungDiem);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("VND");
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_4_1_1.setBounds(384, 98, 45, 22);
		panel_PhieuMuaHang.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("VND");
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_4_1_1_1.setBounds(381, 143, 45, 22);
		panel_PhieuMuaHang.add(lblNewLabel_4_1_1_1);
		
		txtTienGiamKhuyenMai = new JTextField();
		txtTienGiamKhuyenMai.setFont(new Font("Arial", Font.BOLD, 15));
		txtTienGiamKhuyenMai.setText("0");
		txtTienGiamKhuyenMai.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTienGiamKhuyenMai.setEditable(false);
		txtTienGiamKhuyenMai.setColumns(10);
		txtTienGiamKhuyenMai.setBounds(234, 136, 140, 29);
		panel_PhieuMuaHang.add(txtTienGiamKhuyenMai);
		
		btnThemVaoGioHang = new JButton("THÊM");
		btnThemVaoGioHang.setBackground(new Color(128, 255, 0));
		btnThemVaoGioHang.setIcon(new ImageIcon(Panel_TaoPhieuDatHang.class.getResource("/img/ui/add25.png")));
		btnThemVaoGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThemVaoGioHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThemVaoGioHang.setBounds(133, 389, 149, 44);
		add(btnThemVaoGioHang);
		
		JPanel panel_KhachHang = new JPanel();
		panel_KhachHang.setBounds(10, 84, 439, 294);
		add(panel_KhachHang);
		panel_KhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_KhachHang.setLayout(null);
		
		JLabel lblTenKh = new JLabel("Tên khách hàng ");
		lblTenKh.setFont(new Font("Arial", Font.BOLD, 13));
		lblTenKh.setBounds(10, 78, 122, 30);
		panel_KhachHang.add(lblTenKh);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenKhachHang.setBounds(126, 78, 278, 30);
		panel_KhachHang.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);
		
		JLabel lblMaKh = new JLabel("Mã khách hàng");
		lblMaKh.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaKh.setBounds(10, 32, 122, 31);
		panel_KhachHang.add(lblMaKh);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(126, 32, 146, 30);
		panel_KhachHang.add(txtMaKhachHang);
		
		JLabel lblSdt = new JLabel("Số điện thoại ");
		lblSdt.setFont(new Font("Arial", Font.BOLD, 13));
		lblSdt.setBounds(10, 123, 87, 25);
		panel_KhachHang.add(lblSdt);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSDT.setColumns(10);
		txtSDT.setBounds(126, 118, 278, 30);
		panel_KhachHang.add(txtSDT);
		
		JLabel lblDc = new JLabel("Địa chỉ   ");
		lblDc.setFont(new Font("Arial", Font.BOLD, 13));
		lblDc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDc.setBounds(0, 166, 77, 23);
		panel_KhachHang.add(lblDc);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(126, 161, 278, 30);
		panel_KhachHang.add(txtDiaChi);
		
		JLabel lblNewLabel_1 = new JLabel("KHÁCH HÀNG");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(160, 0, 122, 30);
		panel_KhachHang.add(lblNewLabel_1);
		
		btnXacNhan = new JButton("XÁC NHẬN");
		btnXacNhan.setIcon(new ImageIcon(Panel_TaoPhieuDatHang.class.getResource("/img/ui/submit25.png")));
		btnXacNhan.setFont(new Font("Arial", Font.BOLD, 10));
		btnXacNhan.setBounds(282, 31, 122, 37);
		panel_KhachHang.add(btnXacNhan);
		
		btnTaoVoiThanhVien = new JButton("Khách hàng thành viên");
		btnTaoVoiThanhVien.setBackground(new Color(128, 128, 255));
		btnTaoVoiThanhVien.setBounds(100, 240, 261, 44);
		panel_KhachHang.add(btnTaoVoiThanhVien);
		btnTaoVoiThanhVien.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblimTchLy = new JLabel("Dùng điểm");
		lblimTchLy.setBounds(230, 202, 75, 28);
		panel_KhachHang.add(lblimTchLy);
		lblimTchLy.setHorizontalAlignment(SwingConstants.CENTER);
		lblimTchLy.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtDiemDung = new JTextField();
		txtDiemDung.setForeground(new Color(0, 128, 0));
		txtDiemDung.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDiemDung.setEditable(false);
		txtDiemDung.setBounds(315, 202, 87, 30);
		panel_KhachHang.add(txtDiemDung);
		txtDiemDung.setColumns(10);
		
		JLabel lblTngim = new JLabel("Điểm");
		lblTngim.setHorizontalAlignment(SwingConstants.CENTER);
		lblTngim.setFont(new Font("Arial", Font.BOLD, 13));
		lblTngim.setBounds(-11, 202, 75, 28);
		panel_KhachHang.add(lblTngim);
		
		txtDiemCo = new JTextField();
		txtDiemCo.setForeground(new Color(0, 128, 0));
		txtDiemCo.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDiemCo.setEditable(false);
		txtDiemCo.setColumns(10);
		txtDiemCo.setBounds(126, 202, 87, 30);
		panel_KhachHang.add(txtDiemCo);
		btnTaoVoiThanhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTaoVoiThanhVien.addActionListener(this);
		
		btnXoaKhoiGioHang = new JButton("XÓA");
		btnXoaKhoiGioHang.setBackground(new Color(255, 0, 0));
		btnXoaKhoiGioHang.setIcon(new ImageIcon(Panel_TaoPhieuDatHang.class.getResource("/img/ui/delete.png")));
		btnXoaKhoiGioHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoaKhoiGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaKhoiGioHang.setBounds(285, 389, 164, 44);
		add(btnXoaKhoiGioHang);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(459, 83, 1051, 196);
		add(scrollPane_1);
		
		table_Sach = new JTable();
		table_Sach.setFont(new Font("Arial", Font.PLAIN, 15));
		table_Sach.setModel(model_Sach =new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "Tên sách", "Thể loại",  "Giá bán",  "Số lượng còn", "Số trang", "Tác giả"
			}
		));
		table_Sach.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table_Sach.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_Sach.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_Sach.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_Sach.getColumnModel().getColumn(4).setPreferredWidth(30);
		table_Sach.getColumnModel().getColumn(5).setPreferredWidth(20);
		table_Sach.setRowHeight(35);
		scrollPane_1.setViewportView(table_Sach);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 533, 1066, 211);
		add(scrollPane);
		
		table_CTDatHang = new JTable();
		table_CTDatHang.setFont(new Font("Arial", Font.PLAIN, 15));
		table_CTDatHang.setModel(model_CTPhieuDat = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "Gi\u00E1 B\u00E1n", "S\u1ED1 L\u01B0\u1EE3ng", "Th\u00E0nh Ti\u1EC1n"
			}
		));
		table_CTDatHang.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table_CTDatHang.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table_CTDatHang.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_CTDatHang.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_CTDatHang.setRowHeight(35);
		scrollPane.setViewportView(table_CTDatHang);
				
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(459, 302, 887, 210);
		add(scrollPane_2);
				
		table_Vpp = new JTable();
		table_Vpp.setFont(new Font("Arial", Font.PLAIN, 15));
		table_Vpp.setModel(model_Vpp = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID" , "Tên sản phẩm" , "Giá bán" , "Số lượng còn" , "Loại VPP"
			}
		));
		table_Vpp.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table_Vpp.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_Vpp.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_Vpp.setRowHeight(35);
		scrollPane_2.setViewportView(table_Vpp);
		
		JLabel lblAnhSP = new JLabel("");
		lblAnhSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblAnhSP.setBounds(1356, 302, 154, 208);
		add(lblAnhSP);
		
		if(layMaPhieuDat() == null)
			txtMaPhieuDat.setText("a");
		else
		txtMaPhieuDat.setText(layMaPhieuDat());
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
					String maSP = model_Sach.getValueAt(rowSach, 0).toString();
					ConnectDB.getInstance();
					Connection con = ConnectDB.getConnection();
					try {
						String sql = "select HinhAnh from SanPham where MaSanPham = '"+ maSP +"'";
						java.sql.Statement statement =  con.createStatement();
						ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
						while(rs.next()) {
							byte[] imageData = rs.getBytes("HinhAnh");
							if (imageData != null) {
				                ImageIcon imageIcon = new ImageIcon(imageData);
				                Image scaledImage = imageIcon.getImage().getScaledInstance(189, 196, Image.SCALE_SMOOTH);
				                imageIcon = new ImageIcon(scaledImage);
				                lblAnhSP.setIcon(imageIcon);
				            } else {
				                lblAnhSP.setIcon(null);
				            }
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
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
				String maSP = model_Vpp.getValueAt(rowVpp, 0).toString();
				ConnectDB.getInstance();
				Connection con = ConnectDB.getConnection();
				try {
					String sql = "select HinhAnh from SanPham where MaSanPham = '"+ maSP +"'";
					java.sql.Statement statement =  con.createStatement();
					ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
					while(rs.next()) {
						byte[] imageData = rs.getBytes("HinhAnh");
						if (imageData != null) {
			                ImageIcon imageIcon = new ImageIcon(imageData);
			                Image scaledImage = imageIcon.getImage().getScaledInstance(189, 196, Image.SCALE_SMOOTH);
			                imageIcon = new ImageIcon(scaledImage);
			                lblAnhSP.setIcon(imageIcon);
			            } else {
			                lblAnhSP.setIcon(null);
			            }
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	});
	
	txtDiemDung.getDocument().addDocumentListener(new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			try {
				tinhTienDungDiem();
				} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			try {
				tinhTienDungDiem();
				} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			try {
				tinhTienDungDiem();
				} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		public void tinhTienDungDiem() throws ParseException {
            try {
            	int diemDung;
            	if(txtDiemDung.getText().equalsIgnoreCase("")) {
            		diemDung = 0;
            	} else {
            		diemDung = Integer.parseInt(txtDiemDung.getText());
            	}
            		tienGiamDungDiem = (double) (diemDung * 1000);
            		DecimalFormat df = new DecimalFormat("#,###");
                	NumberFormatter formatter = new NumberFormatter(df);
    				txtGiamDungDiem.setText(df.format(tienGiamDungDiem));
    				Double tienTra = df.parse(txtTongTien.getText()).doubleValue()*110/100 - tienGiamDungDiem - tienGiamKhuyenMai;
    				txtPhaiTra.setText(df.format(tienTra));
            	}   	
             catch (NumberFormatException ex) {
            }
		}
	});
	
	cbKhuyenMai.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			int khuyenMai;
			if(cbKhuyenMai.getSelectedItem().toString().equalsIgnoreCase("None")) {
				khuyenMai = 0;
			} else {
				int viTri = cbKhuyenMai.getSelectedIndex();
				khuyenMai = dsKhuyenMai.get(viTri-1).getTiLeGiam();
				maKhuyenMai = dsKhuyenMai.get(viTri-1).getMaKhuyenMai();
			}
				try {
					DecimalFormat df = new DecimalFormat("#,###");
	            	NumberFormatter formatter = new NumberFormatter(df);
	            	Number tongTien = df.parse(txtTongTien.getText());
	                tienGiamKhuyenMai = tongTien.doubleValue()*khuyenMai/100;
	                txtTienGiamKhuyenMai.setText(df.format(tienGiamKhuyenMai));
	                Double tienTra = tongTien.doubleValue()*110/100 - tienGiamDungDiem - tienGiamKhuyenMai;
					txtPhaiTra.setText(df.format(tienTra));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
	});
	
		JLabel lblNewLabel = new JLabel("PHIẾU ĐẶT");
		lblNewLabel.setBounds(25, 39, 86, 32);
		add(lblNewLabel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblNewLabel_2 = new JLabel("ĐẶT HÀNG");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setForeground(new Color(255, 128, 64));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel_2.setBounds(0, 0, 1525, 52);
		add(lblNewLabel_2);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(1031, 41, 301, 32);
		add(txtTimKiem);
		
		btnTimKiem = new JButton("TÌM TÊN");
		btnTimKiem.setFont(new Font("Dialog", Font.BOLD, 11));
		btnTimKiem.setBounds(1342, 41, 138, 32);
		add(btnTimKiem);
		
		btnXoaTrang = new JButton("LÀM MỚI");
		btnXoaTrang.setBackground(new Color(255, 255, 128));
		btnXoaTrang.setIcon(new ImageIcon(Panel_TaoPhieuDatHang.class.getResource("/img/ui/refresh25.png")));
		btnXoaTrang.setFont(new Font("Dialog", Font.BOLD, 12));
		btnXoaTrang.setBounds(10, 389, 120, 44);
		add(btnXoaTrang);
		
		btnThemVaoGioHang.addActionListener(this);
		btnXoaKhoiGioHang.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnTaoPhieuDat.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTimKiem.addActionListener(this);
		
		layDuLieuSach();
		layDuLieuVpp();
		
		
		
	}
	
	public void layDuLieuSach() {
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		for (Sach sp : sachDao.getAllSach()) {
			Object[] rowData = {sp.getMaSach(),sp.getTenSach(),sp.getTheLoai() ,  df.format(sp.getGiaBan())   , sp.getSoLuong() , sp.getSoTrang() , sp.getTacGia()};
			model_Sach.addRow(rowData);
			
		}
	}
	
	public void layDuLieuVpp() {
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		for (VanPhongPham sp : vppDao.getAllVanPhongPham()) {
			Object[] rowData = {sp.getMaSanPham(),sp.getTenSanPham() , df.format(sp.getGiaBan())   , sp.getSoLuong() , sp.getLoaiVanPhongPham()};
			model_Vpp.addRow(rowData);
			
		}
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
			 Object[] rowData = {sach.getMaSach(),sach.getTenSach(),sach.getTheLoai(),df.format(sach.getGiaBan()),sach.getSoLuong(),sach.getSoTrang(),sach.getTacGia()};
			 model_Sach.addRow(rowData);
		}
		
		for (VanPhongPham vanPhongPham : vppDao.timKiemVanPhongPham("", tenSanPham, "", "")) {
			Object[] rowData = {vanPhongPham.getMaSanPham(),vanPhongPham.getTenSanPham(),df.format(vanPhongPham.getGiaBan()),vanPhongPham.getSoLuong(),vanPhongPham.getLoaiVanPhongPham() };
			model_Vpp.addRow(rowData);
		}
		
	}
	
	public String layMaPhieuDat() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "IF ((SELECT MAX(CAST(RIGHT(MaPhieuDatHang, LEN(MaPhieuDatHang) - 2) AS int)) + 1 FROM PhieuDatHang) < 10)  "
					+ "SELECT CONCAT('DH0', MAX(CAST(RIGHT(MaPhieuDatHang, LEN(MaPhieuDatHang) - 2) AS int)) + 1) FROM PhieuDatHang  "
					+ "ELSE SELECT CONCAT('DH', MAX(CAST(RIGHT(MaPhieuDatHang, LEN(MaPhieuDatHang) - 2) AS int)) + 1) FROM PhieuDatHang  ";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maDH = rs.getString(1);
				return maDH;
			}
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return null;
	}
	
	public void xoaTrang() {
		txtMaPhieuDat.setText(layMaPhieuDat());
//		model_CTPhieuDat.setRowCount(0);
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtMaKhachHang.setEditable(false);
		txtTenKhachHang.setEditable(true);
		txtSDT.setEditable(true);
		txtDiaChi.setEditable(true);
		txtVat.setText("");
		txtTongTien.setText("0");
		cbKhuyenMai.setSelectedIndex(0);
		txtTienGiamKhuyenMai.setText("0");
		txtPhaiTra.setText("0");
		txtDiemCo.setText("");
		txtDiemDung.setText("");
		txtTimKiem.setText("");
		model_Sach.setRowCount(0);		
		layDuLieuSach();
		model_Vpp.setRowCount(0);
		layDuLieuVpp();
	}
	
	public int kiemTraTrungCTPhieuDatSach(String ma) {
		int rowSach = table_Sach.getSelectedRow();
		for(int j=0;j<table_CTDatHang.getRowCount();j++) {
			if(model_CTPhieuDat.getValueAt(j, 0).toString().equalsIgnoreCase(ma))
				return j;
		}
		return -1;
	}
	
	public int kiemTraTrungCTPhieuDatVpp(String ma) {
		int rowVpp = table_Vpp.getSelectedRow();
		for(int j=0;j<table_CTDatHang.getRowCount();j++) {
			if(model_CTPhieuDat.getValueAt(j, 0).toString().equalsIgnoreCase(ma))
				return j;
		}
		return -1;
	}
	
	public boolean kiemTraLapPhieuDat() {
		int count = model_CTPhieuDat.getRowCount();
		if(count == 0) {
			JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm để mua");
			return false;
		}
		if(!txtDiemDung.getText().equalsIgnoreCase("")) {
			try {	
				int diemCo = Integer.parseInt(txtDiemCo.getText());
				int diemDung = Integer.parseInt(txtDiemDung.getText());
				if(diemDung < 0) {
					JOptionPane.showMessageDialog(this, "Điểm dùng phải lớn hơn 0");
					return false;
				}		
				else {
					if(diemDung > diemCo) {
						JOptionPane.showMessageDialog(this, "Điểm không đủ");
						return false;
					}		
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Điểm dùng phải là số nguyên");
				return false;
			}
		}	
		String ma = txtMaKhachHang.getText();
		String ten = txtTenKhachHang.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		if(ten.equalsIgnoreCase("") || sdt.equalsIgnoreCase("") || diaChi.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin khách hàng");
			return false;
		}
		return true;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		DecimalFormat df = new DecimalFormat("#,###");
		DecimalFormat df2 = new DecimalFormat("#,###VNĐ");
		if(o.equals(btnThemVaoGioHang)) { 
			int rowSach = table_Sach.getSelectedRow();
			int rowVpp = table_Vpp.getSelectedRow();

			if(rowSach == -1 && rowVpp == -1) {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm để thêm");
			} 
			else if(rowSach != -1) {
				String soLuongBan = JOptionPane.showInputDialog(this,"Nhập số lượng:");
				if (soLuongBan != null) {
				    try {
				        int soLuong = Integer.parseInt(soLuongBan);
				        if(soLuong <= 0)
				        	JOptionPane.showMessageDialog(this, "Số lượng phải lón hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        else {
				        	if(Integer.parseInt(model_Sach.getValueAt(rowSach,4).toString()) < soLuong) {
				        		JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không đủ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        	}
				        	else {
				        		Number number ;
				        		if(kiemTraTrungCTPhieuDatSach(model_Sach.getValueAt(rowSach,0).toString()) != -1) {
				        			int dongTrung = kiemTraTrungCTPhieuDatSach(model_Sach.getValueAt(rowSach, 0).toString());
				        			int capNhatSoLuong = soLuong + Integer.parseInt(model_CTPhieuDat.getValueAt(dongTrung, 4).toString());
				        			model_CTPhieuDat.setValueAt(capNhatSoLuong,dongTrung, 4);
				        			number = df.parse(model_Sach.getValueAt(rowSach, 3).toString());
					                double thanhTien = number.doubleValue() * capNhatSoLuong;
					        		String dfThanhTien = df.format(thanhTien);
					        		model_CTPhieuDat.setValueAt(dfThanhTien , dongTrung, 5);
				        		} else {
				        			number = df.parse(model_Sach.getValueAt(rowSach, 3).toString());
					                double thanhTien = number.doubleValue() * soLuong;
					        		String dfThanhTien = df2.format(thanhTien);
				        			Object[] rowData = {model_Sach.getValueAt(rowSach, 0).toString(),model_Sach.getValueAt(rowSach, 1).toString(),"Sách",model_Sach.getValueAt(rowSach, 3).toString(),soLuongBan, dfThanhTien};
				        			model_CTPhieuDat.addRow(rowData);
				        		}
				        		model_Sach.setValueAt(Integer.parseInt(model_Sach.getValueAt(rowSach, 4).toString())-soLuong, rowSach, 4);
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
				String soLuongBan = JOptionPane.showInputDialog(this,"Nhập số lượng:");
				if (soLuongBan != null) {
				    try {
				        int soLuong = Integer.parseInt(soLuongBan);
				        if(soLuong <= 0)
				        	JOptionPane.showMessageDialog(this, "Số lượng phải lón hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        else {
				        	if(Integer.parseInt(model_Vpp.getValueAt(rowVpp,3).toString()) < soLuong) {
				        		JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không đủ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        	}
				        	else {
				        		Number number ;
				        		if(kiemTraTrungCTPhieuDatVpp(model_Vpp.getValueAt(rowVpp,0).toString()) != -1) {
				        			int dongTrung = kiemTraTrungCTPhieuDatVpp(model_Vpp.getValueAt(rowVpp, 0).toString());
				        			int capNhatSoLuong = soLuong + Integer.parseInt(model_CTPhieuDat.getValueAt(dongTrung, 4).toString());
				        			model_CTPhieuDat.setValueAt(capNhatSoLuong,dongTrung, 4);
				        			number = df.parse(model_Vpp.getValueAt(rowVpp, 2).toString());
					                double thanhTien = number.doubleValue() * capNhatSoLuong;
					        		String dfThanhTien = df.format(thanhTien);
					        		model_CTPhieuDat.setValueAt(dfThanhTien , dongTrung, 5);
				        		} else {
				        			number = df.parse(model_Vpp.getValueAt(rowVpp, 2).toString());
					                double thanhTien = number.doubleValue() * soLuong;
					        		String dfThanhTien = df2.format(thanhTien);
				        			Object[] rowData = {model_Vpp.getValueAt(rowVpp, 0).toString(),model_Vpp.getValueAt(rowVpp, 1).toString(),"Văn phòng phẩm",model_Vpp.getValueAt(rowVpp, 2).toString(),soLuongBan, dfThanhTien};
				        			model_CTPhieuDat.addRow(rowData);
				        		}
				        		model_Vpp.setValueAt(Integer.parseInt(model_Vpp.getValueAt(rowVpp, 3).toString())-soLuong, rowVpp, 3);
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
				for(int i=0;i<table_CTDatHang.getRowCount();i++) {
					Number number = df.parse(model_CTPhieuDat.getValueAt(i, 5).toString());
					tongTien+= number.doubleValue();
				}
				txtTongTien.setText(df.format(tongTien));
				int vat = (int) (tongTien*0.1);
				txtVat.setText(df.format(vat));
				Double tienTra = tongTien+vat;
				txtPhaiTra.setText(df.format(tienTra));
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(btnXoaKhoiGioHang)) {
			int row = table_CTDatHang.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xóa");
			} else {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					int soLuong = Integer.parseInt(table_CTDatHang.getValueAt(row, 4).toString());
					String maSP = table_CTDatHang.getValueAt(row, 0).toString();
					for(int i=0;i<table_Sach.getRowCount();i++) {
						if(model_Sach.getValueAt(i, 0).toString().equalsIgnoreCase(maSP))
							model_Sach.setValueAt(Integer.parseInt(model_Sach.getValueAt(i, 4).toString())+soLuong, i, 4);
					}
					for(int i=0;i<table_Vpp.getRowCount();i++) {
						if(model_Vpp.getValueAt(i, 0).toString().equalsIgnoreCase(maSP))
							model_Vpp.setValueAt(Integer.parseInt(model_Vpp.getValueAt(i, 3).toString())+soLuong, i, 3);
					}
					
					model_CTPhieuDat.removeRow(row);
					Double tongTien = (double) 0;
					try {
						for(int i=0;i<table_CTDatHang.getRowCount();i++) {
							Number number = df.parse(model_CTPhieuDat.getValueAt(i, 5).toString());
							tongTien+= number.doubleValue();
						}
						txtTongTien.setText(df.format(tongTien));
						int vat = (int) (tongTien*0.1);
						txtVat.setText(df.format(vat));
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}	
			}
		}
		if(o.equals(btnTaoVoiThanhVien)) {
			if(btnTaoVoiThanhVien.getText().equalsIgnoreCase("Khách hàng thành viên")) {
				txtMaKhachHang.setEditable(true);
				txtTenKhachHang.setEditable(false);
				txtSDT.setEditable(false);
				txtDiaChi.setEditable(false);
				txtDiemDung.setEditable(true);
				btnTaoVoiThanhVien.setText("Khách hàng không là thành viên");
			} else {
				txtMaKhachHang.setEditable(false);
				txtTenKhachHang.setEditable(true);
				txtSDT.setEditable(true);
				txtDiaChi.setEditable(true);
				txtDiemDung.setEditable(false);
				txtMaKhachHang.setText("");
				txtTenKhachHang.setText("");
				txtTenKhachHang.setText("");
				txtDiaChi.setText("");
				txtDiemDung.setText("");
				btnTaoVoiThanhVien.setText("Khách hàng thành viên");

			}	
		}
		if(o.equals(btnXacNhan)) {
			String maKH = txtMaKhachHang.getText();
			ConnectDB.getInstance();
			Connection con1 = ConnectDB.getConnection();
			try {
				String sql = "select HoTenKhachHang, Sdt, DiaChi, DiemTichLuy from [dbo].[KhachHang] where MaKhachHang like N'" + maKH +"'";
				java.sql.Statement statement =  con1.createStatement();
				ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
				while(rs.next()) {
					if(rs.getString(1) != null) {
						txtTenKhachHang.setText(rs.getString(1));
						txtSDT.setText(rs.getString(2));
						txtDiaChi.setText(rs.getString(3));
						txtDiemCo.setText(String.valueOf(rs.getInt(4)));
					}					
					else {
						JOptionPane.showMessageDialog(this, "Không tồn tại khách hàng này trong dữ liệu");
					}
				}
			} catch (Exception e3) {
				// TODO: handle exception
				e3.printStackTrace();
				JOptionPane.showMessageDialog(this, "Không tồn tại khách hàng này trong dữ liệu");
			}
		}
		if(o.equals(btnXoaTrang)) {
			xoaTrang();	
		}
		if(o.equals(btnTimKiem)) {
			layDanhSachTimKiem();
		}
		if(o.equals(btnTaoPhieuDat)) {
			if(kiemTraLapPhieuDat()) {
				String maPhieuDat = txtMaPhieuDat.getText();
				String maKhachHang;
				int dungDiem;
				if(txtDiemDung.getText().equalsIgnoreCase(""))
					dungDiem = 0;
				else
					dungDiem = Integer.parseInt(txtDiemDung.getText());
				if(txtMaKhachHang.getText().equalsIgnoreCase(""))
					maKhachHang = " ";
				else {
					maKhachHang = txtMaKhachHang.getText();
				}
				String tenKhachHang;
				if(txtTenKhachHang.getText().equalsIgnoreCase(""))
					tenKhachHang = null;
				else
					tenKhachHang = txtTenKhachHang.getText();
				String sdt;
				if(txtSDT.getText().equalsIgnoreCase(""))
					sdt = null;
				else
					sdt = txtSDT.getText();
				String diaChi;
				if(txtDiaChi.getText().equalsIgnoreCase(""))
					diaChi = null;
				else
					diaChi = txtDiaChi.getText();
				PhieuDatHang pdh = new PhieuDatHang(maPhieuDat, maNhanVien, maKhachHang, LocalDate.now(), tenKhachHang, sdt, diaChi,maKhuyenMai,dungDiem,"Chưa thanh toán");
				pdhDao.themPhieuDatHang(pdh);	
				for(int i=0;i<table_CTDatHang.getRowCount();i++) {
					String maSP = model_CTPhieuDat.getValueAt(i, 0).toString();
					String tenSP = model_CTPhieuDat.getValueAt(i, 1).toString();
					Double giaBan = (double) 0;
					try {
						giaBan = df.parse(model_CTPhieuDat.getValueAt(i, 3).toString()).doubleValue();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int soLuong = Integer.parseInt(model_CTPhieuDat.getValueAt(i, 4).toString());
					ChiTietPhieuDatHang ctpdh = new ChiTietPhieuDatHang(maPhieuDat, maSP, tenSP,giaBan, soLuong);
					ctpdhDao.themChiTietPhieuDatHang(ctpdh);
					sachDao.capNhatSoLuongSanPham(soLuong, maSP);
				}
				JOptionPane.showMessageDialog(this, "Tạo phiếu đặt hàng thành công");
				pdhDao.xuatPhieuDat(maPhieuDat);;
				xoaTrang();
				layMaPhieuDat();
			}
		}
	}
}
