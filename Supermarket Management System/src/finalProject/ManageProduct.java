package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class ManageProduct extends JFrame {

	private JPanel contentPane;
	private JTextField prodId;
	private JTextField prodQty;
	private JTextField prodPrice;
	private JTable prodTb;
	private JTextField prodName;
	private JComboBox catCombo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageProduct frame = new ManageProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;

	public void refreshTable() {
		try {
			String query = "SELECT * from ProductTb";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			prodTb.setModel(DbUtils.resultSetToTableModel(rs));
			//rs.close();

		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	public void getCategory() {
		try {
			String query = "SELECT * from Categoriestb";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String myCat = rs.getString("Name");
				catCombo.addItem(myCat);
			}
			rs.close();

		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}

	public ManageProduct() {
		conn = SqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/super (3).jpg")));

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		JLabel logoutLbl = new JLabel("LOGOUT");
		logoutLbl.setToolTipText("Click to logout");
		logoutLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		logoutLbl.setForeground(new Color(255, 140, 0));
		logoutLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
				gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(logoutLbl)
						.addContainerGap(889, Short.MAX_VALUE))
				);
		gl_bottomPanel.setVerticalGroup(
				gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
						.addComponent(logoutLbl)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		bottomPanel.setLayout(gl_bottomPanel);

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel leftTopPanel = new JPanel();
		leftTopPanel.setBackground(new Color(44, 62, 80));
		leftPanel.add(leftTopPanel);
		GridBagLayout gbl_leftfTopPanel = new GridBagLayout();
		gbl_leftfTopPanel.columnWidths = new int[]{0, 0, 0};
		gbl_leftfTopPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_leftfTopPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_leftfTopPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		leftTopPanel.setLayout(gbl_leftfTopPanel);

		JLabel sellerLbl = new JLabel("  HOME");
		sellerLbl.setToolTipText("Click to go to home page");
		sellerLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new HomePage().setVisible(true);
				dispose();
			}
		});
		sellerLbl.setForeground(new Color(255, 140, 0));
		sellerLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
		gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6_1.gridx = 0;
		gbc_lblNewLabel_6_1.gridy = 0;
		leftTopPanel.add(sellerLbl, gbc_lblNewLabel_6_1);

		JLabel dummyLbl2 = new JLabel("");
		GridBagConstraints gbc_dummyLbl2 = new GridBagConstraints();
		gbc_dummyLbl2.insets = new Insets(0, 0, 5, 5);
		gbc_dummyLbl2.gridx = 0;
		gbc_dummyLbl2.gridy = 1;
		leftTopPanel.add(dummyLbl2, gbc_dummyLbl2);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setForeground(new Color(245, 245, 245));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		leftTopPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JLabel dummyLbl1 = new JLabel("");
		GridBagConstraints gbc_dummyLbl1 = new GridBagConstraints();
		gbc_dummyLbl1.insets = new Insets(0, 0, 5, 0);
		gbc_dummyLbl1.gridx = 1;
		gbc_dummyLbl1.gridy = 3;
		leftTopPanel.add(dummyLbl1, gbc_dummyLbl1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);
		contentPane.add(panel_2, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("New label");
		panel_2.add(lblNewLabel_4);

		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(rightPanel, BorderLayout.EAST);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(248, 148, 6));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_10 = new JLabel("MANAGE PRODUCTS");
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setFont(new Font("Century Gothic", Font.BOLD, 20));
		topPanel.add(lblNewLabel_10);

		JPanel CenterPanel = new JPanel();
		CenterPanel.setForeground(Color.WHITE);
		CenterPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(CenterPanel, BorderLayout.CENTER);

		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(Color.WHITE);
		idLbl.setBounds(83, 39, 51, 19);
		idLbl.setFont(new Font("Century Gothic", Font.BOLD, 15));

		JLabel nameLbl = new JLabel("NAME");
		nameLbl.setForeground(Color.WHITE);
		nameLbl.setBounds(83, 76, 51, 19);
		nameLbl.setFont(new Font("Century Gothic", Font.BOLD, 15));

		JLabel categoryLbl = new JLabel("CATEGORY");
		categoryLbl.setForeground(Color.WHITE);
		categoryLbl.setBounds(83, 123, 100, 19);
		categoryLbl.setFont(new Font("Century Gothic", Font.BOLD, 15));

		catCombo = new JComboBox();
		catCombo.setFont(new Font("Century Gothic", Font.BOLD, 15));
		catCombo.setForeground(Color.BLACK);
		catCombo.setBackground(Color.WHITE);
		catCombo.setBounds(193, 123, 171, 29);

		prodId = new JTextField();
		prodId.setBackground(Color.WHITE);
		prodId.setForeground(Color.BLACK);
		prodId.setFont(new Font("Century Gothic", Font.BOLD, 15));
		prodId.setBounds(192, 32, 171, 29);
		prodId.setColumns(10);
		CenterPanel.setLayout(null);
		CenterPanel.add(idLbl);
		CenterPanel.add(nameLbl);
		CenterPanel.add(categoryLbl);
		CenterPanel.add(prodId);
		CenterPanel.add(catCombo);

		JLabel lblNewLabel_1_3 = new JLabel("QUANTITY");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(513, 39, 84, 19);
		CenterPanel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("PRICE");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(523, 76, 44, 19);
		CenterPanel.add(lblNewLabel_1_4);

		prodQty = new JTextField();
		prodQty.setForeground(Color.BLACK);
		prodQty.setFont(new Font("Century Gothic", Font.BOLD, 15));
		prodQty.setBackground(Color.WHITE);
		prodQty.setColumns(10);
		prodQty.setBounds(617, 32, 171, 29);
		CenterPanel.add(prodQty);

		prodPrice = new JTextField();
		prodPrice.setForeground(Color.BLACK);
		prodPrice.setFont(new Font("Century Gothic", Font.BOLD, 15));
		prodPrice.setBackground(Color.WHITE);
		prodPrice.setColumns(10);
		prodPrice.setBounds(617, 73, 171, 29);
		CenterPanel.add(prodPrice);

		JButton addBtn = new JButton("ADD");
		addBtn.setBackground(new Color(255, 140, 0));
		addBtn.setForeground(Color.WHITE);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(prodId.getText().isEmpty()|| prodName.getText().isEmpty()|| prodQty.getText().isEmpty()||
						prodPrice.getText().isEmpty())  {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						String query = "INSERT into ProductTb (ID, Name, Quantity, Price, Category) VALUES (?,?,?,?,?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setInt(1, Integer.valueOf(prodId.getText()));
						pst.setString(2, prodName.getText());
						pst.setInt(3, Integer.valueOf(prodQty.getText()));
						pst.setDouble(4, Double.valueOf(prodPrice.getText()));
						pst.setString(5, catCombo.getSelectedItem().toString());
						pst.execute();

						JOptionPane.showMessageDialog(null, "Product Added");
					
						prodId.setText("");
						prodName.setText("");
						prodPrice.setText("");
						prodQty.setText("");
						
						pst.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					refreshTable();
				}
			}
		});
		addBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		addBtn.setBounds(196, 198, 89, 29);
		CenterPanel.add(addBtn);

		JButton editBtn = new JButton("EDIT");
		editBtn.setBackground(new Color(255, 140, 0));
		editBtn.setForeground(Color.WHITE);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(prodId.getText().isEmpty()|| prodName.getText().isEmpty()|| prodQty.getText().isEmpty()|| prodPrice.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						String query = "UPDATE ProductTb SET ID='"+prodId.getText()+"', Name='"+ prodName.getText()+"', Quantity='"
								+prodQty.getText()+"', Price='"+prodPrice.getText()+"', Category='"+catCombo.getSelectedItem().toString()+"' where ID='"+prodId.getText()+"'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Product Updated");
						
						prodId.setText("");
						prodName.setText("");
						prodPrice.setText("");
						prodQty.setText("");
						pst.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
					refreshTable();
				}
			}
		});
		editBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		editBtn.setBounds(376, 198, 89, 29);
		CenterPanel.add(editBtn);

		JButton deleteBtn = new JButton("DELETE");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(prodId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter the product to be deleted");
				}
				else {
					try {
						String query = "DELETE from ProductTb WHERE Id= '"+ prodId.getText()+"'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Product Deleted");
						prodId.setText("");
						prodName.setText("");
						prodPrice.setText("");
						prodQty.setText("");
						
						pst.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					refreshTable();
				}
			}
		});
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setBackground(new Color(255, 140, 0));
		deleteBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		deleteBtn.setBounds(539, 198, 89, 29);
		CenterPanel.add(deleteBtn);

		JButton clearBtn = new JButton("CLEAR");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prodId.setText("");
				prodName.setText("");
				prodPrice.setText("");
				prodQty.setText("");
				
			}
		});
		clearBtn.setBackground(new Color(255, 140, 0));
		clearBtn.setForeground(Color.WHITE);
		clearBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		clearBtn.setBounds(704, 198, 89, 29);
		CenterPanel.add(clearBtn);

		JLabel prodListLbl = new JLabel("PRODUCTS LIST");
		prodListLbl.setBackground(Color.WHITE);
		prodListLbl.setForeground(Color.WHITE);
		prodListLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		prodListLbl.setBounds(447, 249, 150, 19);
		CenterPanel.add(prodListLbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setBounds(119, 279, 722, 210);
		CenterPanel.add(scrollPane);

		prodTb = new JTable();
		prodTb.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		prodTb.setForeground(Color.BLACK);
		scrollPane.setViewportView(prodTb);
		prodTb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)prodTb.getModel();
				int Myindex = prodTb.getSelectedRow();
				prodId.setText(model.getValueAt(Myindex, 0).toString());
				prodName.setText(model.getValueAt(Myindex, 1).toString());
				prodQty.setText(model.getValueAt(Myindex, 2).toString());
				prodPrice.setText(model.getValueAt(Myindex,3).toString());
				catCombo.setSelectedItem(model.getValueAt(Myindex, 4).toString());
				
			}


		});

		prodName = new JTextField();
		prodName.setForeground(Color.BLACK);
		prodName.setFont(new Font("Century Gothic", Font.BOLD, 15));
		prodName.setColumns(10);
		prodName.setBackground(Color.WHITE);
		prodName.setBounds(192, 70, 171, 29);
		CenterPanel.add(prodName);
		getCategory();
		refreshTable();
		
	}
}
