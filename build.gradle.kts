plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("com.diffplug.spotless") version "7.2.1"
}

version = "0.0.3"

allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")

    group = "org.rybar.joilt4j"

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:26.0.2")

        compileOnly("org.projectlombok:lombok:1.18.42")
        annotationProcessor("org.projectlombok:lombok:1.18.42")

        testImplementation(platform("org.junit:junit-bom:5.13.4"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.assertj:assertj-core:3.27.5")
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
}

spotless {
    java {
        importOrder()
        removeUnusedImports()

        palantirJavaFormat("2.73.0").formatJavadoc(true)
        formatAnnotations()

        target("src/main/java/**", "slf4j/src/main/java/**")
    }
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