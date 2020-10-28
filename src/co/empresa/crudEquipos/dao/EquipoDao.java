package co.empresa.crudEquipos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.crudEquipos.modelo.Equipo;
import co.empresa.crudEquipos.util.Conexion;

public class EquipoDao {
	
	private Conexion conexion;
	
	private static final String INSERT_EQUIPO_SQL= "INSERT INTO equipo(nombre, pais, trofeos) VALUES(?,?,?);";
	private static final String DELETE_EQUIPO_SQL= "DELETE FROM equipo WHERE id = ?;";
	private static final String UPDATE_EQUIPO_SQL= "UPDATE equipo SET nombre = ?, pais = ? , trofeos = ? WHERE id = ? ;";
	private static final String SELECT_EQUIPO_BY_ID= "SELECT * FROM equipo WHERE ID = ?;";
	private static final String SELECT_ALL_EQUIPOS= "SELECT * FROM equipo;";
	
	public EquipoDao() {
		this.conexion = Conexion.getConexion();
	}
	
	
	public void insert(Equipo equipo) throws SQLException{
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(INSERT_EQUIPO_SQL);
			preparedStatement.setString(1, equipo.getNombre());
			preparedStatement.setString(2, equipo.getPais());
			preparedStatement.setString(3, equipo.getTrofeos());
			conexion.execute();
		}catch(SQLException e) {	
		}
	}
	
	public void delete(int id) throws SQLException{
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_EQUIPO_SQL);
			preparedStatement.setInt(1, id);
			conexion.execute();
		}catch(SQLException e) {	
		}
	}
	
	public void update(Equipo equipo) throws SQLException{
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(UPDATE_EQUIPO_SQL);
			preparedStatement.setString(1, equipo.getNombre());
			preparedStatement.setString(2, equipo.getPais());
			preparedStatement.setString(3, equipo.getTrofeos());
			preparedStatement.setInt(4, equipo.getId());
			conexion.execute();
		}catch(SQLException e) {	
		}
	}
	
	public List<Equipo> selectAll(){
		List <Equipo> equipos = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_EQUIPOS);
			ResultSet rs = conexion.query();		
			while (rs.next()) {
				int id= rs.getInt("id");
				String nombre = rs.getString("nombre");
				String pais = rs.getString("pais");
				String trofeos = rs.getString("trofeos");
				equipos.add(new Equipo(id,nombre,pais,trofeos)); 
			}
			}catch(SQLException e){
			
			}
			return equipos; 
	}
	
	public Equipo select(int id){
		Equipo equipo = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_EQUIPO_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();		
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String pais = rs.getString("pais");
				String trofeos = rs.getString("trofeos");
				equipo =new Equipo(id,nombre,pais,trofeos); 
			}
			}catch(SQLException e){
			
			}
			return equipo; 
	}
	
}
