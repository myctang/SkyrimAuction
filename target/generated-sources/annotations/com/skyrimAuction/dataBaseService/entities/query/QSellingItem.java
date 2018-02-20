package com.skyrimAuction.dataBaseService.entities.query;

import com.skyrimAuction.dataBaseService.entities.SellingItem;
import com.skyrimAuction.dataBaseService.entities.query.assoc.QAssocItem;
import com.skyrimAuction.dataBaseService.entities.query.assoc.QAssocUser;
import io.ebean.EbeanServer;
import io.ebean.typequery.PBoolean;
import io.ebean.typequery.PInteger;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PTimestamp;
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
  public PInteger<QSellingItem> buyNowPrice;
  public PBoolean<QSellingItem> finished;
  public PTimestamp<QSellingItem> sellingEnd;
  public PLong<QSellingItem> duration;
  public QAssocUser<QSellingItem> holder;
  public QAssocUser<QSellingItem> lastBidder;


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
    public static PInteger<QSellingItem> buyNowPrice = _alias.buyNowPrice;
    public static PBoolean<QSellingItem> finished = _alias.finished;
    public static PTimestamp<QSellingItem> sellingEnd = _alias.sellingEnd;
    public static PLong<QSellingItem> duration = _alias.duration;
    public static QAssocUser<QSellingItem> holder = _alias.holder;
    public static QAssocUser<QSellingItem> lastBidder = _alias.lastBidder;
  }
}
