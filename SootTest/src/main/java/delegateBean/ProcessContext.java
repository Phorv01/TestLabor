package delegateBean;

import org.camunda.bpm.engine.delegate.VariableScope;

public class ProcessContext {

	private InternalProcessContext internalProcessContext;

    public ProcessContext(VariableScope execution) {
        this.internalProcessContext = new InternalProcessContext(execution);
    }

    public InternalProcessContext getInternal() {
        return internalProcessContext;
    }
	
}
