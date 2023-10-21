package auxTypes;

import java.util.Iterator;

public class AuxTypes {
	
	public record EnteroCadena(Integer a, String s) {	// USED IN E1
		public static EnteroCadena of(Integer a, String s) {
			return new EnteroCadena(a, s);
		}
	}
		
	public record Pair(Integer a, Integer b) {	// USED IN E2 (Every function) AND E4 (Just of)
		public static Pair of(Integer a, Integer b) {
			return new Pair(a, b);
		}

		public Integer res() {	// Accumulated value for each pair
			if (a() < 2 || b() < 2) {
				return a() * b();
			} else if (a() > b()) {
				return a();
			} else {
				return b();
			}
		}

		public Pair nx() {	// Next value of each pair
			if (a() < 2 || b() < 2) {
				return null;	// For being able to identify the base case (end of the algorithm)
			} else if (a() > b()) {
				return Pair.of(a() % b(), b() - 1);
			} else {
				return Pair.of(a() - 2, b() / 2);
			}
		}
	}
	
	public record IteratorPair(Iterator<String> i1, Iterator<String> i2, int counterTo2, boolean isFirst) {	// USED IN E3
		public static IteratorPair of(Iterator<String> i1, Iterator<String> i2) {	// New pair creation
			return new IteratorPair(i1, i2, 0, true);
		}
		public boolean hasNext() {	// Continue if any has next
			return (i1.hasNext() || i2.hasNext());
		}
		public String resAndUpdate() {	// Mapping function, that also updates the iterator
			if(isFirst) {
				if(i1.hasNext()) {	// Now it doesn't only depend on isFirst
					return i1.next();
				} else {
					return i2.next();
				}
			} else {
				if(i2.hasNext()) {
					return i2.next();
				} else {
					return i1.next();
				}
			}
		}
		public IteratorPair nx() {	// When it is called, i1 or i2 has already been updated in resAndUpdate()
			return new IteratorPair(i1,
					i2,
					counterTo2==1?0:1,
					counterTo2==1?!isFirst:isFirst);
		}
	}

}
