plugins {
    id("java")
}

group = "com.Grupp5"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:42.7.4")
}

tasks.test {
    useJUnitPlatform()
}