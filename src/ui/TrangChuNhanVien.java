package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class TrangChuNhanVien extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelNoiDung;
	private CardLayout cardLayout;
    private JPanel cardPanel;
    private JMenu mnNhapHang;
	public  JTextField txtMaNhanVien;
	//private DangNhap dangnhap = new DangNhap() ;
	public JTextField txtTenNhanVien;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TrangChuNhanVien frame = new TrangChuNhanVien();
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
	public TrangChuNhanVien(String ma) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1405, 800);
		setBounds(0, 0, 1920, 1050);
		//setSize(1650,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 1, 950, 42);
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
		mnTrangChu.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/home.png")));
		mnTrangChu.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnTrangChu);
		mnTrangChu.setPreferredSize(new Dimension(150,43));
		JMenu mnNhanVien = new JMenu("BÁN HÀNG");
		mnNhanVien.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/sell32.png")));
		mnNhanVien.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnNhanVien);
		mnNhanVien.setPreferredSize(new Dimension(150,43));
		JMenu mnHoaDon = new JMenu("HÓA ĐƠN");
		mnHoaDon.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/bill.png")));
		mnHoaDon.setFont(new Font("Arial", Font.BOLD, 12));
		mnNhanVien.add(mnHoaDon);
		
		JMenuItem mniTaoHoaDon = new JMenuItem("TẠO HÓA ĐƠN");
		mniTaoHoaDon.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/create30.png")));
		mniTaoHoaDon.setFont(new Font("Arial", Font.BOLD, 10));
		mnHoaDon.add(mniTaoHoaDon);
		
		
		JMenuItem mniTimKiemHoaDon = new JMenuItem("TÌM KIẾM HÓA ĐƠN");
		mniTimKiemHoaDon.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/search30.png")));
		mniTimKiemHoaDon.setFont(new Font("Arial", Font.BOLD, 10));
		mnHoaDon.add(mniTimKiemHoaDon);
		
		JMenu mnPhieuDatHang = new JMenu("PHIẾU ĐẶT HÀNG");
		mnPhieuDatHang.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/order.png")));
		mnPhieuDatHang.setFont(new Font("Arial", Font.BOLD, 12));
		mnNhanVien.add(mnPhieuDatHang);
		
		JMenuItem mniTaoPhieuDatHang = new JMenuItem("TẠO PHIẾU ĐẶT HÀNG");
		mniTaoPhieuDatHang.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/create30.png")));
		mniTaoPhieuDatHang.setFont(new Font("Arial", Font.BOLD, 10));
		mnPhieuDatHang.add(mniTaoPhieuDatHang);
		
		JMenuItem mniTimKiemPhieuDatHang = new JMenuItem("TÌM KIẾM PHIẾU ĐẶT HÀNG");
		mniTimKiemPhieuDatHang.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/search30.png")));
		mniTimKiemPhieuDatHang.setFont(new Font("Arial", Font.BOLD, 10));
		mnPhieuDatHang.add(mniTimKiemPhieuDatHang);
		
		JMenu mnKhuyenMai = new JMenu("KHUYẾN MÃI");
		mnKhuyenMai.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/percent.png")));
		mnNhanVien.add(mnKhuyenMai);
		mnKhuyenMai.setFont(new Font("Arial", Font.BOLD, 12));
		
		JMenuItem mniTaoKhuyenMai = new JMenuItem("TẠO KHUYẾN MÃI");
		mniTaoKhuyenMai.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/create30.png")));
		mniTaoKhuyenMai.setFont(new Font("Arial", Font.BOLD, 10));
		mnKhuyenMai.add(mniTaoKhuyenMai);
		
		JMenu mnKhachHang = new JMenu("KHÁCH HÀNG");
		mnKhachHang.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/customer.png")));
		mnKhachHang.setFont(new Font("Arial", Font.BOLD, 12));
		mnNhanVien.add(mnKhachHang);
		
		JMenuItem mniCapNhatKhachHang = new JMenuItem("CẬP NHẬT KHÁCH HÀNG");
		mniCapNhatKhachHang.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/update32.png")));
		mnKhachHang.add(mniCapNhatKhachHang);
		mniCapNhatKhachHang.setFont(new Font("Arial", Font.BOLD, 12));
		
		JMenuItem mniTimKiemKhachHang = new JMenuItem("TÌM KIẾM KHÁCH");
		mniTimKiemKhachHang.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/search.png")));
		mnKhachHang.add(mniTimKiemKhachHang);
		mniTimKiemKhachHang.setFont(new Font("Arial", Font.BOLD, 12));
		
		JMenu mnPhieuNhapHang = new JMenu("PHIẾU NHẬP HÀNG");
		mnPhieuNhapHang.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/bill.png")));
		mnPhieuNhapHang.setFont(new Font("Arial", Font.BOLD, 12));
		mnNhanVien.add(mnPhieuNhapHang);
		
		
		JMenuItem mniTimKiemPhieuNhapHang = new JMenuItem("TÌM KIẾM PHIẾU NHẬP HÀNG");
		mniTimKiemPhieuNhapHang.setIcon(new ImageIcon(TrangChuQuanLy.class.getResource("/img/ui/search30.png")));
		mniTimKiemPhieuNhapHang.setFont(new Font("Arial", Font.BOLD, 10));
		mnPhieuNhapHang.add(mniTimKiemPhieuNhapHang);
		
		JMenu mnSnPhm = new JMenu("SẢN PHẨM");
		mnSnPhm.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/icons8-item-32.png")));
		mnSnPhm.setPreferredSize(new Dimension(150, 43));
		mnSnPhm.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnSnPhm);
		
		JMenu mnSach = new JMenu("SÁCH");
		mnSach.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/book.png")));
		mnSach.setPreferredSize(new Dimension(150, 43));
		mnSach.setFont(new Font("Arial", Font.BOLD, 12));
		mnSnPhm.add(mnSach);
		
		JMenuItem mniCapNhatSach = new JMenuItem("CẬP NHẬT SÁCH");
		
		mniCapNhatSach.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/update32.png")));
		mniCapNhatSach.setFont(new Font("Arial", Font.BOLD, 10));
		mnSach.add(mniCapNhatSach);
		
		JMenuItem mniTimKiemSach = new JMenuItem("TÌM KIẾM SÁCH");
		mniTimKiemSach.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/search25.png")));
		mniTimKiemSach.setFont(new Font("Arial", Font.BOLD, 10));
		mnSach.add(mniTimKiemSach);
		
		JMenu mnVanPhongPham = new JMenu("VĂN PHÒNG PHẨM");
		mnVanPhongPham.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/ruler.png")));
		mnVanPhongPham.setPreferredSize(new Dimension(200, 43));
		mnVanPhongPham.setFont(new Font("Arial", Font.BOLD, 12));
		mnSnPhm.add(mnVanPhongPham);
		
		JMenuItem mniCapNhatVanPhongPham = new JMenuItem("CẬP NHẬT VĂN PHÒNG PHẨM");
		mniCapNhatVanPhongPham.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/update32.png")));
		mniCapNhatVanPhongPham.setFont(new Font("Arial", Font.BOLD, 10));
		mnVanPhongPham.add(mniCapNhatVanPhongPham);
		
		JMenuItem mniTimKiemVanPhongPham = new JMenuItem("TÌM KIẾM VĂN PHÒNG PHẨM");
		mniTimKiemVanPhongPham.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/search25.png")));
		mniTimKiemVanPhongPham.setFont(new Font("Arial", Font.BOLD, 10));
		mnVanPhongPham.add(mniTimKiemVanPhongPham);
		JMenu mnNhaCungCap = new JMenu("NHÀ CUNG CẤP");
		mnNhaCungCap.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/vendor.png")));
		mnNhaCungCap.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnNhaCungCap);
		mnNhaCungCap.setPreferredSize(new Dimension(200,43));
		JMenuItem mniCapNhatNhaCungCap = new JMenuItem("CẬP NHẬT NHÀ CUNG CẤP");
		mniCapNhatNhaCungCap.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/update32.png")));
		mnNhaCungCap.add(mniCapNhatNhaCungCap);
		mniCapNhatNhaCungCap.setFont(new Font("Arial", Font.BOLD, 12));
		JMenu mnThongKe = new JMenu("THỐNG KÊ");
		mnThongKe.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/stock.png")));
		mnThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnThongKe);
		mnThongKe.setPreferredSize(new Dimension(150,43));
		JMenuItem mniThongKeSanPhamDaBan = new JMenuItem("THỐNG KÊ SẢN PHẨM ĐÃ BÁN");
		mniThongKeSanPhamDaBan.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/icons8-add-item-30.png")));
		mnThongKe.add(mniThongKeSanPhamDaBan);
		mniThongKeSanPhamDaBan.setFont(new Font("Arial", Font.BOLD, 12));
		
		JMenuItem mniThongKeDoanhThu = new JMenuItem("THỐNG KÊ DOANH THU");
		mniThongKeDoanhThu.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/icons8-stock-32.png")));
		mniThongKeDoanhThu.setFont(new Font("Arial", Font.BOLD, 12));
		mnThongKe.add(mniThongKeDoanhThu);
		
		JMenuItem mniThongKeSanPhamSapHet = new JMenuItem("THỐNG KÊ SẢN PHẨM SẮP HẾT");
		mniThongKeSanPhamSapHet.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/icons8-item-32.png")));
		mniThongKeSanPhamSapHet.setFont(new Font("Arial", Font.BOLD, 12));
		mnThongKe.add(mniThongKeSanPhamSapHet);
		
		JMenu mnTaiKhoan = new JMenu("TÀI KHOẢN");
		mnTaiKhoan.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/account.png")));
		mnTaiKhoan.setFont(new Font("Arial", Font.BOLD, 15));
		mnTaiKhoan.setPreferredSize(new Dimension(150, 43));
		menuBar.add(mnTaiKhoan);
		
		JMenuItem mniDoiMatKhau = new JMenuItem("ĐỔI MẬT KHẨU");
		mniDoiMatKhau.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/icons8-change-password-32.png")));
		mniDoiMatKhau.setFont(new Font("Arial", Font.BOLD, 12));
		mnTaiKhoan.add(mniDoiMatKhau);
		
		JMenuItem mniDangXuat = new JMenuItem("ĐĂNG XUẤT");	
		mniDangXuat.setIcon(new ImageIcon(TrangChuNhanVien.class.getResource("/img/ui/icons8-log-out-32.png")));
		mniDangXuat.setFont(new Font("Arial", Font.BOLD, 12));
		mnTaiKhoan.add(mniDangXuat);
		
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBackground(new Color(64, 128, 128));
		txtMaNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setFont(new Font("Arial", Font.BOLD, 25));
		txtMaNhanVien.setBounds(1199, 1, 68, 42);
		contentPane.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setBackground(new Color(64, 128, 128));
		txtTenNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenNhanVien.setForeground(new Color(128, 64, 0));
		txtTenNhanVien.setEditable(false);
		txtTenNhanVien.setFont(new Font("Arial", Font.BOLD, 25));
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(1261, 1, 279, 41);
		contentPane.add(txtTenNhanVien);
		
		
		txtMaNhanVien.setText(ma);
		txtTenNhanVien.setText(nhanVienDao.layTenTheoMa(ma));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        cardPanel.setBounds(0, 41, 1540, 970);
        
        
        
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(1175, 626, 231, 65);
		getContentPane().add(cardPanel,BorderLayout.CENTER);
		cardLayout.show(cardPanel, "tc");
		
		mniDangXuat.addActionListener(new ActionListener() {
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
		
		mniDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDoiMatKhau doiMatKhau = new frmDoiMatKhau(ma);
			    doiMatKhau.setVisible(true);
			    doiMatKhau.setLocationRelativeTo(null);
			    
			}
		});
		
		
	
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
		

		mniTaoHoaDon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel panelTaoHoaDon = new Panel_TaoHoaDon(ma);
		        cardPanel.add(panelTaoHoaDon, "TAOHOADON");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "TAOHOADON");
				
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
		
		mniTaoPhieuDatHang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                Panel_TaoPhieuDatHang panelTaoPhieuDatHang = new Panel_TaoPhieuDatHang(ma);
                cardPanel.add(panelTaoPhieuDatHang, "CAPNHATKHACHHANG");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "CAPNHATKHACHHANG");
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
		
		mniTimKiemPhieuDatHang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel panelTimKiemPhieuDatHang = new Panel_TimKiemPhieuDatHang();
                cardPanel.add(panelTimKiemPhieuDatHang, "B");
		        getContentPane().add(cardPanel, BorderLayout.CENTER);
				cardLayout.show(cardPanel, "B");
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
	mniCapNhatSach.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Panel_CapNhatSach panel_CapNhatSach = new Panel_CapNhatSach();
			cardPanel.add(panel_CapNhatSach, "capnhatsach");
			getContentPane().add(cardPanel,BorderLayout.CENTER);
			cardLayout.show(cardPanel, "capnhatsach");
		}
	});
	
	mniTimKiemSach.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Panel_TimKiemSach panel_TimKiemSach = new Panel_TimKiemSach();
			cardPanel.add(panel_TimKiemSach, "timkiemsach");
			getContentPane().add(cardPanel,BorderLayout.CENTER);
			cardLayout.show(cardPanel, "timkiemsach");
		}
	});
	mniCapNhatVanPhongPham.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Panel_CapNhatVanPhongPham panel_CapNhatVpp = new Panel_CapNhatVanPhongPham();
			cardPanel.add(panel_CapNhatVpp, "capnhatvpp");
			getContentPane().add(cardPanel,BorderLayout.CENTER);
			cardLayout.show(cardPanel, "capnhatvpp");
		}
	});
	mniTimKiemVanPhongPham.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Panel_TimKiemVanPhongPham panel_TimKiemVpp = new Panel_TimKiemVanPhongPham();
			cardPanel.add(panel_TimKiemVpp, "timkiemvpp");
			getContentPane().add(cardPanel,BorderLayout.CENTER);
			cardLayout.show(cardPanel, "timkiemvpp");
		}
	});
	
	
	
	Panel_AnhTrangChu panelAnhTrangChu = new Panel_AnhTrangChu();
    cardPanel.add(panelAnhTrangChu, "ANHTRANGCHU");
    getContentPane().add(cardPanel, BorderLayout.CENTER);
	cardLayout.show(cardPanel, "ANHTRANGCHU");
	
	txtThoiGian = new JTextField();
	txtThoiGian.setHorizontalAlignment(SwingConstants.CENTER);
	txtThoiGian.setFont(new Font("Dialog", Font.BOLD, 28));
	txtThoiGian.setEditable(false);
	txtThoiGian.setBounds(949, 1, 250, 42);
	contentPane.add(txtThoiGian);
	txtThoiGian.setColumns(10);
	txtThoiGian.setText(LocalDate.now().toString());
	}
}
