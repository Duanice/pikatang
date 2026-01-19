#!/bin/bash

# 运行皮卡堂过家家游戏 - Swing图形界面版本
echo "🖥️ 启动皮卡堂过家家游戏 - Swing图形界面版本..."

# 设置Java环境
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

# 检查是否已编译
if [ ! -d "bin" ]; then
    echo "❌ 请先运行编译脚本：./compile.sh"
    exit 1
fi

# 运行Swing应用程序
java -cp "bin:lib/sqlite-jdbc.jar" gui.desktop.PikachuGameSwing

echo ""
echo "感谢游玩皮卡堂过家家游戏！"