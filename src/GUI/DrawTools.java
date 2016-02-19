package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import math.field3d;
import sprt.starmade.viewer.Viewer;

public class DrawTools extends JPanel {

	private WormColorGen ww = WormColorGen.getInstance();
	private Viewer rf;

	public DrawTools(mainWindow w, field3d ff) {
		super();
		setBackground(Color.red);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(380, 380));
		init(w, ff);
	}

	private void init(mainWindow w, field3d ff) {
		ww.regenColor(ff.getWorms().size() + 1);
//		for (int i = 0; i < ww.getColors().size(); i++) {
//			System.out.print(ww.getColor(i).getRed());System.out.print(" "+ww.getColor(i).getGreen());System.out.println(" "+ww.getColor(i).getBlue());
//		}
		rf = new Viewer(ff.getAll(), ww.getColors());
		add(rf, BorderLayout.CENTER);
	}

	public void redraw(field3d ff) {
		remove(rf);
		ww.regenColor(ff.getWorms().size() + 1);
		rf = new Viewer(ff.getAll(), ww.getColors());
		add(rf);
		revalidate();
	}
}
