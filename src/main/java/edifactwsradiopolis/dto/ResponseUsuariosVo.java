package edifactwsradiopolis.dto;

import java.util.ArrayList;


public class ResponseUsuariosVo {
	private String respuesta;
	private String descripcion;
	private ArrayList<UsuarioVo> listUsuariosVo = new ArrayList<UsuarioVo>();
	private ArrayList<WSDLUsersVo> listWSDLUsersVo = new ArrayList<WSDLUsersVo>();
	private ArrayList<ParametrosVo> listParametrosVo = new ArrayList<ParametrosVo>();
	
	public ArrayList<ParametrosVo> getListParametrosVo() {
		return listParametrosVo;
	}
	public void setListParametrosVo(ArrayList<ParametrosVo> listParametrosVo) {
		this.listParametrosVo = listParametrosVo;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList<UsuarioVo> getListUsuariosVo() {
		return listUsuariosVo;
	}
	public void setListUsuariosVo(
			ArrayList<UsuarioVo> listUsuariosVo) {
		this.listUsuariosVo = listUsuariosVo;
	}
	public ArrayList<WSDLUsersVo> getListWSDLUsersVo() {
		return listWSDLUsersVo;
	}
	public void setListWSDLUsersVo(ArrayList<WSDLUsersVo> listWSDLUsersVo) {
		this.listWSDLUsersVo = listWSDLUsersVo;
	}
	
}
