package helperClasses;

public class ProcessVariable {

		  private String name;

		  private String scopeId;
		  
		  private String operation;


		  public ProcessVariable(final String name, final String scopeId, final String operation) {
		    super();
		    this.name = name;
		    this.scopeId = scopeId;
		    this.operation = operation;
		    
		  }

		  public String getName() {
		    return name;
		  }

		  public String getScopeId() {
		    return scopeId;
		  }

		  public String toString() {
		    return name + "Scope: " + scopeId;
		  }

		  @Override
		  public int hashCode() {
		    return name.hashCode();
		  }

		  @Override
		  public boolean equals(final Object o) {
		    if (o instanceof ProcessVariable) {
		      final ProcessVariable p = (ProcessVariable) o;
		      if (name.equals(p.getName())) {
		        return true;
		      }
		    }
		    return false;
		  }
		}

