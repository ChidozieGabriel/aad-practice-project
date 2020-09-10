# Associate Android Developer Practice Project

This project implements a simple app that displays the Google Africa Developer Scholarship 2020 learderboard.

For project details, click [here](https://docs.google.com/document/d/1KS003bk1a-2bOfUnhXvnOPoHfPuKtdkTAQ_APljjgWc/preview?pru=AAABdGfwdCU*Rv2y_0eVQro6UmEZv79W3Q)

## Installation
```
./gradlew installDebug
```

## Tests
#### Unit Tests
```
./gradlew clean
./gradlew test
```

#### Instrumentation Tests
(You must have a connected emulator or device)
```
./gradlew installDebug
./gradlew installDebugAndroidTest
adb shell am instrument -w -e package com.chidozie.n.aadpracticeproject -e debug false com.chidozie.n.aadpracticeproject.test/com.chidozie.n.aadpracticeproject.ui.util.MyTestRunner
```

## License
[MIT](/LICENSE)
