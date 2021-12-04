/**
 * ProductIfServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product;

public class ProductIfServiceServiceLocator extends org.apache.axis.client.Service implements org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfServiceService {

    public ProductIfServiceServiceLocator() {
    }


    public ProductIfServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProductIfServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProductIfService
    private java.lang.String ProductIfService_address = "http://localhost:8080/EiffelCorp/services/ProductIfService";

    public java.lang.String getProductIfServiceAddress() {
        return ProductIfService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProductIfServiceWSDDServiceName = "ProductIfService";

    public java.lang.String getProductIfServiceWSDDServiceName() {
        return ProductIfServiceWSDDServiceName;
    }

    public void setProductIfServiceWSDDServiceName(java.lang.String name) {
        ProductIfServiceWSDDServiceName = name;
    }

    public org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfService getProductIfService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProductIfService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProductIfService(endpoint);
    }

    public org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfService getProductIfService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfServiceSoapBindingStub _stub = new org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getProductIfServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProductIfServiceEndpointAddress(java.lang.String address) {
        ProductIfService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfServiceSoapBindingStub _stub = new org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfServiceSoapBindingStub(new java.net.URL(ProductIfService_address), this);
                _stub.setPortName(getProductIfServiceWSDDServiceName());
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
        if ("ProductIfService".equals(inputPortName)) {
            return getProductIfService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://product.server.ifservice.eiffelcorp.fr.gustaveeiffel.org", "ProductIfServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://product.server.ifservice.eiffelcorp.fr.gustaveeiffel.org", "ProductIfService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProductIfService".equals(portName)) {
            setProductIfServiceEndpointAddress(address);
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
