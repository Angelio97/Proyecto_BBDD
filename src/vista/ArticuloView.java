package vista;

import controlador.Tienda;
import modelo.Articulo;

public class ArticuloView {
	// subMenuArticulos(Tienda):
	// Submenu exclusivo para la gestion de Articulos
	// Input: t (Tienda): Objeto de tipo Tienda.
	// Output: -
	public void subMenuArticulos(Tienda t) {
		byte bOpcionSubMenu;
		boolean bOperacionExito;

		do {
			bOpcionSubMenu = EJEMPLO_ARTICULOS.subMenu(Articulo.class.getSimpleName());
			bOperacionExito = gestionarOpcionArticulo(bOpcionSubMenu, t);

			if (bOpcionSubMenu > 0 && bOpcionSubMenu < 4) {
				if (bOperacionExito) {
					System.out.println("Operacion realizada con exito.");
				} else {
					System.out.println("ERROR: No se ha realizado la operacion.");
				}
			}
		} while (bOpcionSubMenu != 6);
	}

	// gestionarOpcionArticulo(byte, Tienda):
	// Metodo controlador que en funcion de la opcion que haya introducido el
	// usuario en el submenu de articulos realiazara una operacion u otra.
	// Input:
	// - byte bOpcion: Opcion introducida por el usuario en el Submenu.
	// - Tienda t: Objeto tienda de la aplicacion.
	// Output:
	// - boolean bOperacionExito: Booleano que indica si se ha realizado la
	// operacion con exito o no.
	private boolean gestionarOpcionArticulo(byte bOpcion, Tienda t) {
		boolean bOperacionExito = false;
		switch (bOpcion) {
		case 1: // Aniadir articulo
			bOperacionExito = aniadirArticulo(t);
			break;
		case 2: // Modificar articulo
			bOperacionExito = modificarArticulo(t);
			break;
		case 3: // Eliminar articulo
			bOperacionExito = eliminarArticulo(t);
			break;
		case 4: // Buscar articulo
			Articulo oArt = buscarArticulo(t);
			if (oArt != null) {
				System.out.println(oArt);
			} else {
				System.out.println("No se ha encontrado un articulo con la informacion que has introducido.");
			}
			break;
		case 5: // Mostrar articulos
			System.out.println(mostrarArticulos(t));
			break;
		case 6:
			System.out.println("Volviendo al menu principal...");
			break;
		default:
			System.out.println("Opcion incorrecta.");
		}
		return bOperacionExito;
	}

	// ###############################
	// # CONTROLLER FRONTEND-BACKEND #
	// # Articulo METHODS #
	// ###############################

	// aniadirArticulo(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND. Pide al usuario que introduzca
	// los datos de un articulo y llama al BACKEND para poder Aniadir el articulo al
	// objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicacion.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha aniadir el articulo o no.
	private boolean aniadirArticulo(Tienda t) {
		short shIdentificador;
		String sNombre;
		float fPrecio;

		shIdentificador = (short) ValidaLibrary.valida("Introduce un identificador para el articulo: ", 0, 100, 4);
		sNombre = ValidaLibrary.leer("Introduce un nombre para el articulo: ");
		fPrecio = (float) ValidaLibrary.valida("Introduce el precio para el articulo: ", 0, 1000, 2);
		return t.addArticulo(new Articulo(shIdentificador, sNombre, fPrecio));
	}

	// modificarArticulo(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// 1) Pide al usuario que introduzca el nombre de un articulo que desee
	// modificar.
	// 2) Busca en el BACKEND si el articulo esta registrado en la tienda.
	// 3) Si esta registrado en la tienda, el FRONTEND pide al usuario que cambie el
	// precio del articulo.
	// 4) Modificamos el precio del articulo en el objeto.
	// 5) Le pasamos el objeto articulo modificado al BACKEND.
	// Input:
	// - Tienda t: Objeto tienda de la aplicacion.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha modificado el articulo o no.
	private boolean modificarArticulo(Tienda t) {
		String sNombre;
		float fPrecio;
		boolean bExito = false;

		sNombre = ValidaLibrary.leer("Introduce un nombre de un articulo que desees modificar: ");
		Articulo oArt = t.searchArticulo(new Articulo(sNombre));
		if (oArt != null) {
			fPrecio = (float) ValidaLibrary.valida("Introduce un nuevo precio para el articulo", 0.1, 1000, 2);
			oArt.setfPrecio(fPrecio);
			bExito = t.updateArticulo(oArt);
		}
		return bExito;
	}

	// eliminarArticulo(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Pide al usuario que introduzca el nombre del articulo que desea eliminar y
	// llama al BACKEND para poder eliminar el articulo al objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicacion.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha eliminado el articulo o no.
	private boolean eliminarArticulo(Tienda t) {
		String sNombre;
		sNombre = ValidaLibrary.leer("Introduce un nombre de un articulo que desees eliminar: ");
		return t.deleteArticulo(new Articulo(sNombre));
	}

	// buscarArticulo(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Pide al usuario que introduzca el nombre del articulo que queremos buscar y
	// llama al BACKEND para poder buscar el articulo al objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicacion.
	// Output:
	// - boolean: Devuelve el objeto Articulo que ha sido buscado por el BACKEND.
	private Articulo buscarArticulo(Tienda t) {
		String sNombre;
		sNombre = ValidaLibrary.leer("Introduce un nombre de un articulo que desees buscar: ");
		return t.searchArticulo(new Articulo(sNombre));
	}

	// MostrarArticulos(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Mostrar todos los articulos registrados en la tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicacion.
	// Output:
	// - boolean: String con todos los articulos.
	private String mostrarArticulos(Tienda t) {
		return t.printArticulos();
	}
}
