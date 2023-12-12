package auxTypesP3;

public class Neighbourship {

	//Barco Pirata, Casa del Terror, 150, 2.0
	private String ride1;
	private String ride2;
	private double distance;
	private double time;
	
	public String getRide1() {
		return ride1;
	}
	public String getRide2() {
		return ride2;
	}
	public double getDistance() {
		return distance;
	}
	public double getTime() {
		return time;
	}
	
	public Neighbourship(String ride1, String ride2, double distance, double time) {
		this.ride1 = ride1;
		this.ride2 = ride2;
		this.distance = distance;
		this.time = time;
	}
	
	public static Neighbourship ofFormat(String[] values) {
		return new Neighbourship(values[0],
				values[1],
				Double.valueOf(values[2].trim()),
				Double.valueOf(values[3].trim()));
	}
	
}
