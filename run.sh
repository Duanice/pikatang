#!/bin/bash

# 运行皮卡堂过家家游戏
echo "启动皮卡堂过家家游戏..."

# 检查是否已编译
if [ ! -d "bin" ]; then
    echo "请先运行编译脚本：./compile.sh"
    exit 1
fi

# 运行主程序
java -cp "bin:lib/*" PikachuGame