import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class BIT {
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		Controlador controlador = new Controlador();
		Boolean resultado;
				
		LecturaArchivo archivo = new LecturaArchivo();
		//archivo.muestraContenido("C:\\Users\\edmoe\\OneDrive\\Escritorio\\BIT\\insumoBit.txt");
		//archivo.insertarClientes();
		//archivo.segmentarClientes();
		//archivo.llenarTransacciones();
		
		resultado = controlador.realizarpago("AA000001", 80715, "6713", "000");
		System.out.println();
		resultado = controlador.realizarpago("AA000002", 8715, "6713", "001");
		System.out.println();
		resultado = controlador.realizarpago("AA000003", 8715, "4413", "000");
		
		
		

		
	}
}
