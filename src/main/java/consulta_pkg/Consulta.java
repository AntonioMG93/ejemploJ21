/**
 * Consulta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consulta_pkg;

public interface Consulta extends javax.xml.rpc.Service {
    public java.lang.String getconsultaPortAddress();

    public consulta_pkg.ConsultaPortType getconsultaPort(String ambiente) throws javax.xml.rpc.ServiceException;

    public consulta_pkg.ConsultaPortType getconsultaPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
