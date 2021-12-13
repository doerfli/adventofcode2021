plugins {
    kotlin("jvm") version "1.6.0"
}

group = "li.doerf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:5.0.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.2")
}

tasks.test {
    useJUnitPlatform()
}
