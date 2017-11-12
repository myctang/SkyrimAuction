package com.skyrimAuction.dataBaseService.entities.query.assoc;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.query.QItem;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;

/**
 * Association query bean for AssocItem.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocItem<R> extends TQAssocBean<Item,R> {

  public PLong<R> id;
  public PString<R> name;
  public PInteger<R> weight;
  public PInteger<R> defence;
  public PInteger<R> damage;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QItem>... properties) {
    return fetchProperties(properties);
  }

  /**
   * Eagerly fetch this association using a 'query join' loading the specified properties.
   */
  @SafeVarargs
  public final R fetchQuery(TQProperty<QItem>... properties) {
    return fetchQueryProperties(properties);
  }

  /**
   * Use lazy loading for this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetchLazy(TQProperty<QItem>... properties) {
    return fetchLazyProperties(properties);
  }

  public QAssocItem(String name, R root) {
    super(name, root);
  }
}
