package controlador;
import modelo.Cliente;

public class ClienteController implements InterfaceController<Cliente> {
	
	private Cliente aClientes[];
	private int iContClientes;
	
	public ClienteController(int numClientes) {
		aClientes = new Cliente[numClientes];
		setiContClientes(0);
	}

	// Metodos getters y setters
	public Cliente[] getaClientes() {
		return aClientes;
	}

	public void setaClientes(Cliente[] aClientes) {
		this.aClientes = aClientes;
	}

	public int getiContClientes() {
		return iContClientes;
	}

	public void setiContClientes(int iContClientes) {
		this.iContClientes = iContClientes;
	}

	// #####################
	// # INTERFACE METHODS #
	// #####################
	@Override
	public int searchPosition(Cliente oObjeto) {
		int iPosicion = -1;
		int iContador = 0;
		while (iPosicion == -1 && iContador < aClientes.length) {
			if (oObjeto.equals(aClientes[iContador])) {
				iPosicion = iContador;
			}
			iContador++;
		}
		return iPosicion;
	}

	@Override
	public Cliente searchObject(Cliente oObjeto) {
		Cliente oDevolver = null;
		int iContador = 0;
		while (oDevolver == null && iContador < aClientes.length) {
			if (oObjeto.equals(aClientes[iContador])) {
				oDevolver = aClientes[iContador];
			}
			iContador++;
		}
		return oDevolver;
	}

	@Override
	public boolean add(Cliente oObjeto) {
		boolean bExito = false;
		if (iContClientes < aClientes.length && searchPosition(oObjeto) == -1) {
			aClientes[iContClientes] = oObjeto;
			setiContClientes(iContClientes + 1);
			bExito = true;
		}
		return bExito;
	}

	@Override
	public Cliente read(Cliente oObjeto) {
		return searchObject(oObjeto);
	}

	@Override
	public boolean update(Cliente oObjeto) {
		boolean bExito = false;
		int iPosicion = searchPosition(oObjeto);
		if (iPosicion != -1) {
			aClientes[iPosicion] = oObjeto;
			bExito = true;
		}
		return bExito;
	}
	
	@Override
	public boolean delete(Cliente oObjeto) {
		boolean bExito = false;
		int iPosicion = searchPosition(oObjeto);
		if (iPosicion != -1) {
			for (int iCont = iPosicion + 1; iCont < aClientes.length; iCont++) {
				aClientes[iCont - 1] = aClientes[iCont];
			}
			aClientes[aClientes.length - 1] = null;
			setiContClientes(iContClientes - 1);
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String printAll() {
		String sResultado = "";

		if (iContClientes == 0) {
			sResultado = "No hay clientes registrados en la aplicacion.";
		} else {
			for (int iCont = 0; iCont < iContClientes; iCont++) {
				sResultado += aClientes[iCont] + "\n";
			}
		}
		return sResultado;
	}

	
}
