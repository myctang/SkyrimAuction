package com.skyrimAuction.dataBaseService.entities.query.assoc;

import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.entities.query.QUser;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;

/**
 * Association query bean for AssocUser.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocUser<R> extends TQAssocBean<User,R> {

  public PLong<R> id;
  public PString<R> name;
  public PLong<R> money;
  public QAssocItem<R> inventory;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QUser>... properties) {
    return fetchProperties(properties);
  }

  /**
   * Eagerly fetch this association using a 'query join' loading the specified properties.
   */
  @SafeVarargs
  public final R fetchQuery(TQProperty<QUser>... properties) {
    return fetchQueryProperties(properties);
  }

  /**
   * Use lazy loading for this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetchLazy(TQProperty<QUser>... properties) {
    return fetchLazyProperties(properties);
  }

  public QAssocUser(String name, R root) {
    super(name, root);
  }
}
