package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
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

public class ManageCategories extends JFrame {

	private JPanel contentPane;
	private JTextField catId;
	private JTextField catName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCategories frame = new ManageCategories();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTable catTb;

	public void refreshTable() {
		try {
			String query = "SELECT * from Categoriestb";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			catTb.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();

		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public ManageCategories() {
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
		
		JLabel sellersLbl = new JLabel("  HOME");
		sellersLbl.setToolTipText("click to go to home page");
		sellersLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new HomePage().setVisible(true);
				dispose();
			}
		});
		sellersLbl.setForeground(new Color(255, 127, 80));
		sellersLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
		gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6_1.gridx = 0;
		gbc_lblNewLabel_6_1.gridy = 0;
		leftfTopPanel.add(sellersLbl, gbc_lblNewLabel_6_1);
		
		JLabel dummyLbl2 = new JLabel("");
		GridBagConstraints gbc_dummyLbl2 = new GridBagConstraints();
		gbc_dummyLbl2.insets = new Insets(0, 0, 5, 5);
		gbc_dummyLbl2.gridx = 0;
		gbc_dummyLbl2.gridy = 1;
		leftfTopPanel.add(dummyLbl2, gbc_dummyLbl2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setForeground(new Color(245, 245, 245));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		leftfTopPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel dummyLbl1 = new JLabel("");
		GridBagConstraints gbc_dummyLbl1 = new GridBagConstraints();
		gbc_dummyLbl1.insets = new Insets(0, 0, 5, 0);
		gbc_dummyLbl1.gridx = 1;
		gbc_dummyLbl1.gridy = 3;
		leftfTopPanel.add(dummyLbl1, gbc_dummyLbl1);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(248, 148, 6));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel manageCatLbl = new JLabel("MANAGE CATEGORIES");
		manageCatLbl.setForeground(Color.WHITE);
		manageCatLbl.setFont(new Font("Century Gothic", Font.BOLD, 20));
		topPanel.add(manageCatLbl);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(44, 62, 80));
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel logoutLbl = new JLabel("LOGOUT");
		logoutLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		logoutLbl.setToolTipText("Click to logout");
		logoutLbl.setForeground(new Color(255, 127, 80));
		logoutLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(logoutLbl)
					.addGap(479))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(logoutLbl)
		);
		panel.setLayout(gl_panel);
		
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(null);
		CenterPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(CenterPanel, BorderLayout.CENTER);
		
		JLabel idLbl = new JLabel("CATID");
		idLbl.setForeground(Color.WHITE);
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		idLbl.setBounds(83, 39, 72, 19);
		CenterPanel.add(idLbl);
		
		catId = new JTextField();
		catId.setForeground(Color.BLACK);
		catId.setFont(new Font("Century Gothic", Font.BOLD, 15));
		catId.setColumns(10);
		catId.setBackground(Color.WHITE);
		catId.setBounds(192, 32, 171, 29);
		CenterPanel.add(catId);
		
		JLabel nameLbl = new JLabel("NAME");
		nameLbl.setForeground(Color.WHITE);
		nameLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		nameLbl.setBounds(484, 39, 84, 19);
		CenterPanel.add(nameLbl);
		
		catName = new JTextField();
		catName.setForeground(Color.BLACK);
		catName.setFont(new Font("Century Gothic", Font.BOLD, 15));
		catName.setColumns(10);
		catName.setBackground(Color.WHITE);
		catName.setBounds(604, 36, 171, 29);
		CenterPanel.add(catName);
		
		JButton addBtn = new JButton("ADD");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(catId.getText().isEmpty()|| catName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						String query = "INSERT into CategoriesTb (ID, Name) VALUES (?,?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setInt(1, Integer.valueOf(catId.getText()));
						pst.setString(2, catName.getText());
						
						int row  = pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Category Added");
						refreshTable();
						catId.setText("");
						catName.setText("");
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		});
		addBtn.setForeground(Color.WHITE);
		addBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		addBtn.setBackground(new Color(255, 140, 0));
		addBtn.setBounds(159, 125, 89, 29);
		CenterPanel.add(addBtn);
		
		JButton editBtn = new JButton("EDIT");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(catId.getText().isEmpty()|| catName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						String query = "UPDATE CategoriesTb SET ID='"+catId.getText()+"', Name='"+ catName.getText()+"'"
								+ " where ID='"+catId.getText()+"'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Category Updated");
						refreshTable();
						catId.setText("");
						catName.setText("");
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		editBtn.setForeground(Color.WHITE);
		editBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		editBtn.setBackground(new Color(255, 140, 0));
		editBtn.setBounds(311, 125, 89, 29);
		CenterPanel.add(editBtn);
		
		JButton deleteBtn = new JButton("DELETE");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(catId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter the category to be deleted");
				}
				else {
					try {
						String query = "DELETE from Categoriestb WHERE Id= '"+catId.getText()+"'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Category Deleted");
						refreshTable();
						catId.setText("");
						catName.setText("");
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
		deleteBtn.setBounds(479, 125, 89, 29);
		CenterPanel.add(deleteBtn);
		
		JButton clearBtn = new JButton("CLEAR");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				catId.setText("");
				catName.setText("");
			}
		});
		clearBtn.setForeground(Color.WHITE);
		clearBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		clearBtn.setBackground(new Color(255, 140, 0));
		clearBtn.setBounds(619, 125, 89, 29);
		CenterPanel.add(clearBtn);
		
		JLabel catListLbl = new JLabel("CATEGORIES LIST");
		catListLbl.setForeground(Color.WHITE);
		catListLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		catListLbl.setBounds(311, 187, 161, 19);
		CenterPanel.add(catListLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 230, 698, 242);
		CenterPanel.add(scrollPane);
		
		catTb = new JTable();
		catTb.setForeground(new Color(0, 0, 0));
		catTb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setViewportView(catTb);
		catTb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)catTb.getModel();
				int Myindex = catTb.getSelectedRow();
				catId.setText(model.getValueAt(Myindex, 0).toString());
				catName.setText(model.getValueAt(Myindex, 1).toString());
			}
		});
		
		JLabel logOutLbl = new JLabel("LOGOUT");
		logOutLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		refreshTable();
	}
}
