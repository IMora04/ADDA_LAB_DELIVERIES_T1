package exercisesTestsP2;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.common.List2;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.Polynomial;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;
import us.lsi.math.Math2;

public class Exercise2Test {
	
	private static Integer nMin = 1; // n mínimo para el cálculo de potencia
	private static Integer nMax = 50000; // n máximo para el cálculo de potencia
	private static Integer nIncr = 2; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 5000; // número de iteraciones para warmup

	public static void genData() {
		List<Double> ls = List2.empty();
		for(int i = 0; i<nMax; i++){
			ls.add(Math2.getDoubleAleatorio(0., 1000.));
		}
		for(int u = 1; u <= 256; u = u * 4) {
			System.out.println(u);
			int v = u;
			String file = "ficheros_generados/umbral" + v + ".txt";
			Function<Integer,Long> f1 = GenData.time(t -> exercisesP2.Exercise2.mergeSort(List2.ofCollection(ls.subList(0, t)), v));
			GenData.tiemposEjecucionGeometrica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
		}
		
	}
	
	public static void showData() {
		for(int u = 1; u <= 256; u = u * 4) {
			int v = u;
			String file = "generated_files/umbral" + v + ".txt";
			List<WeightedObservedPoint> data = DataFile.points(file);
			Fit pl = Polynomial.of(2);
			pl.fit(data);
			System.out.println(pl.getExpression());
			System.out.println(pl.getEvaluation().getRMS());
			MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
		}
	}
	
	public static void showCombined() {	
		MatPlotLib.showCombined("Tiempos",
				List.of("generated_files/umbral1.txt","generated_files/umbral4.txt","generated_files/umbral16.txt", "generated_files/umbral64.txt", "generated_files/umbral256.txt"), 
				List.of("Umbral 1","Umbral 4","Umbral 16", "Umbral 64", "Umbral 256"));
	}

	public static void main(String[] args) {
		//genData();
		showData();
		showCombined();
	}
	
}