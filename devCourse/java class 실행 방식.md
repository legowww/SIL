#### [CMD]
1. `java -c HelloWorld.java` 명령어를 통해 바이트코드 형식의 class 파일을 생성한다.
2. `java HelloWorld` 명령어를 통해 실행한다.

#### [Build Tool] gradle
1. `gradle init` 명령어를 통해 빌드를 실행한다. 
```
.
├── app
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       ├── main
│   │   │       │   └── com
│   │   │       │       └── dev
│   │   │       │           └── java
│   │   │       │               ├── App.class
│   │   │       │               └── ByeWorld.class
│   │   │       └── test
│   │   │           └── com
│   │   │               └── dev
│   │   │                   └── java
│   │   │                       └── AppTest.class
│   │   ├── distributions
│   │   │   ├── app.tar
│   │   │   └── app.zip
│   │   ├── generated
│   │   │   └── sources
│   │   │       ├── annotationProcessor
│   │   │       │   └── java
│   │   │       │       ├── main
│   │   │       │       └── test
│   │   │       └── headers
│   │   │           └── java
│   │   │               ├── main
│   │   │               └── test
│   │   ├── libs
│   │   │   └── app.jar
│   │   ├── reports
│   │   │   └── tests
│   │   │       └── test
│   │   │           ├── classes
│   │   │           │   └── com.dev.java.AppTest.html
│   │   │           ├── css
│   │   │           │   ├── base-style.css
│   │   │           │   └── style.css
│   │   │           ├── index.html
│   │   │           ├── js
│   │   │           │   └── report.js
│   │   │           └── packages
│   │   │               └── com.dev.java.html
│   │   ├── scripts
│   │   │   ├── app
│   │   │   └── app.bat
│   │   ├── test-results
│   │   │   └── test
│   │   │       ├── TEST-com.dev.java.AppTest.xml
│   │   │       └── binary
│   │   │           ├── output.bin
│   │   │           ├── output.bin.idx
│   │   │           └── results.bin
│   │   └── tmp
│   │       ├── compileJava
│   │       │   ├── compileTransaction
│   │       │   │   ├── backup-dir
│   │       │   │   └── stash-dir
│   │       │   │       └── ByeWorld.class.uniqueId0
│   │       │   └── previous-compilation-data.bin
│   │       ├── compileTestJava
│   │       │   └── previous-compilation-data.bin
│   │       ├── jar
│   │       │   └── MANIFEST.MF
│   │       └── test
│   ├── build.gradle
│   ├── gradle
│   │   └── wrapper
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   ├── gradlew
│   ├── gradlew.bat
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── dev
│       │   │           └── java
│       │   │               ├── App.java
│       │   │               └── ByeWorld.java
│       │   └── resources
│       └── test
│           ├── java
│           │   └── com
│           │       └── dev
│           │           └── java
│           │               └── AppTest.java
│           └── resources
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```
- `gradle/..` 런타임 시 필요한 파일들
- `settings.gralde` 환경설정

#### [ide] Intellj
1. 시작 시 설정을 통해 편리하게 사용
