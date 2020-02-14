package controlador;
import modelo.Articulo;

public class ArticuloController implements InterfaceController<Articulo> {

	private Articulo aArticulos[];
	private int iContArticulos;
	
	public ArticuloController(int numArticulos) {
		aArticulos = new Articulo[numArticulos];
		setiContArticulos(0);
	}
	
	// Metodos getters y setters
	public Articulo[] getaArticulos() {
		return aArticulos;
	}


	public void setaArticulos(Articulo[] aArticulos) {
		this.aArticulos = aArticulos;
	}


	public int getiContArticulos() {
		return iContArticulos;
	}


	public void setiContArticulos(int iContArticulos) {
		this.iContArticulos = iContArticulos;
	}

	// #####################
	// # INTERFACE METHODS  #
	// #####################
	@Override
	public int searchPosition(Articulo oObjeto) {
		int iPosicion = -1;
		int iContador = 0;
		while (iPosicion == -1 && iContador < aArticulos.length) {
			if (oObjeto.equals(aArticulos[iContador])) {
				iPosicion = iContador;
			}
			iContador++;
		}
		return iPosicion;
	}

	@Override
	public Articulo searchObject(Articulo oObjeto) {
		Articulo oDevolver = null;
		int iContador = 0;
		while (oDevolver == null && iContador < aArticulos.length) {
			if (oObjeto.equals(aArticulos[iContador])) {
				oDevolver = aArticulos[iContador];
			}
			iContador++;
		}
		return oDevolver;
	}

	@Override
	public boolean add(Articulo oObjeto) {
		boolean bExito = false;
		if (iContArticulos < aArticulos.length && searchPosition(oObjeto) == -1) {
			aArticulos[iContArticulos] = oObjeto;
			setiContArticulos(iContArticulos + 1);
			bExito = true;
		}
		return bExito;
	}

	@Override
	public Articulo read(Articulo oObjeto) {
		return searchObject(oObjeto);
	}

	@Override
	public boolean update(Articulo oObjeto) {
		boolean bExito = false;
		int iPosicion = searchPosition(oObjeto);
		if (iPosicion != -1) {
			aArticulos[iPosicion] = oObjeto;
			bExito = true;
		}
		return bExito;
	}
	
	@Override
	public boolean delete(Articulo oObjeto) {
		boolean bExito = false;
		int iPosicion = searchPosition(oObjeto);
		if (iPosicion != -1) {
			for (int iCont = iPosicion + 1; iCont < aArticulos.length; iCont++) {
				aArticulos[iCont - 1] = aArticulos[iCont];
			}
			aArticulos[aArticulos.length - 1] = null;
			setiContArticulos(iContArticulos - 1);
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String printAll() {
		String sResultado = "";

		if (iContArticulos == 0) {
			sResultado = "No hay articulos registrados en la aplicacion.";
		} else {
			for (int iCont = 0; iCont < iContArticulos; iCont++) {
				sResultado += aArticulos[iCont] + "\n";
			}
		}
		return sResultado;
	}

	

}
