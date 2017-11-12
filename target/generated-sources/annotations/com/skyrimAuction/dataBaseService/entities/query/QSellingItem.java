package com.skyrimAuction.dataBaseService.entities.query;

import com.skyrimAuction.dataBaseService.entities.SellingItem;
import com.skyrimAuction.dataBaseService.entities.query.assoc.QAssocItem;
import io.ebean.EbeanServer;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PSqlDate;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;

/**
 * Query bean for SellingItem.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QSellingItem extends TQRootBean<SellingItem,QSellingItem> {

  private static final QSellingItem _alias = new QSellingItem(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QSellingItem alias() {
    return _alias;
  }

  public PLong<QSellingItem> id;
  public QAssocItem<QSellingItem> item;
  public PInteger<QSellingItem> price;
  public PSqlDate<QSellingItem> sellingStart;
  public PLong<QSellingItem> duration;


  /**
   * Construct with a given EbeanServer.
   */
  public QSellingItem(EbeanServer server) {
    super(SellingItem.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QSellingItem() {
    super(SellingItem.class);
  }

  /**
   * Construct for Alias.
   */
  private QSellingItem(boolean dummy) {
    super(dummy);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  public static class Alias {
    public static PLong<QSellingItem> id = _alias.id;
    public static QAssocItem<QSellingItem> item = _alias.item;
    public static PInteger<QSellingItem> price = _alias.price;
    public static PSqlDate<QSellingItem> sellingStart = _alias.sellingStart;
    public static PLong<QSellingItem> duration = _alias.duration;
  }
}
