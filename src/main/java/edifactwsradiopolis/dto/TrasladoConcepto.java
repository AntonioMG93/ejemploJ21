package edifactwsradiopolis.dto;

import java.math.BigDecimal;

public class TrasladoConcepto {
	//Atributos version 3.3
	private BigDecimal importe;
    private String impuesto;
    private BigDecimal base;
    private String tipoFactor;
    private String TasaOCuota;
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	public BigDecimal getBase() {
		return base;
	}
	public void setBase(BigDecimal base) {
		this.base = base;
	}
	public String getTipoFactor() {
		return tipoFactor;
	}
	public void setTipoFactor(String tipoFactor) {
		this.tipoFactor = tipoFactor;
	}
	public String getTasaOCuota() {
		return TasaOCuota;
	}
	public void setTasaOCuota(String tasaOCuota) {
		TasaOCuota = tasaOCuota;
	}
}
