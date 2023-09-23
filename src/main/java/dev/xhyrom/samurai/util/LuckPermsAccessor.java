package dev.xhyrom.samurai.util;

import lombok.experimental.UtilityClass;
import net.hollowcube.minestom.extensions.ExtensionBootstrap;
import net.minestom.server.extensions.ExtensionClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * We can't use the LuckPerms API directly because it's not in the classpath.
 */
@UtilityClass
public class LuckPermsAccessor {
    private static Object LUCKPERMS_PROVIDER = null;
    private static Object LUCKPERMS_USERMANAGER = null;

    private static Method LUCKPERMS_USERMANAGER_GET_USER = null;
    private static Method LUCKPERMS_USER_GETPRIMARYGROUP = null;

    public static void init() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ExtensionClassLoader loader = ExtensionBootstrap.getExtensionManager().getExtension("LuckPerms").getOrigin().getClassLoader();

        Class<?> luckPermsProvider = Class.forName("net.luckperms.api.LuckPermsProvider", true, loader);
        Class<?> luckPermsUser = Class.forName("net.luckperms.api.model.user.User", true, loader);

        LUCKPERMS_PROVIDER = luckPermsProvider.getDeclaredMethod("get").invoke(null);
        LUCKPERMS_USERMANAGER = LUCKPERMS_PROVIDER.getClass().getDeclaredMethod("getUserManager").invoke(LUCKPERMS_PROVIDER);

        LUCKPERMS_USERMANAGER_GET_USER = LUCKPERMS_USERMANAGER.getClass().getDeclaredMethod("getUser", java.util.UUID.class);
        LUCKPERMS_USER_GETPRIMARYGROUP = luckPermsUser.getDeclaredMethod("getPrimaryGroup");
    }

    public static String getPrimaryGroup(UUID uniqueId) {
        try {
            Object user = LUCKPERMS_USERMANAGER_GET_USER.invoke(LUCKPERMS_USERMANAGER, uniqueId);
            return (String) LUCKPERMS_USER_GETPRIMARYGROUP.invoke(user);
        } catch (Exception e) {
            return "none";
        }
    }
}
