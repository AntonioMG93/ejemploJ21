package edifactwsradiopolis.dto;

import java.math.BigDecimal;

public class TrasladosDR {
	//Atributos version 3.3
	private BigDecimal importeDR;
    private String impuestoDR;
    private BigDecimal baseDR;
    private String tipoFactorDR;
    private String TasaOCuotaDR;

    public BigDecimal getImporteDR() {
        return importeDR;
    }

    public void setImporteDR(BigDecimal importeDR) {
        this.importeDR = importeDR;
    }

    public String getImpuestoDR() {
        return impuestoDR;
    }

    public void setImpuestoDR(String impuestoDR) {
        this.impuestoDR = impuestoDR;
    }

    public BigDecimal getBaseDR() {
        return baseDR;
    }

    public void setBaseDR(BigDecimal baseDR) {
        this.baseDR = baseDR;
    }

    public String getTipoFactorDR() {
        return tipoFactorDR;
    }

    public void setTipoFactorDR(String tipoFactorDR) {
        this.tipoFactorDR = tipoFactorDR;
    }

    public String getTasaOCuotaDR() {
        return TasaOCuotaDR;
    }

    public void setTasaOCuotaDR(String TasaOCuotaDR) {
        this.TasaOCuotaDR = TasaOCuotaDR;
    }

    
}
