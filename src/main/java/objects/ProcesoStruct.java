package objects;

import com.gpo7.proceso.entity.Proceso;

public class ProcesoStruct {

	Proceso proceso;
	boolean eliminar;
	public Proceso getProceso() {
		return proceso;
	}
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	public boolean isEliminar() {
		return eliminar;
	}
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	public ProcesoStruct(Proceso proceso, boolean eliminar) {
		super();
		this.proceso = proceso;
		this.eliminar = eliminar;
	}
	
}
