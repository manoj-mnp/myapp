package com.abhimantech.hiree.hireelocal;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Collection<File> fileList = FileListParser
				.getAllFilesFromDirectory("/Users/abhimantechnologies/Documents/Parser/resumes/");
		OpenNLPER.processFiles(fileList);
	}
}
