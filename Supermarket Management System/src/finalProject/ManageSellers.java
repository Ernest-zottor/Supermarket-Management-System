package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

public class ManageSellers extends JFrame {

	private JPanel contentPane;
	private JTextField selId;
	private JTextField selPass;
	private JTextField selName;
	private JComboBox genderCombo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageSellers frame = new ManageSellers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTable sellerTb;
	

	public void refreshTable() {
		try {
			String query = "SELECT * from SellerTb";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			sellerTb.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();

		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void clearTextField() {
		selId.setText("");
		selName.setText("");
		selPass.setText("");
		genderCombo.setSelectedItem("");
	}

	
	public ManageSellers() {
		conn = SqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/super (3).jpg")));

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel leftfTopPanel = new JPanel();
		leftfTopPanel.setBackground(new Color(44, 62, 80));
		leftPanel.add(leftfTopPanel);
		GridBagLayout gbl_leftfTopPanel = new GridBagLayout();
		gbl_leftfTopPanel.columnWidths = new int[]{0, 0, 0};
		gbl_leftfTopPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_leftfTopPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_leftfTopPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		leftfTopPanel.setLayout(gbl_leftfTopPanel);
		
		JLabel homeLbl = new JLabel("  HOME");
		homeLbl.setToolTipText("Click to manage products");
		homeLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new HomePage().setVisible(true);
				dispose();
			}
		});
		homeLbl.setForeground(new Color(255, 140, 0));
		homeLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
		gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6_1.gridx = 0;
		gbc_lblNewLabel_6_1.gridy = 0;
		leftfTopPanel.add(homeLbl, gbc_lblNewLabel_6_1);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(248, 148, 6));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel titleLbl = new JLabel("MANAGE SELLERS");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(new Font("Century Gothic", Font.BOLD, 20));
		topPanel.add(titleLbl);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		JLabel logLbl = new JLabel("LOGOUT");
		logLbl.setToolTipText("Click to logout");
		logLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		logLbl.setForeground(new Color(255, 140, 0));
		logLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 974, Short.MAX_VALUE)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(logLbl)
					.addContainerGap(889, Short.MAX_VALUE))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 33, Short.MAX_VALUE)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addComponent(logLbl)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		bottomPanel.setLayout(gl_bottomPanel);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(null);
		CenterPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(CenterPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(83, 39, 51, 19);
		CenterPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(83, 76, 51, 19);
		CenterPanel.add(lblNewLabel_1_1);
		
		selId = new JTextField();
		selId.setForeground(Color.BLACK);
		selId.setFont(new Font("Century Gothic", Font.BOLD, 15));
		selId.setColumns(10);
		selId.setBackground(Color.WHITE);
		selId.setBounds(192, 32, 171, 29);
		CenterPanel.add(selId);
		
		JLabel passLbl = new JLabel("PASSWORD");
		passLbl.setForeground(Color.WHITE);
		passLbl.setFont(new Font("Century Gothic", Font.BOLD, 15));
		passLbl.setBounds(513, 39, 84, 19);
		CenterPanel.add(passLbl);
		
		JLabel genLbl = new JLabel("GENDER");
		genLbl.setForeground(Color.WHITE);
		genLbl.setFont(new Font("Century Gothic", Font.BOLD, 15));
		genLbl.setBounds(513, 76, 74, 19);
		CenterPanel.add(genLbl);
		
		selPass = new JTextField();
		selPass.setForeground(Color.BLACK);
		selPass.setFont(new Font("Century Gothic", Font.BOLD, 15));
		selPass.setColumns(10);
		selPass.setBackground(Color.WHITE);
		selPass.setBounds(617, 32, 171, 29);
		CenterPanel.add(selPass);
		
		JButton addBtn = new JButton("ADD");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selId.getText().isEmpty()|| selName.getText().isEmpty()|| selPass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						String query = "INSERT into sellertb (SellerID, SellerName, SellerPassword, SellerGender) VALUES (?,?,?,?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setInt(1, Integer.valueOf(selId.getText()));
						pst.setString(2, selName.getText());
						pst.setString(3, selPass.getText());
						pst.setString(4, genderCombo.getSelectedItem().toString());

						pst.execute();

						JOptionPane.showMessageDialog(null, "Seller Added");
						refreshTable();
						clearTextField();
						
						pst.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		});
		addBtn.setForeground(Color.WHITE);
		addBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		addBtn.setBackground(new Color(255, 140, 0));
		addBtn.setBounds(196, 198, 89, 29);
		CenterPanel.add(addBtn);
		
		JButton editBtn = new JButton("EDIT");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selId.getText().isEmpty()|| selName.getText().isEmpty()|| selPass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						String query = "UPDATE SellerTb SET SellerID='"+selId.getText()+"', SellerName='"+selName.getText()+"',"
								+ "SellerPassword='"+selPass.getText()+"', SellerGender='"+genderCombo.getSelectedItem().toString()+"' where SellerID='"+selId.getText()+"'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Seller Updated");
						refreshTable();
						clearTextField();
						pst.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		editBtn.setForeground(Color.WHITE);
		editBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		editBtn.setBackground(new Color(255, 140, 0));
		editBtn.setBounds(376, 198, 89, 29);
		CenterPanel.add(editBtn);
		
		JButton deleteBtn = new JButton("DELETE");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter the seller to be deleted");
				}
				else {
					try {
						String query = "DELETE from Sellertb WHERE SellerId= '"+selId.getText()+"'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Seller Deleted");
						refreshTable();
						clearTextField();
						
						pst.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		deleteBtn.setBackground(new Color(255, 140, 0));
		deleteBtn.setBounds(539, 198, 89, 29);
		CenterPanel.add(deleteBtn);
		
		JButton clearBtn = new JButton("CLEAR");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearTextField();
				
			}
		});
		clearBtn.setForeground(Color.WHITE);
		clearBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		clearBtn.setBackground(new Color(255, 140, 0));
		clearBtn.setBounds(704, 198, 89, 29);
		CenterPanel.add(clearBtn);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("SELLERS LIST");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(447, 249, 150, 19);
		CenterPanel.add(lblNewLabel_1_2_1);
		
		selName = new JTextField();
		selName.setForeground(Color.BLACK);
		selName.setFont(new Font("Century Gothic", Font.BOLD, 15));
		selName.setColumns(10);
		selName.setBackground(Color.WHITE);
		selName.setBounds(192, 70, 171, 29);
		CenterPanel.add(selName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 279, 772, 236);
		CenterPanel.add(scrollPane);
		
		sellerTb = new JTable();
		sellerTb.setForeground(new Color(0, 0, 0));
		sellerTb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sellerTb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)sellerTb.getModel();
				int Myindex = sellerTb.getSelectedRow();
				selId.setText(model.getValueAt(Myindex, 0).toString());
				selName.setText(model.getValueAt(Myindex, 1).toString());
				selPass.setText(model.getValueAt(Myindex, 2).toString());
				genderCombo.setSelectedItem(model.getValueAt(Myindex,3).toString());
			}
		});
		scrollPane.setViewportView(sellerTb);
		
		genderCombo = new JComboBox();
		genderCombo.setModel(new DefaultComboBoxModel(new String[] {"Female", "Male"}));
		genderCombo.setFont(new Font("Century Gothic", Font.BOLD, 15));
		genderCombo.setForeground(new Color(0, 0, 0));
		genderCombo.setBounds(617, 73, 171, 29);
		CenterPanel.add(genderCombo);
		refreshTable();
	}
}
