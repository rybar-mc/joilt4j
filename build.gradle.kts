plugins {
    id("java-library")
    id("maven-publish")
}

group = "org.rybar.joilt4j"
version = "0.0.3"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.0")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    testImplementation(platform("org.junit:junit-bom:5.12.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    withSourcesJar()
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

tasks.withType<Zip> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

publishing {
    repositories {
        maven {
            name = "JopgaRepo"
            url = uri("https://repo.jopga.me/releases")
            credentials {
                username = project.findProperty("jopga.user").toString()
                password = project.findProperty("jopga.key").toString()
            }
        }
    }

    publications.create<MavenPublication>("maven") {
        groupId = project.group as String
        artifactId = "core"
        version = project.version as String

        pom {
            name.set("Joilt4j")
            description.set("A lightweight, zero-dependency logging library")
        }

        artifact(tasks.named("jar")) {
            classifier = ""
        }

        artifact(tasks.named("sourcesJar"))
    }
}