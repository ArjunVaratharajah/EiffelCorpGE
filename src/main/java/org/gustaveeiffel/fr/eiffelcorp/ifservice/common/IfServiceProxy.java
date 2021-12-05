package org.gustaveeiffel.fr.eiffelcorp.ifservice.common;

public class IfServiceProxy implements org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService {
  private String _endpoint = null;
  private org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService ifService = null;
  
  public IfServiceProxy() {
    _initIfServiceProxy();
  }
  
  public IfServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIfServiceProxy();
  }
  
  private void _initIfServiceProxy() {
    try {
      ifService = (new org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceServiceLocator()).getIfService();
      if (ifService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ifService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ifService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ifService != null)
      ((javax.xml.rpc.Stub)ifService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService getIfService() {
    if (ifService == null)
      _initIfServiceProxy();
    return ifService;
  }
  
  public java.lang.String getProducts() throws java.rmi.RemoteException{
    if (ifService == null)
      _initIfServiceProxy();
    return ifService.getProducts();
  }
  
  public java.lang.String getCustomerFullname(int customerId) throws java.rmi.RemoteException{
    if (ifService == null)
      _initIfServiceProxy();
    return ifService.getCustomerFullname(customerId);
  }
  
  public java.lang.String getCustomerInfo(int customerId) throws java.rmi.RemoteException{
    if (ifService == null)
      _initIfServiceProxy();
    return ifService.getCustomerInfo(customerId);
  }
  
  public java.lang.String getCart(int customerId) throws java.rmi.RemoteException{
    if (ifService == null)
      _initIfServiceProxy();
    return ifService.getCart(customerId);
  }
  
  public java.lang.String deleteProductFromCart(int productId, int customerId) throws java.rmi.RemoteException{
    if (ifService == null)
      _initIfServiceProxy();
    return ifService.deleteProductFromCart(productId, customerId);
  }
  
  public java.lang.String addProductToCart(int productId, int customerId) throws java.rmi.RemoteException{
    if (ifService == null)
      _initIfServiceProxy();
    return ifService.addProductToCart(productId, customerId);
  }
  
  public java.lang.String payCart(int customerId) throws java.rmi.RemoteException{
    if (ifService == null)
      _initIfServiceProxy();
    return ifService.payCart(customerId);
  }
  
  
}