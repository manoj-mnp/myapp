package com.abhimantech.hiree.hireelocal;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.abhimantech.hiree.hireelocal.callbacks.FileListFetcherCallback;

public class FileListParser implements Runnable {

	FileListFetcherCallback _callback;
	String dirPath;

	public FileListParser(FileListFetcherCallback c, String dirPath) {
		this._callback = c;
		this.dirPath = dirPath;
	}

	public void run() {
		try {
			File dir = new File(dirPath);
			if (dir.exists()) {
				Collection<File> list = FileUtils.listFiles(dir, new String[] {
						"pdf", "doc", "docx", "txt" }, true);
				_callback.callback(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			_callback.callback(null);
		}
	}
}
