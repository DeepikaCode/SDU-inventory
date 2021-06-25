import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField jtfLoginId;
	private JTextField jtfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Shiv Dal MIl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setIcon(new ImageIcon("D:\\Eclipse prj\\Dal Mill Cold Store Inventory Mgmt\\resources\\f47d4442953e47cb959b039ffe7fafdc.png"));
		lblNewLabel.setForeground(new Color(204, 153, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dubai Medium", Font.BOLD, 28));
		lblNewLabel.setBounds(177, 10, 253, 191);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login ID");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_1.setBounds(83, 211, 111, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(83, 271, 111, 33);
		contentPane.add(lblNewLabel_1_1);
		
		jtfLoginId = new JTextField();
		jtfLoginId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jtfLoginId.setBounds(293, 211, 267, 33);
		contentPane.add(jtfLoginId);
		jtfLoginId.setColumns(10);
		
		jtfPassword = new JTextField();
		jtfPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jtfPassword.setColumns(10);
		jtfPassword.setBounds(293, 271, 267, 33);
		contentPane.add(jtfPassword);
		
		JButton jbtnSignIn = new JButton("Sign In");
		jbtnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jbtnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jbtnSignIn.setBounds(234, 348, 123, 37);
		contentPane.add(jbtnSignIn);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnForgotPassword.setBounds(215, 404, 173, 37);
		contentPane.add(btnForgotPassword);
		
	}
}
