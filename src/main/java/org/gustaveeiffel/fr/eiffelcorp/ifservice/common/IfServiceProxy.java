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
  
  
}