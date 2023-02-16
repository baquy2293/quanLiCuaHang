package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Utils {
	private final static String HEXVALUES ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public Utils() {

	}
	
//	public static void main(String args[]) {
//		System.out.println(Utils.random(12));
//	}
	
	public static String random(int doDai) {
		Random r = new Random();
		char[] result = new char[doDai];
		for(int i=0; i < doDai; i++) {
			result[i] = HEXVALUES.charAt(r.nextInt(HEXVALUES.length()));
		}
		return String.valueOf(result);
	}
	
	public static String formatNumber(float n) {
		return String.format("%1$,.2f", n);
	}
	
	public static void setSize(Component c, int minWidth, int maxWidth, int minHeight, int maxHeight) {
		c.setMinimumSize(new Dimension(minWidth, minHeight));
		c.setMaximumSize(new Dimension(maxWidth, maxHeight));
	}

	public static JLabel spacing(int width, int height) {
		JLabel spacing = new JLabel();
		spacing.setMinimumSize(new Dimension(0, height));
		spacing.setMaximumSize(new Dimension(width, height));

		return spacing;
	}

	public static void decorateComponent(JTextField tf, int fontsize, int width, int height, int lrPadding) {
		tf.setFont(new Font("SansSerif", Font.PLAIN, fontsize));
		tf.setMaximumSize(new Dimension(width, height));
	}

	public static void decorateComponent(JLabel lb, int fontsize, int width, int height) {
		lb.setFont(new Font("SansSerif", Font.PLAIN, fontsize));
		lb.setMaximumSize(new Dimension(width, height));
	}

	public static ImageIcon getImageIcon(String src, int width, int height) {
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
	
	private static Dimension getScaledDimension(Dimension imageSize, Dimension boundary) {

	    double widthRatio = boundary.getWidth() / imageSize.getWidth();
	    double heightRatio = boundary.getHeight() / imageSize.getHeight();
	    double ratio = Math.min(widthRatio, heightRatio);

	    return new Dimension((int) (imageSize.width  * ratio),
	                         (int) (imageSize.height * ratio));
	}
	
	// convert BufferedImage to byte[]
	public static byte[] toByteArray(BufferedImage bi, String format) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, format, baos);
		byte[] bytes = baos.toByteArray();
		return bytes;

	}
	
	// convert byte[] to BufferedImage
	public static BufferedImage toBufferedImage(byte[] bytes) throws IOException {
		InputStream bis = new ByteArrayInputStream(bytes);
	    BufferedImage bi = ImageIO.read(bis);
		return bi;
	}
	
	public static BufferedImage toBufferedImage(Icon icon) {
		BufferedImage bi = new BufferedImage(
			    icon.getIconWidth(),
			    icon.getIconHeight(),
			    BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			// paint the Icon to the BufferedImage.
			icon.paintIcon(null, g, 0,0);
			g.dispose();
			return bi;
	}
	
	public static byte[] toByte(String imgSrc) {
		byte[] imgByte = null;
		try {
			BufferedImage img = ImageIO.read(new File(imgSrc));
			imgByte = toByteArray(img, "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgByte;
	}
	
	public static boolean isImage(File file) {
		try {
			if (ImageIO.read(file) != null) {
				return true;
			}
		} catch (IOException e) {
			System.out.println("Lỗi không tìm thấy file image trong phương thức isImage()");
		}
		return false;
	}
	//ImageIcon thì lấy trực tiếp từ UrlString hoặc BufferedImage
	public static Image getImage(String src, int width, int height) {
		try {
			Image img = ImageIO.read(new File(src)).getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage toBufferedImage(Image image){
		BufferedImage buffered = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		buffered.getGraphics().drawImage(image, 0, 0 , null);
		return buffered;
	}
	
	public static String encodeToString(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static byte[] decodeToByteArray(String str) {
			return Base64.getDecoder().decode(str);
	}
}
