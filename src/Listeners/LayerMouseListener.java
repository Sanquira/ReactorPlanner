package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import GUI.DrawLayers;
import GUI.PopupMenuLayers;
import GUI.mainWindow;
import math.field3d;

public class LayerMouseListener extends MouseAdapter {

	private field3d ff;
	private int sx, sy, sz;
	private mainWindow mainWindow;

	public LayerMouseListener(mainWindow mainWindow, field3d ff, int x, int y, int z) {
		super();
		this.mainWindow = mainWindow;
		this.ff = ff;
		sx = x;
		sy = y;
		sz = z;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			JPopupMenu popup = new PopupMenuLayers(mainWindow, ff, sx, sy, sz);
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
		if (e.getButton() == MouseEvent.BUTTON1) {
			ff.invertValue(sx, sy, sz);
		}
		mainWindow.redraw(ff);
		super.mouseClicked(e);
	}

	@Override
	public String toString() {
		return "sx: " + sx + ", sy: " + sy + ", sz: " + sz;
	}

}
