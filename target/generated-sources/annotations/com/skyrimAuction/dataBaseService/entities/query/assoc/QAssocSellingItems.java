package com.skyrimAuction.dataBaseService.entities.query.assoc;

import com.skyrimAuction.dataBaseService.entities.SellingItems;
import com.skyrimAuction.dataBaseService.entities.query.QSellingItems;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PSqlDate;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;

/**
 * Association query bean for AssocSellingItems.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocSellingItems<R> extends TQAssocBean<SellingItems,R> {

  public PLong<R> id;
  public QAssocItem<R> item;
  public PInteger<R> price;
  public PSqlDate<R> sellingStart;
  public PLong<R> duration;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QSellingItems>... properties) {
    return fetchProperties(properties);
  }

  /**
   * Eagerly fetch this association using a 'query join' loading the specified properties.
   */
  @SafeVarargs
  public final R fetchQuery(TQProperty<QSellingItems>... properties) {
    return fetchQueryProperties(properties);
  }

  /**
   * Use lazy loading for this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetchLazy(TQProperty<QSellingItems>... properties) {
    return fetchLazyProperties(properties);
  }

  public QAssocSellingItems(String name, R root) {
    super(name, root);
  }
}
