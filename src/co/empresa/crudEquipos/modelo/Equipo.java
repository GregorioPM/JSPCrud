package co.empresa.crudEquipos.modelo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Equipo implements Serializable {
	
	private Integer idEquipo;
	
	private String nombre;
	
	private String pais;
	
	private String trofeos;


}
