package auxTypesP3;

public class UserRelation {
	
	private String followerUser;
	private String followedUser;
	private double interactionIndex;
	
	public String followerUser() {
		return this.followerUser;	
	}
	
	public String followedUser() {
		return this.followedUser;
	}
	
	public double interactionIndex() {
		return this.interactionIndex;
	}
	
	public UserRelation(String followerUser, String followedUser, double interactionIndex) {
		this.followedUser = followedUser;
		this.followerUser = followerUser;
		if(interactionIndex >= 0 && interactionIndex <= 5) {
			this.interactionIndex = interactionIndex;
		} else {
			throw new IllegalArgumentException("Invalid interaction index value");
		}
	}
	public static UserRelation ofFormat(String[] values) {
		return new UserRelation(values[0].trim(), values[1].trim(), Double.valueOf(values[2].trim()));
	}

	
}
