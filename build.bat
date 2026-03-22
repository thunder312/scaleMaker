@echo off
echo === ScaleMaker Plugin Build ===

echo.
echo [1/4] Kompilieren...
javac -encoding UTF-8 -d bin *.java
if errorlevel 1 (
    echo FEHLER: Kompilierung fehlgeschlagen!
    exit /b 1
)

echo [2/4] META-INF kopieren (ServiceLoader-Registrierung)...
if not exist bin\META-INF\services mkdir bin\META-INF\services
copy /Y META-INF\services\ScaleMakerPlugin bin\META-INF\services\ScaleMakerPlugin > nul

echo [3/4] JAR erstellen...
jar cvf bin/scalemaker.jar -C bin .
if errorlevel 1 (
    echo FEHLER: JAR-Erstellung fehlgeschlagen!
    exit /b 1
)

echo [4/4] JavaDoc generieren...
javadoc -encoding UTF-8 -charset UTF-8 -quiet -d doc -author -version *.java

echo.
echo === Build erfolgreich! ===
echo Plugin-JAR: bin\scalemaker.jar
echo.
echo Einbinden im Hauptprogramm (Classpath):
echo   javac -cp bin\scalemaker.jar MeinHauptprogramm.java
echo   java  -cp bin\scalemaker.jar;. MeinHauptprogramm
