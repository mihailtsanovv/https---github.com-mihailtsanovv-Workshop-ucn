package UI;

import Control.*;
import Model.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JPanel mainMenuPanel;
	private JPanel secondaryMenuPanel;
	private JPanel contentPanel;
	private JTable table;
	private CtrCustomer cc = new CtrCustomer();
	Customer c = new Customer();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainMenuPanel = new JPanel();
		mainMenuPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
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

		JButton btnProducts = new JButton("Products");
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				secondaryMenuPanel.removeAll();

				JButton btnAddProduct = new JButton("Add Products");
				secondaryMenuPanel.add(btnAddProduct);
				btnAddProduct.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						contentPanel.removeAll();
					
						table = new JTable();
						table.setFont(new Font("Tahoma", Font.PLAIN, 15));
						table.setModel(new DefaultTableModel(new Object[][] { {
								null, null, null, null, null, null, null } },
								new String[] { "Barcode", "Name", "Price",
										"Purchase Price", "Rent Price",
										"Country", "Min Amount" }) {
							Class[] columnTypes = new Class[] { Integer.class,
									String.class, Float.class, Float.class,
									Float.class, String.class, Integer.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] canEdit = new boolean[] { true, true,
									true, true, true, true, true };

							public boolean isCellEditable(int rowIndex,
									int columnIndex) {
								return canEdit[columnIndex];
							}
						});

						table.setBounds(10, 27, 588, 195);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 52, 610, 300);
						table.setFillsViewportHeight(true);
						contentPanel.add(scrollPane);
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();

						JButton btnAdd = new JButton("Add");
						btnAdd.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								//
								model.addRow(new Object[] { null, null, null,
										null, null, null, null });
							}
						});
						btnAdd.setBounds(420, 11, 89, 23);
						contentPanel.add(btnAdd);

						JButton btnRemove = new JButton("Remove");
						btnRemove.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (table.getSelectedRow() != -1) {
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

						table.setModel(new DefaultTableModel(new Object[][] { {
								null, null, null, null, null, null, null } },
								new String[] { "Barcode", "Name", "Price",
										"Purchase Price", "Rent Price",
										"Country", "Min Amount" }) {
							Class[] columnTypes = new Class[] { Integer.class,
									String.class, Float.class, Float.class,
									Float.class, String.class, Integer.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] canEdit = new boolean[] { false, true,
									true, true, true, true, true };

							public boolean isCellEditable(int rowIndex,
									int columnIndex) {
								return canEdit[columnIndex];
							}
						});

						table.setBounds(10, 27, 588, 195);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 52, 610, 300);
						table.setFillsViewportHeight(true);
						contentPanel.add(scrollPane);
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();

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

				invalidate();
				revalidate();
				repaint();
				setVisible(true);

			}
		});
		mainMenuPanel.add(btnProducts);

		JButton btnCustomers = new JButton("Customers");
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				secondaryMenuPanel.removeAll();

				JButton btnAddCustomers = new JButton("Add Customers");
				secondaryMenuPanel.add(btnAddCustomers);
				btnAddCustomers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						contentPanel.removeAll();

						table = new JTable();
						table.setFont(new Font("Tahoma", Font.PLAIN, 15));

						table.setModel(new DefaultTableModel(new Object[][] { {
								null, null, null, null, null, null } },
								new String[] { "Name", "Address", "Zip Code",
										"City", "Phone" }) {
							Class[] columnTypes = new Class[] { String.class,
									String.class, Integer.class, String.class,
									Long.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] canEdit = new boolean[] { true, true,
									true, true, true, true };

							public boolean isCellEditable(int rowIndex,
									int columnIndex) {
								return canEdit[columnIndex];
							}
						});

						table.setBounds(10, 27, 588, 195);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 52, 610, 300);
						table.setFillsViewportHeight(true);
						contentPanel.add(scrollPane);
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();

						JButton btnAdd = new JButton("Add");
						btnAdd.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								//
								model.addRow(new Object[] { null, null, null,
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

								table.selectAll();
								int[] vals = table.getSelectedRows();
								for (int i = 0; i < vals.length; i++) {
									ArrayList<String> values = new ArrayList<>();
									for (int x = 0; x < table.getColumnCount(); x++) {
										System.out.println(table.getValueAt(i,
												x));
//										if (table.getValueAt(i, x) == null)
//											values.add("");
//										else
											values.add(table.getValueAt(i, x)
													.toString());
									}

									try {

										cc.insertCust(
												values.get(0),
												values.get(1),
												Integer.parseInt(values.get(2)),
												values.get(3), values.get(4));
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									values.clear();
								}

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

				JButton btnShowCustomers = new JButton("Show Customers");
				secondaryMenuPanel.add(btnShowCustomers);
				btnShowCustomers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						contentPanel.removeAll();

						JTextField txtSearch = new JTextField();
						txtSearch.setBounds(15, 11, 86, 25);
						contentPanel.add(txtSearch);
						txtSearch.setColumns(10);

						JButton btnSearch = new JButton("Search");
						btnSearch.setBounds(111, 11, 89, 25);
						btnSearch.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String search = txtSearch.getText();
								boolean flag = false;
								try {
									Integer.parseInt(search);
									flag = true;
								} catch (Exception e2) {
								}
								
								if (flag) {
									c = cc.findById(Integer.parseInt(search));
								} else {
									c = cc.findByName(search);
								}

								DefaultTableModel model = (DefaultTableModel) table
										.getModel();
								if (table.getRowCount() > 0) {
									int rowCount = model.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model.removeRow(i);
									}
								}
								model.addRow(new Object[] { c.getId(),
										c.getName(), c.getAddress(),
										c.getZipCode(), c.getCity(),
										c.getPhone() });
							}
						});
						contentPanel.add(btnSearch);

						table = new JTable();
						table.setFont(new Font("Tahoma", Font.PLAIN, 15));

						table.setModel(new DefaultTableModel(new Object[][] { {
								null, null, null, null, null, null } },
								new String[] { "ID", "Name", "Address",
										"Zip Code", "City", "Phone" }) {
							Class[] columnTypes = new Class[] { Integer.class,
									String.class, String.class, Integer.class,
									String.class, Long.class };


							
							
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] canEdit = new boolean[] { false, true,
									true, true, true, true };

							public boolean isCellEditable(int rowIndex,
									int columnIndex) {
								return canEdit[columnIndex];
							}
						});

						table.setBounds(10, 27, 588, 195);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 52, 610, 300);
						table.setFillsViewportHeight(true);
						contentPanel.add(scrollPane);
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();
				
						ArrayList<Customer> cList = cc.findAllCustomers();
						model.removeRow(0);
						for(Customer c : cList){
							
							model.addRow(new Object[] { c.getId(),
									c.getName(), c.getAddress(),
									c.getZipCode(), c.getCity(),
									c.getPhone() });
						}
						
						
						JButton btnUpdate = new JButton("Update");
						btnUpdate.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								
								int[] vals = table.getSelectedRows();
								for (int i = 0; i < vals.length; i++) {
									ArrayList<String> values = new ArrayList<>();
									for (int x = 0; x < table.getColumnCount(); x++) {
										System.out.println(table.getValueAt(i,x));
										values.add(table.getValueAt(i, x)
												.toString());
									}
									cc.updateCust(Integer.parseInt(values.get(0)),
											values.get(1),
											values.get(2),
											Integer.parseInt(values.get(3)), values.get(4),values.get(5));
								}
									
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
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

				invalidate();
				revalidate();
				repaint();
				setVisible(true);
			}
		});
		mainMenuPanel.add(btnCustomers);

		JButton btnSuppliers = new JButton("Suppliers");
		btnSuppliers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				secondaryMenuPanel.removeAll();

				JButton btnAddSuppliers = new JButton("Add Suppliers");
				secondaryMenuPanel.add(btnAddSuppliers);
				btnAddSuppliers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						contentPanel.removeAll();

						table = new JTable();
						table.setFont(new Font("Tahoma", Font.PLAIN, 15));

						table.setModel(new DefaultTableModel(new Object[][] { {
								null, null, null, null, null, null } },
								new String[] { "ID", "Name", "Address",
										"Country", "Phone", "E-mail" }) {
							Class[] columnTypes = new Class[] { Integer.class,
									String.class, String.class, String.class,
									Long.class, String.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] canEdit = new boolean[] { true, true,
									true, true, true, true };

							public boolean isCellEditable(int rowIndex,
									int columnIndex) {
								return canEdit[columnIndex];
							}
						});

						table.setBounds(10, 27, 588, 195);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 52, 610, 300);
						table.setFillsViewportHeight(true);
						contentPanel.add(scrollPane);
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();

						JButton btnAdd = new JButton("Add");
						btnAdd.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								//
								model.addRow(new Object[] { null, null, null,
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

				JButton btnShowSuppliers = new JButton("Show Suppliers");
				secondaryMenuPanel.add(btnShowSuppliers);
				btnShowSuppliers.addActionListener(new ActionListener() {
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

						table.setModel(new DefaultTableModel(new Object[][] { {
								null, null, null, null, null, null } },
								new String[] { "ID", "Name", "Address",
										"Country", "Phone", "E-mail" }) {
							Class[] columnTypes = new Class[] { Integer.class,
									String.class, String.class, String.class,
									Long.class, String.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] canEdit = new boolean[] { false, true,
									true, true, true, true };

							public boolean isCellEditable(int rowIndex,
									int columnIndex) {
								return canEdit[columnIndex];
							}
						});

						table.setBounds(10, 27, 588, 195);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 52, 610, 300);
						table.setFillsViewportHeight(true);
						contentPanel.add(scrollPane);
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();

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

				invalidate();
				revalidate();
				repaint();
				setVisible(true);
			}
		});
		mainMenuPanel.add(btnSuppliers);

		JButton btnSales = new JButton("Sales");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondaryMenuPanel.removeAll();

				JButton btnMakeASale = new JButton("Create sale");
				secondaryMenuPanel.add(btnMakeASale);
				btnMakeASale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						contentPanel.removeAll();

						JTextField textFieldCustomer = new JTextField();
						textFieldCustomer.setBounds(101, 8, 102, 20);
						contentPanel.add(textFieldCustomer);
						textFieldCustomer.setColumns(10);

						JLabel lblCustomerId = new JLabel("Customer ID:");
						lblCustomerId.setBounds(15, 11, 111, 14);
						contentPanel.add(lblCustomerId);

						JRadioButton rdbtnNotAMember = new JRadioButton(
								"Not a member");
						rdbtnNotAMember.setBounds(225, 7, 109, 23);
						contentPanel.add(rdbtnNotAMember);
						rdbtnNotAMember.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								if (textFieldCustomer.isEditable()) {
									textFieldCustomer.setText("");
									textFieldCustomer.setEditable(false);

								} else {
									textFieldCustomer.setEditable(true);
								}
							}
						});

						table = new JTable();
						table.setFont(new Font("Tahoma", Font.PLAIN, 15));

						table.setModel(new DefaultTableModel(new Object[][] { {
								null, null, null, null, null, null } },
								new String[] { "Barcode", "Name", "Price/pc",
										"Quantity", "Total" }) {
							Class[] columnTypes = new Class[] { Integer.class,
									String.class, Float.class, Integer.class,
									Float.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] canEdit = new boolean[] { true, true,
									false, true, false };

							public boolean isCellEditable(int rowIndex,
									int columnIndex) {
								return canEdit[columnIndex];
							}
						});

						table.setBounds(10, 27, 588, 195);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 52, 610, 300);
						table.setFillsViewportHeight(true);
						contentPanel.add(scrollPane);
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();

						JButton btnAdd = new JButton("Add");
						btnAdd.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								//
								model.addRow(new Object[] { null, null, null,
										null, null });
							}
						});
						btnAdd.setBounds(425, 11, 89, 23);
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
						btnRemove.setBounds(529, 11, 89, 23);
						contentPanel.add(btnRemove);

						JLabel lblTotalPrice = new JLabel("Total price:");
						lblTotalPrice
								.setHorizontalAlignment(SwingConstants.TRAILING);
						lblTotalPrice.setBounds(348, 355, 72, 23);
						contentPanel.add(lblTotalPrice);

						JTextField textFieldPrice = new JTextField();
						textFieldPrice.setBounds(430, 355, 86, 23);
						contentPanel.add(textFieldPrice);
						textFieldPrice.setColumns(10);

						JButton btnSubmit = new JButton("Submit");
						btnSubmit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						btnSubmit.setBounds(529, 355, 89, 23);
						contentPanel.add(btnSubmit);

						invalidate();
						revalidate();
						repaint();
						setVisible(true);

					}
				});

				JButton btnShowSale = new JButton("Show sale");
				secondaryMenuPanel.add(btnShowSale);
				btnShowSale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

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

						table.setModel(new DefaultTableModel(new Object[][] { {
								null, null, null, null } }, new String[] {
								"ID", "Date", "Customer", "Price" }) {
							Class[] columnTypes = new Class[] { Integer.class,
									String.class, String.class, Float.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] canEdit = new boolean[] { false, false,
									false, false };

							public boolean isCellEditable(int rowIndex,
									int columnIndex) {
								return canEdit[columnIndex];
							}
						});

						table.setBounds(10, 27, 588, 195);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 52, 610, 300);
						table.setFillsViewportHeight(true);
						contentPanel.add(scrollPane);
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();

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

				invalidate();
				revalidate();
				repaint();
				setVisible(true);

			}
		});
		mainMenuPanel.add(btnSales);
	}
}
