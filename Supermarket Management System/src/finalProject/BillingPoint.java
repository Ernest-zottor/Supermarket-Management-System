package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

public class BillingPoint extends JFrame {

	private JPanel contentPane;
	private JTextField prodName;
	private JTextField prodQty;
	private JTable prodTb;
	private JTextArea billTxt;
	private JLabel totalLbl;
	private JComboBox catCombo;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillingPoint frame = new BillingPoint();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	
		Connection conn = null;
		Double getPrice;
		Double prodTotal=0.0, totalPurchase=0.0;
		int availQty, priId, newQty;

		public void refreshTable() {
			try {
				String query = "SELECT * from ProductTb";
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				prodTb.setModel(DbUtils.resultSetToTableModel(rs));
				rs.close();

			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}


		public void update() {
			try {
				newQty = availQty - Integer.valueOf(prodQty.getText());
				String query = "UPDATE ProductTb SET Quantity='"+newQty+"' where ID="+priId;
				PreparedStatement pst = conn.prepareStatement(query);
				pst.execute();

				refreshTable();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}


		public void getCategory() {
			try {
				String query = "SELECT * from Categoriestb";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while(rs.next()) {
					String myCat = rs.getString("Name");
					catCombo.addItem(myCat);
				}
				rs.close();

			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		
		public void showDateAndTime(JTextArea obj) {
			Calendar dateandTime = new GregorianCalendar();
			LocalDate today = LocalDate.now();
			int day = dateandTime.get(Calendar.DAY_OF_MONTH);
			int month = today.getMonthValue(); 
			int year = dateandTime.get(Calendar.YEAR);
			
			int hour = dateandTime.get(Calendar.HOUR);
			int minute = dateandTime.get(Calendar.MINUTE);
			int second = dateandTime.get(Calendar.SECOND);
			obj.setText(obj.getText()+"\n\t\t\t"+hour+":"+minute+":"+second+"    ");
			obj.setText(obj.getText()+day+"/"+month+"/"+year);
		}

		
		
	public BillingPoint() {
		conn = SqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/super (3).jpg")));
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(leftPanel, BorderLayout.WEST);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[]{103, 0};
		gbl_leftPanel.rowHeights = new int[]{99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_leftPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_leftPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_leftPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("LOGOUT");
		lblNewLabel.setToolTipText("Click to logout");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(889, Short.MAX_VALUE))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		bottomPanel.setLayout(gl_bottomPanel);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(248, 148, 6));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_10 = new JLabel("BILLING POINT");
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setFont(new Font("Century Gothic", Font.BOLD, 20));
		topPanel.add(lblNewLabel_10);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(44, 62, 80));
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(44, 62, 80));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("NAME");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(43, 46, 84, 19);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("QUANTITY");
		lblNewLabel_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_3_1.setBounds(43, 93, 84, 19);
		panel.add(lblNewLabel_1_3_1);
		
		prodName = new JTextField();
		prodName.setForeground(Color.BLACK);
		prodName.setFont(new Font("Century Gothic", Font.BOLD, 15));
		prodName.setColumns(10);
		prodName.setBackground(Color.WHITE);
		prodName.setBounds(128, 36, 171, 29);
		panel.add(prodName);
		
		prodQty = new JTextField();
		prodQty.setForeground(Color.BLACK);
		prodQty.setFont(new Font("Century Gothic", Font.BOLD, 15));
		prodQty.setColumns(10);
		prodQty.setBackground(Color.WHITE);
		prodQty.setBounds(128, 88, 171, 29);
		panel.add(prodQty);
		
		JButton btnAddToBill = new JButton("ADD TO BILL");
		btnAddToBill.addActionListener(new ActionListener() {
			int i =0;
			public void actionPerformed(ActionEvent arg0) {

				if(prodName.getText().isEmpty() || prodQty.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing information");
				}
				else if(availQty<=Integer.valueOf(prodQty.getText())) {
					JOptionPane.showMessageDialog(null, "Not Enough in Stock");

				}
				else {
					i++;

					prodTotal = getPrice * Double.valueOf(prodQty.getText());
					totalPurchase+=prodTotal;
					if(i==1) {
						billTxt.setText(billTxt.getText()+ "\t==========DAVINA SUPERMARKET========\n" +"NUM    PRODUCT         PRICE     "
								+ "QUANTITY    "+ "TOTAL\n" + "" +i+ "          "  +prodName.getText()+  "               "  +getPrice+  "	    "   
								+" "+prodQty.getText()+  "           "  +"  "+prodTotal  +"\n");
					}
					else {
						billTxt.setText(billTxt.getText()+i+"          "  +prodName.getText()+  "               "  +getPrice+  "	    "   
								+prodQty.getText()+  "           "  +prodTotal  +"\n");
						
					}

					totalLbl.setText("Gh¢" +totalPurchase);
					update();
					prodName.setText("");
					prodQty.setText("");
				}
			}
		});
		btnAddToBill.setForeground(Color.WHITE);
		btnAddToBill.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnAddToBill.setBackground(new Color(255, 140, 0));
		btnAddToBill.setBounds(43, 168, 130, 29);
		panel.add(btnAddToBill);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prodName.setText("");
				prodQty.setText("");
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnClear.setBackground(new Color(255, 140, 0));
		btnClear.setBounds(233, 168, 108, 29);
		panel.add(btnClear);
		
		JButton btnFilter = new JButton("FILTER");
		btnFilter.setToolTipText("Click to filter table");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * from ProductTb where Category = '"+catCombo.getSelectedItem().toString()+"'";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					prodTb.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();

				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFilter.setForeground(Color.WHITE);
		btnFilter.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnFilter.setBackground(new Color(255, 140, 0));
		btnFilter.setBounds(610, 36, 95, 29);
		panel.add(btnFilter);
		
		JButton btnRfreash = new JButton("REFRESH");
		btnRfreash.setToolTipText("Click to refresh table");
		btnRfreash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTable();
			}
		});
		btnRfreash.setForeground(Color.WHITE);
		btnRfreash.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnRfreash.setBackground(new Color(255, 140, 0));
		btnRfreash.setBounds(723, 36, 108, 29);
		panel.add(btnRfreash);
		
		catCombo = new JComboBox();
		catCombo.setForeground(Color.BLACK);
		catCombo.setFont(new Font("Century Gothic", Font.BOLD, 15));
		catCombo.setBackground(Color.WHITE);
		catCombo.setBounds(473, 36, 108, 29);
		panel.add(catCombo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(463, 76, 398, 180);
		panel.add(scrollPane_1);
		
		prodTb = new JTable();
		prodTb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		prodTb.setForeground(new Color(0, 0, 0));
		prodTb.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(prodTb);
		prodTb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				model = (DefaultTableModel)prodTb.getModel();
				int Myindex = prodTb.getSelectedRow();
				priId =Integer.valueOf(model.getValueAt(Myindex, 0).toString());
				availQty = Integer.valueOf(model.getValueAt(Myindex, 2).toString());
				//newQty = availQty - Integer.valueOf(prodQty.getText());
				prodName.setText(model.getValueAt(Myindex, 1).toString());
				getPrice = Double.valueOf(model.getValueAt(Myindex, 3).toString());

			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(463, 267, 398, 170);
		panel.add(scrollPane);
		
		billTxt = new JTextArea();
		scrollPane.setViewportView(billTxt);
		
		JButton printBtn = new JButton("Print");
		printBtn.setToolTipText("Click to print receipt");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			try {
				
				billTxt.setText(billTxt.getText()+ "---------------------------------------------------------------\n"+"\tTOTAL PURCHASE = "+"Gh¢ " +Double.toString(totalPurchase));
				showDateAndTime(billTxt);
				billTxt.print();
				
			}catch(Exception e) {
				e.printStackTrace();
			}

			}
		});
		printBtn.setForeground(Color.WHITE);
		printBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		printBtn.setBackground(new Color(255, 140, 0));
		printBtn.setBounds(525, 450, 78, 29);
		panel.add(printBtn);
		
		totalLbl = new JLabel("Gh\u00A2");
		totalLbl.setForeground(Color.WHITE);
		totalLbl.setFont(new Font("Century Gothic", Font.BOLD, 15));
		totalLbl.setBounds(642, 461, 78, 17);
		panel.add(totalLbl);
		
		JLabel prodLbl = new JLabel("PRODUCTS LIST");
		prodLbl.setForeground(Color.WHITE);
		prodLbl.setFont(new Font("Century Gothic", Font.BOLD, 18));
		prodLbl.setBounds(585, 6, 135, 19);
		panel.add(prodLbl);
		refreshTable();
		getCategory();
	}
}
