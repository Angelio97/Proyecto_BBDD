package modelo;
public class Articulo {
	
	// 1) Declarar atributos (precio, nombre, identificador)
	private String sNombre;
	private float fPrecio;
	private short shIdentificador;
	private final float IVA = (float)1.21;
	
	// 2) Constructores
	public Articulo(short shIdentificador, String sNombre, float fPrecio) {
		setShIdentificador(shIdentificador);
		setsNombre(sNombre);
		setfPrecio(fPrecio);
	}
	
	public Articulo(String sNombre) {
		setsNombre(sNombre);
	}
	
	// 3) Métodos getters y setters
	public String getsNombre() {
		return sNombre;
	}

	public boolean setsNombre(String sNombre) {
		boolean bExito = false;
		if(sNombre.length() < 10) {
			this.sNombre = sNombre;
			bExito = true;
		}
		return bExito;
	}

	public float getfPrecio() {
		return fPrecio;
	}

	public boolean setfPrecio(float fPrecio) {
		boolean bExito = false;
		if(fPrecio > 0 && fPrecio < 1000) {
			this.fPrecio = fPrecio;
			bExito = true;
		}
		return bExito;
	}

	public short getShIdentificador() {
		return shIdentificador;
	}

	public void setShIdentificador(short shIdentificador) {
		this.shIdentificador = shIdentificador;
	}
	
	// 4) toString
	public String toString() {
		String sResultado = "";
		sResultado += "********************************************************\n";
		sResultado += "ID del Articulo: " + this.shIdentificador + " - ";
		sResultado += "Nombre: " + this.sNombre + " - ";
		sResultado += "Precio(Sin IVA): " + this.fPrecio + " euros. - ";
		sResultado += "Precio(Con IVA): " + this.precioConIva() + " euros.\n";
		sResultado += "********************************************************\n";
		return sResultado;
	}
	
	// 5) Funciones / metodos especificos

	/**
	 * Este metodo devuelve el precio del articulo con el IVA
	 * @return Precio con IVA
	 */
	public float precioConIva() {
		return this.fPrecio * IVA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombre == null) ? 0 : sNombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Articulo otro = (Articulo) obj;
		if(this != null && otro != null && this.getsNombre().equals(otro.getsNombre())) {
			bIgual = true;
		}
		return bIgual;
	}
	
	
}
