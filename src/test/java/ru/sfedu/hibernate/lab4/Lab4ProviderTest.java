package ru.sfedu.hibernate.lab4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.hibernate.enums.CoverType;
import ru.sfedu.hibernate.lab4.model.collection.CoverPrice;
import ru.sfedu.hibernate.lab4.model.collection.PriceParametersCollection;
import ru.sfedu.hibernate.lab4.model.list.PriceParametersList;
import ru.sfedu.hibernate.lab4.model.map.PriceParametersMap;
import ru.sfedu.hibernate.lab4.model.set.PriceParameters;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Lab4ProviderTest extends TestBase5 {

    private static Logger log = LogManager.getLogger(Lab4ProviderTest.class);
    Lab4Provider instance = new Lab4Provider();

    @Test
    public void TestSaveSuccess() {
        log.info("saveSuccess");
        Set<Long> map = new HashSet<>();
        map.add(2L);
        map.add(2L);
        map.add(4L);
        PriceParameters priceParameters = createPriceParametersSet( 13.4, map, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters priceParameters1 = instance.getById(PriceParameters.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
    }

    @Test
    public void TestSaveSuccessList() {
        log.info("saveSuccessList");
        List<Long> list = new ArrayList<>();
        list.add(2L);
        list.add(2L);
        list.add(4L);
        PriceParametersList priceParameters = createPriceParametersList( 13.4, list, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParametersList priceParameters1 = instance.getById(PriceParametersList.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
    }

    @Test
    public void TestSaveSuccessMap() {
        log.info("saveSuccessMap");
        Map<Long, CoverType> map = new HashMap();
        map.put(2L, CoverType.RIGID_COVER);
        map.put(2L, CoverType.RIGID_COVER);
        map.put(4L, CoverType.PAPERBACK);
        PriceParametersMap priceParameters = createPriceParametersMap( 13.4, map, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParametersMap priceParameters1 = instance.getById(PriceParametersMap.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
    }

    @Test
    public void TestSaveSuccessCollection() {
        log.info("saveSuccessCollection");
        Set<CoverPrice> map = new HashSet<>();
        CoverPrice coverPrice = createCoverPrice(CoverType.RIGID_COVER, 123.5);
        CoverPrice coverPrice2 = createCoverPrice(CoverType.PAPERBACK, 143.8);
        map.add(coverPrice);
        map.add(coverPrice);
        map.add(coverPrice2);
        PriceParametersCollection priceParameters = createPriceParametersCollection( 13.4, map, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParametersCollection priceParameters1 = instance.getById(PriceParametersCollection.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
    }

    @Test
    public void TestGetByIdFail() {
        log.info("GetByIdFail");
        Optional<PriceParameters> entity1 = instance.getById(PriceParameters.class, 200L);
        assertNull(entity1.orElse(null));
    }

    @Test
    public void TestDeleteSuccess() {
        log.info("DeleteSuccess");
        Set<Long> map = new HashSet<>();
        map.add(2L);
        map.add(4L);
        PriceParameters priceParameters = createPriceParametersSet( 13.4, map, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters priceParameters1 = instance.getById(PriceParameters.class, resultA).get();
        log.debug(priceParameters1);
        assertEquals(priceParameters, priceParameters1);
        log.debug(instance.delete(PriceParameters.class,resultA));
        PriceParameters priceParameters2 = instance.getById(PriceParameters.class, resultA).orElse(null);
        assertNull(priceParameters2);
    }

    @Test
    public void TestDeleteFail() {
        log.info("DeleteFail");
        PriceParameters priceParameters1 = instance.getById(PriceParameters.class, 301L).orElse(null);
        assertNull(priceParameters1);
        log.debug(instance.delete(PriceParameters.class,301L));
    }

    @Test
    public void TestUpdateSuccess(){
        log.info("UpdateSuccess");
        Set<Long> map = new HashSet<>();
        map.add(2L);
        map.add(4L);
        PriceParameters priceParameters = createPriceParametersSet( 13.4, map, 16.3, "2019-01-01", "2021-01-01");
        Long resultA = instance.save(priceParameters);
        PriceParameters priceParameters1 = instance.getById(PriceParameters.class, resultA).get();
        priceParameters1.setPagePrice(16);
        instance.update(priceParameters1);
        PriceParameters priceParameters2 = instance.getById(PriceParameters.class, resultA).orElse(null);
        assertEquals(priceParameters1, priceParameters2);
    }

    @Test
    public void TestUpdateFail(){
        log.info("UpdateFail");
        Long id1 = 203L;
        Set<Long> map = new HashSet<>();
        map.add(2L);
        map.add(4L);
        PriceParameters priceParameters = createPriceParametersSet( 13.4, map, 16.3, "2019-01-01", "2021-01-01");
        priceParameters.setId(id1);
        instance.update(priceParameters);
        PriceParameters priceParameters1 = instance.getById(PriceParameters.class, id1).orElse(null);
        assertNull(priceParameters1);
    }

}