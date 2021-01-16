package com.arvin.feifeiyu.win.util;

import java.io.File;
import java.util.Collection;

public class FileUtils {
	
	public static void collectFiles(final String parentPath, final Collection<File> fileList) {
		File parent = new File(parentPath);
        File[] files = parent.listFiles();
        if (files == null || files.length <= 0) {
            return;
        }
        for (File file : files) {
            if (file.isHidden()) {
                continue;
            }
            if (!file.exists()) {
                continue;
            }
            fileList.add(file);
        }
	} 

}
