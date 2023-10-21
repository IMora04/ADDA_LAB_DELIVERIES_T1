package deliverables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import auxTypes.AuxTypes.Pair;

public class P1E2 {

	public static List<Integer> f_non_final(Integer a, Integer b) {
		return f_non_final_aux(a, b, new ArrayList<Integer>());
	}

	public static List<Integer> f_non_final_aux(Integer a, Integer b, List<Integer> res) {
		if (a < 2 || b < 2) {	// Base case
			res.add(Integer.valueOf(a * b));
		} else if (a > b) {	// a > b case
			res = f_non_final_aux(a % b, b - 1, res);
			res.add(a);
		} else {	// other case
			res = f_non_final_aux(a - 2, b / 2, res);
			res.add(b);
		}
		return res;
	}

	public static List<Integer> f_iterative(Integer a, Integer b) {
		Integer valA = a;
		Integer valB = b;
		List<Integer> res = new ArrayList<>();
		
		while (!(valA < 2 || valB < 2)) {	// While base case not met
			if (valA > valB) {
				res.add(0, valA);
				valA = valA % valB;
				valB = valB - 1;
			} else {
				res.add(0, valB);
				valA = valA - 2;
				valB = valB / 2;
			}
		}
		
		res.add(0, Integer.valueOf(valA * valB));	// End of loop -> Base case
		return res;
	}

	public static List<Integer> f_final(Integer a, Integer b) {	// First recursive call
		return f_final_aux(a, b, new ArrayList<Integer>());
	}

	public static List<Integer> f_final_aux(Integer a, Integer b, List<Integer> res) {
		if (!(a < 2 || b < 2)) {	// If no base case
			if (a > b) {
				res.add(0, a);	// Added at the beginning to avoid non-final recursion
				f_final_aux(a % b, b - 1, res);
			} else {
				res.add(0, b);
				f_final_aux(a - 2, b / 2, res);
			}
		} else {	// Base case
			res.add(0, Integer.valueOf(a * b));
		}
		return res;
	}

	public static List<Integer> f_functional(Integer a, Integer b) {
		List<Integer> res = Stream.iterate(
					Pair.of(a, b),	// Initial value
					p -> !(p == null),	// While the actual pair is not null (not a<2 | b<2)
					p -> p.nx())
				.map(p -> p.res())	// Map each pair to its value to be accumulated
				.collect(Collectors.toList());	
		Collections.reverse(res);	// Reverse list (It has to be iterated in reverse order, from p(a,b))
		return res;
	}

}
