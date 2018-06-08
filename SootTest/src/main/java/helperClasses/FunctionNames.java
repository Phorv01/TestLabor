package helperClasses;

public enum FunctionNames {

	SetVariable("setVariable", 2, 1, "Defined"), GetVariable("getVariable", 1, 1, "Used"), GetVariable2("getVariable", 2, 1, "Used"), RemoveVariable("removeVariable", 1, 1, "Deleted");
	
	private String name;
	
	private int numberOfArgBoxes;
	
	private int locationOfBox;
	
	private String operationType;
	
	private FunctionNames(final String name, int number, int loc, String type) {
		this.name = name;
		this.numberOfArgBoxes = number;
		this.locationOfBox = loc;
		this.operationType = type;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberOfArgBoxes() {
		return numberOfArgBoxes; 
	}	
	
	public int getLocation() {
		return locationOfBox;
	}
	
	public String getOperationType() {
		return operationType;
	}
	
	public static FunctionNames findByNameAndNumberOfBoxes(String name, int numberOfBoxes) {
		
		for(FunctionNames f : values()) {
			if(f.getName().equals(name) && f.getNumberOfArgBoxes() == numberOfBoxes) {
				return f;
			}
		}
		return null;
	}
}
