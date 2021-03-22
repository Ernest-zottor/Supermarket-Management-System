package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userF;
	private JComboBox roleCombo;
	private JPasswordField passwordF;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	
	
	
	public Login() {
		conn = SqliteConnection.dbConnector();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 427);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/super (3).jpg")));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 62, 80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 148, 6));
		panel.setBounds(0, 0, 257, 427);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imgLbl = new JLabel("");
		Image icon = new ImageIcon(this.getClass().getResource("/super (3).jpg")).getImage();
		imgLbl.setIcon(new ImageIcon(icon));
		imgLbl.setBounds(27, 30, 181, 163);
		panel.add(imgLbl);
		
		JLabel lblNewLabel_1 = new JLabel("DAVINA SUPERMARKET");
		lblNewLabel_1.setForeground(new Color(44, 62, 80));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 223, 236, 43);
		panel.add(lblNewLabel_1);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblLogin.setBounds(347, 23, 101, 18);
		contentPane.add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("Select Role");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblNewLabel.setBounds(282, 128, 100, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblUserName.setBounds(282, 193, 100, 18);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblPassword.setBounds(282, 243, 92, 18);
		contentPane.add(lblPassword);
		
		userF = new JTextField();
		userF.setForeground(new Color(0, 0, 0));
		userF.setFont(new Font("Century Gothic", Font.BOLD, 15));
		userF.setColumns(10);
		userF.setBounds(398, 188, 127, 26);
		contentPane.add(userF);
		
		JComboBox roleCombo = new JComboBox();
		roleCombo.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Seller"}));
		roleCombo.setForeground(Color.BLACK);
		roleCombo.setFont(new Font("Century Gothic", Font.BOLD, 15));
		roleCombo.setBackground(Color.WHITE);
		roleCombo.setBounds(401, 126, 124, 26);
		contentPane.add(roleCombo);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblX.setForeground(Color.RED);
		lblX.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblX.setBounds(545, 11, 23, 18);
		contentPane.add(lblX);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(roleCombo.getSelectedItem().toString().equals("Seller")) {
					String query = "SELECT * from SellerTb WHERE SellerName ='"+userF.getText()+"' AND SellerPassword='"+passwordF.getText()+"'";
					try {
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(query);
						if(rs.next()) {
							new BillingPoint().setVisible(true);
							dispose();
							st.close();
							rs.close();
						}
						else {
							JOptionPane.showMessageDialog(null, "Wrong Seller ID or Password");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					}
					else {
						String query = "SELECT * from AdminTb WHERE Name ='"+userF.getText()+"' AND Password='"+passwordF.getText()+"'";
						try {
							Statement st = conn.createStatement();
							ResultSet rs = st.executeQuery(query);
							if(rs.next()) {
								new HomePage().setVisible(true);
								dispose();
								st.close();
								st.close();
							}
							else {
								JOptionPane.showMessageDialog(null, "Wrong Admin ID or Password");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				
			}
		});
		btnLogin.setForeground(new Color(255, 140, 0));
		btnLogin.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(325, 306, 100, 30);
		contentPane.add(btnLogin);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userF.setText("");
				passwordF.setText("");
			}
			
		});
		btnClear.setForeground(new Color(255, 140, 0));
		btnClear.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnClear.setBackground(Color.WHITE);
		btnClear.setBounds(448, 306, 100, 30);
		contentPane.add(btnClear);
		
		passwordF = new JPasswordField();
		passwordF.setForeground(new Color(0, 0, 0));
		passwordF.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordF.setBounds(398, 246, 124, 26);
		contentPane.add(passwordF);
	}
}
