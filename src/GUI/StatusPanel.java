package GUI;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import math.Worm;
import math.field3d;
import math.generator;

public class StatusPanel extends JPanel {

	private JLabel generated;
	private generator gen = new generator();

	public StatusPanel(field3d ff) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		generated = new JLabel(makeMessage(ff));
		add(generated);
	}

	public void recount(field3d ff) {
		generated.setText(makeMessage(ff));
	}

	private String makeMessage(field3d ff) {
		ArrayList<Worm> wormList = ff.getWorms();
		int numOfGen = 0;
		for (int i = 0; i < wormList.size(); i++) {
			numOfGen += wormList.get(i).getNumOfBlock();
		}
		return "Total Power: " + (double)((int)(gen.totalPower(wormList)*100))/100 + " e/sec | Number of groups: " + wormList.size() +
				" | Number of blocks: " + numOfGen + " | Fulfill: " + (int)((numOfGen / (double)(ff.getSizeX() * ff.getSizeY() * ff.getSizeZ()))*100)+"%";
	}
}
