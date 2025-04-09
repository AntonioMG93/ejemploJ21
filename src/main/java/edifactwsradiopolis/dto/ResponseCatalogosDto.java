package edifactwsradiopolis.dto;

import java.util.ArrayList;

public class ResponseCatalogosDto {
	
	private String codigo;
	private String descripcion;
	private ArrayList<FormatosVo> listFormatosVo = new ArrayList<FormatosVo>();
	
	public ArrayList<FormatosVo> getListFormatosVo() {
		return listFormatosVo;
	}
	public void setListFormatosVo(ArrayList<FormatosVo> listFormatosVo) {
		this.listFormatosVo = listFormatosVo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
