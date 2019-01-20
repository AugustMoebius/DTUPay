package payment.networking;

public enum WebEndpoints {
  BASEPAYMENT("http://127.0.0.1:8585"),
  BASECUSTOMER("http://127.0.0.1:8686"),
  BASEMERCHANT("http://127.0.0.1:8787"),
  BASETOKEN("http://127.0.0.1:8888"),
  BASEREPORT("http://127.0.0.1:8989");
  //BASE("http://02267-munich.compute.dtu.dk:8585"), BASEC("http://127.0.0.1:8080");
  //BASE("http://02267-munich.compute.dtu.dk:8585"), BASEC("http://02267-munich.compute.dtu.dk:8686");

  public final String url;

  WebEndpoints(String url) {
    this.url = url;
  }
}