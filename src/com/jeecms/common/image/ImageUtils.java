package com.jeecms.common.image;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Locale;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 * 图片辅助类
 * 
 * @author liufang
 * 
 */
public abstract class ImageUtils {
	/**
	 * 图片的后缀
	 */
	public static final String[] IMAGE_EXT = new String[] { "jpg", "jpeg",
			"gif", "png", "bmp" };

	/**
	 * 是否是图片
	 * 
	 * @param ext
	 * @return "jpg", "jpeg", "gif", "png", "bmp" 为文件后缀名者为图片
	 */
	public static boolean isValidImageExt(String ext) {
		ext = ext.toLowerCase(Locale.ENGLISH);
		for (String s : IMAGE_EXT) {
			if (s.equalsIgnoreCase(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the underlying input stream contains an image.
	 * 
	 * @param in
	 *            input stream of an image
	 * @return <code>true</code> if the underlying input stream contains an
	 *         image, else <code>false</code>
	 */
	public static boolean isImage(final InputStream in) {
		ImageInfo ii = new ImageInfo();
		ii.setInput(in);
		return ii.check();
	}

	/**
	 * 获得水印位置
	 * 
	 * @param width
	 *            原图宽度
	 * @param height
	 *            原图高度
	 * @param p
	 *            水印位置 1-5，其他值为随机。1：左上；2：右上；3：左下；4：右下；5：中央。
	 * @param offsetx
	 *            水平偏移。
	 * @param offsety
	 *            垂直偏移。
	 * @return 水印位置
	 */
	public static Position markPosition(int width, int height, int p,
			int offsetx, int offsety) {
		if (p < 1 || p > 5) {
			p = (int) (Math.random() * 5) + 1;
		}
		int x, y;
		switch (p) {
		// 左上
		case 1:
			x = offsetx;
			y = offsety;
			break;
		// 右上
		case 2:
			x = width + offsetx;
			y = offsety;
			break;
		// 左下
		case 3:
			x = offsetx;
			y = height + offsety;
			break;
		// 右下
		case 4:
			x = width + offsetx;
			y = height + offsety;
			break;
		// 中央
		case 5:
			x = (width / 2) + offsetx;
			y = (height / 2) + offsety;
			break;
		default:
			throw new RuntimeException("never reach ...");
		}
		return new Position(x, y);
	}

	/**
	 * 水印位置
	 * 
	 * 包含左边偏移量，右边偏移量。
	 * 
	 * @author liufang
	 * 
	 */
	public static class Position {
		private int x;
		private int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
	
	/**
	 * 图片裁剪
	 * 
	 * @author zyh
	 * @throws IOException 
	 * 
	 */
	public static BufferedImage imgCut(String filepath,String newFilePath,
			Integer x,Integer y,Integer width,Integer height) throws IOException{
		FileInputStream is = null ;
	    ImageInputStream iis = null ;
	    try {
		    // 读取图片文件
		    is = new FileInputStream(filepath);
		    /*
		    * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
		    * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
		    *（例如 "jpeg" 或 "tiff"）等 。
		    */
		    Iterator < ImageReader > it = ImageIO.getImageReadersByFormatName("jpg");
		    ImageReader reader = it.next();
		    // 获取图片流
		    iis = ImageIO.createImageInputStream(is);
		    /*
		    * <p>iis:读取源。true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
		    * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
		    * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
		    */
		    reader.setInput(iis,true);
		    /*
		    * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
		    * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
		    * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
		    * ImageReadParam 的实例。
		    */
		    ImageReadParam param = reader.getDefaultReadParam();
		    /*
		    * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
		    * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
		    */
		    Rectangle rect = new Rectangle(x, y, width, height);
		    // 提供一个 BufferedImage，将其用作解码像素数据的目标。
		    param.setSourceRegion(rect);
	
		    /*
		    * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
		    * 它作为一个完整的 BufferedImage 返回。
		    */
		    BufferedImage bi = reader.read( 0 ,param);
		    // 保存新图片
		    ImageIO.write(bi,"jpg",new File(newFilePath));
		    return bi;
	    }finally {
		    if (is!=null)
		    	is.close();
		    if (iis != null)
		    	iis.close();
	    }
	}
	
	public static void main(String[] args) {

	}
	
	/**
	 * 图片圆角处理
	 * 
	 * @author zyh
	 * 
	 */
	public static BufferedImage makeRoundedCorner(BufferedImage image,
            int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,
                cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
