package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controlador.Tienda;
import modelo.Socio;

public class EmpleadoView {
	// subMenuEmpleados(Tienda):
	// Submenú exclusivo para la gestión de Empleados
	// Input: t (Tienda): Objeto de tipo Tienda.
	// Output: -
	public void subMenuEmpleados(Tienda t) throws ParseException {
		byte bOpcionSubMenu;
		boolean bOperacionExito;

		do {
			bOpcionSubMenu = EJEMPLO_ARTICULOS.subMenu(Socio.class.getSimpleName());
			bOperacionExito = gestionarOpcionEmpleado(bOpcionSubMenu, t);

			if (bOpcionSubMenu > 0 && bOpcionSubMenu < 4) {
				if (bOperacionExito) {
					System.out.println("Operacion realizada con exito.");
				} else {
					System.out.println("ERROR: No se ha realizado la operacion.");
				}
			}

		} while (bOpcionSubMenu != 6);
	}
	// gestionarOpcionEmpleado(byte, Tienda):
	// Método controlador que en función de la opción que haya introducido el
	// usuario en el submenu de empleados realizará una operación u otra.
	// Input:
	// - byte bOpcion: Opción introducida por el usuario en el submenú.
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean bOperacionExito: Booleano que indica si se ha realizado la
	// operacion con exito o no.
	public boolean gestionarOpcionEmpleado(byte bOpcion, Tienda t) throws ParseException {
		boolean bOperacionExito = false;
		switch (bOpcion) {
		case 1: // Añadir empleado
			bOperacionExito = añadirEmpleado(t);
			break;
		case 2: // Modificar empleado
			bOperacionExito = modificarEmpleado(t);
			break;
		case 3: // Eliminar empleado
			bOperacionExito = eliminarEmpleado(t);
			break;
		case 4: // Buscar empleado
			Socio oEmp = buscarEmpleado(t);
			if (oEmp != null) {
				System.out.println(oEmp);
			} else {
				System.out.println("No se ha encontrado un articulo con la informacion que has introducido.");
			}
			break;
		case 5: // Mostrar empleado
			System.out.println(mostrarEmpleados(t));
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
	// # Empleado METHODS #
	// ###############################

	// añadirEmpleado(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND. Pide al usuario que introduzca
	// los datos de un Empleado y llama al BACKEND para poder añadir el Empleado al
	// objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha añadido el Empleado o no.
	private boolean añadirEmpleado(Tienda t) throws ParseException {
		String sDni, sNombre, sApellidos, sDomicilio, sEmail, sSeguridadSocial, sDateString;
		Date dFechaNacimiento;

		sDni = ValidaLibrary.leer("Introduce un DNI: ");
		sNombre = ValidaLibrary.leer("Introduce un nombre: ");
		sApellidos = ValidaLibrary.leer("Introduce los apellidos: ");
		sDomicilio = ValidaLibrary.leer("Introduce el domicilio: ");
		sEmail = ValidaLibrary.leer("Introduce un email: ");
		sSeguridadSocial = ValidaLibrary.leer("Introduce un numero de seguridad social: ");
		sDateString = ValidaLibrary.leer("Introduce una fecha de nacimiento (31/12/1998): ");
		dFechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(sDateString);

		Socio oEmp = new Socio(sDni, sNombre, sApellidos, sSeguridadSocial);
		oEmp.setsDomicilio(sDomicilio);
		oEmp.setsEmail(sEmail);
		oEmp.setdFechaNacimiento(dFechaNacimiento);
		return t.addEmpleado(oEmp);
	}

	// modificarEmpleado(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// 1) Pide al usuario que introduzca el DNI de un Empleado que desee
	// modificar.
	// 2) Busca en el BACKEND si el Empleado está registrado en la tienda.
	// 3) Si está registrado en la tienda, el FRONTEND pide al usuario que cambie
	// los datos del Empleado.
	// 4) Modificamos los datos del objeto.
	// 5) Le pasamos el objeto Empleado modificado al BACKEND.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha modificado el artículo o no.
	private boolean modificarEmpleado(Tienda t) throws ParseException {
		String sDni;
		boolean bExito = false;
		sDni = ValidaLibrary.leer("Introduce un DNI de un Empleado que desees modificar: ");
		Socio oEmp = t.searchEmpleado(new Socio(sDni));
		if (oEmp != null) {
			oEmp.setsNombre(ValidaLibrary.leer("Introduce un nombre: "));
			oEmp.setsApellidos(ValidaLibrary.leer("Introduce los apellidos: "));
			oEmp.setsDomicilio(ValidaLibrary.leer("Introduce el domicilio: "));
			oEmp.setsEmail(ValidaLibrary.leer("Introduce un email: "));
			oEmp.setsNumSeguridadSocial(ValidaLibrary.leer("Introduce un numero de seguridad social: "));
			String sDateString = ValidaLibrary.leer("Introduce una fecha de nacimiento (31/12/1998): ");
			oEmp.setdFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(sDateString));
			bExito = t.updateEmpleado(oEmp);
		}
		return bExito;
	}

	// eliminarEmpleado(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Pide al usuario que introduzca el DNI del Empleado que desea eliminar y
	// llama al BACKEND para poder eliminar el Empleado del objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve un booleano si el BACKEND ha eliminado el Empleado o no.
	private boolean eliminarEmpleado(Tienda t) {
		String sDni;
		sDni = ValidaLibrary.leer("Introduce un DNI de un Empleado que desees eliminar: ");
		return t.deleteEmpleado(new Socio(sDni));
	}

	// buscarEmpleado(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Pide al usuario que introduzca el DNI de un Empleado que queremos buscar y
	// llama al BACKEND para poder buscar el Empleado al objeto Tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: Devuelve el objeto Empleado que ha sido buscado por el BACKEND.
	private Socio buscarEmpleado(Tienda t) {
		String sDni;
		sDni = ValidaLibrary.leer("Introduce un DNI de un Empleado que desees buscar: ");
		return t.searchEmpleado(new Socio(sDni));
	}

	// mostrarEmpleados(Tienda t):
	// Funcion CONTROLADORA ENTRE FRONTEND-BACKEND.
	// Mostrar todos los Empleados registrados en la tienda.
	// Input:
	// - Tienda t: Objeto tienda de la aplicación.
	// Output:
	// - boolean: String con todos los Empleados.
	private String mostrarEmpleados(Tienda t) {
		return t.printEmpleados();
	}
}
