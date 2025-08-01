plugins {
    id("java-library")
    id("maven-publish")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public")
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
        languageVersion = JavaLanguageVersion.of(21)
    }
}

publishing {
    publications {
        register("maven", MavenPublication::class) {
            from(components["java"])
        }
    }

    repositories {
        maven("s3://repo") {
            name = "m56738"
            credentials(AwsCredentials::class)
        }
    }
}
