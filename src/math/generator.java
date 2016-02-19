package math;
import java.util.ArrayList;

public class generator {

	public generator() {
	}

	private double energyBlock(int numOfGen) {
		return 25 * numOfGen;
	}

	private double groupSumSizePower(ArrayList<Integer> sumOfDim) {
		double val = 0;
		while (sumOfDim.size() != 0) {
			val += Math.pow(((double) sumOfDim.remove(0)) / 3, 1.7);
		}
		return (double) ((int) ((2000000 / (1 + Math.pow(1.000696, -0.333 * val)) - 1000000) * 100)) / 100;
	}

	public double totalPower(ArrayList<Worm> wormList) {
		ArrayList<Integer> sumOfDim = new ArrayList<Integer>();
		int numOfGen = 0;
		for (int i = 0; i < wormList.size(); i++) {
			sumOfDim.add(wormList.get(i).getSumOfDim());
			numOfGen += wormList.get(i).getNumOfBlock();
		}
//		System.out.println(energyBlock(numOfGen)+ ", "+ groupSumSizePower(sumOfDim)+", "+(energyBlock(numOfGen) + groupSumSizePower(sumOfDim)));
		return (energyBlock(numOfGen) + groupSumSizePower(sumOfDim));
	}
}
