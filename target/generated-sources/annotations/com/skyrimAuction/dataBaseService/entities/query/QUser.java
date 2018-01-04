package com.skyrimAuction.dataBaseService.entities.query;

import com.skyrimAuction.dataBaseService.entities.Role;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.entities.query.assoc.QAssocItem;
import com.skyrimAuction.dataBaseService.entities.query.assoc.QAssocQuest;
import io.ebean.EbeanServer;
import io.ebean.typequery.PArray;
import io.ebean.typequery.PBoolean;
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
  public PString<QUser> password;
  public PArray<QUser,Role> roles;
  public QAssocItem<QUser> inventory;
  public QAssocQuest<QUser> quest;
  public PBoolean<QUser> accountNonExpired;
  public PBoolean<QUser> accountNonLocked;
  public PBoolean<QUser> credentialsNonExpired;
  public PBoolean<QUser> enabled;


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
    public static PString<QUser> password = _alias.password;
    public static PArray<QUser,Role> roles = _alias.roles;
    public static QAssocItem<QUser> inventory = _alias.inventory;
    public static QAssocQuest<QUser> quest = _alias.quest;
    public static PBoolean<QUser> accountNonExpired = _alias.accountNonExpired;
    public static PBoolean<QUser> accountNonLocked = _alias.accountNonLocked;
    public static PBoolean<QUser> credentialsNonExpired = _alias.credentialsNonExpired;
    public static PBoolean<QUser> enabled = _alias.enabled;
  }
}
