import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddDal {

	private JFrame frmAddDal;
	private JTextField jtfDal;
	private JTable jtabDalData;
	String dal_id=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDal window = new AddDal();
					window.frmAddDal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddDal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddDal = new JFrame();
		frmAddDal.setResizable(false);
		frmAddDal.setTitle("Shiv Dal Udyog");
		frmAddDal.setBounds(100, 100, 680, 633);
		frmAddDal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddDal.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dal Inventory");
		lblNewLabel.setForeground(new Color(210, 105, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		lblNewLabel.setBounds(168, 25, 326, 44);
		frmAddDal.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dal Name");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(75, 135, 128, 38);
		frmAddDal.getContentPane().add(lblNewLabel_1);
		
		jtfDal = new JTextField();
		jtfDal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code=e.getKeyCode();
				//System.out.println("dal_id= "+ dal_id + " keyCode= " + code + " jtext= " + jtfDal.getText());
				//if(jtfDal.getText()=="")  System.out.println("empty string in jtf");
					//checking for invalid inputs in jtfDal
				if( (code>=48 && code<=57) || (code>=65 && code<=90) || (code>=97 && code<=122) || code==10 || code==127 || code==8 || code==32 )
				{
					if(code == 10)		//enter key
					{
						if(dal_id==null) 
							addData();
						else 
							updateData();
					} 
					else if(code == 127) 	//delete
					{
						if(dal_id!=null) 
							deleteData();					
					}
				}
			}
		});
		jtfDal.setFont(new Font("Verdana", Font.PLAIN, 18));
		jtfDal.setBounds(232, 125, 268, 38);
		frmAddDal.getContentPane().add(jtfDal);
		jtfDal.setColumns(10);
		
		JButton jbtnAdd = new JButton("Add");
		jbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				addData();
			}
		});
		jbtnAdd.setFont(new Font("Verdana", Font.PLAIN, 18));
		jbtnAdd.setBounds(25, 208, 94, 38);
		frmAddDal.getContentPane().add(jbtnAdd);
		
		JButton jbtnEdit = new JButton("Edit");
		jbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateData();
				
			}
		});
		jbtnEdit.setFont(new Font("Verdana", Font.PLAIN, 18));
		jbtnEdit.setBounds(148, 208, 120, 38);
		frmAddDal.getContentPane().add(jbtnEdit);
		
		JButton jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteData();
			}
		});
		jbtnDelete.setFont(new Font("Verdana", Font.PLAIN, 18));
		jbtnDelete.setBounds(311, 210, 128, 34);
		frmAddDal.getContentPane().add(jbtnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.RED);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane.setAutoscrolls(true);
		scrollPane.setFocusable(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(196, 295, 247, 266);
		frmAddDal.getContentPane().add(scrollPane);
		
		jtabDalData = new JTable();
		jtabDalData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row=jtabDalData.getSelectedRow();
				String dal=jtabDalData.getValueAt(row,1).toString();
				jtfDal.setText(dal);
				dal_id=""+DataManipulation.getID("Select dal_id from dal where dal_name='" + dal + "';");
				System.out.println("fetched dal_id= " + dal_id);
								
			}
		});
		jtabDalData.setForeground(Color.DARK_GRAY);
		jtabDalData.setBackground(new Color(255, 255, 255));
		jtabDalData.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jtabDalData.setAlignmentX(Component.RIGHT_ALIGNMENT);
		jtabDalData.setGridColor(new Color(176, 196, 222));
		jtabDalData.setRequestFocusEnabled(false);
		jtabDalData.setShowHorizontalLines(false);
		jtabDalData.setRowHeight(18);
		jtabDalData.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		jtabDalData.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {},
	            new String [] {"No", "Dal","Flag"}
	        ));
		
		jtabDalData.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtabDalData.getColumnModel().getColumn(1).setPreferredWidth(90);
		scrollPane.setViewportView(jtabDalData);
		
		JButton jbtnReset = new JButton("Reset");
		jbtnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setReset();
			}
		});
		jbtnReset.setFont(new Font("Verdana", Font.PLAIN, 18));
		jbtnReset.setBounds(475, 210, 128, 34);
		frmAddDal.getContentPane().add(jbtnReset);
		showTable();	//function call to display values in table
		
			
				
	}
	
	void showTable()
	{
		int id=1;
		String strQuery="select dal_id as No, dal_name as Dal from dal where flag=1;";
		DataManipulation.getDataTable(strQuery, jtabDalData);
		ResultSet rs=DataManipulation.getData(strQuery);
		Object cols[]= {"No","Dal"};
		DefaultTableModel tb=new DefaultTableModel(cols,0);
		try
		{
			while(rs.next())
			{
				Object arr[]= {id,rs.getObject(2)};
				tb.addRow(arr);
				id++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		jtabDalData.setModel(tb);
		
		//set column width
		jtabDalData.setSize(150,0);
		jtabDalData.setRowHeight(30);
		jtabDalData.setDragEnabled(false);
		jtabDalData.getColumnModel().getColumn(0).setWidth(50);
		jtabDalData.getColumnModel().getColumn(0).setMaxWidth(60);
		jtabDalData.getColumnModel().getColumn(1).setWidth(300);
		jtabDalData.getColumnModel().getColumn(1).setMaxWidth(500);	
				
		/*String strQuery="select * from dal;";
		DataManipulation.getDataTable(strQuery, jtabDalData);*/
	}
	
	
	void addData()
	{
		String dal=jtfDal.getText();
		dal_id=""+DataManipulation.getID("Select max(dal_id)+1 from dal");
		String strQuery="insert into dal values (" + dal_id + ",'" + dal + "', 1);";
		System.out.println(strQuery);
		boolean status=DataManipulation.insertData(strQuery);
		//if(status)
			//JOptionPane.showMessageDialog(null, "Dal added to the record.", "Save",JOptionPane.INFORMATION_MESSAGE);
		if(!status)
			JOptionPane.showMessageDialog(null,"Unable to add Dal to the record.", "Error!", JOptionPane.ERROR_MESSAGE);
		showTable();
		setReset();
	}
	
	
	void updateData()
	{
		String dal=jtfDal.getText();
		String strQuery="Update dal set dal_name='" + dal + "' where dal_id= " + dal_id + ";";
		System.out.println(strQuery);
		int n=DataManipulation.updateData(strQuery);
		JOptionPane.showMessageDialog(null, n + " records updated.", "Save", JOptionPane.INFORMATION_MESSAGE);
		showTable();
		setReset();
	}
	
	
	void deleteData()
	{
		String strQuery="Update Dal set flag=0 where dal_id=" + dal_id + ";";
		System.out.println(strQuery);
		int n=DataManipulation.deleteData(strQuery);
		showTable();
		if(n>0)
		{
			JOptionPane.showMessageDialog(null, n+ " records deleted", "Delete", JOptionPane.INFORMATION_MESSAGE );					
		}
			else
			JOptionPane.showMessageDialog(null, "Unable to delete record", "Error!", JOptionPane.ERROR_MESSAGE);
		setReset();
	}
	
	void setReset()
	{
		jtfDal.setText("");
		jtfDal.grabFocus();
		dal_id=null;
	}
}
