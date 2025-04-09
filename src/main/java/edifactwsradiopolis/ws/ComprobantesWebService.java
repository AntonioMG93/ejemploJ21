/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edifactwsradiopolis.ws;

import edifactwsradiopolis.dao.ComprobanteArchivosDaoImpl;
import edifactwsradiopolis.dao.QueryAdministrador;
import edifactwsradiopolis.dto.ResponseCatalogosDto;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
/*import javax.ejb.Stateless;*/
import edifactwsradiopolis.dto.ResponseComprobanteAdministraDatosDto;
import edifactwsradiopolis.dto.ResponseComprobanteAdminisrtaDatosCDto;
import edifactwsradiopolis.dto.ResponseUsuariosVo;
import edifactwsradiopolis.utils.Base64Coder;
import edifactwsradiopolis.utils.Utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Properties;


/**
 *
 * @author Antonio MG
 */
@WebService(serviceName = "ComprobantesWebService")
/*@Stateless()*/
public class ComprobantesWebService {
//private static Logger log = Logger.getLogger(ComprobantesWebService.class);
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "generaComprobante")
    public synchronized ResponseComprobanteAdministraDatosDto generaComprobante(@WebParam(name = "idoc") String idoc, @WebParam(name = "rfc") String rfc,@WebParam(name = "usuario") String usuario,@WebParam(name = "password") String password) {
        ResponseComprobanteAdministraDatosDto response=new ResponseComprobanteAdministraDatosDto();
        ResponseUsuariosVo responseUsuariosVo = new ResponseUsuariosVo();
	ResponseCatalogosDto responseCatalogosDto = new ResponseCatalogosDto();
        QueryAdministrador uImpl=new QueryAdministrador();
        ComprobanteArchivosDaoImpl envia=new ComprobanteArchivosDaoImpl();
//        System.out.println("Entro con usuario: "+usuario);
//	System.out.println("Entro con password "+password);
//        System.out.println("Entro con rfc: "+rfc);
//	System.out.println("Entro a comprobante wsdl "+idoc);
        String idocCfdiDoc_="";
        try {
           Base64.Decoder dec= Base64.getDecoder();
           byte[] strdec=dec.decode(idoc);
           idocCfdiDoc_= new String(strdec,"UTF-8");

        } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
        }
        
        
        try {	
			
            Boolean login=uImpl.getUsuarios(usuario,password); 
            if(login){
//            System.out.println("Se valido el usuario "+login);
            responseUsuariosVo.setRespuesta("SUCCESS");
            if(responseUsuariosVo.getRespuesta().equals("SUCCESS"))
            {				
                responseUsuariosVo=new ResponseUsuariosVo();
                
                //responseUsuariosVo=iUsuariosService.cargaParametrosIniciales();
                responseUsuariosVo=uImpl.cargaParametrosIniciales();
                if(responseUsuariosVo.getListParametrosVo()!=null)
                {		
//                    System.out.println("Pasamos los parametros");
                    if(responseUsuariosVo.getListParametrosVo().size()==0){
                            System.out.println("Se rechazo la conexion definitavamente, no se pudo conectar con la base");
                            response.setCodigo("JE600");
                            response.setDescripcion("No se pudo conectar con la base de datos, vuelva a intentar nuevamente por favor.");
                            response.setDocumentoxml("");
                            response.setDocumentopdf("");
                    }else{
                            responseCatalogosDto.setCodigo("100");
                            Utils.parametrosVos = responseUsuariosVo.getListParametrosVo();
                            //responseCatalogosDto = iComprobanteAdministraDatosService.cargaFormatosPDF();
                            if(responseCatalogosDto.getCodigo().equals("100"))
                            { 
                                
                                response =envia.enviaComprobanteFiscal(idocCfdiDoc_, rfc);
                                    
                            }
                            else
                            {
                                response.setCodigo("JE500");
                                response.setDescripcion(responseCatalogosDto.getDescripcion());
                                response.setDocumentoxml("");
                            }
                    }

                }
                else
                {
                    response.setCodigo("JE500");
                    response.setDescripcion(responseUsuariosVo.getDescripcion());
                    response.setDocumentoxml("");
                }				
            }
            else
            {
                response.setCodigo(responseUsuariosVo.getRespuesta());
                response.setDescripcion(responseUsuariosVo.getDescripcion());
                response.setDocumentoxml("");
            }
        }else {
//                System.out.println("Credenciales incorrectas");
                response.setCodigo("JE900");
                response.setDescripcion("Credenciales , vuelva a intentar nuevamente por favor.");
                response.setDocumentoxml("");
                response.setDocumentopdf("");
                }

        } catch (Exception e) {
            String message = e.getMessage();
            response.setCodigo("JE100 - "+ "Valida informacion del comprobante");
            response.setDescripcion(message);
            response.setDocumentoxml("");
            System.out.println("Error - "+e.getMessage());
        }
        
        
        return response;
    }

    /**
     * Web service operation
     * @param idoc
     * @param rfc
     * @param usuario
     * @param password
     * @return 
     */
    @WebMethod(operationName = "cancelaComprobante")
    public synchronized ResponseComprobanteAdminisrtaDatosCDto cancelaComprobante(@WebParam(name = "idoc") String idoc, @WebParam(name = "rfc") String rfc,@WebParam(name = "usuario") String usuario,@WebParam(name = "password") String password) {
        //TODO write your implementation code here:
//        try {
//            Properties props = new Properties();
//            props.load(this.getClass().getResourceAsStream("/edifactwsradiopolis/utils/log4j.properties"));
//            PropertyConfigurator.configure(props);
//        } catch (IOException ex) {
//            java.util.logging.Logger.getLogger(ComprobantesWebService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        log.info("Llega peticion...");
        ResponseComprobanteAdminisrtaDatosCDto response=new ResponseComprobanteAdminisrtaDatosCDto();
        ResponseUsuariosVo responseUsuariosVo = new ResponseUsuariosVo();
	ResponseCatalogosDto responseCatalogosDto = new ResponseCatalogosDto();
        QueryAdministrador uImpl=new QueryAdministrador();
        ComprobanteArchivosDaoImpl envia=new ComprobanteArchivosDaoImpl();
//        System.out.println("Entro con usuario: "+usuario);
//	System.out.println("Entro con password "+password);
//        System.out.println("Entro con rfc: "+rfc);
//	System.out.println("Entro a cancelacion wsdl "+idoc);
        String idocCfdiCancelDoc="";
        String idocCfdiCancelDoc_="";
		
        
        try {
           Base64.Decoder dec= Base64.getDecoder();
           byte[] strdec=dec.decode(idoc);
           idocCfdiCancelDoc= new String(strdec,"UTF-8");

        } catch (UnsupportedEncodingException e1) {
//            log.error("Error al decodificar el "+e1.getMessage());
                // TODO Auto-generated catch block
                e1.printStackTrace();
        }
//        try {
//            idocCfdiCancelDoc_ = Base64Coder.decodeString(idoc);
//            idocCfdiCancelDoc = idocCfdiCancelDoc_.trim().replaceFirst("^([\\W]+)<", "<");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
		try {	
                    Boolean login=uImpl.getUsuarios(usuario,password); 
            if(login){
////            System.out.println("Se valido el usuario "+login);
            responseUsuariosVo.setRespuesta("SUCCESS");
            if(responseUsuariosVo.getRespuesta().equals("SUCCESS"))
            {				
                responseUsuariosVo=new ResponseUsuariosVo();
                
                //responseUsuariosVo=iUsuariosService.cargaParametrosIniciales();
                responseUsuariosVo=uImpl.cargaParametrosIniciales();
                if(responseUsuariosVo.getListParametrosVo()!=null)
                {		
//                    System.out.println("Pasamos los parametros");
                    if(responseUsuariosVo.getListParametrosVo().size()==0){
                            System.out.println("Se rechazo la conexion definitavamente, no se pudo conectar con la base");
                            response.setCodigo("JE600");
                            response.setDescripcion("No se pudo conectar con la base de datos, vuelva a intentar nuevamente por favor.");
                            response.setDocumentoxml("");
                            response.setDocumentopdf("");
                    }else{
                        responseCatalogosDto.setCodigo("100");
                            Utils.parametrosVos = responseUsuariosVo.getListParametrosVo();
                            //responseCatalogosDto = iComprobanteAdministraDatosService.cargaFormatosPDF();
                            if(responseCatalogosDto.getCodigo().equals("100"))
                            {
//                    Utils.parametrosVos = responseUsuariosVo.getListParametrosVo();
                    response = envia.cancelaComprobanteFiscal(idocCfdiCancelDoc, rfc);
                        
                            }else
                            {
                                response.setCodigo("JE510");
                                response.setDescripcion(responseCatalogosDto.getDescripcion());
                                response.setDocumentoxml("");
                            }
                    }
                }else
                {
                    response.setCodigo("JE500");
                    response.setDescripcion(responseUsuariosVo.getDescripcion());
                    response.setDocumentoxml("");
                }
                
            }else{
                response.setCodigo(responseUsuariosVo.getRespuesta());
                response.setDescripcion(responseUsuariosVo.getDescripcion());
                response.setDocumentoxml("");
            }
            }else{
                System.out.println("Credenciales incorrectas");
                response.setCodigo("JE900");
                response.setDescripcion("Credenciales , vuelva a intentar nuevamente por favor.");
                response.setDocumentoxml("");
                response.setDocumentopdf("");
            }
                } catch (Exception e) {
            String message = e.getMessage();
            response.setCodigo("JE100 - Valida informacion del comprobante");
            response.setDescripcion(message);
            response.setDocumentoxml("");
            e.printStackTrace();
        }
       
        return response;
    }
}
