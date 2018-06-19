package helperClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soot.toolkits.graph.Block;

public class OutSet {

	private List<VariableBlock> variableBlocks;

	public OutSet(List<VariableBlock> vbs) {

		this.variableBlocks = vbs;
	}

	public List<VariableBlock> getAllVariableBlocks() {
		return variableBlocks;
	}

	public VariableBlock getVariableBlockByBlock(Block b) {

		for (VariableBlock vb : variableBlocks) {

			if (vb.getBlock().equals(b)) {
				return vb;
			}
		}
		return null;
	}

	public void addVariableBlock(VariableBlock vb) {

		this.variableBlocks.add(vb);
	}

	public List<ProcessVariable> getProcessVariablesPerBlock(VariableBlock vb) {
		List<ProcessVariable> variableList = new ArrayList<ProcessVariable>();
		variableList.addAll(vb.getProcessVariables());
		return variableList;
	}

	public Map<String, ProcessVariable> getAllProcessVariables() {

		Map<String, ProcessVariable> variables = new HashMap<String, ProcessVariable>();

		for (VariableBlock vb : variableBlocks) {

			variables.putAll(vb.getProcessVariablesMapped());

		}
		return variables;
	}

	@Override
	public boolean equals(Object o) {

		if(o instanceof OutSet) {
			final OutSet outSet = (OutSet) o;
			
			List<VariableBlock> variables = new ArrayList<VariableBlock>();
			variables.addAll(variableBlocks);
			variables.addAll(outSet.getAllVariableBlocks());
			
			for (VariableBlock v : variables) {
				if(!variableBlocks.contains(v) || !outSet.getAllVariableBlocks().contains(v)) {
					return false;
				}
			}
			return true;
			
		}
		return false;
	}
}
