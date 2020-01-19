package droid.mobile.games.common.log.parser;

import android.os.Message;

import droid.mobile.games.common.log.Parser;
import droid.mobile.games.common.log.utils.ObjectUtil;

/**
 * Created by pengwei on 2017/3/29.
 */

class MessageParse implements Parser<Message> {
    @Override
    public Class<Message> parseClassType() {
        return Message.class;
    }

    @Override
    public String parseString( Message message) {
        return message.getClass().getName() + " [" + LINE_SEPARATOR +
                String.format("%s = %s", "what", message.what) + LINE_SEPARATOR +
                String.format("%s = %s", "when", message.getWhen()) + LINE_SEPARATOR +
                String.format("%s = %s", "arg1", message.arg1) + LINE_SEPARATOR +
                String.format("%s = %s", "arg2", message.arg2) + LINE_SEPARATOR +
                String.format("%s = %s", "data", new BundleParse().parseString(message.getData())) + LINE_SEPARATOR +
                String.format("%s = %s", "obj", ObjectUtil.objectToString(message.obj)) + LINE_SEPARATOR +
                "]";
    }
}
