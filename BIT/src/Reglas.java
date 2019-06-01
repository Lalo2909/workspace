
public class Reglas {
	public int monto;
	public String segmento;
	public String promocion;
	
	public Reglas(int monto, String segmento, String promocion) {
		this.monto = monto;
		this.segmento = segmento;
		this.promocion = promocion;
	}
	
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public String getPromocion() {
		return promocion;
	}
	public void setPromocion(String promocion) {
		this.promocion = promocion;
	}	
}
