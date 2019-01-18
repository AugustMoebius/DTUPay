package payment.networking;

public enum WebEndpoints {
  BASE("http://127.0.0.1:8585");
  //BASE("http://02267-munich.compute.dtu.dk:8585");

  public final String url;

  WebEndpoints(String url) {
    this.url = url;
  }
}