package edifactwsradiopolis.dto;

import java.util.ArrayList;
import java.util.Properties;

import org.w3c.dom.Document;
import mx.gob.sat.cfd4_0.Comprobante;

public class BeanGeneral {

    private String importeLetras;
    private String tipoComprobante;
    private BeanReceptor receptor;
    private Document CFDI;
    private BeanEmisor emisor;
    private AddendaMabeCabecero mabeCabecero;
    private ArrayList<AddendaMabeDetalles> mabeDetalles;
    private AddendaDetallistaC detallistaCabecero;
    private AddendaSanofi addendaSanofi;
    private ArrayList<AddendaDetallistaItem> detallistaItems;
    private Comprobante comprobante;
    private String cadenaOriginal;
    private String cadenaOriginalTFD;
    private String MonedaP;
    private String FormaDePagoP;
    private String fechaTimbrado;
    private String xml;
    private String UUID;
    private Properties parametros;
    private Boolean error;
    private String fileName;
    private String tipoBean;
    private String errorDescripcion;
    private String codigoDescripcion;
    private String log;
    private String FECHA_C;
    private String RFCEMISOR;
    private String carpetaPDF;
    private String carpetaXML;
    private String codigoResultado;
    private String xmlRecibido;
    private String mail;
    private String telefono;
    private String noIdProd;
    private String contrato;
    private String descUnidad;
    private String servicios;
    private String conceptos;
    private String equipos;
    private String otros;
    private String tipoProd;
    private String seguro;
    private String descuentos;
    private String Account_number;
    private String nombreProd;
    private String MANDT;
    private String DOCNUM;
    private String DOCREL;
    private String STATUS;
    private String DIRECT;
    private String OUTMOD;
    private String IDOCTYP;
    private String MESTYP;
    private String SNDPOR;
    private String SNDPRT;
    private String SNDPRN;
    private String RCVPOR;
    private String RCVPRT;
    private String RCVPRN;
    private String CREDAT;
    private String CRETIM;
    private String SERIAL;
    private String DOCNUM2;
    private String REFTYPE;
    private String FISCAL_YEAR;
    private String CMPNY_CODE;
    private String observaciones;
    private String Total_Lit;
    private String serie;
    private String folio;
    private String comentarios;
    private String referencias;
    private String plazoPagos;
    private String noOrden;
    
    public String getNoOrden() {
        return noOrden;
    }

    public void setNoOrden(String noOrden) {
        this.noOrden = noOrden;
    }
    public String getDOCNUM() {
        return DOCNUM;
    }

     public void setDOCNUM(String DOCNUM) {
        this.DOCNUM = DOCNUM;
    }
     
     public String getMANDT() {
        return MANDT;
    }

     public void setMANDT(String MANDT) {
        this.MANDT = MANDT;
    }
     
    public String getAccount_number() {
        return Account_number;
    }

    public AddendaSanofi getAddendaSanofi() {
        return addendaSanofi;
    }

    public void setAddendaSanofi(AddendaSanofi addendaSanofi) {
        this.addendaSanofi = addendaSanofi;
    }
    
    /**
     * @param Account_number the importeLetras to set
     */
    public void setAccount_number(String Account_number) {
        this.Account_number = Account_number;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }
    

//    private ArrayList<BeanConectividad> conectividad;

    /**
     * @return the importeLetras
     */
    public String getImporteLetras() {
        return importeLetras;
    }

    /**
     * @param importeLetras the importeLetras to set
     */
    public void setImporteLetras(String importeLetras) {
        this.importeLetras = importeLetras;
    }

    /**
     * @return the tipoComprobante
     */
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * @param tipoComprobante the tipoComprobante to set
     */
    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    /**
     * @return the receptor
     */
    public BeanReceptor getReceptor() {
        return receptor;
    }

    /**
     * @param receptor the receptor to set
     */
    public void setReceptor(BeanReceptor receptor) {
        this.receptor = receptor;
    }

    /**
     * @return the CFDI
     */
    public Document getCFDI() {
        return CFDI;
    }

    /**
     * @param CFDI the CFDI to set
     */
    public void setCFDI(Document CFDI) {
        this.CFDI = CFDI;
    }

    /**
     * @return the emisor
     */
    public BeanEmisor getEmisor() {
        return emisor;
    }

    /**
     * @param emisor the emisor to set
     */
    public void setEmisor(BeanEmisor emisor) {
        this.emisor = emisor;
    }

    /**
     * @return the comprobante
     */
    public mx.gob.sat.cfd4_0.Comprobante getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(mx.gob.sat.cfd4_0.Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * @return the cadenaOriginal
     */
    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    /**
     * @param cadenaOriginal the cadenaOriginal to set
     */
    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    /**
     * @return the fechaTimbrado
     */
    public String getFechaTimbrado() {
        return fechaTimbrado;
    }

    /**
     * @param fechaTimbrado the fechaTimbrado to set
     */
    public void setFechaTimbrado(String fechaTimbrado) {
        this.fechaTimbrado = fechaTimbrado;
    }

    /**
     * @return the xml
     */
    public String getXml() {
        return xml;
    }

    /**
     * @param xml the xml to set
     */
    public void setXml(String xml) {
        this.xml = xml;
    }

    /**
     * @return the UUID
     */
    public String getUUID() {
        return UUID;
    }

    /**
     * @param UUID the UUID to set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * @return the parametros
     */
    public Properties getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(Properties parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the error
     */
    public Boolean getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(Boolean error) {
        this.error = error;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the tipoBean
     */
    public String getTipoBean() {
        return tipoBean;
    }

    /**
     * @param tipoBean the tipoBean to set
     */
    public void setTipoBean(String tipoBean) {
        this.tipoBean = tipoBean;
    }

    /**
     * @return the errorDescripcion
     */
    public String getErrorDescripcion() {
        return errorDescripcion;
    }

    /**
     * @param errorDescripcion the errorDescripcion to set
     */
    public void setErrorDescripcion(String errorDescripcion) {
        this.errorDescripcion = errorDescripcion;
    }


    /**
     * @return the xmlRecibido
     */
    public String getXmlRecibido() {
        return xmlRecibido;
    }

    /**
     * @param xmlRecibido the xmlRecibido to set
     */
    public void setXmlRecibido(String xmlRecibido) {
        this.xmlRecibido = xmlRecibido;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the diasSuspension
     */

    /**
     * @return the servicios
     */
    public String getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(String servicios) {
        this.servicios = servicios;
    }
      public String getConceptos() {
        return conceptos;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setConceptos(String conceptos) {
        this.conceptos = conceptos;
    }

    /**
     * @return the equipos
     */
    public String getEquipos() {
        return equipos;
    }

    /**
     * @param equipos the equipos to set
     */
    public void setEquipos(String equipos) {
        this.equipos = equipos;
    }

    /**
     * @return the otros
     */
    public String getOtros() {
        return otros;
    }

    /**
     * @param otros the otros to set
     */
    public void setOtros(String otros) {
        this.otros = otros;
    }

    /**
     * @return the flete
     */

    /**
     * @return the seguro
     */
    public String getSeguro() {
        return seguro;
    }

    /**
     * @param seguro the seguro to set
     */
    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    /**
     * @return the contrato
     */
    public String getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    /**
     * @return the descuentos
     */
    public String getDescuentos() {
        return descuentos;
    }

    /**
     * @param descuentos the descuentos to set
     */
    public void setDescuentos(String descuentos) {
        this.descuentos = descuentos;
    }

    /**
     * @return the comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the DOCREL
     */
    public String getDOCREL() {
        return DOCREL;
    }

    /**
     * @param DOCREL the DOCREL to set
     */
    public void setDOCREL(String DOCREL) {
        this.DOCREL = DOCREL;
    }

    /**
     * @return the STATUS
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     * @param STATUS the STATUS to set
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    /**
     * @return the DIRECT
     */
    public String getDIRECT() {
        return DIRECT;
    }

    /**
     * @param DIRECT the DIRECT to set
     */
    public void setDIRECT(String DIRECT) {
        this.DIRECT = DIRECT;
    }

    /**
     * @return the OUTMOD
     */
    public String getOUTMOD() {
        return OUTMOD;
    }

    /**
     * @param OUTMOD the OUTMOD to set
     */
    public void setOUTMOD(String OUTMOD) {
        this.OUTMOD = OUTMOD;
    }

    /**
     * @return the IDOCTYP
     */
    public String getIDOCTYP() {
        return IDOCTYP;
    }

    /**
     * @param IDOCTYP the IDOCTYP to set
     */
    public void setIDOCTYP(String IDOCTYP) {
        this.IDOCTYP = IDOCTYP;
    }

    /**
     * @return the MESTYP
     */
    public String getMESTYP() {
        return MESTYP;
    }

    /**
     * @param MESTYP the MESTYP to set
     */
    public void setMESTYP(String MESTYP) {
        this.MESTYP = MESTYP;
    }

    /**
     * @return the SNDPOR
     */
    public String getSNDPOR() {
        return SNDPOR;
    }

    /**
     * @param SNDPOR the SNDPOR to set
     */
    public void setSNDPOR(String SNDPOR) {
        this.SNDPOR = SNDPOR;
    }

    /**
     * @return the SNDPRT
     */
    public String getSNDPRT() {
        return SNDPRT;
    }

    /**
     * @param SNDPRT the SNDPRT to set
     */
    public void setSNDPRT(String SNDPRT) {
        this.SNDPRT = SNDPRT;
    }

    /**
     * @return the SNDPRN
     */
    public String getSNDPRN() {
        return SNDPRN;
    }

    /**
     * @param SNDPRN the SNDPRN to set
     */
    public void setSNDPRN(String SNDPRN) {
        this.SNDPRN = SNDPRN;
    }

    /**
     * @return the RCVPOR
     */
    public String getRCVPOR() {
        return RCVPOR;
    }

    /**
     * @param RCVPOR the RCVPOR to set
     */
    public void setRCVPOR(String RCVPOR) {
        this.RCVPOR = RCVPOR;
    }

    /**
     * @return the RCVPRT
     */
    public String getRCVPRT() {
        return RCVPRT;
    }

    /**
     * @param RCVPRT the RCVPRT to set
     */
    public void setRCVPRT(String RCVPRT) {
        this.RCVPRT = RCVPRT;
    }

    /**
     * @return the RCVPRN
     */
    public String getRCVPRN() {
        return RCVPRN;
    }

    /**
     * @param RCVPRN the RCVPRN to set
     */
    public void setRCVPRN(String RCVPRN) {
        this.RCVPRN = RCVPRN;
    }

    /**
     * @return the CREDAT
     */
    public String getCREDAT() {
        return CREDAT;
    }

    /**
     * @param CREDAT the CREDAT to set
     */
    public void setCREDAT(String CREDAT) {
        this.CREDAT = CREDAT;
    }

    /**
     * @return the CRETIM
     */
    public String getCRETIM() {
        return CRETIM;
    }

    /**
     * @param CRETIM the CRETIM to set
     */
    public void setCRETIM(String CRETIM) {
        this.CRETIM = CRETIM;
    }

    /**
     * @return the SERIAL
     */
    public String getSERIAL() {
        return SERIAL;
    }

    /**
     * @param SERIAL the SERIAL to set
     */
    public void setSERIAL(String SERIAL) {
        this.SERIAL = SERIAL;
    }

    /**
     * @return the DOCNUM2
     */
    public String getDOCNUM2() {
        return DOCNUM2;
    }

    /**
     * @param DOCNUM2 the DOCNUM2 to set
     */
    public void setDOCNUM2(String DOCNUM2) {
        this.DOCNUM2 = DOCNUM2;
    }

    /**
     * @return the REFTYPE
     */
    public String getREFTYPE() {
        return REFTYPE;
    }

    /**
     * @param REFTYPE the REFTYPE to set
     */
    public void setREFTYPE(String REFTYPE) {
        this.REFTYPE = REFTYPE;
    }

    /**
     * @return the FISCAL_YEAR
     */
    public String getFISCAL_YEAR() {
        return FISCAL_YEAR;
    }

    /**
     * @param FISCAL_YEAR the FISCAL_YEAR to set
     */
    public void setFISCAL_YEAR(String FISCAL_YEAR) {
        this.FISCAL_YEAR = FISCAL_YEAR;
    }

    /**
     * @return the CMPNY_CODE
     */
    public String getCMPNY_CODE() {
        return CMPNY_CODE;
    }

    /**
     * @param CMPNY_CODE the CMPNY_CODE to set
     */
    public void setCMPNY_CODE(String CMPNY_CODE) {
        this.CMPNY_CODE = CMPNY_CODE;
    }

    /**
     * @return the codigoDescripcion
     */
    public String getCodigoDescripcion() {
        return codigoDescripcion;
    }

    /**
     * @param codigoDescripcion the codigoDescripcion to set
     */
    public void setCodigoDescripcion(String codigoDescripcion) {
        this.codigoDescripcion = codigoDescripcion;
    }

    /**
     * @return the codigoResultado
     */
    public String getCodigoResultado() {
        return codigoResultado;
    }

    /**
     * @param codigoResultado the codigoResultado to set
     */
    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(String log) {
        this.log = log;
    }

    public void setRespuestaTimbrado(BeanRespuestaTimbrado respuestaTimbrado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the FECHA_C
     */
    public String getFECHA_C() {
        return FECHA_C;
    }

    /**
     * @param FECHA_C the FECHA_C to set
     */
    public void setFECHA_C(String FECHA_C) {
        this.FECHA_C = FECHA_C;
    }

    /**
     * @return the Total_Lit
     */
    public String getTotal_Lit() {
        return Total_Lit;
    }

    /**
     * @param Total_Lit the Total_Lit to set
     */
    public void setTotal_Lit(String Total_Lit) {
        this.Total_Lit = Total_Lit;
    }

    /**
     * @return the RFCEMISOR
     */
    public String getRFCEMISOR() {
        return RFCEMISOR;
    }

    /**
     * @param RFCEMISOR the RFCEMISOR to set
     */
    public void setRFCEMISOR(String RFCEMISOR) {
        this.RFCEMISOR = RFCEMISOR;
    }

    /**
     * @return the carpetaPDF
     */
    public String getCarpetaPDF() {
        return carpetaPDF;
    }

    /**
     * @param carpetaPDF the carpetaPDF to set
     */
    public void setCarpetaPDF(String carpetaPDF) {
        this.carpetaPDF = carpetaPDF;
    }

    /**
     * @return the carpetaXML
     */
    public String getCarpetaXML() {
        return carpetaXML;
    }

    /**
     * @param carpetaXML the carpetaXML to set
     */
    public void setCarpetaXML(String carpetaXML) {
        this.carpetaXML = carpetaXML;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the MonedaP
     */
    public String getMonedaP() {
        return MonedaP;
    }

    /**
     * @param MonedaP the MonedaP to set
     */
    public void setMonedaP(String MonedaP) {
        this.MonedaP = MonedaP;
    }

    /**
     * @return the FormaDePagoP
     */
    public String getFormaDePagoP() {
        return FormaDePagoP;
    }

    /**
     * @param FormaDePagoP the FormaDePagoP to set
     */
    public void setFormaDePagoP(String FormaDePagoP) {
        this.FormaDePagoP = FormaDePagoP;
    }

    /**
     * @return the referencias
     */
    public String getReferencias() {
        return referencias;
    }

    /**
     * @param referencias the referencias to set
     */
    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    /**
     * @return the cadenaOriginalTFD
     */
    public String getCadenaOriginalTFD() {
        return cadenaOriginalTFD;
    }

    /**
     * @param cadenaOriginalTFD the cadenaOriginalTFD to set
     */
    public void setCadenaOriginalTFD(String cadenaOriginalTFD) {
        this.cadenaOriginalTFD = cadenaOriginalTFD;
    }

    public String getNoIdProd() {
        return noIdProd;
    }

    public void setNoIdProd(String noIdProd) {
        this.noIdProd = noIdProd;
    }

    public String getDescUnidad() {
        return descUnidad;
    }

    public void setDescUnidad(String descUnidad) {
        this.descUnidad = descUnidad;
    }

    public String getTipoProd() {
        return tipoProd;
    }

    public void setTipoProd(String tipoProd) {
        this.tipoProd = tipoProd;
    }

    public String getPlazoPagos() {
        return plazoPagos;
    }

    public void setPlazoPagos(String plazoPagos) {
        this.plazoPagos = plazoPagos;
    }

    public AddendaMabeCabecero getMabeCabecero() {
        return mabeCabecero;
    }

    public void setMabeCabecero(AddendaMabeCabecero mabeCabecero) {
        this.mabeCabecero = mabeCabecero;
    }

    public ArrayList<AddendaMabeDetalles> getMabeDetalles() {
        return mabeDetalles;
    }

    public void setMabeDetalles(ArrayList<AddendaMabeDetalles> mabeDetalles) {
        this.mabeDetalles = mabeDetalles;
    }

    public AddendaDetallistaC getDetallistaCabecero() {
        return detallistaCabecero;
    }

    public void setDetallistaCabecero(AddendaDetallistaC detallistaCabecero) {
        this.detallistaCabecero = detallistaCabecero;
    }

    public ArrayList<AddendaDetallistaItem> getDetallistaItems() {
        return detallistaItems;
    }

    public void setDetallistaItems(ArrayList<AddendaDetallistaItem> detallistaItems) {
        this.detallistaItems = detallistaItems;
    }
    

    

    
}
