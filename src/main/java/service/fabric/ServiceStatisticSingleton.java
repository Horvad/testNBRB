package service.fabric;

import dao.fabric.DaoStatisticSingleton;
import service.ServiceStatistic;
import service.api.IServiceStatistic;

public class ServiceStatisticSingleton {
    private static volatile IServiceStatistic instance;

    private ServiceStatisticSingleton() {
    }

    public static IServiceStatistic getInstance() {
        if(instance==null){
            synchronized (ServiceStatisticSingleton.class){
                if(instance==null){
                    instance = new ServiceStatistic(
                            DaoStatisticSingleton.getInstance(),
                            ServiceCurrencySingleton.getInstance(),
                            ServiceSendSingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}