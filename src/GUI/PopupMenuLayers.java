package GUI;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import math.field3d;
import files.SaveLoadFile;
import files.SaveTemplate;

public class PopupMenuLayers extends JPopupMenu {

	public PopupMenuLayers(mainWindow mainWindow, field3d ff, int sx, int sy, int sz) {
		super();
		add(new JMenuItem(new AbstractAction("Remove layer") {

			@Override
			public void actionPerformed(ActionEvent e) {
				ff.rstXY(sz);
				mainWindow.redraw(ff);
			}
		}));
		add(new JMenuItem(new AbstractAction("Remove all") {

			@Override
			public void actionPerformed(ActionEvent e) {
				ff.rstAll();
				mainWindow.redraw(ff);
			}
		}));
		addSeparator();
		add(new JMenuItem(new AbstractAction("New") {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] namesplit;
				try {
					namesplit = JOptionPane.showInputDialog("Insert new generator size (format: \"x y z\" eg. 11 11 15)").split(" ");
				} catch (NullPointerException e1) {
					return;
				}
				mainWindow.ff = new field3d(Integer.valueOf(namesplit[0]), Integer.valueOf(namesplit[1]), Integer.valueOf(namesplit[2]));
				mainWindow.repaint();
			}
		}));

		add(new JMenuItem(new AbstractAction("Save as") {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = JOptionPane.showInputDialog("Save as (without .txt)");
					if (name == null)
						return;
					SaveLoadFile.saveField(ff, name);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}));

		add(new JMenuItem(new AbstractAction("Load from") {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Load from (without .txt)");
				if (name == null)
					return;
				try {
					SaveLoadFile.loadField(mainWindow, name);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}));
		addSeparator();
		add(new JMenuItem(new AbstractAction("Save template") {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Save as (without .smtpl)");
				if (name == null)
					return;
				try {
					SaveTemplate.SaveTemp(ff, name);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}));
//		add(new JMenuItem(new AbstractAction("Save templates with bounded size ") {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showConfirmDialog(null, "Not implemented yet");
////				String name = JOptionPane.showInputDialog("Save as (without .smtpl)");
////				if (name == null)
////					return;
////				String size = JOptionPane.showInputDialog("Max bound size:");
////				if (size == null)
////					return;
////				SaveTemplate.SaveTempBounded(Integer.valueOf(size), ff, name);
//			}
//		}));
	}
}
