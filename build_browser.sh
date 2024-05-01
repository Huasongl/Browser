#!/bin/bash

#set -e
if [ "$1" ] ; then
  ./gbc_aw_aar.sh "$1" || exit
fi;

echo "编译""$1""版本apk" &&
if [ "$1" = "release" ] || [ "$1" = "release64" ] ; then
    ./gradlew installRelease &&
    echo "安装成功，$(date '+%Y%m%d %T')秒启动..." &&
    adb shell am start -W com.example.myapplication/.MainActivity
    exit 1;
fi;

./gradlew installDebug &&
echo "安装成功，$(date '+%Y%m%d %T')秒启动..." &&
  adb shell am start -W com.example.myapplication/.MainActivity
#set +e