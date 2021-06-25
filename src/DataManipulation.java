

	/*
	 * To change this template, choose Tools | Templates
	 * and open the template in the editor.
	 */

	
	import java.util.Vector;
	import java.sql.*;
	import javax.swing.JTable;
	import javax.swing.table.DefaultTableModel;

	/**
	 *
	 * @author Deepika 
	 */

	public class DataManipulation {
	    static Connection con=null;
	    static ResultSet rs=null;
	    static Statement stmt=null;
	    static void setConnection()
	    {
	        try
	        {
	            String userName="root";
	            String password="root";            
	            //Class.forName ("com.mysql.jdbc.Driver").newInstance();  //load the driver from jar file
	            String url="jdbc:mysql://localhost/sdu";
	            con = DriverManager.getConnection (url, userName, password);    //establish the connecction
	            //System.out.println ("Database connection established");
	        }
	        catch(Exception e)
	        {
	            System.out.println("Cannot connect to the database: "+e);
	            e.printStackTrace();
	        }
	    }
	    static boolean insertData(String strQuery)
	    {
	        boolean status=false;
	        try
	        {
	            setConnection();
	            stmt=con.createStatement();
	            stmt.executeUpdate(strQuery);
	            status=true;
	            stmt.close();
	            con.close();
	        }
	        catch(Exception e)
	        {
	            System.out.println("Exception in data insertion: "+e);
	            e.printStackTrace();
	        }
	        return status;
	    }
	    static int updateData(String strQuery)
	    {
	        int n=0;
	        try
	        {
	            setConnection();
	            stmt=con.createStatement();
	            n= stmt.executeUpdate(strQuery);
	            stmt.close();
	            con.close();
	        }
	        catch(Exception e)
	        {
	            System.out.println("Exception in data updation"+e);
	            e.printStackTrace();
	        }
	        return n;
	    }
	    static int deleteData(String strQuery)
	    {
	    	int n=0;
	        try
	        {
	            setConnection();
	            stmt=con.createStatement();
	            n= stmt.executeUpdate(strQuery);
	            stmt.close();
	            con.close();
	        }
	        catch(Exception e)
	        {
	            System.out.println("Exception in data deletion"+e);
	            e.printStackTrace();
	        }
	        return n;
	    }
	    
	    
	    static ResultSet getData(String strQuery)
	    {
	        try
	        {
	            setConnection();
	            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	            rs=stmt.executeQuery(strQuery);	            
	        }
	        catch(Exception e)
	        {
	            System.out.println("Failed to retrive data"+e);
	            e.printStackTrace();
	        }
	        return rs;
	    }
	    
	    static int getID(String strQuery)
	    {
	        int n=1;
	        try
	        {
	            setConnection();
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(strQuery);
	            if(rs.next())
	            {
	                n=rs.getInt(1);
	                if(n==0)
	                n=1;
	                System.out.println("Id Search  Success");
	            }
	        }
	        catch(Exception e)
	        {
	            System.out.println("Failed to retrive data"+e);
	            e.printStackTrace();
	        }
	        return n;
	    }

	    static String getValue(String strQuery)
	    {
	    String v=null;
	    try
	    {
	    setConnection();
	    stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    rs=stmt.executeQuery(strQuery);
	    if(rs.next())
	    v=rs.getString(1);
	    }
	    catch(Exception e)
	    {
	    System.out.println("Failed to retrive data"+e);
	    }
	    return v;
	    }

	    static int getRowCount(String strQuery)
	    {
	        int n=0;
	        try
	        {
	            setConnection();
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(strQuery);
	            while(rs.next())
	            {
	                n++;
	            }
	        }
	        catch(Exception e)
	        {
	            System.out.println("Error in getting row count: " + e);
	            e.printStackTrace();
	        }
	        return n;
	    }
	    
	    static void getDataTable(String strQuery, JTable jtable1)
	    {
	            try
	            {
	                setConnection();
	                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	                rs = stmt.executeQuery(strQuery);
	                Vector cols=new Vector();
	                ResultSetMetaData rsmd=rs.getMetaData();
	                int colsSize=rsmd.getColumnCount();
	                    //adding columns
	                for(int i=1;i<=colsSize;i++)
	                {
	                   cols.add(rsmd.getColumnLabel(i));
	                }
	                DefaultTableModel tbl=new DefaultTableModel(cols,0);
	                Vector row;
	                    //getting row
	                while(rs.next())
	                {
	                    row=new Vector();
	                    for(int i=1;i<=colsSize;i++)
	                        row.add(rs.getObject(i));
	                    tbl.addRow(row);
	                    row=null;
	                }
	                jtable1.setModel(tbl);
	            }
	            catch (SQLException ex)
	            {
	                ex.printStackTrace();
	            }

	    }

	}

	

