package ru.sfedu.hibernate.lab4;

import ru.sfedu.hibernate.enums.CoverType;
import ru.sfedu.hibernate.lab4.model.collection.CoverPrice;
import ru.sfedu.hibernate.lab4.model.collection.PriceParametersCollection;
import ru.sfedu.hibernate.lab4.model.list.PriceParametersList;
import ru.sfedu.hibernate.lab4.model.map.PriceParametersMap;
import ru.sfedu.hibernate.lab4.model.set.PriceParameters;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestBase5 {
    public PriceParameters createPriceParametersSet(double pagePrice, Set<Long> set, double workPrice, String validFromDate, String validToDate){
        PriceParameters priceParameters = new PriceParameters();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(set);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public PriceParametersList createPriceParametersList(double pagePrice, List<Long> list, double workPrice, String validFromDate, String validToDate){
        PriceParametersList priceParameters = new PriceParametersList();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(list);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public PriceParametersMap createPriceParametersMap(double pagePrice, Map<Long, CoverType> map, double workPrice, String validFromDate, String validToDate){
        PriceParametersMap priceParameters = new PriceParametersMap();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(map);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public PriceParametersCollection createPriceParametersCollection(double pagePrice, Set<CoverPrice> set, double workPrice, String validFromDate, String validToDate){
        PriceParametersCollection priceParameters = new PriceParametersCollection();
        priceParameters.setPagePrice(pagePrice);
        priceParameters.setCoverPrice(set);
        priceParameters.setWorkPrice(workPrice);
        priceParameters.setValidFromDate(validFromDate);
        priceParameters.setValidToDate(validToDate);
        return priceParameters;
    }

    public CoverPrice createCoverPrice(CoverType coverType, double price){
        CoverPrice coverPrice = new CoverPrice();
        coverPrice.setCoverType(coverType);
        coverPrice.setPrice(price);
        return coverPrice;
    }
}
