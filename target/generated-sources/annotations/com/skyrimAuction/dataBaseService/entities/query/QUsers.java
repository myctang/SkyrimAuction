package com.skyrimAuction.dataBaseService.entities.query;

import com.skyrimAuction.dataBaseService.entities.Users;
import com.skyrimAuction.dataBaseService.entities.query.assoc.QAssocItem;
import io.ebean.EbeanServer;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;

/**
 * Query bean for Users.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QUsers extends TQRootBean<Users,QUsers> {

  private static final QUsers _alias = new QUsers(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QUsers alias() {
    return _alias;
  }

  public PLong<QUsers> id;
  public PString<QUsers> name;
  public PLong<QUsers> money;
  public QAssocItem<QUsers> inventory;


  /**
   * Construct with a given EbeanServer.
   */
  public QUsers(EbeanServer server) {
    super(Users.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QUsers() {
    super(Users.class);
  }

  /**
   * Construct for Alias.
   */
  private QUsers(boolean dummy) {
    super(dummy);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  public static class Alias {
    public static PLong<QUsers> id = _alias.id;
    public static PString<QUsers> name = _alias.name;
    public static PLong<QUsers> money = _alias.money;
    public static QAssocItem<QUsers> inventory = _alias.inventory;
  }
}
