import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import GUI.mainWindow;

public class planner3d {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("run");

		new mainWindow();
		
		System.out.println("done");

		Timer tim = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// tim.start();

	}

}
