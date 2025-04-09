package edifactwsradiopolis.dto;

public class ExpedidoEnDto {
	private String Calle;
    private String CodigoPostal;
    private String Colonia;
    private String Estado;
    private String NoExterior;
    private String NoInterior;
    private String Pais;
    private String Municipio;
    private String Localidad;
    private String Referencia;

    public String getReferencia() {
		return Referencia;
	}

	public void setReferencia(String referencia) {
		Referencia = referencia;
	}

	public String getNoInterior() {
		return NoInterior;
	}

	public void setNoInterior(String noInterior) {
		NoInterior = noInterior;
	}

	public String getMunicipio() {
		return Municipio;
	}

	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	/**
     * @return the Calle
     */
    public String getCalle() {
        return Calle;
    }

    /**
     * @param Calle the Calle to set
     */
    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    /**
     * @return the CodigoPostal
     */
    public String getCodigoPostal() {
        return CodigoPostal;
    }

    /**
     * @param CodigoPostal the CodigoPostal to set
     */
    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    /**
     * @return the Colonia
     */
    public String getColonia() {
        return Colonia;
    }

    /**
     * @param Colonia the Colonia to set
     */
    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the NoExterior
     */
    public String getNoExterior() {
        return NoExterior;
    }

    /**
     * @param NoExterior the NoExterior to set
     */
    public void setNoExterior(String NoExterior) {
        this.NoExterior = NoExterior;
    }

    /**
     * @return the Pais
     */
    public String getPais() {
        return Pais;
    }

    /**
     * @param Pais the Pais to set
     */
    public void setPais(String Pais) {
        this.Pais = Pais;
    }
}
