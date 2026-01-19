# 皮卡堂过家家游戏 🎮

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)



**课程项目**: 皮卡堂过家家

## 项目结构

```
pikachu_game/
├── src/
│   ├── boundary/          # 边界层（用户界面）
│   │   ├── AccountUI.java     # 账号管理界面
│   │   └── RoleUI.java        # 角色管理界面
│   ├── control/           # 控制层（业务逻辑）
│   │   ├── AccountManager.java # 账号管理器
│   │   └── RoleManager.java    # 角色管理器
│   ├── entity/            # 实体层（数据模型）
│   │   ├── Player.java         # 玩家实体
│   │   ├── Role.java           # 角色实体
│   │   ├── VirtualCurrency.java # 虚拟货币实体
│   │   ├── Skill.java          # 技能实体
│   │   ├── Clothing.java       # 服装实体
│   │   ├── Accessory.java      # 配饰实体
│   │   ├── Wardrobe.java       # 衣橱实体
│   │   ├── Outfit.java         # 穿搭实体
│   │   ├── Task.java           # 任务实体
│   │   ├── Friend.java         # 好友实体
│   │   ├── Transaction.java    # 交易实体
│   │   ├── Recharge.java       # 充值实体
│   │   ├── Room.java           # 房间实体
│   │   ├── Furniture.java      # 家具实体
│   │   ├── Farm.java           # 农场实体
│   │   ├── Crop.java           # 农作物实体
│   │   ├── Pasture.java        # 牧场实体
│   │   ├── Animal.java         # 动物实体
│   │   └── Account.java        # 账号实体
│   ├── persistence/        # 持久化层（数据访问）
│   │   ├── DatabaseManager.java # 数据库管理器
│   │   ├── PlayerDAO.java      # 玩家数据访问对象
│   │   └── RoleDAO.java        # 角色数据访问对象
│   ├── PikachuGame.java    # 主游戏类
│   └── GameDemo.java       # 功能演示类
├── lib/
│   ├── sqlite-jdbc.jar     # SQLite JDBC驱动
│   └── spring-boot-starter-web.jar # Spring Boot Web框架
├── .gitignore              # Git忽略文件
├── compile.sh              # 编译脚本
├── run.sh                  # 运行脚本
├── run_fx.sh               # JavaFX图形界面运行脚本
├── run_swing.sh            # Swing图形界面运行脚本
├── run_web.sh              # Web版本运行脚本
├── create_jar.sh           # JAR文件创建脚本
├── start_game.sh           # 智能启动器
├── test_game.sh            # 测试脚本
├── GAME_GUIDE.md           # 详细使用指南
└── README.md              # 项目说明
```

### 已实现功能
- ✅ **账号管理**：注册、登录、修改密码、修改邮箱、注销账号
- ✅ **角色管理**：创建角色、修改角色信息、学习技能、提升经验
- ✅ **虚拟货币系统**：金币和银币的管理
- ✅ **技能系统**：多种类型技能的学习和管理
- ✅ **数据持久化**：基于SQLite的JDBC数据存储
- ✅ **Swing图形界面**：现代化桌面应用程序（推荐）
- ✅ **JavaFX图形界面**：高品质桌面应用程序
- ✅ **角色形象定制**：可视化服装选择和模特展示
- ✅ **饰品系统**：帽子、眼镜、项链等配饰
- ✅ **肤色选择**：多种肤色选项
- ✅ **Web浏览器界面**：网页版本游戏
- ✅ **可执行文件打包**：支持JAR文件分发



## 运行环境

### 系统要求
- **Java版本**: JDK 17 或更高版本
- **操作系统**: macOS, Linux, Windows
- **内存**: 最少 256MB 可用内存（图形界面需要更多）
- **数据库**: SQLite 3.x（自动包含在JDBC驱动中）

### 依赖说明
项目所需的外部依赖已经包含在`lib/`目录中：
- `sqlite-jdbc.jar` - SQLite数据库驱动
- `spring-boot-starter-web.jar` - Spring Boot Web框架（用于Web版本）

如果需要JavaFX支持，请下载OpenJFX并将其JAR文件添加到`lib/`目录中。

## 🚀 快速开始

### 环境要求
- **Java版本**: JDK 17 或更高版本
- **操作系统**: macOS, Linux, Windows
- **内存**: 最少 256MB 可用内存（图形界面需要更多）

### 智能启动器（推荐）🌟
```bash
./start_game.sh
```
**自动菜单选择**：
- 🚀 演示模式
- 🎮 控制台完整版
- 🖥️ JavaFX图形界面
- 🌐 Web浏览器版
- 📖 使用指南
- 🧪 系统测试

### 单独运行版本

#### 控制台版本（传统命令行）
```bash
./compile.sh  # 先编译
java -cp "bin:lib/*" PikachuGame
```

#### 演示版本（自动展示功能）
```bash
./compile.sh  # 先编译
java -cp "bin:lib/*" GameDemo
```

#### Swing图形界面版本（桌面应用，推荐）🖥️
```bash
./run_swing.sh
# 脚本会自动编译并运行
```


#### JavaFX图形界面版本（桌面应用）🎨
```bash
./run_fx.sh
```
⚠️  **注意**: JavaFX需要图形环境支持和JavaFX运行时，在纯终端环境中可能无法运行。需要先下载并安装JavaFX。

#### Web浏览器版本（网页应用）🌐
```bash
./run_web.sh
# 然后在浏览器访问: http://localhost:8080
```

### 创建可执行文件 📦
```bash
./create_jar.sh
```
**生成文件**：
- `PikachuGame.jar` - 控制台版本
- `PikachuGameFX.jar` - JavaFX图形界面版本

## 数据库设计

使用SQLite数据库，包含以下主要表：
- `players` - 玩家信息表
- `roles` - 角色信息表
- `skills` - 技能表
- `role_skills` - 角色技能关联表
- `clothings` - 服装表
- `accessories` - 配饰表
- `wardrobes` - 衣橱表
- `wardrobe_clothings` - 衣橱服装关联表
- `wardrobe_accessories` - 衣橱配饰关联表


## 扩展计划

- [ ] 家园建设系统
- [ ] 虚拟交易系统
- [ ] 社区交友功能
- [ ] 充值系统
- [ ] 任务系统
- [ ] 农场牧场管理
- [ ] 支付系统集成


