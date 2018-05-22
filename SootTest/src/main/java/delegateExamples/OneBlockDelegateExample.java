package delegateExamples;

import java.util.ArrayList;
import java.util.Collection;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.test.ProcessEngineRule;

import delegateBean.Person;

public class OneBlockDelegateExample implements JavaDelegate {

	public ProcessEngineRule processEngineRule = new ProcessEngineRule();

	@Override
	public void execute(final DelegateExecution execution) {

		int a = 2;
		a = 1;

		Person p = new Person();

		final RuntimeService runtimeService = processEngineRule.getRuntimeService();

		final String vsnr = (String) execution.getVariable("ext_vsnr");

		final String vertrag = "vsnr";

		String example = "Beispiel";

		if (a == 1) {

			execution.setVariable("ext_vertrag", vertrag);

			execution.setVariable("something", example);

			Collection<String> variables = new ArrayList<String>();

			variables.add("soemgo");

			runtimeService.getVariables(execution.getId(), variables);

		}

		Collection<String> variableNames = new ArrayList<String>();
		variableNames.add("something");
		variableNames.add("ext_vertrag");

		execution.removeVariables(variableNames);
	}

}