package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Listeners.LayerMouseListener;
import math.field3d;

public class layerViewportView extends JPanel {

	private WormColorGen ww = WormColorGen.getInstance();
	private JLabel[] labels;

	public layerViewportView(mainWindow mainWindow, field3d ff, int layerZ) {
		super();
		setPreferredSize(new Dimension(30 * ff.getSizeX(), 30 * ff.getSizeY()));
		setLayout(new GridLayout(ff.getSizeY(), ff.getSizeX(), 2, 2));
		setBackground(Color.black);
		createButtonField(mainWindow, ff, layerZ);
	}

	private void createButtonField(mainWindow mainWindow, field3d ff, int layerZ) {
		labels = new JLabel[ff.getSizeX() * ff.getSizeY()];
		ww.regenColor(ff.getWorms().size() + 1);
		for (int i = 0; i < ff.getSizeY(); i++) {
			for (int j = 0; j < ff.getSizeX(); j++) {
				int index = i * ff.getSizeX() + j;
				labels[index] = new JLabel();
				labels[index].setHorizontalAlignment(JLabel.CENTER);
				labels[index].setText(getLabelText(ff, j, i, layerZ));
				labels[index].setBackground(ww.getColor(ff.get(j, i, layerZ)));
				labels[index].setOpaque(true);
				labels[index].addMouseListener(new LayerMouseListener(mainWindow, ff, j, i, layerZ));
				add(labels[index]);
			}
		}
	}

	private String getLabelText(field3d ff, int x, int y, int z) {
		if (ff.get(x, y, z) != 0)
			return ff.get(x, y, z) + "";
		try {
			if (ff.get(x, y, z - 1) != 0 && ff.get(x, y, z + 1) != 0)
				return "±";
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (ff.get(x, y, z - 1) != 0)
				return "-";
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (ff.get(x, y, z + 1) != 0)
				return "+";
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return "";
	}

	public void redraw(field3d ff, int layerZ) {
		ww.regenColor(ff.getWorms().size() + 1);
		for (int i = 0; i < ff.getSizeY(); i++) {
			for (int j = 0; j < ff.getSizeX(); j++) {
				int index = i * ff.getSizeX() + j;
				labels[index].setText(getLabelText(ff, j, i, layerZ));
				labels[index].setBackground(ww.getColor(ff.get(j, i, layerZ)));
				add(labels[index]);
			}
		}
	}

}
