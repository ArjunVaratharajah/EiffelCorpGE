/**
 * IfServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.gustaveeiffel.fr.eiffelcorp.ifservice.common;

public class IfServiceServiceLocator extends org.apache.axis.client.Service implements org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceService {

    public IfServiceServiceLocator() {
    }


    public IfServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IfServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IfService
    private java.lang.String IfService_address = "http://localhost:8080/EiffelCorp/services/IfService";

    public java.lang.String getIfServiceAddress() {
        return IfService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IfServiceWSDDServiceName = "IfService";

    public java.lang.String getIfServiceWSDDServiceName() {
        return IfServiceWSDDServiceName;
    }

    public void setIfServiceWSDDServiceName(java.lang.String name) {
        IfServiceWSDDServiceName = name;
    }

    public org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService getIfService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IfService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIfService(endpoint);
    }

    public org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService getIfService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceSoapBindingStub _stub = new org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getIfServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIfServiceEndpointAddress(java.lang.String address) {
        IfService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceSoapBindingStub _stub = new org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceSoapBindingStub(new java.net.URL(IfService_address), this);
                _stub.setPortName(getIfServiceWSDDServiceName());
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
        if ("IfService".equals(inputPortName)) {
            return getIfService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://server.ifservice.eiffelcorp.fr.gustaveeiffel.org", "IfServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://server.ifservice.eiffelcorp.fr.gustaveeiffel.org", "IfService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IfService".equals(portName)) {
            setIfServiceEndpointAddress(address);
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
