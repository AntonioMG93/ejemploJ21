package edifactwsradiopolis.dto;

import java.math.BigDecimal;

public class TrasladoDto {
	private BigDecimal importe;
    private String impuesto;
    //private BigDecimal tasa;
    private BigDecimal totalImpuestosTrasladados;
    //Cambios 3.3
    private String tasa;
    private String tipoFactor;

	public String getTipoFactor() {
		return tipoFactor;
	}

	public void setTipoFactor(String tipoFactor) {
		this.tipoFactor = tipoFactor;
	}

	public BigDecimal getTotalImpuestosTrasladados() {
		return totalImpuestosTrasladados;
	}

	public void setTotalImpuestosTrasladados(BigDecimal totalImpuestosTrasladados) {
		this.totalImpuestosTrasladados = totalImpuestosTrasladados;
	}

	/**
     * @return the importe
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    /**
     * @return the impuesto
     */
    public String getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * @return the tasa
     */
    public String getTasa() {
        return tasa;
    }

    /**
     * @param tasa the tasa to set
     */
    public void setTasa(String tasa) {
        this.tasa = tasa;
    }
}
