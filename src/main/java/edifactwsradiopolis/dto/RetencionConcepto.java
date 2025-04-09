package edifactwsradiopolis.dto;

import java.math.BigDecimal;

public class RetencionConcepto {
	private BigDecimal importe;
    private String impuesto;
    private BigDecimal base;
    private String tipoFactor;
    private BigDecimal TasaOCuota;
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
	public BigDecimal getTasaOCuota() {
		return TasaOCuota;
	}
	public void setTasaOCuota(BigDecimal tasaOCuota) {
		TasaOCuota = tasaOCuota;
	}
}
