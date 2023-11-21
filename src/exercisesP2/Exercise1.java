package exercisesP2;

import java.math.BigInteger;

public class Exercise1 {	
		
	public static Double exercise1_double_rec(Integer a, Integer b) {
		Double res = 10.;
		return exercise1_double_aux(a, b, res);
	}
	
	public static Double exercise1_double_aux(Integer a, Integer b, Double res) {
		if (a >= 6) {
			res *= Math.pow(a, b);
			return exercise1_double_aux(a-1, b, res);
		} else { 
			return res; 
		}
	}
	
	public static Double exercise1_double_it(Integer a, Integer b) {
		Double res = 10.;
		while(a >= 6) {
			res *= Math.pow(a, b);
			a -= 1;
		}
		return res;
	}
	
	public static BigInteger exercise1_bigInt_rec(Integer a, Integer b) {
		BigInteger res = BigInteger.valueOf(10);
		BigInteger new_a = BigInteger.valueOf(a);
		return exercise1_bigInt_aux(new_a, b, res);
	}
	
	public static BigInteger exercise1_bigInt_aux(BigInteger a, Integer b, BigInteger res) {
		if (a.compareTo(BigInteger.valueOf(6)) >= 0) {
			res = res.multiply(a.pow(b));
			return exercise1_bigInt_aux(a.subtract(BigInteger.valueOf(1)), b, res);
		} else {
			return res;
		}
	}
	
	public static BigInteger exercise1_bigInt_it(Integer a, Integer b) {
		BigInteger res = BigInteger.valueOf(10);
		while (a >= 6) {
			res = res.multiply(BigInteger.valueOf((long)Math.pow(a, b)));
			a -= 1;
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(exercise1_bigInt_rec(10, 3));
		System.out.println(exercise1_bigInt_it(10, 3));
		System.out.println(exercise1_double_it(10, 3));
		System.out.println(exercise1_double_rec(10, 3));
	}
		
}
