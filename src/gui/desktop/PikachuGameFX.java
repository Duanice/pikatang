package gui.desktop;

import boundary.AccountUI;
import boundary.RoleUI;
import control.AccountManager;
import control.RoleManager;
import entity.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import persistence.DatabaseManager;

/**
 * 皮卡堂过家家游戏 - JavaFX桌面版本
 * 图形用户界面版本，支持现代化的游戏体验
 */
public class PikachuGameFX extends Application {

    private Stage primaryStage;
    private Player currentPlayer;
    private AccountManager accountManager;
    private RoleManager roleManager;

    // 主界面组件
    private VBox mainLayout;
    private Label welcomeLabel;
    private Button accountBtn;
    private Button roleBtn;
    private Button homeBtn;
    private Button tradeBtn;
    private Button friendsBtn;
    private Button rechargeBtn;
    private Button logoutBtn;
    private Button exitBtn;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // 初始化管理器
        this.accountManager = new AccountManager();
        this.roleManager = new RoleManager();

        // 初始化数据库
        DatabaseManager.initializeDatabase();

        // 设置窗口属性
        primaryStage.setTitle("皮卡堂过家家游戏");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(true);

        // 设置窗口图标（如果有的话）
        try {
            // primaryStage.getIcons().add(new Image("file:resources/icons/game_icon.png"));
        } catch (Exception e) {
            // 忽略图标加载错误
        }

        // 创建主界面
        createMainInterface();

        // 显示主菜单
        showMainMenu();

        primaryStage.show();
    }

    /**
     * 创建主界面布局
     */
    private void createMainInterface() {
        mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #e8f4f8, #d4e8f3);");

        // 标题
        welcomeLabel = new Label("欢迎来到皮卡堂过家家游戏");
        welcomeLabel.setFont(Font.font("Microsoft YaHei", FontWeight.BOLD, 24));
        welcomeLabel.setTextFill(Color.DARKBLUE);

        // 游戏图标（占位符）
        ImageView gameIcon = new ImageView();
        gameIcon.setFitWidth(100);
        gameIcon.setFitHeight(100);
        gameIcon.setStyle("-fx-background-color: #ffcccc; -fx-border-radius: 50; -fx-background-radius: 50;");

        // 按钮布局
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(15);
        buttonGrid.setVgap(15);
        buttonGrid.setAlignment(Pos.CENTER);

        // 创建按钮
        accountBtn = createStyledButton("账号管理", "#4CAF50");
        roleBtn = createStyledButton("角色管理", "#2196F3");
        homeBtn = createStyledButton("家园建设", "#FF9800");
        tradeBtn = createStyledButton("虚拟交易", "#9C27B0");
        friendsBtn = createStyledButton("社区交友", "#FF5722");
        rechargeBtn = createStyledButton("充值系统", "#795548");
        logoutBtn = createStyledButton("注销登录", "#607D8B");
        exitBtn = createStyledButton("退出游戏", "#F44336");

        // 添加按钮到网格
        buttonGrid.add(accountBtn, 0, 0);
        buttonGrid.add(roleBtn, 1, 0);
        buttonGrid.add(homeBtn, 0, 1);
        buttonGrid.add(tradeBtn, 1, 1);
        buttonGrid.add(friendsBtn, 0, 2);
        buttonGrid.add(rechargeBtn, 1, 2);
        buttonGrid.add(logoutBtn, 0, 3);
        buttonGrid.add(exitBtn, 1, 3);

        // 设置按钮事件
        setupButtonEvents();

        // 添加到主布局
        mainLayout.getChildren().addAll(welcomeLabel, gameIcon, buttonGrid);

        // 创建场景
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
    }

    /**
     * 创建样式化的按钮
     */
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setPrefWidth(120);
        button.setPrefHeight(40);
        button.setFont(Font.font("Microsoft YaHei", FontWeight.NORMAL, 14));
        button.setStyle(
            "-fx-background-color: " + color + ";" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5;" +
            "-fx-border-radius: 5;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);"
        );

        // 鼠标悬停效果
        button.setOnMouseEntered(e ->
            button.setStyle(button.getStyle() + "-fx-background-color: derive(" + color + ", 20%);"));
        button.setOnMouseExited(e ->
            button.setStyle(button.getStyle().replace("-fx-background-color: derive(" + color + ", 20%);", "")));

        return button;
    }

    /**
     * 设置按钮事件处理
     */
    private void setupButtonEvents() {
        accountBtn.setOnAction(e -> showAccountManagement());
        roleBtn.setOnAction(e -> showRoleManagement());
        homeBtn.setOnAction(e -> showHomeConstruction());
        tradeBtn.setOnAction(e -> showVirtualTrading());
        friendsBtn.setOnAction(e -> showCommunityFriends());
        rechargeBtn.setOnAction(e -> showRechargeSystem());
        logoutBtn.setOnAction(e -> logout());
        exitBtn.setOnAction(e -> exitGame());
    }

    /**
     * 显示主菜单
     */
    private void showMainMenu() {
        if (currentPlayer == null) {
            welcomeLabel.setText("欢迎来到皮卡堂过家家游戏");
            // 未登录状态，只显示账号管理和退出
            roleBtn.setDisable(true);
            homeBtn.setDisable(true);
            tradeBtn.setDisable(true);
            friendsBtn.setDisable(true);
            rechargeBtn.setDisable(true);
            logoutBtn.setDisable(true);
        } else {
            welcomeLabel.setText("欢迎回来，" + currentPlayer.getUsername() + "！");
            // 已登录状态，启用所有功能
            roleBtn.setDisable(false);
            homeBtn.setDisable(false);
            tradeBtn.setDisable(false);
            friendsBtn.setDisable(false);
            rechargeBtn.setDisable(false);
            logoutBtn.setDisable(false);
        }
    }

    /**
     * 显示账号管理界面
     */
    private void showAccountManagement() {
        if (currentPlayer == null) {
            showLoginDialog();
        } else {
            showAccountMenuDialog();
        }
    }

    /**
     * 显示登录对话框
     */
    private void showLoginDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("登录账号");
        dialog.setHeaderText("请输入您的账号信息");

        // 创建登录表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        usernameField.setPromptText("用户名");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("密码");

        grid.add(new Label("用户名:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("密码:"), 0, 1);
        grid.add(passwordField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // 添加按钮
        ButtonType loginButtonType = new ButtonType("登录", ButtonBar.ButtonData.OK_DONE);
        ButtonType registerButtonType = new ButtonType("注册", ButtonBar.ButtonData.LEFT);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, registerButtonType, ButtonType.CANCEL);

        // 处理登录
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    showAlert("错误", "用户名和密码不能为空！");
                    return null;
                }

                Player player = accountManager.login(username, password);
                if (player != null) {
                    currentPlayer = player;
                    showMainMenu();
                    showAlert("成功", "登录成功！欢迎 " + username);
                } else {
                    showAlert("错误", "用户名或密码错误！");
                    return null;
                }
            } else if (dialogButton == registerButtonType) {
                showRegisterDialog();
            }
            return null;
        });

        dialog.showAndWait();
    }

    /**
     * 显示注册对话框
     */
    private void showRegisterDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("注册账号");
        dialog.setHeaderText("创建新账号");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        usernameField.setPromptText("用户名");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("密码");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("确认密码");
        TextField emailField = new TextField();
        emailField.setPromptText("邮箱");

        grid.add(new Label("用户名:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("密码:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("确认密码:"), 0, 2);
        grid.add(confirmPasswordField, 1, 2);
        grid.add(new Label("邮箱:"), 0, 3);
        grid.add(emailField, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                String email = emailField.getText().trim();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    showAlert("错误", "所有字段都必须填写！");
                    return null;
                }

                if (!password.equals(confirmPassword)) {
                    showAlert("错误", "两次输入的密码不一致！");
                    return null;
                }

                boolean success = accountManager.registerAccount(username, password, email);
                if (success) {
                    showAlert("成功", "注册成功！请登录您的账号。");
                } else {
                    showAlert("错误", "注册失败！用户名可能已存在。");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    /**
     * 显示账号菜单对话框
     */
    private void showAccountMenuDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("账号管理");
        alert.setHeaderText("选择操作");

        ButtonType modifyPasswordBtn = new ButtonType("修改密码");
        ButtonType modifyEmailBtn = new ButtonType("修改邮箱");
        ButtonType deleteAccountBtn = new ButtonType("注销账号");
        ButtonType cancelBtn = new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(modifyPasswordBtn, modifyEmailBtn, deleteAccountBtn, cancelBtn);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == modifyPasswordBtn) {
                showChangePasswordDialog();
            } else if (buttonType == modifyEmailBtn) {
                showChangeEmailDialog();
            } else if (buttonType == deleteAccountBtn) {
                showDeleteAccountDialog();
            }
        });
    }

    /**
     * 显示修改密码对话框
     */
    private void showChangePasswordDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("修改密码");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        PasswordField oldPasswordField = new PasswordField();
        oldPasswordField.setPromptText("旧密码");
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("新密码");

        grid.add(new Label("旧密码:"), 0, 0);
        grid.add(oldPasswordField, 1, 0);
        grid.add(new Label("新密码:"), 0, 1);
        grid.add(newPasswordField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String oldPassword = oldPasswordField.getText();
                String newPassword = newPasswordField.getText();

                if (oldPassword.isEmpty() || newPassword.isEmpty()) {
                    showAlert("错误", "密码不能为空！");
                    return null;
                }

                boolean success = accountManager.changePassword(currentPlayer, oldPassword, newPassword);
                if (success) {
                    showAlert("成功", "密码修改成功！");
                } else {
                    showAlert("错误", "密码修改失败！");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    /**
     * 显示修改邮箱对话框
     */
    private void showChangeEmailDialog() {
        TextInputDialog dialog = new TextInputDialog(currentPlayer.getEmail());
        dialog.setTitle("修改邮箱");
        dialog.setHeaderText("输入新的邮箱地址");
        dialog.setContentText("邮箱:");

        dialog.showAndWait().ifPresent(email -> {
            if (email.trim().isEmpty()) {
                showAlert("错误", "邮箱不能为空！");
                return;
            }

            boolean success = accountManager.changeEmail(currentPlayer, email.trim());
            if (success) {
                showAlert("成功", "邮箱修改成功！");
            } else {
                showAlert("错误", "邮箱修改失败！");
            }
        });
    }

    /**
     * 显示注销账号对话框
     */
    private void showDeleteAccountDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("注销账号");
        alert.setHeaderText("确定要注销账号吗？");
        alert.setContentText("此操作不可撤销，所有数据将被删除！");

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                boolean success = accountManager.deleteAccount(currentPlayer);
                if (success) {
                    currentPlayer = null;
                    showMainMenu();
                    showAlert("成功", "账号已注销！");
                } else {
                    showAlert("错误", "账号注销失败！");
                }
            }
        });
    }

    /**
     * 显示角色管理界面
     */
    private void showRoleManagement() {
        if (currentPlayer == null) {
            showAlert("错误", "请先登录账号！");
            return;
        }

        // 检查玩家是否有角色
        entity.Role role = roleManager.getRoleByPlayerId(currentPlayer.getPlayerId());

        if (role == null) {
            // 没有角色，显示创建角色对话框
            showCreateRoleDialog();
        } else {
            // 有角色，显示角色管理菜单
            showRoleMenuDialog(role);
        }
    }

    /**
     * 显示创建角色对话框
     */
    private void showCreateRoleDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("创建角色");
        dialog.setHeaderText("为您的账号创建游戏角色");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("角色名");
        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.getItems().addAll("男", "女", "其他");
        genderCombo.setValue("男");
        TextField appearanceField = new TextField();
        appearanceField.setPromptText("外观描述");

        grid.add(new Label("角色名:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("性别:"), 0, 1);
        grid.add(genderCombo, 1, 1);
        grid.add(new Label("外观描述:"), 0, 2);
        grid.add(appearanceField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String name = nameField.getText().trim();
                String gender = genderCombo.getValue();
                String appearance = appearanceField.getText().trim();

                if (name.isEmpty() || appearance.isEmpty()) {
                    showAlert("错误", "角色名和外观描述不能为空！");
                    return null;
                }

                boolean success = roleManager.createRole(currentPlayer, name, gender, appearance);
                if (success) {
                    showAlert("成功", "角色创建成功！");
                } else {
                    showAlert("错误", "角色创建失败！");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    /**
     * 显示角色菜单对话框
     */
    private void showRoleMenuDialog(entity.Role role) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("角色信息");
        alert.setHeaderText("您的角色：" + role.getName());

        String content = String.format(
            "等级: %d\n经验: %d\n性别: %s\n外观: %s\n技能数量: %d",
            role.getLevel(), role.getExperience(), role.getGender(),
            role.getAppearance(), role.getSkills().size()
        );
        alert.setContentText(content);

        ButtonType viewSkillsBtn = new ButtonType("查看技能");
        ButtonType learnSkillBtn = new ButtonType("学习技能");
        ButtonType gainExpBtn = new ButtonType("提升经验");
        ButtonType editRoleBtn = new ButtonType("编辑角色");
        ButtonType closeBtn = new ButtonType("关闭", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(viewSkillsBtn, learnSkillBtn, gainExpBtn, editRoleBtn, closeBtn);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == viewSkillsBtn) {
                showSkillsDialog(role);
            } else if (buttonType == learnSkillBtn) {
                showLearnSkillDialog(role);
            } else if (buttonType == gainExpBtn) {
                showGainExperienceDialog(role);
            } else if (buttonType == editRoleBtn) {
                showEditRoleDialog(role);
            }
        });
    }

    /**
     * 显示技能对话框
     */
    private void showSkillsDialog(entity.Role role) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("角色技能");
        alert.setHeaderText(role.getName() + " 的技能");

        if (role.getSkills().isEmpty()) {
            alert.setContentText("暂无技能");
        } else {
            StringBuilder skillsText = new StringBuilder();
            for (entity.Skill skill : role.getSkills()) {
                skillsText.append(skill.getName())
                          .append(" (等级: ")
                          .append(skill.getLevel())
                          .append(")\n");
            }
            alert.setContentText(skillsText.toString());
        }

        alert.showAndWait();
    }

    /**
     * 显示学习技能对话框
     */
    private void showLearnSkillDialog(entity.Role role) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("学习技能");
        dialog.setHeaderText("选择要学习的技能");

        ComboBox<String> skillCombo = new ComboBox<>();
        skillCombo.getItems().addAll("挖矿", "钓鱼", "种植", "烹饪");
        skillCombo.setValue("挖矿");

        VBox content = new VBox(10);
        content.getChildren().addAll(new Label("选择技能:"), skillCombo);

        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String skillName = skillCombo.getValue();
                String skillType = getSkillType(skillName);
                String description = getSkillDescription(skillName);

                entity.Skill newSkill = new entity.Skill(skillName, skillType, description);
                boolean success = roleManager.learnSkill(role, newSkill);

                if (success) {
                    showAlert("成功", "技能学习成功！");
                } else {
                    showAlert("错误", "技能学习失败！可能已拥有此技能。");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    /**
     * 显示提升经验对话框
     */
    private void showGainExperienceDialog(entity.Role role) {
        TextInputDialog dialog = new TextInputDialog("100");
        dialog.setTitle("提升经验");
        dialog.setHeaderText("输入要提升的经验值");
        dialog.setContentText("经验值:");

        dialog.showAndWait().ifPresent(expStr -> {
            try {
                int exp = Integer.parseInt(expStr);
                if (exp <= 0) {
                    showAlert("错误", "经验值必须大于0！");
                    return;
                }

                boolean success = roleManager.gainExperience(role, exp);
                if (success) {
                    showAlert("成功", "经验提升成功！当前等级: " + role.getLevel());
                } else {
                    showAlert("错误", "经验提升失败！");
                }
            } catch (NumberFormatException e) {
                showAlert("错误", "请输入有效的数字！");
            }
        });
    }

    /**
     * 显示编辑角色对话框
     */
    private void showEditRoleDialog(entity.Role role) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("编辑角色");
        dialog.setHeaderText("修改角色信息");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField(role.getName());
        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.getItems().addAll("男", "女", "其他");
        genderCombo.setValue(role.getGender());
        TextField appearanceField = new TextField(role.getAppearance());

        grid.add(new Label("角色名:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("性别:"), 0, 1);
        grid.add(genderCombo, 1, 1);
        grid.add(new Label("外观描述:"), 0, 2);
        grid.add(appearanceField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String name = nameField.getText().trim();
                String gender = genderCombo.getValue();
                String appearance = appearanceField.getText().trim();

                if (name.isEmpty() || appearance.isEmpty()) {
                    showAlert("错误", "角色名和外观描述不能为空！");
                    return null;
                }

                boolean success = roleManager.updateRole(role, name, gender, appearance);
                if (success) {
                    showAlert("成功", "角色信息修改成功！");
                } else {
                    showAlert("错误", "角色信息修改失败！");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    /**
     * 显示家园建设界面
     */
    private void showHomeConstruction() {
        showAlert("提示", "家园建设功能正在开发中，敬请期待！");
    }

    /**
     * 显示虚拟交易界面
     */
    private void showVirtualTrading() {
        showAlert("提示", "虚拟交易功能正在开发中，敬请期待！");
    }

    /**
     * 显示社区交友界面
     */
    private void showCommunityFriends() {
        showAlert("提示", "社区交友功能正在开发中，敬请期待！");
    }

    /**
     * 显示充值系统界面
     */
    private void showRechargeSystem() {
        showAlert("提示", "充值系统功能正在开发中，敬请期待！");
    }

    /**
     * 注销登录
     */
    private void logout() {
        currentPlayer = null;
        showMainMenu();
        showAlert("成功", "已注销登录");
    }

    /**
     * 退出游戏
     */
    private void exitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("退出游戏");
        alert.setHeaderText("确定要退出游戏吗？");

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                DatabaseManager.closeConnection();
                primaryStage.close();
            }
        });
    }

    /**
     * 显示提示对话框
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 获取技能类型
     */
    private String getSkillType(String skillName) {
        switch (skillName) {
            case "挖矿":
            case "钓鱼":
            case "种植":
                return "生产技能";
            case "烹饪":
                return "生活技能";
            default:
                return "其他技能";
        }
    }

    /**
     * 获取技能描述
     */
    private String getSkillDescription(String skillName) {
        switch (skillName) {
            case "挖矿":
                return "能够进行挖矿作业";
            case "钓鱼":
                return "能够进行钓鱼作业";
            case "种植":
                return "能够进行种植作业";
            case "烹饪":
                return "能够进行烹饪作业";
            default:
                return "技能描述";
        }
    }

    /**
     * 主方法
     */
    public static void main(String[] args) {
        launch(args);
    }
}