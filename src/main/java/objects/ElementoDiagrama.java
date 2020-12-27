package objects;

public class ElementoDiagrama {
	int idElementoDiagramaBpmn;

	/**
	 * @param idElementoDiagramaBpmn
	 */
	public ElementoDiagrama(int idElementoDiagramaBpmn) {
		super();
		this.idElementoDiagramaBpmn = idElementoDiagramaBpmn;
	}

	/**
	 * 
	 */
	public ElementoDiagrama() {
		super();
	}

	public int getIdElementoDiagramaBpmn() {
		return idElementoDiagramaBpmn;
	}

	public void setIdElementoDiagramaBpmn(int idElementoDiagramaBpmn) {
		this.idElementoDiagramaBpmn = idElementoDiagramaBpmn;
	}

	@Override
	public String toString() {
		return "ElementoDiagrama [idElementoDiagramaBpmn=" + idElementoDiagramaBpmn + "]";
	}
	
	
}
