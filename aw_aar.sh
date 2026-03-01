#!/bin/bash

set -e  # 任何命令失败立即退出

browser_path=$(pwd)
libs_path=$browser_path/app/libs/aar

# 参数校验
if [ "$1" == "debug" ]; then
    out_dir="Debug64"
    targetCPU="arm64"
    isDebug=true
    isJavaDebug=true
    isComponentBuild=true
elif [ "$1" == "release" ]; then
    out_dir="Release64"
    targetCPU="arm64"
    isDebug=false
    isJavaDebug=false
    isComponentBuild=false
else
    echo "用法: $0 [debug|release]"
    exit 1
fi

echo "编译 $1 版本 Android_WebView..."

cd ./chromium/src

gn gen out/$out_dir --args="
    target_os=\"android\"
    target_cpu=\"${targetCPU}\"
    is_debug=$isDebug
    is_java_debug=$isJavaDebug
    is_component_build=$isComponentBuild
    proprietary_codecs=true
    ffmpeg_branding=\"Chrome\""

echo "编译 WebView..."
ninja -C out/$out_dir aw_aar

echo "编译 aw_aar.aar $1 完成，正在拷贝..."

if [ ! -d "$libs_path" ]; then
    mkdir -p "$libs_path"
fi

rm -f "$libs_path/Aw.aar"
cp "./out/$out_dir/Aw.aar" "$libs_path/Aw.aar"

echo "✅ 构建完成：$libs_path/Aw.aar"
exit 0