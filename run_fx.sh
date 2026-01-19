#!/bin/bash

# 运行皮卡堂过家家游戏 - JavaFX图形界面版本
echo "🎮 启动皮卡堂过家家游戏 - 图形界面版本..."

# 设置Java环境
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

# 设置JavaFX路径
JAVAFX_PATH="lib/javafx/lib"

# 检查是否已编译
if [ ! -d "bin" ]; then
    echo "❌ 请先运行编译脚本：./compile.sh"
    exit 1
fi

# 检查JavaFX是否存在
if [ ! -d "$JAVAFX_PATH" ]; then
    echo "❌ 找不到JavaFX库，请确保已正确安装"
    echo "请运行：./compile.sh"
    exit 1
fi

# 启动虚拟显示服务器
echo "启动虚拟显示服务器..."
Xvfb :99 -screen 0 1024x768x24 &
XVFB_PID=$!
export DISPLAY=:99

# 等待虚拟显示启动
sleep 2

# 运行JavaFX应用程序
java --module-path "$JAVAFX_PATH" --add-modules javafx.controls,javafx.fxml \
     -cp "bin:lib/sqlite-jdbc.jar" gui.desktop.PikachuGameFX

# 清理虚拟显示进程
kill $XVFB_PID 2>/dev/null

echo ""
echo "感谢游玩皮卡堂过家家游戏！"