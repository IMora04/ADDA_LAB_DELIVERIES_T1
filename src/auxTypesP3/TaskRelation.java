package auxTypesP3;

public class TaskRelation {
	
	private String toDo;
	private String isNeeded;
	
	public String getToDo() {
		return toDo;
	}
	public String getIsNeeded() {
		return isNeeded;
	}
	
	public TaskRelation(String toDo, String isNeeded) {
		this.toDo = toDo;
		this.isNeeded = isNeeded;
	}
	
	public static TaskRelation ofFormat(String[] values) {
		return new TaskRelation(values[0].trim(), values[1].trim());
	}

}
