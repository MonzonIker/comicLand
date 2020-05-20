package modelo.bean;

public class Genero {
	
	private int id;
	private String nombre;
	
	/**
	 * Constructor simple de la clase Genero
	 */
	public Genero() {
		super();
	}

	/**
	 * Constructor con parametros de la clase Genero
	 * 
	 * @param id guarda el id de un genero
	 * @param nombre es el nombre del genero
	 */
	public Genero(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 *Este metodo combia el print por defecto de la clase Genero
	 */
	@Override
	public String toString() {
		return "Genero [id=" + id + ", nombre=" + nombre + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
