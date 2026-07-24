plugins {
    id("java-library")
    id("maven-publish")
}

repositories {
    maven("https://repo.56738.me")
}

tasks {
    javadoc {
        (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:all,-missing", "-quiet")
    }
}

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

publishing {
    publications {
        register("maven", MavenPublication::class) {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "m56738"
            credentials(PasswordCredentials::class)
            if (project.version.toString().endsWith("-SNAPSHOT")) {
                setUrl("https://repo.56738.me/repository/maven-snapshots/")
            } else {
                setUrl("https://repo.56738.me/repository/maven-releases/")
            }
        }
    }
}
