package dev.fluxi.futils.utils;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Base64 {
    public static String serializeAndEncode(Object object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeObject(object);
            bukkitObjectOutputStream.flush();
            return new String(java.util.Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()));
        } catch (IOException ignored) {}
        return null;
    }

    public static Object deserializeAndDecode(String encoded) {
        try {
            byte[] serializedObject = java.util.Base64.getDecoder().decode(encoded);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedObject);
            return new BukkitObjectInputStream(byteArrayInputStream).readObject();
        } catch (IOException | ClassNotFoundException ignored) {}
        return null;
    }
}
