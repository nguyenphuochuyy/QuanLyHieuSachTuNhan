package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.NhanVien_Dao;
import thongKe.Panel_ThongKeDoanhThu;
import thongKe.Panel_ThongKeSanPhamDaBan;
import thongKe.Panel_ThongKeSanPhamSapHet;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dimension;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrangChuQuanLy extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelNoiDung;
	private CardLayout cardLayout;
    private JPanel cardPanel;
    private JMenu mnNhapHang;
	public  JTextField txtMaNhanVien;
	private DangNhap dangnhap = new DangNhap() ;
	public JTextField txtTenNhanVien;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TrangChuQuanLy frame = new TrangChuQuanLy();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	NhanVien_Dao nhanVienDao = new NhanVien_Dao();
	private JTextField txtThoiGian;

	/**
	 * Create the frame.
	 */
	public TrangChuQuanLy(String ma) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1405, 800);
		setBounds(0, 0, 1920, 1050);
		//setSize(1650,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 1, 1200, 42);
		contentPane.add(menuBar);
		
		JMenu mnTrangChu = new JMenu("TRANG CHỦ");
		mnTrangChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Panel_AnhTrangChu panelAnhTrangChu = new Panel_AnhTrangChu();
			    cardPanel.add(panelAnhTrangChu, "ANHTRANGCHU");
			    getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "ANHTRANGCHU");
			}
		});
		mnTrangChu.setHorizontalAlignment(SwingConstants.CENTER);
		mnTrangChu.setFont(new Font("Arial", Font.BOLD, 15));
		mnTrangChu.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/home.png")));
		menuBar.add(mnTrangChu);
		mnTrangChu.setPreferredSize(new Dimension(150, 43));
		JMenu mnQuanLy = new JMenu("DANH MỤC");
		mnQuanLy.setHorizontalAlignment(SwingConstants.CENTER);
		mnQuanLy.setFont(new Font("Arial", Font.BOLD, 15));
		mnQuanLy.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/icons8-manage-32.png")));
		menuBar.add(mnQuanLy);
		mnQuanLy.setPreferredSize(new Dimension(150, 43));
		JMenu mnQuanLyNhanVien = new JMenu("NHÂN VIÊN");
		mnQuanLyNhanVien.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/customer.png")));
		mnQuanLyNhanVien.setFont(new Font("Arial", Font.BOLD, 12));
		mnQuanLy.add(mnQuanLyNhanVien);
		
		JMenuItem mniCapNhatNhanVien = new JMenuItem("CẬP NHẬT NHÂN VIÊN");
		mniCapNhatNhanVien.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/update32.png")));
		mniCapNhatNhanVien.setFont(new Font("Arial", Font.BOLD, 10));
		mnQuanLyNhanVien.add(mniCapNhatNhanVien);
		
		JMenuItem mniTimKiemNhanVien = new JMenuItem("TÌM KIẾM NHÂN VIÊN");
		mniTimKiemNhanVien.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/search.png")));
		mniTimKiemNhanVien.setFont(new Font("Arial", Font.BOLD, 10));
		mnQuanLyNhanVien.add(mniTimKiemNhanVien);
		
		JMenu mnKhachHang = new JMenu("KHÁCH HÀNG");
		mnKhachHang.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/account.png")));
		mnKhachHang.setFont(new Font("Arial", Font.BOLD, 12));
		mnQuanLy.add(mnKhachHang);
		
		JMenuItem mniCapNhatKhachHang = new JMenuItem("CẬP NHẬT KHÁCH HÀNG");
		mniCapNhatKhachHang.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/update32.png")));
		mniCapNhatKhachHang.setFont(new Font("Arial", Font.BOLD, 10));
		mnKhachHang.add(mniCapNhatKhachHang);
		
		JMenuItem mniTimKiemKhachHang = new JMenuItem("TÌM KIẾM KHÁCH HÀNG");
		mniTimKiemKhachHang.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/search.png")));
		mniTimKiemKhachHang.setFont(new Font("Arial", Font.BOLD, 10));
		mnKhachHang.add(mniTimKiemKhachHang);
		
		JMenu mnKhuyenMai = new JMenu("KHUYẾN MÃI");
		mnKhuyenMai.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/percent.png")));
		mnKhuyenMai.setFont(new Font("Arial", Font.BOLD, 12));
		mnQuanLy.add(mnKhuyenMai);
		
		JMenuItem mniTaoKhuyenMai = new JMenuItem("TẠO KHUYẾN MÃI");
		mniTaoKhuyenMai.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/create30.png")));
		mniTaoKhuyenMai.setFont(new Font("Arial", Font.BOLD, 10));
		mnKhuyenMai.add(mniTaoKhuyenMai);
		
		JMenu mnPhieuNhapHang = new JMenu("PHIẾU NHẬP HÀNG");
		mnPhieuNhapHang.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/submit25.png")));
		mnPhieuNhapHang.setFont(new Font("Arial", Font.BOLD, 12));
		mnQuanLy.add(mnPhieuNhapHang);
		
		JMenuItem mniTaoPhieuNhapHang = new JMenuItem("TẠO PHIẾU NHẬP HÀNG");
		mniTaoPhieuNhapHang.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/create30.png")));
		mniTaoPhieuNhapHang.setFont(new Font("Arial", Font.BOLD, 10));
		mnPhieuNhapHang.add(mniTaoPhieuNhapHang);
		
		JMenuItem mniTimKiemPhieuNhapHang = new JMenuItem("TÌM KIẾM PHIẾU NHẬP HÀNG");
		mniTimKiemPhieuNhapHang.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/search30.png")));
		mniTimKiemPhieuNhapHang.setFont(new Font("Arial", Font.BOLD, 10));
		mnPhieuNhapHang.add(mniTimKiemPhieuNhapHang);
		
		JMenu mnHoaDon = new JMenu("HÓA ĐƠN");
		mnHoaDon.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/bill.png")));
		mnHoaDon.setFont(new Font("Arial", Font.BOLD, 12));
		mnQuanLy.add(mnHoaDon);
		
		JMenuItem mniTimKiemHoaDon = new JMenuItem("TÌM KIẾM HÓA ĐƠN");
		mniTimKiemHoaDon.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/search30.png")));
		mniTimKiemHoaDon.setFont(new Font("Arial", Font.BOLD, 10));
		mnHoaDon.add(mniTimKiemHoaDon);
		
		JMenu mnPhieuDatHang = new JMenu("PHIẾU ĐẶT HÀNG");
		mnPhieuDatHang.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/order.png")));
		mnPhieuDatHang.setFont(new Font("Arial", Font.BOLD, 12));
		mnQuanLy.add(mnPhieuDatHang);
		
		
		JMenuItem mniTimKiemPhieuDatHang = new JMenuItem("TÌM KIẾM PHIẾU ĐẶT HÀNG");
		mniTimKiemPhieuDatHang.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/search30.png")));
		mniTimKiemPhieuDatHang.setFont(new Font("Arial", Font.BOLD, 10));
		mnPhieuDatHang.add(mniTimKiemPhieuDatHang);
		
		JMenu mnSanPham = new JMenu("SẢN PHẨM");
		mnSanPham.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/icons8-item-30.png")));
		mnSanPham.setPreferredSize(new Dimension(150, 43));
		mnSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		mnSanPham.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnSanPham);
		
		JMenu mnSach = new JMenu("SÁCH");
		mnSach.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/book.png")));
		mnSach.setFont(new Font("Arial", Font.BOLD, 12));
		mnSanPham.add(mnSach);
		
		JMenuItem mniCapNhatSach = new JMenuItem("CẬP NHẬT SÁCH");
		mniCapNhatSach.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/update32.png")));
		mniCapNhatSach.setFont(new Font("Arial", Font.BOLD, 10));
		mnSach.add(mniCapNhatSach);
		
		JMenuItem mniTimkiemSach = new JMenuItem("TÌM KIẾM NHÂN VIÊN");
		mniTimkiemSach.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/search25.png")));
		mniTimkiemSach.setFont(new Font("Arial", Font.BOLD, 10));
		mnSach.add(mniTimkiemSach);
		
		JMenu mnVpp = new JMenu("VĂN PHÒNG PHẨM");
		mnVpp.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/ruler.png")));
		mnVpp.setFont(new Font("Arial", Font.BOLD, 12));
		mnSanPham.add(mnVpp);
		
		JMenuItem mniCapNhatVpp = new JMenuItem("CẬP NHẬT SÁCH");
		mniCapNhatVpp.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/update32.png")));
		mniCapNhatVpp.setFont(new Font("Arial", Font.BOLD, 10));
		mnVpp.add(mniCapNhatVpp);
		
		JMenuItem mniTimkiemVpp = new JMenuItem("TÌM KIẾM NHÂN VIÊN");
		mniTimkiemVpp.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/search25.png")));
		mniTimkiemVpp.setFont(new Font("Arial", Font.BOLD, 10));
		mnVpp.add(mniTimkiemVpp);
		
//		JMenu mnSach = new JMenu("SÁCH");
//		mnSach.setFont(new Font("Arial", Font.BOLD, 15));
//		mnSach.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/book.png")));
//		menuBar.add(mnSach);
//		mnSach.setPreferredSize(new Dimension(150, 43));
//		JMenuItem mniCapNhatSach = new JMenuItem("CẬP NHẬT SÁCH");
//		mniCapNhatSach.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/update32.png")));
//		mniCapNhatSach.setFont(new Font("Arial", Font.BOLD, 12));
//		mnSach.add(mniCapNhatSach);
//		
//		JMenuItem mniTimKiemSach = new JMenuItem("TÌM KIẾM  SÁCH");
//		mniTimKiemSach.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/search.png")));
//		mniTimKiemSach.setFont(new Font("Arial", Font.BOLD, 12));
//		mnSach.add(mniTimKiemSach);
//		
//		JMenu mnVanPhongPham = new JMenu("VĂN PHÒNG PHẨM");
//		mnVanPhongPham.setFont(new Font("Arial", Font.BOLD, 15));
//		mnVanPhongPham.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/ruler.png")));
//		menuBar.add(mnVanPhongPham);
//		mnVanPhongPham.setPreferredSize(new Dimension(200, 43));
//		JMenuItem mniCapNhatVanPhongPham = new JMenuItem("CẬP NHẬT VĂN PHÒNG PHẨM ");
//		mniCapNhatVanPhongPham.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/update32.png")));
//		mniCapNhatVanPhongPham.setFont(new Font("Arial", Font.BOLD, 12));
//		mnVanPhongPham.add(mniCapNhatVanPhongPham);
//		
//		JMenuItem mniTimKiemVanPhongPham = new JMenuItem("TÌM KIẾM VĂN PHÒNG PHẨM");
//		mniTimKiemVanPhongPham.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/search.png")));
//		mniTimKiemVanPhongPham.setFont(new Font("Arial", Font.BOLD, 12));
//		mnVanPhongPham.add(mniTimKiemVanPhongPham);
		
		JMenu mnNhaCungCap = new JMenu("NHÀ CUNG CẤP");
		mnNhaCungCap.setFont(new Font("Arial", Font.BOLD, 15));
		mnNhaCungCap.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/vendor.png")));
		menuBar.add(mnNhaCungCap);
		mnNhaCungCap.setPreferredSize(new Dimension(200, 43));
		JMenuItem mniCapNhatNhaCungCap = new JMenuItem("CẬP NHẬT NHÀ CUNG CẤP");
		mniCapNhatNhaCungCap.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/update32.png")));
		mniCapNhatNhaCungCap.setFont(new Font("Arial", Font.BOLD, 12));
		mnNhaCungCap.add(mniCapNhatNhaCungCap);
		
		JMenu mnThongKe = new JMenu("THỐNG KÊ");
		mnThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		mnThongKe.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/stock.png")));
		menuBar.add(mnThongKe);
		mnThongKe.setPreferredSize(new Dimension(150, 43));
		JMenuItem mniThongKeSanPhamDaBan = new JMenuItem("THỐNG KÊ SẢN PHẨM ĐÃ BÁN");
		mniThongKeSanPhamDaBan.setFont(new Font("Arial", Font.BOLD, 12));
		mniThongKeSanPhamDaBan.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/add_item.png")));
		mnThongKe.add(mniThongKeSanPhamDaBan);
		
		JMenuItem mniThongKeDoanhThu = new JMenuItem("THỐNG KÊ DOANH THU");
		mniThongKeDoanhThu.setFont(new Font("Arial", Font.BOLD, 12));
		mniThongKeDoanhThu.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/icons8-stock-32.png")));
		mnThongKe.add(mniThongKeDoanhThu);
		
		JMenuItem mniThongKeSanPhamSapHet = new JMenuItem("THỐNG KÊ SẢN PHẨM SẮP HẾT");
		mniThongKeSanPhamSapHet.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/icons8-item-32.png")));
		mniThongKeSanPhamSapHet.setFont(new Font("Arial", Font.BOLD, 12));
		mnThongKe.add(mniThongKeSanPhamSapHet);
		
		JMenu mnTaiKhoan = new JMenu("TÀI KHOẢN");
		mnTaiKhoan.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/account.png")));
		mnTaiKhoan.setPreferredSize(new Dimension(200, 43));
		mnTaiKhoan.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnTaiKhoan);
		
		JMenuItem mniDoiMatKhau = new JMenuItem("ĐỔI MẬT KHẨU");
		mniDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						frmDoiMatKhau dmk = new frmDoiMatKhau(ma);
						dmk.setVisible(true);
						dmk.setLocationRelativeTo(null);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			
		});
		
		mniDoiMatKhau.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/icons8-change-password-32.png")));
		mniDoiMatKhau.setFont(new Font("Arial", Font.BOLD, 12));
		mnTaiKhoan.add(mniDoiMatKhau);
		
		JMenuItem mnDangXuat = new JMenuItem("ĐĂNG XUẤT");
		mnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					setVisible(false);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		mnDangXuat.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/icons8-log-out-32.png")));
		mnDangXuat.setFont(new Font("Arial", Font.BOLD, 12));
		mnTaiKhoan.add(mnDangXuat);
		
		txtThoiGian = new JTextField();
		txtThoiGian.setHorizontalAlignment(SwingConstants.CENTER);
		txtThoiGian.setFont(new Font("Arial", Font.BOLD, 28));
		txtThoiGian.setEditable(false);
		menuBar.add(txtThoiGian);
		txtThoiGian.setColumns(10);
		txtThoiGian.setText(LocalDate.now().toString());
		
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setForeground(new Color(0, 0, 0));
		txtMaNhanVien.setBackground(new Color(137, 182, 176));
		txtMaNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setFont(new Font("Arial", Font.BOLD, 25));
		txtMaNhanVien.setBounds(1199, 0, 66, 42);
		contentPane.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		
		
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        cardPanel.setBounds(0, 41, 1540, 804);
        
        
        
		
		getContentPane().add(cardPanel,BorderLayout.CENTER);
		cardLayout.show(cardPanel, "tc");
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setBackground(new Color(137, 182, 176));
		txtTenNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenNhanVien.setForeground(new Color(128, 64, 0));
		txtTenNhanVien.setEditable(false);
		txtTenNhanVien.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(1264, 0, 276, 42);
		contentPane.add(txtTenNhanVien);
		
		txtMaNhanVien.setText(ma);
		txtTenNhanVien.setText(nhanVienDao.layTenTheoMa(ma));
        
		
		mniCapNhatKhachHang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                Panel_CapNhatKhachHang panelCapNhatKhachHang = new Panel_CapNhatKhachHang();
                cardPanel.add(panelCapNhatKhachHang, "CAPNHATKHACHHANG");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "CAPNHATKHACHHANG");
			}
		});
		
		mniTimKiemKhachHang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel panelTimKiemKhachHang = new Panel_TimKiemKhachHang();
                cardPanel.add(panelTimKiemKhachHang, "TIMKIEMKHACHHANG");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "TIMKIEMKHACHHANG");
			}
		});
		
		mniCapNhatSach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel panelCapNhatSach = new Panel_CapNhatSach();
		        cardPanel.add(panelCapNhatSach, "CAPNHATSACH");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "CAPNHATSACH");
				
			}
		});	
		
//		mniTimKiemSach.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				JPanel panelTimKiemSach = new Panel_TimKiemSach();
//               cardPanel.add(panelTimKiemSach, "TIMKIEMSACH");
//		        getContentPane().add(cardPanel, BorderLayout.CENTER);
//				cardLayout.show(cardPanel, "TIMKIEMSACH");
//			}
//		});
//		
		mniCapNhatNhaCungCap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel panelCapNhatNhaCungCap = new Panel_CapNhapNhaCungCap();
                cardPanel.add(panelCapNhatNhaCungCap, "A");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "A");
			}
		});
		
//		mniCapNhatVanPhongPham.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				Panel_CapNhatVanPhongPham panelCapNhatVanPhongPham = new Panel_CapNhatVanPhongPham();
//				cardPanel.add(panelCapNhatVanPhongPham,"C");
//				getContentPane().add(cardPanel,BorderLayout.CENTER);
//				cardLayout.show(cardPanel, "C");
//			}
//		});
//		
//		mniTimKiemVanPhongPham.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				Panel_TimKiemVanPhongPham panelTimKiemVPP = new Panel_TimKiemVanPhongPham();
//				cardPanel.add(panelTimKiemVPP, "D");
//				getContentPane().add(cardPanel,BorderLayout.CENTER);
//				cardLayout.show(cardPanel, "D");
//			}
//		});
//		

	mniThongKeDoanhThu.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Panel_ThongKeDoanhThu panelThongKeDoanhThu = new Panel_ThongKeDoanhThu();
			cardPanel.add(panelThongKeDoanhThu, "G");
			getContentPane().add(cardPanel,BorderLayout.CENTER);
			cardLayout.show(cardPanel, "G");
			
		}
	});
	
	mniThongKeSanPhamDaBan.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Panel_ThongKeSanPhamDaBan panelThongKeSanPhamDaBan = new Panel_ThongKeSanPhamDaBan();
			cardPanel.add(panelThongKeSanPhamDaBan, "H");
			getContentPane().add(cardPanel,BorderLayout.CENTER);
			cardLayout.show(cardPanel, "H");
			
		}
	});
	
	mniTaoKhuyenMai.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Panel_KhuyenMai panelKhuyenMai = new Panel_KhuyenMai();
			cardPanel.add(panelKhuyenMai, "I");
			getContentPane().add(cardPanel,BorderLayout.CENTER);
			cardLayout.show(cardPanel, "I");
			
		}
	});
	
	mniCapNhatNhanVien.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        Panel_CapNhatNhanVien panelCapNhatNhanVien = new Panel_CapNhatNhanVien();
        cardPanel.add(panelCapNhatNhanVien, "CAPNHATKHACHHANG");
        getContentPane().add(cardPanel, BorderLayout.CENTER);
		cardLayout.show(cardPanel, "CAPNHATKHACHHANG");
	}
});

	mniTimKiemNhanVien.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel panelTimKiemNhanVien = new Panel_TimKiemNhanVien();
        cardPanel.add(panelTimKiemNhanVien, "TIMKIEMNHANVIEN");
        getContentPane().add(cardPanel, BorderLayout.CENTER);
		cardLayout.show(cardPanel, "TIMKIEMNHANVIEN");
	}
});
	
	mniTaoPhieuNhapHang.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Panel_TaoPhieuNhapHang panelTaoPhieuNhapHang = new Panel_TaoPhieuNhapHang(ma);
		cardPanel.add(panelTaoPhieuNhapHang, "E");
		getContentPane().add(cardPanel,BorderLayout.CENTER);
		cardLayout.show(cardPanel, "E");
		
	}
});
	mniTimKiemPhieuNhapHang.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Panel_TimKiemPhieuNhapHang panelTimKiemPhieuNhapHang = new Panel_TimKiemPhieuNhapHang();
		cardPanel.add(panelTimKiemPhieuNhapHang, "F");
		getContentPane().add(cardPanel,BorderLayout.CENTER);
		cardLayout.show(cardPanel, "F");
		
	}
	});
	
	mniThongKeSanPhamSapHet.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Panel_ThongKeSanPhamSapHet panel_ThongKeSanPhamSapHet = new Panel_ThongKeSanPhamSapHet();
			cardPanel.add(panel_ThongKeSanPhamSapHet, "zzz");
			getContentPane().add(cardPanel,BorderLayout.CENTER);
			cardLayout.show(cardPanel, "zzz");
			
		}
		});
	
	mniTimKiemHoaDon.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JPanel panelTimKiemHoaDon = new Panel_TimKiemHoaDon();
	        cardPanel.add(panelTimKiemHoaDon, "TIMKIEMHOADON");
	        getContentPane().add(cardPanel, BorderLayout.CENTER);
			cardLayout.show(cardPanel, "TIMKIEMHOADON");
			
		}
	});	
	
	mniTimKiemPhieuDatHang.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JPanel panelTimKiemPhieuDatHang = new Panel_TimKiemPhieuDatHang();
            cardPanel.add(panelTimKiemPhieuDatHang, "TIMKIEMPHIEUDATHANG");
	        getContentPane().add(cardPanel, BorderLayout.CENTER);
			cardLayout.show(cardPanel, "TIMKIEMPHIEUDATHANG");
		}
	});

	mniCapNhatSach.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Panel_CapNhatSach panelCapNhatSach = new Panel_CapNhatSach();
			  cardPanel.add(panelCapNhatSach, "CapNhatSach");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "CapNhatSach");
		}
	});
	
	mniTimkiemSach.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Panel_TimKiemSach panelTimKiemSach = new Panel_TimKiemSach();
			  cardPanel.add(panelTimKiemSach, "TimKiemSach");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "TimKiemSach");
		}
	});

	mniCapNhatVpp.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Panel_CapNhatVanPhongPham panelCapNhatVpp = new Panel_CapNhatVanPhongPham();
			  cardPanel.add(panelCapNhatVpp, "CapNhatVpp");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "CapNhatVpp");
		}
	});
	
	
	
	mniTimkiemVpp.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Panel_TimKiemVanPhongPham panelTimKiemVpp = new Panel_TimKiemVanPhongPham();
			  cardPanel.add(panelTimKiemVpp, "TimKiemVpp");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "TimKiemVpp");
		}
	});
	Panel_AnhTrangChu panelAnhTrangChu = new Panel_AnhTrangChu();
    cardPanel.add(panelAnhTrangChu, "ANHTRANGCHU");
    getContentPane().add(cardPanel, BorderLayout.CENTER);
	cardLayout.show(cardPanel, "ANHTRANGCHU");
	}
}
