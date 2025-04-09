package edifactwsradiopolis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComprobanteParseDto {
	private String version;
	private String folio;
	private String serie;
    private Date Fecha;
    private String FormaDePago;
    private String selloCFD;
    private String noCertificado;
    private String certificado;
    private String condicionesPago;
    private BigDecimal SubTotal;
    private BigDecimal Total;
    private String Moneda;
    private BigDecimal tipoCambio;
    private String TipoDeComprobante;
    private String MetodoDePago;
    private String LugarExpedicion;
    
    private String regimen;
    private String qrcode;
    private String numCta;
    
    private String orden_compra;
    private String orden_venta;
    
    private BigDecimal descuento;
    
    private String folioFiscalOrig;
    private String serieFolioFiscalOrig;
    private String fechaFolioFiscalOrig;
    private String montoFolioFiscalOrig;
    private String selloEmisor;
    private String UUID;
    private String FechaTimbrado;
    private String noCertificadoSAT;
    
    private String selloSAT;
    private String versionTFD;
    
    private String detallePDF;
    private String telefono;
    private String correo;
    private String correodos;
    private String correotres;
    private String nosap;
    private String cadenaOriginal;
    private String addenda;
    private String FormatoPDF;
    private String CantidadLetra;
    private EmisorDto emisor;
    private ReceptorDto receptorDto;
    private List<DetalleComprobanteXMLParseDto> detalle = new ArrayList<DetalleComprobanteXMLParseDto>();
    private List<TrasladoDto> impuestosTrasladados = new ArrayList<TrasladoDto>();
    private List<RetencionDto> impuestosRetenidos = new ArrayList<RetencionDto>();
    private TrasladoDto trasladoDto;
    private RetencionDto retencionDto;
    private ExpedidoEnDto expedidoEnDto;
    private String Posicion; 
    private String Job;
    private String Ciudad;
    private String Localidad;
    private String Pais;
    
    private String RfcCliente;
    private String RfcOriginal;
	private String OrdenCompra;
	private String Origen;
	private String Destino;
	private String Aduana;
	private String Agente;
	private String TotalParcial;
	private String IVA;
	private String Retenciones;
	private String TotalFactura;
	private String Anticipos;
	private String TotalEnLetras;
	private String Leyenda;
    private String fechaEm;
    private String fechaVto;
	private String impuestoRetenido;
	private String observacionesUno;
	private String observacionesDos;
	private String observacionesTres;
	private String observacionesCuatro;
	private String observacionesCinco;
	private String tipoPdf;
	private String eta;
	private String etd;
    private String TelefonoCli;
    private String fechaFormat;
    private String nomTipoDocumento;
    private String nomTipoDocEncabezado;
	private String saldo;
	private String totalImporte;
	private String totImporte;
	private String cfdiRelacionado;
	private ArrayList<Relacionados> listRelacionados = new ArrayList<Relacionados>();  
    //PROA 
	private String previsualizar;
	private String selloQr;
	private String rfcPac;
	private String fechaEmisPdf;
	
	private String fecha_pdf;
        private ComercioExterior comercioExt;
        private EmisorComercioExt emisorComExt;
        private ReceptorComercioExt receptorComExt;
        private ArrayList<MercanciaComercion> mercComercioExt = new ArrayList<MercanciaComercion>();
        private ArrayList<DescripcionesEspMercanciaComerExt> descEspMerc = new ArrayList<DescripcionesEspMercanciaComerExt>();
        private ArrayList<LeyendaFiscal> listLeyenda = new ArrayList<LeyendaFiscal>();
        private ArrayList<Pagos> listPagos =new ArrayList<Pagos>();
        
//        private AddendaConcepto addendaConcepto;
	private AddendaMabeDetalles addendaLeyendasF;
	private AddendaDetallistaItem addendaFDevolucion;
	private AddendaMabeCabecero addendaNCR;
	private AddendaDetallistaC addendaExtras;
	private AddendaReceptor addendaRecepDomicilio;
	private AddendaSanofi addendaRecepEntrega;
        private List<AddendaConcepto> addendaConcepto = new ArrayList<AddendaConcepto>();
        private AddendaCPagos addendaCPagos;
        
        // correo
        private String usuario;
        private String ejecutivo;
        

    public AddendaCPagos getAddendaCPagos() {
        return addendaCPagos;
    }

    public void setAddendaCPagos(AddendaCPagos addendaCPagos) {
        this.addendaCPagos = addendaCPagos;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEjecutivo() {
        return ejecutivo;
    }

    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

    public List<AddendaConcepto> getAddendaConcepto() {
        return addendaConcepto;
    }

    public void setAddendaConcepto(List<AddendaConcepto> addendaConcepto) {
        this.addendaConcepto = addendaConcepto;
    }

    public AddendaMabeDetalles getAddendaLeyendasF() {
        return addendaLeyendasF;
    }

    public void setAddendaLeyendasF(AddendaMabeDetalles addendaLeyendasF) {
        this.addendaLeyendasF = addendaLeyendasF;
    }

    public AddendaDetallistaItem getAddendaFDevolucion() {
        return addendaFDevolucion;
    }

    public void setAddendaFDevolucion(AddendaDetallistaItem addendaFDevolucion) {
        this.addendaFDevolucion = addendaFDevolucion;
    }

    public AddendaMabeCabecero getAddendaNCR() {
        return addendaNCR;
    }

    public void setAddendaNCR(AddendaMabeCabecero addendaNCR) {
        this.addendaNCR = addendaNCR;
    }

    public AddendaDetallistaC getAddendaExtras() {
        return addendaExtras;
    }

    public void setAddendaExtras(AddendaDetallistaC addendaExtras) {
        this.addendaExtras = addendaExtras;
    }

    public AddendaReceptor getAddendaRecepDomicilio() {
        return addendaRecepDomicilio;
    }

    public void setAddendaRecepDomicilio(AddendaReceptor addendaRecepDomicilio) {
        this.addendaRecepDomicilio = addendaRecepDomicilio;
    }

    public AddendaSanofi getAddendaRecepEntrega() {
        return addendaRecepEntrega;
    }

    public void setAddendaRecepEntrega(AddendaSanofi addendaRecepEntrega) {
        this.addendaRecepEntrega = addendaRecepEntrega;
    }

    public ArrayList<Pagos> getListPagos() {
        return listPagos;
    }

    public void setListPagos(ArrayList<Pagos> listPagos) {
        this.listPagos = listPagos;
    }

    public ArrayList<LeyendaFiscal> getListLeyenda() {
        return listLeyenda;
    }

    public void setListLeyenda(ArrayList<LeyendaFiscal> listLeyenda) {
        this.listLeyenda = listLeyenda;
    }
    public ArrayList<MercanciaComercion> getMercComercioExt() {
        return mercComercioExt;
    }

    public void setMercComercioExt(ArrayList<MercanciaComercion> mercComercioExt) {
        this.mercComercioExt = mercComercioExt;
    }

    public ArrayList<DescripcionesEspMercanciaComerExt> getDescEspMerc() {
        return descEspMerc;
    }

    public void setDescEspMerc(ArrayList<DescripcionesEspMercanciaComerExt> descEspMerc) {
        this.descEspMerc = descEspMerc;
    }

    public ComercioExterior getComercioExt() {
        return comercioExt;
    }

    public void setComercioExt(ComercioExterior comercioExt) {
        this.comercioExt = comercioExt;
    }

    public EmisorComercioExt getEmisorComExt() {
        return emisorComExt;
    }

    public void setEmisorComExt(EmisorComercioExt emisorComExt) {
        this.emisorComExt = emisorComExt;
    }

    public ReceptorComercioExt getReceptorComExt() {
        return receptorComExt;
    }

    public void setReceptorComExt(ReceptorComercioExt receptorComExt) {
        this.receptorComExt = receptorComExt;
    }
	public String getCertificado() {
		return certificado;
	}
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	
	public String getFecha_pdf() {
		return fecha_pdf;
	}
	public void setFecha_pdf(String fecha_pdf) {
		this.fecha_pdf = fecha_pdf;
	}
	
	public String getFechaEmisPdf() {
		return fechaEmisPdf;
	}
	public void setFechaEmisPdf(String fechaEmisPdf) {
		this.fechaEmisPdf = fechaEmisPdf;
	}
	public String getRfcPac() {
		return rfcPac;
	}
	public void setRfcPac(String rfcPac) {
		this.rfcPac = rfcPac;
	}
	public String getSelloQr() {
		return selloQr;
	}
	public void setSelloQr(String selloQr) {
		this.selloQr = selloQr;
	}
	public String getPrevisualizar() {
		return previsualizar;
	}
	public void setPrevisualizar(String previsualizar) {
		this.previsualizar = previsualizar;
	}
	public String getCfdiRelacionado() {
		return cfdiRelacionado;
	}
	public void setCfdiRelacionado(String cfdiRelacionado) {
		this.cfdiRelacionado = cfdiRelacionado;
	}
	public ArrayList<Relacionados> getListRelacionados() {
		return listRelacionados;
	}
	public void setListRelacionados(ArrayList<Relacionados> listRelacionados) {
		this.listRelacionados = listRelacionados;
	}
	public String getCantidadLetra() {
		return CantidadLetra;
	}
	public void setCantidadLetra(String cantidadLetra) {
		CantidadLetra = cantidadLetra;
	}
	public String getObservacionesUno() {
		return observacionesUno;
	}
	public void setObservacionesUno(String observacionesUno) {
		this.observacionesUno = observacionesUno;
	}
	public String getObservacionesDos() {
		return observacionesDos;
	}
	public void setObservacionesDos(String observacionesDos) {
		this.observacionesDos = observacionesDos;
	}
	public String getObservacionesTres() {
		return observacionesTres;
	}
	public void setObservacionesTres(String observacionesTres) {
		this.observacionesTres = observacionesTres;
	}
	public String getObservacionesCuatro() {
		return observacionesCuatro;
	}
	public void setObservacionesCuatro(String observacionesCuatro) {
		this.observacionesCuatro = observacionesCuatro;
	}
	public String getObservacionesCinco() {
		return observacionesCinco;
	}
	public void setObservacionesCinco(String observacionesCinco) {
		this.observacionesCinco = observacionesCinco;
	}
	public String getImpuestoRetenido() {
		return impuestoRetenido;
	}
	public void setImpuestoRetenido(String impuestoRetenido) {
		this.impuestoRetenido = impuestoRetenido;
	}
	
	public String getAddenda() {
		return addenda;
	}
	public void setAddenda(String addenda) {
		this.addenda = addenda;
	}
	public String getFormatoPDF() {
		return FormatoPDF;
	}
	public void setFormatoPDF(String formatoPDF) {
		this.FormatoPDF = formatoPDF;
	}
	public List<TrasladoDto> getImpuestosTrasladados() {
		return impuestosTrasladados;
	}
	public void setImpuestosTrasladados(List<TrasladoDto> impuestosTrasladados) {
		this.impuestosTrasladados = impuestosTrasladados;
	}
	public List<RetencionDto> getImpuestosRetenidos() {
		return impuestosRetenidos;
	}
	public void setImpuestosRetenidos(List<RetencionDto> impuestosRetenidos) {
		this.impuestosRetenidos = impuestosRetenidos;
	}
	public String getNosap() {
		return nosap;
	}
	public void setNosap(String nosap) {
		this.nosap = nosap;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCadenaOriginal() {
		return cadenaOriginal;
	}
	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDetallePDF() {
		return detallePDF;
	}
	public void setDetallePDF(String detallePDF) {
		this.detallePDF = detallePDF;
	}
	public String getFolioFiscalOrig() {
		return folioFiscalOrig;
	}
	public void setFolioFiscalOrig(String folioFiscalOrig) {
		this.folioFiscalOrig = folioFiscalOrig;
	}
	public String getMontoFolioFiscalOrig() {
		return montoFolioFiscalOrig;
	}
	public void setMontoFolioFiscalOrig(String montoFolioFiscalOrig) {
		this.montoFolioFiscalOrig = montoFolioFiscalOrig;
	}
	public String getFechaFolioFiscalOrig() {
		return fechaFolioFiscalOrig;
	}
	public void setFechaFolioFiscalOrig(String fechaFolioFiscalOrig) {
		this.fechaFolioFiscalOrig = fechaFolioFiscalOrig;
	}
	public String getSerieFolioFiscalOrig() {
		return serieFolioFiscalOrig;
	}
	public void setSerieFolioFiscalOrig(String serieFolioFiscalOrig) {
		this.serieFolioFiscalOrig = serieFolioFiscalOrig;
	}
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public String getCondicionesPago() {
		return condicionesPago;
	}
	public void setCondicionesPago(String condicionesPago) {
		this.condicionesPago = condicionesPago;
	}
	public String getSelloEmisor() {
		return selloEmisor;
	}
	public void setSelloEmisor(String selloEmisor) {
		this.selloEmisor = selloEmisor;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getFechaTimbrado() {
		return FechaTimbrado;
	}
	public void setFechaTimbrado(String fechaTimbrado) {
		FechaTimbrado = fechaTimbrado;
	}
	public String getNoCertificadoSAT() {
		return noCertificadoSAT;
	}
	public void setNoCertificadoSAT(String noCertificadoSAT) {
		this.noCertificadoSAT = noCertificadoSAT;
	}
	public String getSelloSAT() {
		return selloSAT;
	}
	public void setSelloSAT(String selloSAT) {
		this.selloSAT = selloSAT;
	}
	public String getSelloCFD() {
		return selloCFD;
	}
	public void setSelloCFD(String selloCFD) {
		this.selloCFD = selloCFD;
	}
	public String getVersionTFD() {
		return versionTFD;
	}
	public void setVersionTFD(String versionTFD) {
		this.versionTFD = versionTFD;
	}
	public String getNoCertificado() {
		return noCertificado;
	}
	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}
	public ReceptorDto getReceptorDto() {
		return receptorDto;
	}
	public void setReceptorDto(ReceptorDto receptorDto) {
		this.receptorDto = receptorDto;
	}
	public TrasladoDto getTrasladoDto() {
		return trasladoDto;
	}
	public void setTrasladoDto(TrasladoDto trasladoDto) {
		this.trasladoDto = trasladoDto;
	}
	public RetencionDto getRetencionDto() {
		return retencionDto;
	}
	public void setRetencionDto(RetencionDto retencionDto) {
		this.retencionDto = retencionDto;
	}
	public ExpedidoEnDto getExpedidoEnDto() {
		return expedidoEnDto;
	}
	public void setExpedidoEnDto(ExpedidoEnDto expedidoEnDto) {
		this.expedidoEnDto = expedidoEnDto;
	}
	public String getOrden_compra() {
		return orden_compra;
	}
	public void setOrden_compra(String orden_compra) {
		this.orden_compra = orden_compra;
	}
	public String getOrden_venta() {
		return orden_venta;
	}
	public void setOrden_venta(String orden_venta) {
		this.orden_venta = orden_venta;
	}
	public String getNumCta() {
		return numCta;
	}
	public void setNumCta(String numCta) {
		this.numCta = numCta;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public String getFormaDePago() {
		return FormaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		FormaDePago = formaDePago;
	}
	public BigDecimal getSubTotal() {
		return SubTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		SubTotal = subTotal;
	}
	public BigDecimal getTotal() {
		return Total;
	}
	public void setTotal(BigDecimal total) {
		Total = total;
	}
	public String getTipoDeComprobante() {
		return TipoDeComprobante;
	}
	public void setTipoDeComprobante(String tipoDeComprobante) {
		TipoDeComprobante = tipoDeComprobante;
	}
	public String getMetodoDePago() {
		return MetodoDePago;
	}
	public void setMetodoDePago(String metodoDePago) {
		MetodoDePago = metodoDePago;
	}
	public String getLugarExpedicion() {
		return LugarExpedicion;
	}
	public void setLugarExpedicion(String lugarExpedicion) {
		LugarExpedicion = lugarExpedicion;
	}
	public String getMoneda() {
		return Moneda;
	}
	public void setMoneda(String moneda) {
		Moneda = moneda;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public EmisorDto getEmisor() {
		return emisor;
	}
	public void setEmisor(EmisorDto emisor) {
		this.emisor = emisor;
	}
	
	public List<DetalleComprobanteXMLParseDto> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DetalleComprobanteXMLParseDto> detalle) {
		this.detalle = detalle;
	}
	public String getPosicion() {
		return Posicion;
	}
	public void setPosicion(String posicion) {
		Posicion = posicion;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}	
	public String getCiudad() {
		return Ciudad;
	}
	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}
	
	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	
	public String getPais() {
		return Pais;
	}
	public void setPais(String pais) {
		Pais = pais;
	}
	
	public String getRfcCliente() {
		return RfcCliente;
	}
	public void setRfcCliente(String rfcCliente) {
		RfcCliente = rfcCliente;
	}
	public String getRfcOriginal() {
		return RfcOriginal;
	}
	public void setRfcOriginal(String rfcOriginal) {
		RfcOriginal = rfcOriginal;
	}
	public String getOrdenCompra() {
		return OrdenCompra;
	}
	public void setOrdenCompra(String ordenCompra) {
		OrdenCompra = ordenCompra;
	}
	public String getOrigen() {
		return Origen;
	}
	public void setOrigen(String origen) {
		Origen = origen;
	}
	public String getDestino() {
		return Destino;
	}
	public void setDestino(String destino) {
		Destino = destino;
	}
	public String getAduana() {
		return Aduana;
	}
	public void setAduana(String aduana) {
		Aduana = aduana;
	}
	public String getAgente() {
		return Agente;
	}
	public void setAgente(String agente) {
		Agente = agente;
	}
	public String getTotalParcial() {
		return TotalParcial;
	}
	public void setTotalParcial(String totalParcial) {
		TotalParcial = totalParcial;
	}
	public String getIVA() {
		return IVA;
	}
	public void setIVA(String iVA) {
		IVA = iVA;
	}
	public String getRetenciones() {
		return Retenciones;
	}
	public void setRetenciones(String retenciones) {
		Retenciones = retenciones;
	}
	public String getTotalFactura() {
		return TotalFactura;
	}
	public void setTotalFactura(String totalFactura) {
		TotalFactura = totalFactura;
	}
	public String getAnticipos() {
		return Anticipos;
	}
	public void setAnticipos(String anticipos) {
		Anticipos = anticipos;
	}
	public String getTotalEnLetras() {
		return TotalEnLetras;
	}
	public void setTotalEnLetras(String totalEnLetras) {
		TotalEnLetras = totalEnLetras;
	}
	public String getFechaEm() {
		return fechaEm;
	}
	public void setFechaEm(String fechaEm) {
		this.fechaEm = fechaEm;
	}
	public String getFechaVto() {
		return fechaVto;
	}
	public void setFechaVto(String fechaVto) {
		this.fechaVto = fechaVto;
	}
	public String getCorreodos() {
		return correodos;
	}
	public void setCorreodos(String correodos) {
		this.correodos = correodos;
	}
	public String getCorreotres() {
		return correotres;
	}
	public void setCorreotres(String correotres) {
		this.correotres = correotres;
	}
	public String getTipoPdf() {
		return tipoPdf;
	}
	public void setTipoPdf(String tipoPdf) {
		this.tipoPdf = tipoPdf;
	}
	public String getLeyenda() {
		return Leyenda;
	}
	public void setLeyenda(String leyenda) {
		Leyenda = leyenda;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getEtd() {
		return etd;
	}
	public void setEtd(String etd) {
		this.etd = etd;
	}
	public String getTelefonoCli() {
		return TelefonoCli;
	}
	public void setTelefonoCli(String telefonoCli) {
		TelefonoCli = telefonoCli;
	}
	public String getFechaFormat() {
		return fechaFormat;
	}
	public void setFechaFormat(String fechaFormat) {
		this.fechaFormat = fechaFormat;
	}
	public String getNomTipoDocumento() {
		return nomTipoDocumento;
	}
	public void setNomTipoDocumento(String nomTipoDocumento) {
		this.nomTipoDocumento = nomTipoDocumento;
	}
	public String getNomTipoDocEncabezado() {
		return nomTipoDocEncabezado;
	}
	public void setNomTipoDocEncabezado(String nomTipoDocEncabezado) {
		this.nomTipoDocEncabezado = nomTipoDocEncabezado;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getTotalImporte() {
		return totalImporte;
	}
	public void setTotalImporte(String totalImporte) {
		this.totalImporte = totalImporte;
	}
	public String getTotImporte() {
		return totImporte;
	}
	public void setTotImporte(String totImporte) {
		this.totImporte = totImporte;
	}
	
	
}
