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
	
	private static Integer nMin = 0; // n mínimo para el cálculo de potencia
	private static Integer nMax = 10000; // n máximo para el cálculo de potencia
//	private static Integer nIncr = 5100; // incremento en los valores de n del cálculo de potencia
	private static Integer nIncr = nMax/30; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 5; // número de iteraciones para cada medición de tiempo
//	private static Integer nIterWarmup = 100; // número de iteraciones para warmup
	private static Integer nIterWarmup = 2500; // número de iteraciones para warmup

	
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
//			Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
			GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
		}
		
	}
	
	public static void showData() {
		for(int u = 1; u <= 256; u = u * 4) {
			int v = u;
			String file = "ficheros_generados/umbral" + v + ".txt";
			List<WeightedObservedPoint> data = DataFile.points(file);
			Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))); //
			pl.fit(data);
			System.out.println(pl.getExpression());
			System.out.println(pl.getEvaluation().getRMS());
			MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
		}
	}
	
	public static void showCombined() {	
		MatPlotLib.showCombined("Tiempos",
				//List.of("ficheros_generados/umbral1_.txt","ficheros_generados/umbral4_.txt","ficheros_generados/umbral16_.txt", "ficheros_generados/umbral64_.txt", "ficheros_generados/umbral256_.txt"), 
				//DONE WITH _ TXTS
				List.of("ficheros_generados/umbral1.txt","ficheros_generados/umbral4.txt","ficheros_generados/umbral16.txt", "ficheros_generados/umbral64.txt", "ficheros_generados/umbral256.txt"), 
				List.of("Umbral 1","Umbral 4","Umbral 16", "Umbral 64", "Umbral 256"));
	}

	
	public static void main(String[] args) {
		genData();
		showData();
		showCombined();
	}

}