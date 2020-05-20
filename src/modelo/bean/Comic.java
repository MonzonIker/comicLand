package modelo.bean;

import java.util.Date;

public class Comic {

	private int id;
	private String nombre;
	private	String titulo;
	private int num;
	private Date fecha_publicacion;
	private String imagen;
	private int num_likes;
	private Genero genero;
	
	/**
	 * Constructor simple de la clase Comic
	 */
	public Comic() {
		super();
	}

	/**
	 * Constructor con parametros de la clase Comic
	 * 
	 * @param id es la id unica de cada comic
	 * @param nombre es el nombre que comparte una saga de comics
	 * @param titulo es el titulo que tiene un numero concreto
	 * @param num es el numero de la coleccion de un comic
	 * @param fecha_publicacion es la fecha en la que ha sido publicado el comic
	 * @param imagen para guardar un enlace a una imgen de la portadad del comic
	 * @param num_likes guarda la cantidad de likes que tiene un comic
	 * @param genero es un objeto de la clase Genero con la informacion sobre el genero de un comic
	 */
	public Comic(int id, String nombre, String titulo, int num, Date fecha_publicacion, String imagen, int num_likes,
			Genero genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.titulo = titulo;
		this.num = num;
		this.fecha_publicacion = fecha_publicacion;
		this.imagen = imagen;
		this.num_likes = num_likes;
		this.genero = genero;
	}

	/**
	 *Este metodo combia el printt por defecto de la clase Comic
	 */
	@Override
	public String toString() {
		return "Comics [id=" + id + ", nombre=" + nombre + ", titulo=" + titulo + ", num=" + num
				+ ", fecha_publicacion=" + fecha_publicacion + ", imagen=" + imagen + ", num_likes=" + num_likes
				+ ", genero=" + genero + "]";
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getNum_likes() {
		return num_likes;
	}

	public void setNum_likes(int num_likes) {
		this.num_likes = num_likes;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
}
