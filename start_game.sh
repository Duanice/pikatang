#!/bin/bash

# 皮卡堂过家家游戏启动器
echo "========================================="
echo "      皮卡堂过家家游戏启动器"
echo "========================================="

# 检查Java环境
if ! command -v java &> /dev/null; then
    echo "❌ 错误: 未找到Java运行环境"
    echo "请安装JDK 17或更高版本"
    echo "macOS: brew install openjdk@17"
    exit 1
fi

# 设置Java环境变量
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

# 检查项目文件
if [ ! -d "bin" ]; then
    echo "📦 正在编译游戏..."
    ./compile.sh
    if [ $? -ne 0 ]; then
        echo "❌ 编译失败，请检查代码"
        exit 1
    fi
fi

if [ ! -f "lib/sqlite-jdbc.jar" ]; then
    echo "❌ 错误: 找不到SQLite JDBC驱动"
    echo "请确保 lib/sqlite-jdbc.jar 文件存在"
    exit 1
fi

echo ""
echo "请选择游戏模式:"
echo "1) 🚀 演示模式 (自动展示功能)"
echo "2) 🎮 完整控制台模式 (交互式游戏)"
echo "3) 🖥️  Swing图形界面 (推荐，兼容性好)"
echo "4) 🎨 JavaFX图形界面 (需要图形环境)"
echo "5) 👗 角色装饰演示 (新功能介绍)"
echo "6) 🎭 角色装饰指南 (新功能介绍)"
echo "7) 📖 查看使用指南"
echo "8) 🧪 运行测试"
echo "0) 退出"
echo ""
read -p "请输入选择 (0-8): " choice

case $choice in
    1)
        echo ""
        echo "🎯 启动演示模式..."
        echo ""
        export JAVA_HOME=/opt/homebrew/opt/openjdk@17
        export PATH=$JAVA_HOME/bin:$PATH
        java -cp "bin:lib/*" GameDemo
        ;;
    2)
        echo ""
        echo "🎯 启动完整控制台游戏模式..."
        echo "注意: 这是一个交互式程序，需要键盘输入"
        echo ""
        export JAVA_HOME=/opt/homebrew/opt/openjdk@17
        export PATH=$JAVA_HOME/bin:$PATH
        java -cp "bin:lib/*" PikachuGame
        ;;
    3)
        echo ""
        echo "🎯 启动Swing图形界面模式..."
        echo "Swing界面兼容性更好，适合各种环境"
        echo ""
        ./run_swing.sh
        ;;
    4)
        echo ""
        echo "🎯 启动JavaFX图形界面模式..."
        echo "JavaFX需要图形环境支持，可能在终端中无法显示"
        echo ""
        ./run_fx.sh
        ;;
    5)
        echo ""
        echo "👗 显示角色装饰功能演示..."
        echo ""
        ./demo_dressup.sh
        echo ""
        read -p "按回车键继续..."
        ;;
    6)
        echo ""
        echo "🎭 显示外观设置指南..."
        if command -v less &> /dev/null; then
            less APPEARANCE_GUIDE.md
        else
            cat APPEARANCE_GUIDE.md | more
        fi
        echo ""
        read -p "按回车键继续..."
        ;;
    7)
        echo ""
        echo "📖 显示使用指南..."
        if command -v less &> /dev/null; then
            less GAME_GUIDE.md
        else
            cat GAME_GUIDE.md | more
        fi
        echo ""
        read -p "按回车键继续..."
        ;;
    8)
        echo ""
        echo "🧪 运行完整测试..."
        echo ""
        ./test_game.sh
        ;;
    0)
        echo ""
        echo "👋 感谢游玩，再见！"
        exit 0
        ;;
    *)
        echo ""
        echo "❌ 无效选择，请重新运行脚本"
        exit 1
        ;;
esac

echo ""
echo "========================================="
echo "游戏结束，感谢体验！"
echo "========================================="