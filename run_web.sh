#!/bin/bash

# 运行皮卡堂过家家游戏 - Web版本
echo "🌐 启动皮卡堂过家家游戏 - Web版本..."

# 设置Java环境
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

# 检查是否已编译
if [ ! -d "bin" ]; then
    echo "❌ 请先运行编译脚本：./compile.sh"
    exit 1
fi

# 运行Spring Boot Web应用程序
echo "🚀 启动Web服务器..."
echo "📱 浏览器访问: http://localhost:8080"
echo "🛑 按 Ctrl+C 停止服务器"
echo ""

java -cp "bin:lib/*" gui.web.PikachuWebApplication