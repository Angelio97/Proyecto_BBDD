package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controlador.Tienda;
import modelo.Cliente;

public class ClienteView {
	// subMenuClientes(Tienda):
	// Submenú exclusivo para la gestión de Clientes
	// Input: t (Tienda): Objeto de tipo Tienda.
	// Output: -
	public void subMenuClientes(Tienda t) throws ParseException {
		byte bOpcionSubMenu;
		boolean bOperacionExito;

		do {
			bOpcionSubMenu = EJEMPLO_ARTICULOS.subMenu(Cliente.class.getSimpleName());
			bOperacionExito = gestionarOpcionCliente(bOpcionSubMenu, t);

			if (bOpcionSubMenu > 0 && bOpcionSubMenu < 4) {
				if (bOperacionExito) {
					System.out.println("Operacion realizada con exito.");
				} else {
					System.out.println("ERROR: No se ha realizado la operacion.");
				}
			}

		} while (bOpcionSubMenu != 6);
	}

	// gestionarOpcionCliente(byte, Tienda):
	// Método controlador que en función de la opción que haya introducido el
	// usuario en el submenu de cliente realizará una operación u otra.
	// Input:
	// - byte bOpcion: Opción introducida por el usuario en el submenú.
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean bOperacionExito: Booleano que indica si se ha realizado la
	// operacion con exito o no.
	private boolean gestionarOpcionCliente(byte bOpcion, Tienda t) throws ParseException {
		boolean bOperacionExito = false;
		switch (bOpcion) {
		case 1: // Añadir cliente
			bOperacionExito = aniadirCliente(t);
			break;
		case 2: // Modificar cliente
			bOperacionExito = modificarCliente(t);
			break;
		case 3: // Eliminar cliente
			bOperacionExito = eliminarCliente(t);
			break;
		case 4: // Buscar cliente
			Cliente oCli = buscarCliente(t);
			if (oCli != null) {
				System.out.println(oCli);
			} else {
				System.out.println("No se ha encontrado un articulo con la informacion que has introducido.");
			}
			break;
		case 5: // Mostrar clientes
			System.out.println(mostrarClientes(t));
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
	// # Cliente METHODS #
	// ###############################

	// aniadirCliente(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND. Pide al usuario que introduzca
	// los datos de un cliente y llama al BACKEND para poder añadir el cliente al
	// objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha añadido el cliente o no.
	private boolean aniadirCliente(Tienda t) throws ParseException {
		String sDni, sNombre, sApellidos, sDomicilio, sEmail, sTarjetaCredito, sDateString;
		Date dFechaNacimiento;

		sDni = ValidaLibrary.leer("Introduce un DNI: ");
		sNombre = ValidaLibrary.leer("Introduce un nombre: ");
		sApellidos = ValidaLibrary.leer("Introduce los apellidos: ");
		sDomicilio = ValidaLibrary.leer("Introduce el domicilio: ");
		sEmail = ValidaLibrary.leer("Introduce un email: ");
		sTarjetaCredito = ValidaLibrary.leer("Introduce una tarjeta de credito: ");
		sDateString = ValidaLibrary.leer("Introduce una fecha de nacimiento (31/12/1998): ");
		dFechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(sDateString);

		Cliente oCli = new Cliente(sDni, sNombre, sApellidos, sTarjetaCredito);
		oCli.setsDomicilio(sDomicilio);
		oCli.setsEmail(sEmail);
		oCli.setdFechaNacimiento(dFechaNacimiento);
		return t.addCliente(oCli);
	}

	// modificarCliente(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// 1) Pide al usuario que introduzca el DNI de un cliente que desee
	// modificar.
	// 2) Busca en el BACKEND si el cliente está registrado en la tienda.
	// 3) Si está registrado en la tienda, el FRONTEND pide al usuario que cambie
	// los datos del cliente.
	// 4) Modificamos los datos del objeto.
	// 5) Le pasamos el objeto cliente modificado al BACKEND.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha modificado el artículo o no.
	private boolean modificarCliente(Tienda t) throws ParseException {
		String sDni;	
		boolean bExito = false;
		sDni = ValidaLibrary.leer("Introduce un DNI de un cliente que desees modificar: ");
		Cliente oCli = t.searchCliente(new Cliente(sDni));
		if (oCli != null) {
			oCli.setsNombre(ValidaLibrary.leer("Introduce un nombre: "));
			oCli.setsApellidos(ValidaLibrary.leer("Introduce los apellidos: "));
			oCli.setsDomicilio(ValidaLibrary.leer("Introduce el domicilio: "));
			oCli.setsEmail(ValidaLibrary.leer("Introduce un email: "));
			oCli.setsTarjetaCredito(ValidaLibrary.leer("Introduce una tarjeta de credito: "));
			String sDateString = ValidaLibrary.leer("Introduce una fecha de nacimiento (31/12/1998): ");
			oCli.setdFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(sDateString));
			bExito = t.updateCliente(oCli);
		}
		return bExito;
	}

	// eliminarCliente(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Pide al usuario que introduzca el DNI del cliente que desea eliminar y
	// llama al BACKEND para poder eliminar el cliente del objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha eliminado el cliente o no.
	private boolean eliminarCliente(Tienda t) {
		String sDni;
		sDni = ValidaLibrary.leer("Introduce un DNI de un cliente que desees eliminar: ");
		return t.deleteCliente(new Cliente(sDni));
	}

	// buscarCliente(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Pide al usuario que introduzca el DNI de un cliente que queremos buscar y
	// llama al BACKEND para poder buscar el cliente al objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve el objeto CLiente que ha sido buscado por el BACKEND.
	private Cliente buscarCliente(Tienda t) {
		String sDni;
		sDni = ValidaLibrary.leer("Introduce un DNI de un cliente que desees buscar: ");
		return t.searchCliente(new Cliente(sDni));
	}

	// mostrarClientes(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Mostrar todos los clientes registrados en la tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: String con todos los clientes.
	private String mostrarClientes(Tienda t) {
		return t.printClientes();
	}
}
