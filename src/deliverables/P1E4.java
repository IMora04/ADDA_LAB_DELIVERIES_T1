package deliverables;

import java.util.HashMap;
import java.util.Map;

import auxTypes.AuxTypes.Pair;

public class P1E4 {

	public static String g_rec_no_mem(Integer a, Integer b) {
		String res;
		if (a <= 4) {	// Case 1
			res = a + "." + b;
		} else if (b <= 4) {	// Case 2
			res = b + "-" + a;
		} else {	// Other cases
			res = g_rec_no_mem(a / 2, b - 2) + "," + g_rec_no_mem(a - 2, b / 2) + "," + g_rec_no_mem(a - 1, b - 1);
		}
		return res;
	}

	public static String g_rec_mem(Integer a, Integer b) {	// First recursive call
		Map<Pair, String> m = new HashMap<>();	// Memory creation
		return g_rec_mem(a, b, m);
	}

	public static String g_rec_mem(Integer a, Integer b, Map<Pair, String> m) {
		if (!m.containsKey(Pair.of(a, b))) {	// If result is not in memory, compute it
			String res;
			
			if (a <= 4) {
				res = a + "." + b;
			} else if (b <= 4) {
				res = b + "-" + a;
			} else {
				res = g_rec_mem(a / 2, b - 2, m) + "," + g_rec_mem(a - 2, b / 2, m) + "," + g_rec_mem(a - 1, b - 1, m);
			}
			
			m.put(Pair.of(a, b), res);	// Store value
		}
		return m.get(Pair.of(a, b));	// Return stored value
	}

	public static String g_iterative(Integer a, Integer b) {
		Map<Pair, String> m = new HashMap<>();	// Implemented with memory
		
		for (int i = 0; i <= a; i++) {	// Loop for 1st pairs' value
			for (int j = 0; j <= b; j++) {	// Loop for 2nd pairs' value
				String res;
				
				if (i <= 4) {
					res = i + "." + j;
				} else if (j <= 4) {
					res = j + "-" + i;
				} else {
					res = m.get(Pair.of(i / 2, j - 2)) + "," + m.get(Pair.of(i - 2, j / 2)) + ","
							+ m.get(Pair.of(i - 1, j - 1));
				}
				m.put(Pair.of(i, j), res);	// Store values from (0,0) to (a,b)
			}
		}
		return m.get(Pair.of(a, b));	// Return wanted value
	}

}
