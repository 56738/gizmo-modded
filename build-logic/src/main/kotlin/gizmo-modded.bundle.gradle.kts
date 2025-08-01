plugins {
    id("java")
}

val bundle by configurations.registering {
    isTransitive = false
}

tasks {
    jar {
        dependsOn(bundle)
        from(bundle.map { c -> c.map { zipTree(it) } }) {
            exclude("META-INF/MANIFEST.MF")
        }
    }
}
