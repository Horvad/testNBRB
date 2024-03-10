package service.fabric;

import service.ServiceSend;
import service.api.IServiceSend;

public class ServiceSendSingleton {
    private static volatile IServiceSend instance;

    private ServiceSendSingleton() {
    }

    public static IServiceSend getInstance() {
        if(instance==null){
            synchronized (ServiceSendSingleton.class){
                if(instance==null){
                    instance = new ServiceSend();

                }
            }
        }
        return instance;
    }
}