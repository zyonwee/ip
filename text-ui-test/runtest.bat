@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM Initialize success counter
setlocal EnableDelayedExpansion
set /a successCount=0

:loop
    REM Read input file line by line
    for /f "tokens=*" %%a in (input.txt) do (

        REM Create temporary input file
        echo %%a>temp_input.txt

        REM Run the program with temporary input
        java -classpath ..\bin Bob < input.txt > ACTUAL.TXT

        REM Compare the output to the expected output
        FC ACTUAL.TXT EXPECTED.TXT
        IF ERRORLEVEL 1 (
            echo ********** TEST FAILED for input: %%a **********
        ) else (
            echo ********** TEST SUCCESSFUL for input: %%a **********
            set /a successCount=!successCount!+1
        )
    )

echo.
echo Total Successful Tests: !successCount!

endlocal