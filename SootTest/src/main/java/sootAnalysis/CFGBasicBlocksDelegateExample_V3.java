package sootAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import soot.Body;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeStmt;
import soot.jimple.internal.JInterfaceInvokeExpr;
import soot.jimple.internal.JSpecialInvokeExpr;
import soot.options.Options;
import soot.toolkits.graph.Block;
import soot.toolkits.graph.BlockGraph;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.ClassicCompleteBlockGraph;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.SimpleDominatorsFinder;
import soot.toolkits.graph.UnitGraph;
import soot.util.Chain;

public class CFGBasicBlocksDelegateExample_V3 {

	public static void main(String[] args) // throws IOException
	{
		String projectPath = System.getProperty("user.dir");

		Options.v().setPhaseOption("jb", "use-original-names:true");

		System.setProperty("soot.class.path",
				"D:\\Projects\\TestLabor\\SootTest\\target\\classes;C:\\Program Files\\Java\\jdk1.8.0_161\\jre\\lib\\rt.jar;"
						+ "C:\\Program Files\\Java\\jdk1.8.0_161\\jre\\lib\\jce.jar;"
						+ "C:\\Users\\b62\\Downloads\\java_ee_sdk-8\\glassfish5\\glassfish\\modules\\javax.persistence.jar;"
						+ "C:\\Users\\b62\\Thesis\\sts\\sts-bundle\\sts-3.9.2.RELEASE\\plugins\\org.junit_4.12.0.v201504281640\\junit.jar;"
						+ "C:\\Users\\b62\\Thesis\\sts\\sts-bundle\\sts-3.9.2.RELEASE\\plugins\\org.hamcrest.core_1.3.0.v201303031735.jar;"
						+ "C:\\Users\\b62\\.m2\\repository\\junit\\junit\\4.12\\junit-4.12.jar;C:\\Users\\b62\\.m2\\repository\\org\\hamcrest\\hamcrest-core\\1.3\\hamcrest-core-1.3.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\bpm\\camunda-engine\\7.8.0\\camunda-engine-7.8.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\bpm\\model\\camunda-bpmn-model\\7.8.0\\camunda-bpmn-model-7.8.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\bpm\\model\\camunda-xml-model\\7.8.0\\camunda-xml-model-7.8.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\bpm\\model\\camunda-cmmn-model\\7.8.0\\camunda-cmmn-model-7.8.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\bpm\\dmn\\camunda-engine-dmn\\7.8.0\\camunda-engine-dmn-7.8.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\commons\\camunda-commons-utils\\1.4.0\\camunda-commons-utils-1.4.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\bpm\\model\\camunda-dmn-model\\7.8.0\\camunda-dmn-model-7.8.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\bpm\\dmn\\camunda-engine-feel-api\\7.8.0\\camunda-engine-feel-api-7.8.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\bpm\\dmn\\camunda-engine-feel-juel\\7.8.0\\camunda-engine-feel-juel-7.8.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\commons\\camunda-commons-logging\\1.4.0\\camunda-commons-logging-1.4.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\camunda\\commons\\camunda-commons-typed-values\\1.4.0\\camunda-commons-typed-values-1.4.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\apache\\commons\\commons-email\\1.2\\commons-email-1.2.jar;C:\\Users\\b62\\.m2\\repository\\javax\\mail\\mail\\1.4.1\\mail-1.4.1.jar;C:\\Users\\b62\\.m2\\repository\\javax\\activation\\activation\\1.1\\activation-1.1.jar;C:\\Users\\b62\\.m2\\repository\\org\\mybatis\\mybatis\\3.4.4\\mybatis-3.4.4.jar;C:\\Users\\b62\\.m2\\repository\\org\\springframework\\spring-beans\\3.1.2.RELEASE\\spring-beans-3.1.2.RELEASE.jar;C:\\Users\\b62\\.m2\\repository\\org\\springframework\\spring-core\\3.1.2.RELEASE\\spring-core-3.1.2.RELEASE.jar;C:\\Users\\b62\\.m2\\repository\\org\\springframework\\spring-asm\\3.1.2.RELEASE\\spring-asm-3.1.2.RELEASE.jar;C:\\Users\\b62\\.m2\\repository\\commons-logging\\commons-logging\\1.1.1\\commons-logging-1.1.1.jar;C:\\Users\\b62\\.m2\\repository\\joda-time\\joda-time\\2.1\\joda-time-2.1.jar;C:\\Users\\b62\\.m2\\repository\\ca\\mcgill\\sable\\soot\\3.0.0-SNAPSHOT\\soot-3.0.0-SNAPSHOT.jar;C:\\Users\\b62\\.m2\\repository\\commons-io\\commons-io\\2.6\\commons-io-2.6.jar;C:\\Users\\b62\\.m2\\repository\\org\\smali\\dexlib2\\2.2.2\\dexlib2-2.2.2.jar;C:\\Users\\b62\\.m2\\repository\\org\\smali\\util\\2.2.2\\util-2.2.2.jar;C:\\Users\\b62\\.m2\\repository\\com\\beust\\jcommander\\1.64\\jcommander-1.64.jar;C:\\Users\\b62\\.m2\\repository\\com\\google\\code\\findbugs\\jsr305\\1.3.9\\jsr305-1.3.9.jar;C:\\Users\\b62\\.m2\\repository\\com\\google\\guava\\guava\\18.0\\guava-18.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\ow2\\asm\\asm-debug-all\\5.2\\asm-debug-all-5.2.jar;C:\\Users\\b62\\.m2\\repository\\xmlpull\\xmlpull\\1.1.3.4d_b4_min\\xmlpull-1.1.3.4d_b4_min.jar;C:\\Users\\b62\\.m2\\repository\\org\\jboss\\jboss-common-core\\2.5.0.Final\\jboss-common-core-2.5.0.Final.jar;C:\\Users\\b62\\.m2\\repository\\org\\jboss\\logging\\jboss-logging-spi\\2.1.2.GA\\jboss-logging-spi-2.1.2.GA.jar;C:\\Users\\b62\\.m2\\repository\\pxb\\android\\axml\\2.0.0-SNAPSHOT\\axml-2.0.0-SNAPSHOT.jar;C:\\Users\\b62\\.m2\\repository\\ca\\mcgill\\sable\\polyglot\\2006\\polyglot-2006.jar;C:\\Users\\b62\\.m2\\repository\\heros\\heros\\1.0.0-SNAPSHOT\\heros-1.0.0-SNAPSHOT.jar;C:\\Users\\b62\\.m2\\repository\\org\\functionaljava\\functionaljava\\4.2\\functionaljava-4.2.jar;C:\\Users\\b62\\.m2\\repository\\ca\\mcgill\\sable\\jasmin\\3.0.0-SNAPSHOT\\jasmin-3.0.0-SNAPSHOT.jar;C:\\Users\\b62\\.m2\\repository\\ca\\mcgill\\sable\\java_cup\\0.9.2\\java_cup-0.9.2.jar;C:\\Users\\b62\\.m2\\repository\\org\\slf4j\\slf4j-api\\1.7.5\\slf4j-api-1.7.5.jar;C:\\Users\\b62\\.m2\\repository\\org\\slf4j\\slf4j-simple\\1.7.5\\slf4j-simple-1.7.5.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\jdt\\org.eclipse.jdt.core\\3.13.100\\org.eclipse.jdt.core-3.13.100.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.core.resources\\3.12.0\\org.eclipse.core.resources-3.12.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.core.expressions\\3.6.0\\org.eclipse.core.expressions-3.6.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.core.runtime\\3.13.0\\org.eclipse.core.runtime-3.13.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.osgi\\3.12.50\\org.eclipse.osgi-3.12.50.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.equinox.common\\3.9.0\\org.eclipse.equinox.common-3.9.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.core.jobs\\3.9.2\\org.eclipse.core.jobs-3.9.2.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.equinox.registry\\3.7.0\\org.eclipse.equinox.registry-3.7.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.equinox.preferences\\3.7.0\\org.eclipse.equinox.preferences-3.7.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.core.contenttype\\3.6.0\\org.eclipse.core.contenttype-3.6.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.equinox.app\\1.3.400\\org.eclipse.equinox.app-1.3.400.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.core.filesystem\\1.7.0\\org.eclipse.core.filesystem-1.7.0.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.text\\3.6.100\\org.eclipse.text-3.6.100.jar;C:\\Users\\b62\\.m2\\repository\\org\\eclipse\\platform\\org.eclipse.core.commands\\3.9.0\\org.eclipse.core.commands-3.9.0.jar");

		Scene.v().addBasicClass("delegateExamples.SimpleDelegateExample", SootClass.SIGNATURES);

		SootClass c = Scene.v().loadClassAndSupport("delegateExamples.SimpleDelegateExample");
		c.setApplicationClass();

		Chain<SootClass> list = Scene.v().getClasses();
		for (SootClass clazz : list) {
			String clazzName = clazz.getName();
			Scene.v().addBasicClass(clazzName, SootClass.SIGNATURES);
		}

		Scene.v().loadNecessaryClasses();

		// Retrieve the method and its body
		SootMethod m = c.getMethodByName("execute");
		Body b = m.retrieveActiveBody();

		// Build the CFG and run the analysis
		BlockGraph g = new ClassicCompleteBlockGraph(b);
		System.out.println(g);

		// Map<String, Integer> functions = new HashMap<String, Integer>();
		// functions.put("setVariable", 1);

		List<Block> graphTails = g.getTails();
		List<Block> graphHeads = g.getHeads();
		
		HashMap<Block, List<String>> setVariables = new HashMap<Block, List<String>>();
		List<String> getVariables = new ArrayList<String>();

		for (Block head : graphHeads) {
			HashMap<Block, List<String>> myResult = graphIterator(g, head, graphTails,
					new HashMap<Block, List<String>>(), setVariables, getVariables);
			System.out.println(myResult.values());
		}
	}

	/**
	 * 
	 * @param graph
	 * @param head
	 * @param blockTails
	 * @param visitedBoxOut
	 *            - keep track of already visited Blocks and their OUT for data-flow
	 *            analytics logic
	 */
	public static HashMap<Block, List<String>> graphIterator(BlockGraph graph, Block head, List<Block> blockTails,
			HashMap<Block, List<String>> visitedBoxOut, HashMap<Block, List<String>> setVariables, List<String> getVariables) {

		List<Block> successors = graph.getSuccsOf(head);

		// Collect the functions Unit by Unit via the blockIterator
		HashMap<Block, List<String>> resultFunctions = blockIteraror(head, setVariables, getVariables);
		HashMap<Block, List<String>> newOut = new HashMap<Block, List<String>>();
		newOut.putAll(resultFunctions);

		// Iterate through the successors of the Block, but with DFA logic restrictions
		// for avoiding infinite loops
		// while(change to any OUT occur)
		for (Block s : successors) {

			// if a sucessor Block was already visited only visit again if there is any
			// change to the OUT of current Block from before
			if (visitedBoxOut.containsKey(s)) {
				if (!newOut.get(head).equals(visitedBoxOut.get(head))) {
					
					visitedBoxOut.putAll(resultFunctions);
					graphIterator(graph, s, blockTails, visitedBoxOut, setVariables, getVariables);
				}

			}

			else {
				visitedBoxOut.putAll(resultFunctions);
				graphIterator(graph, s, blockTails, visitedBoxOut, setVariables, getVariables);
			}

		}

		if (blockTails.contains(head)) {
			visitedBoxOut.putAll(resultFunctions);
		}
		return visitedBoxOut;
	}

	public static HashMap<Block, List<String>> blockIteraror(Block block, HashMap<Block, List<String>> setVariables, List<String> getVariables) {

		List<String> variables = new ArrayList<String>();

		Iterator unitIt = block.iterator();
		while (unitIt.hasNext()) {
			Unit unit = (Unit) unitIt.next();
			if (unit instanceof AssignStmt || unit instanceof InvokeStmt) {

				if (unit instanceof InvokeStmt) {

					if (((InvokeStmt) unit).getInvokeExprBox().getValue() instanceof JInterfaceInvokeExpr) {

						JInterfaceInvokeExpr expr = (JInterfaceInvokeExpr) ((InvokeStmt) unit).getInvokeExprBox()
								.getValue();
						if (expr.getMethodRef().name().equals("setVariable")) {
							variables.add(expr.getArgBox(0).getValue().toString());
							
						}
						if (expr.getMethodRef().name().equals("getVariable")) {
							String variable = expr.getArgBox(0).getValue().toString();
							if(setVariables.containsValue(variable))
							{
								getVariables.add(variable);
							}
						}
						
						// if
						// (expr.getMethod().toString().equals("setVariable")){System.out.println(expr.getArgBox(0));}
						// System.out.println(expr);
					}
				}
				if (unit instanceof AssignStmt) {

					if (((AssignStmt) unit).getRightOpBox().getValue() instanceof JInterfaceInvokeExpr) {

						JInterfaceInvokeExpr expr = (JInterfaceInvokeExpr) ((AssignStmt) unit).getRightOpBox()
								.getValue();
						if (expr.getMethodRef().name().equals("getVariable")) {
							// System.out.println(expr.getArgBox(0));
//							functions.add("getVariable");
						}

					}

				}

			}
		}
		setVariables.put(block, variables);
		return setVariables;
	}
}
