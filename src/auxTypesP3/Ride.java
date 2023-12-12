package auxTypesP3;

public class Ride {

	private String name;
	private int waitTime;
	private double popularity;
	private int duration;
	
	public String getName() {
		return name;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public double getPopularity() {
		return popularity;
	}

	public int getDuration() {
		return duration;
	}
	
	public Ride(String name, int waitTime, double popularity, int duration) {
		if(popularity < 0 || popularity > 10) {
			throw new IllegalArgumentException("Incorrect value for popularity");
		} else {
			this.name = name;
			this.waitTime = waitTime;
			this.popularity = popularity;
			this.duration = duration;
		}
	}
	
	public static Ride ofFormat(String[] values) {
		return new Ride(values[0],
				Integer.valueOf(values[1].trim()),
				Double.valueOf(values[2].trim()),
				Integer.valueOf(values[3].trim()));
	}
	
}
