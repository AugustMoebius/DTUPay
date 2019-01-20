package networking.adapters.message_queue;


import java.util.Properties;

public enum HostMessageQueue {
    //BASE("http://127.0.0.1:8585");
    //BASE("http://02267-munich.compute.dtu.dk:8585");
    BASE("02267-munich.compute.dtu.dk");

    public final String url;


    HostMessageQueue(String url) {
        java.util.Properties p = new Properties();
        
        this.url = url;
    }
}
