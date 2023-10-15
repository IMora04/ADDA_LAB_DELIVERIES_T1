package deliverables;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P1E1 {
	
	public record EnteroCadena(Integer a, String s) {	//Define record used in the ex.
		public static EnteroCadena of(Integer a, String s) {
			return new EnteroCadena(a, s);
		}
	}
	
	public static String exercise1 (Integer varA, Integer varB) {	//Given ex.
		UnaryOperator<EnteroCadena> nx = elem ->
		{
		return EnteroCadena.of(elem.a()+3,	//Each "a" contains string with previous a + "*"/"!"
		elem.a()%2==0?
		elem.a()+"*":
		elem.a()+"!");
		};
		return Stream
		.iterate(EnteroCadena.of(varA,"A"), elem -> elem.a() < varB, nx)
		.filter(elem -> elem.a()%10 != 0)
		.map(elem -> elem.s())
		.collect(Collectors.joining("-"));
		}
	
	public static String iterativeSol(Integer varA, Integer varB) {
		String res = "";				//Accumulator
		Integer a = varA;				//Sequence
		String b = "A";					//f(a) to be accumulated
		while(a < varB) {
			if(a%10 != 0) {
				res = res + "-" + b;	//If the actual value is not divisible, accumulate previous b
			}
			if(a%2 == 0) {
				b = a + "*";			//We accumulate the previous value of a + "*"/"!"
			} else {
				b = a + "!";
			}
			a = a + 3;					//Update sequence
		}
		return res.substring(1);		//Remove first "-"
	}
	
	public static String finalRecursiveSol(Integer varA, Integer varB) {	//First call of the recursive solution
		String res = "";
		String b = "A";
		return finalRecursiveSolAux(varA, varB, res, b);
	}
	
	public static String finalRecursiveSolAux(Integer a, Integer varB, String res, String b) {
		if(a < varB) {										//Same conditions as iterative solution
			if(a%10 != 0) {
				res = res + "-" + b;
			}
			if(a%2 == 0) {
				b = a + "*";
			} else {
				b = a + "!";
			}
			a = a + 3;
			res = finalRecursiveSolAux(a, varB, res, b);	//Recursive call with updated values
		} else {
			res = res.substring(1);
		}
		return res;
	}

}
