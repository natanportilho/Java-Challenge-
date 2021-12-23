package com.portilho;

import java.util.*;

class Program {
	public List<Integer[]> fourNumberSum(int[] array, int targetSum) {
//      array: [7 , 6,  4, -1, 1 ,2]
//      targetSum = 16
//      [[7, 6, 4, -1], [7, 6, 1, 2]]  // the quadruplets could be ordered differently

		//create a hash table with pairs
		//match pairs with pairs that sum to the target // make sure we have unique numbers
		// create quadruplets and add them to a list, make sure we don't have duplicates

		if (array.length < 4) {
			return new ArrayList<>();
		}

		Map<Integer, List<Pair>> map = new HashMap<>();
		List<Integer[]> quadruplets = new ArrayList<>();

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {

				int sum = array[i] + array[j];
				Pair pair = new Pair(array[i], array[j]);
				List<Pair> pairs = new ArrayList<>();
				if (map.get(sum) != null) {
					pairs = map.get(sum);
					pairs.add(pair);
				} else {
					pairs.add(pair);
				}
				map.put(sum, pairs);
			}
		}

		for (Integer key : map.keySet()) {
			int target = targetSum - key;
			List<Pair> pairs1 = map.get(key);
			List<Pair> pairs2 = map.get(target);

			if (pairs2 != null) {
				for (int i = 0; i < pairs1.size(); i++) {
					for (int j = 0; j < pairs2.size(); j++) {
						Pair pair1 = pairs1.get(i);
						Pair pair2 = pairs2.get(j);

						// is this a valid pair combination
						if (pair1.a != pair2.a && pair1.b != pair2.b && pair1.a != pair2.b && pair2.a != pair1.b) {
							Integer[] quadruplet = new Integer[]{pair1.a, pair1.b, pair2.a, pair2.b};
							Arrays.sort(quadruplet);

							if (quadruplets.stream().noneMatch(q -> Arrays.equals(q, quadruplet))) {
								quadruplets.add(quadruplet);
							}
						}
					}
				}

			}
		}

		return quadruplets;
	}

	class Pair {
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		int a, b;
	}
}
