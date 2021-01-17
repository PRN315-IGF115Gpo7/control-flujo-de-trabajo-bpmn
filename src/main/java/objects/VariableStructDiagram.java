package objects;

import com.gpo7.proceso.entity.Variable;

public class VariableStructDiagram {

	private Variable variable;
	private boolean esEscritura;
	private boolean mostrar;
	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	public boolean isEsEscritura() {
		return esEscritura;
	}
	public void setEsEscritura(boolean esEscritura) {
		this.esEscritura = esEscritura;
	}
	public boolean isMostrar() {
		return mostrar;
	}
	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	public VariableStructDiagram(Variable variable, boolean esEscritura, boolean mostrar) {
		super();
		this.variable = variable;
		this.esEscritura = esEscritura;
		this.mostrar = mostrar;
	}
	
}
