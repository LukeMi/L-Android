package com.luke.android.demo.testspeedmodel;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class FileControl {
	Context context;

	public FileControl(Context ct) {
		if (UnitTool.FileSavePath.equals("")) {
			UnitTool.setLocalPath();
		}
		context = ct;
	}
	public void deletFileList(ArrayList<String> fileList) {

		if (fileList.size() > 0) {
			for (int i = fileList.size(); i > 0; i--) {
				String filename = fileList.get(i - 1);
				File file = new File(UnitTool.FileSavePath + filename);
				if (file.exists()) {
					file.delete();
				}
			}
		}

	}

	/**
	 * 删除单个文件
	 * 
	 * @param fliename
	 * @return
	 */
	public boolean deletFileSingle(String fliename) {
		boolean b = false;
		File file = new File(UnitTool.FileSavePath + fliename);
		if (file.exists()) {
			b = file.delete();
		}
		return b;
	}

	/**
	 * 删除文件夹里所有文件
	 * 
	 * @param file
	 */
	public void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		} else {
			Log.v("FileContnrol", "文件不存在！" + "\n");
		}
	}

	/**
	 * 查找文件夹中与几天前的文件
	 * 
	 * @param path
	 *            装文件的文件夹
	 */
	public ArrayList<String> getdeleteFile(String path) {
		ArrayList<String> deletFileList = new ArrayList<String>();
		File file = new File(path);
		if (file.exists()) {
			long currentTime = System.currentTimeMillis();
			Log.v("Filecontrol", "currentTime" + currentTime);
			if (file.isDirectory()) { // 是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					
					if ((currentTime - files[i].lastModified())
							/ (1000.0 * 24.0 * 3600.0) >= UnitTool.DELETE_LOG_FILE_DAY) {
						Log.v("Filecontrol", files[i].getName());
						deletFileList.add(files[i].getName());
					}
				}
			}
		}
		return deletFileList;
	}
}
