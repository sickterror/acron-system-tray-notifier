plugins {
    kotlin("jvm") version "1.4.30"
}

group = "it.acron.si"
version = ""

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.ajalt.clikt:clikt:3.1.0")
    implementation("fr.jcgay.send-notification:send-notification:0.16.0")
    implementation("org.slf4j:slf4j-log4j12:1.7.29")
    implementation("com.andreapivetta.kolor:kolor:1.0.0")
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "it.acron.si.acornsysnotifer.MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}