package edifactwsradiopolis.dto;

public class ResponseComprobanteAdminisrtaDatosCDto {

	private String documentoxml;
	private String documentopdf;
	private String codigo;
	private String descripcion;
	private String UUID;
	private String fechaTimbrado;
	private String selloCFDI;
	private String selloSAT;
	private String cancelable;
	private String estado;
	
	
	public String getCancelable() {
		return cancelable;
	}

	public void setCancelable(String cancelable) {
		this.cancelable = cancelable;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getSelloCFDI() {
		return selloCFDI;
	}

	public void setSelloCFDI(String selloCFDI) {
		this.selloCFDI = selloCFDI;
	}

	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}

	private String noCertificado;
	
	
	public String getSelloSAT() {
		return selloSAT;
	}

	public void setSelloSAT(String selloSAT) {
		this.selloSAT = selloSAT;
	}


	public String getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(String fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}


	public String getDocumentopdf() {
		return documentopdf;
	}

	public void setDocumentopdf(String documentopdf) {
		this.documentopdf = documentopdf;
	}

	public String getDocumentoxml() {
		return documentoxml;
	}

	public void setDocumentoxml(String documentoxml) {
		this.documentoxml = documentoxml;
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

	public ResponseComprobanteAdminisrtaDatosCDto() {

	}

	

}
