package exercisesTestsP2;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class Exercise1Test {
	
	private static Integer nMin = 100; // n mínimo para el cálculo de potencia
	private static Integer nMax = 10000; // n máximo para el cálculo de potencia
	private static Integer nIncr = (nMax-nMin)/15; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 15; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 1000; // número de iteraciones para warmup
	
	
	public static void genDataDoubleIt() {
		String file = "ficheros_generados/double_it.txt";
		Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise1.exercise1_double_it(t, 3));
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
	
	public static void genDataDoubleRec() {
		String file = "ficheros_generados/double_rec.txt";
		Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise1.exercise1_double_rec(t, 3));
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
	
	public static void genDataBigIntIt() {
		String file = "ficheros_generados/bigInt_it.txt";
		Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise1.exercise1_bigInt_it(t, 3));
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
	
	public static void genDataBigIntRec() {
		String file = "ficheros_generados/bigInt_rec.txt";
		Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise1.exercise1_bigInt_rec(t, 3));
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}

	
	public static void showDoubleIt() {
		String file = "generated_files/double_it.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void showDoubleRec() {
		String file = "generated_files/double_rec.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void showBigIntIt() {
		String file = "generated_files/bigInt_it.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void showBigIntRec() {
		String file = "generated_files/bigInt_rec.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	

	
	
	public static void showCombinedAll() {
		MatPlotLib.showCombined("Tiempos",
				List.of("generated_files/double_it.txt","generated_files/double_rec.txt","generated_files/bigInt_it.txt", "generated_files/bigInt_rec.txt"), 
				List.of("double_it","double_rec","bigInt_it", "bigInt_rec"));
	}
	
	public static void showCombinedDouble() {
		MatPlotLib.showCombined("Tiempos",
				List.of("generated_files/double_it.txt","generated_files/double_rec.txt"), 
				List.of("double_it","double_rec"));
	}

	

	public static void main(String[] args) {
//		genDataDoubleIt();
//		genDataDoubleRec();
//		genDataBigIntIt();
//		genDataBigIntRec();

		showDoubleIt();
		showDoubleRec();
		showBigIntIt();
		showBigIntRec();
				
		showCombinedAll();
		showCombinedDouble();
	}
}
