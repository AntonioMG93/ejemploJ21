/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edifactwsradiopolis.dao;

import consulta_pkg.ConsultaLocator;
import consulta_pkg.ConsultaPortType;
import edifactwsradiopolis.cancela.EnviaAcuseCancelacionBindingStub;
import edifactwsradiopolis.cancela.EnviaAcuseCancelacionLocator;
import edifactwsradiopolis.cancela.EnviaAcuseCancelacionPortType;
import edifactwsradiopolis.dto.AddendaCPagos;
import edifactwsradiopolis.dto.AddendaConcepto;
import edifactwsradiopolis.dto.ResponseComprobanteAdminisrtaDatosCDto;
import edifactwsradiopolis.dto.ReturnCancelaCFDIDto;
import edifactwsradiopolis.dto.AddendaDetallistaC;
import edifactwsradiopolis.dto.AddendaDetallistaItem;
import edifactwsradiopolis.dto.AddendaMabeCabecero;
import edifactwsradiopolis.dto.AddendaMabeDetalles;
import edifactwsradiopolis.dto.AddendaReceptor;
import edifactwsradiopolis.dto.MercanciaComercion;
import edifactwsradiopolis.dto.AddendaSanofi;
import edifactwsradiopolis.dto.BeanEmisor;
import edifactwsradiopolis.dto.BeanGeneral;
import edifactwsradiopolis.dto.BeanReceptor;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import edifactwsradiopolis.dto.ComercioExterior;
import edifactwsradiopolis.dto.ComprobanteParseDto;
import edifactwsradiopolis.dto.CorreoVo;
import edifactwsradiopolis.dto.DescripcionesEspMercanciaComerExt;
import edifactwsradiopolis.dto.DetalleComprobanteXMLParseDto;
import edifactwsradiopolis.dto.DoctoRelacionado;
import edifactwsradiopolis.dto.EmisorComercioExt;
import edifactwsradiopolis.dto.EmisorDto;
import edifactwsradiopolis.dto.LeyendaFiscal;
import edifactwsradiopolis.dto.Pagos;
import edifactwsradiopolis.dto.ReceptorComercioExt;
import edifactwsradiopolis.dto.ReceptorDto;
import edifactwsradiopolis.dto.Relacionados;
import edifactwsradiopolis.dto.RespaldoVo;
import edifactwsradiopolis.dto.ResponseComprobanteAdministraDatosDto;
import edifactwsradiopolis.dto.ResponseXMLParseDto;
import edifactwsradiopolis.dto.RetencionConcepto;
import edifactwsradiopolis.dto.RetencionDto;
import edifactwsradiopolis.dto.TrasladoConcepto;
import edifactwsradiopolis.dto.TrasladoDto;
import edifactwsradiopolis.timbra.RespuestaTimbrado;
import edifactwsradiopolis.timbra.TimbrarCFDIBindingStub;
import edifactwsradiopolis.timbra.TimbrarCFDILocator;
import edifactwsradiopolis.timbra.TimbrarCFDIPortType;
import edifactwsradiopolis.utils.Base64Coder;
import edifactwsradiopolis.utils.DefaultTrustManager;
import edifactwsradiopolis.utils.PDFMaker_Timbrado;
import edifactwsradiopolis.utils.SendMailPrepare;
import edifactwsradiopolis.utils.Utils;
import edifactwsradiopolis.utils.XMLParseUtils;
import edifactwsradiopolis.utils.CFDv40;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
//import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import jakarta.xml.bind.JAXBElement;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.rpc.ServiceException;
//import javax.xml.rpc.ServiceException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

//import mx.bigdata.sat.security.KeyLoaderEnumeration;

import mx.gob.sat.cfd4_0.Comprobante;
import mx.gob.sat.cfd4_0.Comprobante.Complemento;
import mx.gob.sat.cfd4_0.Comprobante.CfdiRelacionados;
import mx.gob.sat.cfd4_0.Comprobante.CfdiRelacionados.CfdiRelacionado;
import mx.gob.sat.cfd4_0.Comprobante.Conceptos;
import mx.gob.sat.cfd4_0.Comprobante.Conceptos.Concepto;
import mx.gob.sat.cfd4_0.Comprobante.Conceptos.Concepto.Impuestos;
import mx.gob.sat.cfd4_0.Comprobante.Conceptos.Concepto.Impuestos.Retenciones;
import mx.gob.sat.cfd4_0.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion;
import mx.gob.sat.cfd4_0.Comprobante.Conceptos.Concepto.Impuestos.Traslados;
import mx.gob.sat.cfd4_0.Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado;
import mx.gob.sat.cfd4_0.Comprobante.Emisor;
import mx.gob.sat.cfd4_0.Comprobante.Receptor;

//import mx.bigdata.sat.security.factory.KeyLoaderFactory;
import mx.gob.sat.cfd4_0.ObjectFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.ssl.PKCS8Key;



/**
 *
 * @author zamud
 */
public class ComprobanteArchivosDaoImpl {

    private ComprobanteParseDto comprobanteParseDto = null;

    private ResponseXMLParseDto responseXMLParseDto = null;
    private EmisorDto emisorDto = null;
    private ReceptorDto receptorDto = null;
    private DescripcionesEspMercanciaComerExt descM = null;
    private RetencionDto retencionDto = null;
    private TrasladoDto trasladoDto = null;
    private LeyendaFiscal l = null;
    private ComercioExterior exterior = null;
    private QueryAdministrador query = null;
    private String uuidC = "";
    private String fechaC = "";
    private String rfcEmisorC = "";
    private String rfcReceptorC = "";
    private String totalC = "";
    private String motivo = "";
    private String uuidSust = "";
    private BeanGeneral general;
    Boolean error = false;
    Boolean errorLeer = false;
    File file;
    private Properties parametros = new Properties();
    BeanEmisor bEmisor = new BeanEmisor();
    BeanReceptor bReceptor = new BeanReceptor();

    public ComprobanteArchivosDaoImpl() {

    }

    public Document generaComprobanteCancelacion(String rfc, String idoc) throws Exception {

        Document doc = null;
        String opera = "";
        String complemento = "";
        //String fecha ="2015-05-04T17:15:41.410";
        //String uuid="1d2421f4-bcde-4cf0-8d23-32f6f5b54729";
        String idoc_utf8 = idoc;

        byte[] certificado = FileUtils.readFileToByteArray(new File(Utils.getParamValue("path_files_key_cert") + rfc + ".cer"));

        byte[] llave = FileUtils.readFileToByteArray(new File(Utils.getParamValue("path_files_key_cert") + rfc + ".key"));

        ArrayList<RespaldoVo> respaldoVos = query.extraerPropiedad(rfc);

        if (respaldoVos.size() >= 1) {
            complemento = respaldoVos.get(0).getCfdi_tool();
            opera = Utils.quitarAlias(complemento, "XMzDdG4D03CKm2IxIWQw7g==");

            String uuids = "";
        if (!uuidSust.equals("")) {
        	uuids = "<Folio UUID=\"" +uuidC.toUpperCase()+ "\" Motivo=\""+motivo+"\" FolioSustitucion=\""+uuidSust+"\"/>";
		} else {
			uuids = "<Folio UUID=\"" +uuidC.toUpperCase()+ "\" Motivo=\""+motivo+"\"/>";
		}
		 


		String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<Cancelacion xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://cancelacfd.sat.gob.mx\" Fecha=\""
				+ fechaC + "\" RfcEmisor=\"" + rfc.replace("&", "&amp;") + "\">" + "<Folios>" + uuids + "</Folios>"
				+ "</Cancelacion>";
            
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
            Reference ref = fac.newReference("",
                    fac.newDigestMethod(DigestMethod.SHA1, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                    null, null);
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                    Collections.singletonList(ref));
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(
                    new ByteArrayInputStream(certificado));
            KeyInfoFactory kif = fac.getKeyInfoFactory();
            List x509Content = new ArrayList();
            //x509Content.add(kif.newX509IssuerSerial(cert.getIssuerDN().getName(), cert.getSerialNumber()));
            x509Content.add(kif.newX509IssuerSerial(cert.getIssuerDN().getName(), cert.getSerialNumber()));
            x509Content.add(cert);
            X509Data xd = kif.newX509Data(x509Content);
            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            InputSource source = new InputSource(new StringReader(xmlString));
            doc = dbf.newDocumentBuilder().parse(source);
            InputStream bis = new ByteArrayInputStream(llave);
            PKCS8Key key = new PKCS8Key(bis, opera.toCharArray());
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(key.getDecryptedBytes());
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(privKeySpec);
            DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
            XMLSignature signature = fac.newXMLSignature(si, ki);
            signature.sign(dsc);
//            System.out.println("Acabo signature");
        }
        return doc;
    }

    private String enviarPeticionCancelacion(Document doc) throws Exception {
        //String ambiente = this.request.getEmpresa().getPropiedades().getProperty("timbrado.ambiente");
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        String strArchivoCancelacion = new String(writer.toString().getBytes("UTF-8"));
        String respuestaCancelacion = "";
        //System.out.println("FileCancel: "+ strArchivoCancelacion);
        try {
            EnviaAcuseCancelacionPortType weatherSoap = new EnviaAcuseCancelacionLocator(Utils.getParamValue("ambiente_timbrado")).getenviaAcuseCancelacionPort();

            EnviaAcuseCancelacionBindingStub stub = (EnviaAcuseCancelacionBindingStub) weatherSoap;
//            System.out.println("Accion: Cancelar - Ambiente: " + Utils.getParamValue("ambiente_timbrado") + " \nArchivo a cancelar " + strArchivoCancelacion);
            respuestaCancelacion = weatherSoap.enviaAcuseCancelacion(strArchivoCancelacion, Utils.getParamValue("ambiente_timbrado"));
//            System.out.println("RESPUESTA: " + respuestaCancelacion);
        } catch (Exception ex) {
//            log.error("Error al consumir cancelaciones: " + ex);
            System.out.println("Error al consumir cancelaciones: " + ex);
        }
        return respuestaCancelacion;
    }

    public ResponseComprobanteAdminisrtaDatosCDto cancelaComprobanteFiscal(String idoc, String rfc) throws Exception {
        query = new QueryAdministrador();
        ResponseComprobanteAdminisrtaDatosCDto responseComprobanteAdminisrtaDatosDto = new ResponseComprobanteAdminisrtaDatosCDto();
        ReturnCancelaCFDIDto returnCancelaCFDIDto = new ReturnCancelaCFDIDto();
        String[] campos = null;
        try {
            //Extracción de información
            
        InputStream targetStream = IOUtils.toInputStream(idoc);
        BufferedReader in = new BufferedReader(new InputStreamReader(targetStream, "ISO-8859-1"));
        String linea = in.readLine();
        campos = linea.split(","); 
        
         fechaC=campos[0].trim();   
         rfcEmisorC=campos[1].trim();  
         uuidC=campos[2].trim(); 
         rfcReceptorC = campos[3].trim();
         totalC = campos[4].trim();
         motivo = campos[5].trim();
            if (campos.length > 6) {
                uuidSust = campos[6].trim();
            }
         
         
         
            
            //Valida si existe ya la cancelación en curso
//            log.info("consulta  cfdi_cancelaciones ");
            String[] buscaCancela = query.buscaCancelacion(uuidC);

            if (buscaCancela != null) {
    //            log.info("resultado busqueda cfdi_cancelaciones " + buscaCancela[4]);
                if (buscaCancela[4].equals("P")) {
                    String pdfbase64 = "";
 //                   log.info("*** Buscando estatus del documento:P " + uuidC);
                    String busqueda[] = consultaCancela(rfcEmisorC, rfcReceptorC, totalC, uuidC);
                    //validamos que nos haya regresado información.
                    if (busqueda.length > 1) {
//                        busqueda[2]="Cancelado"; // comentar en productivo 
                        //Actualiza el estatus en la base.
                        if (busqueda[2].equalsIgnoreCase("Cancelado")) {// comentado para pruebas
                            //Actualizamos los estatus en las tablas cfdis y cfdi_cancelaciones.

                            query.actualizaCancelaciones(uuidC);
             //               log.info("termina: actualizaCancelaciones");
                            query.actualizaCancelacionCfdi(uuidC);
           //                 log.info("termina: actualizaCancelacionCfdi");
                            
                            try {
                                    String nombreArchivo = "";
                                    String[] arreglo = query.buscarInfoUUID(uuidC);

                                    if (arreglo != null) {
                                        if (arreglo[0] != null && !arreglo[0].equals("")) {
                                            nombreArchivo = arreglo[0] + arreglo[1];
                                        } else {
                                            nombreArchivo = arreglo[1];
                                        }
           //                             log.info("Nombre del pdf: " + nombreArchivo);

                                        PDFMaker_Timbrado pdfMaker = new PDFMaker_Timbrado();
                                        try {
                                            pdfMaker.manipulatePdfWatermark(
                                                    Utils.getParamValue("path_base") + nombreArchivo + " .pdf",
                                                    Utils.getParamValue("path_base") + "CAN_" + nombreArchivo + " .pdf");
//                                            System.out.println("Termine de crear el pdf con la marca de agua.");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        pdfbase64=Utils.pdfBase64(Utils.getParamValue("path_base"), "CAN_" + nombreArchivo);
						
                                    }

                                } catch (Exception fe) {
                                    fe.printStackTrace();
                                }

                        }// comentado para pruebas
                        //si es no cancelable solamente retorna 3 valores
                        if (busqueda.length <= 3) {
//                            System.out.println(busqueda[0] + "," + busqueda[1] + "," + busqueda[2] + ",");
                            //responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                            responseComprobanteAdminisrtaDatosDto.setCodigo("201");
                            responseComprobanteAdminisrtaDatosDto.setDescripcion("Búsqueda de estatus exitosa");
//                            responseComprobanteAdminisrtaDatosDto.setCancelable("");
                            responseComprobanteAdminisrtaDatosDto.setCancelable(busqueda[1]);
//                            responseComprobanteAdminisrtaDatosDto.setEstado("");
                            responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[2]);
                            responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                            responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);
                        } else {
//                            System.out.println(busqueda[0] + "," + busqueda[1] + "," + busqueda[2] + "," + busqueda[3]);
                            //responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                            responseComprobanteAdminisrtaDatosDto.setCodigo("201");
                            responseComprobanteAdminisrtaDatosDto.setDescripcion("Búsqueda de estatus exitosa");
                            responseComprobanteAdminisrtaDatosDto.setCancelable(busqueda[1]);
                            responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[3]);
                            //responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[2]);
                            responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                            responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);
                        }

                    } else {

                        //responseComprobanteAdminisrtaDatosDto.setDocumentoxml(Base64Coder.encodeString(xmlCancelado));
                        responseComprobanteAdminisrtaDatosDto.setCodigo("000");
                        responseComprobanteAdminisrtaDatosDto.setDescripcion("El SAT esta presentando intermitencia, vuelva intentar más tarde");
                        responseComprobanteAdminisrtaDatosDto.setCancelable("");
                        responseComprobanteAdminisrtaDatosDto.setEstado("");
                        responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                        responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);

                    }

                } else if (buscaCancela[4].equals("C")) {
                    //responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                    String pdfbase64="";
           //         log.info("*** Buscando estatus del documento: C " + uuidC);
                    String busqueda[] = consultaCancela(rfcEmisorC, rfcReceptorC, totalC, uuidC);
                    //validamos que nos haya regresado información.

                    if (busqueda.length > 1) {
     //                   log.info("Resultado estatus C - 0 " + busqueda[0] + ",1 " + busqueda[1] + ",2 " + busqueda[2] + ",3 " + busqueda[3]);
                        //Actualiza el estatus en la base.
                        if (buscaCancela[4].equals("C")) {
                                //Actualizamos los estatus en las tablas cfdis y cfdi_cancelaciones.
     //                           log.info(busqueda[2] + " actualizando Base");
                                query.actualizaCancelaciones(uuidC);
     //                           log.info("termina: actualizaCancelaciones");
                                query.actualizaCancelacionCfdi(uuidC);
       //                         log.info("termina: actualizaCancelacionCfdi");

                            try {
                                    String nombreArchivo = "";
                                    String[] arreglo = query.buscarInfoUUID(uuidC);

                                    if (arreglo != null) {
                                        if (arreglo[0] != null && !arreglo[0].equals("")) {
                                            nombreArchivo = arreglo[0] + arreglo[1];
                                        } else {
                                            nombreArchivo = arreglo[1];
                                        }
       //                                 log.info("Nombre del pdf: " + nombreArchivo);

                                        PDFMaker_Timbrado pdfMaker = new PDFMaker_Timbrado();
                                        try {
                                            pdfMaker.manipulatePdfWatermark(
                                                    Utils.getParamValue("path_base") + nombreArchivo + " .pdf",
                                                    Utils.getParamValue("path_base") + "CAN_" + nombreArchivo + " .pdf");
//                                            System.out.println("Termine de crear el pdf con la marca de agua.");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        pdfbase64=Utils.pdfBase64(Utils.getParamValue("path_base"), "CAN_" + nombreArchivo);

                                    }

                                } catch (Exception fe) {
                                    fe.printStackTrace();
                                }
                            //responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                            responseComprobanteAdminisrtaDatosDto.setCodigo("201");
                            responseComprobanteAdminisrtaDatosDto.setDescripcion("Comprobante cancelado anteriormente");
                            responseComprobanteAdminisrtaDatosDto.setCancelable(busqueda[1]);
                            responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[3]);
//                        responseComprobanteAdminisrtaDatosDto.setCancelable("");
//                        responseComprobanteAdminisrtaDatosDto.setEstado("");
                            //responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[2]);
                            responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                            responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);
                        } else {
//                            log.info("000");
                            //responseComprobanteAdminisrtaDatosDto.setDocumentoxml(Base64Coder.encodeString(xmlCancelado));
                            responseComprobanteAdminisrtaDatosDto.setCodigo("000");
                            responseComprobanteAdminisrtaDatosDto.setDescripcion("El SAT esta presentando intermitencia, vuelva intentar más tarde");
                            responseComprobanteAdminisrtaDatosDto.setCancelable("");
                            responseComprobanteAdminisrtaDatosDto.setEstado("");
                            responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                            responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);

                        }

                        responseComprobanteAdminisrtaDatosDto.setCodigo("201");
                        responseComprobanteAdminisrtaDatosDto.setDescripcion("Comprobante cancelado anteriormente");
                        responseComprobanteAdminisrtaDatosDto.setCancelable("");
                        responseComprobanteAdminisrtaDatosDto.setEstado("Cancelado");
                        responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                        responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);

                    }
                }
            } else {
                String pdfbase64="";
     //           log.info("*** Cancelando documento: " + uuidC);
                Document documentoCancelacion = generaComprobanteCancelacion(rfc, idoc);
                if (documentoCancelacion != null) {
                    String xmlCancelado = enviarPeticionCancelacion(documentoCancelacion);
                    returnCancelaCFDIDto = validarRespuestaCancelacion(xmlCancelado);
///////////////////////////////////////////////////////////////////////
xmlCancelado=" no esta vacio";
returnCancelaCFDIDto.setCode("201");

///////////////////////////////////////////////////////////////////////////
                    try {
                        if (xmlCancelado != null) {
                            if (returnCancelaCFDIDto.getCode().equals("201") || returnCancelaCFDIDto.getCode().equals("202")) {

                                //Busqueda del documento despues de haber mandado la cancelación
     //                           log.info("*** Buscando estatus del documento: " + uuidC);
                                String busqueda[] = consultaCancela(rfcEmisorC, rfcReceptorC, totalC, uuidC);
//                                busqueda[2]="Cancelado";// Comentar esto en productivo o si el servicio de cancelacion de pruebas del SAT está funcionando correctamente
                                if (busqueda[1]!= null && !busqueda[1].equalsIgnoreCase("No Cancelable")) {
                                    
//                                     busqueda[2]="Cancelado";// Comentar esto en productivo o si el servicio de cancelacion de pruebas del SAT está funcionando correctamente
                                   
                                     if (busqueda[2].equalsIgnoreCase("Cancelado")) {
                                        try {
                                    String nombreArchivo = "";
                                    String[] arreglo = query.buscarInfoUUID(uuidC);

                                    if (arreglo != null) {
                                        if (arreglo[0] != null && !arreglo[0].equals("")) {
                                            nombreArchivo = arreglo[0] + arreglo[1];
                                        } else {
                                            nombreArchivo = arreglo[1];
                                        }
        //                                log.info("Nombre del pdf: " + nombreArchivo);

                                        PDFMaker_Timbrado pdfMaker = new PDFMaker_Timbrado();
                                        try {
                                            pdfMaker.manipulatePdfWatermark(
                                                    Utils.getParamValue("path_base") + nombreArchivo + " .pdf",
                                                    Utils.getParamValue("path_base") + "CAN_" + nombreArchivo + " .pdf");
//                                            System.out.println("Termine de crear el pdf con la marca de agua.");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        pdfbase64=Utils.pdfBase64(Utils.getParamValue("path_base"), "CAN_" + nombreArchivo );
                                    }

                                } catch (Exception fe) {
                                    fe.printStackTrace();
                                }
                                    }// comentado para pruebas
  
                                    
                                    
                                    
                                    
                                }
                                //validamos que nos haya regresado información.
                                if (busqueda.length > 1) {
                                    //validamos si necesita tener aceptación, no lo registramos y que vuelva a mandar la solicitud
                                    if (busqueda[1].equalsIgnoreCase("Cancelable con aceptación")) {

                                    } else {
  ////////////////////////////////////                                      query.registraCancelacion("Manual", fechaC, uuidC, rfcEmisorC, rfcReceptorC, totalC);
                                    }
//                                    System.out.println(busqueda[0] + "," + busqueda[1] + "," + busqueda[2] + "," + busqueda[3]);
                                    //Actualiza el estatus en la base.
                                    if (busqueda[2].equals("Cancelado")) {
                                        //validamos si necesita tener aceptación, no lo registramos y que vuelva a mandar la solicitud
                                        if (busqueda[1].equalsIgnoreCase("Cancelable con aceptación")) {
 ////////////////////////////////////////                                           query.registraCancelacion("Manual", fechaC, uuidC, rfcEmisorC, rfcReceptorC, totalC);
                                        }
                                        try {
                                    String nombreArchivo = "";
                                    String[] arreglo = query.buscarInfoUUID(uuidC);

                                    if (arreglo != null) {
                                        if (arreglo[0] != null && !arreglo[0].equals("")) {
                                            nombreArchivo = arreglo[0] + arreglo[1];
                                        } else {
                                            nombreArchivo = arreglo[1];
                                        }
        //                                log.info("Nombre del pdf: " + nombreArchivo);

                                        PDFMaker_Timbrado pdfMaker = new PDFMaker_Timbrado();
                                        try {
                                            pdfMaker.manipulatePdfWatermark(
                                                    Utils.getParamValue("path_base") + nombreArchivo + " .pdf",
                                                    Utils.getParamValue("path_base") + "CAN_" + nombreArchivo + " .pdf");
//                                            System.out.println("Termine de crear el pdf con la marca de agua.");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

					pdfbase64=Utils.pdfBase64(Utils.getParamValue("path_base"), "CAN_" + nombreArchivo);
                                    }

                                } catch (Exception fe) {
                                    fe.printStackTrace();
                                }
                                        //Actualizamos los estatus en las tablas cfdis y cfdi_cancelaciones.
                                        query.actualizaCancelaciones(uuidC);
           //                             log.info("termina: actualizaCancelaciones");
                                        query.actualizaCancelacionCfdi(uuidC);
              //                          log.info("termina: actualizaCancelacionCfdi");
                                    }
                                    //responseComprobanteAdminisrtaDatosDto.setDocumentoxml(Base64Coder.encodeString(xmlCancelado));
                                    //responseComprobanteAdminisrtaDatosDto.setCodigo(returnCancelaCFDIDto.getCode());// se retorna siempre 201 a petición de proa.
                                    responseComprobanteAdminisrtaDatosDto.setCodigo("201");
                                    responseComprobanteAdminisrtaDatosDto.setDescripcion(returnCancelaCFDIDto.getMessage());
//                                    responseComprobanteAdminisrtaDatosDto.setCancelable("");
                                    responseComprobanteAdminisrtaDatosDto.setCancelable(busqueda[1]);
                                    responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[2]);
//                                    responseComprobanteAdminisrtaDatosDto.setEstado("");
//                                    responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[3]);
                                    responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                                    responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);
                                } else {
                                    //responseComprobanteAdminisrtaDatosDto.setDocumentoxml(Base64Coder.encodeString(xmlCancelado));
                                    responseComprobanteAdminisrtaDatosDto.setCodigo("000");
                                    responseComprobanteAdminisrtaDatosDto.setDescripcion("El SAT esta presentando intermitencia, vuelva intentar más tarde");
                                    responseComprobanteAdminisrtaDatosDto.setCancelable("");
                                    responseComprobanteAdminisrtaDatosDto.setEstado("");
                                    responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                                    responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);
                                }

                            } else {
                                responseComprobanteAdminisrtaDatosDto.setCodigo(returnCancelaCFDIDto.getCode());
                                responseComprobanteAdminisrtaDatosDto.setDescripcion(returnCancelaCFDIDto.getMessage());
                                responseComprobanteAdminisrtaDatosDto.setCancelable("");
                                responseComprobanteAdminisrtaDatosDto.setEstado("");
                                responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                                responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);
                            }

                        }
                    } catch (Exception fe) {
//                        log.error("Error al guardar la cancelación del CFDI: " + uuidC + " por, " + fe.getMessage());
                        System.out.println("Error al guardar la cancelación del CFDI: " + uuidC + " por, " + fe.getMessage());
                    }

                } else {
                    responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                    responseComprobanteAdminisrtaDatosDto.setCodigo("DE500");
                    responseComprobanteAdminisrtaDatosDto.setDescripcion("El documento no tiene los requisitos minimos para enviarlo");
                }
            }

        } catch (Exception e) {
            responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
            responseComprobanteAdminisrtaDatosDto.setCodigo(e.getMessage());
            responseComprobanteAdminisrtaDatosDto.setDescripcion(e.getMessage());
        }
        return responseComprobanteAdminisrtaDatosDto;
    }

    public ResponseComprobanteAdminisrtaDatosCDto consultaRelacionado(String uuid, String rfcReceptor) throws Exception {
        query = new QueryAdministrador();
        ResponseComprobanteAdminisrtaDatosCDto responseComprobanteAdminisrtaDatosDto = new ResponseComprobanteAdminisrtaDatosCDto();
        ReturnCancelaCFDIDto returnCancelaCFDIDto = new ReturnCancelaCFDIDto();
        try {
            //Extracción de información
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            //System.out.println("<Inicio><Identifica "+docs_sp[i]+"</Identifica></Inicio>");
            Document docCan = Utils.convertStringToDocument(uuid); //dBuilder.parse(fXmlFile);				
            docCan.getDocumentElement().normalize();

            NodeList nodesId = docCan.getElementsByTagName("Cancelacion");
            Element elementId = (Element) nodesId.item(0);
            fechaC = (elementId.getAttribute("Fecha"));
            Date fechaPeriodo = (Utils.stringToDatePatern(elementId.getAttribute("Fecha"), "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss"));
            rfcEmisorC = (elementId.getAttribute("RfcEmisor"));

            //Etiqueta a mapear uuid´s
            NodeList nodesFolios = docCan.getElementsByTagName("Folios");
            Element elementUuid = (Element) nodesFolios.item(0);

            if (elementUuid != null) {
                uuidC = (XMLParseUtils.getValueToTagName(elementUuid, "UUID"));
                rfcReceptorC = (XMLParseUtils.getValueToTagName(elementUuid, "rfcreceptor"));
                totalC = (XMLParseUtils.getValueToTagName(elementUuid, "total"));

            }
            //Valida si existe ya la cancelación en curso
            String[] buscaCancela = query.buscaCancelacion(uuidC);
            if (buscaCancela != null) {
                if (buscaCancela[4].equals("P")) {
//                    System.out.println("*** Buscando estatus del documento: " + uuidC);
                    String busqueda[] = consultaCancela(rfcEmisorC, rfcReceptorC, totalC, uuidC);
                    //validamos que nos haya regresado información.
                    if (busqueda.length > 1) {
//                        System.out.println(busqueda[0] + "," + busqueda[1] + "," + busqueda[2]);
                        //Actualiza el estatus en la base.
                        if (busqueda[2].equals("Cancelado")) {
                            //Actualizamos los estatus en las tablas cfdis y cfdi_cancelaciones.
                            query.actualizaCancelaciones(uuidC);
                            query.actualizaCancelacionCfdi(uuidC);
                        }
                        //responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                        responseComprobanteAdminisrtaDatosDto.setCodigo("201");
                        responseComprobanteAdminisrtaDatosDto.setDescripcion("Búsqueda de estatus exitosa");
                        responseComprobanteAdminisrtaDatosDto.setCancelable(busqueda[1]);
                        responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[2]);
                        responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                    } else {

                        //responseComprobanteAdminisrtaDatosDto.setDocumentoxml(Base64Coder.encodeString(xmlCancelado));
                        responseComprobanteAdminisrtaDatosDto.setCodigo("000");
                        responseComprobanteAdminisrtaDatosDto.setDescripcion("El SAT esta presentando intermitencia, vuelva intentar más tarde");
                        responseComprobanteAdminisrtaDatosDto.setCancelable("");
                        responseComprobanteAdminisrtaDatosDto.setEstado("");
                        responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);

                    }

                } else if (buscaCancela[4].equals("C")) {
                    //responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
//                    System.out.println("*** Buscando estatus del documento: " + uuidC);
                    String busqueda[] = consultaCancela(rfcEmisorC, rfcReceptorC, totalC, uuidC);
                    //validamos que nos haya regresado información.
                    if (busqueda.length > 1) {
//                        System.out.println(busqueda[0] + "," + busqueda[1] + "," + busqueda[2]);
                        //Actualiza el estatus en la base.
                        if (busqueda[2].equals("Cancelado")) {
                            //Actualizamos los estatus en las tablas cfdis y cfdi_cancelaciones.
                            query.actualizaCancelaciones(uuidC);
                            query.actualizaCancelacionCfdi(uuidC);
                        }
                        //responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                        responseComprobanteAdminisrtaDatosDto.setCodigo("201");
                        responseComprobanteAdminisrtaDatosDto.setDescripcion("Comprobante cancelado anteriormente");
                        responseComprobanteAdminisrtaDatosDto.setCancelable(busqueda[1]);
                        responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[2]);
                        responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                    } else {

                        //responseComprobanteAdminisrtaDatosDto.setDocumentoxml(Base64Coder.encodeString(xmlCancelado));
                        responseComprobanteAdminisrtaDatosDto.setCodigo("000");
                        responseComprobanteAdminisrtaDatosDto.setDescripcion("El SAT esta presentando intermitencia, vuelva intentar más tarde");
                        responseComprobanteAdminisrtaDatosDto.setCancelable("");
                        responseComprobanteAdminisrtaDatosDto.setEstado("");
                        responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);

                    }

                }
            } else {
 //               System.out.println("*** Cancelando documento: " + uuidC);
                Document documentoCancelacion = generaComprobanteCancelacion(uuid, uuid);
                if (documentoCancelacion != null) {
                    String xmlCancelado = enviarPeticionCancelacion(documentoCancelacion);
                    returnCancelaCFDIDto = validarRespuestaCancelacion(xmlCancelado);

                    try {
                        if (xmlCancelado != null) {
                            if (returnCancelaCFDIDto.getCode().equals("201") || returnCancelaCFDIDto.getCode().equals("202")) {
  ////////////////////////////////////                              query.registraCancelacion("Manual", fechaC, uuidC, rfcEmisorC, rfcReceptorC, totalC);
                                //Busqueda del documento despues de haber mandado la cancelación
//                                System.out.println("*** Buscando estatus del documento: " + uuidC);
                                String busqueda[] = consultaCancela(rfcEmisorC, rfcReceptorC, totalC, uuidC);

                                //validamos que nos haya regresado información.
                                if (busqueda.length > 1) {
//                                    System.out.println(busqueda[0] + "," + busqueda[1] + "," + busqueda[2]);
                                    //Actualiza el estatus en la base.
                                    if (busqueda[2].equals("Cancelado")) {
                                        //Actualizamos los estatus en las tablas cfdis y cfdi_cancelaciones.
                                        query.actualizaCancelaciones(uuidC);
                                        query.actualizaCancelacionCfdi(uuidC);
                                    }
                                    //responseComprobanteAdminisrtaDatosDto.setDocumentoxml(Base64Coder.encodeString(xmlCancelado));
                                    responseComprobanteAdminisrtaDatosDto.setCodigo(returnCancelaCFDIDto.getCode());
                                    responseComprobanteAdminisrtaDatosDto.setDescripcion(returnCancelaCFDIDto.getMessage());
                                    responseComprobanteAdminisrtaDatosDto.setCancelable(busqueda[1]);
                                    responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[3]);
                                    //responseComprobanteAdminisrtaDatosDto.setEstado(busqueda[2]);
                                    responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                                } else {
                                    //responseComprobanteAdminisrtaDatosDto.setDocumentoxml(Base64Coder.encodeString(xmlCancelado));
                                    responseComprobanteAdminisrtaDatosDto.setCodigo("000");
                                    responseComprobanteAdminisrtaDatosDto.setDescripcion("El SAT esta presentando intermitencia, vuelva intentar más tarde");
                                    responseComprobanteAdminisrtaDatosDto.setCancelable("");
                                    responseComprobanteAdminisrtaDatosDto.setEstado("");
                                    responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                                }

                            } else {
                                responseComprobanteAdminisrtaDatosDto.setCodigo(returnCancelaCFDIDto.getCode());
                                responseComprobanteAdminisrtaDatosDto.setDescripcion(returnCancelaCFDIDto.getMessage());
                                responseComprobanteAdminisrtaDatosDto.setCancelable("");
                                responseComprobanteAdminisrtaDatosDto.setEstado("");
                                responseComprobanteAdminisrtaDatosDto.setUUID(uuidC);
                            }

                        }
                    } catch (Exception fe) {
//                        log.error("Error al guardar la cancelación del CFDI: " + uuidC + " por, " + fe.getMessage());
                        System.out.println("Error al guardar la cancelación del CFDI: " + uuidC + " por, " + fe.getMessage());
                    }

                } else {
                    responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                    responseComprobanteAdminisrtaDatosDto.setCodigo("DE500");
                    responseComprobanteAdminisrtaDatosDto.setDescripcion("El documento no tiene los requisitos minimos para enviarlo");
                }
            }

        } catch (Exception e) {
            responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
            responseComprobanteAdminisrtaDatosDto.setCodigo(e.getMessage());
            responseComprobanteAdminisrtaDatosDto.setDescripcion(e.getMessage());
        }
        return responseComprobanteAdminisrtaDatosDto;
    }

    public String[] consultaCancela(String rfcEmisor, String rfcReceptor, String importe, String uuid) throws RemoteException, KeyManagementException, NoSuchAlgorithmException, ServiceException {
        String resultado = "";
        query = new QueryAdministrador();
        //String xmlString="?re="+rfcEmisor+"&rr="+rfcReceptor+"&tt="+importe+"&id="+uuid;
        String xmlString = rfcEmisor + "," + rfcReceptor + "," + importe + "," + uuid;
 //       log.info("Consulta cancelación " + xmlString);
//        System.out.println(xmlString);
        byte ptext[] = xmlString.getBytes();
        try {
            String value = new String(ptext, "UTF-8");
        } catch (UnsupportedEncodingException ex) {

        }

        String rest[] = consulta(xmlString).split(",");
        if (rest.length > 1) {
//            System.out.println(rest[0]);
//            log.info(rest[0]);
        } else {
            System.out.println("No se esta devolviendo informacion tras la busqueda en cancelaciones");
        }

        //Acuse rest2=consultaCFDI(xmlString);
        //resultado="Estatus: "+rest.getEstado().toString();
        //System.out.println(resultado);
        return rest;

    }

    private static String consulta(java.lang.String arg0) throws RemoteException, KeyManagementException, NoSuchAlgorithmException, ServiceException {
        SSLContext ctx = SSLContext.getInstance("SSL");
        ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);
        ConsultaLocator serviceLocator = new ConsultaLocator();
//        log.info("Ambiente - " + Utils.getParamValue("ambiente_timbrado"));
//        System.setProperty("https.proxyHost", "sv3mx1wf02.adm02.ads.gbo.com");
//        System.setProperty("https.proxyPort", "8080");
        System.setProperty("https.proxyHost", "");
        System.setProperty("https.proxyPort", "");
        ConsultaPortType port = serviceLocator.getconsultaPort(Utils.getParamValue("ambiente_timbrado"));
//        System.out.println("ambiente canc " + Utils.getParamValue("ambiente_timbrado"));
  //      log.info("arg0 " + arg0);
        String rest = port.consultaComprobante(arg0);
//        System.out.println("rest - " + rest);
        return rest;

    }

    public ReturnCancelaCFDIDto validarRespuestaCancelacion(String respuesta) throws Exception {
        query = new QueryAdministrador();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setCoalescing(true);

        ReturnCancelaCFDIDto cancelaCFDIDto = new ReturnCancelaCFDIDto();

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(respuesta));

        Document docRespuesta = db.parse(is);

        //docRespuesta = factory.newDocumentBuilder().parse(new InputSource(new StringReader(this.strAcuse)));
        String mensaje = "";

        String CodEstatus = "";

        NodeList nodeList = docRespuesta.getElementsByTagName("Acuse");
        for (int x = 0, size = nodeList.getLength(); x < size; x++) {
            Element e = (Element) nodeList.item(x);
            CodEstatus = e.getAttribute("CodEstatus");
        }

        if (CodEstatus.equals("")) {
            CodEstatus = getTagValue(respuesta, "EstatusUUID");
        }

        String descripcionError = "";

        if (CodEstatus.trim().equals("201")) {
            mensaje = "Solicitud enviada satisfactoriamente";
        } else if (CodEstatus.trim().equals("202")) {
            mensaje = "Solicitud enviada satisfactoriamente";
        } else {
            if (CodEstatus != null && !CodEstatus.isEmpty()) {
                int codigoEstatus = 0;
                try {
                    codigoEstatus = Integer.parseInt(CodEstatus);
                } catch (Exception e) {
                }

                switch (codigoEstatus) {
                    case 201:
                        descripcionError = "Solicitud enviada satisfactoriamente";
                        break;
                    case 202:
                        descripcionError = "Solicitud enviada satisfactoriamente";
                        break;
                    case 301:
                        descripcionError = "XML mal formado";
                        break;
                    case 302:
                        descripcionError = "Sello mal formado o invalido";
                        break;
                    case 303:
                        descripcionError = "Sello no corresponde a emisor o caduco";
                        break;
                    case 304:
                        descripcionError = "Certificado revicado o caduco";
                        break;
                    case 305:
                        descripcionError = "La fecha de emision no esta dentro de la vigencia del CSD del emisor";
                        break;
                    case 306:
                        descripcionError = "El certificado no es del tipo CFD";
                        break;
                    case 307:
                        descripcionError = "El CFDI contiene un timbre previo";
                        break;
                    case 308:
                        descripcionError = "Certificado no expedido por el SAT";
                        break;
                    default:
                        descripcionError = "Error no especificado";
                }
                mensaje = "Se produjo un error con codigo " + CodEstatus + " ("
                        + descripcionError + ")";
                /*if (this.log != null) {
                this.log.error(mensaje);
            }*/
                //throw new Exception(mensaje);
            }
        }
        cancelaCFDIDto.setCode(CodEstatus);
        cancelaCFDIDto.setMessage(mensaje);
        return cancelaCFDIDto;
    }

    @SuppressWarnings({"null", "ConstantConditions", "UnusedAssignment"})

    public BeanGeneral creaBeanCFDI(String archivoLayout) throws Exception {

        ObjectFactory of = new ObjectFactory();
        Comprobante comprobante = of.createComprobante();
        Comprobante.Emisor emisor = of.createComprobanteEmisor();
        Comprobante.CfdiRelacionados.CfdiRelacionado CfdiRelacionado = of.createComprobanteCfdiRelacionadosCfdiRelacionado();
        Comprobante.CfdiRelacionados CfdiRelacionados = of.createComprobanteCfdiRelacionados();
        Comprobante.Receptor receptor = of.createComprobanteReceptor();
        Comprobante.Impuestos.Traslados traslados = of.createComprobanteImpuestosTraslados();
        Comprobante.Impuestos.Retenciones retenciones = of.createComprobanteImpuestosRetenciones();
        Comprobante.Conceptos conceptos = of.createComprobanteConceptos();
        Comprobante.Conceptos.Concepto concepto = of.createComprobanteConceptosConcepto();
        Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado conceptoImpT = of.createComprobanteConceptosConceptoImpuestosTrasladosTraslado();
        Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion conceptoImpR = of.createComprobanteConceptosConceptoImpuestosRetencionesRetencion();
        Comprobante.Conceptos.Concepto.Impuestos.Traslados conceptoTras = of.createComprobanteConceptosConceptoImpuestosTraslados();
        Comprobante.Conceptos.Concepto.Impuestos.Retenciones conceptoRet = of.createComprobanteConceptosConceptoImpuestosRetenciones();
        Comprobante.Conceptos.Concepto.Impuestos impuesosCon = of.createComprobanteConceptosConceptoImpuestos();
        Comprobante.Impuestos impuestos = of.createComprobanteImpuestos();
        Comprobante.Complemento complemento = of.createComprobanteComplemento();
        AddendaMabeCabecero amc = null;
        AddendaMabeDetalles amd = null;
        AddendaDetallistaC adc = null;
        AddendaDetallistaItem adi = null;
        AddendaSanofi asc = null;
        ArrayList<AddendaMabeDetalles> listMabeDetalle  = new ArrayList<>();
        ArrayList<AddendaDetallistaItem> listDetallistaItem  = new ArrayList<>();
        general = new BeanGeneral();
        error = false;
        errorLeer = false;

        int cfdir = 0;
        boolean tras = false;
        boolean ret = false;
        Boolean rfc = false;
        String xml = null;
        String[] campos = null;

        InputStream targetStream = IOUtils.toInputStream(archivoLayout);
        BufferedReader in = new BufferedReader(new InputStreamReader(targetStream));
        String linea = in.readLine();
        int temp = 0;
        String monto = "";

        while (linea != null) {
            
            campos = linea.split(",");
            if (campos[0].equalsIgnoreCase("P")) {
               DecimalFormat df = new DecimalFormat("#.00");
               DecimalFormat df3 = new DecimalFormat("#.000");
               DecimalFormat df4 = new DecimalFormat("#.0000");
                // ****CONCEPTOS***
                concepto = of.createComprobanteConceptosConcepto();
                if (campos[1].trim() !=null && !campos[1].isEmpty()) {
                    concepto.setClaveProdServ(campos[1].trim());
                } else {
//                    log.error("Falta Clave producto Columna B");
                    System.out.println("Falta Clave producto Columna B");
                }
                concepto.setNoIdentificacion(campos[17].trim());//opcional
                if (campos[3].trim()!= null && !campos[3].isEmpty()) {
                    //System.out.println("desc- "+campos[3].trim());
                    concepto.setDescripcion(campos[3].replaceAll("\\|", ","));
                } else {
//                    log.error("Falta Descripcion Columna D");
                    System.out.println("Falta Descripcion Columna D");
                }
                if (campos[4].trim()!= null && !campos[4].trim().isEmpty()) {
                concepto.setCantidad(Utils.stringtoBigDecimal(campos[4].trim()));
                } else {
//                    log.error("Falta Cantidad Columna E");
                    System.out.println("Falta Cantidad Columna E");
                }
                if (campos[5]!= null && !campos[5].isEmpty()) {
                    concepto.setClaveUnidad(campos[5].trim());
                } else {
//                    log.error("Falta Clave Unidad Columna F");
                    System.out.println("Falta Clave Unidad Columna F");
                }
                concepto.setUnidad(campos[6].trim());//opcional
                if (campos[7].trim()!=null && !campos[7].isEmpty()) {
                    concepto.setValorUnitario(new BigDecimal(campos[7].trim()));
                } else {
//                     log.error("Falta precio unitario Columna H");
                     System.out.println("Falta precio unitario Columna H");
                }
                if (campos[8].trim()!=null && !campos[8].isEmpty()) {
                    concepto.setImporte(new BigDecimal(campos[8].trim()));
                } else {
//                    log.error("Falta Importe Columna I");
                     System.out.println("Falta Importe Columna I");
                }
                if (!campos[19].trim().isEmpty()) {
                        concepto.setObjetoImp(campos[19].trim());
                    }

                impuesosCon = of.createComprobanteConceptosConceptoImpuestos();
                conceptoTras = of.createComprobanteConceptosConceptoImpuestosTraslados();
                conceptoRet = of.createComprobanteConceptosConceptoImpuestosRetenciones();
                boolean conTras = false;
                boolean conRet = false;
                if (!campos[9].isEmpty()) {
                    conceptoImpT = of.createComprobanteConceptosConceptoImpuestosTrasladosTraslado();
                    conceptoImpT.setBase(new BigDecimal(campos[8].trim()));
                    if (campos[9].trim()!=null && !campos[9].isEmpty()) {
                    conceptoImpT.setImpuesto(campos[9].trim());
                    } else {
//                        log.info("Falta impuesto Traslado Columna J");
                     System.out.println("Falta impuesto Traslado Columna J");
                    }
                    if (campos[10].trim()!=null && !campos[10].isEmpty()) {
                     conceptoImpT.setTipoFactor(mx.gob.sat.cfd4_0.CTipoFactor.fromValue(campos[10].trim()));
                    } else {
//                        log.info("Falta tipo Factor Traslado Columna K");
                     System.out.println("Falta tipo Factor Traslado Columna K");
                    }
                    String decim[] = campos[12].split("\\.");
                    if (decim.length > 1) {
                         int numDec = decim[1].length();
                    conceptoImpT.setTasaOCuota(new BigDecimal(campos[11].trim()));
                    Double impTC = Double.parseDouble(campos[12].trim());
                   switch (numDec) {
                       case 4:
                           conceptoImpT.setImporte(new BigDecimal(df4.format(impTC)));
                           break;
                       case 3:
                           conceptoImpT.setImporte(new BigDecimal(df3.format(impTC)));
                           break;
                       default:
                           conceptoImpT.setImporte(new BigDecimal(df.format(impTC)));
                           break;
                   }
                    } else {
                        conceptoImpT.setTasaOCuota(new BigDecimal(campos[11].trim()));
                    Double impTC = Double.parseDouble(campos[12].trim());
                    conceptoImpT.setImporte(new BigDecimal(df.format(impTC)));
                    }
                   
                    conceptoTras.getTraslado().add(conceptoImpT);
                    conTras = true;
                }else{
//                    log.info("Faltan impuestos Traslado por concepto Columna J");
                    System.out.println("Faltan impuestos Traslado por concepto Columna J");
                }
                if (!campos[13].isEmpty()) {
                    conceptoImpR = of.createComprobanteConceptosConceptoImpuestosRetencionesRetencion();
                    conceptoImpR.setBase(new BigDecimal(campos[8].trim()));
                    if (campos[13].trim()!=null && !campos[13].isEmpty()) {
                     conceptoImpR.setImpuesto(campos[13].trim());
                    } else {
//                        log.info("Falta impuesto Retencion Columna N");
                     System.out.println("Falta impuesto Retencion Columna N");
                    }
                    if (campos[14].trim()!=null && !campos[14].isEmpty()) {
                     conceptoImpR.setTipoFactor(mx.gob.sat.cfd4_0.CTipoFactor.fromValue(campos[14].trim()));
                    } else {
//                        log.info("Falta tipo factor Retencion Columna O");
                     System.out.println("Falta tipo factor Retencion Columna O");
                    }
                    if (campos[15].trim()!=null && !campos[15].isEmpty()) {
                     conceptoImpR.setTasaOCuota(new BigDecimal(campos[15].trim()));
                    } else {
//                        log.info("Falta tasa o cuota Retencion Columna P");
                     System.out.println("Falta tasa o cuota Retencion Columna P");
                    }
                    if (campos[16].trim()!=null && !campos[16].isEmpty()) {
                        String decim[] = campos[16].split("\\.");
                    int numDec = decim[1].length();
//                    conceptoImpT.setTasaOCuota(campos[11].trim());
                    Double impRC = Double.parseDouble(campos[16].trim());
                   switch (numDec) {
                       case 4:
                           conceptoImpR.setImporte(new BigDecimal(df4.format(impRC)));
                           break;
                       case 3:
                           conceptoImpR.setImporte(new BigDecimal(df3.format(impRC)));
                           break;
                       default:
                           conceptoImpR.setImporte(new BigDecimal(df.format(impRC)));
                           break;
                   }
                    } else {
//                        log.info("Falta importe Retencion Columna Q");
                     System.out.println("Falta importe Retencion Columna Q");
                    }
                    
                    if (!campos[17].trim().isEmpty()) {
                        concepto.setNoIdentificacion(campos[17].trim());
                        general.setNombreProd(campos[17].trim());
                    }
                    
                    conceptoRet.getRetencion().add(conceptoImpR);
                    conRet = true;
                }else{
         //           System.out.println("Faltan impuestos retencion por concepto Columna N (Opcional)");
                }
                if (conTras && conRet) {
                    impuesosCon.setTraslados(conceptoTras);
                    impuesosCon.setRetenciones(conceptoRet);
                    concepto.setImpuestos(impuesosCon);
                    conTras = false;
                    conRet = false;
                } else if (conTras && !conRet) {
                    impuesosCon.setTraslados(conceptoTras);
                    impuesosCon.setRetenciones(null);
                    concepto.setImpuestos(impuesosCon);
                    conTras = false;
                } else if (!conTras && conRet) {
                    impuesosCon.setRetenciones(conceptoRet);
                    impuesosCon.setTraslados(null);
                    concepto.setImpuestos(impuesosCon);
                    conRet = false;
                }
//                concepto.setImpuestos(impuesosCon);
                conceptos.getConcepto().add(concepto);
            } else if (campos[0].equalsIgnoreCase("AMC")) {
                amc = new AddendaMabeCabecero();
                amc.setOrdenCompra(campos[1].trim());
                amc.setReferencia1(campos[2].trim());
                amc.setReferencia2(campos[3].trim());
                amc.setImporteLetra(campos[4].trim());
                amc.setCodigo(campos[5].trim());
                amc.setPlantaEntrega(campos[6].trim());
                amc.setTipoT(campos[7].trim());
                amc.setDescripcionT(campos[8].trim());
                amc.setImporteT(campos[9].trim());
            }else if (campos[0].equalsIgnoreCase("AMI")) {
                amd = new AddendaMabeDetalles();
                
                amd.setPrecioSinIVA(campos[1].trim());
                amd.setPrecuiConIVA(campos[2].trim());
                amd.setImporteSinIVA(campos[3].trim());
                amd.setImporteConIVA(campos[4].trim());
                amd.setUnidad(campos[5].trim());
                listMabeDetalle.add(amd);
                
            
            }else if (campos[0].equalsIgnoreCase("CDC")) {
                adc = new AddendaDetallistaC();
                
                adc.setDocumentStatus(campos[1].trim());
                adc.setImporteLetra(campos[2].trim());
                adc.setOrdenCompra(campos[3].trim());
                adc.setFechaPedido(campos[4].trim());
                adc.setNumeroRemision(campos[5].trim());
                adc.setContraRecibo(campos[6].trim());
                adc.setFechaContraRecibo(campos[7].trim());
                adc.setGlnComprador(campos[8].trim());
                adc.setContactoCompras(campos[9].trim());
                adc.setGlnProveedor(campos[10].trim());
                adc.setIdentificacionSecundaria(campos[11].trim());
                adc.setPorcentaje(campos[12].trim());
                adc.setTotalAmount(campos[13].trim());
                adc.setMontoTotalDescuentos(campos[14].trim());
               
            }else if (campos[0].equalsIgnoreCase("CDI")) {
                adi = new AddendaDetallistaItem();
                
                adi.setEan(campos[1].trim());
                adi.setSku(campos[2].trim());
                adi.setGrossPriceAmount(campos[3].trim());
                adi.setNetPriceAmount(campos[4].trim());
                adi.setTotalLineAmount(campos[5].trim());
                adi.setTotalLineAMountNeto(campos[6].trim());
                listDetallistaItem.add(adi);
            
            }else if (campos[0].equalsIgnoreCase("ASC")) {
                asc = new AddendaSanofi();
                
                asc.setOrdenCompra(campos[1].trim());
                asc.setCorreo(campos[2].trim());            
            }else {
                DecimalFormat df = new DecimalFormat("#.00");
                DecimalFormat df3 = new DecimalFormat("#.000");
                DecimalFormat df4 = new DecimalFormat("#.0000");
                if (campos[0].trim()!=null && !campos[0].isEmpty()) {
                    comprobante.setTipoDeComprobante(mx.gob.sat.cfd4_0.CTipoDeComprobante.fromValue(campos[0].trim()));
                    } else {
//                    log.error("Falta tipo de comprobante Columna A");
                     System.out.println("Falta tipo de comprobante Columna A");
                    }
                if (!campos[1].isEmpty()) {
                    comprobante.setSerie(campos[1].trim());//opcional
                }
                if (!campos[2].isEmpty()) {
                    comprobante.setFolio(campos[2].trim());//opcional
                }
                if (campos[3].trim()!=null && !campos[3].isEmpty()) {
                    DateFormat format = new SimpleDateFormat("dd/MM/yyy");
                Date date = format.parse(campos[3].trim());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(date);
                XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                comprobante.setFecha(xmlGregCal);
                    } else {
//                    log.error("Falta fecha emision Columna D");
                     System.out.println("Falta fecha emision Columna D");
                }
                comprobante.setVersion(campos[4].trim());
                if (campos[5].trim()!=null && !campos[5].isEmpty()) {
                    emisor.setRfc(campos[5].trim());
                    } else {
//                    log.error("Falta RFC emisor Columna F");
                     System.out.println("Falta RFC emisor Columna F");
                    }
                emisor.setNombre(campos[6].replaceAll("\\|", ","));
                if (campos[7].trim()!=null && !campos[7].isEmpty()) {
                    emisor.setRegimenFiscal(campos[7].trim());
                    } else {
//                    log.error("Falta Regimen fiscal Columna H");
                     System.out.println("Falta Regimen fiscal Columna H");
                    }
                if (campos[8].trim()!=null && !campos[8].isEmpty()) {
                    receptor.setRfc(campos[8].trim());
                    } else {
//                    log.error("Falta RFC receptor Columna I");
                     System.out.println("Falta RFC receptor Columna I");
                    }
                if (!campos[9].isEmpty()) {
                    receptor.setNombre(campos[9].replaceAll("\\|", ","));
                }
                if (!campos[10].isEmpty()) {
                    receptor.setResidenciaFiscal(mx.gob.sat.cfd4_0.CPais.fromValue(campos[10].trim()));
                }
                if (!campos[11].isEmpty()) {
                    receptor.setNumRegIdTrib(campos[11].trim());
                }
                
                if (campos[12].trim()!=null && !campos[12].isEmpty()) {
                    String ucfdi="";
                    if(campos[12].trim().equalsIgnoreCase("G01")){
                    ucfdi="G_01";
                    }else if(campos[12].trim().equalsIgnoreCase("G02")){
                    ucfdi="G_02";
                    }else if(campos[12].trim().equalsIgnoreCase("G03")){
                    ucfdi="G03";
                    }else if(campos[12].trim().equalsIgnoreCase("I01")){
                    ucfdi="I_01";
                    }else if(campos[12].trim().equalsIgnoreCase("I02")){
                    ucfdi="I_02";
                    }else if(campos[12].trim().equalsIgnoreCase("I03")){
                    ucfdi="I_03";
                    }else if(campos[12].trim().equalsIgnoreCase("I04")){
                    ucfdi="I_04";
                    }else if(campos[12].trim().equalsIgnoreCase("I05")){
                    ucfdi="I_05";
                    }else if(campos[12].trim().equalsIgnoreCase("I06")){
                    ucfdi="I_06";
                    }else if(campos[12].trim().equalsIgnoreCase("I07")){
                    ucfdi="I_07";
                    }else if(campos[12].trim().equalsIgnoreCase("I08")){
                    ucfdi="I_08";
                    }else if(campos[12].trim().equalsIgnoreCase("D01")){
                    ucfdi="D_01";
                    }else if(campos[12].trim().equalsIgnoreCase("D02")){
                    ucfdi="D_02";
                    }else if(campos[12].trim().equalsIgnoreCase("D03")){
                    ucfdi="D_03";
                    }else if(campos[12].trim().equalsIgnoreCase("D04")){
                    ucfdi="D_04";
                    }else if(campos[12].trim().equalsIgnoreCase("D05")){
                    ucfdi="D_05";
                    }else if(campos[12].trim().equalsIgnoreCase("D06")){
                    ucfdi="D_06";
                    }else if(campos[12].trim().equalsIgnoreCase("D07")){
                    ucfdi="D_07";
                    }else if(campos[12].trim().equalsIgnoreCase("D08")){
                    ucfdi="D_08";
                    }else if(campos[12].trim().equalsIgnoreCase("D09")){
                    ucfdi="D_09";
                    }else if(campos[12].trim().equalsIgnoreCase("D10")){
                    ucfdi="D_10";
                    }else if(campos[12].trim().equalsIgnoreCase("P01")){
                    ucfdi="P_01";
                    }else if(campos[12].trim().equalsIgnoreCase("S01")){
                    ucfdi="S_01";
                    }
                    receptor.setUsoCFDI(mx.gob.sat.cfd4_0.CUsoCFDI.fromValue(campos[12].trim()));
                    } else {
//                    log.error("Falta UsoCFDI Columna M");
                     System.out.println("Falta UsoCFDI Columna M");
                    }
                if (campos[13].trim()!=null && !campos[13].isEmpty()) {
                    comprobante.setMoneda(mx.gob.sat.cfd4_0.CMoneda.valueOf(campos[13].trim()));
                    } else {
//                    log.error("Falta Moneda Columna N");
                     System.out.println("Falta Moneda Columna N");
                    }
                if (!campos[14].isEmpty()) {
                    Double tipoC = Double.parseDouble(campos[14].trim());
                    if (campos[13].trim().equalsIgnoreCase("MXN")) {
                    comprobante.setTipoCambio(new BigDecimal(tipoC));
                    } else {
                    comprobante.setTipoCambio(new BigDecimal(df.format(tipoC)));
                    }
                }
                if (!campos[15].isEmpty()) {
                    comprobante.setCondicionesDePago(campos[15].trim());
                    general.setPlazoPagos(campos[15].trim());
                }
                comprobante.setMetodoPago(mx.gob.sat.cfd4_0.CMetodoPago.valueOf(campos[16].trim()));
                general.setNoOrden(campos[17].trim());
                if (campos[18].trim()!=null && !campos[18].isEmpty()) {
                    comprobante.setLugarExpedicion(campos[18].trim());
                    } else {
//                    log.error("Falta Lugar Expedicion Columna S");
                     System.out.println("Falta Lugar Expedicion Columna S");
                    }
                comprobante.setFormaPago(campos[19].trim());
                general.setObservaciones(campos[20].trim());
                if (!campos[21].isEmpty()) {
                    // ****CFDIS RELACIONADOS***
                    
                    if (campos[21].trim()!=null && !campos[21].isEmpty()) {
                    CfdiRelacionados.setTipoRelacion(campos[21].trim());
                    
                    } else {
//                        log.info("Falta tipo relacion Columna V");
                     System.out.println("Falta tipo relacion Columna V");
                    }
                    CfdiRelacionado = of.createComprobanteCfdiRelacionadosCfdiRelacionado();
                    if (campos[22].trim()!=null && !campos[22].isEmpty()) {
                    CfdiRelacionado.setUUID(campos[22].trim());
                    CfdiRelacionados.getCfdiRelacionado().add(CfdiRelacionado);
                    cfdir = 1;
                    } else {
//                        log.info("Falta uuid relacionado Columna W");
                     System.out.println("Falta uuid relacionado Columna W");
                    }
                }
                if(!campos[24].isEmpty()){
                Comprobante.Impuestos.Traslados.Traslado traslado = of.createComprobanteImpuestosTrasladosTraslado();
                if (campos[48].trim()!=null && !campos[48].isEmpty()) {
                    
                    String[] decim = campos[48].split("\\.");
                    if (decim.length > 1) {
                        int numDec = decim[1].length();
                     Double impTras = Double.parseDouble(campos[48].trim());
                   switch (numDec) {
                       case 4:
                           traslado.setImporte(new BigDecimal(df4.format(impTras)));
                           break;
                       case 3:
                           traslado.setImporte(new BigDecimal(df3.format(impTras)));
                           break;
                       default:
                           traslado.setImporte(new BigDecimal(df.format(impTras)));
                           break;
                   }
                    } else {
                     Double impTras = Double.parseDouble(campos[48].trim());
                     traslado.setImporte(new BigDecimal(df.format(impTras)));

                   
                    }
                    
               
                    } else {
//                    log.info("Falta importe Impuestos Columna AW");
                     System.out.println("Falta importe Impuestos Columna AW");
                    }
                if (campos[52].trim()!=null && !campos[52].isEmpty()) {
                    traslado.setBase(new BigDecimal(campos[52].trim()));
                    } else {
//                    log.info("Falta base Impuestos Columna BA");
                     System.out.println("Falta base Impuestos Columna BA");
                    }
               if (campos[45].trim()!=null && !campos[45].isEmpty()) {
                    traslado.setImpuesto(campos[45].trim());
                    } else {
//                   log.info("Falta impuesto Impuestos Columna AW");
                     System.out.println("Falta impuesto Impuestos Columna AW");
                    }
                if (campos[47].trim()!=null && !campos[47].isEmpty()) {
                    traslado.setTasaOCuota(new BigDecimal(campos[47].trim()));
                    } else {
//                    log.info("Falta tasa o cuota Impuestos Columna AV");
                     System.out.println("Falta tasa o cuota Impuestos Columna AV");
                    }
                if (campos[46].trim()!=null && !campos[46].isEmpty()) {
                    traslado.setTipoFactor(mx.gob.sat.cfd4_0.CTipoFactor.valueOf(campos[46].trim().toUpperCase()));
                    } else {
//                    log.info("Falta tipo Factor Impuestos Columna AU");
                     System.out.println("Falta tipo Factor Impuestos Columna AU");
                    }
                traslados.getTraslado().add(traslado);
                tras = true;
                }
                if (!campos[23].isEmpty()) {
                    Comprobante.Impuestos.Retenciones.Retencion retencion = of.createComprobanteImpuestosRetencionesRetencion();
                    if (campos[44].trim()!=null && !campos[44].isEmpty()) {
                        
                    String decim[] = campos[44].split("\\.");
                    int numDec = decim[1].length();
                     Double impret = Double.parseDouble(campos[44].trim());
                   switch (numDec) {
                       case 4:
                           retencion.setImporte(new BigDecimal(df4.format(impret)));
                           break;
                       case 3:
                           retencion.setImporte(new BigDecimal(df3.format(impret)));
                           break;
                       default:
                           retencion.setImporte(new BigDecimal(df.format(impret)));
                           break;
                   }    
                    } else {
//                        log.info("Falta Importe Retencion Columna AS");
                     System.out.println("Falta Importe Retencion Columna AS");
                    }
                    if (campos[43].trim()!=null && !campos[43].isEmpty()) {
                    retencion.setImpuesto(campos[43].trim());
                    } else {
//                        log.info("Falta impuesto Retencion Columna AR");
                     System.out.println("Falta impuesto Retencion Columna AR");
                    }
                    retenciones.getRetencion().add(retencion);
                    ret = true;
                }
                if (!campos[23].isEmpty()) {
                    Double totImpRet = Double.parseDouble(campos[23].trim());
                    impuestos.setTotalImpuestosRetenidos(BigDecimal.valueOf(totImpRet));
                    ret = true;
                }
                if (!campos[24].isEmpty()) {
                    Double totImpTras = Double.parseDouble(campos[24].trim());
                    impuestos.setTotalImpuestosTrasladados(BigDecimal.valueOf(totImpTras));
                    tras = true;
                }
                
//                DecimalFormat df = new DecimalFormat("#.00");
                if (campos[25].trim()!=null && !campos[25].isEmpty()) {
                  Double subt = Double.parseDouble(campos[25].trim());
                   comprobante.setSubTotal(new BigDecimal(df.format(subt)));
                    } else {
//                    log.info("Falta subtotal Columna Z");
                     System.out.println("Falta subtotal Columna Z");
                    }
                if (campos[26].trim()!=null && !campos[26].isEmpty()) {
                  Double total = Double.parseDouble(campos[26].trim());
                comprobante.setTotal(new BigDecimal(df.format(total)));
                    } else {
//                    log.info("Falta total Columna AA");
                     System.out.println("Falta total Columna AA");
                    }
                if (campos.length > 53) {                  
                if (campos[53].trim()!=null && !campos[53].isEmpty()) {
                    Comprobante.InformacionGlobal infoG = of.createComprobanteInformacionGlobal();
                    infoG.setPeriodicidad(campos[53].trim());
                    if (campos[54].trim()!=null && !campos[54].isEmpty()) {
                 infoG.setMeses(campos[54].trim());
                    } else {
//                        log.info("Falta Meses Columna BC");
                     System.out.println("Falta Meses Columna BC");
                    }
                    if (campos[55].trim()!=null && !campos[55].isEmpty()) {
                 infoG.setAño(new Short(campos[55].trim()));
                    } else {
//                        log.info("Falta Año Columna BD");
                     System.out.println("Falta Año Columna BD");
                    }
                    comprobante.setInformacionGlobal(infoG);
                }
            }
               
                bReceptor.setNombre(campos[27].trim());
                bReceptor.setNacionalidad(campos[28].trim());
                bReceptor.setRfc(campos[29].trim());
                bReceptor.setCalle(campos[30].trim());
                bReceptor.setNumExterior(campos[31].trim());
                bReceptor.setColonia(campos[32].trim());
                bReceptor.setMunicipio(campos[33].trim());
                bReceptor.setCveCiudad(campos[34].trim());
                bReceptor.setCiudad(campos[35].trim());
                bReceptor.setCveEstado(campos[36].trim());
                bReceptor.setEstado(campos[37].trim());
                bReceptor.setCvePais(campos[38].trim());
                bReceptor.setPais(campos[39].trim());
                if (campos[51].trim()!=null && !campos[51].isEmpty()) {
                 bReceptor.setRegimenFiscalReceptor(campos[51].trim());
                }else{
//                    log.error("Falta regimenFiscal Cliente Columna AZ");
                    System.out.println("Falta regimenFiscal Cliente Columna AZ");
                }
//                comprobante.getReceptor().getDomicilioFiscalReceptor(campos[40].trim());
                bReceptor.setCp(campos[40].trim());
                bReceptor.setTelefono(campos[41].trim());
                bEmisor.setTelefono(campos[42].trim());
                
                try {
                    if (campos[49].trim()!=null && !campos[49].isEmpty()) {
                 bReceptor.setEdificio(campos[49].trim());
                    } else {
                     bReceptor.setEdificio("");
                    }
                } catch (Exception e) {
                    
                }
                if (campos[50].trim()!=null && !campos[50].isEmpty()) {
                 comprobante.setExportacion(campos[50].trim());
                    }else{
//                    log.error("Falta Exportacion Columna AY");
                    System.out.println("Falta Exportacion Columna AY");
                }
                

            }

            linea = in.readLine();
            temp++;
        }
        
//        pagos.getPago().add(pago);

        if (tras && ret) {
            impuestos.setTraslados(traslados);
            impuestos.setRetenciones(retenciones);
            comprobante.setImpuestos(impuestos);
        } else if (tras && !ret) {
            impuestos.setTraslados(traslados);
            comprobante.setImpuestos(impuestos);
        } else if (!tras && ret) {
            impuestos.setRetenciones(retenciones);
            comprobante.setImpuestos(impuestos);
        }
///////////////////////        bEmisor = this.getEmisor("EPR5210064M1");

        general.setEmisor(bEmisor);
        general.setReceptor(bReceptor);
        if (amc != null) {
            general.setMabeCabecero(amc);
            if (listMabeDetalle.size() > 0) {
            general.setMabeDetalles(listMabeDetalle);
            }
        }
        
        if(adc != null ){
            general.setDetallistaCabecero(adc);
            if (listDetallistaItem.size() > 0) {
                general.setDetallistaItems(listDetallistaItem);
            }
        }
        
        if(asc != null ){
            general.setAddendaSanofi(asc);
        }
        
        
        
        if (cfdir == 1) {
            comprobante.getCfdiRelacionados().add(CfdiRelacionados);
        }

        comprobante.setEmisor(emisor);
        comprobante.setReceptor(receptor);
        comprobante.setConceptos(conceptos);
//        complemento.getAny().add(pagos);
//        comprobante.setComplemento(complemento);

        if (!StringUtils.isBlank(emisor.getRfc())) {
//            comprobante.setNoCertificado(bEmisor.getSerial().trim().replaceAll("\\s+", ""));
//            String a2 = bEmisor.getLlavePublica();
//            String certificado = StringUtils.substringBetween(a2, "-----BEGIN CERTIFICATE-----", "-----END CERTIFICATE-----"); //Obtiene la cadena del certificado
//            comprobante.setCertificado(certificado.trim().replaceAll("\\s+", ""));	//Reemplaza los espacios en blanco	
        } else {

            String errorMessage = "El documento " + general.getFileName() + " no contiene un rfc de emisor válido";
            throw new Exception(errorMessage);
        }
        general.setComprobante(comprobante);

        return general;
    }

    public synchronized ResponseComprobanteAdministraDatosDto enviaComprobanteFiscal(String idoc, String rfc) throws Exception {
        query = new QueryAdministrador();
        ResponseComprobanteAdministraDatosDto responseComprobanteAdminisrtaDatosDto = new ResponseComprobanteAdministraDatosDto();
        comprobanteParseDto = new ComprobanteParseDto();
        ResponseXMLParseDto respParseXML = new ResponseXMLParseDto();
        ResponseXMLParseDto respTimbrarXML = new ResponseXMLParseDto();

        BeanGeneral compr = creaBeanCFDI(idoc);

        //Parseo de la información
//        respParseXML = parseXMLIDOCV2(idoc);
//        System.out.println("Se parseo el documento");
        respParseXML.setCodigo("100");
        //Se valida que el rfc coincida con el del XML, para tomar certificados.
        if (Utils.nullToString(rfc).equals(Utils.nullToString(compr.getComprobante().getEmisor().getRfc()))) {
            //Se valida la existencia del folio y serie ante la base
//            System.out.println("antes de valida existencia serie= " + compr.getComprobante().getSerie() + "  folio = " + compr.getComprobante().getFolio());
            boolean resp = query.validaExistencia(compr.getComprobante().getSerie(), compr.getComprobante().getFolio());
//            System.out.println("respuesta - " + resp);
            if (resp == false) {
                if (respParseXML.getCodigo().equals("100")) {
                    //Generamos el objeto comprobante, con los distintos datos
                    Comprobante comp = generaCFDI(compr);

                    if (comp != null) {
//                        System.out.println("Se genero objeto comprobante");
                        String docXML = "";
                        //Sellamos el xml
                        docXML = sellaComprobanteXML(comp, rfc, compr);
                        //System.out.println("Documento sellado:" + docXML);

    //                    System.out.println("*** Iniciando proceso de timbrado documento:" + comp.getFolio());
                        responseXMLParseDto = timbrarComprobante(rfc, docXML, "", respParseXML.getComprobanteParseDto());
//                        System.out.println(responseXMLParseDto.getCodigo() + "\n" + responseXMLParseDto.getDescripcion());
                        //validamos el codigo resultante
                        if (responseXMLParseDto.getCodigo().equals("100")) {
                            String cfdiTimbrado = new String(Base64.decodeBase64(responseXMLParseDto.getDocumento()));
                            
                            
                            
                            String selloEmisor = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "Sello");
                            String UUID = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "UUID");
                            String FechaTimbrado = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "FechaTimbrado");
                            String noCertificadoSAT = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "NoCertificadoSAT");
                            String selloSAT = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "SelloSAT");
                            String selloCFD = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "SelloCFD");
//                            String versionTFD = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "Version");
                            String noCertificado = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "NoCertificado");
                            String folio = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "Folio");
                            String serie = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "Serie");
                            String rfcPac = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "RfcProvCertif");
                            String fechaEmision = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "Fecha");
                            String subtotal = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "SubTotal");
                            String total = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "Total");
                            String tipocomp = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "TipoDeComprobante");
                            String rfcE = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Emisor", "Rfc");
                            String rfcR = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Receptor", "Rfc");
                            String xmlComplemento = "";
                            String xml64 = "";

                            comprobanteParseDto.setUUID(UUID);
                            comprobanteParseDto.setFechaTimbrado(FechaTimbrado);
                            comprobanteParseDto.setNoCertificadoSAT(noCertificadoSAT);
                            comprobanteParseDto.setSelloEmisor(selloEmisor);
                            comprobanteParseDto.setSelloSAT(selloSAT);
                            comprobanteParseDto.setSelloCFD(selloCFD);
                            comprobanteParseDto.setNoCertificado(noCertificado);
                            try {
                                String nombreArchivo = "";
                                if (serie != null) {
                                    nombreArchivo = serie + folio;
                                } else {
                                    nombreArchivo = folio;
                                }
//                                String nombreArchivo = UUID;
                                //ESCRIBE DOCUMENTO XML
                                String string = Base64Coder.decodeString(responseXMLParseDto.getDocumento());
                                byte[] response = string.getBytes(Charset.forName("ISO-8859-1"));
                                  String respuesta = new String(response, "ISO-8859-1");
                                  
                                  String rawString = Base64Coder.decodeString(responseXMLParseDto.getDocumento());
                                ByteBuffer buffer = StandardCharsets.UTF_8.encode(rawString); 

                                String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();

                                
                                xmlComplemento = Utils.writeXMLFile(Base64Coder.decodeString(responseXMLParseDto.getDocumento()),
                                        Utils.getParamValue("path_base") + nombreArchivo + ".xml",
                                        respParseXML.getComprobanteParseDto(), compr);
                                xml64 = Base64Coder.encodeString(xmlComplemento);
                            } catch (Exception e) {
                                System.out.println("Error al generar el XML");
                            }
                            //genera pdf
                            String path_base = "";
                            String ruta_plantillas = "";
                            String pdfbase64 = "";
                            try {

                                PDFMaker_Timbrado pdfMakerT = new PDFMaker_Timbrado();

                                path_base = Utils.getParamValue("path_base");
                                ruta_plantillas = Utils.getParamValue("path_ruta_pdf");
                                String pdfbase64AR = ""; //Ponemos vacia la variable para generar nuevo el pdf
                                //if(comprobanteParseDto.getDetalle().size()<=500){

                                //Validación para verificar que plantilla crear
                                    pdfbase64=pdfMakerT.createPDF(path_base, comprobanteParseDto,compr);
                             

                                responseXMLParseDto.setPdfBase64(pdfbase64); //asignamos el pdf para regresar
                                //}
                            } catch (Exception e) {
                                System.out.println("Error al generar el PDF " + e.getMessage());
                            }
                            
                            responseComprobanteAdminisrtaDatosDto.setCodigo(responseXMLParseDto.getCodigo());
                            responseComprobanteAdminisrtaDatosDto.setDescripcion(responseXMLParseDto.getDescripcion());
                            responseComprobanteAdminisrtaDatosDto.setDocumentoxml(xml64);
                            responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);
                            responseComprobanteAdminisrtaDatosDto.setFechaTimbrado(FechaTimbrado);
                            responseComprobanteAdminisrtaDatosDto.setNoCertificado(noCertificado);
                            responseComprobanteAdminisrtaDatosDto.setSelloCFDI(selloCFD);
                            responseComprobanteAdminisrtaDatosDto.setSelloSAT(selloSAT);
                            responseComprobanteAdminisrtaDatosDto.setUUID(UUID);

                            //registro filtradas
                            Boolean inserta = query.actualizaFiltradas(folio, serie, "1");
                            if (inserta) {
//                                System.out.println("Se registro corectamente en filtradas");
                            } else {
                                System.out.println("Hubo un error al insertar en filtradas");
                            }
                            //registro cfdis
                            Boolean insertaCfdis = query.actualizaCfdis(serie, folio, fechaEmision, FechaTimbrado, subtotal, total, tipocomp, responseXMLParseDto.getDocumento(), UUID, rfcE, rfcR, idoc);
                            if (insertaCfdis) {
//                                System.out.println("Se registro corectamente en cfdis");
//                                verificaCorreosPorEnviar();
                            } else {
                                System.out.println("Hubo un error al insertar en cfdis");
                            }
                        } else {
                            responseComprobanteAdminisrtaDatosDto.setCodigo(responseXMLParseDto.getCodigo());
                            responseComprobanteAdminisrtaDatosDto.setDescripcion(responseXMLParseDto.getDescripcion());
                        }
                    }
                } else {
                    responseComprobanteAdminisrtaDatosDto.setCodigo(respParseXML.getCodigo());
                    responseComprobanteAdminisrtaDatosDto.setDescripcion(respParseXML.getDescripcion());
                    responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
                    responseComprobanteAdminisrtaDatosDto.setDocumentopdf("");
                }
            } else {
                
                
                String[] arreglo= query.buscaUUIDTimbrado(compr.getComprobante().getSerie(),compr.getComprobante().getFolio());
                if (arreglo != null) {
                    String pdfbase64="";
                    String cfdiTimbrado = new String(Base64.decodeBase64(arreglo[1]));

                            String UUID = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "UUID");
                            String FechaTimbrado = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "FechaTimbrado");
                            String selloSAT = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "SelloSAT");
                            String selloCFD = Utils.getNodeAttributeValue(cfdiTimbrado, "tfd:TimbreFiscalDigital", "SelloCFD");
                            String noCertificado = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "NoCertificado");
                            String folio = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "Folio");
                            String serie = Utils.getNodeAttributeValue(cfdiTimbrado, "cfdi:Comprobante", "Serie");
                            String nombreArchivo="";
                            if (!serie.equals("")) {
                        nombreArchivo=serie+folio;
                    } else {
                                nombreArchivo=folio;
                    }
                            String pdf=Utils.getParamValue("path_base") + nombreArchivo + " .pdf";
                            File originalFile = new File(pdf); //141
//				File originalFileXML = new File("//home//azamudio//archivos_proa//facturas//"+uuid+".xml"); //141
				//File originalFile = new File("C:\\archivos_proa\\facturas\\"+uuid.trim()+" .pdf");
				//File originalFileXML = new File("C:\\archivos_proa\\facturas\\"+uuid.trim()+".xml");
				//validamos si existe el archivo en ruta
				if(originalFile.exists()){
//		            System.out.println("Comenzando a recuperar pdf desde ruta: "+originalFile.getPath());
					//File originalFile = new File("/home/admin/archivos_proa/facturas/"+uuid+" .pdf"); //151
		    		//File originalFile = new File("C:\\archivos_proa\\facturas\\"+uuid+" .pdf");
		            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
		            byte[] bytes = new byte[(int)originalFile.length()];
		            fileInputStreamReader.read(bytes);
		            pdfbase64 = new String(Base64Coder.encode(bytes));
				}
                            
                            responseComprobanteAdminisrtaDatosDto.setCodigo("JE307");
                            responseComprobanteAdminisrtaDatosDto.setDescripcion("El folio " + compr.getComprobante().getFolio() + " y la serie " + compr.getComprobante().getSerie() + " ya ha sido utilizado por otro documento.");
                            responseComprobanteAdminisrtaDatosDto.setDocumentoxml(arreglo[1]);
                            responseComprobanteAdminisrtaDatosDto.setDocumentopdf(pdfbase64);
                            responseComprobanteAdminisrtaDatosDto.setFechaTimbrado(FechaTimbrado);
                            responseComprobanteAdminisrtaDatosDto.setNoCertificado(noCertificado);
                            responseComprobanteAdminisrtaDatosDto.setSelloCFDI(selloCFD);
                            responseComprobanteAdminisrtaDatosDto.setSelloSAT(selloSAT);
                            responseComprobanteAdminisrtaDatosDto.setUUID(UUID);
                } else {
                responseComprobanteAdminisrtaDatosDto.setCodigo("JE307");
                responseComprobanteAdminisrtaDatosDto.setDescripcion("El folio " + compr.getComprobante().getFolio() + " y la serie " + compr.getComprobante().getSerie() + " ya ha sido utilizado por otro documento.");
                responseComprobanteAdminisrtaDatosDto.setUUID(arreglo[0]);
                responseComprobanteAdminisrtaDatosDto.setDocumentoxml(arreglo[1]);
                }
                
                
                
                
//                System.out.println("El folio " + compr.getComprobante().getFolio() + " y la serie " + compr.getComprobante().getSerie() + " ya ha sido utilizado por otro documento.");
            }
        } else {
            responseComprobanteAdminisrtaDatosDto.setCodigo("JE340");
            responseComprobanteAdminisrtaDatosDto.setDescripcion("El rfc enviado por el servicio no coincide con el del emisor, verifique");
            responseComprobanteAdminisrtaDatosDto.setDocumentoxml("");
            responseComprobanteAdminisrtaDatosDto.setDocumentopdf("");
        }

        return responseComprobanteAdminisrtaDatosDto;
    }

    public ResponseXMLParseDto parseXMLIDOCV2(String IDOCxml) throws Exception {
        responseXMLParseDto = new ResponseXMLParseDto();
        comprobanteParseDto = new ComprobanteParseDto();
        emisorDto = new EmisorDto();
        receptorDto = new ReceptorDto();
        l = new LeyendaFiscal();

        exterior = new ComercioExterior();
        IDOCxml = IDOCxml.replaceAll("&(?!amp;)", "&amp;");
        //String cadena=new String(IDOCxml.getBytes(Charset.forName("utf-8")));
        //System.out.println("xml mal:"+IDOCxml);
        //IDOCxml = new String(IDOCxml.getBytes("ISO-8859-1"), "UTF-8");
        Utils utils = new Utils();

        String texto_traslado = "";
        String total_traslado = "";
        String porc_traslado = "";

        String texto_retencion = "";
        String total_retencion = "";
        String porc_retencion = "";


        String xmlTrim = IDOCxml.trim();
        Comprobante comp = Utils.xmlToClass(xmlTrim);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(IDOCxml.getBytes("UTF-8")));

        //DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        //InputSource is = new InputSource();
        //is.setCharacterStream(new StringReader(utils.normaliza(IDOCxml)));              
        //Document doc = db.parse(is);	
        NodeList nodesIdDocCfdi = doc.getElementsByTagName("cfdi:Comprobante");
        Element elementIdDoCfdi = (Element) nodesIdDocCfdi.item(0);

        //Verificación, a que layout tendra que mapear el servicio (pagos o facturas).
        NodeList nodesIdDoc = null;
        nodesIdDoc = doc.getElementsByTagName("cfdi:Comprobante");
        Element elementIdDoc = null;
        elementIdDoc = (Element) nodesIdDoc.item(0);
        boolean varPagos = false;

        comprobanteParseDto.setVersion(comp.getVersion());
        comprobanteParseDto.setSerie(comp.getSerie());
        String prueba = comp.getSerie();
        comprobanteParseDto.setFolio(comp.getFolio());
//	        comprobanteParseDto.setFecha(Utils.stringToDatePatern(elementIdDoc.getAttribute("Fecha"),"yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss"));
        comprobanteParseDto.setFecha(Utils.stringToDatePatern(comp.getFecha().toString(), "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss"));
        comprobanteParseDto.setFecha_pdf(comp.getFecha().toString());
        comprobanteParseDto.setLugarExpedicion(comp.getLugarExpedicion());
//        comprobanteParseDto.setFormaDePago(comp.getFormaPago());
//        if (!comp.getMetodoPago().toString().equalsIgnoreCase("")) {
//            comprobanteParseDto.setMetodoDePago(comp.getMetodoPago().toString());
//        }
        comprobanteParseDto.setSubTotal(comp.getSubTotal());
        comprobanteParseDto.setMoneda(comp.getMoneda().toString());
        comprobanteParseDto.setTotal(comp.getTotal());
        comprobanteParseDto.setTipoDeComprobante(comp.getTipoDeComprobante().toString());
        comprobanteParseDto.setTipoCambio(comp.getTipoCambio());
        //Si el layout es de pagos, se lee diferente la etiqueta.
//        System.out.println("993");
//        System.out.println(comp.getMetodoPago());
        if (comp.getFormaPago() != null) {
            comprobanteParseDto.setFormaDePago(comp.getFormaPago());
        }
        if (comp.getMetodoPago() != null) {
            comprobanteParseDto.setMetodoDePago(comp.getMetodoPago().toString());
        }

        if (comp.getDescuento() != null) {
            comprobanteParseDto.setDescuento(comp.getDescuento());
        }

        
        if (comp.getCfdiRelacionados() != null) {
            
            NodeList nodesCfdiR = doc.getElementsByTagName("cfdi:CfdiRelacionados");
            Element elementCfdiR = (Element) nodesCfdiR.item(0);
            
            for (int i = 0; i < comprobanteParseDto.getCfdiRelacionado().length(); i++) {
                comprobanteParseDto.setCfdiRelacionado(comp.getCfdiRelacionados().get(i).getTipoRelacion());
//            System.out.println("RELACION-> " + comprobanteParseDto.getCfdiRelacionado());
            NodeList nodesCfdiRelacionado = elementCfdiR.getElementsByTagName("cfdi:CfdiRelacionado");
            //Element elementCfdiRelacionado = (Element) nodesCfdiRelacionado.item(0);

            ArrayList<Relacionados> listRelacionado = new ArrayList<Relacionados>();
            //System.out.println("Tamaño al mapear-> "+nodesRelacionado.getLength());
            for (int j = 0; j < comp.getCfdiRelacionados().get(i).getCfdiRelacionado().size(); j++) {
                Element elementRelacionado = (Element) nodesCfdiRelacionado.item(j);
                Relacionados relacion = new Relacionados();
                relacion.setUUID(comp.getCfdiRelacionados().get(i).getCfdiRelacionado().get(j).getUUID());

                listRelacionado.add(relacion);
            }
            comprobanteParseDto.setListRelacionados(listRelacionado);
            }         
        }

        NodeList nodesEmisor = doc.getElementsByTagName("cfdi:Emisor");
        Element elementEmisor = (Element) nodesEmisor.item(0);
        emisorDto.setRfc(comp.getEmisor().getRfc());
        emisorDto.setNombre(Utils.nullToString(comp.getEmisor().getNombre()));
        emisorDto.setRegimenFiscal(comp.getEmisor().getRegimenFiscal());
        //Atributos v3.3
        emisorDto.setCalle("AVENIDA HUINALA KM 2.8 ");
        emisorDto.setNum_ext(" # 400");
        emisorDto.setColonia("Parque Industrial las Americas ");
        emisorDto.setLocalidad("Apodaca ");
        emisorDto.setCiudad("Nuevo Leon ");
        emisorDto.setEstado("Monterey");
        emisorDto.setPais("México");
        emisorDto.setCp("66645");
        comprobanteParseDto.setTelefono("+81 8156 8909273");
        comprobanteParseDto.setEmisor(emisorDto);

        NodeList nodesReceptor = doc.getElementsByTagName("cfdi:Receptor");
        Element elementReceptor = (Element) nodesReceptor.item(0);
        receptorDto.setRfc(comp.getReceptor().getRfc());
        receptorDto.setNombre(Utils.nullToString(comp.getReceptor().getNombre()));
        receptorDto.setUsoCfdi(comp.getReceptor().getUsoCFDI().toString());

        if (comp.getReceptor().getNumRegIdTrib() != null) {
            receptorDto.setNumRegIdTributario(Utils.nullToString(comp.getReceptor().getNumRegIdTrib()));
        }
        if (comp.getReceptor().getResidenciaFiscal() != null) {
            receptorDto.setResidenciaFiscal(mx.gob.sat.cfd4_0.CPais.fromValue(comp.getReceptor().getResidenciaFiscal().toString()));
        }

        comprobanteParseDto.setReceptorDto(receptorDto);

        NodeList nodesConceptos = doc.getElementsByTagName("cfdi:Conceptos");
        Element elementConceptos = (Element) nodesConceptos.item(0);

        NodeList nodesConcepto = elementConceptos.getElementsByTagName("cfdi:Concepto");
        List<DetalleComprobanteXMLParseDto> listDetalleDto = new ArrayList<DetalleComprobanteXMLParseDto>();
        for (int j = 0; j < nodesConcepto.getLength(); j++) {
            Element elementConcepto = (Element) nodesConcepto.item(j);
            DetalleComprobanteXMLParseDto m = new DetalleComprobanteXMLParseDto();
            m.setClaveProdServ(elementConcepto.getAttribute("ClaveProdServ"));
            m.setClaveUnidad(elementConcepto.getAttribute("ClaveUnidad"));
            m.setCantidad(Utils.stringtoBigDecimal(elementConcepto.getAttribute("Cantidad")));
            m.setDescuento(Utils.stringtoBigDecimal((elementConcepto.getAttribute("Descuento"))));
            m.setUnidad_medida(elementConcepto.getAttribute("Unidad"));
            m.setNoIdentificacion(elementConcepto.getAttribute("NoIdentificacion"));
            m.setDescripcion(elementConcepto.getAttribute("Descripcion"));
            m.setValorUnitario(Utils.stringtoBigDecimal(elementConcepto.getAttribute("ValorUnitario")));
            m.setImporte(Utils.stringtoBigDecimal((elementConcepto.getAttribute("Importe"))));

            //IMPUESTOS de cada concepto.
            TrasladoConcepto trasladoConcepto = new TrasladoConcepto();
            RetencionConcepto retencionConcepto = new RetencionConcepto();
            NodeList nodesImp = elementConcepto.getElementsByTagName("cfdi:Impuestos");
            Element elementImp = (Element) nodesImp.item(0);
            ArrayList<TrasladoConcepto> listTraslado = new ArrayList<TrasladoConcepto>();
            //Impuestos traslados.
            if (elementImp != null) {

                //Element elementTras = (Element) nodesTrasl.item(0);
                NodeList nodesTras = elementImp.getElementsByTagName("cfdi:Traslados");
                Element elementTraslados = (Element) nodesTras.item(0);
                if (elementTraslados != null) {

                    NodeList nodesTraslado = elementTraslados.getElementsByTagName("cfdi:Traslado");
                    for (int h = 0; h < nodesTraslado.getLength(); h++) {
                        Node nodereceptorDF = nodesTraslado.item(h);
                        Element elementTraslado = (Element) nodereceptorDF;
                        trasladoConcepto = new TrasladoConcepto();
                        //Element elementTraslado = (Element) nodesTras.item(h);
                        trasladoConcepto.setBase(Utils.stringtoBigDecimal(elementTraslado.getAttribute("Base")));
                        trasladoConcepto.setImpuesto(elementTraslado.getAttribute("Impuesto"));
                        trasladoConcepto.setTipoFactor(elementTraslado.getAttribute("TipoFactor"));
                        trasladoConcepto.setTasaOCuota(elementTraslado.getAttribute("TasaOCuota"));
                        trasladoConcepto.setImporte(Utils.stringtoBigDecimal(elementTraslado.getAttribute("Importe")));

                        listTraslado.add(trasladoConcepto);
                    }
                }
                //Nodo, Impuestos retenidos.
                ArrayList<RetencionConcepto> listRetencion = new ArrayList<RetencionConcepto>();
                NodeList nodesRetenido = elementImp.getElementsByTagName("cfdi:Retenciones");
                Element elementRetenciones = (Element) nodesRetenido.item(0);
                if (elementRetenciones != null) {

                    NodeList nodesRetencion = elementRetenciones.getElementsByTagName("cfdi:Retencion");
                    for (int g = 0; g < nodesRetencion.getLength(); g++) {
                        Node nodeConReten = nodesRetencion.item(g);
                        Element elementConReten = (Element) nodeConReten;
                        retencionConcepto = new RetencionConcepto();
                        //Element elementRet = (Element) nodesRetenido.item(g);
                        retencionConcepto.setBase(Utils.stringtoBigDecimal(XMLParseUtils.getValueToTagName(elementConReten, "Base")));
                        retencionConcepto.setImpuesto(elementConReten.getAttribute("Impuesto"));
                        retencionConcepto.setTipoFactor(elementConReten.getAttribute("TipoFactor"));
                        retencionConcepto.setTasaOCuota(Utils.stringtoBigDecimal(elementConReten.getAttribute("TasaOCuota")));
                        retencionConcepto.setImporte(Utils.stringtoBigDecimal(elementConReten.getAttribute("Importe")));

                        listRetencion.add(retencionConcepto);
                    }
                }

                m.setListTrasladoConcepto(listTraslado);
                m.setListRetencionConcepto(listRetencion);
            }
            listDetalleDto.add(m);

        }

        //}
        comprobanteParseDto.setDetalle(listDetalleDto);

        NodeList nodesTotImpuesto = doc.getElementsByTagName("cfdi:Impuestos");
        Element elementTotImpuesto = (Element) nodesTotImpuesto.item(0);

        if (comp.getImpuestos() != null) {

            //System.out.println("EXISTEN IMPUESTOS AL MAPEAR");
            NodeList nodesTotTraslados = elementTotImpuesto.getElementsByTagName("cfdi:Traslados");
            Element elementTotTraslados = (Element) nodesTotTraslados.item(0);
            if (comp.getImpuestos().getTraslados() != null) {
                BigDecimal totalImpTras = comp.getImpuestos().getTotalImpuestosTrasladados();
                NodeList nodesTotTraslado = elementTotImpuesto.getElementsByTagName("cfdi:Traslado");
                for (int i = 0; i < comp.getImpuestos().getTraslados().getTraslado().size(); i++) {
                    trasladoDto = new TrasladoDto();
                    Element elementTotTraslado = (Element) nodesTotTraslado.item(i);
                    trasladoDto.setImporte(comp.getImpuestos().getTraslados().getTraslado().get(i).getImporte());
                    trasladoDto.setImpuesto(comp.getImpuestos().getTraslados().getTraslado().get(i).getImpuesto());
                    trasladoDto.setTasa(comp.getImpuestos().getTraslados().getTraslado().get(i).getTasaOCuota().toString());
                    trasladoDto.setTipoFactor(comp.getImpuestos().getTraslados().getTraslado().get(i).getTipoFactor().toString());
                    //trasladoDto.setTotalImpuestosTrasladados(Utils.stringtoBigDecimal(XMLParseUtils.getValueToTagName(elementTraslados, "totalImpuestosTrasladados")));
                    trasladoDto.setTotalImpuestosTrasladados(totalImpTras);

                    comprobanteParseDto.getImpuestosTrasladados().add(trasladoDto);
                }
                comprobanteParseDto.setTrasladoDto(trasladoDto);

            }

            NodeList nodesRetenciones = elementTotImpuesto.getElementsByTagName("RetencionTotales");
            if (comp.getImpuestos().getRetenciones() != null) {
                BigDecimal totalImpRet = comp.getImpuestos().getTotalImpuestosRetenidos();

                for (int i = 0; i < comp.getImpuestos().getRetenciones().getRetencion().size(); i++) {
                    Element elementRetenciones = (Element) nodesRetenciones.item(i);
                    retencionDto = new RetencionDto();
                    retencionDto.setImporte(comp.getImpuestos().getRetenciones().getRetencion().get(i).getImporte());
                    retencionDto.setImpuesto(comp.getImpuestos().getRetenciones().getRetencion().get(i).getImpuesto());
//			    		retencionDto.setTasa(comp.getImpuestos().getRetenciones().getRetencion().get(i).get);
                    //retencionDto.setTotalImpuestosRetenidos(Utils.stringtoBigDecimal(XMLParseUtils.getValueToTagName(elementRetenciones, "totalImpuestosRetenidos")));
                    retencionDto.setTotalImpuestosRetenidos(totalImpRet);
                    comprobanteParseDto.getImpuestosRetenidos().add(retencionDto);
                }

                comprobanteParseDto.setRetencionDto(retencionDto);
            }
        }

        NodeList nodesAddenda = doc.getElementsByTagName("cfdi:Addenda");
        Element elementAddenda = (Element) nodesAddenda.item(0);
        if (elementAddenda != null) {
            //Addenda concepto
            NodeList nodesConceptoAdd = elementAddenda.getElementsByTagName("Concepto");
            ArrayList<AddendaConcepto> listAddConcepto = new ArrayList<AddendaConcepto>();
            for (int ds = 0; ds < nodesConceptoAdd.getLength(); ds++) {
                Element elementConceptoAdd = (Element) nodesConceptoAdd.item(ds);
                AddendaConcepto conceptoAdd = new AddendaConcepto();
                conceptoAdd.setNoDelivery(XMLParseUtils.getValueToTagName(elementConceptoAdd, "noDelivery"));
                conceptoAdd.setfDelivery(XMLParseUtils.getValueToTagName(elementConceptoAdd, "fDelivery"));
                conceptoAdd.setpOCliente(XMLParseUtils.getValueToTagName(elementConceptoAdd, "POCliente"));
                conceptoAdd.setMaterialCliente(XMLParseUtils.getValueToTagName(elementConceptoAdd, "materialCliente"));
                conceptoAdd.setASN(XMLParseUtils.getValueToTagName(elementConceptoAdd, "ASN"));
                conceptoAdd.setValorUnitario(XMLParseUtils.getValueToTagName(elementConceptoAdd, "valorUnitario"));

                listAddConcepto.add(conceptoAdd);
            }
//             as.setListSanofiDetalle(listAddDetalle);
            comprobanteParseDto.setAddendaConcepto(listAddConcepto);

            //Addenda Leyendas fiscales.
//            NodeList nodesLeyendaAdd = elementAddenda.getElementsByTagName("Leyendas");
//            Element elementLeyendaAdd = (Element) nodesLeyendaAdd.item(0);
//            if (elementLeyendaAdd != null) {
//                AddendaMabeDetalles addLeyenda = new AddendaMabeDetalles();
//                addLeyenda.setTextoLeyenda(XMLParseUtils.getValueToTagName(elementLeyendaAdd, "textoLeyenda"));
//                addLeyenda.setASCI(XMLParseUtils.getValueToTagName(elementLeyendaAdd, "ASCI"));
//                comprobanteParseDto.setAddendaLeyendasF(addLeyenda);
//            }

            //Addenda para pagos
            NodeList nodesCPagos = elementAddenda.getElementsByTagName("CPagos");
            Element elementCPagos = (Element) nodesCPagos.item(0);
            if (elementCPagos != null) {
                AddendaCPagos addCPagos = new AddendaCPagos();
                addCPagos.setCuenta(XMLParseUtils.getValueToTagName(elementCPagos, "cuenta"));
                addCPagos.setBanco(XMLParseUtils.getValueToTagName(elementCPagos, "banco"));
                comprobanteParseDto.setAddendaCPagos(addCPagos);
            }

            //Addenda Receptor Domicilio.
            NodeList nodesDomicilio = elementAddenda.getElementsByTagName("Domicilio");
            Element elementDomicilio = (Element) nodesDomicilio.item(0);
            if (elementDomicilio != null) {
                AddendaReceptor addDomicilio = new AddendaReceptor();
                addDomicilio.setCalleD(XMLParseUtils.getValueToTagName(elementDomicilio, "calle"));
                addDomicilio.setNoExtD(XMLParseUtils.getValueToTagName(elementDomicilio, "numeroExt"));
                addDomicilio.setNoIntD(XMLParseUtils.getValueToTagName(elementDomicilio, "numeroInt"));
                addDomicilio.setColoniaD(XMLParseUtils.getValueToTagName(elementDomicilio, "colonia"));
                addDomicilio.setMunicipioD(XMLParseUtils.getValueToTagName(elementDomicilio, "municipio"));
                addDomicilio.setEstadoD(XMLParseUtils.getValueToTagName(elementDomicilio, "estado"));
                addDomicilio.setPaisD(XMLParseUtils.getValueToTagName(elementDomicilio, "pais"));
                addDomicilio.setCpD(XMLParseUtils.getValueToTagName(elementDomicilio, "cp"));
                addDomicilio.setTelefonoD(XMLParseUtils.getValueToTagName(elementDomicilio, "telefono"));
                addDomicilio.setCorreoD(XMLParseUtils.getValueToTagName(elementDomicilio, "correo"));
                comprobanteParseDto.setAddendaRecepDomicilio(addDomicilio);
            }

//            //Addenda Receptor Entrega.
//            NodeList nodesEntrega = elementAddenda.getElementsByTagName("Entrega");
//            Element elementEntrega = (Element) nodesEntrega.item(0);
//            if (elementEntrega != null) {
//                AddendaSanofi addEntrega = new AddendaSanofi();
//                addEntrega.setRazonSocialE(XMLParseUtils.getValueToTagName(elementEntrega, "razonSocial"));
//                addEntrega.setCalleE(XMLParseUtils.getValueToTagName(elementEntrega, "calle"));
//                addEntrega.setNoExtE(XMLParseUtils.getValueToTagName(elementEntrega, "numeroExt"));
//                addEntrega.setNoIntE(XMLParseUtils.getValueToTagName(elementEntrega, "numeroInt"));
//                addEntrega.setColoniaE(XMLParseUtils.getValueToTagName(elementEntrega, "colonia"));
//                addEntrega.setMunicipioE(XMLParseUtils.getValueToTagName(elementEntrega, "municipio"));
//                addEntrega.setEstadoE(XMLParseUtils.getValueToTagName(elementEntrega, "estado"));
//                addEntrega.setPaisE(XMLParseUtils.getValueToTagName(elementEntrega, "pais"));
//                addEntrega.setCpE(XMLParseUtils.getValueToTagName(elementEntrega, "cp"));
//                addEntrega.setCondEntrega(XMLParseUtils.getValueToTagName(elementEntrega, "condicionEntrega"));
//                comprobanteParseDto.setAddendaRecepEntrega(addEntrega);
//            }

            //Addenda Extras
//            NodeList nodesExtras = elementAddenda.getElementsByTagName("Extras");
//            Element elementExtras = (Element) nodesExtras.item(0);
//            if (elementExtras != null) {
//                AddendaDetallistaC addendaExtras = new AddendaDetallistaC();
//                addendaExtras.setCondicionesPago(XMLParseUtils.getValueToTagName(elementExtras, "condicionesPago"));
//                addendaExtras.setTextoLibre(XMLParseUtils.getValueToTagName(elementExtras, "textoLibre"));
//                addendaExtras.setPedimento(XMLParseUtils.getValueToTagName(elementExtras, "pedimento"));
//                comprobanteParseDto.setAddendaExtras(addendaExtras);
//            }
        }

        NodeList nodesComplemento = doc.getElementsByTagName("cfdi:Complemento");
        Element elementComplemento = (Element) nodesComplemento.item(0);
        if (elementComplemento != null) {

            //Complemento Leyenda Fiscal.
            NodeList nodesLeyendaFiscal = elementComplemento.getElementsByTagName("leyendasFisc:LeyendasFiscales");
            Element elementLeyendaFiscal = (Element) nodesLeyendaFiscal.item(0);
            if (elementLeyendaFiscal != null) {
                NodeList nodesLeyenda = elementComplemento.getElementsByTagName("leyendasFisc:Leyenda");
                ArrayList<LeyendaFiscal> listLeyenda = new ArrayList<LeyendaFiscal>();
                for (int ley = 0; ley < nodesLeyenda.getLength(); ley++) {
                    Element elementLeyenda = (Element) nodesLeyenda.item(ley);
                    l = new LeyendaFiscal();
                    l.setTextoLeyenda(elementLeyenda.getAttribute("textoLeyenda"));
                    l.setNorma(elementLeyenda.getAttribute("norma"));
                    l.setDisposicionFiscal(elementLeyenda.getAttribute("disposicionFiscal"));
                    listLeyenda.add(l);
                    //System.out.println("Leyenda->"+listLeyenda.get(ley).getLeyendaUno());
                }
                comprobanteParseDto.setListLeyenda(listLeyenda);
            }

            NodeList nodesComercio = elementComplemento.getElementsByTagName("cce11:ComercioExterior");
            Element elementComercio = (Element) nodesComercio.item(0);
            if (elementComercio != null) {//comercio exterior
                ComercioExterior comext = new ComercioExterior();

                String tipoCUSD = elementComercio.getAttribute("TipoCambioUSD");
                String totUSD = elementComercio.getAttribute("TotalUSD");
                BigDecimal tipoCambioUSD = new BigDecimal(tipoCUSD);
                BigDecimal totalUSD = new BigDecimal(totUSD);
                comext.setVersion(elementComercio.getAttribute("Version"));
                comext.setTipoOperacion(elementComercio.getAttribute("TipoOperacion"));
                comext.setClaveDePedimento(elementComercio.getAttribute("ClaveDePedimento"));
                comext.setCertificadoOrigen(elementComercio.getAttribute("CertificadoOrigen"));
                comext.setIncoterm(elementComercio.getAttribute("Incoterm"));
                comext.setSubDivision(elementComercio.getAttribute("Subdivision"));
                comext.setTipoCambioUSD(tipoCambioUSD);
                comext.setTotalUSD(totalUSD);

                NodeList nodesComercioEmisor = doc.getElementsByTagName("cce11:Emisor");
                Element elementComercioEmisor = (Element) nodesComercioEmisor.item(0);
                if (elementComercioEmisor != null) {//comercio exterior
                    NodeList nodesEmisorDir = elementComercioEmisor.getElementsByTagName("cce11:Domicilio");
                    Element elementEmisorDir = (Element) nodesEmisorDir.item(0);
                    EmisorComercioExt emisorComercio = new EmisorComercioExt();

                    emisorComercio.setCalle(elementEmisorDir.getAttribute("Calle"));
                    emisorComercio.setNumeroExterior(elementEmisorDir.getAttribute("NumeroExterior"));
                    emisorComercio.setNumeroInterior(elementEmisorDir.getAttribute("NumeroInterior"));
                    emisorComercio.setColonia(elementEmisorDir.getAttribute("Colonia"));
                    emisorComercio.setLocalidad(elementEmisorDir.getAttribute("Localidad"));
                    emisorComercio.setMunicipio(elementEmisorDir.getAttribute("Municipio"));
                    emisorComercio.setEstado(elementEmisorDir.getAttribute("Estado"));
                    emisorComercio.setPais(elementEmisorDir.getAttribute("Pais"));
                    emisorComercio.setCodigoPostal(elementEmisorDir.getAttribute("CodigoPostal"));
                    comext.setEmisorComercio(emisorComercio);
                    // comprobanteParseDto.setEmisorComExt(emisorComercio);
                }

                NodeList nodesComercioReceptor = doc.getElementsByTagName("cce11:Receptor");
                Element elementComercioReceptor = (Element) nodesComercioReceptor.item(0);
                if (elementComercioReceptor != null) {//comercio exterior
                    NodeList nodesReceptorDir = elementComercioReceptor.getElementsByTagName("cce11:Domicilio");
                    Element elementReceptorDir = (Element) nodesReceptorDir.item(0);
                    ReceptorComercioExt receptorComercio = new ReceptorComercioExt();

                    receptorComercio.setCalle(elementReceptorDir.getAttribute("Calle"));
                    receptorComercio.setNumExterior(elementReceptorDir.getAttribute("NumeroExterior"));
                    receptorComercio.setNumInterior(elementReceptorDir.getAttribute("NumeroInterior"));
                    receptorComercio.setColonia(elementReceptorDir.getAttribute("Colonia"));
                    receptorComercio.setMunicipio(elementReceptorDir.getAttribute("Municipio"));
                    receptorComercio.setEstado(elementReceptorDir.getAttribute("Estado"));
                    receptorComercio.setPais(elementReceptorDir.getAttribute("Pais"));
                    receptorComercio.setCodigoPostal(elementReceptorDir.getAttribute("CodigoPostal"));
                    comext.setReceptorComercio(receptorComercio);
                    //comprobanteParseDto.setReceptorComExt(receptorComercio);
                }

                NodeList nodesComercioMercancias = doc.getElementsByTagName("cce11:Mercancias");
                Element elementComercioMercancias = (Element) nodesComercioMercancias.item(0);
                ArrayList<MercanciaComercion> listM = new ArrayList<MercanciaComercion>();
                ArrayList<DescripcionesEspMercanciaComerExt> listDesMer = null;
                if (elementComercioMercancias != null) {
                    NodeList nodesComercioMercancia = elementComercioMercancias.getElementsByTagName("cce11:Mercancia");
                    for (int i = 0; i < nodesComercioMercancia.getLength(); i++) {
                        listDesMer = new ArrayList<DescripcionesEspMercanciaComerExt>();
                        Element elementComMercancia = (Element) nodesComercioMercancia.item(i);
                        MercanciaComercion mercancia = new MercanciaComercion();
                        String canAd = elementComMercancia.getAttribute("CantidadAduana");
                        String vUA = elementComMercancia.getAttribute("ValorUnitarioAduana");
                        String vD = elementComMercancia.getAttribute("ValorDolares");
                        BigDecimal cantidadAduana = new BigDecimal(canAd);
                        BigDecimal valorUAdiana = new BigDecimal(vUA);
                        BigDecimal valorDolar = new BigDecimal(vD);

                        mercancia.setNoIdentificacion(elementComMercancia.getAttribute("NoIdentificacion"));
                        if (elementComMercancia.getAttribute("FraccionArancelaria") != null && !elementComMercancia.getAttribute("FraccionArancelaria").equals("")) {
                            mercancia.setFraccionAracelaria(elementComMercancia.getAttribute("FraccionArancelaria"));
                        }
                        mercancia.setCantidadAduana(cantidadAduana);
                        mercancia.setUnidadAduana(elementComMercancia.getAttribute("UnidadAduana"));
                        mercancia.setValorUnitarioAduana(valorUAdiana);
                        mercancia.setValorDolares(valorDolar);

                        NodeList nodesDescEspecifica = elementComMercancia.getElementsByTagName("cce11:DescripcionesEspecificas");
                        if (nodesDescEspecifica != null) {
                            for (int j = 0; j < nodesDescEspecifica.getLength(); j++) {
                                Element elementDescEspecificas = (Element) nodesDescEspecifica.item(j);
                                descM = new DescripcionesEspMercanciaComerExt();

                                descM.setMarca(elementDescEspecificas.getAttribute("Marca"));
                                descM.setSubModelo(elementDescEspecificas.getAttribute("SubModelo"));
                                descM.setModelo(elementDescEspecificas.getAttribute("Modelo"));
                                listDesMer.add(descM);
                            }
                            mercancia.setDescEspecificas(listDesMer);
                        }

                        listM.add(mercancia);
                    }
                    //comprobanteParseDto.setMercComercioExt(listM);
                    comext.setListMercancia(listM);
                }

                comprobanteParseDto.setComercioExt(comext);
            }

            //Complemento Recepción de Pagos.
            NodeList nodesPagos = elementComplemento.getElementsByTagName("pago10:Pagos");
            Element elementPagos = (Element) nodesPagos.item(0);
            if (elementPagos != null) {
                NodeList nodesPago = elementPagos.getElementsByTagName("pago10:Pago");
                ArrayList<Pagos> listPagos = new ArrayList<Pagos>();
                for (int pago = 0; pago < nodesPago.getLength(); pago++) {
                    Element elementPago = (Element) nodesPago.item(pago);
                    Pagos pagos = new Pagos();
                    if (elementPago != null) {

                        pagos.setFechaPago(Utils.stringToDatePatern(elementPago.getAttribute("FechaPago").trim(), "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss"));
                        pagos.setFechaPago_pdf(elementPago.getAttribute("FechaPago").trim());
                        pagos.setFormaDePagoP(elementPago.getAttribute("FormaDePagoP").trim());
                        pagos.setModenaP(elementPago.getAttribute("MonedaP").trim());
                        pagos.setTipoCambioP(Utils.stringtoBigDecimal(elementPago.getAttribute("TipoCambioP").trim()));
                        pagos.setMonto(Utils.stringtoBigDecimal(elementPago.getAttribute("Monto").trim()));
                        pagos.setNumOperacion(elementPago.getAttribute("NumOperacion").trim());
                        pagos.setRfcEmisorCtaOrd(elementPago.getAttribute("RfcEmisorCtaOrd").trim());
                        pagos.setNomBancoOrdExt(elementPago.getAttribute("NomBancoOrdExt").trim());
                        pagos.setCtaOrdenante(elementPago.getAttribute("CtaOrdenante").trim());
                        pagos.setRfcEmisorCtaBen(elementPago.getAttribute("RfcEmisorCtaBen").trim());
                        pagos.setCtaBeneficiario(elementPago.getAttribute("CtaBeneficiario").trim());
                        pagos.setTipoCadPago(elementPago.getAttribute("TipoCadPago").trim());
                        pagos.setCertPago(elementPago.getAttribute("CertPago").trim());
                        pagos.setCadPago(elementPago.getAttribute("CadPago").trim());
                        pagos.setSelloPago(elementPago.getAttribute("SelloPago").trim());
                        //System.out.println("CadPago---> "+pagos.getCadPago());
                        //Dcumentos Relacionado, de pagos.
                        NodeList nodesPagosDoct = elementPago.getElementsByTagName("pago10:DoctoRelacionado");
                        ArrayList<DoctoRelacionado> listDoct = new ArrayList<DoctoRelacionado>();

                        for (int doct = 0; doct < nodesPagosDoct.getLength(); doct++) {
                            Element elementPagosDoct = (Element) nodesPagosDoct.item(doct);
                            DoctoRelacionado doctR = new DoctoRelacionado();
                            doctR.setIdDocumento(elementPagosDoct.getAttribute("IdDocumento").trim());
                            doctR.setSerie(elementPagosDoct.getAttribute("Serie").trim());
                            doctR.setFolio(elementPagosDoct.getAttribute("Folio").trim());
                            doctR.setMonedaDr(elementPagosDoct.getAttribute("MonedaDR").trim());
                            doctR.setTipoCambioDr(Utils.stringtoBigDecimal(elementPagosDoct.getAttribute("TipoCambioDR").trim()));
                            doctR.setMetodoDePagoDr(elementPagosDoct.getAttribute("MetodoDePagoDR").trim());
                            doctR.setNumParcialidad(new BigInteger(elementPagosDoct.getAttribute("NumParcialidad")));
                            doctR.setImpSaldoAnt(Utils.stringtoBigDecimal(elementPagosDoct.getAttribute("ImpSaldoAnt").trim()));
                            doctR.setImpPagado(Utils.stringtoBigDecimal(elementPagosDoct.getAttribute("ImpPagado").trim()));
                            doctR.setImpoSaldoInsoluto(Utils.stringtoBigDecimal(elementPagosDoct.getAttribute("ImpSaldoInsoluto").trim()));
                            listDoct.add(doctR);
                        }
                        pagos.setListDocRelacionado(listDoct);
                    }
                    listPagos.add(pagos);
                }
                comprobanteParseDto.setListPagos(listPagos);
            }

        }

        responseXMLParseDto.setComprobanteParseDto(comprobanteParseDto);

        return responseXMLParseDto;
    }

    public Comprobante generaCFDI(BeanGeneral responseXMLParseDto) throws Exception {
        ObjectFactory of = new ObjectFactory();
        Comprobante comp = of.createComprobante();
        DecimalFormat df = new DecimalFormat("#.00");
        df.setRoundingMode(RoundingMode.DOWN);
        DecimalFormat df3 = new DecimalFormat("#.000");
        DecimalFormat df4 = new DecimalFormat("#.0000");
        if (responseXMLParseDto != null) {
            comp.setVersion(responseXMLParseDto.getComprobante().getVersion());

            //Conversión de fecha
//            GregorianCalendar c = new GregorianCalendar();
//            c.setTime(responseXMLParseDto.getComprobante().getFecha());
//            //System.out.println(respxml.getFecha());
//            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            String f = responseXMLParseDto.getComprobante().getFecha().toString();
            String[] fec = f.split("\\.");
            String valor = fec[0];
            XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(valor);
            comp.setFecha(date);
            X509Certificate cert = null;
			try {
				InputStream is = new FileInputStream(new File(
				Utils.getParamValue("path_files_key_cert") + responseXMLParseDto.getComprobante().getEmisor().getRfc() + ".cer"));
				CertificateFactory cf = CertificateFactory.getInstance("X.509");
				cert = (X509Certificate) cf.generateCertificate(is);
				String nc = new String(cert.getSerialNumber().toByteArray());
				cert.checkValidity();
				byte[] bytes = cert.getEncoded();
				org.apache.commons.codec.binary.Base64 b64 = new org.apache.commons.codec.binary.Base64(-1);
				String certStr = b64.encodeToString(bytes);
				comp.setCertificado(certStr);
				comp.setNoCertificado(nc);
				comp.setSello("");
			} catch (Exception e) {
				e.printStackTrace();
			}
            if (!Utils.nullToString(responseXMLParseDto.getComprobante().getSerie()).equals("")) {
                comp.setSerie(responseXMLParseDto.getComprobante().getSerie());
            }
            if (!Utils.nullToString(responseXMLParseDto.getComprobante().getFolio()).equals("")) {
                comp.setFolio(responseXMLParseDto.getComprobante().getFolio());
            }
            //sello
//            System.out.println("TipoComprobante--> " + responseXMLParseDto.getComprobante().getTipoDeComprobante());
            comp.setTipoDeComprobante(responseXMLParseDto.getComprobante().getTipoDeComprobante());
            if (responseXMLParseDto.getComprobante().getFormaPago() != null && !(responseXMLParseDto.getComprobante().getFormaPago()).equals("")) {
                comp.setFormaPago(responseXMLParseDto.getComprobante().getFormaPago());
            }

            //nocertificado
            //certificado
            if (!Utils.nullToString(responseXMLParseDto.getComprobante().getCondicionesDePago()).equals("")) {
                comp.setCondicionesDePago(responseXMLParseDto.getComprobante().getCondicionesDePago());
            }
            comp.setSubTotal(responseXMLParseDto.getComprobante().getSubTotal());
            if (responseXMLParseDto.getComprobante().getTipoCambio() != null) {
                if (!Utils.nullToString(responseXMLParseDto.getComprobante().getTipoCambio().toString()).equals("0.0")) {
                    comp.setTipoCambio(responseXMLParseDto.getComprobante().getTipoCambio());
                }
            }

            //comp.setTipoCambio(responseXMLParseDto.getComprobanteParseDto().getTipoCambio());
            if (!Utils.nullToString(responseXMLParseDto.getComprobante().getMoneda().toString()).equals("")) {
                comp.setMoneda(mx.gob.sat.cfd4_0.CMoneda.fromValue(responseXMLParseDto.getComprobante().getMoneda().toString()));
            }
            comp.setTotal(responseXMLParseDto.getComprobante().getTotal());
            if (responseXMLParseDto.getComprobante().getMetodoPago() != null && !responseXMLParseDto.getComprobante().getMetodoPago().equals("")) {
                comp.setMetodoPago(mx.gob.sat.cfd4_0.CMetodoPago.fromValue(responseXMLParseDto.getComprobante().getMetodoPago().toString()));
            }
            comp.setExportacion(responseXMLParseDto.getComprobante().getExportacion());
            comp.setLugarExpedicion(responseXMLParseDto.getComprobante().getLugarExpedicion());
            if (responseXMLParseDto.getComprobante().getDescuento() != null && !(responseXMLParseDto.getComprobante().getDescuento()).equals("")) {
                if (!(responseXMLParseDto.getComprobante().getDescuento().toString()).equals("0.00")) {
                    if (!(responseXMLParseDto.getComprobante().getDescuento().toString()).equals("0.0")) {
                        comp.setDescuento(responseXMLParseDto.getComprobante().getDescuento());
                    }
                }
            }
            if (!Utils.nullToString(responseXMLParseDto.getComprobante().getCondicionesDePago()).equals("")) {
                comp.setCondicionesDePago(responseXMLParseDto.getComprobante().getCondicionesDePago());
            }

          
            if (responseXMLParseDto.getComprobante().getCfdiRelacionados().size() > 0) {
                  //Cdfi relacionados, del comprobante.
            CfdiRelacionados crelaciondo = of.createComprobanteCfdiRelacionados();
            List<CfdiRelacionado> list = crelaciondo.getCfdiRelacionado();
            //System.out.println("Tamaño al comp-> "+respxml.getListRelacionados().size());
                for (int j = 0; j < responseXMLParseDto.getComprobante().getCfdiRelacionados().size(); j++) {
                    
                
                crelaciondo.setTipoRelacion(responseXMLParseDto.getComprobante().getCfdiRelacionados().get(j).getTipoRelacion());
                for (int i = 0; i < responseXMLParseDto.getComprobante().getCfdiRelacionados().get(j).getCfdiRelacionado().size(); i++) {

                    CfdiRelacionado re = of.createComprobanteCfdiRelacionadosCfdiRelacionado();
                    re.setUUID(responseXMLParseDto.getComprobante().getCfdiRelacionados().get(j).getCfdiRelacionado().get(i).getUUID());
                    //System.out.println("ingreso -> "+i);
                    list.add(re);
                }
                crelaciondo.getCfdiRelacionado();
                }
                comp.getCfdiRelacionados().add(crelaciondo);
            }

            Emisor emisor = of.createComprobanteEmisor();
            emisor.setNombre(responseXMLParseDto.getComprobante().getEmisor().getNombre());
            emisor.setRfc(responseXMLParseDto.getComprobante().getEmisor().getRfc());
            emisor.setRegimenFiscal(responseXMLParseDto.getComprobante().getEmisor().getRegimenFiscal());

            Receptor receptor = of.createComprobanteReceptor();
            receptor.setRfc(responseXMLParseDto.getComprobante().getReceptor().getRfc());
            receptor.setRegimenFiscalReceptor(responseXMLParseDto.getReceptor().getRegimenFiscalReceptor());
            receptor.setDomicilioFiscalReceptor(responseXMLParseDto.getReceptor().getCp());
            if (!Utils.nullToString(responseXMLParseDto.getComprobante().getReceptor().getNombre()).equals("")) {
                receptor.setNombre(responseXMLParseDto.getComprobante().getReceptor().getNombre());
            }
            String uso = responseXMLParseDto.getComprobante().getReceptor().getUsoCFDI().toString();
            uso = uso.replace("_", "");
//				String usocfdi=
            receptor.setUsoCFDI(mx.gob.sat.cfd4_0.CUsoCFDI.fromValue(uso));
            if (responseXMLParseDto.getComprobante().getReceptor().getNumRegIdTrib() != null) {
                receptor.setNumRegIdTrib(responseXMLParseDto.getComprobante().getReceptor().getNumRegIdTrib());
            }
            if (responseXMLParseDto.getComprobante().getReceptor().getResidenciaFiscal() != null) {
                receptor.setResidenciaFiscal(responseXMLParseDto.getComprobante().getReceptor().getResidenciaFiscal());
            }
                Comprobante.InformacionGlobal infog = null;
            if (responseXMLParseDto.getComprobante().getInformacionGlobal() != null) {
                infog = of.createComprobanteInformacionGlobal();
                 infog.setPeriodicidad(responseXMLParseDto.getComprobante().getInformacionGlobal().getPeriodicidad());
                 infog.setMeses(responseXMLParseDto.getComprobante().getInformacionGlobal().getMeses());
                 infog.setAño(responseXMLParseDto.getComprobante().getInformacionGlobal().getAño());    
            }
           
            
            
            //Objetos, de conceptos del comprobante.
            Conceptos cps = of.createComprobanteConceptos();
            List<Concepto> listc = cps.getConcepto();

//            List<DetalleComprobanteXMLParseDto> detalleComprobanteXMLParseDtos = responseXMLParseDto.getComprobante().getConceptos().getConcepto();
            //TInformacionAduanera iad=of.createTInformacionAduanera();
            //iad.set
            for (int i = 0; i < responseXMLParseDto.getComprobante().getConceptos().getConcepto().size(); i++) {
                Concepto c1 = of.createComprobanteConceptosConcepto();
                if (hasMoreThanOneDecimalPlace(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getCantidad())) { 
                    BigDecimal truncated = responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getCantidad().setScale(2, RoundingMode.DOWN);
                    c1.setCantidad(truncated);
                } else { 
                    c1.setCantidad(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getCantidad());
                }
//                c1.setCantidad(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getCantidad());
                c1.setClaveProdServ(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getClaveProdServ());
                if (!(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getNoIdentificacion()).equals("")) {
                    c1.setNoIdentificacion(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getNoIdentificacion());
                }
                c1.setDescripcion(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getDescripcion());
                if (responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getDescuento() != null && !(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getDescuento().toString()).equals("")) {
                    if (!(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getDescuento().toString()).equals("0.0")) {
                        if (!(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getDescuento().toString()).equals("0.00")) {
                            c1.setDescuento(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getDescuento());
                        }
                    }
                }
                c1.setImporte(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImporte());
                c1.setValorUnitario(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getValorUnitario());
                if (!(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getUnidad()).equals("")) {
                    c1.setUnidad(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getUnidad());
                }
                c1.setClaveUnidad(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getClaveUnidad());
                
                c1.setObjetoImp(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getObjetoImp());

                //IMPUESTOS de cada concepto
                Impuestos compImp = of.createComprobanteConceptosConceptoImpuestos();
                Traslados compImpTraslados = of.createComprobanteConceptosConceptoImpuestosTraslados();
                Retenciones compImpRets = of.createComprobanteConceptosConceptoImpuestosRetenciones();

                List<Traslado> listtras = compImpTraslados.getTraslado();
                List<Retencion> listr = compImpRets.getRetencion();
                if (responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos() != null) {
                if (responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados() != null) {
                    for (int t = 0; t < responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().size(); t++) {
                        Traslado compImpTraslado = of.createComprobanteConceptosConceptoImpuestosTrasladosTraslado();
                        compImpTraslado.setBase(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().get(t).getBase());
                        String primeraLetraT = responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().get(t).getTipoFactor().toString().substring(0, 1).toUpperCase();
                        // Obtener el resto de la cadena, intacta
                        String restoDeLaCadenaT = responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().get(t).getTipoFactor().toString().substring(1).toLowerCase();
                        // Concatenar
                        String primeraMinusculaT = primeraLetraT + restoDeLaCadenaT;
                        compImpTraslado.setTipoFactor(mx.gob.sat.cfd4_0.CTipoFactor.fromValue(primeraMinusculaT));
                        compImpTraslado.setImpuesto(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().get(t).getImpuesto());
                        if (!(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().get(t).getTasaOCuota()).equals("")) {
                            compImpTraslado.setTasaOCuota(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().get(t).getTasaOCuota());
                        }
                        if (!(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().get(t).getImporte()).equals("")) {
                            compImpTraslado.setImporte(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getTraslados().getTraslado().get(t).getImporte());
                        }

                        listtras.add(compImpTraslado);
                        //concepto.getImpuestos().getTraslados().getTraslado().addAll(listtras);
                        compImp.setTraslados(compImpTraslados);
                        c1.setImpuestos(compImp);
                    }
                }

                if (responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getRetenciones() != null) {
                    for (int r = 0; r < responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getRetenciones().getRetencion().size(); r++) {
                        Retencion compImpRetencion = of.createComprobanteConceptosConceptoImpuestosRetencionesRetencion();
                        compImpRetencion.setBase(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getRetenciones().getRetencion().get(r).getBase());
                        compImpRetencion.setImporte(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getRetenciones().getRetencion().get(r).getImporte());
                        compImpRetencion.setImpuesto(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getRetenciones().getRetencion().get(r).getImpuesto());
                        compImpRetencion.setTasaOCuota(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getRetenciones().getRetencion().get(r).getTasaOCuota());
                        String primeraLetraR = responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getRetenciones().getRetencion().get(r).getTipoFactor().toString().substring(0, 1).toUpperCase();
                        // Obtener el resto de la cadena, intacta
                        String restoDeLaCadenaR = responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getImpuestos().getRetenciones().getRetencion().get(r).getTipoFactor().toString().substring(1).toLowerCase();
                        // Concatenar
                        String primeraMinusculaR = primeraLetraR + restoDeLaCadenaR;
                        compImpRetencion.setTipoFactor(mx.gob.sat.cfd4_0.CTipoFactor.fromValue(primeraMinusculaR));
                        listr.add(compImpRetencion);
                        //concepto.getImpuestos().getRetenciones().getRetencion().addAll(listr);
                        compImp.setRetenciones(compImpRets);
                        c1.setImpuestos(compImp);
                    }
                }
                
            }

                listc.add(c1);

            }
            //Version 3.3, nullos
            //receptor.setDomicilio(uf);

            //Objetos, de impuesto del comprobante.
            Comprobante.Impuestos imps = of.createComprobanteImpuestos();

            if (responseXMLParseDto.getComprobante().getImpuestos() != null) {

                if (responseXMLParseDto.getComprobante().getImpuestos().getTraslados() != null) {
                    //System.out.println("Si hay traslado");
                    Comprobante.Impuestos.Traslados trs = of.createComprobanteImpuestosTraslados();
                    List<Comprobante.Impuestos.Traslados.Traslado> listras = trs.getTraslado();
                    for (int i = 0; i < responseXMLParseDto.getComprobante().getImpuestos().getTraslados().getTraslado().size(); i++) {
                        Comprobante.Impuestos.Traslados.Traslado t1 = of.createComprobanteImpuestosTrasladosTraslado();
                        t1.setBase(responseXMLParseDto.getComprobante().getImpuestos().getTraslados().getTraslado().get(i).getBase());
                        t1.setImporte(responseXMLParseDto.getComprobante().getImpuestos().getTraslados().getTraslado().get(i).getImporte());
                        t1.setImpuesto(responseXMLParseDto.getComprobante().getImpuestos().getTraslados().getTraslado().get(i).getImpuesto());
                        // Obtener primera letra y convertirla a mayúscula
                        String primeraLetra = responseXMLParseDto.getComprobante().getImpuestos().getTraslados().getTraslado().get(i).getTipoFactor().toString().substring(0, 1).toUpperCase();
                        // Obtener el resto de la cadena, intacta
                        String restoDeLaCadena = responseXMLParseDto.getComprobante().getImpuestos().getTraslados().getTraslado().get(i).getTipoFactor().toString().substring(1).toLowerCase();
                        // Concatenar
                        String primeraMinuscula = primeraLetra + restoDeLaCadena;
                        t1.setTasaOCuota(responseXMLParseDto.getComprobante().getImpuestos().getTraslados().getTraslado().get(i).getTasaOCuota());
                        t1.setTipoFactor(mx.gob.sat.cfd4_0.CTipoFactor.fromValue(primeraMinuscula));
                        listras.add(t1);
                        imps.setTotalImpuestosTrasladados(new BigDecimal(df.format(responseXMLParseDto.getComprobante().getImpuestos().getTotalImpuestosTrasladados())));
                    }
                    imps.setTraslados(trs);
                }

                if (responseXMLParseDto.getComprobante().getImpuestos().getTotalImpuestosRetenidos() != null) {
                    //	System.out.println("Si hay retencion");
                    Boolean entro_retencion = false;
                    Comprobante.Impuestos.Retenciones rets = of.createComprobanteImpuestosRetenciones();
                    List<Comprobante.Impuestos.Retenciones.Retencion> listr = rets.getRetencion();
                    for (int i = 0; i < responseXMLParseDto.getComprobante().getImpuestos().getRetenciones().getRetencion().size(); i++) {
                        Comprobante.Impuestos.Retenciones.Retencion r1 = of.createComprobanteImpuestosRetencionesRetencion();
                        r1.setImporte(responseXMLParseDto.getComprobante().getImpuestos().getRetenciones().getRetencion().get(i).getImporte());
                        r1.setImpuesto(responseXMLParseDto.getComprobante().getImpuestos().getRetenciones().getRetencion().get(i).getImpuesto());

                        //r1.setTasa(new BigDecimal("0.00"));
                        listr.add(r1);
                        imps.setTotalImpuestosRetenidos(new BigDecimal(df.format(responseXMLParseDto.getComprobante().getImpuestos().getTotalImpuestosRetenidos())));
                        entro_retencion = true;
                    }

                    if (entro_retencion) {
                        imps.setRetenciones(rets);
                    }
                }
            }
            
            Complemento complemento = of.createComprobanteComplemento();
             //Complemento Detallistaa.
            
            mx.gob.sat.detallista.ObjectFactory objDetallista = new mx.gob.sat.detallista.ObjectFactory();
            mx.gob.sat.detallista.Detallista detallista = objDetallista.createDetallista();
            if (responseXMLParseDto.getDetallistaCabecero() != null) {
                detallista.setContentVersion("1.3.1");
                detallista.setDocumentStatus(responseXMLParseDto.getDetallistaCabecero().getDocumentStatus());
                detallista.setDocumentStructureVersion("AMC8.1");
                detallista.setType("SimpleInvoiceType");

                String entityType = "";
                if (responseXMLParseDto.getComprobante().getTipoDeComprobante().toString().equalsIgnoreCase("I")) {
                    entityType = "INVOICE";
                }else if(responseXMLParseDto.getComprobante().getTipoDeComprobante().toString().equalsIgnoreCase("E")){
                    entityType = "CREDIT_NOTE";
                }
                
                mx.gob.sat.detallista.Detallista.RequestForPaymentIdentification requestForPaymentIdentification = new mx.gob.sat.detallista.Detallista.RequestForPaymentIdentification();
                requestForPaymentIdentification.setEntityType(entityType);
                detallista.setRequestForPaymentIdentification(requestForPaymentIdentification);

                mx.gob.sat.detallista.Detallista.SpecialInstruction specialInstruction = objDetallista
                        .createDetallistaSpecialInstruction();

                JAXBElement<String> valorTexto = objDetallista
                        .createDetallistaSpecialInstructionText(responseXMLParseDto.getDetallistaCabecero().getImporteLetra());

                specialInstruction.setCode("ZZZ");
                specialInstruction.getContent().add(valorTexto);
                detallista.getSpecialInstruction().add(specialInstruction);

                mx.gob.sat.detallista.Detallista.OrderIdentification orderIdentification = new mx.gob.sat.detallista.Detallista.OrderIdentification();
                mx.gob.sat.detallista.Detallista.OrderIdentification.ReferenceIdentification orderIdentificationReferenceIdentification = new mx.gob.sat.detallista.Detallista.OrderIdentification.ReferenceIdentification();
                orderIdentificationReferenceIdentification.setType("ON");
                orderIdentificationReferenceIdentification
                        .setValue(responseXMLParseDto.getDetallistaCabecero().getOrdenCompra());
                orderIdentification.getReferenceIdentification().add(orderIdentificationReferenceIdentification);
                detallista.setOrderIdentification(orderIdentification);

                mx.gob.sat.detallista.Detallista.AdditionalInformation additionalInformation = new mx.gob.sat.detallista.Detallista.AdditionalInformation();
                mx.gob.sat.detallista.Detallista.AdditionalInformation.ReferenceIdentification referenceIdentification = new mx.gob.sat.detallista.Detallista.AdditionalInformation.ReferenceIdentification();

                referenceIdentification.setType("ACE");
                referenceIdentification.setValue(responseXMLParseDto.getDetallistaCabecero().getNumeroRemision());
                //referenceIdentification.setType(ComplementoDetallista.getAdditionalInformationType());
                //referenceIdentification.setValue(ComplementoDetallista.getAdditionalInformation());
                additionalInformation.getReferenceIdentification().add(referenceIdentification);

                detallista.setAdditionalInformation(additionalInformation);

                mx.gob.sat.detallista.Detallista.DeliveryNote deliVeryNote = objDetallista
                        .createDetallistaDeliveryNote();
                deliVeryNote.setReferenceDate(Utils.stringXMLGregorian(responseXMLParseDto.getDetallistaCabecero().getFechaContraRecibo()));
                deliVeryNote.getReferenceIdentification()
                        .add(responseXMLParseDto.getDetallistaCabecero().getContraRecibo());
                detallista.setDeliveryNote(deliVeryNote);

                mx.gob.sat.detallista.Detallista.Buyer buyer = new mx.gob.sat.detallista.Detallista.Buyer();
                buyer.setGln(responseXMLParseDto.getDetallistaCabecero().getGlnComprador());
                mx.gob.sat.detallista.Detallista.Buyer.ContactInformation.PersonOrDepartmentName personOrDepartmentName = objDetallista
                        .createDetallistaBuyerContactInformationPersonOrDepartmentName();
                personOrDepartmentName.setText(responseXMLParseDto.getDetallistaCabecero().getContactoCompras());
                mx.gob.sat.detallista.Detallista.Buyer.ContactInformation contactInformation = objDetallista
                        .createDetallistaBuyerContactInformation();
                contactInformation.setPersonOrDepartmentName(personOrDepartmentName);
                buyer.setContactInformation(contactInformation);
                detallista.setBuyer(buyer);

                mx.gob.sat.detallista.Detallista.Seller seller = new mx.gob.sat.detallista.Detallista.Seller();
                mx.gob.sat.detallista.Detallista.Seller.AlternatePartyIdentification alternatePartyIdentificationSeller = new mx.gob.sat.detallista.Detallista.Seller.AlternatePartyIdentification();
                seller.setGln(responseXMLParseDto.getDetallistaCabecero().getGlnProveedor());
                alternatePartyIdentificationSeller.setType("SELLER_ASSIGNED_IDENTIFIER_FOR_A_PARTY");
                alternatePartyIdentificationSeller.setValue(responseXMLParseDto.getDetallistaCabecero().getIdentificacionSecundaria());
                seller.setAlternatePartyIdentification(alternatePartyIdentificationSeller);
                detallista.setSeller(seller);

                mx.gob.sat.detallista.Detallista.AllowanceCharge allowanceCharge = objDetallista
                        .createDetallistaAllowanceCharge();

                allowanceCharge.setSettlementType("OFF_INVOICE");
                allowanceCharge.setAllowanceChargeType("ALLOWANCE_GLOBAL");
                allowanceCharge.setSpecialServicesType("AJ");
                mx.gob.sat.detallista.Detallista.AllowanceCharge.MonetaryAmountOrPercentage monetaryAmountOrPercentage = objDetallista
                        .createDetallistaAllowanceChargeMonetaryAmountOrPercentage();
                mx.gob.sat.detallista.Detallista.AllowanceCharge.MonetaryAmountOrPercentage.Rate rate = objDetallista
                        .createDetallistaAllowanceChargeMonetaryAmountOrPercentageRate();
                rate.setBase("INVOICE_VALUE");
                rate.setPercentage(new BigDecimal(responseXMLParseDto.getDetallistaCabecero().getPorcentaje()));
                monetaryAmountOrPercentage.setRate(rate);
                allowanceCharge.setMonetaryAmountOrPercentage(monetaryAmountOrPercentage);
                detallista.getAllowanceCharge().add(allowanceCharge);
                int contador = 1 ;
                
                for (int i = 0; i < responseXMLParseDto.getDetallistaItems().size(); i++) {
                    String number = "";
                    number = String.valueOf(contador);
                    mx.gob.sat.detallista.Detallista.LineItem lineItem = objDetallista
                            .createDetallistaLineItem();
                    lineItem.setType(("ALLOWANCE_GLOBAL"));
                    lineItem.setNumber(new BigInteger(number));

                    mx.gob.sat.detallista.Detallista.LineItem.TradeItemIdentification tradeItemIdentification = objDetallista
                            .createDetallistaLineItemTradeItemIdentification();
                    tradeItemIdentification.setGtin(responseXMLParseDto.getDetallistaItems().get(i).getEan());
                    lineItem.setTradeItemIdentification(tradeItemIdentification);

                    if (responseXMLParseDto.getDetallistaItems().get(i).getTotalLineAmount() != null) {

                        mx.gob.sat.detallista.Detallista.LineItem.AlternateTradeItemIdentification alternateTradeItemIdentification = objDetallista
                                .createDetallistaLineItemAlternateTradeItemIdentification();
                        alternateTradeItemIdentification.setType("BUYER_ASSIGNED");
                        alternateTradeItemIdentification.setValue(responseXMLParseDto.getDetallistaItems().get(i).getSku());
                        lineItem.getAlternateTradeItemIdentification().add(alternateTradeItemIdentification);

                    }

                    mx.gob.sat.detallista.Detallista.LineItem.TradeItemDescriptionInformation tradeItemDescriptionInformation = objDetallista
                            .createDetallistaLineItemTradeItemDescriptionInformation();
                    tradeItemDescriptionInformation.setLanguage("ES");

                    tradeItemDescriptionInformation
                            .setLongText(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getDescripcion());
                    lineItem.setTradeItemDescriptionInformation(tradeItemDescriptionInformation);

                    mx.gob.sat.detallista.Detallista.LineItem.InvoicedQuantity invoicedQuantity = objDetallista
                            .createDetallistaLineItemInvoicedQuantity();
                    invoicedQuantity.setUnitOfMeasure(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getClaveUnidad());
                    invoicedQuantity.setValue(responseXMLParseDto.getComprobante().getConceptos().getConcepto().get(i).getCantidad());
                    lineItem.setInvoicedQuantity(invoicedQuantity);

                    mx.gob.sat.detallista.Detallista.LineItem.GrossPrice grossPrice = objDetallista
                            .createDetallistaLineItemGrossPrice();
                    grossPrice.setAmount(new BigDecimal(responseXMLParseDto.getDetallistaItems().get(i).getGrossPriceAmount()));
//                    grossPrice.setAmount(new BigDecimal(Utils.decimalFormat(this.comprobanteParseSAPDto.getLineItem().get(i).getGrossPriceAmount())));
                    lineItem.setGrossPrice(grossPrice);

                    mx.gob.sat.detallista.Detallista.LineItem.NetPrice netPrice = objDetallista
                            .createDetallistaLineItemNetPrice();
                    netPrice.setAmount(new BigDecimal(responseXMLParseDto.getDetallistaItems().get(i).getNetPriceAmount()));
                    lineItem.setNetPrice(netPrice);

                    mx.gob.sat.detallista.Detallista.LineItem.TotalLineAmount totalLineAmount = objDetallista
                            .createDetallistaLineItemTotalLineAmount();
                    mx.gob.sat.detallista.Detallista.LineItem.TotalLineAmount.GrossAmount totalLineAmountGrossAmount = objDetallista
                            .createDetallistaLineItemTotalLineAmountGrossAmount();
                    // System.out.println("posicion: "+ i);
                    totalLineAmountGrossAmount.setAmount(new BigDecimal(responseXMLParseDto.getDetallistaItems().get(i).getTotalLineAmount()));

                    totalLineAmount.setGrossAmount(totalLineAmountGrossAmount);

                    mx.gob.sat.detallista.Detallista.LineItem.TotalLineAmount.NetAmount totalLineAmountNetAmount = objDetallista
                            .createDetallistaLineItemTotalLineAmountNetAmount();
                    totalLineAmountNetAmount.setAmount(new BigDecimal(responseXMLParseDto.getDetallistaItems().get(i).getTotalLineAMountNeto()));
                    totalLineAmount.setNetAmount(totalLineAmountNetAmount);

                    lineItem.setTotalLineAmount(totalLineAmount);
                    detallista.getLineItem().add(lineItem);
                }

                mx.gob.sat.detallista.Detallista.TotalAmount totalAmount = new mx.gob.sat.detallista.Detallista.TotalAmount();
                totalAmount.setAmount(new BigDecimal(responseXMLParseDto.getDetallistaCabecero().getTotalAmount()));
                detallista.setTotalAmount(totalAmount);

                mx.gob.sat.detallista.Detallista.TotalAllowanceCharge totalAllowanceCharge = new mx.gob.sat.detallista.Detallista.TotalAllowanceCharge();
                totalAllowanceCharge.setAllowanceOrChargeType("ALLOWANCE");
                totalAllowanceCharge.setSpecialServicesType("AJ");
                totalAllowanceCharge.setAmount(new BigDecimal(responseXMLParseDto.getDetallistaCabecero().getMontoTotalDescuentos()));
                detallista.getTotalAllowanceCharge().add(totalAllowanceCharge);

                complemento.getAny().add(detallista);
            }
            
            //Complemento de Recepción de Pagos.
//            Complemento complemento = of.createComprobanteComplemento();
            /////////////////////////////////////////////////

            //Complemento de Recepción de Pagos.
//            mx.bigdata.sat.common.pagos10.schema.ObjectFactory objPagos = new mx.bigdata.sat.common.pagos10.schema.ObjectFactory();
//            mx.bigdata.sat.common.pagos10.schema.Pagos pagos = objPagos.createPagos();
//            if (responseXMLParseDto.getComprobante().getListPagos().size() != 0) {
//                ArrayList<mx.bigdata.sat.common.pagos10.schema.Pagos.Pago> listPago = new ArrayList<mx.bigdata.sat.common.pagos10.schema.Pagos.Pago>();
//                pagos.setVersion("1.0");
//                for (int cp = 0; cp < responseXMLParseDto.getComprobante().getListPagos().size(); cp++) {
//                    mx.bigdata.sat.common.pagos10.schema.Pagos.Pago pago = objPagos.createPagosPago();
//                    //Conversión de fecha	
//                    GregorianCalendar cale = new GregorianCalendar();
//                    cale.setTime(responseXMLParseDto.getComprobante().getListPagos().get(cp).getFechaPago());
//                    //System.out.println(respxml.getFecha());
//                    XMLGregorianCalendar fecha2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(cale);
//                    String dat = fecha2.toString();
//                    String[] fec2 = dat.split("\\.");
//                    String valorFe = fec2[0];
//                    XMLGregorianCalendar dateFinal = DatatypeFactory.newInstance().newXMLGregorianCalendar(valorFe);
//
//                    pago.setFechaPago(dateFinal);
//                    pago.setFormaDePagoP(responseXMLParseDto.getComprobante().getListPagos().get(cp).getFormaDePagoP());
//                    pago.setMonedaP(CMoneda.fromValue(responseXMLParseDto.getComprobante().getListPagos().get(cp).getModenaP()));
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getTipoCambioP()).equals("")) {
//                        if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getTipoCambioP().toString()).equals("0.0")) {
//                            //System.out.println("Imprime cambio-> "+respxml.getListComPagos().get(cp).getTipoCambioP());
//                            pago.setTipoCambioP(responseXMLParseDto.getComprobante().getListPagos().get(cp).getTipoCambioP());
//                        }
//
//                    }
//                    pago.setMonto(responseXMLParseDto.getComprobante().getListPagos().get(cp).getMonto());
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getNumOperacion()).equals("")) {
//                        pago.setNumOperacion(responseXMLParseDto.getComprobante().getListPagos().get(cp).getNumOperacion());
//                    }
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getRfcEmisorCtaOrd()).equals("")) {
//                        pago.setRfcEmisorCtaOrd(responseXMLParseDto.getComprobante().getListPagos().get(cp).getRfcEmisorCtaOrd());
//                    }
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getNomBancoOrdExt()).equals("")) {
//                        pago.setNomBancoOrdExt(responseXMLParseDto.getComprobante().getListPagos().get(cp).getNomBancoOrdExt());
//                    }
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getCtaOrdenante()).equals("")) {
//                        pago.setCtaOrdenante(responseXMLParseDto.getComprobante().getListPagos().get(cp).getCtaOrdenante());
//                    }
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getRfcEmisorCtaBen()).equals("")) {
//                        pago.setRfcEmisorCtaBen(responseXMLParseDto.getComprobante().getListPagos().get(cp).getRfcEmisorCtaBen());
//                    }
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getCtaBeneficiario()).equals("")) {
//                        pago.setCtaBeneficiario(responseXMLParseDto.getComprobante().getListPagos().get(cp).getCtaBeneficiario());
//                    }
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getTipoCadPago()).equals("")) {
//                        pago.setTipoCadPago(responseXMLParseDto.getComprobante().getListPagos().get(cp).getTipoCadPago());
//                    }
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getCertPago()).equals("")) {
//                        //pago.setCertPago(respxml.getListComPagos().get(cp).getCertPago());
//                    }
//                    //System.out.println("CadPago genera---> "+respxml.getListComPagos().get(cp).getCadPago());
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getCadPago()).equals("")) {
//                        pago.setCadPago(responseXMLParseDto.getComprobante().getListPagos().get(cp).getCadPago());
//                    }
//                    if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getSelloPago()).equals("")) {
//                        //pago.setSelloPago(respxml.getListComPagos().get(cp).getSelloPago());
//                    }
//                    ArrayList<mx.bigdata.sat.common.pagos10.schema.Pagos.Pago.DoctoRelacionado> listDoctoRelacionado = new ArrayList<mx.bigdata.sat.common.pagos10.schema.Pagos.Pago.DoctoRelacionado>();
//                    for (int doctRel = 0; doctRel < responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().size(); doctRel++) {
//                        mx.bigdata.sat.common.pagos10.schema.Pagos.Pago.DoctoRelacionado docto = objPagos.createPagosPagoDoctoRelacionado();
//                        docto.setIdDocumento(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getIdDocumento());
//                        if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getSerie()).equals("")) {
//                            docto.setSerie(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getSerie());
//                        }
//                        if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getFolio()).equals("")) {
//                            docto.setFolio(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getFolio());
//                        }
//                        docto.setMonedaDR(CMoneda.fromValue(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getMonedaDr()));
//                        if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getTipoCambioDr()).equals("")) {
//                            if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getTipoCambioDr().toString()).equals("0.0")) {
//                                docto.setTipoCambioDR(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getTipoCambioDr());
//                            }
//
//                        }
//
//                        docto.setMetodoDePagoDR(CMetodoPago.fromValue(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getMetodoDePagoDr()));
//                        if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getNumParcialidad()).equals("")) {
//                            docto.setNumParcialidad(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getNumParcialidad());
//                        }
//                        if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getImpSaldoAnt()).equals("")) {
//                            docto.setImpSaldoAnt(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getImpSaldoAnt());
//                        }
//                        if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getImpPagado()).equals("")) {
//                            docto.setImpPagado(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getImpPagado());
//                        }
//                        if (!(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getImpoSaldoInsoluto()).equals("")) {
//                            docto.setImpSaldoInsoluto(responseXMLParseDto.getComprobante().getListPagos().get(cp).getListDocRelacionado().get(doctRel).getImpoSaldoInsoluto());
//                        }
//                        listDoctoRelacionado.add(docto);
//                    }
//
//                    pago.getDoctoRelacionado().addAll(listDoctoRelacionado);
//                    listPago.add(pago);
//                }
//                pagos.getPago().addAll(listPago);
//                complemento.getAny().add(pagos);
//            }
            ////////////////////////////////////////////////
            //Complemento de comercio exterior.
//            if (responseXMLParseDto.getComprobante().getComercioExt() != null) {
//
//                mx.bigdata.sat.common.comercioExterior11.schema.ObjectFactory objComercio = new mx.bigdata.sat.common.comercioExterior11.schema.ObjectFactory();
//                mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior exterior = objComercio.createComercioExterior();
//                mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Receptor receptorExt = objComercio.createComercioExteriorReceptor();
//                mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Emisor emisorExt = objComercio.createComercioExteriorEmisor();
//                mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Mercancias mercancias = objComercio.createComercioExteriorMercancias();
//
//                exterior.setVersion("1.1");
//                exterior.setTipoOperacion("2");
//                if (responseXMLParseDto.getComprobante().getComercioExt().getIncoterm() != null && !responseXMLParseDto.getComprobante().getComercioExt().getIncoterm().equals("")) {
//                    exterior.setIncoterm(CINCOTERM.fromValue(responseXMLParseDto.getComprobante().getComercioExt().getIncoterm()));
//                }
//                exterior.setClaveDePedimento(CClavePedimento.fromValue(responseXMLParseDto.getComprobante().getComercioExt().getClaveDePedimento()));
//                if (responseXMLParseDto.getComprobante().getComercioExt().getCertificadoOrigen() != null && !responseXMLParseDto.getComprobante().getComercioExt().getCertificadoOrigen().equals("")) {
//                    exterior.setCertificadoOrigen(Integer.valueOf(responseXMLParseDto.getComprobante().getComercioExt().getCertificadoOrigen()));
//                }
//                if (responseXMLParseDto.getComprobante().getComercioExt().getSubDivision() != null && !responseXMLParseDto.getComprobante().getComercioExt().getSubDivision().equals("")) {
//                    exterior.setSubdivision(Integer.valueOf(responseXMLParseDto.getComprobante().getComercioExt().getSubDivision()));
//                }
//                if (responseXMLParseDto.getComprobante().getComercioExt().getTipoCambioUSD() != null && !responseXMLParseDto.getComprobante().getComercioExt().getTipoCambioUSD().equals("")) {
//                    exterior.setTipoCambioUSD(responseXMLParseDto.getComprobante().getComercioExt().getTipoCambioUSD());
//                }
//                if (responseXMLParseDto.getComprobante().getComercioExt().getTotalUSD() != null && !responseXMLParseDto.getComprobante().getComercioExt().getTotalUSD().equals("")) {
//                    exterior.setTotalUSD(responseXMLParseDto.getComprobante().getComercioExt().getTotalUSD());
//                }
//                if (responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio() != null) {
//                    mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Emisor.Domicilio emisorDom = objComercio.createComercioExteriorEmisorDomicilio();
//                    emisorDom.setCalle(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getCalle());
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getNumeroExterior())).equals("")) {
//                        emisorDom.setNumeroExterior(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getNumeroExterior());
//                    }
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getNumeroInterior())).equals("")) {
//                        emisorDom.setNumeroInterior(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getNumeroInterior());
//                    }
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getColonia())).equals("")) {
//                        emisorDom.setColonia(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getColonia());
//                    }
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getLocalidad())).equals("")) {
//                        emisorDom.setLocalidad(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getLocalidad());
//                    }
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getMunicipio())).equals("")) {
//                        emisorDom.setMunicipio(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getMunicipio());
//                    }
//
//                    emisorDom.setEstado(CEstado.fromValue(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getEstado()));
//                    emisorDom.setPais(CPais.fromValue(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getPais()));
//                    emisorDom.setCodigoPostal(responseXMLParseDto.getComprobante().getComercioExt().getEmisorComercio().getCodigoPostal());
//
//                    emisorExt.setDomicilio(emisorDom);
//                    exterior.setEmisor(emisorExt);
//                }
//                if (responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio() != null) {
//                    mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Receptor.Domicilio receptorDom = objComercio.createComercioExteriorReceptorDomicilio();
//                    receptorDom.setCalle(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getCalle());
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getNumExterior())).equals("")) {
//                        receptorDom.setNumeroExterior(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getNumExterior());
//                    }
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getNumInterior())).equals("")) {
//                        receptorDom.setNumeroInterior(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getNumInterior());
//                    }
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getColonia())).equals("")) {
//                        receptorDom.setColonia(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getColonia());
//                    }
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getLocalidad())).equals("")) {
//                        receptorDom.setLocalidad(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getLocalidad());
//                    }
//                    if (!(Utils.nullToString(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getMunicipio())).equals("")) {
//                        receptorDom.setMunicipio(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getMunicipio());
//                    }
//
//                    receptorDom.setEstado(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getEstado());
//                    receptorDom.setPais(CPais.fromValue(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getPais()));
//                    receptorDom.setCodigoPostal(responseXMLParseDto.getComprobante().getComercioExt().getReceptorComercio().getCodigoPostal());
//
//                    receptorExt.setDomicilio(receptorDom);
//                    exterior.setReceptor(receptorExt);
//                }
//                ArrayList<mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Mercancias.Mercancia> listMercancia = new ArrayList<mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Mercancias.Mercancia>();
//
//                for (int e = 0; e < responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().size(); e++) {
//                    mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Mercancias.Mercancia mercancia = objComercio.createComercioExteriorMercanciasMercancia();
//                    mercancia.setNoIdentificacion(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getNoIdentificacion());
//
//                    if (responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getCantidadAduana() != null) {
//                        mercancia.setCantidadAduana(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getCantidadAduana());
//                    }
//                    if (responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getFraccionAracelaria() != null) {
//                        mercancia.setFraccionArancelaria(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getFraccionAracelaria());
//                    }
//                    if (responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getUnidadAduana() != null) {
//                        mercancia.setUnidadAduana(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getUnidadAduana());
//                    }
//                    if (responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getValorUnitarioAduana() != null) {
//                        mercancia.setValorUnitarioAduana(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getValorUnitarioAduana());
//                    }
//                    mercancia.setValorDolares(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getValorDolares());
//                    //mercancia.setValorDolares(new BigDecimal("165.90"));
//
//                    ArrayList<mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Mercancias.Mercancia.DescripcionesEspecificas> descMerca = new ArrayList<mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Mercancias.Mercancia.DescripcionesEspecificas>();
//                    //descripciones de mercancias.
//                    for (int d = 0; d < responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getDescEspecificas().size(); d++) {
//                        mx.bigdata.sat.common.comercioExterior11.schema.ComercioExterior.Mercancias.Mercancia.DescripcionesEspecificas des = objComercio.createComercioExteriorMercanciasMercanciaDescripcionesEspecificas();
//                        des.setMarca(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getDescEspecificas().get(d).getMarca());
//                        des.setSubModelo(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getDescEspecificas().get(d).getSubModelo());
//                        des.setModelo(responseXMLParseDto.getComprobante().getComercioExt().getListMercancia().get(e).getDescEspecificas().get(d).getModelo());
//                        descMerca.add(des);
//
//                    }
//                    mercancia.getDescripcionesEspecificas().addAll(descMerca);
//                    listMercancia.add(mercancia);
//                }
//                mercancias.getMercancia().addAll(listMercancia);
//
//                exterior.setMercancias(mercancias);
//                complemento.getAny().add(exterior);
//            }
            ////////////////////////////////////////////////////////////
            //Complemento Leyenda Fiscales.
//            if (responseXMLParseDto.getComprobante().getListLeyenda() != null) {
//                mx.bigdata.sat.common.leyendasfisc.schema.ObjectFactory objLeyendas = new mx.bigdata.sat.common.leyendasfisc.schema.ObjectFactory();
//                LeyendasFiscales leyendasF = objLeyendas.createLeyendasFiscales();
//                ArrayList<LeyendasFiscales.Leyenda> listLeyendas = new ArrayList<LeyendasFiscales.Leyenda>();
//                for (int ley = 0; ley < responseXMLParseDto.getComprobante().getListLeyenda().size(); ley++) {
//                    LeyendasFiscales.Leyenda leyenda = objLeyendas.createLeyendasFiscalesLeyenda();
//                    leyendasF.setVersion("1.0");
//                    leyenda.setDisposicionFiscal(responseXMLParseDto.getComprobante().getListLeyenda().get(ley).getDisposicionFiscal());
//                    leyenda.setNorma(responseXMLParseDto.getComprobante().getListLeyenda().get(ley).getNorma());
//                    leyenda.setTextoLeyenda(responseXMLParseDto.getComprobante().getListLeyenda().get(ley).getTextoLeyenda());
//
//                    listLeyendas.add(leyenda);
//
//                }
//                if (listLeyendas.size() != 0) {
//                    leyendasF.getLeyenda().addAll(listLeyendas);
//                    complemento.getAny().add(leyendasF);
//                }
//            }
            ////////////////////////////
            comp.setEmisor(emisor);
            comp.setReceptor(receptor);
            if (infog != null) {
                if (infog.getAño() > 2022) {
                    comp.setInformacionGlobal(infog);
                }
                
            }
            comp.setConceptos(cps);
            if (imps.getRetenciones() != null || imps.getTraslados() != null) {
                comp.setImpuestos(imps);
                //	System.out.println("Si hay impuesto");
            }
            if (complemento.getAny().size() != 0) {
                comp.setComplemento(complemento);
            }
            //System.out.println("VERSION--> "+comp.getVersion());
            //comp.setComplemento(complemento);
        }
        return comp;
    }

    public String sellaComprobanteXML(Comprobante comprobante, String rfc, BeanGeneral compr) throws Exception {

        String schemaBase = "";
        String schemaBase2 = "";
        String complemento = "";
        String opera = "";
        String xmlSellado = "";
        CFDv40 cfd=null;
        //Obtención de datos, .CER,.KEY,PASSWORD
        String private_key_dir = Utils.getParamValue("path_files_key_cert") + rfc + ".key";
        String cert_dir = Utils.getParamValue("path_files_key_cert") + rfc + ".cer";
        ArrayList<RespaldoVo> respaldoVos = query.extraerPropiedad(rfc);
        
        if (comprobante.getComplemento() != null) {
            if (comprobante.getComplemento().getAny() != null) {
                cfd = new CFDv40("detallista",comprobante, "mx.gob.sat.detallista");
                cfd.addNamespace("http://www.sat.gob.mx/detallista", "detallista");

            }else {
			cfd = new CFDv40("cfdi",comprobante, "");
			cfd.addNamespace("http://www.edifactmx.com.mx", "cfdi");
		}
        }else {
			cfd = new CFDv40("cfdi",comprobante, "");
			cfd.addNamespace("http://www.edifactmx.com.mx", "cfdi");
		}

        if (respaldoVos.size() >= 1) {
            complemento = respaldoVos.get(0).getCfdi_tool();
            opera = Utils.quitarAlias(complemento, "XMzDdG4D03CKm2IxIWQw7g==");
        }
//        PrivateKey key = KeyLoaderFactory.createInstance(
//                KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
//                new FileInputStream(private_key_dir), opera).getKey();

//        X509Certificate cert = KeyLoaderFactory.createInstance(
//                KeyLoaderEnumeration.PUBLIC_KEY_LOADER, new FileInputStream(cert_dir)).getKey();
            String xml = "";
        if (cfd != null){
             xml = cfd.guardar();
    }
			String cadenaOriginal = "";
			String sello = "";
			try {
                            //cadena original 4.0 prod
//                            cadenaOriginal = creaCadenaOriginal("/edifactmx_greiner/sstxsd/xslt_xsd/cadenaoriginal_4_0.xslt", xml);
//                            cadena original 4.0 160
//                            cadenaOriginal = creaCadenaOriginal("/edifactmx_radiopolis/sstxsd/xslt_xsd/cadenaoriginal_4_0.xslt", xml);
//                            // cadena original /local
				cadenaOriginal = creaCadenaOriginal(Utils.getParamValue("path_files_xsd_xslt") + File.separator + "cadenaoriginal_4_0.xslt", xml);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			try {
				sello = sellarCfdi(cadenaOriginal, Utils.getParamValue("path_files_key_cert") + comprobante.getEmisor().getRfc() + ".key", opera);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				String[] temp = xml.split("Sello=\"");
				xmlSellado = temp[0] + "Sello=\"" + sello + temp[1];
				// log.info(this.xmlSellado);
				// System.out.println(this.xmlSellado);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}


        
//        if (comprobante.getComplemento() != null) {
//            if (comprobante.getComplemento().getAny() != null) {
//
//            xmlSellado = Utils.cambiaDetallista(comprobante, xmlSellado, compr);
//
////            InputStream is = null; // new ByteArrayInputStream(xml.getBytes());
////            // xml = addNamespaceToXml(is);
////
////            is = new ByteArrayInputStream(xmlSellado.getBytes("UTF-8"));
////            cfd = new CFDv40(is, schemaBase);
////            cfd = new CFDv40("cfdi",comprobante, "");
//
//            if (comprobante.getComplemento().getAny() != null) {
//                cfd = new CFDv40("detallista",comprobante, "mx.gob.sat.detallista");
//                cfd.addNamespace("http://www.sat.gob.mx/detallista", "detallista");
//
//            }else {
//			cfd = new CFDv40("cfdi",comprobante, "");
//			cfd.addNamespace("http://www.edifactmx.com.mx", "cfdi");
//		}
//if (respaldoVos.size() >= 1) {
//            complemento = respaldoVos.get(0).getCfdi_tool();
//            opera = Utils.quitarAlias(complemento, "XMzDdG4D03CKm2IxIWQw7g==");
//        }
////            Comprobante sellado1 = cfd.sellarComprobante(key, cert);
//                try {
//				sello = sellarCfdi(cadenaOriginal, Utils.getParamValue("path_files_key_cert") + comprobante.getEmisor().getRfc() + ".key", opera);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//			try {
//				String[] temp = xml.split("Sello=\"");
//				xmlSellado = temp[0] + "Sello=\"" + sello + temp[1];
//				// log.info(this.xmlSellado);
//				// System.out.println(this.xmlSellado);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//            //System.out.println(sellado1.getSello());
////            OutputStream scfdi1 = new OutputStream() {
////
////                private StringBuilder string = new StringBuilder();
////
////                @Override
////                public void write(int b) throws IOException {
////                    // TODO Auto-generated method stub
////                    this.string.append((char) b);
////                }
////
////                public String toString() {
////                    return this.string.toString();
////                }
////            };
//
////            cfd.guardar(scfdi1, schemaLocation);
////            xmlSellado = scfdi1.toString();
//            //System.out.println("XML ANDRES ----> "+xmlSellado);
//
//            xmlSellado = Utils.cambiaDetallista(comprobante, xmlSellado, compr);
//
//            xmlSellado = xmlSellado.replaceAll("\\n\\r", "");
//
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/catalogos", "");
////            xmlSellado = reemplazar(xmlSellado, " http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI\"", "\"");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI", "");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd  ", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd ");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd ", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsdhttp://www.sat.gob.mx/leyendasFiscales", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/leyendasFiscales");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsdhttp://www.sat.gob.mx/ComercioExterior11", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/ComercioExterior11");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsdhttp://www.sat.gob.mx/Pagos", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/Pagos");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsdhttp://www.sat.gob.mx/detallista", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/detallista");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsdhttp://www.sat.gob.mx/ine", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/ine");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/cfd/3http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd", "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
////            xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd", "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
////            xmlSellado = xmlSellado.replaceAll("http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsdhttp://www.sat.gob.mx/ComercioExterior11", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/ComercioExterior11");
//
//
////            System.out.println("XML SEGUNDO SELLADO:" + xmlSellado);
//        }
//        }
        
        
        

//        xmlSellado = xmlSellado.replaceAll("http://www.sat.gob.mx/sitio_internet/cfd/catalogos", "");
//        xmlSellado = xmlSellado.replaceAll("http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI\"", "\"");
//        xmlSellado = xmlSellado.replaceAll("http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI", "");
//        xmlSellado = xmlSellado.replaceAll("http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd  ", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
//
//        xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd \"", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\"");
//        xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsdhttp://www.sat.gob.mx/detallista", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/detallista");
//        xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsdhttp://www.sat.gob.mx/ComercioExterior11", "http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/ComercioExterior11");
//        xmlSellado = reemplazar(xmlSellado, " http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI\"", "\"");
//        xmlSellado = reemplazar(xmlSellado, "http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI ", "");
//        xmlSellado = xmlSellado.replaceAll("\t", "");
//        xmlSellado = xmlSellado.replaceAll("\n", "");
//        xmlSellado = xmlSellado.replaceAll("\\s*$", "");
//        xmlSellado = xmlSellado.replaceAll(">\\s*<", "><");

        //String xmlSellado=cfd.marshallInString(true); 
//        System.out.println("xmlSellado - "+ xmlSellado);
        return xmlSellado;
    }

    public String reemplazar(String cadena, String busqueda, String reemplazo) {
        return cadena.replaceAll(busqueda, reemplazo);
    }

    public ResponseXMLParseDto timbrarComprobante(String rfc, String docXML, String filename, ComprobanteParseDto parseDto) throws Exception {
        responseXMLParseDto = new ResponseXMLParseDto();

        TimbrarCFDIPortType weatherSoap = new TimbrarCFDILocator(Utils.getParamValue("ambiente_timbrado")).gettimbrarCFDIPort();
        TimbrarCFDIBindingStub stub = (TimbrarCFDIBindingStub) weatherSoap;

        String rep_xmldocString = docXML.replace("xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\"", "");
        byte[] bytes = rep_xmldocString.getBytes("UTF-8");
        docXML = Base64Coder.encodeLines(bytes);

//        System.out.println("Enviando datos al WSDL RFC=" + rfc + " RFCPAC=" + Utils.getParamValue("rfc_pac") + " AMBIENTE=" + Utils.getParamValue("ambiente_timbrado"));
        RespuestaTimbrado result = weatherSoap.timbrarCFDI(rfc, Utils.getParamValue("rfc_pac"), docXML, Utils.getParamValue("ambiente_timbrado"));

        responseXMLParseDto.setCodigo(result.getCodigoResultado());
        responseXMLParseDto.setDescripcion(result.getCodigoDescripcion());
        responseXMLParseDto.setDocumento(result.getDocumentoTimbrado());

        return responseXMLParseDto;
    }

    public Boolean verificaCorreosPorEnviar() throws Exception {
        Boolean resp = false;
        //ArrayList<CorreoVo> correos=validaEnvioCorreo();

        resp = true;

        try {
            SendMailPrepare enviarCorreo = new SendMailPrepare();
            String email = comprobanteParseDto.getAddendaRecepDomicilio().getCorreoD();
            String correo = "";
            /*String correoDos="";
			String correoTres="";
			String correoCuatro="";
			String correoCinco="";*/
            if (email != null && !email.equals("")) {
                correo += comprobanteParseDto.getAddendaRecepDomicilio().getCorreoD() + ";";
            }
//			if(!comprobanteParseDto.getCorreodos().equals("")){
//				correo+=comprobanteParseDto.getCorreodos()+";";
//			}
//			if(!comprobanteParseDto.getCorreotres().equals("")){
//				correo+=comprobanteParseDto.getCorreotres()+";";
//			}
//			if(!comprobanteParseDto.getUsuario().equals("")){
//				correo+=comprobanteParseDto.getUsuario()+";";
//			}
//			if(!comprobanteParseDto.getEjecutivo().equals("")){
//				correo+=comprobanteParseDto.getEjecutivo();
//			}
            //System.out.println("->"+correoUno+receptorDto.getNombre()+"-"+correos.get(i).getSerie()+correos.get(i).getFolio());

            if (!(correo).equals("")) {
                //Validación para el rfc CMI111215AR5, si tiene complemento de pagos se asignan dos correos por default.
//				if(comprobanteParseDto.getReceptorDto().getRfc().equals("CMI111215AR5")){
//					if(!(comprobanteParseDto.getEmisor().getRfc()).equals("AAA010101AAA")){
//						if(comprobanteParseDto.getListPagos().size()!=0){
//							correo="contabilidad@cervezaminerva.mx;alejandra.amigon@seacargo.com";
//						}
//						
//					}
//					//correo="andres.zamudio@edifact.com.mx";
//				}
                enviarCorreo.enviarPreparaCorreo(comprobanteParseDto.getUUID(), correo, receptorDto.getNombre(), comprobanteParseDto.getSerie(), comprobanteParseDto.getFolio(), comprobanteParseDto.getTipoDeComprobante()); //"armexico@singlesourcetech.com";
                //enviarCorreo.enviarPreparaCorreo(correos.get(i).getUuid(), "zl.andres@yahoo.com,and_psyfull@hotmail.com"); //"armexico@singlesourcetech.com";
                actualizaEnvioCorreo(comprobanteParseDto.getUUID(), correo);
                //System.out.println("Se esta enviando correo --> "+enviarCorreo);
            } else {
                System.out.println("No hay direcciones de correo, para enviar documento con serie/folio: " + comprobanteParseDto.getSerie() + comprobanteParseDto.getFolio());
            }

        } catch (Exception e) {
            System.out.println("Error al extraer los archivos " + e.getMessage());
            e.printStackTrace();
        }

        return resp;
    }

    public ArrayList<CorreoVo> validaEnvioCorreo() throws Exception {
        ArrayList<CorreoVo> listCorreo = new ArrayList<CorreoVo>();
        try {
            ConnectionData conexion = new ConnectionData();
            Connection connect = conexion.getConnection();

            PreparedStatement ps = connect.prepareStatement("SELECT * FROM cfdis WHERE enviado <> '1' OR enviado IS NULL");
            ps.setString(1, "");
            ResultSet rs = ps.executeQuery();
            //String sql = "SELECT * FROM cfdis WHERE uuid='ce8a72c5-85cc-4441-828c-2527559e01bd'";
//			JdbcTemplate jdbcTemplate = new JdbcTemplate(dsConnect); 
            while (rs.next()) {
                CorreoVo par = new CorreoVo();
                par.setFolio(rs.getString("folio"));
                par.setSerie(rs.getString("serie"));
                par.setUuid(rs.getString("uuid"));
                par.setCorreo(rs.getString("rfcReceptor"));
                listCorreo.add(par);
            }

            rs.close();
            ps.close();
//			listCorreo = (ArrayList<CorreoVo>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<CorreoVo>(CorreoVo.class));
//            System.out.println("correo ------> " + listCorreo);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
//        System.out.println("correo ------> " + listCorreo);
        return listCorreo;

    }

    public Boolean actualizaEnvioCorreo(String uuid, String correoUno) throws Exception {
        Boolean actualizado = false;

        try {
            ConnectionData conexion = new ConnectionData();
            Connection connect = conexion.getConnection();
//            System.out.println("correos a enviar----> " + correoUno);
            String sql = "UPDATE cfdis SET enviado = '1', correo='" + correoUno + "' WHERE uuid ='" + uuid + "'";
            PreparedStatement ps = connect.prepareStatement(sql);
            //System.out.println("AC");
            int result = ps.executeUpdate();
            if (result > 0) {
                actualizado = true;
//                System.out.println("Se actualizo cfdis Correo con uuid: " + uuid);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return actualizado;
    }
    
    public String sellarCfdi(String cadena, String rutaKey, String pass) throws Exception {
		String retorno = "";
		try {
			byte[] keyBytes = FileUtils.readFileToByteArray(new File(rutaKey));
			PKCS8Key pkcs8 = new PKCS8Key(keyBytes, pass.toCharArray());
			PrivateKey pkey = pkcs8.getPrivateKey();
			Signature firma = Signature.getInstance("SHA256withRSA");
			firma.initSign(pkey);
			Charset csets = Charset.forName("UTF-8");
			firma.update(csets.encode(cadena));
			byte[] firmado = firma.sign();
			String sellodigital = Base64Coder.encodeLines(firmado).replace("\n", "").replace("\r", "");
			retorno = sellodigital;
			// System.out.println("sellodigital:" + sellodigital);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}
	
	public String creaCadenaOriginal(String xslt, String cfdi) throws Exception {
		String retorno = "";
		try {
			StreamSource sourceXSL = new StreamSource(xslt);
			StreamSource sourceXML = new StreamSource(new java.io.StringReader(cfdi));
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(sourceXSL);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Result out = new StreamResult(baos);
			transformer.transform(sourceXML, out);
			byte[] cadenaOriginalArray = baos.toByteArray();

			retorno = new String(cadenaOriginalArray, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("error: "+e.getMessage());
		}
		return retorno;
	}
        
        public static boolean hasMoreThanOneDecimalPlace(BigDecimal number) { 
            return number.scale() > 1;
        
        }
    public String getTagValue(String xml, String tagName) {
        String res = "";
        try {
            res = StringUtils.substringBetween(xml, "<" + tagName + ">", "</" + tagName + ">");
            if (res == null) {
                res = "";
            }
            //res = xml.split("<"+tagName+">")[1].split("</"+tagName+">")[0];
        } catch (Exception e) {
            System.out.println("No se pudo encontrar el tag <" + tagName + ">");
        }
        return res;
    }
}
