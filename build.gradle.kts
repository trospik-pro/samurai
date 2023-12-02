import java.io.ByteArrayOutputStream
import java.time.LocalDate

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val majorVersion = 0
val minorVersion = 1
val patchVersion = determinePatchVersion()
val commitHash = determineCommitHash()

group = "dev.xhyrom.samurai"
version = "$majorVersion.$minorVersion.$patchVersion+$commitHash"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    // Server implementation
    implementation("dev.hollowcube:minestom-ce:a22d769740")
    implementation("dev.hollowcube:minestom-ce-extensions:1.2.0")

    // Config loader
    implementation("eu.okaeri:okaeri-configs-yaml-snakeyaml:5.0.0-beta.5")
    implementation(project(":okaeri-serdes-minestom"))

    // World loaders
    implementation("com.github.nindza-pro:polar:392f8852")

    // Messaging
    implementation("redis.clients:jedis:5.0.0")

    implementation("com.google.guava:guava:32.1.2-jre")
    implementation("net.kyori:adventure-text-minimessage:4.13.1")

    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.withType<Jar> {
    val date = LocalDate.now()

    manifest {
        attributes(
            "Main-Class" to "dev.xhyrom.samurai.SamuraiBootstrap",
            "Implementation-Title" to "Samurai",
            "Implementation-Version" to "git-Samurai-$version",
            "Implementation-Vendor" to date,
            "Specification-Title" to "Samurai",
            "Specification-Version" to "git-Samurai-$version",
            "Specification-Vendor" to date
        )
        attributes["Main-Class"] = "dev.xhyrom.samurai.SamuraiBootstrap"
    }
}

tasks.register("runServer") {
    group = "server"
    dependsOn("shadowJar")
    doLast {
        val runDir = File(project.projectDir.path, "run")
        runDir.mkdirs()

        runDir.listFiles()?.forEach {
            if (it.name.endsWith(".jar")) {
                it.delete()
            }
        }

        val builtJar = tasks.getByName("shadowJar").outputs.files.singleFile
        val targetJar = File(runDir, builtJar.name)

        builtJar.copyTo(targetJar, true)

        exec {
            commandLine("java", "-jar", targetJar.absolutePath)
        }
    }
}

fun determinePatchVersion(): Int {
    val tagInfo = ByteArrayOutputStream()

    return try {
        exec {
            commandLine("git", "describe", "--tags")
            standardOutput = tagInfo
        }

        val result = tagInfo.toString()

        if (result.contains("-")) result.split("-")[1].toInt() else 0
    } catch (e: Exception) {
        0
    }
}

fun determineCommitHash(): String {
    val commitHashInfo = ByteArrayOutputStream()

    exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = commitHashInfo
    }

    return commitHashInfo.toString().trim()
}