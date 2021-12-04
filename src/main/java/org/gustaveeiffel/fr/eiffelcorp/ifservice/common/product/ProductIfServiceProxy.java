package org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product;

public class ProductIfServiceProxy implements org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfService {
  private String _endpoint = null;
  private org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfService productIfService = null;
  
  public ProductIfServiceProxy() {
    _initProductIfServiceProxy();
  }
  
  public ProductIfServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initProductIfServiceProxy();
  }
  
  private void _initProductIfServiceProxy() {
    try {
      productIfService = (new org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfServiceServiceLocator()).getProductIfService();
      if (productIfService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)productIfService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)productIfService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (productIfService != null)
      ((javax.xml.rpc.Stub)productIfService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfService getProductIfService() {
    if (productIfService == null)
      _initProductIfServiceProxy();
    return productIfService;
  }
  
  public java.lang.String getProducts() throws java.rmi.RemoteException{
    if (productIfService == null)
      _initProductIfServiceProxy();
    return productIfService.getProducts();
  }
  
  
}