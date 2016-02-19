package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import math.field3d;

public class mainWindow extends JFrame {

	public field3d ff;
	private DrawLayers drawlayer;
	private StatusPanel statusPanel;
	private DrawTools drawTools;

	public mainWindow() {
		super("Planner");
		initField();
		init();
		setSize(1200, 430);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void init() {
		setLayout(new BorderLayout());

		drawlayer = new DrawLayers(this, ff);
		statusPanel = new StatusPanel(ff);
		drawTools = new DrawTools(this, ff);
		this.add(drawTools, BorderLayout.EAST);
		this.add(drawlayer, BorderLayout.CENTER);
		this.add(statusPanel, BorderLayout.SOUTH);
	}

	private void initField() {
		int[][][] t = {
				{ { 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 0 }, { 1, 1, 0, 1, 1 }, { 0, 1, 1, 0, 1 }, { 1, 0, 1, 0, 1 } },
				{ { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 1, 0, 1, 1, 0 } },
				{ { 1, 1, 0, 1, 1 }, { 0, 0, 1, 0, 0 }, { 1, 1, 1, 1, 1 }, { 0, 0, 1, 0, 0 }, { 1, 1, 0, 1, 1 } },
				{ { 1, 0, 1, 1, 0 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 1, 1, 0, 1 } },
				{ { 1, 0, 1, 0, 1 }, { 0, 1, 1, 0, 1 }, { 1, 1, 0, 1, 1 }, { 1, 0, 1, 1, 0 }, { 1, 0, 1, 0, 1 } },
		};
		ff = new field3d(t);
		// ff=new field3d(11,11,11);
		// ff = new field3d(11, 11, 15);
	}

	public void redraw(field3d ff) {
		drawlayer.redraw(ff);
		statusPanel.recount(ff);
		drawTools.redraw(ff);
	}

	public void repaint() {
		remove(drawlayer);
		remove(statusPanel);
		drawlayer = new DrawLayers(this, ff);
		statusPanel = new StatusPanel(ff);
		this.add(drawlayer, BorderLayout.CENTER);
		this.add(statusPanel, BorderLayout.SOUTH);
		revalidate();
	}

}
