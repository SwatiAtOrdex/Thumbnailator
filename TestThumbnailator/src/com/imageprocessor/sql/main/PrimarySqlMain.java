package com.imageprocessor.sql.main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import com.imageprocessor.util.ImageProcessConstants;

public class PrimarySqlMain {
	public static void main(String[] args) {
		File folder = new File(ImageProcessConstants.WETRANSFER_SOURCE_PATH);
		File file = new File(ImageProcessConstants.PRIMARY_SQL_FILE_PATH);
        FileWriter fr = null;
        BufferedWriter br = null;
        int count = 0;
		try {
			StringBuffer data = new StringBuffer();
			final File[] files = folder.listFiles();
			Arrays.sort(files);
			boolean first = true;
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);
			
			for (File child : files ) {
				count++;
				if(!child.getName().contains("_")) {
					if(first) {
						first = false;
					} else {
						data.append(");").append(System.getProperty("line.separator"));
					}
					data.append("UPDATE dbo.WebInv SET SmallPic = ").append(ImageProcessConstants.QUOTE_STRING)
						.append(ImageProcessConstants.SQL_SMALL_FOLDER_DESTINATION_PATH)
						.append(ImageProcessConstants.PATH_STRING).append(child.getName())
						.append(ImageProcessConstants.QUOTE_STRING).append(ImageProcessConstants.COMMA_STRING)
						.append(" MediumPic = ").append(ImageProcessConstants.QUOTE_STRING)
						.append(ImageProcessConstants.SQL_MEDIUM_FOLDER_DESTINATION_PATH)
						.append(ImageProcessConstants.PATH_STRING).append(child.getName())
						.append(ImageProcessConstants.QUOTE_STRING).append(ImageProcessConstants.COMMA_STRING)
						.append(" LargePic = ").append(ImageProcessConstants.QUOTE_STRING)
						.append(ImageProcessConstants.SQL_LARGE_FOLDER_DESTINATION_PATH)
						.append(ImageProcessConstants.PATH_STRING).append(child.getName())
						.append(ImageProcessConstants.QUOTE_STRING).append(ImageProcessConstants.COMMA_STRING)
						.append(" ExtraPic = ").append(ImageProcessConstants.QUOTE_STRING)
						.append(ImageProcessConstants.SQL_XLARGE_FOLDER_DESTINATION_PATH)
						.append(ImageProcessConstants.PATH_STRING).append(child.getName())
						.append(ImageProcessConstants.QUOTE_STRING).append(" WHERE PROD_CD = ")
						.append(ImageProcessConstants.QUOTE_STRING)
						.append(FilenameUtils.getBaseName(child.getName()))
						.append(ImageProcessConstants.QUOTE_STRING);
				} 
			}
			data.append(");");
			br.write(data.toString());
			System.out.println("-------------Total "+count+" files processed------------");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
