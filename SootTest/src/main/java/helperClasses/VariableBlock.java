package helperClasses;

import java.util.List;

import soot.toolkits.graph.Block;

public class VariableBlock {
	
	private Block block;
	private List<ProcessVariable> processVariables;
	
	public VariableBlock(Block block, List<ProcessVariable> pvs) {
		this.block = block;
		this.processVariables = pvs;
	}
	
	public void setBlock(Block block) {
		
		this.block = block;
	}
	
	public Block getBlock() {
		return block;
	}

	public void addProcessVariable(ProcessVariable processVariable) {
		this.processVariables.add(processVariable);
	}
}
