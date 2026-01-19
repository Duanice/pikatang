#!/bin/bash

# 创建可执行JAR文件 - 皮卡堂过家家游戏
echo "📦 创建可执行JAR文件..."

# 设置Java环境
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

# 检查是否已编译
if [ ! -d "bin" ]; then
    echo "❌ 请先运行编译脚本：./compile.sh"
    exit 1
fi

# 创建临时目录用于JAR打包
mkdir -p temp_jar

# 复制编译后的class文件
cp -r bin/* temp_jar/

# 复制依赖库
mkdir -p temp_jar/lib
cp lib/sqlite-jdbc.jar temp_jar/lib/

# 创建JAR清单文件
cat > temp_jar/MANIFEST.MF << EOF
Manifest-Version: 1.0
Main-Class: PikachuGame
Class-Path: lib/sqlite-jdbc.jar
EOF

# 创建控制台版本JAR
echo "🔨 创建控制台版本JAR..."
jar cfm PikachuGame.jar temp_jar/MANIFEST.MF -C temp_jar .

# 创建JavaFX版本JAR（需要特殊处理）
echo "🔨 创建JavaFX图形界面版本JAR..."
cat > temp_jar/MANIFEST-FX.MF << EOF
Manifest-Version: 1.0
Main-Class: gui.desktop.PikachuGameFX
Class-Path: lib/sqlite-jdbc.jar
EOF

jar cfm PikachuGameFX.jar temp_jar/MANIFEST-FX.MF -C temp_jar .

# 清理临时文件
rm -rf temp_jar

echo "✅ JAR文件创建成功！"
echo ""
echo "📁 生成的文件："
echo "   PikachuGame.jar    - 控制台版本"
echo "   PikachuGameFX.jar  - 图形界面版本"
echo ""
echo "🚀 运行方式："
echo "   控制台版: java -jar PikachuGame.jar"
echo "   图形界面: java --module-path lib/javafx/lib --add-modules javafx.controls,javafx.fxml -jar PikachuGameFX.jar"
echo ""
echo "💡 提示：图形界面版本需要JavaFX支持"