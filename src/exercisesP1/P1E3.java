package exercisesP1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import auxTypesP1.AuxTypes.IteratorPair;

public class P1E3 {

	public static List<String> ex3_iterative(String fileA, String fileB) {

		List<String> res = new ArrayList<String>();
		try {	// Try-catch block
			Iterator<String> iterator1 = Files.lines(Path.of(fileA)).iterator();	//Initializing iterators and variables used
			Iterator<String> iterator2 = Files.lines(Path.of(fileB)).iterator();

			int counterTo2 = 0;
			boolean isFirst = true;
			
			while (iterator1.hasNext() && iterator2.hasNext()) {	//If both of them have next, iterate
				if (isFirst) {	//IsFirst is true for 2 iterations, then false for other 2
					res.add(iterator1.next());
				} else {
					res.add(iterator2.next());
				}
				if (counterTo2 == 1) {	// isFirst update using a counter
					counterTo2 = 0;
					isFirst = !isFirst;
				} else {
					counterTo2 = 1;
				}
			}
			
			if (!iterator1.hasNext()) {		//If the iteration stopped, then
				while (iterator2.hasNext()) {	//Iterator 1 doesn't have next
					res.add(iterator2.next());
				}
			} else if (!iterator2.hasNext()) {	//Iterator 2 doesn't have next
				while (iterator1.hasNext()) {
					res.add(iterator1.next());
				}
			}									// Any of them has next

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static List<String> ex3_final_recursive(String fileA, String fileB) {	//First recursive calls
		List<String> res = new ArrayList<String>();
		Iterator<String>  iterator1 = null;
		Iterator<String> iterator2 = null;
		int counterTo2 = 0;
		boolean isFirst = true;
		
		try {
			iterator1 = Files.lines(Path.of(fileA)).iterator();	//Initializing iterators and variables used
			iterator2 = Files.lines(Path.of(fileB)).iterator();
			res = ex3_final_aux(iterator1, iterator2, counterTo2, isFirst, res);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static List<String> ex3_final_aux(Iterator<String> iterator1, Iterator<String> iterator2, int counterTo2, boolean isFirst, List<String> res) {
		if (iterator1.hasNext() && iterator2.hasNext()) {	// If both of them have next, iterate
			if (isFirst) {	// isFirst is true for 2 iterations, then false for other 2
				res.add(iterator1.next());
			} else {
				res.add(iterator2.next()); 
			}
			if (counterTo2 == 1) {	// isFirst update using a counter
				counterTo2 = 0;
				isFirst = !isFirst;
			} else {
				counterTo2 = 1;
			}
			return ex3_final_aux(iterator1, iterator2, counterTo2, isFirst, res);
			
		} else {
			if (!iterator1.hasNext()) {	// If the iteration stopped, then:
				while (iterator2.hasNext()) {	// Iterator 1 doesn't have next
					res.add(iterator2.next());
				}
			} else if (!iterator2.hasNext()) {	// Iterator 2 doesn't have next
				while (iterator1.hasNext()) {
					res.add(iterator1.next());
				}
			}	// Any of them has next
				return res;
		}		
	}
	
	public static List<String> ex3_functional(String fileA, String fileB) {
		Iterator<String>  i1 = null;	// Initializing iterators
		Iterator<String> i2 = null;
		try {
			i1 = Files.lines(Path.of(fileA)).iterator();
			i2 = Files.lines(Path.of(fileB)).iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Stream.iterate(IteratorPair.of(i1, i2), i->i.hasNext(), i->i.nx())
				.map(i->i.resAndUpdate())
				.toList();
	}
	

}
