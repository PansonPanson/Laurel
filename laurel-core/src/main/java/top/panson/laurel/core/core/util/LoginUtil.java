package top.panson.laurel.core.core.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import top.panson.laurel.core.core.attribute.Attributes;

public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
