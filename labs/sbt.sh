export SBT_OPTS=-Dfile.encoding=UTF8
java $SBT_OPTS -Xmx512M -jar -XX:MaxPermSize=384m `dirname $0`/sbt-launch-0.13.0.jar "$@" 
