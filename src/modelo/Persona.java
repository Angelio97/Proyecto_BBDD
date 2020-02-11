package modelo;

import java.util.Date;

public abstract class Persona {
	protected String sDni, sNombre, sApellidos, sDomicilio, sEmail;
	protected Date dFechaNacimiento;
	
	public Persona(String sDni, String sNombre, String sApellidos) {
		this.setsDni(sDni);
		this.setsNombre(sNombre);
		this.setsApellidos(sApellidos);
	}
	
	public Persona(String sDni) {
		this.setsDni(sDni);
	}

	public String getsDni() {
		return sDni;
	}

	public void setsDni(String sDni) {
		this.sDni = sDni;
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public String getsApellidos() {
		return sApellidos;
	}

	public void setsApellidos(String sApellidos) {
		this.sApellidos = sApellidos;
	}

	public String getsDomicilio() {
		return sDomicilio;
	}

	public void setsDomicilio(String sDomicilio) {
		this.sDomicilio = sDomicilio;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public Date getdFechaNacimiento() {
		return dFechaNacimiento;
	}

	public void setdFechaNacimiento(Date dFechaNacimiento) {
		this.dFechaNacimiento = dFechaNacimiento;
	}
	
	public String toString() {
		String sResultado = "";
		sResultado += "DNI: " + this.sDni + "\n";
		sResultado += "Nombre: " + this.sNombre + "\n";
		sResultado += "Apellidos: " + this.sApellidos + "\n";
		sResultado += "Domicilio: " + this.sDomicilio + "\n";
		sResultado += "Email: " + this.sEmail + "\n";
		sResultado += "Fecha de nacimiento: " + this.dFechaNacimiento + "\n";
		return sResultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sDni == null) ? 0 : sDni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Persona otro = (Persona) obj;
		if(this != null && otro != null && this.getsDni().equals(otro.getsDni())) {
			bIgual = true;
		}
		return bIgual;
	}
	
	
	
}
