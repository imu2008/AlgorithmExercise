package com.apelover.algorithm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

public class RecursionExercise {

	private static final String TEMP = System.getProperty("java.io.tmpdir");

	class Result {
		Long sum = 0L;
		int fileCount = 0;

		public Long getSum() {
			return sum;
		}

		public void addSum(long value) {
			this.sum += value;
		}

		public int getFileCount() {
			return fileCount;
		}

		public void addFileCount(int add) {
			this.fileCount += add;
		}
	}

	/**
	 * 运算结果：总文件数：2202，文件内数字之和：10802832，用时：39(s)
	 * */
	public static void main(String[] args) {
		String gzFilePath = "files/00.tar.gz";
		RecursionExercise re = new RecursionExercise();
		Result result = re.new Result();
		long startTime = System.currentTimeMillis();
		try {
			re.visitTARGZ(new File(gzFilePath), result, TEMP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("总文件数：" + result.getFileCount() + "，文件内数字之和：" + result.getSum() + "，用时："
				+ (System.currentTimeMillis() - startTime) / 1000 + "(s)");
	}

	private void visitTARGZ(File targzFile, Result result, String destDir) throws Exception {
		TarArchiveInputStream taris = null;
		BufferedInputStream bis = null;
		try {
			System.out.println("处理压缩包：" + targzFile.getAbsolutePath());
			taris = new TarArchiveInputStream(new GZIPInputStream(new FileInputStream(targzFile)));
			TarArchiveEntry entry = null;
			bis = new BufferedInputStream(taris);
			while ((entry = taris.getNextTarEntry()) != null) {
				String name = entry.getName();
				String[] names = name.split("/");
				String fileName = destDir;
				for (int i = 0; i < names.length; i++) {
					String str = names[i];
					fileName = fileName + File.separator + str;
				}
				if (name.endsWith("/")) {
					File f = new File(fileName);
					if (!f.exists() || !f.isDirectory()) {
						f.getParentFile().mkdirs();
						f.mkdir();
					}
					System.out.println("  生成目录：" + f);
				} else {
					File file = createFile(fileName, bis);
					if (fileName.endsWith(".tar.gz")) {
						visitTARGZ(file, result, file.getParent());
					} else {
						readFile(new FileInputStream(file), result);
						System.out.println("  读文件：" + file.getAbsolutePath() + "。文件数：" + result.getFileCount() + "，数值和："
								+ result.getSum());
					}
					file.delete();
				}
			}
			/** 解压后的目录名 */
			String unZipDir = destDir + File.separator
					+ targzFile.getName().substring(0, targzFile.getName().length() - 7);
			File file = new File(unZipDir);
			if (file.exists() && file.isDirectory()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (taris != null)
				taris.close();
		}
	}

	private File createFile(String destPath, BufferedInputStream bis) {
		BufferedOutputStream bos = null;
		File destFile = new File(destPath);
		try {
			if (!destFile.exists()) {
				destFile.getParentFile().mkdirs();
				destFile.createNewFile();
			}
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			int b = -1;
			while ((b = bis.read()) != -1) {
				bos.write(b);
			}
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return destFile;
	}

	private void readFile(InputStream stream, Result result) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(stream));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Integer value = Integer.valueOf(line);
				result.addSum(value);
				result.addFileCount(1);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
