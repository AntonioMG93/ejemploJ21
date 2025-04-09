package edifactwsradiopolis.dto;

public class WSDLUsersVo {
	int prewsdl_id;
	String usuario;
	String clave;
	String estatus;
	String fecha_alta;
	
	public int getPrewsdl_id() {
		return prewsdl_id;
	}
	public void setPrewsdl_id(int prewsdl_id) {
		this.prewsdl_id = prewsdl_id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(String fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
}
