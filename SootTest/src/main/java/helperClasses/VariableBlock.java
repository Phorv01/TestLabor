package helperClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

	public List<ProcessVariable> getProcessVariables() {
		return processVariables;
	}

	public void addProcessVariable(ProcessVariable processVariable) {
		this.processVariables.add(processVariable);
	}

	public Map<String, ProcessVariable> getProcessVariablesMapped() {

		Map<String, ProcessVariable> variables = new HashMap<String, ProcessVariable>();
		for (ProcessVariable pv : processVariables) {

			variables.put(pv.getName(), pv);
		}

		return variables;
	}

	public boolean listProcessVariableEquals(List<ProcessVariable> list1, List<ProcessVariable> list2) {

		final List<ProcessVariable> allVariables = new ArrayList<ProcessVariable>();
		allVariables.addAll(list2);
		allVariables.addAll(list1);
		for(ProcessVariable pv : allVariables) {
			if(!list1.contains(pv) || !list2.contains(pv)) {
				return false;
			}
			
		}
		
		return true;
	}
	
	@Override
	public boolean equals(final Object o) {
		
		if(o instanceof VariableBlock) {
			final VariableBlock v = (VariableBlock) o;
			if(block.equals(v.getBlock()) && listProcessVariableEquals(processVariables, v.getProcessVariables()) ) {
				return true;
			}
		}
		
		return false;
	}
	
}
