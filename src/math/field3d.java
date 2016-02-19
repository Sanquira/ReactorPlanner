package math;

import java.util.ArrayList;

import GUI.WormColorGen;

public class field3d {
	private int[][][] field;
	private int lastWorm = 0;
	private ArrayList<Worm> wormList = new ArrayList<Worm>();
	private WormColorGen ww = WormColorGen.getInstance();

	public field3d(int sizex, int sizey, int sizez) {
		field = new int[sizex][sizey][sizez];
	}

	public field3d(int[][][] field) {
		this.field = field.clone();
		findWorms();
		ww.regenColor(wormList.size() + 1);
	}

	public int getSizeX() {
		return field.length;
	}

	public int getSizeY() {
		return field[0].length;
	}

	public int getSizeZ() {
		return field[0][0].length;
	}

	public int[][] getXY(int layerZ) {
		int[][] tmp = new int[field.length][field[0].length];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				tmp[i][j] = field[i][j][layerZ];
			}
		}
		return tmp;
	}

	public int[][] getXZ(int layerY) {
		int[][] tmp = new int[field.length][field[0][0].length];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i][0].length; j++) {
				tmp[i][j] = field[i][layerY][j];
			}
		}
		return tmp;
	}

	public int[][] getYZ(int layerX) {
		int[][] tmp = new int[field[0].length][field[0][0].length];
		for (int i = 0; i < field[0].length; i++) {
			for (int j = 0; j < field[0][i].length; j++) {
				tmp[i][j] = field[layerX][i][j];
			}
		}
		return tmp;
	}

	public int get(int X, int Y, int Z) {
		return field[X][Y][Z];
	}

	public int[][][] getAll() {
		return field;
	}

	public void set(int X, int Y, int Z, int val) {
		field[X][Y][Z] = val;
	}

	public void rstXY(int Z) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j][Z] = 0;
			}
		}
		findWorms();
	}

	public void invertValue(int x, int y, int z) {
		if (isBlock(x, y, z)) {
			set(x, y, z, 0);
		} else {
			set(x, y, z, -1);
		}
		findWorms();
	}

	private boolean isBlock(int X, int Y, int Z) {
		return get(X, Y, Z) == 0 ? false : true;
	}

	private boolean isPartOfMe(int X, int Y, int Z, int wormID) {
		return get(X, Y, Z) == wormID ? true : false;
	}

	private void findWorms() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				for (int k = 0; k < field[i][j].length; k++) {
					if (isBlock(i, j, k)) {
						set(i, j, k, -1);
					}
				}
			}
		}
		lastWorm = 0;
		wormList.clear();
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				for (int k = 0; k < field[i][j].length; k++) {
					if (isBlock(i, j, k)) {
						expandWorm(i, j, k);
					}
				}
			}
		}
	}

	private void expandWorm(int X, int Y, int Z) {
		ArrayList<WormElement> expand = new ArrayList<WormElement>();
		if (get(X, Y, Z) <= lastWorm && get(X, Y, Z) > 0) {
			return;
		}
		if (get(X, Y, Z) > lastWorm || get(X, Y, Z) < 0) {
			lastWorm++;
		}
		wormList.add(new Worm(lastWorm, X, Y, Z));
		set(X, Y, Z, lastWorm);
		WormElement e = new WormElement(X, Y, Z, lastWorm);
		boolean done = false;
		do {
			try {
				if (isBlock(e.ex - 1, e.ey, e.ez) && !isPartOfMe(e.ex - 1, e.ey, e.ez, e.eID)) {
					set(e.ex - 1, e.ey, e.ez, e.eID);
					expand.add(new WormElement(e.ex - 1, e.ey, e.ez, e.eID));
					wormList.get(wormList.size() - 1).addX(e.ex - 1, e.ey, e.ez);
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
			}
			try {
				if (isBlock(e.ex + 1, e.ey, e.ez) && !isPartOfMe(e.ex + 1, e.ey, e.ez, e.eID)) {
					set(e.ex + 1, e.ey, e.ez, e.eID);
					expand.add(new WormElement(e.ex + 1, e.ey, e.ez, e.eID));
					wormList.get(wormList.size() - 1).addX(e.ex + 1, e.ey, e.ez);
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
			}
			try {
				if (isBlock(e.ex, e.ey - 1, e.ez) && !isPartOfMe(e.ex, e.ey - 1, e.ez, e.eID)) {
					set(e.ex, e.ey - 1, e.ez, e.eID);
					expand.add(new WormElement(e.ex, e.ey - 1, e.ez, e.eID));
					wormList.get(wormList.size() - 1).addY(e.ex, e.ey - 1, e.ez);
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
			}
			try {
				if (isBlock(e.ex, e.ey + 1, e.ez) && !isPartOfMe(e.ex, e.ey + 1, e.ez, e.eID)) {
					set(e.ex, e.ey + 1, e.ez, e.eID);
					expand.add(new WormElement(e.ex, e.ey + 1, e.ez, e.eID));
					wormList.get(wormList.size() - 1).addY(e.ex, e.ey + 1, e.ez);
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
			}
			try {
				if (isBlock(e.ex, e.ey, e.ez - 1) && !isPartOfMe(e.ex, e.ey, e.ez - 1, e.eID)) {
					set(e.ex, e.ey, e.ez - 1, e.eID);
					expand.add(new WormElement(e.ex, e.ey, e.ez - 1, e.eID));
					wormList.get(wormList.size() - 1).addZ(e.ex, e.ey, e.ez - 1);
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
			}
			try {
				if (isBlock(e.ex, e.ey, e.ez + 1) && !isPartOfMe(e.ex, e.ey, e.ez + 1, e.eID)) {
					set(e.ex, e.ey, e.ez + 1, e.eID);
					expand.add(new WormElement(e.ex, e.ey, e.ez + 1, e.eID));
					wormList.get(wormList.size() - 1).addZ(e.ex, e.ey, e.ez + 1);
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
			}
			try {
				e = expand.remove(0);
			} catch (IndexOutOfBoundsException ex) {
				done = true;
			}
		} while (!done);
	}

	public ArrayList<Worm> getWorms() {
		findWorms();
		return wormList;
	}

	private class WormElement {
		int ex, ey, ez, eID;

		public WormElement(int X, int Y, int Z, int wormID) {
			ex = X;
			ey = Y;
			ez = Z;
			eID = wormID;
		}
	}

	public void printField() {
		for (int h = 0; h < this.getAll()[0][0].length; h++) {
			// int h = 0;
			for (int[] is : this.getXY(h)) {
				for (int i : is) {
					System.out.print(i);
				}
				System.out.println();
			}
			System.out.println("-----");
		}
		System.out.println("=====");
	}

	public void printWorms() {
		for (int i = 0; i < wormList.size(); i++) {
			System.out.println(wormList.get(i).toString());
		}
		System.out.println("=====");
	}

	public void rstAll() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				for (int k = 0; k < field[i][j].length; k++) {
					set(i, j, k, 0);
				}
			}
		}
	}

}
