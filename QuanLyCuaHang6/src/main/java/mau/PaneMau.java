package mau;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class PaneMau extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected JPanel pane;
	protected int fontSize = 16;
	protected String defaultImage="src/main/resources/img/DefaultImage.png" ;
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setSize(980, 640);
		PaneMau p = new PaneMau();
		jf.getContentPane().add(p);
		jf.setVisible(true);
	}
	/**
	 * Create the panel.
	 */
	public PaneMau() {
		setSize(new Dimension(980, 700));
		setLayout(new BorderLayout(0, 0));
		pane = new JPanel();
		add(pane, BorderLayout.CENTER);
		pane.setLayout(null);
	}
	
	public JLabel createDefaultJLabel(int x, int y) {
		JLabel jlb = new JLabel();
		jlb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		jlb.setBounds(x, y, 130, 30);
		return jlb;
	}
	
	protected JLabel createDefaultLabelImage(int x, int y, int width, int height) {
		JLabel lb = new JLabel();
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setBounds(x, y, width, height);
		lb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		lb.setIcon(getImageIcon(defaultImage, width, height));
		return lb;
	}
	
	public JTextField createDefaultJTextField(int x, int y) {
		JTextField jtf = new JTextField();
		jtf.setHorizontalAlignment(SwingConstants.LEFT);
		jtf.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		jtf.setBounds(x, y, 400, 30);
		jtf.setColumns(10);
		return jtf;
	}
	
	public JButton createDefaultJButton(int x, int y) {
		JButton jb = new JButton("Chọn Ảnh");
		jb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		jb.setBounds(x, y, 102, 30);
		return jb;
	}
	
	public void decorateComboBox(@SuppressWarnings("rawtypes") JComboBox cb) {
		cb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		cb.setSize(200, 30);
	}
	
	public JTextArea createDefaultJTextArea(int x, int y, int width, int height) {
		JTextArea jta = new JTextArea();
		jta.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setBorder(new EmptyBorder(2, 4, 2, 4));
		jta.setBounds(x, y, width-4, height-8);
		return jta;
	}
	
	public JScrollPane createDefaultJScrollPane(JTextArea jta) {
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setBounds(jta.getX(), jta.getY(), jta.getWidth(), jta.getHeight());
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		return jsp;
	}
	
	protected JTable createDefaultJTable(int x, int y, int width, int height) {
		JTable jt = new JTable();
		jt.setBounds(x, y, width, height);
		jt.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jt.setRowHeight(28);
		JTableHeader jtheader = jt.getTableHeader();
		jtheader.setFont(new Font("SansSerif", Font.BOLD, 18));
		jtheader.setBackground(Color.yellow);
		jt.setTableHeader(jtheader);
		return jt;
	}
	
	protected JScrollPane createDefaultJScrollPane(JTable jt ,int x, int y, int width, int height) {
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(x, y, width, height);
		return jsp;
	}
	
	protected ImageIcon getImageIcon(String src, int width, int height) {
		try {
			BufferedImage bimg =  ImageIO.read(new File(src));
			Dimension newSize = getScaledDimension(new Dimension(bimg.getWidth(), bimg.getHeight()),
													new Dimension(width, height));
			Image img = bimg.getScaledInstance(newSize.width, newSize.height, Image.SCALE_SMOOTH);
			return new ImageIcon(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ImageIcon();
	}
	
	protected static Dimension getScaledDimension(Dimension imageSize, Dimension boundary) {

	    double widthRatio = boundary.getWidth() / imageSize.getWidth();
	    double heightRatio = boundary.getHeight() / imageSize.getHeight();
	    double ratio = Math.min(widthRatio, heightRatio);

	    return new Dimension((int) (imageSize.width  * ratio),
	                         (int) (imageSize.height * ratio));
	}
}
