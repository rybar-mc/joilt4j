plugins {
    id("java-library")
    id("maven-publish")
}

version = "0.0.1"

dependencies {
    implementation(project(":"))

    // SLF4J API dependency
    api("org.slf4j:slf4j-api:2.0.12")

    // Test dependencies
    testImplementation("org.slf4j:slf4j-api:2.0.12")
}

java {
    withSourcesJar()
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.withType<Zip> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            artifactId = "slf4j"
            version = project.version as String

            pom {
                name.set("Joilt4j-SLF4J")
                description.set("SLF4J integration for joilt4j logging library")
            }

            artifact(tasks.named("jar")) {
                classifier = ""
            }

            artifact(tasks.named("sourcesJar"))
        }
    }
}