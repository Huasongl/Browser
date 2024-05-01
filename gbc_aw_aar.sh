#!/bin/bash

#set -e
browser_path=$(pwd)
libs_path=$browser_path/app/libs/aar
cd ../../chromium/src &&
echo "编译$1版本GBC_Android_WebView"

[ "$1" == "debug" ] && { out_dir=Default targetCPU="arm64" isJavaDebug=true; }

gn gen out/$out_dir --args="
    target_os=\"android\"
    target_cpu=\"${targetCPU}\"
    is_java_debug=$isJavaDebug" &&

echo "编译WebView..." &&
ninja -C out/$out_dir gbc_aw_aar || exit 1
echo "编译gbc_aw_aar.aar ""$1""..." &&
if [ ! -d "$libs_path" ]; then
  mkdir "$libs_path"
fi
rm -f "$libs_path"/GbcAw.aar &&
cp ./out/$out_dir/GbcAw.aar "$libs_path"/GbcAw.aar && exit 0