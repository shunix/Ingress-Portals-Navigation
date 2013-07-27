package com.shunix.portalsnavn.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.os.Environment;

public final class UnZipHelper {
	public static boolean unzipFile(String path, String zipname) {
		InputStream inputStream;
		ZipInputStream zipInputStream;
		ZipEntry zipEntry;
		try {
			inputStream = new FileInputStream(path + zipname);
			zipInputStream = new ZipInputStream(new BufferedInputStream(
					inputStream));
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int count;
				String filename = zipEntry.getName();
				FileOutputStream fileOutputStream = new FileOutputStream(path
						+ filename);
				while ((count = zipInputStream.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, count);
					byte[] bytes = byteArrayOutputStream.toByteArray();
					fileOutputStream.write(bytes);
					byteArrayOutputStream.reset();
				}

				fileOutputStream.close();
				zipInputStream.closeEntry();
			}
			zipInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	public static String getZipStorageDir(Context context, String dirName) {
		File file = new File(context.getExternalFilesDir(null), dirName);
		if(file.exists()) {
			return file.getAbsolutePath();
		}
		if (!file.mkdirs()) {
			return null;
		}
		return file.getAbsolutePath();
	}
	
	public static File getTempFile(Context context, String fileName) {
	    File file = null;
	    try {
	        file = File.createTempFile(fileName, null, context.getCacheDir());
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    return file;
	}
}
