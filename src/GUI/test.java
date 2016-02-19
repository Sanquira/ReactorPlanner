package GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class test extends JFrame {

	GridBagConstraints gbc;
	GridBagLayout gbl;

	public test() throws HeadlessException {
		super();
		setSize(800, 600);

		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		gbc.weightx = 1;
		vytvorTlacitko(Color.red, 0, 0);
		gbc.gridwidth=2;
		vytvorTlacitko(Color.black, 1, 0);
		gbc.gridwidth=1;
		vytvorTlacitko(Color.yellow, 3, 0);
		gbc.weightx=0;
		gbc.gridwidth=3;
		vytvorTlacitko(Color.blue, 0, 1);
		gbc.gridwidth=1;

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void vytvorTlacitko(Color name, int gridx, int gridy) {
		JPanel b = new JPanel();
		b.setBackground(name);
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		add(b,gbc);
	}

}
