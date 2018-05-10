import java.util.*;

public class NonDivisibleSubsetFour {

	public static int getResults(int element, int oneElementModZero, HashMap<Integer, Integer> first,
			HashMap<Integer, Integer> second, int firstHalf, int secondHalf) {

		int totalResult = 0;

		if (element == 2) {
			if (firstHalf != 0 && oneElementModZero != 0) {
				totalResult = 2;
				return totalResult;
			}
			return totalResult;
		}
		if (firstHalf == 0) {
			totalResult = secondHalf + oneElementModZero;
			return totalResult;
		}
		if (secondHalf == 0) {
			totalResult = firstHalf + oneElementModZero;
			return totalResult;
		}

		int firstHalfMin = 0;
		int secondHalfMin = 0;

		for (Integer n1 : first.keySet()) {
			firstHalfMin = n1;
			for (Integer n2 : second.keySet()) {
				secondHalfMin = n2;
				break;
			}
			break;
		}

		if (firstHalfMin > element - secondHalfMin) {
			totalResult = firstHalf + secondHalf + oneElementModZero;
			return totalResult;
		}

		int counter = 0;
		for (Integer n1 : first.keySet()) {
			
			if (second.containsKey(element - n1)) {
				int firstCount = first.get(n1);
				int secondCount = second.get(element - n1);
				if (firstCount < secondCount) {
					counter += firstCount;
				} else {
					counter += secondCount;
				}
			}
		}
		if (first.containsKey(element - element/2)  && first.get(element - element/2) > 1) {
			counter += first.get(element - element/2) - 1;
		}
		return totalResult = firstHalf + secondHalf + oneElementModZero - counter;
	}

	public static void main(String[] args) {

		int totalResult = 0;
		Scanner reader = new Scanner(System.in);
		int numberOfElements = reader.nextInt();
		int element = reader.nextInt();

		int middle = element / 2;
		int oneElementModZero = 0;

		HashMap<Integer, Integer> first = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> second = new HashMap<Integer, Integer>();
		int firstHalf = 0;
		int secondHalf = 0;
		for (int i = 0; i < numberOfElements; i++) {
			int input = reader.nextInt();
			int elm = input % element;
			if (elm != 0) {

				if (elm <= middle) {
					firstHalf++;
					if (!first.containsKey(elm)) {
						first.put(elm, 1);
					} else {
						first.put(elm, (first.get(elm) + 1));
					}

				} else if (elm > middle) {
					secondHalf++;
					if (!second.containsKey(elm)) {
						second.put(elm, 1);
					} else {
						second.put(elm, second.get(elm) + 1);
					}
				}
			} else {
				oneElementModZero = 1;
			}
		}
		int result = getResults(element, oneElementModZero, first, second, firstHalf, secondHalf);
		System.out.println(result);
	}
}
