package edifactwsradiopolis.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import edifactwsradiopolis.utils.Propiedades;





public class ConnectionData {
	
	private Connection connect = null;
	private Propiedades  prop=null;
	
	public Connection getConnection()throws Exception
	{
            prop=new Propiedades();
            String user=prop.getPropValues("mysql_user");
            String passw=prop.getPropValues("mysql_password");
            String host=prop.getPropValues("mysql_host");
            String port=prop.getPropValues("mysql_port");
//            System.out.println("Datos de conexión: "+user+", "+passw+", "+host+", "+port);

            String shema=prop.getPropValues("mysql_database");;
/*		
//prop=new Propiedades();
            String user="azamudio";
            String passw="@Z@m4d10#^..";
            String host="192.168.100.145";
            String port="3306";
            System.out.println("Datos de conexión: "+user+", "+passw+", "+host+", "+port);

            String shema="cfdi_greiner_test";*/

//            Class.forName("com.mysql.jdbc.Driver");
//            connect = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+shema+"?user="+user+"&password="+passw+"&useSSL=false");
            Class.forName("org.mariadb.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+shema+"?user="+user+"&password="+passw+"&useSSL=false");
            return connect;
	}
	public Connection getConnectionSql(String usuario)throws Exception
	{
		prop = new Propiedades();
	   String user=prop.getPropValues("sql_user");
	   String passw=prop.getPropValues("sql_password");
	   String host=prop.getPropValues("sql_host");
	   String port=prop.getPropValues("sql_port");
	  
	   String shema=prop.getPropValues("sql_database");
	   String url = "jdbc:sqlserver://"+host+"\\dbo:"+port+";databaseName="+shema+"";
	        
	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	   connect = DriverManager.getConnection(url, user, passw);
	   
	   /*String user="cancela";
	   String passw="cancela";
	   String host="localhost";
	   String port="1433";
	   String shema="cfdi_cancelacion";
	   String url = "jdbc:sqlserver://"+host+"\\dbo:"+port+";databaseName="+shema+"";
	        
	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	   connect = DriverManager.getConnection(url, user, passw);*/
	   return connect;

	}
	
}
