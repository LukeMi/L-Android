package com.lukemi.android.common.util;

import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SDCard工具类封装了常用的操作SDCard的方法 Enviroment
 * 
 * @author Jimmy
 *
 */
public class SDCardUtils {
	/**
	 * 1.判断SDCard挂载的情况
	 * 
	 * @return true 说明挂载成功 ,false则失败
	 */
	public static boolean isSDCardMounted() {
		// 挂载外存储设备成功的常量 (挂载成功,挂载以后只能读取,没有挂载等等)
		String state = Environment.MEDIA_MOUNTED;
        // 字符串判断相等千万不能用==
        return Environment.getExternalStorageState().equals(state);
    }

	/**
	 * 获取SDCard的根路径
	 * 
	 * @return file 文件路径
	 */
	public static File getSDCardRootFilePath() {
		if (isSDCardMounted()) { // 挂载成功以后获取根路径
			return Environment.getExternalStorageDirectory();
		}
		return null;
	}

	/**
	 * 获取SDCard的根路径
	 * 
	 * @return string 文件字符串路径
	 */
	public static String getSDCardRootStringPath() {
		if (isSDCardMounted()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}

	/**
	 * 查看SDCard的总的大小 StatFs
	 * 
	 * @return long 默认单位是MB
	 */
	public static long getSDCardTotalSize() {
		// 填写的路径就是SDCard的根路径
		long size = 0;
		long count = 0;
		if (getSDCardRootStringPath() != null) {
			StatFs fs = new StatFs(getSDCardRootStringPath());
			// 获取long类型的放回值,因为int值可能溢出
			if (VERSION.SDK_INT >= 18) { // 18以上的版本调用这个方法
				size = fs.getBlockSizeLong(); // 18以后这方法才有效果
				count = fs.getBlockCountLong();
			} else {
				size = fs.getBlockSize();
				count = fs.getBlockCount();
			}
			// size * count返回的单位 B -> KB -> MB ->GB
		}
		return size * count / 1024 / 1024;
	}

	
	/**
	 * 查看SDCard可用空间的大小
	 *  
	 * @return long 默认单位是MB
	 */
	public static long getSDCardAvaliableSize() {
		// 填写的路径就是SDCard的根路径
		long size = 0;
		long count = 0;
		if (getSDCardRootStringPath() != null) {
			StatFs fs = new StatFs(getSDCardRootStringPath());

			// 获取long类型的放回值,因为int值可能溢出
			if (VERSION.SDK_INT >= 18) { // 18以上的版本调用这个方法
				size = fs.getBlockSizeLong(); // 18以后这方法才有效果
				count = fs.getAvailableBlocksLong();
			} else {
				size = fs.getBlockSize();
				count = fs.getAvailableBlocks();
			}
			// size * count返回的单位 B -> KB -> MB ->GB
		}
		return size * count / 1024 / 1024;
	}

	/**
	 * 写入文件到SDCard中 保存到根路径下
	 * 
	 * @param dir  	存放SDCard根目录下定义的自定目录
	 * @param fileName   文件实际的名字
	 * @param data 写入的数据 byte[] 通用性比较强
	 */
	public static void write2SDCard(String dirPath, String fileName, byte[] data) {
		/**
		 * 构造方法 第一个参数:目录名称 第二个参数:文件名字
		 */
		FileOutputStream fos = null;
		File fileDir = new File(getSDCardRootStringPath() + File.separator + dirPath);
		if (!fileDir.exists()) {
			// 创建目录
			fileDir.mkdirs();
		}
		//第一个参数:存在的目录 /aaa/bbb/ccc
		//第二个参数:最后一层目录中
		File filePath = new File(fileDir, fileName);
		try {
			// 实际填写的参数就是File
			fos = new FileOutputStream(filePath);
			fos.write(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从SDCard中读取文件
	 * 
	 * @param 填写完整的路径就可以了,要么读取到,要么读取失败
	 * @return data byte[] null表示读取失败
	 */
	public static byte[] readFromSDCard(String filePath) {
		File file = new File(getSDCardRootStringPath() + File.separator + filePath);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024 * 8];
			int len = 0;
			baos = new ByteArrayOutputStream();
			while ((len = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (baos != null)
					baos.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * SDCard填写是指定的目录,删除目录下所有的文件和文件夹
	 * 通过递归的方式实现
	 * @param File
	 */
	public static void deleteFiles(File filePath){
		if(isSDCardMounted()){
			if(filePath.isFile()){ //文件情况
				filePath.delete();
			}else if(filePath.isDirectory()){ //文件夹的情况
				File[] fList = filePath.listFiles();
				for (int i = 0; i < fList.length; i++) {
					File f = fList[i];
					//进行递归调用
					deleteFiles(f);
				}
			}
			filePath.delete(); //把当前的目录也删除了
		}
	}
	
	
	/**
	 * SDCard填写是指定的目录,删除目录下所有的文件和文件夹
	 * 通过递归的方式实现
	 * @param String 接收String的参数
	 */
	public static void deleteFiles(String path){
		deleteFiles(new File(path));
	}
	
	/**
	 * 获取SDCard根目录下的公共目录
	 * @param type的填写 
	 *  比如:Environment.DIRECTORY_ALARMS,Environment.DIRECTORY_DCIM 等等
	 */
	public static String  getSDCardPublicDirectory(String type){
		if(isSDCardMounted()){
			return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath();	
		}
		return null;
	}
	
}
