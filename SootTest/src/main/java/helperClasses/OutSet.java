package helperClasses;

import java.util.List;

import soot.toolkits.graph.Block;

public class OutSet {
	
	private int id;
	
	private List<VariableBlock> variableBlocks;
	
	public OutSet(int id, List<VariableBlock> vbs) {
		
		this.id = id;
		this.variableBlocks = vbs;
	}
	
	public VariableBlock getVariableBlock(Block b) {
		
		for(VariableBlock vb : variableBlocks) {
			
			if(vb.getBlock().equals(b)) {
				return vb;
			}			
		}
		return null;
	}
	
	public void addVariableBlock(VariableBlock vb) {
		
		this.variableBlocks.add(vb);
	}

}
