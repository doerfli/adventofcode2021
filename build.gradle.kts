plugins {
    kotlin("jvm") version "1.6.10"
}

group = "li.doerf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:5.0.3")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.3")
}

tasks.test {
    useJUnitPlatform()
}
