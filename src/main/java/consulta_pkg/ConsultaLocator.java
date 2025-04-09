/**
 * ConsultaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consulta_pkg;

//import org.apache.logging.log4j.Logger;


public class ConsultaLocator extends org.apache.axis.client.Service implements consulta_pkg.Consulta {
//private static Logger log = Logger.getLogger(ConsultaLocator.class);
    public ConsultaLocator() {
    }


    public ConsultaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for consultaPort
    private java.lang.String consultaPort_address = null;

    public java.lang.String getconsultaPortAddress() {
        return consultaPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String consultaPortWSDDServiceName = "consultaPort";

    public java.lang.String getconsultaPortWSDDServiceName() {
        return consultaPortWSDDServiceName;
    }

    public void setconsultaPortWSDDServiceName(java.lang.String name) {
        consultaPortWSDDServiceName = name;
    }

    public consulta_pkg.ConsultaPortType getconsultaPort(String ambiente) throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint = null;
       
       if(ambiente.equalsIgnoreCase("produccion")){
    	   consultaPort_address="https://cfdiee.com/wsconsulta/clienteConsultaDocumento.php";
       }else{
    	   consultaPort_address="http://edifactmex.dyndns-ip.com:8302/wsCancelacionPruebas/clienteConsultaDocumento.php";
       }
//       log.info("consultaPort_address- "+consultaPort_address);
        try {
//        System.setProperty("https.proxyHost", "sv3mx1wf02.adm02.ads.gbo.com");
//        System.setProperty("https.proxyPort", "8080");
        System.setProperty("https.proxyHost", "");
        System.setProperty("https.proxyPort", "");
            endpoint = new java.net.URL(consultaPort_address); 
        }
        catch (java.net.MalformedURLException e) {
//            log.error(e.getMessage());
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getconsultaPort(endpoint);
    }

    public consulta_pkg.ConsultaPortType getconsultaPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            
            consulta_pkg.ConsultaBindingStub _stub = new consulta_pkg.ConsultaBindingStub(portAddress, this);
            _stub.setPortName(getconsultaPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setconsultaPortEndpointAddress(java.lang.String address) {
        consultaPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (consulta_pkg.ConsultaPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                consulta_pkg.ConsultaBindingStub _stub = new consulta_pkg.ConsultaBindingStub(new java.net.URL(consultaPort_address), this);
                _stub.setPortName(getconsultaPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("consultaPort".equals(inputPortName)) {
            return getconsultaPort("Produccionr");
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:consulta", "consulta");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:consulta", "consultaPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("consultaPort".equals(portName)) {
            setconsultaPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
