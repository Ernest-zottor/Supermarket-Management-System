package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class UpdateAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField adminName;
	private JTextField adminPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAdmin frame = new UpdateAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTable adminTb;
	public void refreshTable() {
		try {
			String query = "SELECT name, password from AdminTb";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			adminTb.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();

		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public UpdateAdmin() {
		setUndecorated(true);
		conn = SqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 407);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/super (3).jpg")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(44, 62, 80));
		contentPane.add(contentPane_1, BorderLayout.CENTER);
		
		JLabel nameLbl = new JLabel("NAME");
		nameLbl.setForeground(new Color(255, 140, 0));
		nameLbl.setFont(new Font("Century Gothic", Font.BOLD, 15));
		nameLbl.setBounds(82, 89, 60, 23);
		contentPane_1.add(nameLbl);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(255, 140, 0));
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblPassword.setBounds(82, 134, 103, 23);
		contentPane_1.add(lblPassword);
		
		adminName = new JTextField();
		adminName.setFont(new Font("Century Gothic", Font.BOLD, 15));
		adminName.setColumns(10);
		adminName.setBounds(192, 89, 151, 23);
		contentPane_1.add(adminName);
		
		adminPass = new JTextField();
		adminPass.setFont(new Font("Century Gothic", Font.BOLD, 15));
		adminPass.setColumns(10);
		adminPass.setBounds(192, 136, 151, 23);
		contentPane_1.add(adminPass);
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(adminName.getText().isEmpty()|| adminPass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						String query = "UPDATE AdminTb SET Name='"+adminName.getText()+"',Password='"+adminPass.getText()+"' where ID='"+1+"'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Admin Updated");
						refreshTable();
						pst.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		
			
		});
		btnNewButton.setForeground(new Color(255, 140, 0));
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnNewButton.setBounds(173, 204, 89, 31);
		contentPane_1.add(btnNewButton);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adminName.setText("");
				adminPass.setText("");
			}
		});
		btnClear.setForeground(new Color(255, 140, 0));
		btnClear.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnClear.setBounds(282, 204, 89, 31);
		contentPane_1.add(btnClear);
		
		JLabel lblUpdateAdmin = new JLabel("UPDATE ADMIN");
		lblUpdateAdmin.setBackground(new Color(255, 140, 0));
		lblUpdateAdmin.setForeground(new Color(255, 140, 0));
		lblUpdateAdmin.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblUpdateAdmin.setBounds(192, 11, 164, 23);
		contentPane_1.add(lblUpdateAdmin);
		
		JLabel lblBack = new JLabel("BACK");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new HomePage().setVisible(true);
				dispose();
			}
		});
		lblBack.setToolTipText("Click to go back");
		lblBack.setForeground(new Color(255, 140, 0));
		lblBack.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblBack.setBounds(10, 350, 60, 23);
		contentPane_1.add(lblBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(423, 74, 284, 99);
		contentPane_1.add(scrollPane);
		
		adminTb = new JTable();
		adminTb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(adminTb);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblX.setForeground(Color.RED);
		lblX.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblX.setBounds(697, 13, 22, 18);
		contentPane_1.add(lblX);
		refreshTable();
	}
}
