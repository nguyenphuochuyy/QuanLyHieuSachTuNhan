package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import dao.NhanVien_Dao;
import dao.PhieuDatHang_Dao;
import dao.Sach_Dao;
import dao.VanPhongPham_Dao;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatHang;
import entity.HoaDon;
import entity.PhieuDatHang;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;

public class Panel_TimKiemPhieuDatHang extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhieuDat;
	private JTextField txtTenKhachHang;
	private JTable table_PhieuDat;
	private JTable table_CTPhieuDat;
	private JTextField txtMaKhachHang;
	private JTextField txtMaNhanVien;
	private DefaultTableModel model_PhieuDat, model_ChiTiet;
	private JButton btnThanhToan,btnTimKiem,btnLamMoi,btnXoa;
	private JComboBox cbTrangThai;
	ArrayList<String> dsKhuyenMai = new ArrayList<>();
	
	PhieuDatHang_Dao pdhDao = new PhieuDatHang_Dao();
	ChiTietPhieuDatHang_Dao ctpdhDao = new ChiTietPhieuDatHang_Dao();
	Sach_Dao sachDao = new Sach_Dao();
	KhachHang_Dao khachHangDao = new KhachHang_Dao();
	HoaDon_Dao hoaDonDao = new HoaDon_Dao();
	ChiTietHoaDon_Dao chiTietHoaDon_Dao = new ChiTietHoaDon_Dao();
	VanPhongPham_Dao vppDao = new VanPhongPham_Dao();
	NhanVien_Dao nhanVienDao = new NhanVien_Dao();
	private JTextField txtTongTien;
	private JButton btnInPhieuDat;

	/**
	 * Create the panel.
	 */
	public Panel_TimKiemPhieuDatHang() {
		setBounds(0, 41, 1520, 777);
		setLayout(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM PHIẾU ĐẶT HÀNG");
		lblNewLabel.setForeground(new Color(255, 128, 0));
		lblNewLabel.setBounds(599, 0, 462, 52);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(1108, 118, 402, 610);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Mã phiếu đặt hàng");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 20, 150, 36);
		panel_2.add(lblNewLabel_2);
		
		txtMaPhieuDat = new JTextField();
		txtMaPhieuDat.setFont(new Font("Arial", Font.BOLD, 20));
		txtMaPhieuDat.setBounds(160, 20, 218, 36);
		panel_2.add(txtMaPhieuDat);
		txtMaPhieuDat.setColumns(10);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Tên khách hàng");
		lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1_1.setBounds(10, 215, 122, 36);
		panel_2.add(lblNewLabel_2_1_1_1);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(160, 216, 218, 36);
		panel_2.add(txtTenKhachHang);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Trạng thái");
		lblNewLabel_2_1_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1_1_1_1_1.setBounds(10, 347, 122, 36);
		panel_2.add(lblNewLabel_2_1_1_1_1_1_1);
		
		cbTrangThai = new JComboBox();
		cbTrangThai.setFont(new Font("Arial", Font.PLAIN, 15));
		cbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Đã thanh toán", "Chưa thanh toán"}));
		cbTrangThai.setBounds(163, 346, 215, 36);
		panel_2.add(cbTrangThai);
		
		btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setForeground(new Color(0, 0, 0));
		btnTimKiem.setBackground(new Color(64, 128, 128));
		btnTimKiem.setIcon(new ImageIcon(Panel_TimKiemPhieuDatHang.class.getResource("/img/ui/search30.png")));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setBounds(10, 393, 177, 65);
		panel_2.add(btnTimKiem);
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Mã khách hàng");
		lblNewLabel_2_1_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1_1_2.setBounds(10, 82, 122, 36);
		panel_2.add(lblNewLabel_2_1_1_1_2);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(160, 82, 218, 36);
		panel_2.add(txtMaKhachHang);
		
		JLabel lblNewLabel_2_1_1_1_3 = new JLabel("Mã nhân viên");
		lblNewLabel_2_1_1_1_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1_1_3.setBounds(10, 150, 122, 36);
		panel_2.add(lblNewLabel_2_1_1_1_3);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(160, 150, 218, 36);
		panel_2.add(txtMaNhanVien);
		
		btnThanhToan = new JButton("THANH TOÁN");
		btnThanhToan.setForeground(new Color(0, 0, 0));
		btnThanhToan.setBackground(new Color(128, 64, 0));
		btnThanhToan.setIcon(new ImageIcon(Panel_TimKiemPhieuDatHang.class.getResource("/img/ui/bill.png")));
		btnThanhToan.setFont(new Font("Arial", Font.BOLD, 15));
		btnThanhToan.setBounds(215, 469, 177, 65);
		panel_2.add(btnThanhToan);
		
		btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setForeground(new Color(0, 0, 0));
		btnLamMoi.setBackground(new Color(255, 255, 128));
		btnLamMoi.setIcon(new ImageIcon(Panel_TimKiemPhieuDatHang.class.getResource("/img/ui/refresh.png")));
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setBounds(215, 393, 177, 65);
		panel_2.add(btnLamMoi);
		
		btnXoa = new JButton("XÓA PHIẾU");
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setIcon(new ImageIcon(Panel_TimKiemPhieuDatHang.class.getResource("/img/ui/delete.png")));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setBounds(10, 469, 177, 65);
		panel_2.add(btnXoa);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Tổng tiền");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1_1_1.setBounds(10, 285, 122, 36);
		panel_2.add(lblNewLabel_2_1_1_1_1);
		
		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(160, 285, 218, 36);
		panel_2.add(txtTongTien);
		
		btnInPhieuDat = new JButton("IN PHIẾU");
		btnInPhieuDat.setForeground(new Color(0, 0, 0));
		btnInPhieuDat.setBackground(new Color(37, 90, 46));
		btnInPhieuDat.setIcon(new ImageIcon(Panel_TimKiemPhieuDatHang.class.getResource("/img/ui/print.png")));
		btnInPhieuDat.setFont(new Font("Arial", Font.BOLD, 15));
		btnInPhieuDat.setBounds(123, 545, 177, 55);
		panel_2.add(btnInPhieuDat);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm phiếu đặt hàng");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(1188, 80, 322, 42);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 96, 1088, 311);
		add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 430, 1088, 316);
		add(scrollPane_1);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		table_PhieuDat = new JTable();
		table_PhieuDat.setFont(new Font("Arial", Font.PLAIN, 15));
		table_PhieuDat.setModel(model_PhieuDat = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mã phiếu đặt" , "Mã nhân viên", "Tên nhân viên" ,"Mã khách hàng" , "Tên khách hàng" , "Số điện thoại" , "Địa chỉ" , "Ngày lập" , "KM" , "Điểm" , "Trạng thái"
			}
		));
		
		table_PhieuDat.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_PhieuDat.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_PhieuDat.getColumnModel().getColumn(2).setPreferredWidth(110);
		table_PhieuDat.getColumnModel().getColumn(3).setPreferredWidth(70);
		table_PhieuDat.getColumnModel().getColumn(4).setPreferredWidth(100);
		table_PhieuDat.getColumnModel().getColumn(5).setPreferredWidth(70);
		table_PhieuDat.getColumnModel().getColumn(6).setPreferredWidth(50);
		table_PhieuDat.getColumnModel().getColumn(7).setPreferredWidth(70);
		table_PhieuDat.getColumnModel().getColumn(8).setPreferredWidth(2);
		table_PhieuDat.getColumnModel().getColumn(9).setPreferredWidth(2);
		table_PhieuDat.getColumnModel().getColumn(10).setPreferredWidth(90);
		table_PhieuDat.setRowHeight(35);
	

		scrollPane.setViewportView(table_PhieuDat);
		
		
		table_CTPhieuDat = new JTable();
		table_CTPhieuDat.setFont(new Font("Arial", Font.PLAIN, 15));
		table_CTPhieuDat.setModel(model_ChiTiet = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mã phiếu đặt" , "Mã sản phẩm" , "Tên sản phẩm" , "Giá bán" , "Số lượng" , "Thành tiền"
			}
		));
		table_CTPhieuDat.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table_CTPhieuDat.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table_CTPhieuDat.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_CTPhieuDat.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_CTPhieuDat.getColumnModel().getColumn(2).setPreferredWidth(250);
		table_CTPhieuDat.setRowHeight(35);
		scrollPane_1.setViewportView(table_CTPhieuDat);
		layDanhSachPhieuDat();
		
		table_PhieuDat.addMouseListener(new MouseListener() {
			
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
				DecimalFormat df = new DecimalFormat("#,### VNĐ");
				int row = table_PhieuDat.getSelectedRow();
				String maPhieuDat = model_PhieuDat.getValueAt(row, 0).toString();
				layChiTietDatHang(maPhieuDat);
				txtMaPhieuDat.setText(model_PhieuDat.getValueAt(row, 0).toString());
				txtMaNhanVien.setText(model_PhieuDat.getValueAt(row, 1).toString());
				txtMaKhachHang.setText(model_PhieuDat.getValueAt(row, 3).toString());
				txtTenKhachHang.setText(model_PhieuDat.getValueAt(row, 4).toString());
				cbTrangThai.setSelectedItem(model_PhieuDat.getValueAt(row, 10).toString());
				txtTongTien.setText(df.format(pdhDao.layTongTienPhieuDat(maPhieuDat)));
			}
		});
		
		txtTongTien.setEditable(false);
		
		btnThanhToan.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXoa.addActionListener(this);
		btnInPhieuDat.addActionListener(this);


	
	}
	
	
	public void layDanhSachPhieuDat() {
		model_PhieuDat.setRowCount(0);
		for (PhieuDatHang lh : pdhDao.getAllPhieuDatHangTimKiem()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			Object[] rowData = {lh.getMaPhieuDat(),lh.getMaNhanVien(),nhanVienDao.layTenTheoMa(lh.getMaNhanVien()),lh.getMaKhachHang() ,lh.getTenKhachHang(), lh.getSdt() , lh.getDiaChi() , lh.getNgayDatHang().format(formatter), lh.getMaKhuyenMai(), lh.getDungDiem() , lh.getTrangThai()};
			model_PhieuDat.addRow(rowData);
		}
		for (PhieuDatHang lh : pdhDao.getAllPhieuDatHang()) {
			dsKhuyenMai.add(lh.getMaKhuyenMai());
		}
	}
	
	
	public void layChiTietDatHang(String maHoaDon) {
		model_ChiTiet.setRowCount(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DecimalFormat df = new DecimalFormat("#,### VNĐ");
		for (ChiTietPhieuDatHang lh : ctpdhDao.getAllChiTietPhieuDatHang(maHoaDon)) {
			Double thanhTien = lh.getSoLuong() * lh.getGiaBan();
			Object[] rowData = {lh.getMaPhieuDatHang(),lh.getMaSanPham(),lh.getTenSanPham(),df.format(lh.getGiaBan()),lh.getSoLuong(),df.format(thanhTien)};
			model_ChiTiet.addRow(rowData);
		}
	}
	
	
	
	public void lamMoi() {
		model_ChiTiet.setRowCount(0);
		model_PhieuDat.setRowCount(0);
		layDanhSachPhieuDat();
		txtMaKhachHang.setText("");
		txtMaPhieuDat.setText("");
		txtMaNhanVien.setText("");
		txtTenKhachHang.setText("");
		txtTongTien.setText("");
		cbTrangThai.setSelectedIndex(0);
	}
	
	public String layMaHoaDon() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "IF ((SELECT MAX(CAST(RIGHT(MaHoaDon, LEN(MaHoaDon) - 2) AS int)) + 1 FROM HoaDon) < 10)  "
					+ "SELECT CONCAT('HD0', MAX(CAST(RIGHT(MaHoaDon, LEN(MaHoaDon) - 2) AS int)) + 1) FROM HoaDon  "
					+ "ELSE SELECT CONCAT('HD', MAX(CAST(RIGHT(MaHoaDon, LEN(MaHoaDon) - 2) AS int)) + 1) FROM HoaDon  ";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maHd = rs.getString(1);
				return maHd;
			}
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		if(o.equals(btnThanhToan)) {
			int row = table_PhieuDat.getSelectedRow();
			if(row == -1)
				JOptionPane.showMessageDialog(this, "Chọn phiếu đặt hàng để thanh toán");
			else {
				if(model_PhieuDat.getValueAt(row, 9).toString().equalsIgnoreCase("Đã thanh toán"))
					JOptionPane.showMessageDialog(this, "Phiếu đặt hàng đã được thanh toán");
				else {
					String maHoaDon = layMaHoaDon();
					String maNhanVien = model_PhieuDat.getValueAt(row, 1).toString();
					String maKhachHang;
					if(model_PhieuDat.getValueAt(row, 3).toString().equalsIgnoreCase(" "))
						maKhachHang = " ";
					else
						maKhachHang = model_PhieuDat.getValueAt(row, 3).toString();
					String tenKhachHang = model_PhieuDat.getValueAt(row, 4).toString();
					String sdt = model_PhieuDat.getValueAt(row, 5).toString();
					String diaChi = model_PhieuDat.getValueAt(row, 6).toString();
					String maKhuyenMai;
//					if(model_PhieuDat.getValueAt(row, 7).toString().equalsIgnoreCase("0"))
//						maKhuyenMai = "0";
//					else
//						maKhuyenMai = model_PhieuDat.getValueAt(row, 7).toString();
					int dungDiem = Integer.parseInt(model_PhieuDat.getValueAt(row, 9).toString());
					HoaDon dh = new HoaDon(maHoaDon, maNhanVien, maKhachHang, LocalDate.now(), tenKhachHang, sdt, diaChi,dsKhuyenMai.get(row),dungDiem);
					hoaDonDao.themHoaDon(dh);		
					khachHangDao.capNhatDiem(model_PhieuDat.getValueAt(row, 3).toString(), -Integer.parseInt(model_PhieuDat.getValueAt(row, 9).toString()));
					double tongTien = pdhDao.layTongTienPhieuDat(model_PhieuDat.getValueAt(row, 0).toString());
					int diemCong = (int) (tongTien / 100000);
					khachHangDao.capNhatDiem(model_PhieuDat.getValueAt(row, 3).toString(), diemCong);
					for(int i=0;i<table_CTPhieuDat.getRowCount();i++) {
						String maSP = model_ChiTiet.getValueAt(i, 1).toString();
						String tenSP = model_ChiTiet.getValueAt(i, 2).toString();
						Double giaBan = (double) 0;
						//giaBan = df.parse(model_ChiTiet.getValueAt(i, 3).toString()).doubleValue();
						DecimalFormat df = new DecimalFormat("#,### VNĐ");
		            	NumberFormatter formatter = new NumberFormatter(df);
		            	Number giaBanStr = 0;
		            	try {
							giaBanStr = df.parse(model_ChiTiet.getValueAt(i, 3).toString());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						giaBan = giaBanStr.doubleValue();
						int soLuong = Integer.parseInt(model_ChiTiet.getValueAt(i, 4).toString());
						ChiTietHoaDon cthd = new ChiTietHoaDon(maHoaDon, maSP, tenSP,giaBan, soLuong);
						chiTietHoaDon_Dao.themChiTietHoaDon(cthd);
						sachDao.capNhatSoLuongSanPham(soLuong, maSP);
					}
					pdhDao.capNhatTrangThai(model_PhieuDat.getValueAt(row, 0).toString());
					JOptionPane.showMessageDialog(this, "Thanh toán thành công");
					lamMoi();
				}
			}
		}
		if(o.equals(btnTimKiem)) {
			String maPhieuDat;
			if(txtMaPhieuDat.getText().contentEquals("")) {
				maPhieuDat = "";
			} else {
				maPhieuDat = txtMaPhieuDat.getText();
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
			String trangThai;
			if(cbTrangThai.getSelectedItem().toString().equalsIgnoreCase("Tất cả"))
				trangThai = "is not null";
			else if(cbTrangThai.getSelectedItem().toString().equalsIgnoreCase("Đã thanh toán"))
				trangThai = "= 1";
			else
				trangThai = "= 0";

//			pdhDao.timKiemPhieuDatHang(maPhieuDat, maKhachHang, maNhanVien, tenKhachHang, trangThai);
			model_PhieuDat.setRowCount(0);
			for (PhieuDatHang lh : pdhDao.timKiemPhieuDatHang(maPhieuDat, maKhachHang, maNhanVien, tenKhachHang, trangThai)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y");
				Object[] rowData = {lh.getMaPhieuDat(),lh.getMaNhanVien(),nhanVienDao.layTenTheoMa(lh.getMaNhanVien()),lh.getMaKhachHang() ,lh.getTenKhachHang(), lh.getSdt() , lh.getDiaChi() , lh.getNgayDatHang().format(formatter), lh.getMaKhuyenMai(), lh.getDungDiem() , lh.getTrangThai()};
				model_PhieuDat.addRow(rowData);
			}
				
		}
		if(o.equals(btnLamMoi)) {
			lamMoi();
		}
		if(o.equals(btnXoa)) {
			int row = table_PhieuDat.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(this, "Chọn phiếu đặt để xóa");
			}
			else {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					pdhDao.xoaPhieuDat(model_PhieuDat.getValueAt(row, 0).toString());
					model_PhieuDat.removeRow(row);
					for(int i=0;i<table_CTPhieuDat.getRowCount();i++) {
						sachDao.capNhatSoLuongSanPham(-Integer.parseInt(model_ChiTiet.getValueAt(i, 4).toString()), model_ChiTiet.getValueAt(i, 1).toString());
					}
					model_ChiTiet.setRowCount(0);
					JOptionPane.showMessageDialog(this, "Xóa phiếu đặt thành công");
				}	
			}
		}
		if(o.equals(btnInPhieuDat)) {
			int row = table_PhieuDat.getSelectedRow();
			if(row == -1)
				JOptionPane.showMessageDialog(null, "Chọn phiếu đặt hàng để in");
			else {
				pdhDao.xuatPhieuDat(model_PhieuDat.getValueAt(row, 0).toString());
			}	
		}
	}
}
