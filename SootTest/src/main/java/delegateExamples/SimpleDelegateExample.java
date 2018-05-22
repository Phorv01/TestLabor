package delegateExamples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.test.ProcessEngineRule;

import delegateBean.Person;
import delegateBean.ProcessConstants;

public class SimpleDelegateExample implements JavaDelegate {

	public ProcessEngineRule processEngineRule = new ProcessEngineRule();

	@Override
	public void execute(final DelegateExecution execution) {

		int a = 2;
		a = 1;
//		try {
			Person p = new Person();

			final RuntimeService runtimeService = processEngineRule.getRuntimeService();

			final String vsnr = (String) execution.getVariable("ext_vsnr");

			final String vertrag = "vsnr";

			String example = "Beispiel";
			example = example + "2";
			
			execution.setVariable(vertrag, "smt");

			if (a == 1) {

				execution.setVariable(vertrag, "smt");

				execution.setVariable(ProcessConstants.CAMUNDA_LOOP_COUNTER, "something");
				execution.setVariable(example, "something");

				Collection<String> variables = new ArrayList<String>();

				variables.add("varName1");
				variables.add("varName2");
				
				Map<String, Object> allVars = runtimeService.getVariables(execution.getId(), variables);
				
				Object valueVarName1 = allVars.get("varName1");
				
				if((Integer)execution.getVariable("age") == 1) {
					
				}
				
				Object result = runtimeService.getVariable(execution.getId(), "varName1");
				runtimeService.getVariable(execution.getId(), "varName2");
				
				System.out.println(result);
			
			}
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}

		ArrayList<String> variableNames = new ArrayList<>();
		variableNames.add("something");
		variableNames.add("ext_vertrag");

		execution.removeVariables(variableNames);
	}

}
