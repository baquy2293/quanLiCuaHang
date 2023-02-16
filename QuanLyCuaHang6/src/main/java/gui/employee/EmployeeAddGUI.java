package gui.employee;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mau.SubDialog;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EmployeeAddGUI extends SubDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmployeeAddGUI dialog = new EmployeeAddGUI(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmployeeAddGUI(JFrame owner) {
		super(owner);
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setBounds(100, 100, 556, 502);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Thêm Nhân Viên");
			lblNewLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(10, 11, 520, 30);
			contentPanel.add(lblNewLabel);
		}
	}

}
