package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ProductsGUI extends JFrame {

	private JPanel contentPane;
	private JPanel mainMenuPanel;
	private JPanel secondaryMenuPanel;
	private JPanel contentPanel;
	private JTable table;

	public ProductsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainMenuPanel = new JPanel();
		mainMenuPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		mainMenuPanel.setBounds(10, 11, 631, 39);
		contentPane.add(mainMenuPanel);
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		secondaryMenuPanel = new JPanel();
		secondaryMenuPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		secondaryMenuPanel.setBounds(10, 55, 631, 39);
		contentPane.add(secondaryMenuPanel);

		contentPanel = new JPanel();
		contentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contentPanel.setBounds(10, 105, 631, 386);
		contentPane.add(contentPanel);
		contentPanel.setLayout(null);
		makeProductsMenu();
	}

	public void makeProductsPanel() {

		JButton btnAddProduct = new JButton("Add Products");
		secondaryMenuPanel.add(btnAddProduct);
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				contentPanel.removeAll();

				table = new JTable();
				table.setFont(new Font("Tahoma", Font.PLAIN, 15));

				table.setModel(new DefaultTableModel(new Object[][] { { null,
						null, null, null, null, null, null } }, new String[] {
						"Barcode", "Name", "Price", "Purchase Price",
						"Rent Price", "Country", "Min Amount" }) {
					Class[] columnTypes = new Class[] { Integer.class,
							String.class, Float.class, Float.class,
							Float.class, String.class, Integer.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] canEdit = new boolean[] { true, true, true, true,
							true, true, true };

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});

				table.setBounds(10, 27, 588, 195);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(10, 52, 610, 300);
				table.setFillsViewportHeight(true);
				contentPanel.add(scrollPane);
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				JButton btnAdd = new JButton("Add");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//
						model.addRow(new Object[] { null, null, null, null,
								null, null, null });
					}
				});
				btnAdd.setBounds(420, 11, 89, 23);
				contentPanel.add(btnAdd);

				JButton btnRemove = new JButton("Remove");
				btnRemove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (table.getSelectedRow() != -1) {
							// remove selected row from the model
							model.removeRow(table.getSelectedRow());
						}
					}
				});
				btnRemove.setBounds(520, 11, 89, 23);
				contentPanel.add(btnRemove);

				JButton btnSubmit = new JButton("Submit");
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnSubmit.setBounds(530, 355, 89, 23);
				contentPanel.add(btnSubmit);

				invalidate();
				revalidate();
				repaint();
				setVisible(true);

			}
		});

		JButton btnShowProducts = new JButton("Show products");
		secondaryMenuPanel.add(btnShowProducts);
		btnShowProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				contentPanel.removeAll();

				JTextField txtSearch = new JTextField();
				txtSearch.setBounds(15, 11, 86, 25);
				contentPanel.add(txtSearch);
				txtSearch.setColumns(10);

				JButton btnSearch = new JButton("Search");
				btnSearch.setBounds(111, 11, 89, 25);
				contentPanel.add(btnSearch);

				table = new JTable();
				table.setFont(new Font("Tahoma", Font.PLAIN, 15));

				table.setModel(new DefaultTableModel(new Object[][] { { null,
						null, null, null, null, null, null } }, new String[] {
						"Barcode", "Name", "Price", "Purchase Price",
						"Rent Price", "Country", "Min Amount" }) {
					Class[] columnTypes = new Class[] { Integer.class,
							String.class, Float.class, Float.class,
							Float.class, String.class, Integer.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] canEdit = new boolean[] { false, true, true,
							true, true, true, true };

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});

				table.setBounds(10, 27, 588, 195);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(10, 52, 610, 300);
				table.setFillsViewportHeight(true);
				contentPanel.add(scrollPane);
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnUpdate.setBounds(430, 355, 89, 23);
				contentPanel.add(btnUpdate);

				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnDelete.setBounds(530, 355, 89, 23);
				contentPanel.add(btnDelete);

				invalidate();
				revalidate();
				repaint();
				setVisible(true);

			}
		});
	}
	
	public void makeProductsMenu(){
		makeProductsPanel();
		//this.getContentPane().add(mainMenuPanel);
		this.getContentPane().add(secondaryMenuPanel, BorderLayout.NORTH);
		//this.getContentPane().add(contentPanel);
	}

}
