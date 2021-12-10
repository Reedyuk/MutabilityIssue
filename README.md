# MutabilityIssue

To recreate the issue, run 
```
./gradlew iosSimulatorArm64Test
```
this will cause a
```
kotlin.native.concurrent.InvalidMutabilityException at MutabilityIssue/src/commonTest/kotlin/me.andrewreed/ViewModelTests.kt:32
```

This is down to the view model using the new memory model for kotlin multiplatform and also using **KOIN** to inject the dependancy.