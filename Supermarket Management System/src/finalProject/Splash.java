package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Splash extends JFrame {

	private JPanel contentPane;
	JProgressBar progressBar;
	JLabel percentageLbl;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Splash splash = new Splash();
		splash.setVisible(true);
		
		try {
			for(int i=0; i<=100; i++) {
				splash.progressBar.setValue(i);
				Thread.sleep(50);
				splash.percentageLbl.setText(Integer.toString(i)+ "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Login().setVisible(true);
		splash.dispose();
	}
	
	
	
	public Splash() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 284);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 140, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imgLbl = new JLabel("");
		Image icon = new ImageIcon(this.getClass().getResource("/signPost.jpg")).getImage();
		imgLbl.setIcon(new ImageIcon(icon));
		imgLbl.setBounds(178, 67, 202, 161);
		contentPane.add(imgLbl);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(44, 62, 80));
		progressBar.setForeground(Color.WHITE);
		progressBar.setBounds(0, 270, 547, 14);
		contentPane.add(progressBar);
		
		percentageLbl = new JLabel("%");
		percentageLbl.setForeground(Color.WHITE);
		percentageLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		percentageLbl.setBounds(245, 236, 63, 23);
		contentPane.add(percentageLbl);
		
		lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(513, 11, 24, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("DAVINA SUPERMARKET");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(44, 62, 80));
		lblNewLabel_1.setBounds(167, 13, 236, 43);
		contentPane.add(lblNewLabel_1);
	}
}
