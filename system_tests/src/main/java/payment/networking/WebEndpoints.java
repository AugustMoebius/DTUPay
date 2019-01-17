package payment.networking;

public enum WebEndpoints {
  BASE("http://127.0.0.1:8383");

  public final String url;

  WebEndpoints(String url) {
    this.url = url;
  }
}