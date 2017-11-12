package com.skyrimAuction.dataBaseService.entities.query;

import com.skyrimAuction.dataBaseService.entities.SellingItems;
import com.skyrimAuction.dataBaseService.entities.query.assoc.QAssocItem;
import io.ebean.EbeanServer;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PSqlDate;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;

/**
 * Query bean for SellingItems.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QSellingItems extends TQRootBean<SellingItems,QSellingItems> {

  private static final QSellingItems _alias = new QSellingItems(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QSellingItems alias() {
    return _alias;
  }

  public PLong<QSellingItems> id;
  public QAssocItem<QSellingItems> item;
  public PInteger<QSellingItems> price;
  public PSqlDate<QSellingItems> sellingStart;
  public PLong<QSellingItems> duration;


  /**
   * Construct with a given EbeanServer.
   */
  public QSellingItems(EbeanServer server) {
    super(SellingItems.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QSellingItems() {
    super(SellingItems.class);
  }

  /**
   * Construct for Alias.
   */
  private QSellingItems(boolean dummy) {
    super(dummy);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  public static class Alias {
    public static PLong<QSellingItems> id = _alias.id;
    public static QAssocItem<QSellingItems> item = _alias.item;
    public static PInteger<QSellingItems> price = _alias.price;
    public static PSqlDate<QSellingItems> sellingStart = _alias.sellingStart;
    public static PLong<QSellingItems> duration = _alias.duration;
  }
}
