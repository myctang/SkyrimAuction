package com.skyrimAuction.dataBaseService.entities.query;

import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.entities.query.assoc.QAssocItem;
import io.ebean.EbeanServer;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;

/**
 * Query bean for User.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QUser extends TQRootBean<User,QUser> {

  private static final QUser _alias = new QUser(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QUser alias() {
    return _alias;
  }

  public PLong<QUser> id;
  public PString<QUser> name;
  public PLong<QUser> money;
  public QAssocItem<QUser> inventory;


  /**
   * Construct with a given EbeanServer.
   */
  public QUser(EbeanServer server) {
    super(User.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QUser() {
    super(User.class);
  }

  /**
   * Construct for Alias.
   */
  private QUser(boolean dummy) {
    super(dummy);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  public static class Alias {
    public static PLong<QUser> id = _alias.id;
    public static PString<QUser> name = _alias.name;
    public static PLong<QUser> money = _alias.money;
    public static QAssocItem<QUser> inventory = _alias.inventory;
  }
}
