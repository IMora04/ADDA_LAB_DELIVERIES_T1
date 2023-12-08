package auxTypesP3;

import java.util.Arrays;
import java.util.List;

public class User {
	
	private String userName;
	private double activityIndex;
	private List<String> hobbies;
	
	public String userName() {
		return this.userName;
	}
	
	public double activityIndex() {
		return this.activityIndex;
	}
	
	public List<String> hobbies() {
		return this.hobbies;
	}
	
	public User(String userName, double activityIndex, List<String> hobbies) {
		this.userName = userName;
		if(activityIndex >= 0 && activityIndex <= 5) {
			this.activityIndex = activityIndex;
		} else {
			throw new IllegalArgumentException("Invalid activity index value");
		}
		this.hobbies = hobbies;
	}
	
	public static User ofFormat(String[] values) {
		String[] listValues = values[2].replace("[", "").replace("]", "").split(";");
		return new User(values[0].trim(), Double.valueOf(values[1].trim()), Arrays.asList(listValues));
	}

}
