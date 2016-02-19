package math;

public class Worm {
	private int bbx, bby, bbz, numB, name;
	private int lx, ux, ly, uy, lz, uz;

	public Worm(int wormName, int X, int Y, int Z) {
		name = wormName;
		bbx = 1;
		bby = 1;
		bbz = 1;
		numB = 1;
		lx = X;
		ux = X;
		ly = Y;
		uy = Y;
		lz = Z;
		uz = Z;
	}

	public void addX(int X, int Y, int Z) {
		numB++;
		boolean can = false;
		if (lx > X) {
			lx = X;
			can = true;
		}
		if (ux < X) {
			ux = X;
			can = true;
		}
		if (can)
			bbx++;
	}

	public void addY(int X, int Y, int Z) {
		numB++;
		boolean can = false;
		if (ly > Y) {
			ly = Y;
			can = true;
		}

		if (uy < Y) {
			uy = Y;
			can = true;
		}
		if (can)
			bby++;
	}

	public void addZ(int X, int Y, int Z) {
		numB++;
		boolean can = false;
		if (lz > Z) {
			lz = Z;
			can = true;
		}
		if (uz < Z) {
			uz = Z;
			can = true;
		}
		if (can)
			bbz++;
	}

	public int getSumOfDim() {
		return bbx + bby + bbz;
	}

	public int getNumOfBlock() {
		return numB;
	}

	public String toString() {
		return "name: " + name + ", bbx: " + bbx + ", bby: " + bby + ", bbz: " + bbz + ", numB: " + numB;
	}
}