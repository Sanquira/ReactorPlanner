package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import math.field3d;

public class DrawLayers extends JScrollPane {

	private JPanel viewportPanel = new JPanel();
	private JScrollPane[] layers;

	public DrawLayers(mainWindow mainWindow, field3d ff) {
		super(null, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		viewportPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		initLayers(mainWindow,ff);
		setViewportView(viewportPanel);
		getHorizontalScrollBar().setUnitIncrement(16);
	}

	public void initLayers(mainWindow mainWindow, field3d ff) {
		int sizeZ = ff.getSizeZ();
		layers = new JScrollPane[sizeZ];
		for (int i = 0; i < sizeZ; i++) {
			layers[i] = new JScrollPane(new layerViewportView(mainWindow, ff, i),
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			layers[i].getHorizontalScrollBar().setUnitIncrement(8);
			layers[i].getVerticalScrollBar().setUnitIncrement(8);
			layers[i].setPreferredSize(new Dimension(350, 350));
			layers[i].setCorner(JScrollPane.LOWER_RIGHT_CORNER, new JLabel(i+""));
			viewportPanel.add(layers[i]);
		}
	}

	public void redraw(field3d ff) {
		for (int k = 0; k < ff.getSizeZ(); k++) {
			((layerViewportView) layers[k].getViewport().getView()).redraw(ff, k);
		}
	}
}
