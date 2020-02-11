package vista;
import java.text.ParseException;
import controlador.Tienda;

public class EJEMPLO_ARTICULOS {

	public static final int MAXARTICULOS = 100;
	public static final int MAXCLIENTES = 100;
	public static final int MAXEMPLEADOS = 50;

	public static void main(String[] args) throws ParseException {
				
		ArticuloView vistaArticulo = new ArticuloView();
		ClienteView vistaCliente = new ClienteView();
		EmpleadoView vistaEmpleado = new EmpleadoView();

		byte bOpcionMenuPrincipal;

		Tienda t = new Tienda("Tienda DAM", MAXARTICULOS, MAXCLIENTES, MAXEMPLEADOS);
		System.out.println("Bienvenido al panel de administración");
		System.out.println(t.getsNombre() + " - Tu tienda de confianza");

		do {
			bOpcionMenuPrincipal = menuPrincipal();
			switch (bOpcionMenuPrincipal) {
			case 1: // Gestion de articulos
				vistaArticulo.subMenuArticulos(t);
				break;
			case 2: // Gestion de empleados
				vistaEmpleado.subMenuEmpleados(t);
				break;
			case 3: // Gestion de clientes
				vistaCliente.subMenuClientes(t);
				break;
			case 4:
				System.out.println("Hasta luego, administrador.");
				break;
			default:
				System.out.println("Opción incorrecta.");
			}
		} while (bOpcionMenuPrincipal != 4);
	}

	// ################
	// # MENU METHODS #
	// ################

	// menuPrincipal():
	// Funcion que imprime el menu por pantalla y le da la posibilidad al usuario de
	// introducir una opcion entre 1 y 4.
	// Input: -
	// Output: Opcion entre 1 y 4.
	private static byte menuPrincipal() {
		Byte bOpcion;
		System.out.println("###############################################");
		System.out.println("¿Que deseas gestionar?");
		System.out.println("1. Gestionar articulos.");
		System.out.println("2. Gestionar empleados.");
		System.out.println("3. Gestionar clientes.");
		System.out.println("4. Salir");
		bOpcion = (byte) ValidaLibrary.valida("Introduce una opcion valida: ", 1, 4, 3);
		return bOpcion;
	}

	// subMenu(String):
	// Funcion que imprime el menu por pantalla y le da la posibilidad al usuario de
	// introducir una opcion entre 1 y 6.
	// Input: sClase (String): Nombre de la clase que desea modificar.
	// Output: Opcion entre 1 y 6.
	public static byte subMenu(String sClase) {
		Byte bOpcion;
		System.out.println("¿Que deseas hacer?");
		System.out.println("---------------------------");
		System.out.println("1. Añadir " + sClase);
		System.out.println("2. Modificar " + sClase);
		System.out.println("3. Eliminar " + sClase);
		System.out.println("4. Buscar " + sClase);
		System.out.println("5. Mostrar " + sClase);
		System.out.println("6. Volver a menu principal");
		bOpcion = (byte) ValidaLibrary.valida("Introduce una opcion valida: ", 1, 6, 3);
		return bOpcion;
	}
}
