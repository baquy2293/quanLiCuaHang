package gui.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import gui.Utils;

public class LoginGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame thisFrame;
	private JPanel contentPane;
	private JTextField user;
	private JLabel icon2;
	private JLabel icon1;
	private JPasswordField password;
	private JButton logIn;
	private JButton signUp;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(960, 800);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(screenSize.getWidth() - this.getWidth())/2,
				(int)(screenSize.getHeight() - this.getHeight()-40)/2); //taskbar 20
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#eeddaf"));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 10, 480, 727);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel poster = new JLabel("");
		poster.setHorizontalAlignment(SwingConstants.CENTER);
		poster.setBounds(39, 30, 400, 654);
		poster.setIcon(Utils.getImageIcon("src/main/resources/logo/tikibar.jpg", 400, 628));
		panel.add(poster);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(514, 10, 420, 727);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(20, 320, 380, 320);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		icon1 = new JLabel("");
		icon1.setHorizontalAlignment(SwingConstants.CENTER);
		icon1.setBounds(30, 40, 64, 32);
		icon1.setIcon(Utils.getImageIcon("src/main/resources/logo/login_user.png", 30, 30));
		icon1.setBorder(new MatteBorder(new Insets(0,0,1,0), new Color(0,0,0)));
		panel_3.add(icon1);
		
		icon2 = new JLabel("");
		icon2.setHorizontalAlignment(SwingConstants.CENTER);
		icon2.setBounds(30, 84, 64, 32);
		icon2.setIcon(Utils.getImageIcon("src/main/resources/logo/login_password.png", 30, 30));
		icon2.setBorder(new MatteBorder(new Insets(0,0,1,0), new Color(0,0,0)));
		panel_3.add(icon2);
		
		JLabel forgetPassword = new JLabel("Quên mật khẩu ? ");
		forgetPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		forgetPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		forgetPassword.setBounds(224, 127, 120, 20);
		panel_3.add(forgetPassword);
		
		logIn = new JButton("Đăng Nhập");
		logIn.setOpaque(false);
		logIn.setContentAreaFilled(false);
		logIn.setFocusPainted(false);
		logIn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		logIn.setBounds(30, 160, 314, 39);
		panel_3.add(logIn);
		
		signUp = new JButton("Đăng Ký");
		signUp.setOpaque(false);
		signUp.setContentAreaFilled(false);
		signUp.setFocusPainted(false);
		//signUp.setBorderPainted(false);
		signUp.setFont(new Font("SansSerif", Font.PLAIN, 16));
		signUp.setBounds(30, 210, 314, 32);
		panel_3.add(signUp);
		
		JLabel terms = new JLabel("Điều khoản sử dụng.");
		terms.setHorizontalAlignment(SwingConstants.LEFT);
		terms.setFont(new Font("SansSerif", Font.PLAIN, 14));
		terms.setBounds(32, 285, 160, 24);
		panel_3.add(terms);
		
		user = new JTextField();
		user.setOpaque(false);
		user.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		user.setBorder(new MatteBorder(new Insets(0,0,1,0), new Color(0,0,0)));
		user.setBounds(94, 40, 250, 32);
		panel_3.add(user);
		user.setColumns(10);
		
		password = new JPasswordField();
		password.setOpaque(false);
		password.setFont(new Font("SansSerif", Font.BOLD, 18));
		password.setBorder(new MatteBorder(new Insets(0,0,1,0), new Color(0,0,0)));
		password.setBounds(94, 84, 250, 32);
		panel_3.add(password);
		
		JLabel logo = new JLabel("");
		logo.setBounds(120, 80, 200, 200);
		logo.setIcon(Utils.getImageIcon("src/main/resources/logo/shop.png", 200, 200));
		panel_1.add(logo);
		
		/*-------------------------------------Event ------------------------------*/
		signUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(thisFrame, "Chưa làm! Sắp thi đến nơi rồi nên thôi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	public void addEventForLoginBtn(ActionListener listener) {
		logIn.addActionListener(listener);
	}
	
	public void clear() {
		password.setText("");
	}
	
	public Account getResponse() {
		return new Account(user.getText(), password.getPassword());
	}
}
