package exercisesTestsP2;


import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Exponential;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.Polynomial;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class Exercise1Test {
	
	private static Integer nMin = 100; // n mínimo para el cálculo de potencia
	private static Integer nMax = 10000; // n máximo para el cálculo de potencia
//	private static Integer nIncr = 5100; // incremento en los valores de n del cálculo de potencia
	private static Integer nIncr = (nMax-nMin)/10; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 5; // número de iteraciones para cada medición de tiempo
//	private static Integer nIterWarmup = 100; // número de iteraciones para warmup
	private static Integer nIterWarmup = 1000; // número de iteraciones para warmup
	
//	private static Double a = 100.;
//	private static Double a = 3.;
	
/*	
	public static void genDataPr() {
		String file = "ficheros_generados/pr.txt";
		Function<Integer,Long> f1 = GenData.time(t -> Ejemplo1.potenciaR(a,t));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
*/
	
	public static void genDataDoubleIt() {
		String file = "ficheros_generados/double_it.txt";
		Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise1.exercise1_double_it(t, 3));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
	
	
	public static void genDataDoubleRec() {
		String file = "ficheros_generados/double_rec.txt";
		Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise1.exercise1_double_rec(t, 3));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
	
	public static void genDataBigIntIt() {
		String file = "ficheros_generados/bigInt_it.txt";
		Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise1.exercise1_bigInt_it(t, 3));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
	
	public static void genDataBigIntRec() {
		String file = "ficheros_generados/bigInt_rec.txt";
		Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise1.exercise1_bigInt_rec(t, 3));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}

	
	
	public static void showDoubleIt() {
		String file = "ficheros_generados/double_it.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void showDoubleRec() {
		String file = "ficheros_generados/double_rec.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void showBigIntIt() {
		String file = "ficheros_generados/bigInt_it.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void showBigIntRec() {
		String file = "ficheros_generados/bigInt_rec.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	

	
	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/double_it.txt","ficheros_generados/double_rec.txt","ficheros_generados/bigInt_it.txt", "ficheros_generados/bigInt_rec.txt"), 
				List.of("double_it","double_rec","bigInt_it", "bigInt_rec"));
		//List.of("ficheros_generados/double_it.txt","ficheros_generados/double_rec.txt"), 
		//List.of("double_it","double_rec"));
	}
	

	public static void main(String[] args) {
		//genDataDoubleIt();
		//genDataDoubleRec();
//		genDataBigIntIt();
//		genDataBigIntRec();

//		showDoubleIt();
//		showDoubleRec();
//		showBigIntIt();
//		showBigIntRec();
				
		showCombined();
	}

}
