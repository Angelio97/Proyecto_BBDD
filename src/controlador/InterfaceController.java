package controlador;

public interface InterfaceController<T> {
	
	public int searchPosition(T oObjeto);
	public T searchObject(T oObjeto);
	public boolean add(T oObjeto);
	public T read(T oObjeto);
	public boolean update(T oObjeto);
	public boolean delete(T oObjeto);
	public String printAll();
	

}
