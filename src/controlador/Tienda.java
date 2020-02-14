package controlador;

import modelo.Articulo;
import modelo.Cliente;
import modelo.Empleado;

public class Tienda {

	// Atributos
	private ArticuloController artControl;
	private ClienteController cliControl;
	private EmpleadoController empControl;
	private String sNombre;
	
	// Constructores
	public Tienda(String sNombre, int numArticulos, int numClientes, int numEmpleados) {
		artControl = new ArticuloController(numArticulos);
		cliControl = new ClienteController(numClientes);
		empControl = new EmpleadoController(numEmpleados);
		setsNombre(sNombre);
	}

	// Metodos getters y setters
	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	// ################## 
	// # SEARCH METHODS # 
	// ##################
	public int searchPositionEmpleado(Empleado oEmp) {
		return empControl.searchPosition(oEmp);
	}

	public Empleado searchEmpleado(Empleado oEmp) {
		return empControl.searchObject(oEmp);
	}

	public int searchPositionCliente(Cliente oCli) {
		return cliControl.searchPosition(oCli);
	}

	public Cliente searchCliente(Cliente oCli) {
		return cliControl.searchObject(oCli);
	}

	public int searchPositionArticulo(Articulo oArt) {
		return artControl.searchPosition(oArt);
	}

	public Articulo searchArticulo(Articulo oArt) {
		return artControl.searchObject(oArt);
	}

	// ################### 
	// # CRUD OPERATIONS # 
	// # Cliente CLASS # 
	// ###################
	// CREATE
	public boolean addCliente(Cliente oCli) {
		return cliControl.add(oCli);
	}

	// READ
	public Cliente readCliente(Cliente oCli) {
		return cliControl.read(oCli);
	}

	// UPDATE
	public boolean updateCliente(Cliente oCli) {
		return cliControl.update(oCli);
	}

	// DELETE
	public boolean deleteCliente(Cliente oCli) {
		return cliControl.delete(oCli);
	}

	// PRINT ALL CLIENTES
	public String printClientes() {
		return cliControl.printAll();
	}

	// ################### 
	// # CRUD OPERATIONS # 
	// # Empleado CLASS #
	// ###################
	// CREATE
	public boolean addEmpleado(Empleado oEmp) {
		return empControl.add(oEmp);
	}

	// READ
	public Empleado readEmpleado(Empleado oEmp) {
		return empControl.read(oEmp);
	}

	// UPDATE
	public boolean updateEmpleado(Empleado oEmp) {
		return empControl.update(oEmp);
	}

	// DELETE
	public boolean deleteEmpleado(Empleado oEmp) {
		return empControl.delete(oEmp);
	}

	// PRINT ALL EMPLEADOS
	public String printEmpleados() {
		return empControl.printAll();
	}

	// ###################
	// # CRUD OPERATIONS #
	// # Articulo CLASS  #
	// ###################
	// CREATE
	public boolean addArticulo(Articulo oArt) {
		return artControl.add(oArt);
	}

	// READ
	public Articulo readArticulo(Articulo oArt) {
		return artControl.read(oArt);
	}

	// UPDATE
	public boolean updateArticulo(Articulo oArt) {
		return artControl.update(oArt);
	}

	// DELETE
	public boolean deleteArticulo(Articulo oArt) {
		return artControl.delete(oArt);
	}

	// PRINT ALL ARTICULOS
	public String printArticulos() {
		return artControl.printAll();
	}
}
