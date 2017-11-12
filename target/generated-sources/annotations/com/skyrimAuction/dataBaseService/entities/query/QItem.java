package com.skyrimAuction.dataBaseService.entities.query;

import com.skyrimAuction.dataBaseService.entities.Item;
import io.ebean.EbeanServer;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;

/**
 * Query bean for Item.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QItem extends TQRootBean<Item,QItem> {

  private static final QItem _alias = new QItem(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QItem alias() {
    return _alias;
  }

  public PLong<QItem> id;
  public PString<QItem> name;
  public PInteger<QItem> weight;
  public PInteger<QItem> defence;
  public PInteger<QItem> damage;


  /**
   * Construct with a given EbeanServer.
   */
  public QItem(EbeanServer server) {
    super(Item.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QItem() {
    super(Item.class);
  }

  /**
   * Construct for Alias.
   */
  private QItem(boolean dummy) {
    super(dummy);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  public static class Alias {
    public static PLong<QItem> id = _alias.id;
    public static PString<QItem> name = _alias.name;
    public static PInteger<QItem> weight = _alias.weight;
    public static PInteger<QItem> defence = _alias.defence;
    public static PInteger<QItem> damage = _alias.damage;
  }
}
