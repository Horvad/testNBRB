package service;

import core.Currency;
import dao.api.IDaoCurrency;
import service.api.IServiceCurrency;
import service.api.IServiceSend;

import java.util.LinkedList;
import java.util.List;

public class ServiceCurrency implements IServiceCurrency {
    private final IDaoCurrency daoCurrency;
    private final IServiceSend serviceSend;

    public ServiceCurrency(IDaoCurrency daoCurrency, IServiceSend serviceSend) {
        this.daoCurrency = daoCurrency;
        this.serviceSend = serviceSend;
    }

    @Override
    public List<Currency> updateCurrency() {
        List<Currency> currencyList = serviceSend.getCurrency();
        save(currencyList);
        return currencyList;
    }

    @Override
    public void save(List<Currency> currencies) {
        List<Currency> currenciesListFromDataBase = daoCurrency.getCurrency();
        if (currenciesListFromDataBase == null || currenciesListFromDataBase.size() == 0){
            daoCurrency.saveCurrency(currencies);
        } else {
            List<Currency> currenciesToAdd = new LinkedList<>();
            for (Currency newCurrency : currencies) {
                boolean needToAdd = true;
                for (Currency currencyFromDataBase : currenciesListFromDataBase) {
                    if (newCurrency.getId() == currencyFromDataBase.getId() ){
                        needToAdd = false;
                        if(!newCurrency.equals(currencyFromDataBase)) {
                            daoCurrency.remove(currencyFromDataBase.getId());
                            currenciesToAdd.add(newCurrency);
                        }
                        break;
                    }
                }
                if (needToAdd){
                    currenciesToAdd.add(newCurrency);
                }
            }
            daoCurrency.saveCurrency(currenciesToAdd);
        }
    }

    @Override
    public List<Currency> getCurrency(String typeCurrency) {
        List<Currency> currency = daoCurrency.getCurrency(typeCurrency);
        if(currency == null || currency.size() == 0) {
            throw new IllegalArgumentException("Данной валюты не существуетт");
        }
        return daoCurrency.getCurrency(typeCurrency);
    }

    @Override
    public List<Currency> getCurrency() {
        List<Currency> currency = daoCurrency.getCurrency();
        if (currency == null || currency.size() == 0){
            currency = updateCurrency();
            if (currency == null || currency.size() == 0){
                throw new IllegalArgumentException("В базе нет валют и не получилось достать из apiNBRB");
            }
            else {
                return currency;
            }
        } else {
            return currency;
        }
    }
}