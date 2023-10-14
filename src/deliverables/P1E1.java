package deliverables;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P1E1 {
	
	public record EnteroCadena(Integer a, String s) {
		public static EnteroCadena of(Integer a, String s) {
			return new EnteroCadena(a, s);
		}
	}
	
	public static String ejercicio1 (Integer varA, Integer varB) {
		UnaryOperator<EnteroCadena> nx = elem ->
		{
		return EnteroCadena.of(elem.a()+3,
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
		String res = "";
		Integer a = varA;
		String b = "A";
		while(a < varB) {
			if(a%10 != 0) {
				res = res + "-" + b;
			}
			if(a%2 == 0) {
				b = a + "*";
			} else {
				b = a + "!";
			}
			a = a + 3;
		}
		return res.substring(1);
		//while ECa < varb
		//if a%10 != 0
			//a = a+3
			//if a%2 == 0
				//s = a + *
			//else
				//s = a + !
	}
	
	public static String finalRecursiveSol(Integer varA, Integer varB) {
		String res = "";
		String b = "A";
		return finalRecursiveSolAux(varA, varB, res, b);
	}
	
	public static String finalRecursiveSolAux(Integer a, Integer varB, String res, String b) {
		if(a < varB) {
			if(a%10 != 0) {
				res = res + "-" + b;
			}
			if(a%2 == 0) {
				b = a + "*";
			} else {
				b = a + "!";
			}
			a = a + 3;
			res = finalRecursiveSolAux(a, varB, res, b);
		} else {
			res = res.substring(1);
		}
		return res;
	}

}
