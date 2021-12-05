/**
 * IfService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.gustaveeiffel.fr.eiffelcorp.ifservice.common;

public interface IfService extends java.rmi.Remote {
    public java.lang.String getProducts() throws java.rmi.RemoteException;
    public java.lang.String deleteProductFromCart(int productId, int customerId) throws java.rmi.RemoteException;
    public java.lang.String getCustomer(int customerId) throws java.rmi.RemoteException;
    public java.lang.String getCart(int customerId) throws java.rmi.RemoteException;
    public java.lang.String addProductToCart(int productId, int customerId) throws java.rmi.RemoteException;
    public java.lang.String payCart(int customerId) throws java.rmi.RemoteException;
}
