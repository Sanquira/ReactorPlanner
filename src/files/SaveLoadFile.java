package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import GUI.mainWindow;
import math.field3d;

public class SaveLoadFile {

	public static void saveField(field3d ff, String name) throws Exception {
		FileWriter fw = null;
		File f = new File(name + ".txt"); // vytvori soubor
		fw = new FileWriter(name + ".txt");
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(ff.getSizeX() + " " + ff.getSizeY() + " " + ff.getSizeZ());
		bw.newLine();
		for (int i = 0; i < ff.getSizeZ(); i++) {
			int[][] ffxy = ff.getXY(i);
			bw.write("Layer " + i);
			bw.newLine();
			for (int j = 0; j < ffxy[0].length; j++) {
				for (int j2 = 0; j2 < ffxy.length; j2++) {
					bw.write((ffxy[j2][j] == 0) ? 0 + " " : 1 + " ");
				}
				bw.newLine();
			}
			bw.newLine();
		}

		bw.close();
		fw.close();
	}

	public static int[][][] loadField(mainWindow mainWindow, String name) throws FileNotFoundException {
		Scanner s = new Scanner(new FileInputStream(name + ".txt"), "UTF8");

		mainWindow.ff = new field3d(s.nextInt(), s.nextInt(), s.nextInt());
		String next;
		for (int z = 0; z < mainWindow.ff.getSizeZ(); z++) {
			s.next();
			s.next();
			for (int y = 0; y < mainWindow.ff.getSizeY(); y++) {
				for (int x = 0; x < mainWindow.ff.getSizeX(); x++) {
					mainWindow.ff.set(x, y, z, s.nextInt());
				}
			}
		}
		mainWindow.repaint();

		s.close();
		return null;
	}

}
