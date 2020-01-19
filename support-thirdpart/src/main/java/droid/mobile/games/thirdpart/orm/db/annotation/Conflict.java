package droid.mobile.games.thirdpart.orm.db.annotation;

import droid.mobile.games.thirdpart.orm.db.enums.Strategy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 冲突策略
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Conflict {
    public Strategy value();
}
