package delegateExamples;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import delegateBean.beans.ProcessContext;

public class CreateCollectionDelegate implements JavaDelegate {

   
    public void execute(final DelegateExecution delegateExecution) throws Exception {
    	
        List<Integer> personIdList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            personIdList.add(i);
        }
        
        delegateExecution.setVariable("example", "Beispiel");
        
        ProcessContext processContext = new ProcessContext(delegateExecution);
        processContext.getInternal().setPersonIdList(personIdList);
    }

}
