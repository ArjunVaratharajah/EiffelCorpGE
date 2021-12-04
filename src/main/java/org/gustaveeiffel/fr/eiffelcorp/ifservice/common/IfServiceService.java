/**
 * IfServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.gustaveeiffel.fr.eiffelcorp.ifservice.common;

public interface IfServiceService extends javax.xml.rpc.Service {
    public java.lang.String getIfServiceAddress();

    public org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService getIfService() throws javax.xml.rpc.ServiceException;

    public org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService getIfService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
