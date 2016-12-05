package com.imageprocessor.util;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FileUtil {
	public static boolean deleteDirectory(File dir) 
	{ 
		boolean success = false;
		if (dir.isDirectory()) {
			File[] children = dir.listFiles(); 
			if(children.length > 0) {
				for (File i :children) 
				{ 
					success = i.delete();
					if(success) deleteDirectory(i); 
					else { return false; }
				} 
			} else return true;
		}
		return success;
	}

	public static void resizeFile(File file, File destinationFolder) throws IOException {
		switch(FilenameUtils.getExtension(file.getName())) {
		case ImageProcessConstants.IMAGE_FORMAT_JPG : 
			Thumbnails.of(file).size(ImageProcessConstants.LARGE_DIMENSION,ImageProcessConstants.LARGE_DIMENSION)
			.outputFormat(ImageProcessConstants.IMAGE_FORMAT_JPG).toFiles(destinationFolder,Rename.PREFIX_DOT_THUMBNAIL);
			break;
		
		case ImageProcessConstants.IMAGE_FORMAT_jpg : 
			Thumbnails.of(file).size(ImageProcessConstants.LARGE_DIMENSION,ImageProcessConstants.LARGE_DIMENSION)
			.outputFormat(ImageProcessConstants.IMAGE_FORMAT_jpg).toFiles(destinationFolder,Rename.PREFIX_DOT_THUMBNAIL);
			break;
		case ImageProcessConstants.IMAGE_FORMAT_png : 
			Thumbnails.of(file).size(ImageProcessConstants.LARGE_DIMENSION,ImageProcessConstants.LARGE_DIMENSION)
			.outputFormat(ImageProcessConstants.IMAGE_FORMAT_png).toFiles(destinationFolder,Rename.PREFIX_DOT_THUMBNAIL);
			break;
		
		default : 
			System.out.println(file.getName() + " : ");
			System.out.println(FilenameUtils.getExtension(file.getName()));
		} 
	}
}
