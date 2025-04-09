package edifactwsradiopolis.dto;

import java.math.BigDecimal;

public class RetencionDto {
	private BigDecimal importe;
    private String impuesto;
    private BigDecimal tasa;
    private BigDecimal totalImpuestosRetenidos;

    public BigDecimal getTotalImpuestosRetenidos() {
		return totalImpuestosRetenidos;
	}

	public void setTotalImpuestosRetenidos(BigDecimal totalImpuestosRetenidos) {
		this.totalImpuestosRetenidos = totalImpuestosRetenidos;
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
    public BigDecimal getTasa() {
        return tasa;
    }

    /**
     * @param tasa the tasa to set
     */
    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }

}
