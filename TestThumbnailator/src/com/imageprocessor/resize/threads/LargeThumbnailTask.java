package com.imageprocessor.resize.threads;

import java.io.File;
import com.imageprocessor.util.FileUtil;
import com.imageprocessor.util.ImageProcessConstants;

public class LargeThumbnailTask implements Runnable{
	
	private File file;
	File destinationFolder = new File(ImageProcessConstants.LARGE_FOLDER_DESTINATION_PATH);

	public LargeThumbnailTask(File file) {
		this.file = file;
	}

	@Override
	public void run() {
		try {
			FileUtil.resizeFile(file, destinationFolder);
		} catch (Exception e) {
			System.out.println(file.getName());
			e.printStackTrace();
		}
	}
}
