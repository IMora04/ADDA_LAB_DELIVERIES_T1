package deliverables;

import java.util.HashMap;
import java.util.Map;

public class P1E4 {
	
	public static String g_rec_no_mem(Integer a, Integer b) {
		String res; 
		if(a <= 4) {
			res = a + "." + b;
		} else if (b <= 4) {
			res = b + "-" + a;
		} else {
			res = g_rec_no_mem(a/2, b-2) + "," + g_rec_no_mem(a-2, b/2) + "," + g_rec_no_mem(a-1, b-1);
		}
		return res;
	}
	
	public record Pair(Integer a, Integer b) {
		public static Pair of(Integer a, Integer b) {
			return new Pair(a, b);
		}
	}
		
	public static String g_rec_mem(Integer a, Integer b) {
		Map<Pair, String> m = new HashMap<>();
		return g_rec_mem(a, b, m);
	}
	
	public static String g_rec_mem(Integer a, Integer b, Map<Pair, String> m) {
		String res;
		if(!m.containsKey(Pair.of(a, b))) {
			if(a <= 4) {
				res = a + "." + b;
			} else if (b <= 4) {
				res = b + "-" + a;
			} else {
				res = g_rec_mem(a/2, b-2, m) + "," + g_rec_mem(a-2, b/2, m) + "," + g_rec_mem(a-1, b-1, m);
			}
			m.put(Pair.of(a, b), res);
		}
		return m.get(Pair.of(a, b));
	}
	
	public static String g_iterative(Integer a, Integer b) {
		Map<Pair, String> m = new HashMap<>();
		for(int i = 0; i <= a; i++) {
			for(int j = 0; j <= b; j++) {
				String res = "";
				if(i <= 4) {
					res = i + "." + j;
				} else if (j <= 4) {
					res = j + "-" + i;
				} else {
					res = m.get(Pair.of(i/2, j-2)) + "," + m.get(Pair.of(i-2, j/2)) + "," + m.get(Pair.of(i-1, j-1));
				}
				m.put(Pair.of(i, j), res);
			}
		}
		return m.get(Pair.of(a, b));	
	}

}
