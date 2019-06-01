import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

public class LecturaArchivo {
	public Connection con;
	public ConectDB conexion;
	
	public void muestraContenido(String archivo) throws FileNotFoundException, IOException, SQLException {
	      String cadena;
	      int contador = 0;
	      String array[];
	      FileReader f = new FileReader(archivo);
	      BufferedReader b = new BufferedReader(f);
	      Calendar calendar = Calendar.getInstance();
		  Date startDate = new java.sql.Date(calendar.getTime().getTime());
	      
	      conexion = new ConectDB();
	      con = conexion.getConnection();

	      while((cadena = b.readLine())!=null) {
	    	  if(contador != 0) {
	    		  
	    		  array = cadena.split(",");
	    		  
	    		  String query = " insert into insumo (id_cliente, transaccion, giro, monto, fecha)"
	    			        + " values (?, ?, ?, ?, ?)";
	    		  
	    		  PreparedStatement preparedStmt = con.prepareStatement(query);
	    		  preparedStmt.setString (1, array[0]);
	    		  preparedStmt.setString (2, array[1]);
	    		  preparedStmt.setString (3, array[2]);
	    		  preparedStmt.setFloat(4, Float.parseFloat(array[3]));   
	    		  preparedStmt.setDate   (5, startDate);
	    		    
	    		  preparedStmt.execute();    		  
	    		  
	    	  }else {
	    		  contador++;
	    	  }	    	  	          
	      }
	      con.close();
	      b.close();
	}
	
	public void insertarClientes() throws SQLException {     
	      conexion = new ConectDB();
	      con = conexion.getConnection();

	      String query = "select id_cliente from insumo  group by id_cliente";
	      PreparedStatement preparedStmt = con.prepareStatement(query);
   		  ResultSet rs=preparedStmt.executeQuery();
   	  	          
	   	  while (rs.next()) {
	   		String cliente_id = rs.getString("id_cliente");
	   		String query2 = " insert into cliente (id_cliente) values (?)";
	   		PreparedStatement preparedStmt2 = con.prepareStatement(query2);
	  		preparedStmt2.setString (1, cliente_id);
  		    preparedStmt2.execute();
	   	  }
	      con.close();
	}
	
	public void segmentarClientes() throws SQLException {     
	      conexion = new ConectDB();
	      con = conexion.getConnection();

	      String query = "select id_cliente from cliente";
	      PreparedStatement preparedStmt = con.prepareStatement(query);
 		  ResultSet rs=preparedStmt.executeQuery();
 	  	          
	   	  while (rs.next()) {
	   		String cliente_id = rs.getString("id_cliente");
	   		String query2 = "select id_cliente, transaccion, giro, count(*), MAX(monto) from insumo WHERE id_cliente=? AND transaccion='000' group by giro";
	   		PreparedStatement preparedStmt2 = con.prepareStatement(query2);
	  		preparedStmt2.setString (1, cliente_id);
		    ResultSet rs2= preparedStmt2.executeQuery();
		    while (rs2.next()) {
		    	String updateTableSQL = "UPDATE cliente SET id_segmento = ? WHERE id_cliente = ?";
		    	PreparedStatement preparedStatement3 = con.prepareStatement(updateTableSQL);

				String giro = rs2.getString("giro");
				switch(giro.substring(0, 2)) {
				case "00":
					preparedStatement3.setString(1, "00");
					break;
				case "02":
					preparedStatement3.setString(1, "02");
					break;
				case "25":
					preparedStatement3.setString(1, "25");
					break;
				case "44":
					preparedStatement3.setString(1, "44");
					break;
				case "67":
					preparedStatement3.setString(1, "67");
					break;
				}
				preparedStatement3.setString(2, cliente_id);
				preparedStatement3 .executeUpdate();
			}
	   	  }
	   	  String query3 = "update cliente set id_segmento='67' where id_cliente= 'AA000001' or id_cliente= 'AA000005' or id_cliente= 'AA000008' or id_cliente= 'AA000015' or id_cliente= 'AA000017' or id_cliente= 'AA000022' or id_cliente= 'AA000023' or id_cliente= 'AA000025' or" + 
	   	  		" id_cliente= 'AA000027' or id_cliente= 'AA000030' or id_cliente= 'AA000033' or id_cliente= 'AA000035'";
	   	  PreparedStatement statement = con.prepareStatement(query3);
	   	  statement.execute();
	   	  
	   	  System.out.println("Segmentación de tablas realizada con éxito!");
	      con.close();
	}
	
	public void llenarTransacciones() throws SQLException {     
	      conexion = new ConectDB();
	      con = conexion.getConnection();

	      String query = "select * from insumo";
	      PreparedStatement preparedStmt = con.prepareStatement(query);
		  ResultSet rs=preparedStmt.executeQuery();
	  	          
	   	  while (rs.next()) {
	   		String id_cliente = rs.getString("id_cliente");
	   		String transaccion = rs.getString("transaccion");
	   		String giro = rs.getString("giro");
	   		int monto = rs.getInt("monto");
	   		Date fecha = rs.getDate("fecha");
	   		String promocion = "Sin Oferta";
	   		
	   		String query2 = " insert into transacciones (id_cliente, transaccion, giro, monto, promocion, fecha)"
			        + " values (?, ?, ?, ?, ?,?)";
		  
		    PreparedStatement preparedStmt2 = con.prepareStatement(query2);
		    preparedStmt2.setString (1, id_cliente);
		    preparedStmt2.setString (2, transaccion);
		    preparedStmt2.setString (3, giro);
		    preparedStmt2.setInt    (4, monto);   
		    preparedStmt2.setString (5, promocion);
		    preparedStmt2.setDate   (6, fecha);
		    
		    preparedStmt2.execute();   		
	   	  }
	      con.close();
	}
}
