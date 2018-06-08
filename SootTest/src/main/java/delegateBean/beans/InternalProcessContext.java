package delegateBean.beans;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.VariableScope;

public class InternalProcessContext {

	 private VariableScope execution;

	    public InternalProcessContext(final VariableScope execution) {
	        this.execution = execution;
	    }

	    public Person getPerson() {
	        return (Person) execution.getVariable(ProcessConstants.INT_PERSON);
	    }

	    public void setPerson(Person p) {
	        execution.setVariable(ProcessConstants.INT_PERSON, p);
	    }

	    public void setPersonList(List<Person> personList) {
	        execution.setVariable(ProcessConstants.INT_PERSON_LIST, personList);
	    }

	    public List<Person> getPersonList() {
	        List<Person> variable = (List<Person>) execution.getVariable(ProcessConstants.INT_PERSON_LIST);
	        if (variable != null)
	            return variable;
	        return new ArrayList<>();
	    }

	    public void setPersonIdList(List<Integer> idList) {
	        execution.setVariable(ProcessConstants.INT_PERSON_ID_LIST, idList);
	    }

	    public List<Integer> getPersonIdList() {
	        return (List<Integer>) execution.getVariable(ProcessConstants.INT_PERSON_ID_LIST);
	    }
}
