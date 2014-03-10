package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.MarkConfig;
import com.jeecms.cms.entity.main.MemberConfig;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.image.ImageScale;
import com.jeecms.common.image.ImageUtils;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.common.web.springmvc.RealPathResolver;
import com.jeecms.core.entity.Ftp;
import com.jeecms.core.manager.DbFileMng;
import com.jeecms.core.web.WebErrors;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
public class ImageUploadAct {
	private static final Logger log = LoggerFactory
			.getLogger(ImageUploadAct.class);
	/**
	 * 用户头像路径
	 */
	private static final String USER_IMG_PATH = "/user/images";
	/**
	 * 结果页
	 */
	private static final String RESULT_PAGE = "tpl.iframe_upload";
	/**
	 * 错误信息参数
	 */
	public static final String ERROR = "error";
	/**
	 * kindediter 头像上传
	 * @throws FileUploadException 
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/member/photo_upload_image.jspx")
	public void photoUpload(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "imgFile", required = true) CommonsMultipartFile imgFile) throws FileUploadException, IOException {
		//文件保存目录路径
		String savePath = request.getSession().getServletContext().getRealPath("/");
		
		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/user/";
		
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		
		//最大文件大小
		long maxSize = 2000000;
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter write=response.getWriter();
		if(!ServletFileUpload.isMultipartContent(request)){
			write.print(getError("请选择文件。"));
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			write.print(getError("上传目录不存在。"));
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			write.print(getError("上传目录没有写权限。"));
			return;
		}
		
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			write.print(getError("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += "user/"+dirName + "/";
		saveUrl += dirName + "/";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File pathFile = new File(savePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		File originFile = new File(savePath+"/origin");
		if (!originFile.exists()) {
			originFile.mkdirs();
		} 
		
		//上传文件处理
		if(imgFile!=null){
			FileItem item =imgFile.getFileItem();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > maxSize){
					write.print(getError("上传文件大小超过限制。"));
					return;
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					write.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return;
				} 
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				
				//上传文件是附加
				if(dirName.equals("file")){
					try {
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try{
						originFile = new File(savePath+"/origin", newFileName);
						item.write(originFile);
					}catch(Exception e){
						write.print(getError("上传文件失败。"));
						return;
					}
				}
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl+"/origin/"+newFileName);
				write.print(obj.toJSONString());
			}
		}	
	}
	
	/**
	 * kindediter 团队LOGO上传
	 * @throws FileUploadException 
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/member/team_photo_upload_image.jspx")
	public void teamPhotoUpload(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "imgFile", required = true) CommonsMultipartFile imgFile) throws FileUploadException, IOException {
		//文件保存目录路径
		String savePath = request.getSession().getServletContext().getRealPath("/");
		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/team/";
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		//最大文件大小
		long maxSize = 2000000;
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter write=response.getWriter();
		if(!ServletFileUpload.isMultipartContent(request)){
			write.print(getError("请选择文件。"));
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			write.print(getError("上传目录不存在。"));
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			write.print(getError("上传目录没有写权限。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			write.print(getError("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += "team/"+dirName + "/";
		saveUrl += dirName + "/";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File pathFile = new File(savePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		File originFile = new File(savePath+"/origin");
		if (!originFile.exists()) {
			originFile.mkdirs();
		} 
		
		//上传文件处理
		if(imgFile!=null){
			FileItem item =imgFile.getFileItem();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > maxSize){
					write.print(getError("上传文件大小超过限制。"));
					return;
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					write.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return;
				} 
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				
				//上传文件是附加
				if(dirName.equals("file")){
					try {
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try{
						originFile = new File(savePath+"/origin", newFileName);
						item.write(originFile);
						String orgfilepath=savePath+"origin/"+newFileName; 
						String newFilePath=savePath+newFileName;
						String thumbFilePath=savePath+"thumb/"+newFileName;
						String circleFilePath=savePath+"circle/"+newFileName;
						String cornerFilePath=savePath+"corner/"+newFileName;
						//判断当前路径是否存在 不存在则创建
						File thumbFile=new File(thumbFilePath);
						File circleFile=new File(circleFilePath);
						File cornerFile=new File(cornerFilePath);
						if (!thumbFile.getParentFile().exists()) {
							thumbFile.getParentFile().mkdirs();
						}
						if (!circleFile.getParentFile().exists()) {
							circleFile.getParentFile().mkdirs();
						}
						if (!cornerFile.getParentFile().exists()) {
							cornerFile.getParentFile().mkdirs();
						}
						
						InputStream is=new FileInputStream(originFile);
						BufferedImage image=ImageIO.read(is);
						int srcWidth = image.getWidth(null); //得到源图宽 
						int srcHeight = image.getHeight(null); //得到源图长
						//如果图片宽高大于260则将图片缩小
						if(srcWidth>260){
							Integer zoomWidth = 260;
							Integer zoomHeight = Math.round((float) 260 * srcHeight / srcWidth);
							Thumbnails.of(orgfilepath).size(zoomWidth,zoomHeight).toFile(newFilePath);
						}else{
							//不缩小写入缩略图文件夹里
							ImageIO.write(image,"jpg",new File(newFilePath));
						}
						//如果图片宽高大于60则将图片缩小
						if(srcWidth>60){
							Integer zoomWidth = 60;
							Integer zoomHeight = Math.round((float) 60 * srcHeight / srcWidth);
							Thumbnails.of(orgfilepath).size(zoomWidth,zoomHeight).toFile(thumbFilePath);
						}else{
							//不缩小写入缩略图文件夹里
							ImageIO.write(image,"jpg",new File(thumbFilePath));
						}
						
						//制作图片圆角以及圆形图片
						BufferedImage icon = ImageIO.read(new File(thumbFilePath));
						BufferedImage rounded = ImageUtils.makeRoundedCorner(icon, 20);
				        BufferedImage roundedA = ImageUtils.makeRoundedCorner(icon, 1000);
				        ImageIO.write(rounded, "png", new File(cornerFilePath));
				        ImageIO.write(roundedA, "png", new File(circleFilePath));
					}catch(Exception e){
						write.print(getError("上传文件失败。"));
						return;
					}
				}
				JSONObject obj = new JSONObject();
				obj.put("error",0);
				obj.put("url",saveUrl+"//"+newFileName);
				write.print(obj.toJSONString());
			}
		}	
	}
	/**
	 * kindediter 图片上传
	 * @throws FileUploadException 
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/kindeidter/o_upload_image.jspx")
	public void kindUpload(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "imgFile", required = true) CommonsMultipartFile imgFile) throws FileUploadException, IOException {
		//文件保存目录路径
		String savePath = request.getSession().getServletContext().getRealPath("/");

		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/upload/";

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		//最大文件大小
		long maxSize = 2000000;

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter write=response.getWriter();
		if(!ServletFileUpload.isMultipartContent(request)){
			write.print(getError("请选择文件。"));
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			write.print(getError("上传目录不存在。"));
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			write.print(getError("上传目录没有写权限。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			write.print(getError("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += "upload/"+dirName + "/";
		saveUrl += dirName + "/";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File pathFile = new File(savePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		File originFile = new File(savePath+"/origin");
		if (!originFile.exists()) {
			originFile.mkdirs();
		}
		File thumbFile=new File(savePath+"/thumb");
		if (!thumbFile.exists()) {
			thumbFile.mkdirs();
		}
		File dirFile = new File(saveUrl);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		//上传文件处理
		if(imgFile!=null){
			FileItem item =imgFile.getFileItem();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > maxSize){
					write.print(getError("上传文件大小超过限制。"));
					return;
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					write.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return;
				}
				

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				
				//上传文件是附加
				if(dirName.equals("file")){
					try {
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try{
						originFile = new File(savePath+"/origin", newFileName);
						item.write(originFile);
						//如果图片分辨率大于800则将图片进行裁剪
						InputStream is=new FileInputStream(originFile);
						BufferedImage image=ImageIO.read(is);
						int srcWidth = image.getWidth(null); //得到源图宽 
						int srcHeight = image.getHeight(null); //得到源图长
						
						//图片分辨率大于800
						if(srcWidth>800){
							File uploadedFile=new File(savePath, newFileName);
							int zoomWidth;			//缩放宽度
							int zoomHeight;			//缩放高度
							
							zoomWidth = 800;
							zoomHeight = Math.round((float) 800 * srcHeight / srcWidth);
							
							Thumbnails.of(savePath+"/origin/"+newFileName).size(zoomWidth, zoomHeight).toFile(savePath+"/"+newFileName);
							
							//生成墙缩略图
							zoomWidth = 226;
							zoomHeight = Math.round((float) 226 * srcHeight / srcWidth);
							Thumbnails.of(savePath+"/origin/"+newFileName).size(zoomWidth, zoomHeight).toFile(savePath+"/thumb/"+newFileName);
							
							//BufferedImage imgBuff=AverageImageScale.scaleImage(image, srcWidth,srcHeight, zoomWidth, zoomHeight);
							//将缩减的图片写入文件
							//ImageIO.write(imgBuff, fileExt, uploadedFile);
							//为上传图片添加水印
							markImage(uploadedFile,request);
							
						}else{
							File uploadedFile = new File(savePath, newFileName);
							//生成墙缩略图
							if(srcWidth>226){
								Thumbnails.of(savePath+"/origin/"+newFileName).size(226, Math.round((float) 226 * srcHeight / srcWidth)).toFile(savePath+"/thumb/"+newFileName);
							}else{
								Thumbnails.of(savePath+"/origin/"+newFileName).size(srcWidth,srcHeight).toFile(savePath+"/thumb/"+newFileName);
							}
							ImageIO.write(image,fileExt,uploadedFile);
							
							//为上传图片添加水印
							markImage(uploadedFile,request);
						}					
					}catch(Exception e){
						write.print(getError("上传文件失败。"));
						return;
					}
				}
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl+"/thumb/"+newFileName);
				write.print(obj.toJSONString());
			}
		}	
	}
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	//为上传文件添加水印
	private void markImage(File file,HttpServletRequest request){
		String markFilePath=request.getSession().getServletContext().getRealPath("/")+"/r/cms/watermark.png";
		File markFile=new File(markFilePath);
		try {
			Image image=ImageIO.read(file);
			int width=image.getWidth(null);
			int height=image.getHeight(null);
			BufferedImage buff=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g=buff.getGraphics();
			g.drawImage(image, 0, 0, width, height, null);
			
			//水印文件
			Image water=ImageIO.read(markFile);
			int waterWidth=water.getWidth(null);
			int waterHeight=water.getHeight(null);
			g.drawImage(water, 10, 10, waterWidth, waterHeight, null);
			//水印文件结束  
			g.dispose();  
			FileOutputStream out = new FileOutputStream(file);  
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(buff);
			out.close();  

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private WebErrors validate(String filename, MultipartFile file,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (file == null) {
			errors.addErrorCode("imageupload.error.noFileToUpload");
			return errors;
		}
		if (StringUtils.isBlank(filename)) {
			filename = file.getOriginalFilename();
		}
		String ext = FilenameUtils.getExtension(filename);
		if (!ImageUtils.isValidImageExt(ext)) {
			errors.addErrorCode("imageupload.error.notSupportExt", ext);
			return errors;
		}
		try {
			if (!ImageUtils.isImage(file.getInputStream())) {
				errors.addErrorCode("imageupload.error.notImage", ext);
				return errors;
			}
		} catch (IOException e) {
			log.error("image upload error", e);
			errors.addErrorCode("imageupload.error.ioError", ext);
			return errors;
		}
		return errors;
	}

	private File mark(MultipartFile file, MarkConfig conf) throws Exception {
		String path = System.getProperty("java.io.tmpdir");
		File tempFile = new File(path, String.valueOf(System
				.currentTimeMillis()));
		file.transferTo(tempFile);
		boolean imgMark = !StringUtils.isBlank(conf.getImagePath());
		if (imgMark) {
			File markImg = new File(realPathResolver.get(conf.getImagePath()));
			imageScale.imageMark(tempFile, tempFile, conf.getMinWidth(), conf
					.getMinHeight(), conf.getPos(), conf.getOffsetX(), conf
					.getOffsetY(), markImg);
		} else {
			imageScale.imageMark(tempFile, tempFile, conf.getMinWidth(), conf
					.getMinHeight(), conf.getPos(), conf.getOffsetX(), conf
					.getOffsetY(), conf.getContent(), Color.decode(conf
					.getColor()), conf.getSize(), conf.getAlpha());
		}
		return tempFile;
	}

	private FileRepository fileRepository;
	private DbFileMng dbFileMng;
	private ImageScale imageScale;
	private RealPathResolver realPathResolver;

	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	@Autowired
	public void setDbFileMng(DbFileMng dbFileMng) {
		this.dbFileMng = dbFileMng;
	}

	@Autowired
	public void setImageScale(ImageScale imageScale) {
		this.imageScale = imageScale;
	}

	@Autowired
	public void setRealPathResolver(RealPathResolver realPathResolver) {
		this.realPathResolver = realPathResolver;
	}
}