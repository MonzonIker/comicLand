package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Conector;
import modelo.bean.Comic;

public class ModeloComic extends Conector {
	
	/**
	 * Este metodo devuelbe una lista de comics que es rellenado con la informacion de la abse de datos
	 * 
	 * @return lista de comics
	 */
	public ArrayList<Comic> getAll(){

		ArrayList<Comic> comics = new ArrayList<Comic>();
		ModeloGenero MG = new ModeloGenero();

		try {

			PreparedStatement pst = super.conexion.prepareStatement("select * from comics");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Comic comic = new Comic();

				comic.setId(rs.getInt("id"));

				comic.setNombre(rs.getString("nombre"));

				comic.setTitulo(rs.getString("titulo"));
				
				comic.setNum(rs.getInt("num"));
				
				comic.setFecha_publicacion(rs.getDate("fecha_publicacion"));
				
				comic.setImagen(rs.getString("imagen"));
				
				comic.setNum_likes(rs.getInt("num_likes"));
				
				comic.setGenero(MG.get(rs.getInt("genero_id")));

				comics.add(comic);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return comics;

	}
	/**
	 * Este metodo nos recupera un Comic y su informacion que tenga el mismo id que le damos como parametro
	 * 
	 * @param id es la id de el comic que queremos recuperar
	 * @return objeto de la clase Comic
	 */
	public Comic get(int id){

		Comic comic = new Comic();
		ModeloGenero MG = new ModeloGenero();

		try {

			PreparedStatement pst = super.conexion.prepareStatement("select * from comics where id="+id);

			ResultSet rs = pst.executeQuery();
			
			

			while (rs.next()) {

				

				comic.setId(rs.getInt("id"));

				comic.setNombre(rs.getString("nombre"));

				comic.setTitulo(rs.getString("titulo"));
				
				comic.setNum(rs.getInt("num"));
				
				comic.setFecha_publicacion(rs.getDate("fecha_publicacion"));
				
				comic.setImagen(rs.getString("imagen"));
				
				comic.setNum_likes(rs.getInt("num_likes"));
				
				comic.setGenero(MG.get(rs.getInt("genero_id")));

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return comic;

	}
	
	/**
	 * Este metodo actualiza el numerod e likes de el Comic que le demos como parametros en la base de datos
	 * 
	 * @param c1 es un objeto de clse Comic
	 */
	public void updateLike(Comic c1) {
		
		int id = c1.getId();
		
		try {
			PreparedStatement pstUpdate = super.conexion.prepareStatement("update comics set num_likes=num_likes + 1 where id="+id);
			pstUpdate.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Este metodo elimina de la base de datos el comic que tenga la id que le damos como parametro
	 * 
	 * @param id la id del comic
	 */
	public void delete(int id) {
		try {
			 PreparedStatement pstDelete = conexion.prepareStatement("DELETE FROM comics WHERE id = ?");
			 pstDelete.setInt(1, id);
			 pstDelete.execute();
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Este metodo crea una nueva entrada en la base de datos con informacion de un comic
	 * 
	 * @param c1 es de clase Comic y guarda la info de un comic
	 */
	public void insert(Comic c1) {
		java.util.Date utilData = c1.getFecha_publicacion();
		java.sql.Date sqlData = new java.sql.Date(utilData.getTime());
		try {
			 PreparedStatement pstDelete = conexion.prepareStatement("CALL spInsertComic(?,?,?,?,?,?,?)");
			 pstDelete.setString(1, c1.getNombre());
			 pstDelete.setString(2, c1.getTitulo());
			 pstDelete.setInt(3, c1.getNum());
			 pstDelete.setDate(4, sqlData);
			 pstDelete.setString(5, c1.getImagen());
			 pstDelete.setInt(6, c1.getNum_likes());
			 pstDelete.setInt(7, c1.getGenero().getId());
			 pstDelete.execute();
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Este metodo actualiza la informacion de un comic existente en la base de datos
	 * 
	 * @param c1 es de tipo Comic y tiene la indormacion a actualizar
	 */
	public void update(Comic c1) {
		java.util.Date utilData = c1.getFecha_publicacion();
		java.sql.Date sqlData = new java.sql.Date(utilData.getTime());
		try {
			 PreparedStatement pstDelete = conexion.prepareStatement("CALL spUpdateComic(?,?,?,?,?,?,?,?)");
			 pstDelete.setInt(1, c1.getId());
			 pstDelete.setString(2, c1.getNombre());
			 pstDelete.setString(3, c1.getTitulo());
			 pstDelete.setInt(4, c1.getNum());
			 pstDelete.setDate(5, sqlData);
			 pstDelete.setString(6, c1.getImagen());
			 pstDelete.setInt(7, c1.getNum_likes());
			 pstDelete.setInt(8, c1.getGenero().getId());
			 pstDelete.execute();
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
