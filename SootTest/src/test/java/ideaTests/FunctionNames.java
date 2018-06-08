package ideaTests;

public enum FunctionNames {

	SetVariable("setVariable", 2, 1), GetVariable("getVariable", 1, 1), GetVariable2("getVariable", 2, 1);
	
	private String name;
	
	private int numberOfArgBoxes;
	
	private int boxOfVariableName;
	
	private FunctionNames(final String name, int number, int box) {
		this.name = name;
		this.numberOfArgBoxes = number;
		this.boxOfVariableName = box;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberOfArgBoxes() {
		return numberOfArgBoxes; 
	}
	
	public static FunctionNames findByNameAndNumberOfBoxes(String name, int numberOfBoxes) {

        for (FunctionNames f : values()) {
            if (f.getName().equals(name) && f.getNumberOfArgBoxes() == numberOfBoxes) {
                return f;
            }
        }
        return null;
    }
}
