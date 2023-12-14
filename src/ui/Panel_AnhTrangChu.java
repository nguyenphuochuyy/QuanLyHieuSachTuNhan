package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Panel_AnhTrangChu extends JPanel {

	/**
	 * Create the panel.
	 */
	public Panel_AnhTrangChu() {
		setBounds(0, 41, 1540, 970);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 57, 1550, 809);
		lblNewLabel.setIcon(new ImageIcon(Panel_AnhTrangChu.class.getResource("/img/ui/trangchu.png")));
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 128));
		panel.setBounds(0, 0, 1550, 58);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("NHÓM 15");
		lblNewLabel_2.setBounds(10, 10, 141, 32);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(Panel_AnhTrangChu.class.getResource("/img/ui/team.png")));
		lblNewLabel_2.setForeground(new Color(255, 0, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ HIỆU SÁCH TƯ NHÂN");
		lblNewLabel_1.setBounds(599, 0, 557, 58);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
	}

}
