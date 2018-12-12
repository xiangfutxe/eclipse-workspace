package com.ssm.utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;


/**
* <p>Title: PictureMerge</p>
* <p>Description: 图片合并</p>
* <p>Company: DINGGE</p>
* @author    FANQIBU
* @date       2017年12月1日
*/
public class ImageMergeUtil {
	private static Logger LOGGER = Logger.getLogger(ImageMergeUtil.class);
	
	public static void combineImage(String srcDir,String destFile) throws Exception{
		//创建File对象，并判断是否是一个目录
		File dir = new File(srcDir);
		if(!(dir.exists() && dir.isDirectory())){
			System.out.println("输入的文件夹路径有误！");
			return ;
		}
		File[] fileList = dir.listFiles();//获取该文件夹下的所有图片
		
		//创建一个BufferedImage数据
		BufferedImage[] imageArray = new BufferedImage[fileList.length];
		//初始化
		for (int i = 0; i < fileList.length; i++) {
		imageArray[i] = ImageIO.read(fileList[i]);
		}

		//用第一张图片作为底图，在他上面继续合成其他图片
		Graphics2D graphics2d = imageArray[0].createGraphics();
		
		//获取第一张图片的width，和height
		int width = imageArray[0].getWidth();
		int height = imageArray[0].getHeight();
		//遍历，把剩下的其他图片都画在底图上(也就是从第二张图片开始)
		for (int i = 1; i < imageArray.length; i++) {
		graphics2d.drawImage(imageArray[i], 0, 0, width, height, null);
		}

		//获取文件后缀
		String suffix = destFile.substring(destFile.lastIndexOf(".")+1);
		//输出画好的图片
		ImageIO.write(imageArray[0], suffix, new File(destFile));
		graphics2d.dispose();// 释放图形上下文使用的系统资源
	}
	
	    public static void main(String[] args) throws Exception {
	    	String  destFile="upload/qr/gQHs8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyNS11eHhFcGpjTWwxMDAwMDAwM2EAAgRLwPBbAwQAAAAA.jpg";
	    	String  srcDir= "wap/images/user_code.jpg";
	    	combineImage(srcDir, destFile);
	    	
	    }
	}

