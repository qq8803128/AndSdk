package droid.mobile.games.common.log.parser;

import android.util.Log;

import droid.mobile.games.common.log.Parser;

/**
 * Created by pengwei on 16/3/8.
 */
class ThrowableParse implements Parser<Throwable> {
    @Override
    public Class<Throwable> parseClassType() {
        return Throwable.class;
    }

    @Override
    public String parseString( Throwable throwable) {
        return Log.getStackTraceString(throwable);
    }
}
