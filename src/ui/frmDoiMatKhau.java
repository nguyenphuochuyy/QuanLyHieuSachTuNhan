package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;
import dao.NhanVien_Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class frmDoiMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField matKhauCu;
	private JPasswordField matKhauMoi;
	private JPasswordField xacNhanMatKhauMoi;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frmDoiMatKhau frame = new frmDoiMatKhau();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	NhanVien_Dao nvDao = new NhanVien_Dao();

	/**
	 * Create the frame.
	 */
	public frmDoiMatKhau(String ma) {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBounds(100, 100, 383, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Đổi mật khẩu");
		
		JLabel lblNewLabel = new JLabel("Đổi Mật Khẩu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(36, 10, 296, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu hiện tại");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 77, 176, 34);
		contentPane.add(lblNewLabel_1);
		
		matKhauCu = new JPasswordField();
		matKhauCu.setBounds(10, 121, 322, 47);
		contentPane.add(matKhauCu);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu mới");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 196, 176, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Xác nhận mật khẩu mới");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 319, 252, 34);
		contentPane.add(lblNewLabel_1_2);
		
		matKhauMoi = new JPasswordField();
		matKhauMoi.setBounds(10, 245, 322, 47);
		contentPane.add(matKhauMoi);
		
		xacNhanMatKhauMoi = new JPasswordField();
		xacNhanMatKhauMoi.setBounds(10, 377, 322, 47);
		contentPane.add(xacNhanMatKhauMoi);
		
		JButton btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDoiMatKhau.setBounds(192, 455, 140, 34);
		contentPane.add(btnDoiMatKhau);
		
		JCheckBox showMatKhauCu = new JCheckBox("");
		showMatKhauCu.setBounds(338, 134, 26, 21);
		contentPane.add(showMatKhauCu);
		
		showMatKhauCu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox = (JCheckBox) e.getSource();
				matKhauCu.setEchoChar(checkBox.isSelected() ? '\u0000' : '*');
			}
		});
		
		
		JCheckBox showMatKhauMoi = new JCheckBox("");
		showMatKhauMoi.setBounds(338, 258, 26, 21);
		contentPane.add(showMatKhauMoi);
		
		showMatKhauMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox = (JCheckBox) e.getSource();
				matKhauMoi.setEchoChar(checkBox.isSelected() ? '\u0000' : '*');
			}
		});
		
		JCheckBox showXacNhanMatKhauMoi = new JCheckBox("");
		showXacNhanMatKhauMoi.setBounds(338, 390, 26, 21);
		contentPane.add(showXacNhanMatKhauMoi);
		
		showXacNhanMatKhauMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox = (JCheckBox) e.getSource();
				xacNhanMatKhauMoi.setEchoChar(checkBox.isSelected() ? '\u0000' : '*');
			}
		});
		
		btnDoiMatKhau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String strMatKhauCu = matKhauCu.getText();
				String strMatKhauMoi = matKhauMoi.getText();
				String strXacNhanMatKhauMoi = xacNhanMatKhauMoi.getText();
				if(strMatKhauCu.equalsIgnoreCase("") || strMatKhauMoi.equalsIgnoreCase("") || strXacNhanMatKhauMoi.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
				else {
					if(!strMatKhauMoi.equalsIgnoreCase(strXacNhanMatKhauMoi))
						JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu mới không chính xác");
					else {
						if(kiemTra()) {
							if(nvDao.doiMatKhau(ma, strMatKhauCu, strMatKhauMoi)) {
								JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
								setVisible(false);
								TrangChuNhanVien tcnv = new TrangChuNhanVien(ma);
								TrangChuQuanLy tcql = new TrangChuQuanLy(ma);
								tcnv.dispose();
								tcql.dispose();
							}
						}
					}
				}
			}
		});
		
//		public void doiMatKhau(String mkcu , String mkmoi , String xacnhanmk) {
//			
//		}
	}
	
	public boolean kiemTra() {
		String matKhau = matKhauMoi.getText();
		if(!(matKhau.matches("^.{8,}$"))) {
			JOptionPane.showMessageDialog(this,"Mật khẩu phải từ 8 kí tự trở lên");
            return false;
		}
		return true;
	}
}
