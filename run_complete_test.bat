@echo off
REM Kompa Music Complete Test Runner
REM This script compiles and runs the complete test program

echo.
echo =========================================================
echo  KOMPA MUSIC - COMPLETE A TO Z TEST RUNNER
echo =========================================================
echo.

REM Change to project directory
cd /d "C:\Users\waqas\IdeaProjects\QA_Automation_Practice"

REM Check if Maven is available
echo [1/3] Checking dependencies...
if exist "pom.xml" (
    echo  ✓ Maven project detected
    echo.

    REM Clean and compile with Maven
    echo [2/3] Compiling with Maven...
    call mvn clean compile

    if %errorlevel% neq 0 (
        echo.
        echo ❌ Compilation failed!
        pause
        exit /b 1
    )

    echo.
    echo [3/3] Running Complete Test Suite...
    echo.
    call mvn exec:java -Dexec.mainClass="org.example.KompaMusicTest_Complete"

) else (
    echo  ✗ pom.xml not found!
    echo.
    echo Manual compilation required:
    echo.
    echo javac -cp "lib/*" src/main/java/org/example/KompaMusicTest_Complete.java
    echo java -cp "lib/*:src/main/java" org.example.KompaMusicTest_Complete
    echo.
    pause
    exit /b 1
)

echo.
echo =========================================================
echo  TEST COMPLETED!
echo  Check results in:
echo  - KompaMusic_Complete_Screenshots/ (folder)
echo  - KompaMusic_Complete_Report.html (file)
echo =========================================================
echo.
pause
