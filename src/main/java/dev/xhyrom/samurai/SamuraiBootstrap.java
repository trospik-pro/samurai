package dev.xhyrom.samurai;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.logging.Logger;

public class SamuraiBootstrap {
    public static final Package PACKAGE = SamuraiBootstrap.class.getPackage();

    public static void main(String[] args) {
        Logger.getLogger("Samurai").info("Starting Samurai");

        Samurai.init();
        Samurai.run();
    }

    public static Path getPath(String path) {
        try {
            return Path.of(
                            new File(
                                    SamuraiBootstrap.class
                                            .getProtectionDomain()
                                            .getCodeSource()
                                            .getLocation()
                                            .toURI()
                            ).getPath()
                    ).getParent()
                    .resolve(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
