package files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import math.Worm;
import math.field3d;

public class SaveTemplate {

	public static void SaveTemp(field3d ff, String name) throws IOException {
		// File f = new File(name + ".smtpl"); // vytvori soubor
		FileOutputStream fw = new FileOutputStream(name + ".smtpl");

		fw.write(0x01);
		for (int i = 0; i < 3; i++) {
			fw.write(getByte(0));
		}
		fw.write(getByte(ff.getSizeX()));
		fw.write(getByte(ff.getSizeY()));
		fw.write(getByte(ff.getSizeZ()));
		int numOfGen = 0;
		ArrayList<Worm> wormList = ff.getWorms();
		for (int i = 0; i < wormList.size(); i++) {
			numOfGen += wormList.get(i).getNumOfBlock();
		}
		fw.write(getByte(numOfGen));

		for (int i = 0; i < ff.getSizeZ(); i++) {
			for (int j = 0; j < ff.getSizeY(); j++) {
				for (int k = 0; k < ff.getSizeX(); k++) {
					if (ff.get(k, j, i) != 0) {
						fw.write(getByte(k));
						fw.write(getByte(j));
						fw.write(getByte(i));
						fw.write(0x08);
						fw.write(0xc8);
						fw.write(0x02);
					}
				}
			}
		}
		fw.write(getByte(0));

		fw.close();

	}

	private static byte[] getByte(int val) {
		return ByteBuffer.allocate(4).putInt(val).array();
	}

	public static void SaveTempBounded(Integer valueOf, field3d ff, String name) {
		
		
	}

}
