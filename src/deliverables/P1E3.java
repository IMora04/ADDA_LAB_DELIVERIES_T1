package deliverables;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class P1E3 {

	public static List<String> ex3_iterative(String fileA, String fileB) {

		List<String> res = new ArrayList<String>();
		try {
			Iterator<String> iterator1 = Files.lines(Path.of(fileA)).iterator();	//Initializing iterators and variables used
			Iterator<String> iterator2 = Files.lines(Path.of(fileB)).iterator();

			int counterTo2 = 0;
			boolean isFirst = true;
			
			while (iterator1.hasNext() && iterator2.hasNext()) {	//If both of them have next, iterate
				if (isFirst) {	//IsFirst is true during 2 iterations
					res.add(iterator1.next());
				} else {
					res.add(iterator2.next());
				}
				if (counterTo2 == 1) {
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
			}									//Both don't have next
			
			return res;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
