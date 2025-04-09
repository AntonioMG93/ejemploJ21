package edifactwsradiopolis.dto;

public class Cancelaciones {
	private String nombre;
	private String uuid;
	private String fecha;
	private String rfcEmisor;
	private String rfcReceptor;
	private String fechatimbrado;
	private String importe;
	private String estado;
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getRfcEmisor() {
		return rfcEmisor;
	}
	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}
	public String getRfcReceptor() {
		return rfcReceptor;
	}
	public void setRfcReceptor(String rfcReceptor) {
		this.rfcReceptor = rfcReceptor;
	}
	public String getFechatimbrado() {
		return fechatimbrado;
	}
	public void setFechatimbrado(String fechatimbrado) {
		this.fechatimbrado = fechatimbrado;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	
}
