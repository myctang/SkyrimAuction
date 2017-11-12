package com.skyrimAuction.dataBaseService.entities.query.assoc;

import com.skyrimAuction.dataBaseService.entities.SellingItem;
import com.skyrimAuction.dataBaseService.entities.query.QSellingItem;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PSqlDate;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;

/**
 * Association query bean for AssocSellingItem.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocSellingItem<R> extends TQAssocBean<SellingItem,R> {

  public PLong<R> id;
  public QAssocItem<R> item;
  public PInteger<R> price;
  public PSqlDate<R> sellingStart;
  public PLong<R> duration;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QSellingItem>... properties) {
    return fetchProperties(properties);
  }

  /**
   * Eagerly fetch this association using a 'query join' loading the specified properties.
   */
  @SafeVarargs
  public final R fetchQuery(TQProperty<QSellingItem>... properties) {
    return fetchQueryProperties(properties);
  }

  /**
   * Use lazy loading for this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetchLazy(TQProperty<QSellingItem>... properties) {
    return fetchLazyProperties(properties);
  }

  public QAssocSellingItem(String name, R root) {
    super(name, root);
  }
}
