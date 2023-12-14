package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;
import entity.KhachHang;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenDangNhap;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setBounds(363, 6, 192, 80);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setAlignmentX(0.5f);
		
		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setFont(new Font("Arial", Font.BOLD, 15));
		txtTenDangNhap.setBounds(336, 125, 231, 35);
		contentPane.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(334, 96, 154, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setBounds(336, 180, 119, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnDangNhap = new JButton("ĐĂNG NHẬP");
		
		btnDangNhap.setBackground(new Color(64, 128, 128));
		btnDangNhap.setForeground(new Color(255, 255, 255));
		btnDangNhap.setIcon(new ImageIcon(DangNhap.class.getResource("/img/ui/login.png")));
		btnDangNhap.setFont(new Font("Arial", Font.BOLD, 15));
		btnDangNhap.setBounds(350, 277, 175, 54);
		contentPane.add(btnDangNhap);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(6, 6, 286, 360);
		
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setIcon(new ImageIcon(DangNhap.class.getResource("/img/ui/istockphoto-1326048762-612x612.jpg")));
		lblNewLabel_2.setBounds(6, 6, 274, 354);
		panel.add(lblNewLabel_2);
		
		password = new JPasswordField();
		password.setFont(new Font("Arial", Font.PLAIN, 15));
		password.setBounds(336, 207, 231, 35);
		contentPane.add(password);
		
		JCheckBox showMatKhau = new JCheckBox("Hiển thị mật khẩu");
		showMatKhau.setFont(new Font("Arial", Font.PLAIN, 13));
		showMatKhau.setBounds(435, 248, 132, 21);
		contentPane.add(showMatKhau);
		
		btnDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtTenDangNhap.getText().equals("") || password.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tài khoản !");
				}
				else 
					dangNhap();
			}
		});
		
		showMatKhau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox = (JCheckBox) e.getSource();
				password.setEchoChar(checkBox.isSelected() ? '\u0000' : '*');
			}
		});
	}
	
	public Boolean dangNhap() { 
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			//String sql = "select MaNhanVien,TaiKhoan,MatKhau,TrangThai,ChucVu from NhanVien";
			String sql = "select MaNhanVien,HoTenNhanVien , TaiKhoan , MatKhau , TrangThai , ChucVu  from NhanVien";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				if(txtTenDangNhap.getText().equalsIgnoreCase(rs.getString(3)) && password.getText().equalsIgnoreCase(rs.getString(4)) && rs.getInt(5) == 0 && rs.getInt(6) == 0)  {
					TrangChuNhanVien tcnv = new TrangChuNhanVien(rs.getString(1));	
					tcnv.setVisible(true);
					tcnv.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					setVisible(false);
					return true;
				}
				else if(txtTenDangNhap.getText().equalsIgnoreCase(rs.getString(3)) && password.getText().equalsIgnoreCase(rs.getString(4)) && rs.getInt(5) == 0 && rs.getInt(6) == 1) {
					TrangChuQuanLy tcql = new TrangChuQuanLy(rs.getString(1));
					tcql.setVisible(true);
					tcql.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					setVisible(false);
					return true;
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Thông tin tài khoản hoặc mật khẩu không đúng");
		return false;
	}
}
