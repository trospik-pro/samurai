plugins {
    id("java")
}

group = "dev.xhyrom.okaeri.serdes.minestom"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    implementation("dev.hollowcube:minestom-ce:e9d0098418")

    implementation("eu.okaeri:okaeri-configs-core:5.0.0-beta.5")

    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}