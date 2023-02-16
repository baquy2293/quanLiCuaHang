package mau;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import subclass.CheckingFile;
import subclass.FileTypeFilter;

public class SubDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	protected final JPanel pane = new JPanel();
	protected int fontSize = 16;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame j = new JFrame();
			SubDialog dialog = new SubDialog(j);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SubDialog(JFrame owner) {
		super(owner, "", true);
		setSize(600, 600);
//		setLocation(owner.getX() + (owner.getWidth() - this.getWidth())/2, 
//				owner.getY() + (owner.getHeight()-this.getHeight())/2);
		setLocation (600, 200);
		getContentPane().setLayout(new BorderLayout());
		pane.setLayout(null);
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pane, BorderLayout.CENTER);
	}
	
	protected JLabel createDefaultHeader(String title, int x, int y) { //200 va 10
		JLabel lb = new JLabel(title);
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setFont(new Font("SansSerif", Font.BOLD, 20));
		lb.setBounds(x, y, 200, 30);
		return lb;
	}
	
	protected JLabel createDefaultJLabel(int x, int y, int width, int height) {// (width-height) = (120-30)
		JLabel lb = new JLabel();
		lb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		lb.setBounds(x, y, width, height);
		return lb;
	}
	
	protected JLabel createDefaultJLabel(String text, int x, int y, int width, int height) {// (width-height) = (120-30)
		JLabel lb = new JLabel(text);
		lb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		lb.setBounds(x, y, width, height);
		return lb;
	}
	
	protected JLabel createDefaultLabelImage(int x, int y, int width, int height) {
		JLabel lb = new JLabel();
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setBounds(x, y, width, height);
		lb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		return lb;
	}
	
	protected JTextField createDefaultJTextField(int x, int y, int width, int height) { //(width-height) = (300-30)
		JTextField tf = new JTextField();
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		tf.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		tf.setBounds(x, y, width, height);
		tf.setColumns(10);
		tf.setHorizontalAlignment(JTextField.CENTER);
		return tf;
	}
	
	protected JButton createDefaultJButton(String text, int x, int y, int width, int height) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("SansSerif", Font.PLAIN | Font.ITALIC, fontSize));
		btn.setBounds(x, y, width, height);
		return btn;
	}
	
	protected void decorateJComboBox(@SuppressWarnings("rawtypes") JComboBox cb, int x, int y, int width, int height) {
		cb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		cb.setBounds(x, y, width, height);
	}
	
	protected JRadioButton createDefaultJRadioButton(String text, int x, int y, int width, int height, boolean isSelected) {
		JRadioButton jrb = new JRadioButton(text, isSelected);
		jrb.setHorizontalAlignment(SwingConstants.CENTER);
		jrb.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		jrb.setBounds(x, y, width, height);
		return jrb;
	}
	
	protected JTextArea createDefaultJTextArea(int x, int y, int width, int height) {
		JTextArea jta = new JTextArea();
		jta.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setBounds(x, y, width, height);		
		return jta;
	}
	
	protected JScrollPane createDefaultJScrollPane(JTextArea jta, int x, int y, int width, int height) {
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setBounds(x, y, width, height);
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
	
	protected String getFileChooser(SubDialog jsd, List<FileTypeFilter> ftf, CheckingFile checkFunc) {
		JFileChooser chooseImageDialog = new JFileChooser();
		chooseImageDialog.setDialogTitle("Hãy chọn hình ảnh");
		chooseImageDialog.setMultiSelectionEnabled(false);
		//chooseImageDialog.setFileFilter(new FileTypeFilter(".png", "PNG"));
		ftf.forEach(f ->{
			chooseImageDialog.setFileFilter(f);
		});
		int returnVal = chooseImageDialog.showOpenDialog(jsd);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			java.io.File file = chooseImageDialog.getSelectedFile();
			if (checkFunc.check(file)) {
				return file.getAbsolutePath();
			} else {
				JOptionPane.showMessageDialog(jsd, "File không hợp lệ!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// statusLabel.setText("Open command cancelled by user.");
		}
		return null;
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
