plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.xhyrom.samurai"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("dev.hollowcube:minestom-ce:e9d0098418")

    // World loaders
    implementation("dev.hollowcube:polar:1.3.1")
    implementation("com.github.CatDevz:SlimeLoader:master-SNAPSHOT")

    implementation("net.kyori:adventure-text-minimessage:4.13.1")

    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "dev.xhyrom.samurai.Samurai"
    }
}

tasks.register("runServer") {
    group = "server"
    dependsOn("shadowJar")
    doLast {
        val runDir = File(project.projectDir.path, "run")
        runDir.mkdirs()

        val builtJar = tasks.getByName("shadowJar").outputs.files.singleFile
        val targetJar = File(runDir, builtJar.name)

        builtJar.copyTo(targetJar, true)

        exec {
            commandLine("java", "-jar", targetJar.absolutePath)
        }
    }
}