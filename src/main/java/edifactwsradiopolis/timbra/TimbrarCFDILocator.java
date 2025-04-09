package edifactwsradiopolis.timbra;

public class TimbrarCFDILocator extends org.apache.axis.client.Service implements TimbrarCFDI {
		
    public TimbrarCFDILocator(String ambiente) {        
    	if(ambiente.equals("produccion"))
        {
            //liga 4.0
            timbrarCFDIPort_address="https://www.edifactmx-pac.com/serviceCFDI4/timbraCFDI40.php?wsdl";
        //liga 3.3
//        timbrarCFDIPort_address="https://www.edifactmx-pac.com/serviceCFDI/timbraCFDI.php?wsdl";
        }
        else if(ambiente.equals("busca1"))
        {  
//        	System.out.println("Entro con url para buscar cfdi");
        	//http://comprobantes-fiscales.com/service/timbrarCFDI.php?wsdl
        	timbrarCFDIPort_address="https://www.edifactmx-pac.com/serviceCFDI4/regresaCFDIU.php?wsdl";
        	//timbrarCFDIPort_address="https://207.21.196.135/service/timbrarCFDI.php?wsdl";
        }
        else if(ambiente.equals("busca2"))
        { //http://comprobantes-fiscales.com/service/timbrarCFDI.php?wsdl
        	timbrarCFDIPort_address="https://www.edifactmx-pac.com/serviceCFDI4/regresaCFDIU.php?wsdl";
        	//timbrarCFDIPort_address="https://207.21.196.136/service/timbrarCFDI.php?wsdl";
        }
        else if(ambiente.equals("busca3"))
        { //http://comprobantes-fiscales.com/service/timbrarCFDI.php?wsdl
        	timbrarCFDIPort_address="https://www.edifactmx-pac.com/serviceCFDI4/regresaCFDIU.php?wsdl";
        	//timbrarCFDIPort_address="https://207.21.196.137/service/timbrarCFDI.php?wsdl";
        }
        else if(ambiente.equals("busca4"))
        { //http://comprobantes-fiscales.com/service/timbrarCFDI.php?wsdl
        	timbrarCFDIPort_address="https://www.edifactmx-pac.com/serviceCFDI4/regresaCFDIU.php?wsdl";
        	//timbrarCFDIPort_address="https://207.21.196.138/service/timbrarCFDI.php?wsdl";
        }
        else if(ambiente.equals("busca5"))
        { //http://comprobantes-fiscales.com/service/timbrarCFDI.php?wsdl
        	timbrarCFDIPort_address="https://www.edifactmx-pac.com/serviceCFDI/regresaCFDIU.php?wsdl";
        	//timbrarCFDIPort_address="https://207.21.196.139/service/timbrarCFDI.php?wsdl";
        }
        else
        {
            //http://comprobantes-fiscales.com/service/timbrarCFDI.php?wsdl
            //4.0
        	timbrarCFDIPort_address="https://comprobantes-fiscales.com/serviceCFDI4/timbraCFDI.php?wsdl";
        	//3.3
        	//timbrarCFDIPort_address="http://edifactmex.dyndns-ip.com/service/timbraCFDI.php";
//        	timbrarCFDIPort_address="http://comprobantes-fiscales.com/service/timbraCFDI.php?wsdl";
//        	timbrarCFDIPort_address="https://comprobantes-fiscales.com/service/timbraCFDIC.php?wsdl"; //pruebas
        	
        	
        	//timbrarCFDIPort_address="http://comprobantes-fiscales.com/service/timbraCFDIC.php?wsdl"; //SAT pruebas
        }
    }


    public TimbrarCFDILocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TimbrarCFDILocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for timbrarCFDIPort
    private java.lang.String timbrarCFDIPort_address = "";

    public java.lang.String gettimbrarCFDIPortAddress() {
        return timbrarCFDIPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String timbrarCFDIPortWSDDServiceName = "timbrarCFDIPort";

    public java.lang.String gettimbrarCFDIPortWSDDServiceName() {
        return timbrarCFDIPortWSDDServiceName;
    }

    public void settimbrarCFDIPortWSDDServiceName(java.lang.String name) {
        timbrarCFDIPortWSDDServiceName = name;
    }

    public TimbrarCFDIPortType gettimbrarCFDIPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(timbrarCFDIPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return gettimbrarCFDIPort(endpoint);
    }

    public TimbrarCFDIPortType gettimbrarCFDIPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            TimbrarCFDIBindingStub _stub = new TimbrarCFDIBindingStub(portAddress, this);
            _stub.setPortName(gettimbrarCFDIPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void settimbrarCFDIPortEndpointAddress(java.lang.String address) {
        timbrarCFDIPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (TimbrarCFDIPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                TimbrarCFDIBindingStub _stub = new TimbrarCFDIBindingStub(new java.net.URL(timbrarCFDIPort_address), this);
                _stub.setPortName(gettimbrarCFDIPortWSDDServiceName());
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
        if ("timbrarCFDIPort".equals(inputPortName)) {
            return gettimbrarCFDIPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:respuestaTimbrado", "timbrarCFDI");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:respuestaTimbrado", "timbrarCFDIPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("timbrarCFDIPort".equals(portName)) {
            settimbrarCFDIPortEndpointAddress(address);
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
