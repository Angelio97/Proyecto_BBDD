package vista;

import controlador.Tienda;
import modelo.Articulo;

public class ArticuloView {
	// subMenuArticulos(Tienda):
	// Submenú exclusivo para la gestión de Artículos
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
	// Método controlador que en función de la opción que haya introducido el
	// usuario en el submenu de articulos realizará una operación u otra.
	// Input:
	// - byte bOpcion: Opción introducida por el usuario en el submenú.
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean bOperacionExito: Booleano que indica si se ha realizado la
	// operacion con exito o no.
	private boolean gestionarOpcionArticulo(byte bOpcion, Tienda t) {
		boolean bOperacionExito = false;
		switch (bOpcion) {
		case 1: // Añadir articulo
			bOperacionExito = añadirArticulo(t);
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

	// añadirArticulo(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND. Pide al usuario que introduzca
	// los datos de un articulo y llama al BACKEND para poder añadir el articulo al
	// objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha añadido el artículo o no.
	private boolean añadirArticulo(Tienda t) {
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
	// 2) Busca en el BACKEND si el articulo está registrado en la tienda.
	// 3) Si está registrado en la tienda, el FRONTEND pide al usuario que cambie el
	// precio del articulo.
	// 4) Modificamos el precio del articulo en el objeto.
	// 5) Le pasamos el objeto articulo modificado al BACKEND.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha modificado el artículo o no.
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
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha eliminado el artículo o no.
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
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve el objeto Articulo que ha sido buscado por el BACKEND.
	private Articulo buscarArticulo(Tienda t) {
		String sNombre;
		sNombre = ValidaLibrary.leer("Introduce un nombre de un articulo que desees buscar: ");
		return t.searchArticulo(new Articulo(sNombre));
	}

	// mostrarArticulos(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Mostrar todos los articulos registrados en la tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: String con todos los articulos.
	private String mostrarArticulos(Tienda t) {
		return t.printArticulos();
	}
}
