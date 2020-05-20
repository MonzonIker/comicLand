package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Conector;
import modelo.bean.Genero;

public class ModeloGenero extends Conector {
	
	/**
	 * Este metodo devuelbe una lista de generos que es rellenado con la informacion de la abse de datos
	 * 
	 * @return lista de generos
	 */
	public ArrayList<Genero> getAll(){

		ArrayList<Genero> generos = new ArrayList<Genero>();

		try {

			PreparedStatement pst = super.conexion.prepareStatement("select * from generos");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Genero genero = new Genero();

				genero.setId(rs.getInt("id"));

				genero.setNombre(rs.getString("nombre"));

				generos.add(genero);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return generos;

	}
	
	/**
	 * Este metodo nos recupera un Genero y su informacion que tenga el mismo id que le damos como parametro
	 * 
	 * @param id es la id de el genero que queremos recuperar
	 * @return objeto de la clase Genero
	 */
	public Genero get(int id){

		Genero genero = new Genero();

		try {

			PreparedStatement pst = super.conexion.prepareStatement("select * from generos where id="+id);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				genero.setId(rs.getInt("id"));

				genero.setNombre(rs.getString("nombre"));

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return genero;

	}

}
