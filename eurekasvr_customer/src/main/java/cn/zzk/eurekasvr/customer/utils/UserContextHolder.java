package cn.zzk.eurekasvr.customer.utils;

import org.springframework.util.Assert;

/**
 * Create Time : 2019/09/12
 *
 * @author zzk
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

    public static UserContext getContext() {
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);

        }
        return userContext.get();
    }

    public static void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    public static UserContext createEmptyContext() {
        return new UserContext();
    }
}
