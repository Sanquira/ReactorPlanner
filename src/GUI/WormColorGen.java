package GUI;

import java.awt.Color;
import java.util.ArrayList;

public class WormColorGen {

	private ArrayList<Color> WormColors = new ArrayList<Color>();
	private static WormColorGen instance = null;

	public static WormColorGen getInstance() {
		if (instance == null) {
			instance = new WormColorGen();
			instance.regenColor(2);
		}
		return instance;
	}

	private WormColorGen() {
	}

	public void regenColor(int maxCol) {
		WormColors.clear();
		for (int i = 0; i < maxCol; i++) {
			int[] col = genCol(((double) i) / maxCol);
			WormColors.add(new Color(col[0], col[1], col[2]));
		}
		WormColors.set(0, Color.lightGray);
	}

	public Color getColor(int id) {
		return WormColors.get(id);
	}

	public ArrayList<Color> getColors() {
		return WormColors;
	}

	private int[] genCol(double normTemp) {
		int[] RGB = new int[3];
		if (normTemp <= 0.75) {
			RGB[0] = (int) Math.round((4 * normTemp - 1.5) * 256);
		}
		if (normTemp > 0.75) {
			RGB[0] = (int) Math.round((-4 * normTemp + 4.5) * 256);
		}
		// G
		if (normTemp <= 0.5) {
			RGB[1] = (int) Math.round((4 * normTemp - 0.5) * 256);
		}
		if (normTemp > 0.5) {
			RGB[1] = (int) Math.round((-4 * normTemp + 3.5) * 256);
		}
		// B
		if (normTemp <= 0.25) {
			RGB[2] = (int) Math.round((4 * normTemp + 0.5) * 256);
		}
		if (normTemp > 0.25) {
			RGB[2] = (int) Math.round((-4 * normTemp + 2.5) * 256);
		}
		for (int i = 0; i < RGB.length; i++) {
			if (RGB[i] < 0)
				RGB[i] = 0;
			if (RGB[i] >= 256)
				RGB[i] = 255;
		}

		return RGB;
	}

}
