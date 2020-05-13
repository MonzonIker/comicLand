package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Conector;
import modelo.bean.Comic;

public class ModeloComic extends Conector {
	
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
	
	public void updateLike(Comic c1) {
		
		int id = c1.getId();
		
		try {
			PreparedStatement pstUpdate = super.conexion.prepareStatement("update comics set num_likes=num_likes + 1 where id="+id);
			pstUpdate.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
