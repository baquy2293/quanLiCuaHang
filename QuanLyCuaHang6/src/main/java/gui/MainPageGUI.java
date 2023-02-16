package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class MainPageGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private MainPageGUI thisMainPage = this;
	private JPanel contentPane;
	private JLabel logout;
	
	private DefaultListModel<ControlItem> controlModel;
	private JTabbedPane tabbedPane;

	final static Color blackTextColor = Color.decode("#000000");
	final static Color blackTextColor2 = Color.decode("#444444");
	final static Color whiteTextColor = Color.decode("#d3dce0");
	final static Color lightBackgroundColor = Color.decode("#CDCDCD");
	final static Color darkBackgroundColor = new Color(45, 45, 44);
	final static Color selectedItemColor = Color.decode("#90e0ef");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageGUI frame = new MainPageGUI();
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
	public MainPageGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 10, 1200, 800);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(screenSize.getWidth() - this.getWidth())/2,
				(int)(screenSize.getHeight() - this.getHeight()-40)/2); //taskbar 20
		contentPane = new JPanel();
		this.setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		////////////////////////////////////////////////////////////////////////////////////////////
		int headerHeight = 48;
		JPanel header = new JPanel();
		BoxLayout headerLayout = new BoxLayout(header, BoxLayout.X_AXIS);
		header.setLayout(headerLayout);
		header.setBackground(MainPageGUI.darkBackgroundColor);

		JLabel logo = new JLabel();
		logo.setIcon(Utils.getImageIcon("src/main/resources/logo/shop.png", 42, 42));
		logo.setBorder(new EmptyBorder(4, 20, 4, 16));
		header.add(logo);

		final JLabel userInfo = new JLabel("Shop");
		userInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		userInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userInfo.setForeground(MainPageGUI.whiteTextColor);
		Utils.decorateComponent(userInfo, 20, 120, headerHeight);
		header.add(userInfo);
		header.add(Utils.spacing(960, 48));
		logout = new JLabel();
		logout.setIcon(Utils.getImageIcon("src/main/resources/logo/logout.png", 40, 40));
		header.add(logout);
		contentPane.add(header, BorderLayout.NORTH);
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(Color.BLACK);

		// Dinh nghia model
		controlModel = new DefaultListModel<ControlItem>();
		JList<ControlItem> controlList = new JList<ControlItem>(controlModel);
		controlList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(controlList.getSelectedValue()!=null) {
					String title = controlList.getSelectedValue().getTitle();
					int tabCount = tabbedPane.getTabCount();
					for (int i=0; i < tabCount; i++) {
					  String tabTitle = tabbedPane.getTitleAt(i);
					  if (tabTitle.equals(title)) {
					  	tabbedPane.setSelectedIndex(i);
					  	 return;
					  }
					}
					if(title.equals("Thoát")) {
						  thisMainPage.dispose();
						  System.exit(0);
					 }
				}
				 
			}
		});
		controlList.setBackground(darkBackgroundColor);
		controlList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		controlList.setCellRenderer(new ControlItemGUI());
		JScrollPane listItemScrollPane = new JScrollPane(controlList);
		leftPanel.add(listItemScrollPane);
		contentPane.add(leftPanel, BorderLayout.WEST);

		JPanel centerPane = new JPanel();
		contentPane.add(centerPane, BorderLayout.CENTER);
		centerPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 980, 700);
		
		tabbedPane.setUI(new BasicTabbedPaneUI() {
		    @Override
		    protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
		        return 0;
		    }
		});
		centerPane.add(tabbedPane);
	}
	
	public void setLogoutMouseClickedEvent(MouseAdapter ma) {
		logout.addMouseListener(ma);
	}
	
	public void addContent(JPanel p, ControlItem c, int pos) {
		if(p!=null) {
			tabbedPane.add(c.getTitle() ,p);
		}
		controlModel.add(pos ,c);
	}
	
	public void clear() {
		tabbedPane.removeAll();
		controlModel.removeAllElements();
	}
	
	/*
	 * JList khác JTable là 
	 * JTable mình để giao diện mặc định chỉnh mỗi độ cao bằng phương thức có sẵn cho Item
	 * Còn JList mình custom giao diện cho Item là 1 Pane với nhiều thành phần bên trong
	 * ControlItem để là kiểu cho DefaultListModel<ControlItem> và JList<ControlItem> controlList
	 */
	public class ControlItem { // addElement(new LeftMenuItem(JLabel))
		private String title;
		private ImageIcon icon;
		private String description;
		
		private boolean hasSeperator = false;

		public ControlItem(String title, ImageIcon img) {
			this.title = title;
			this.icon = img;
			this.description = "";
		}

		public void setSeparator(boolean hasSeperator) {
			this.hasSeperator = hasSeperator;
		}
		
		public String getTitle() {
			return this.title;
		}
	}

	/*
	 * Custom giao diện cho JList
	 * controlList.setCellRenderer(new ControlItemGUI());
	 */
	private class ControlItemGUI extends JPanel implements ListCellRenderer<ControlItem> {
		private static final long serialVersionUID = 1L;
		private JLabel icon = new JLabel();
		private JLabel title = new JLabel();
		private JLabel description = new JLabel();
		
		public ControlItemGUI() {
			setLayout(new BorderLayout(5, 5));
			add(icon, BorderLayout.WEST);
			add(title, BorderLayout.CENTER);
			add(description, BorderLayout.SOUTH);

			icon.setBorder(new EmptyBorder(8, 16, 8, 6));

			title.setBorder(new EmptyBorder(0, 0, 0, 20));
			title.setFont(new Font("SansSerif", Font.PLAIN, 18));

			description.setBorder(new EmptyBorder(0, 0, 0, 20));
			description.setFont(new Font("SansSerif", Font.PLAIN, 16));

		}
		
		//Tự động được gọi ngầm định
		//Đây là hàm để render dữ liệu kiểu ControlItem lên giao diện
		public Component getListCellRendererComponent(JList<? extends ControlItem> list, ControlItem value, int index,
				boolean isSelected, boolean cellHasFocus) {
			icon.setIcon(value.icon);
			title.setText(value.title);
			description.setText(value.description);
			if (value.hasSeperator == true) {
				this.setBorder(new MatteBorder(0, 0, 1, 0, Color.YELLOW));
			} else {
				this.setBorder(new EmptyBorder(0, 0, 1, 0));
			}
			// when select item
			if (isSelected) {
				title.setForeground(blackTextColor2);
				description.setForeground(blackTextColor2);
				icon.setBackground(selectedItemColor);
				title.setBackground(selectedItemColor);
				description.setBackground(selectedItemColor);
				setBackground(selectedItemColor);
			} else { // when don't select
				icon.setBackground(darkBackgroundColor);
				title.setBackground(darkBackgroundColor);
				description.setBackground(darkBackgroundColor);
				title.setForeground(whiteTextColor);
				description.setForeground(whiteTextColor);
				setBackground(darkBackgroundColor);
			}
			return this;
		}
	}
}
