package com.imageprocessor.resize.main;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.FileUtils;
import com.imageprocessor.resize.threads.LargeThumbnailTask;
import com.imageprocessor.util.FileUtil;
import com.imageprocessor.util.ImageProcessConstants;;

public class LargeThumbnailThreadMain {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		File folder = new File(ImageProcessConstants.WETRANSFER_SOURCE_PATH);
		File destinationFolder = new File(ImageProcessConstants.LARGE_FOLDER_DESTINATION_PATH);
		try {
			boolean successFlag = FileUtil.deleteDirectory(destinationFolder);
			if(successFlag) {
				ExecutorService executor = Executors.newFixedThreadPool(5);
				String[] extensions = new String[] { ImageProcessConstants.IMAGE_FORMAT_JPG, ImageProcessConstants.IMAGE_FORMAT_jpg, ImageProcessConstants.IMAGE_FORMAT_png };
				for(File file : (List<File>) FileUtils.listFiles(folder, extensions, true)) {
					 Runnable worker = new LargeThumbnailTask(file);
					 executor.submit(worker);
				}
				executor.shutdown();
				while (!executor.isTerminated()) {
		        }
				System.out.println("Done resizing to large images.");
			} else {
				System.out.println("Unable to delete directory");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
