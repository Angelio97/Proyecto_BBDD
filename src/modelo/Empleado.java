package modelo;

public class Empleado extends Persona {
	
	private String sNumSeguridadSocial;
	
	public Empleado(String sDni, String sNombre, String sApellidos, String sNumSeguridadSocial) {
		super(sDni,sNombre,sApellidos);
		setsNumSeguridadSocial(sNumSeguridadSocial);
	}
	
	public Empleado(String sDni) {
		super(sDni);
	}

	public String getsNumSeguridadSocial() {
		return sNumSeguridadSocial;
	}

	public void setsNumSeguridadSocial(String sNumSeguridadSocial) {
		this.sNumSeguridadSocial = sNumSeguridadSocial;
	}
	
	public String toString() {
		String sResultado = "";
		sResultado += super.toString() + "\n";
		sResultado += "Seguridad social: " + this.sNumSeguridadSocial + "\n";
		return sResultado;
	}

	
	
	
	
}
