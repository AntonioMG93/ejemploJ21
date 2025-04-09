/**
 * ConsultaPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consulta_pkg;

public interface ConsultaPortType extends java.rmi.Remote {

    /**
     * consulta Estado de un Comprobante.
     * @param consulta
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String consultaComprobante(java.lang.String consulta) throws java.rmi.RemoteException;
}
