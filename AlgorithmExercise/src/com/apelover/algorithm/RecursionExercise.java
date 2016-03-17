package com.apelover.algorithm;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

public class RecursionExercise {

	public static void main(String[] args) {
		String gzFilePath = "G:/git-repository/AlgorithmExercise/AlgorithmExercise/files/00.tar.gz";
		RecursionExercise re = new RecursionExercise();
		Long sum = 0L;
		Integer fileNumber = 0;
		try {
			re.visitTARGZ(new File(gzFilePath), sum, fileNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("总文件数：" + fileNumber + "，文件内数字之和：" + sum);
	}

	private void visitTARGZ(File targzFile, Long sum, Integer fileNumber)
			throws Exception {
		TarArchiveInputStream taris = null;
		try {
			taris = new TarArchiveInputStream(new GZIPInputStream(
					new FileInputStream(targzFile)));
			TarArchiveEntry entry = null;
			while ((entry = taris.getNextTarEntry()) != null) {
				if (entry.isDirectory())
					continue;
				System.out.println(entry.isFile());
				File file = entry.getFile();
				byte[] b = new byte[(int) entry.getSize()];
				taris.read(b);
				taris.read(b, 0, (int) entry.getSize());
				System.out.println(b.length);
				System.out.println(b);
				if (file.getName().endsWith(".tar.gz")) {
					visitTARGZ(file, sum, fileNumber);
				} else {
					readFile(file, sum, fileNumber);
				}
			}
		} finally {
			if (taris != null)
				taris.close();
		}
	}

	private void readFile(File file, Long sum, Integer fileNumber) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Integer value = Integer.valueOf(line);
				sum += value;
				fileNumber++;
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
