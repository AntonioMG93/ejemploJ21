package edifactwsradiopolis.dto;

import mx.gob.sat.cfd4_0.CPais;

public class ReceptorDto {
	private String rfc;   
	private String nombre;    
	private String calle;    
	private String num_int;  
	private String num_ext;   
	private String colonia;    
	private String municipio;
	private String ciudad;
	private String estado;
	private String pais;
	private String cp;
	private String email;
	private String localidad;
	private String referencia;
	private String rfcOriginal;
	//Atributos v3.3
	private String usoCfdi;
	private String correo;
	private String iAddenda;
	private String numeroCliente;
	private String ruta;
	private String numRegIdTributario;
        private CPais residenciaFiscal;

    public CPais getResidenciaFiscal() {
        return residenciaFiscal;
    }

    public void setResidenciaFiscal(CPais residenciaFiscal) {
        this.residenciaFiscal = residenciaFiscal;
    }


    public String getCorreo() {
		return correo;
	}

	public String getNumRegIdTributario() {
		return numRegIdTributario;
	}

	public void setNumRegIdTributario(String numRegIdTributario) {
		this.numRegIdTributario = numRegIdTributario;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getiAddenda() {
		return iAddenda;
	}

	public void setiAddenda(String iAddenda) {
		this.iAddenda = iAddenda;
	}

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getUsoCfdi() {
		return usoCfdi;
	}

	public void setUsoCfdi(String usoCfdi) {
		this.usoCfdi = usoCfdi;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ReceptorDto() {
        super();
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNum_int() {
        return num_int;
    }

    public void setNum_int(String num_int) {
        this.num_int = num_int;
    }

    public String getNum_ext() {
        return num_ext;
    }

    public void setNum_ext(String num_ext) {
        this.num_ext = num_ext;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getRfcOriginal() {
		return rfcOriginal;
	}

	public void setRfcOriginal(String rfcOriginal) {
		this.rfcOriginal = rfcOriginal;
	}
}
