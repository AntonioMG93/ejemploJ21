package edifactwsradiopolis.dto;

public class ResponseXMLParseDto {
	private ComprobanteParseDto comprobanteParseDto;
	private String documento;
	private String codigo;
	private String descripcion;
	private String codigoRecuperado;
	private String pdfBase64;
	
	
	
	public String getPdfBase64() {
		return pdfBase64;
	}

	public void setPdfBase64(String pdfBase64) {
		this.pdfBase64 = pdfBase64;
	}

	public String getCodigoRecuperado() {
		return codigoRecuperado;
	}

	public void setCodigoRecuperado(String codigoRecuperado) {
		this.codigoRecuperado = codigoRecuperado;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public ComprobanteParseDto getComprobanteParseDto() {
		return comprobanteParseDto;
	}

	public void setComprobanteParseDto(ComprobanteParseDto comprobanteParseDto) {
		this.comprobanteParseDto = comprobanteParseDto;
	}
}
