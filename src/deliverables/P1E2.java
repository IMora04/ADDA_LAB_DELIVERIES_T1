package deliverables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P1E2 {
	
	public static List<Integer> f_non_final(Integer a, Integer b) {
		return f_non_final_aux(a, b, new ArrayList<Integer>());
	}
	
	public static List<Integer> f_non_final_aux(Integer a, Integer b, List<Integer> res) {
		if(a < 2 || b < 2) {
			res.add(Integer.valueOf(a*b));
		} else if(a > b) {
			res = f_non_final_aux(a%b, b-1, res);
			res.add(a);
		} else {
			res = f_non_final_aux(a-2, b/2, res);
			res.add(b);
		}
		return res;
	}
	
	public static List<Integer> f_iterative(Integer a, Integer b) {
		Integer valA = a;
		Integer valB = b;
		List<Integer> res = new ArrayList<>();
		while(!(valA < 2 || valB < 2)) {
			if(valA > valB) {							
				res.add(0, valA);
				valA = valA % valB;
				valB = valB - 1;
			} else {
				res.add(0, valB);
				valA = valA - 2;
				valB = valB / 2;
			}
		}
		res.add(0, Integer.valueOf(valA * valB));		//End of loop -> Last case
		return res;
	}
	
	public static List<Integer> f_final(Integer a, Integer b) {
		return f_final_aux(a, b, new ArrayList<Integer>());
	}
	
	public static List<Integer> f_final_aux(Integer a, Integer b, List<Integer> res) {
		if(!(a < 2 || b < 2)) {
			if(a > b) {
				res.add(0, a);
				f_final_aux(a%b, b-1, res);
			} else {
				res.add(0, b);
				f_final_aux(a-2, b/2, res);
			}
		} else {
			res.add(0, Integer.valueOf(a*b));
		}
		return res;
	}
	
	public record Pair(Integer a, Integer b) {
		public static Pair of(Integer a, Integer b) {
			return new Pair(a, b);
		}
		public Integer res() {
			if (a() < 2 || b() < 2) {
				return a()*b();
			} else if (a() > b()) {
				return a();
			} else {
				return b();
			}
		}
		public Pair nx() {
			if (a() < 2 || b() < 2) {
				return null;
			} else if (a() > b()) {
				return Pair.of(a() % b(), b() - 1);
			} else {
				return Pair.of(a() - 2, b() / 2);
			}
		}
	}
		
	public static List<Integer> f_functional(Integer a, Integer b) {
		List<Integer> res = Stream.iterate(
					Pair.of(a,b),
					p -> !(p == null),
					p -> p.nx())
				.map(p -> p.res())
				.collect(Collectors.toList());
		Collections.reverse(res);
		return res;
	}
	
}


