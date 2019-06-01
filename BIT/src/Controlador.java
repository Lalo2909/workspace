import java.awt.List;
import java.sql.*;
import java.util.ArrayList;

public class Controlador {
	public ArrayList<Reglas> reglas = new ArrayList<>();
	public Connection con;
	public ConectDB conexion;
	
	public Controlador() {		
		this.reglas.add(new Reglas(1000, "Libros", "MSI"));
		this.reglas.add(new Reglas(1000, "Deportes", "Puntos"));
		this.reglas.add(new Reglas(1000, "Juegos", "CashBack"));
		this.reglas.add(new Reglas(1000, "Cines", "Puntos"));
		this.reglas.add(new Reglas(1000, "Otros", "Sin Oferta"));
	}
	
	public boolean realizarpago(String cliente, int monto, String giro, String transaccion) throws SQLException {
		if(transaccion.equals("000")) {
			String segmentoCliente = "";
			//Validacion de segmento del cliente
			segmentoCliente = getSegmentoCliente(cliente);
			//Validación de segmento de la transcción
			switch(giro.substring(0, 2)) {
			case "00":
				if(segmentoCliente.equals("00") && monto > this.reglas.get(0).getMonto()) {
					System.out.println("Felicidades acabas de obtener promocion de " + this.reglas.get(0).getPromocion());
				}else {
					System.out.println("La promocion no aplica");
				}
				break;
			case "02":
				if(segmentoCliente.equals("02") && monto > this.reglas.get(1).getMonto()) {
					System.out.println("Felicidades acabas de obtener promocion de " + this.reglas.get(1).getPromocion());
				}else {
					System.out.println("La promocion no aplica");
				}
				break;
			case "25":
				if(segmentoCliente.equals("25") && monto > this.reglas.get(2).getMonto()) {
					System.out.println("Felicidades acabas de obtener promocion de " + this.reglas.get(2).getPromocion());
				}else {
					System.out.println("La promocion no aplica");
				}
				break;
			case "44":
				if(segmentoCliente.equals("44") && monto > this.reglas.get(3).getMonto()) {
					System.out.println("Felicidades acabas de obtener promocion de " + this.reglas.get(3).getPromocion());
				}else {
					System.out.println("La promocion no aplica");
				}
				break;
			case "67":
				if(segmentoCliente.equals("67") && monto > this.reglas.get(4).getMonto()) {
					System.out.println("Felicidades acabas de obtener promocion de " + this.reglas.get(4).getPromocion());
				}else {
					System.out.println("La promocion no aplica");
				}
				break;
			}
			
			return true;
		}else {
			System.out.println("Transaccion no valida");
			return false;
		}
	}
	
	public String getSegmentoCliente(String cliente) throws SQLException {     
	    conexion = new ConectDB();
	    con = conexion.getConnection();
	    String segmento = "";
	    
	    String query = "select * from cliente where id_cliente = ?";	    
	    PreparedStatement preparedStmt = con.prepareStatement(query);
	    preparedStmt.setString (1, cliente);
 		ResultSet rs=preparedStmt.executeQuery();
 	  	          
	   	while (rs.next()) {
	   		segmento = rs.getString("id_segmento");
	   	  }
	      con.close();
		return segmento;
	}
}
