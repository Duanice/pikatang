#!/bin/bash

# 皮卡堂过家家游戏 - 角色装饰功能演示
echo "========================================="
echo "      角色装饰功能演示"
echo "========================================="

# 设置Java环境
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

# 检查是否已编译
if [ ! -d "bin" ]; then
    echo "❌ 请先运行编译脚本：./compile.sh"
    exit 1
fi

echo ""
echo "🎨 角色装饰功能特色："
echo "• 可视化模特显示"
echo "• 实时服装预览"
echo "• 多样化服装选择"
echo "• 饰品搭配系统"
echo "• 肤色自定义"
echo "• 一键换装效果"
echo ""

echo "📖 使用方法："
echo "1. 运行 ./run_swing.sh 启动Swing界面"
echo "2. 登录或注册账号"
echo "3. 点击'角色管理'"
echo "4. 创建或选择角色"
echo "5. 在角色菜单中选择'装饰角色'"
echo "6. 在弹出的装饰窗口中："
echo "   - 左侧：实时模特预览"
echo "   - 右侧：服装和饰品选择"
echo "   - 底部：保存或取消按钮"
echo ""

echo "🎭 可选服装款式："
echo "上衣：T恤、衬衫、毛衣、夹克、西装上衣、卫衣、连帽衫"
echo "下衣：牛仔裤、休闲裤、运动裤、短裤、裙子、短裙"
echo "连衣裙：连衣裙、晚礼服、小礼服、长裙、短裙"
echo "鞋子：运动鞋、皮鞋、凉鞋、靴子、高跟鞋、拖鞋"
echo ""

echo "💎 可选饰品："
echo "帽子：棒球帽、贝雷帽、礼帽、鸭舌帽、渔夫帽、牛仔帽"
echo "眼镜：墨镜、圆框眼镜、方框眼镜、细框眼镜、蛤蟆镜"
echo "项链：项链、吊坠、珠链、锁链、十字架"
echo ""

echo "🌈 肤色选择："
echo "• 默认肤色"
echo "• 白色"
echo "• 浅黄色"
echo "• 小麦色"
echo "• 古铜色"
echo "• 深色"
echo ""

echo "🎮 现在就开始体验吧！"
echo ""
echo "运行命令："
echo "  ./run_swing.sh"
echo ""
echo "========================================="