plugins {
    `java-library`
    `maven-publish`
    signing
    alias(libs.plugins.spotless)
}

spotless {
    isEnforceCheck = false
    java {
        palantirJavaFormat()
    }
}

dependencies {
    api(libs.gdx.core)
}

java {
    withJavadocJar()
    withSourcesJar()
}

val libraryArtifact = "library"
val libraryName = "Library"
val libraryDescription = "Library description"
val libraryUrl = "https://www.github.com/fourlastor-alexandria/library"
val licenseUrl = "$libraryUrl/blob/main/LICENSE"
val licenseName = "MIT License"
val developerId = "fourlastor"
val developerName = "Daniele Conti"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = libraryArtifact
            from(components["java"])
            pom {
                name.set(libraryName)
                description.set(libraryDescription)
                url.set(libraryUrl)
                licenses {
                    license {
                        name.set(licenseName)
                        url.set(licenseUrl)
                    }
                }
                developers {
                    developer {
                        id.set(developerId)
                        name.set(developerName)
                    }
                }
                scm {
                    connection.set("scm:git:$libraryUrl.git")
                    developerConnection.set("scm:git:$libraryUrl.git")
                    url.set(libraryUrl)
                }
            }
        }
    }
}

signing {
    setRequired({ project.hasProperty("RELEASE") })
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}
