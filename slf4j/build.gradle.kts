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