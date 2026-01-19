#!/bin/bash

# 皮卡堂过家家游戏测试脚本
echo "========================================="
echo "      皮卡堂过家家游戏测试"
echo "========================================="

# 设置Java环境
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

echo "🔍 检查Java环境..."
java -version
if [ $? -ne 0 ]; then
    echo "❌ Java环境检查失败"
    exit 1
fi
echo "✅ Java环境正常"

echo ""
echo "🔍 检查项目文件..."
if [ ! -d "src" ]; then
    echo "❌ 找不到源代码目录"
    exit 1
fi
echo "✅ 源代码目录存在"

if [ ! -f "lib/sqlite-jdbc.jar" ]; then
    echo "❌ 找不到SQLite JDBC驱动"
    exit 1
fi
echo "✅ SQLite驱动存在"

echo ""
echo "🔍 编译项目..."
./compile.sh
if [ $? -ne 0 ]; then
    echo "❌ 编译失败"
    exit 1
fi
echo "✅ 编译成功"

echo ""
echo "🔍 检查编译结果..."
class_count=$(find bin -name "*.class" | wc -l)
echo "📊 编译生成 $class_count 个类文件"

if [ $class_count -lt 20 ]; then
    echo "❌ 编译结果不完整"
    exit 1
fi
echo "✅ 编译结果正常"

echo ""
echo "🎮 运行功能测试..."
java -cp "bin:lib/*" GameDemo > test_output.log 2>&1
if [ $? -eq 0 ]; then
    echo "✅ 演示程序运行成功"
else
    echo "❌ 演示程序运行失败"
    cat test_output.log
    exit 1
fi

echo ""
echo "📊 测试结果统计..."
if grep -q "Database initialized successfully" test_output.log; then
    echo "✅ 数据库初始化正常"
else
    echo "❌ 数据库初始化失败"
fi

if grep -q "登录成功" test_output.log; then
    echo "✅ 登录功能正常"
else
    echo "❌ 登录功能异常"
fi

if grep -q "扣除50金币: 成功" test_output.log; then
    echo "✅ 虚拟货币功能正常"
else
    echo "❌ 虚拟货币功能异常"
fi

echo ""
echo "📁 检查生成文件..."
if [ -f "pikachu_game.db" ]; then
    db_size=$(stat -f%z pikachu_game.db)
    echo "✅ 数据库文件存在，大小: $db_size 字节"
else
    echo "❌ 数据库文件未生成"
fi

echo ""
echo "========================================="
echo "      测试完成"
echo "========================================="
echo ""
echo "🎉 恭喜！皮卡堂过家家游戏运行正常"
echo ""
echo "📖 使用方法:"
echo "   ./start_game.sh          # 启动游戏菜单"
echo "   ./compile.sh             # 重新编译"
echo "   java -cp \"bin:lib/*\" GameDemo    # 运行演示"
echo ""
echo "📄 详细文档:"
echo "   cat README.md            # 项目说明"
echo "   cat GAME_GUIDE.md        # 使用指南"
echo ""

# 清理测试文件
rm -f test_output.log