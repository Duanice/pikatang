#!/bin/bash

# 编译皮卡堂过家家游戏
echo "编译皮卡堂过家家游戏..."

# 设置Java环境
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

# 创建输出目录
mkdir -p bin

# 编译所有Java文件（排除Web版本因为缺少Spring依赖）
# 尝试JavaFX编译，如果失败则使用标准编译
if [ -d "lib/javafx/lib" ]; then
    JAVAFX_PATH="lib/javafx/lib"
    javac --module-path "$JAVAFX_PATH" --add-modules javafx.controls,javafx.fxml -cp "lib/*:." -d bin src/*.java src/boundary/*.java src/control/*.java src/entity/*.java src/persistence/*.java src/gui/desktop/*.java 2>/dev/null
fi

# 如果JavaFX编译失败或没有JavaFX，使用标准编译
if [ ! -d "bin" ] || [ -z "$(ls -A bin)" ]; then
    javac -cp "lib/*:." -d bin src/*.java src/boundary/*.java src/control/*.java src/entity/*.java src/persistence/*.java
fi

if [ $? -eq 0 ]; then
    echo "编译成功！"
    echo ""
    echo "🎮 可用的运行方式："
    echo "1. 控制台版本: ./run.sh"
    echo "2. 图形界面版本: ./run_fx.sh"
    echo "3. 演示版本: ./run_demo.sh"
else
    echo "编译失败！"
    exit 1
fi