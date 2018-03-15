plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    compile("org.slf4j:slf4j-api:1.7.25")
    runtime("ch.qos.logback:logback-classic:1.2.3")
}

application {
    mainClassName = "com.example.Main"
}

