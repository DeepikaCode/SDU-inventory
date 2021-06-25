import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;

public class AddGodown {

	private JFrame frmAddGodown;
	private JTextField jtfGodown;
	private JTable jtabGodownData;
	String godown_id=null;
	private JTextField jtfCapacity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGodown window = new AddGodown();
					window.frmAddGodown.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddGodown() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddGodown = new JFrame();
		frmAddGodown.setResizable(false);
		frmAddGodown.setTitle("Shiv Dal Udyog");
		frmAddGodown.setBounds(100, 100, 576, 627);
		frmAddGodown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddGodown.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Godown Inventory");
		lblNewLabel.setForeground(new Color(210, 105, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		lblNewLabel.setBounds(156, 22, 247, 44);
		frmAddGodown.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Godown Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(25, 93, 165, 38);
		frmAddGodown.getContentPane().add(lblNewLabel_1);
		
		jtfGodown = new JTextField();
		jtfGodown.addKeyListener(new KeyAdapter() {
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
						if(godown_id==null) 
							addData();
						else 
							updateData();
					} 
					else if(code == 127) 	//delete
					{
						if(godown_id!=null) 
							deleteData();					
					}
				}
			}
		});
		jtfGodown.setFont(new Font("Verdana", Font.PLAIN, 18));
		jtfGodown.setBounds(230, 94, 268, 38);
		frmAddGodown.getContentPane().add(jtfGodown);
		jtfGodown.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Capacity");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(25, 142, 165, 38);
		frmAddGodown.getContentPane().add(lblNewLabel_1_1);
		
		jtfCapacity = new JTextField();
		jtfCapacity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code=e.getKeyCode();
				if( (code>=97 && code<=122) || code==10 || code==127 || code==8)
				{
					if(code == 10)		//enter key
					{
						if(godown_id==null) 
							addData();
						else 
							updateData();
					} 
					else if(code == 127) 	//delete
					{
						if(godown_id!=null) 
							deleteData();					
					}
				}
					
			}
		});
		jtfCapacity.setFont(new Font("Verdana", Font.PLAIN, 18));
		jtfCapacity.setColumns(10);
		jtfCapacity.setBounds(230, 143, 268, 38);
		frmAddGodown.getContentPane().add(jtfCapacity);
		
		JButton jbtnAdd = new JButton("Add");
		jbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				addData();
			}
		});
		jbtnAdd.setFont(new Font("Verdana", Font.PLAIN, 18));
		jbtnAdd.setBounds(25, 208, 79, 38);
		frmAddGodown.getContentPane().add(jbtnAdd);
		
		JButton jbtnEdit = new JButton("Edit");
		jbtnEdit.setMaximumSize(new Dimension(2147483647, 2147483647));
		jbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateData();
				
			}
		});
		jbtnEdit.setFont(new Font("Verdana", Font.PLAIN, 18));
		jbtnEdit.setBounds(132, 208, 91, 38);
		frmAddGodown.getContentPane().add(jbtnEdit);
		
		JButton jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteData();
			}
		});
		jbtnDelete.setFont(new Font("Verdana", Font.PLAIN, 18));
		jbtnDelete.setBounds(261, 210, 106, 34);
		frmAddGodown.getContentPane().add(jbtnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.RED);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane.setAutoscrolls(true);
		scrollPane.setFocusable(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(103, 293, 339, 266);
		frmAddGodown.getContentPane().add(scrollPane);
		
		jtabGodownData = new JTable();
		jtabGodownData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//jbtnAdd.setEnabled(false);
				int row=jtabGodownData.getSelectedRow();
				String godown=jtabGodownData.getValueAt(row,1).toString();
				jtfGodown.setText(godown);
				String capacity=""+jtabGodownData.getValueAt(row,2);
				jtfCapacity.setText(capacity);;
				godown_id=""+DataManipulation.getID("Select godown_id from godown where godown_name='" + godown + "';");
				System.out.println("fetched dal_id= " + godown_id);
								
			}
		});
		jtabGodownData.setForeground(Color.DARK_GRAY);
		jtabGodownData.setBackground(new Color(255, 255, 255));
		jtabGodownData.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jtabGodownData.setAlignmentX(Component.RIGHT_ALIGNMENT);
		jtabGodownData.setGridColor(new Color(176, 196, 222));
		jtabGodownData.setRequestFocusEnabled(false);
		jtabGodownData.setShowHorizontalLines(false);
		jtabGodownData.setRowHeight(18);
		jtabGodownData.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		/*DefaultTableModel tableModel = new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};

		table.setModel(tableModel);
	*/
		
		jtabGodownData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Godown", "Capacity"
			}
		));
		scrollPane.setViewportView(jtabGodownData);
		
		JButton jbtnReset = new JButton("Reset");
		jbtnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setReset();
			}
		});
		jbtnReset.setFont(new Font("Verdana", Font.PLAIN, 18));
		jbtnReset.setBounds(410, 210, 91, 34);
		frmAddGodown.getContentPane().add(jbtnReset);
		
		showTable();	//function call to display values in table
		
			
				
	}
	
	void showTable()
	{
		int id=1;
		String strQuery="select godown_id as No, godown_name as Godown, capacity from godown where flag=1";
		DataManipulation.getDataTable(strQuery, jtabGodownData);
		ResultSet rs=DataManipulation.getData(strQuery);
		Object cols[]= {"No","Godown","Capacity"};
		DefaultTableModel tb=new DefaultTableModel(cols,0);
		try
		{
			while(rs.next())
			{
				Object arr[]= {id,rs.getObject(2),rs.getObject(3)};
				tb.addRow(arr);
				id++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		jtabGodownData.setModel(tb);
		
		//set column width
		jtabGodownData.setSize(150,0);
		jtabGodownData.setRowHeight(30);
		jtabGodownData.setDragEnabled(false);
				
		/*String strQuery="select * from godown;";
		DataManipulation.getDataTable(strQuery, jtabDalData);*/
	}
	
	
	void addData()
	{
		String godown=jtfGodown.getText();
		String capacity=jtfCapacity.getText();
		godown_id=""+DataManipulation.getID("Select max(godown_id)+1 from godown");
		
		if(capacity.isEmpty())	//if value is not provided
			capacity="null";
		String strQuery="insert into godown (godown_id, godown_name, capacity) values (" + godown_id + ",'" + godown + "', " + capacity +");";
		System.out.println(strQuery);
		boolean status=DataManipulation.insertData(strQuery);
		//if(status)
			//JOptionPane.showMessageDialog(null, "Dal added to the record.", "Save",JOptionPane.INFORMATION_MESSAGE);
		if(!status)
			JOptionPane.showMessageDialog(null,"Unable to add Godown to the record.", "Error!", JOptionPane.ERROR_MESSAGE);
		showTable();
		setReset();
	}
	
	
	void updateData()
	{
		String godown=jtfGodown.getText();
		String capacity=jtfCapacity.getText();
		String strQuery="Update godown set godown_name='" + godown + "', capacity=" + capacity + " where godown_id= " + godown_id + ";";
		System.out.println(strQuery);
		int n=DataManipulation.updateData(strQuery);
		JOptionPane.showMessageDialog(null, n + " records updated.", "Save", JOptionPane.INFORMATION_MESSAGE);
		showTable();
		setReset();
	}
	
	
	void deleteData()
	{
		String strQuery="Update GODOWN set flag=0 where godown_id=" + godown_id + ";";
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
		jtfGodown.setText("");
		jtfGodown.grabFocus();
		jtfCapacity.setText("");
		godown_id=null;
	}
	
}


