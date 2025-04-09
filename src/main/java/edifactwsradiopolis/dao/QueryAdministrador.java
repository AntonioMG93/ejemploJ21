/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edifactwsradiopolis.dao;

import edifactwsradiopolis.dto.Cancelaciones;
import edifactwsradiopolis.dto.Cfdis;
import edifactwsradiopolis.dto.ParametrosVo;
import edifactwsradiopolis.dto.RespaldoVo;
import edifactwsradiopolis.dto.ResponseUsuariosVo;
import edifactwsradiopolis.dto.UsuarioVo;
import edifactwsradiopolis.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author zamud
 */
public class QueryAdministrador {
    public ResponseUsuariosVo cargaParametrosIniciales() throws Exception{
//        System.out.println("Iniciando los parametros");
        ResponseUsuariosVo responseUsuariosVo=new ResponseUsuariosVo();
        ArrayList<ParametrosVo> listParametrosVos = new ArrayList<ParametrosVo>();

        String sql = "";
        try
        {

            ConnectionData conexion = new ConnectionData();
            Connection connect = conexion.getConnection();

            PreparedStatement ps = connect.prepareStatement("SELECT * FROM cfdi_parametros");
            ResultSet rs = ps.executeQuery();

           // Usuario u = null;
            while (rs.next()) {
                ParametrosVo par=new ParametrosVo();
                par.setParametro_id(rs.getString("parametro_id"));
                par.setParametro_llave(rs.getString("parametro_llave"));
                par.setParametro_valor(rs.getString("parametro_valor"));
                listParametrosVos.add(par);
            }

            rs.close();
            ps.close();

            if(connect!=null)
        {
                connect.close();
        }

            responseUsuariosVo.setListParametrosVo(listParametrosVos);
        }
        catch(Exception ex){
                System.out.println(ex.getMessage());
        }
        /*
        try {
                ds = conexionDS.getDataSource(ds_cfdi);
                sql="SELECT * FROM cfdi_parametros";
                System.out.println("Iniciando los parametros 2");
                JdbcTemplate jdbcTemplate = new JdbcTemplate(ds); 
                listParametrosVos = (ArrayList<ParametrosVo>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<ParametrosVo>(ParametrosVo.class));
                System.out.println("Parametros cargados: "+listParametrosVos.size());
                System.out.println("Parametros cargados: "+listParametrosVos.get(0).getParametro_llave());

                if(listParametrosVos.size()>=1)
                {

                        responseUsuariosVo.setRespuesta("SUCCESS");
                        responseUsuariosVo.setListParametrosVo(listParametrosVos);
                }
                else
                {
                        responseUsuariosVo.setRespuesta("FAULT");
                        responseUsuariosVo.setListParametrosVo(listParametrosVos);
                        responseUsuariosVo.setDescripcion("Favor de verificar datos");
                }
        } catch (Exception e) {
                System.out.println(e.getMessage());
                responseUsuariosVo.setRespuesta("FAULT");
                responseUsuariosVo.setListParametrosVo(listParametrosVos);
                responseUsuariosVo.setDescripcion(e.getMessage());
        }
        */
        return responseUsuariosVo;
    }	
    public ArrayList<RespaldoVo> extraerPropiedad(String rfc) throws Exception
    {           
        ArrayList<RespaldoVo> listRespaldo = new ArrayList<RespaldoVo>();
        try
        {
                ConnectionData conexion = new ConnectionData();
                Connection connect = conexion.getConnection();

                PreparedStatement ps = connect.prepareStatement("SELECT * FROM cfdi_respaldo WHERE cfdi_rfc=?");
                ps.setString(1, rfc);
                ResultSet rs = ps.executeQuery();

           // Usuario u = null;
            while (rs.next()) {
                RespaldoVo par=new RespaldoVo();
                par.setCfdi_rfc(rs.getString("cfdi_rfc"));
                par.setCfdi_id(rs.getInt("cfdi_id"));
                par.setCfdi_complemento(rs.getString("cfdi_complemento"));
                par.setCfdi_fecha_add(rs.getString("cfdi_fecha_add"));
                par.setCfdi_tool(rs.getString("cfdi_tool"));
                listRespaldo.add(par);
            }

            rs.close();
            ps.close();

            if(connect!=null)
        {
                connect.close();
        }
            /*
                conexionDS = new ConexionDS();
                ds = conexionDS.getDataSource(ds_cfdi);
                String sql = "SELECT * FROM cfdi_respaldo WHERE cfdi_rfc='"+rfc+"'";
                JdbcTemplate jdbcTemplate = new JdbcTemplate(ds); 
                listRespaldo = (ArrayList<RespaldoVo>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<RespaldoVo>(RespaldoVo.class));
                */
        }catch(Exception ex){
                        System.out.println(ex.getMessage());
        }

        return listRespaldo;
    }
    
    public Boolean getUsuarios(String user,String pswd) throws Exception
	{
		ArrayList<UsuarioVo> usuarioDtos = new ArrayList<UsuarioVo>();
                Boolean actualizado = false;
	try
        {
            ConnectionData conexion = new ConnectionData();
            Connection connect = conexion.getConnection();

            PreparedStatement ps = connect.prepareStatement("SELECT * FROM cfdi_usuarios WHERE usuario_cuenta=? AND usuario_passwd=?");
            ps.setString(1, user);
            ps.setString(2, pswd);
            ResultSet rs = ps.executeQuery();

           // Usuario u = null;
            while (rs.next()) {
                actualizado=true;
            }

            rs.close();
            ps.close();

            if(connect!=null)
        {
                connect.close();
        }
        }catch(Exception ex){
                System.out.println(ex.getMessage());
        }
      
		
		return actualizado;
	}
    
    public boolean registraCancelacion(String nombre,String fecha,String uuid,String rfcEmisor,String rfcReceptor,String total) throws Exception
	{
		boolean var=false;
                try {
		ConnectionData conexion = new ConnectionData();
                Connection connect = conexion.getConnection();
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dsConnect); 
		
		Locale espanol = new Locale("es","ES");
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss",espanol);
		String fechaLocal = dateFormat.format(new Date());
		try{
																																																																																																																																																																				    //version,                               serie,                                folio,                                fecha,                                formaDePago,                              condicionesDePago,                                Subtotal,                                TipoCambio,                                   Moneda,                             Total,                             TipoDeComprobante,                                    MetodoDePago,                               LugarExpedicion,                                NumCtaPago,                                  erfc,                              enombre,                                  edcalle,                              ednoExterior,                                 edcolonia,                               edreferencia,                                  edmunicipio,                              edestado,                                edpais,                                edcodigoPostal,                                  ExpedidoEn,                              Regimen,                                  rfc,                             nombre,                                  correoElectronico,                                 calle,                              noExterior,                                  colonia,                              referencia,                                municipio,                                estado,                                pais,                                codigoPostal,                                cantidad,                                  unidad,                              noIdentificacion,                                descripcion,                                valorUnitario,                                importe,                                 totalImpuestosTrasladados,                               cfdi_items, generado,                         posicion,                                  matricula,                              seccion,                                grado,                                grupo,                                  curp,                              referencia_1,                                nombre_1,                                ednoInterior,                                noInterior,                                comments										
		//System.out.println("INSERT INTO [cfdi_jafra].[dbo].[cfdi_filtradas] (cfdi_numero, cfdi_fecha_up, cfdi_items, cfdi_serie, generado) VALUES ('"+folio+"', 'GETDATE()', '"+items+"', '"+serie+"', '1')");
		
			String query="INSERT INTO cfdi_cancelaciones (nombre,fecha,uuid,estado,rfcEmisor,rfcReceptor,fechaTimbrado,importe) VALUES ('"+nombre+"','"+fechaLocal+"','"+uuid+"','P','"+rfcEmisor+"','"+rfcReceptor+"','"+fecha+"','"+total+"')";
//			jdbcTemplate.update(sql,nombre,fechaLocal,uuid,"P",rfcEmisor,rfcReceptor,fecha,total);
			 PreparedStatement ps = connect.prepareStatement(query);
			
                        int result= ps.executeUpdate();
                            if (result > 0) {
                                var=true;
//                                System.out.println("Se guardo cancelación con uuid: "+uuid);
                            }  
		}catch(Exception e){
			System.out.println("La insersión de cancelacion "+uuid+", fallo por: "+e);
		}
                if(connect!=null)
        {
        	connect.close();
        }
		} catch (Exception e) {
                    System.out.println("error - "+e.getMessage());
            }
		return var;
	} 
    
    public Boolean actualizaCancelaciones(String uuid) throws Exception
	{
		Boolean actualizado = false;
		
		try
		{
                ConnectionData conexion = new ConnectionData();
                Connection connect = conexion.getConnection();
			
                String sql = "UPDATE cfdi_cancelaciones SET estado ='C' WHERE uuid ='"+uuid+"'";
	        PreparedStatement ps = connect.prepareStatement(sql);
	        //System.out.println("AC");
                int result= ps.executeUpdate();
                if (result > 0) {
                    actualizado=true;
//                    System.out.println("Se actualizo cancelación con uuid: "+uuid);
                }  
	        
		}catch (Exception e) {
                    System.out.println("error - "+e.getMessage());
            }	
		
		return actualizado;
	}
    
    public Boolean actualizaCancelacionCfdi(String uuid) throws Exception
	{
		Boolean actualizado = false;
		
		try
		{
                ConnectionData conexion = new ConnectionData();
                Connection connect = conexion.getConnection(); 
			
		String sql = "UPDATE cfdis SET estatus = 'C' WHERE uuid ='"+uuid+"'";
                 PreparedStatement ps = connect.prepareStatement(sql);
//	        jdbcTemplate.update(sql, "C",uuid);
                int result= ps.executeUpdate();
                if (result > 0) {
                    actualizado=true;
//                    System.out.println("Se actualizo estatus cfdis con uuid: "+uuid);
                }
		}catch (Exception e) {
                    System.out.println("error - "+e.getMessage());
            }	
		
		return actualizado;
	}
    
     public String[] buscaCancelacion(String uuid) throws Exception{
    	ArrayList<Cancelaciones> listCfdi = new ArrayList<Cancelaciones>();
    	String [] arreglo=null;
		try 
		{
			ConnectionData conexion = new ConnectionData();
                        Connection connect = conexion.getConnection();
//			String sql = "SELECT uuid,fecha,rfcEmisor,rfcReceptor,estado,fechaTimbrado,importe FROM cfdi_cancelaciones WHERE uuid='"+uuid+"';";
			PreparedStatement ps = connect.prepareStatement("SELECT uuid,fecha,rfcEmisor,rfcReceptor,estado,fechaTimbrado,importe FROM cfdi_cancelaciones WHERE uuid=?");
                        ps.setString(1, uuid);
                        ResultSet rs = ps.executeQuery();

           // Usuario u = null;
            while (rs.next()) {
                arreglo=new String[7];
                arreglo[0]=rs.getString("uuid");
                arreglo[1]=rs.getString("fecha");
                arreglo[2]=rs.getString("rfcEmisor");
                arreglo[3]=rs.getString("rfcReceptor");
                arreglo[4]=rs.getString("estado");
                arreglo[5]=rs.getString("fechaTimbrado");
                arreglo[6]=rs.getString("importe");
            }

            rs.close();
            ps.close();

            if(connect!=null)
        {
                connect.close();
        }
                }catch(Exception ex){
                System.out.println("Error - "+ex.getMessage());
        }
		
		return arreglo;
		
    }
    
    public String[] buscarInfoUUID(String uuid) throws Exception{
    	String [] arreglo=null;
		try 
		{
			ConnectionData conexion = new ConnectionData();
                        Connection connect = conexion.getConnection();
//			String sql = "SELECT uuid,fecha,rfcEmisor,rfcReceptor,estado,fechaTimbrado,importe FROM cfdi_cancelaciones WHERE uuid='"+uuid+"';";
			PreparedStatement ps = connect.prepareStatement("SELECT serie,folio,tipoComprobante,estatus,uuid,rfcReceptor FROM cfdis WHERE uuid=?");
                        ps.setString(1, uuid);
                        ResultSet rs = ps.executeQuery();

           // Usuario u = null;
            while (rs.next()) {
                arreglo=new String[6];
                arreglo[0]=rs.getString("serie");
                arreglo[1]=rs.getString("folio");
                arreglo[2]=rs.getString("tipoComprobante");
                arreglo[3]=rs.getString("estatus");
                arreglo[4]=rs.getString("uuid");
                arreglo[5]=rs.getString("rfcReceptor");                
            }

            rs.close();
            ps.close();

            if(connect!=null)
        {
                connect.close();
        }
                }catch(Exception ex){
                System.out.println("Error - "+ex.getMessage());
        }
		
		return arreglo;
		
    }
    
    public String[] buscaUUIDTimbrado(String serie,String folio) throws Exception{
    	String [] arreglo=null;
		try 
		{
			ConnectionData conexion = new ConnectionData();
                        Connection connect = conexion.getConnection();
//			String sql = "SELECT uuid,fecha,rfcEmisor,rfcReceptor,estado,fechaTimbrado,importe FROM cfdi_cancelaciones WHERE uuid='"+uuid+"';";
			PreparedStatement ps = connect.prepareStatement("SELECT uuid,xml FROM cfdis WHERE serie=? and folio=?");
                        ps.setString(1, serie);
                        ps.setString(2, folio);
                        ResultSet rs = ps.executeQuery();

           // Usuario u = null;
            while (rs.next()) {
                arreglo=new String[2];
                arreglo[0]=rs.getString("uuid");
                arreglo[1]=rs.getString("xml");               
            }

            rs.close();
            ps.close();

            if(connect!=null)
        {
                connect.close();
        }
                }catch(Exception ex){
                System.out.println("Error - "+ex.getMessage());
        }
		
		return arreglo;
		
    }
    
    public Boolean validaExistencia(String serie, String folio) throws Exception
    {
//        System.out.println("queryadministrador serie= "+serie+" folio = "+folio);
        Boolean actualizado = false;

        try
        {
            ConnectionData conexion = new ConnectionData();
            Connection connect = conexion.getConnection();
//            System.out.println("SELECT * FROM cfdi_filtradas WHERE cfdi_numero = "+folio +"AND cfdi_serie= "+serie+" ");
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM cfdi_filtradas WHERE cfdi_numero = ? AND cfdi_serie= '"+Utils.nullToString(serie)+"'");
            ps.setString(1, folio);
            ResultSet rs = ps.executeQuery();

           // Usuario u = null;
            while (rs.next()) {
                actualizado=true;
            }

            rs.close();
            ps.close();

            if(connect!=null)
        {
                connect.close();
        }
        }catch(Exception ex){
                System.out.println(ex.getMessage());
        }



        return actualizado;
    }
    
    public Boolean actualizaCfdis(String serie, String folio, String fechaemision, String fechatimbrado, String subtotal, String total, String tipocomprobante, String xml,String uuid,String rfcemisor,String rfcreceptor,String idoc) throws Exception
	{
            boolean resp=false; 
            try {
                ConnectionData conexion = new ConnectionData();
                Connection connect = conexion.getConnection();
		String encodedString = Base64.getEncoder().encodeToString(idoc.getBytes());

		
			if(!validaCfdis(folio, serie))
			{
				try{
					String query="";
					if(serie==null){
						query="INSERT INTO cfdis (version, serie, folio, fechaEmision, fechaTimbrado, subTotal, total, tipoComprobante, xml, estatus,uuid,rfcEmisor,rfcReceptor,xml_idoc) VALUES ('4.0','','"+folio+"','"+fechaemision+"', '"+fechatimbrado+"','"+subtotal+"', '"+total+"', '"+tipocomprobante+"', '"+xml+"','F','"+uuid+"','"+rfcemisor+"','"+rfcreceptor+"','"+encodedString+"')";
					}else{
						query="INSERT INTO cfdis (version, serie, folio, fechaEmision, fechaTimbrado, subTotal, total, tipoComprobante, xml, estatus,uuid,rfcEmisor,rfcReceptor,xml_idoc) VALUES ('4.0','"+serie+"','"+folio+"', '"+fechaemision+"', '"+fechatimbrado+"','"+subtotal+"', '"+total+"', '"+tipocomprobante+"', '"+xml+"','F','"+uuid+"','"+rfcemisor+"','"+rfcreceptor+"','"+encodedString+"')";
					}
					
                         PreparedStatement ps = connect.prepareStatement(query);
                        int result= ps.executeUpdate();
                            if (result > 0) {
                                resp=true;
//                                System.out.println("Se inserto correctamente el cfdi, con serie/folio: "+serie+folio);
                            }        
//					statement = connect.createStatement();																																																																																																																																								    //version,                               serie,                                folio,                                fecha,                                formaDePago,                              condicionesDePago,                                Subtotal,                                TipoCambio,                                   Moneda,                             Total,                             TipoDeComprobante,                                    MetodoDePago,                               LugarExpedicion,                                NumCtaPago,                                  erfc,                              enombre,                                  edcalle,                              ednoExterior,                                 edcolonia,                               edreferencia,                                  edmunicipio,                              edestado,                                edpais,                                edcodigoPostal,                                  ExpedidoEn,                              Regimen,                                  rfc,                             nombre,                                  correoElectronico,                                 calle,                              noExterior,                                  colonia,                              referencia,                                municipio,                                estado,                                pais,                                codigoPostal,                                cantidad,                                  unidad,                              noIdentificacion,                                descripcion,                                valorUnitario,                                importe,                                 totalImpuestosTrasladados,                               cfdi_items, generado,                         posicion,                                  matricula,                              seccion,                                grado,                                grupo,                                  curp,                              referencia_1,                                nombre_1,                                ednoInterior,                                noInterior,                                comments										
//					statement.execute(query);
//					System.out.println("Se inserto correctamente el cfdi, con serie/folio: "+serie+folio);
				}catch(Exception e){
					System.out.println("La insersión al cfdi, con serie/folio: "+serie+folio+", fallo por: "+e);				}
			}
		if(connect!=null)
        {
        	connect.close();
        }
            } catch (Exception e) {
                System.out.println("error - "+e.getMessage());
            }
            return resp;
	}
    
    public Boolean validaCfdis(String folio, String serie) throws Exception
	{
            Boolean existe = false;
            try {
                ConnectionData conexion = new ConnectionData();
                Connection connect = conexion.getConnection();
		
                PreparedStatement ps = connect.prepareStatement("SELECT * FROM cfdis WHERE folio = ? AND serie= '"+Utils.nullToString(serie)+"'");
                ps.setString(1, folio);
               // ps.setString(2, folio);
                ResultSet rs = ps.executeQuery();
                
                
                
        while(rs.next())
        {
            existe=true;
        }
        
        if(connect!=null)
        {
        	connect.close();
        }
            } catch (Exception e) {
            }
		
        
		return existe;
	}
    
    public Boolean validaFiltradas(String folio, String serie) throws Exception
	{
            Boolean existe = false;
            try {
                
                ConnectionData conexion = new ConnectionData();
                Connection connect = conexion.getConnection();

		PreparedStatement ps = connect.prepareStatement("SELECT * FROM cfdi_filtradas WHERE cfdi_numero = ? AND cfdi_serie= '"+Utils.nullToString(serie)+"'");
                ps.setString(1, folio);
               // ps.setString(2, folio);
                ResultSet rs = ps.executeQuery();
            
        while(rs.next())
        {
            existe=true;
        }
        
        if(connect!=null)
        {
        	connect.close();
        }
            } catch (Exception e) {
                
            }
        
		return existe;
	}
    
    public Boolean actualizaFiltradas(String folio, String serie, String items) throws Exception
	{
            Boolean insert=false;
            try {
                ConnectionData conexion = new ConnectionData();
                Connection connect = conexion.getConnection();
		
		
			if(!validaFiltradas(folio,Utils.nullToString(serie)))
			{
                            
                         PreparedStatement ps = connect.prepareStatement("INSERT INTO cfdi_filtradas (cfdi_numero, cfdi_fecha_up, cfdi_items, cfdi_serie, generado) VALUES (?,  NOW(), ?, ?, '1')");
			 ps.setString(1, folio);
                         ps.setString(2, items);
                         ps.setString(3, serie);
                      int result= ps.executeUpdate();
                            if (result > 0) {
                                insert=true;
                            }
			}
		if(connect!=null)
        {
        	connect.close();
        }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
		return insert;
	}
}
