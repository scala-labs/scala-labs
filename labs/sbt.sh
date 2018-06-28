#!/usr/bin/env bash
export SBT_OPTS=-Dfile.encoding=UTF8
java $SBT_OPTS -Xmx512M -jar `dirname $0`/sbt-launch-0.13.15.jar "$@"
