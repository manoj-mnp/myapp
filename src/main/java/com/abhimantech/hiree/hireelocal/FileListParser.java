package com.abhimantech.hiree.hireelocal;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class FileListParser {

	/**
	 * get the list of files from a given directory including subdirectories.
	 * 
	 * @param dirPath
	 */
	public static Collection<File> getAllFilesFromDirectory(String dirPath) {
		try {
			File dir = new File(dirPath);
			if (dir.exists()) {
				Collection<File> list = FileUtils.listFiles(dir, new String[] {
						"pdf", "doc", "docx", "txt" }, true);
				return list;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
