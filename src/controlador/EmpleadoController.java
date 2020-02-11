package controlador;
import modelo.Empleado;

public class EmpleadoController implements InterfaceController<Empleado> {
	
	private Empleado aEmpleados[];
	private int iContEmpleados;
	
	public EmpleadoController(int numEmpleados) {
		aEmpleados = new Empleado[numEmpleados];
		setiContEmpleados(0);
	}
	
	// Metodos getters y setters
	public Empleado[] getaEmpleados() {
		return aEmpleados;
	}

	public void setaEmpleados(Empleado[] aEmpleados) {
		this.aEmpleados = aEmpleados;
	}

	public int getiContEmpleados() {
		return iContEmpleados;
	}

	public void setiContEmpleados(int iContEmpleados) {
		this.iContEmpleados = iContEmpleados;
	}

	// #####################
	// # INTERFACE METHODS #
	// #####################
	@Override
	public int searchPosition(Empleado oObjeto) {
		int iPosicion = -1;
		int iContador = 0;
		while (iPosicion == -1 && iContador < aEmpleados.length) {
			if (oObjeto.equals(aEmpleados[iContador])) {
				iPosicion = iContador;
			}
			iContador++;
		}
		return iPosicion;
	}

	@Override
	public Empleado searchObject(Empleado oObjeto) {
		Empleado oDevolver = null;
		int iContador = 0;
		while (oDevolver == null && iContador < aEmpleados.length) {
			if (oObjeto.equals(aEmpleados[iContador])) {
				oDevolver = aEmpleados[iContador];
			}
			iContador++;
		}
		return oDevolver;
	}

	@Override
	public boolean add(Empleado oObjeto) {
		boolean bExito = false;
		if (iContEmpleados < aEmpleados.length && searchPosition(oObjeto) == -1) {
			aEmpleados[iContEmpleados] = oObjeto;
			setiContEmpleados(iContEmpleados + 1);
			bExito = true;
		}
		return bExito;
	}

	@Override
	public Empleado read(Empleado oObjeto) {
		return searchObject(oObjeto);
	}

	@Override
	public boolean update(Empleado oObjeto) {
		boolean bExito = false;
		int iPosicion = searchPosition(oObjeto);
		if (iPosicion != -1) {
			aEmpleados[iPosicion] = oObjeto;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean delete(Empleado oObjeto) {
		boolean bExito = false;
		int iPosicion = searchPosition(oObjeto);
		if (iPosicion != -1) {
			for (int iCont = iPosicion + 1; iCont < aEmpleados.length; iCont++) {
				aEmpleados[iCont - 1] = aEmpleados[iCont];
			}
			aEmpleados[aEmpleados.length - 1] = null;
			setiContEmpleados(iContEmpleados - 1);
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String printAll() {
		String sResultado = "";

		if (iContEmpleados == 0) {
			sResultado = "No hay empleados registrados en la aplicacion.";
		} else {
			for (int iCont = 0; iCont < iContEmpleados; iCont++) {
				sResultado += aEmpleados[iCont] + "\n";
			}
		}
		return sResultado;
	}
}
