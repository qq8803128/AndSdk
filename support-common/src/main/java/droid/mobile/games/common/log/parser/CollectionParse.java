package droid.mobile.games.common.log.parser;

import android.annotation.SuppressLint;

import java.util.Collection;
import java.util.Iterator;

import droid.mobile.games.common.log.Parser;
import droid.mobile.games.common.log.utils.ObjectUtil;

/**
 * Created by pengwei on 16/3/8.
 */
class CollectionParse implements Parser<Collection> {
    @Override
    public Class<Collection> parseClassType() {
        return Collection.class;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String parseString( Collection collection) {
        String simpleName = collection.getClass().getName();
        StringBuilder msg = new StringBuilder(String.format("%s size = %d [" + LINE_SEPARATOR, simpleName,
                collection.size()));
        if (!collection.isEmpty()) {
            Iterator iterator = collection.iterator();
            int flag = 0;
            while (iterator.hasNext()) {
                String itemString = "[%d]:%s%s";
                Object item = iterator.next();
                msg.append(String.format(itemString, flag, ObjectUtil.objectToString(item),
                        flag++ < collection.size() - 1 ? "," + LINE_SEPARATOR : LINE_SEPARATOR));
            }
        }
        return msg + "]";
    }
}
