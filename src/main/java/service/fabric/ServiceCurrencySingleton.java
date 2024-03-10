package service.fabric;

import dao.fabric.DaoCurrencySingleton;
import service.ServiceCurrency;
import service.api.IServiceCurrency;

public class ServiceCurrencySingleton {
    private static volatile IServiceCurrency instance;

    private ServiceCurrencySingleton() {
    }

    public static IServiceCurrency getInstance(){
        if(instance==null){
            synchronized (ServiceCurrencySingleton.class){
                if(instance==null){
                    instance = new ServiceCurrency(
                            DaoCurrencySingleton.getInstance(),
                            ServiceSendSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}