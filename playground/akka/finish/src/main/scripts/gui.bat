@echo off
cd ..\..\..\target\scala_2.9.0\dist
set AKKA_HOME=.
echo %AKKA_HOME%
set AKKA_CLASSPATH=%AKKA_HOME%\lib\*;%AKKA_HOME%\deploy\*
set JAVA_OPTS=-Xms206M -Xmx1536M -Xss1M -XX:MaxPermSize=256M -XX:+UseParallelGC

java -cp "%AKKA_CLASSPATH%" -Dakka.home="%AKKA_HOME%" duse12.gui.JunctionGUIMain