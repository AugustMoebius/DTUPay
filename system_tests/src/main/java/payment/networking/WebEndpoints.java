package payment.networking;

public enum WebEndpoints {
  BASE("http://127.0.0.1:8585"), BASEC("http://127.0.0.1:8686");
  //BASE("http://02267-munich.compute.dtu.dk:8585"), BASEC("http://02267-munich.compute.dtu.dk:8686");

  public final String url;

  WebEndpoints(String url) {
    this.url = url;
  }
}