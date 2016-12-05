package com.imageprocessor.resize.threads;

import java.io.File;
import com.imageprocessor.util.FileUtil;
import com.imageprocessor.util.ImageProcessConstants;

public class MediumThumbnailTask implements Runnable{
	
	private File file;
	File destinationFolder = new File(ImageProcessConstants.MEDIUM_FOLDER_DESTINATION_PATH);

	public MediumThumbnailTask(File file) {
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
