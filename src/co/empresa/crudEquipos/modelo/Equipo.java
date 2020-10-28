package co.empresa.crudEquipos.modelo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo implements Serializable {
	
	private Integer id;
	
	private String nombre;
	
	private String pais;
	
	private String trofeos;
	
	public Equipo(String nombre, String pais, String trofeos) {
		this.nombre= nombre;
		this.pais = pais;
		this.trofeos = trofeos;
	}


}
