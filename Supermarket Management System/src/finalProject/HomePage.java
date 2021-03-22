package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 509);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/super (3).jpg")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel img = new JLabel("");
		img.setBounds(0, 0, 772, 460);
		Image icon = new ImageIcon(this.getClass().getResource("/supermarket.jpg")).getImage();
		img.setIcon(new ImageIcon(icon));
		panel.add(img);
		
		JButton proLbl = new JButton("PRODUCTS");
		proLbl.setToolTipText("Click to manage products");
		proLbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ManageProduct().setVisible(true);
				dispose();
			}
		});
		proLbl.setBackground(new Color(44, 62, 80));
		proLbl.setForeground(new Color(255, 140, 0));
		proLbl.setFont(new Font("Century Gothic", Font.BOLD, 20));
		proLbl.setBounds(138, 56, 154, 63);
		img.add(proLbl);
		
		JButton usersLbl = new JButton("USERS");
		usersLbl.setToolTipText("Click to manage users");
		usersLbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ManageSellers().setVisible(true);
				dispose();
			}
		});
		usersLbl.setBackground(new Color(44, 62, 80));
		usersLbl.setForeground(new Color(255, 140, 0));
		usersLbl.setFont(new Font("Century Gothic", Font.BOLD, 20));
		usersLbl.setBounds(512, 56, 154, 63);
		img.add(usersLbl);
		
		JButton adminLbl = new JButton("ADMIN");
		adminLbl.setToolTipText("Click to update Admin");
		adminLbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UpdateAdmin().setVisible(true);
				dispose();
				setEnabled(false);
			}
		});
		adminLbl.setBackground(new Color(44, 62, 80));
		adminLbl.setForeground(new Color(255, 140, 0));
		adminLbl.setFont(new Font("Century Gothic", Font.BOLD, 20));
		adminLbl.setBounds(149, 296, 154, 63);
		img.add(adminLbl);
		
		JButton catLbl = new JButton("CATEGORIES");
		catLbl.setToolTipText("Click to manage categories");
		catLbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ManageCategories().setVisible(true);
				dispose();
			}
		});
		catLbl.setBackground(new Color(44, 62, 80));
		catLbl.setForeground(new Color(255, 140, 0));
		catLbl.setFont(new Font("Century Gothic", Font.BOLD, 20));
		catLbl.setBounds(519, 294, 163, 63);
		img.add(catLbl);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 435, 86, 25);
		panel.add(label);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnLogout.setToolTipText("Click to log out");
		btnLogout.setForeground(new Color(255, 140, 0));
		btnLogout.setFont(new Font("Century Gothic", Font.BOLD, 10));
		btnLogout.setBackground(new Color(255, 0, 0));
		btnLogout.setBounds(10, 424, 74, 25);
		img.add(btnLogout);
	}
}
