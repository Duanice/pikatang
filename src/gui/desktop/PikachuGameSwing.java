package gui.desktop;

import boundary.AccountUI;
import control.AccountManager;
import control.RoleManager;
import entity.Player;
import persistence.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 皮卡堂过家家游戏 - Swing图形界面版本
 * 使用Swing组件，不依赖JavaFX，适合无图形环境
 */
public class PikachuGameSwing extends JFrame {
    private Player currentPlayer;
    private AccountManager accountManager;
    private RoleManager roleManager;

    // 主界面组件
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JButton accountBtn;
    private JButton roleBtn;
    private JButton homeBtn;
    private JButton tradeBtn;
    private JButton friendsBtn;
    private JButton rechargeBtn;
    private JButton logoutBtn;
    private JButton exitBtn;

    public PikachuGameSwing() {
        // 初始化管理器
        this.accountManager = new AccountManager();
        this.roleManager = new RoleManager();

        // 初始化数据库
        DatabaseManager.initializeDatabase();

        // 设置窗口属性
        setTitle("皮卡堂过家家游戏 - Swing版本");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建主界面
        createMainInterface();

        // 显示主菜单
        showMainMenu();
    }

    /**
     * 创建主界面布局
     */
    private void createMainInterface() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(232, 248, 248));

        // 标题面板
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(232, 248, 248));

        welcomeLabel = new JLabel("欢迎来到皮卡堂过家家游戏", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(0, 0, 139));
        titlePanel.add(welcomeLabel);

        // 游戏图标（简单占位）
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(new Color(255, 204, 204));
        iconPanel.setPreferredSize(new Dimension(120, 120));
        iconPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        // 按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        buttonPanel.setBackground(new Color(232, 248, 248));

        // 创建按钮
        accountBtn = createStyledButton("账号管理", new Color(76, 175, 80));
        roleBtn = createStyledButton("角色管理", new Color(33, 150, 243));
        homeBtn = createStyledButton("家园建设", new Color(255, 152, 0));
        tradeBtn = createStyledButton("虚拟交易", new Color(156, 39, 176));
        friendsBtn = createStyledButton("社区交友", new Color(244, 67, 54));
        rechargeBtn = createStyledButton("充值系统", new Color(121, 85, 72));
        logoutBtn = createStyledButton("注销登录", new Color(96, 125, 139));
        exitBtn = createStyledButton("退出游戏", new Color(244, 67, 54));

        // 添加按钮到面板
        buttonPanel.add(accountBtn);
        buttonPanel.add(roleBtn);
        buttonPanel.add(homeBtn);
        buttonPanel.add(tradeBtn);
        buttonPanel.add(friendsBtn);
        buttonPanel.add(rechargeBtn);
        buttonPanel.add(logoutBtn);
        buttonPanel.add(new JPanel()); // 占位
        buttonPanel.add(exitBtn);

        // 设置按钮事件
        setupButtonEvents();

        // 添加到主面板
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(iconPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    /**
     * 创建样式化的按钮
     */
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 50));
        button.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());

        // 鼠标悬停效果
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        return button;
    }

    /**
     * 设置按钮事件处理
     */
    private void setupButtonEvents() {
        accountBtn.addActionListener(e -> showAccountManagement());
        roleBtn.addActionListener(e -> showRoleManagement());
        homeBtn.addActionListener(e -> showHomeConstruction());
        tradeBtn.addActionListener(e -> showVirtualTrading());
        friendsBtn.addActionListener(e -> showCommunityFriends());
        rechargeBtn.addActionListener(e -> showRechargeSystem());
        logoutBtn.addActionListener(e -> logout());
        exitBtn.addActionListener(e -> exitGame());
    }

    /**
     * 显示主菜单
     */
    private void showMainMenu() {
        if (currentPlayer == null) {
            welcomeLabel.setText("欢迎来到皮卡堂过家家游戏");
            roleBtn.setEnabled(false);
            homeBtn.setEnabled(false);
            tradeBtn.setEnabled(false);
            friendsBtn.setEnabled(false);
            rechargeBtn.setEnabled(false);
            logoutBtn.setEnabled(false);
        } else {
            welcomeLabel.setText("欢迎回来，" + currentPlayer.getUsername() + "！");
            roleBtn.setEnabled(true);
            homeBtn.setEnabled(false); // 开发中
            tradeBtn.setEnabled(false); // 开发中
            friendsBtn.setEnabled(false); // 开发中
            rechargeBtn.setEnabled(false); // 开发中
            logoutBtn.setEnabled(true);
        }
        repaint();
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
        JDialog dialog = new JDialog(this, "登录账号", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("用户名:"), gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("密码:"), gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel();
        JButton loginBtn = new JButton("登录");
        JButton registerBtn = new JButton("注册");
        JButton cancelBtn = new JButton("取消");

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "用户名和密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Player player = accountManager.login(username, password);
            if (player != null) {
                currentPlayer = player;
                showMainMenu();
                JOptionPane.showMessageDialog(dialog, "登录成功！欢迎 " + username, "成功", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "用户名或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerBtn.addActionListener(e -> {
            dialog.dispose();
            showRegisterDialog();
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * 显示注册对话框
     */
    private void showRegisterDialog() {
        JDialog dialog = new JDialog(this, "注册账号", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPasswordField confirmPasswordField = new JPasswordField(15);
        JTextField emailField = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("用户名:"), gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("密码:"), gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("确认密码:"), gbc);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("邮箱:"), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        JPanel buttonPanel = new JPanel();
        JButton registerBtn = new JButton("注册");
        JButton cancelBtn = new JButton("取消");

        registerBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String email = emailField.getText().trim();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "所有字段都必须填写！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(dialog, "两次输入的密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = accountManager.registerAccount(username, password, email);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "注册成功！请登录您的账号。", "成功", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "注册失败！用户名可能已存在。", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(registerBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * 显示账号菜单对话框
     */
    private void showAccountMenuDialog() {
        String[] options = {"修改密码", "修改邮箱", "注销账号", "取消"};
        int choice = JOptionPane.showOptionDialog(this,
            "选择账号操作",
            "账号管理",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

        switch (choice) {
            case 0:
                showChangePasswordDialog();
                break;
            case 1:
                showChangeEmailDialog();
                break;
            case 2:
                showDeleteAccountDialog();
                break;
        }
    }

    /**
     * 显示修改密码对话框
     */
    private void showChangePasswordDialog() {
        JDialog dialog = new JDialog(this, "修改密码", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(350, 200);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPasswordField oldPasswordField = new JPasswordField(15);
        JPasswordField newPasswordField = new JPasswordField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("旧密码:"), gbc);
        gbc.gridx = 1;
        formPanel.add(oldPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("新密码:"), gbc);
        gbc.gridx = 1;
        formPanel.add(newPasswordField, gbc);

        JPanel buttonPanel = new JPanel();
        JButton confirmBtn = new JButton("确认");
        JButton cancelBtn = new JButton("取消");

        confirmBtn.addActionListener(e -> {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());

            if (oldPassword.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = accountManager.changePassword(currentPlayer, oldPassword, newPassword);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "密码修改成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "密码修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(confirmBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * 显示修改邮箱对话框
     */
    private void showChangeEmailDialog() {
        String newEmail = JOptionPane.showInputDialog(this,
            "输入新的邮箱地址",
            "修改邮箱",
            JOptionPane.QUESTION_MESSAGE);

        if (newEmail != null && !newEmail.trim().isEmpty()) {
            boolean success = accountManager.changeEmail(currentPlayer, newEmail.trim());
            if (success) {
                JOptionPane.showMessageDialog(this, "邮箱修改成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "邮箱修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * 显示注销账号对话框
     */
    private void showDeleteAccountDialog() {
        int result = JOptionPane.showConfirmDialog(this,
            "确定要注销账号吗？\n此操作不可撤销，所有数据将被删除！",
            "注销账号",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            boolean success = accountManager.deleteAccount(currentPlayer);
            if (success) {
                currentPlayer = null;
                showMainMenu();
                JOptionPane.showMessageDialog(this, "账号已注销！", "成功", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "账号注销失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * 显示角色管理界面
     */
    private void showRoleManagement() {
        if (currentPlayer == null) {
            JOptionPane.showMessageDialog(this, "请先登录账号！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        entity.Role role = roleManager.getRoleByPlayerId(currentPlayer.getPlayerId());

        if (role == null) {
            showCreateRoleDialog();
        } else {
            showRoleMenuDialog(role);
        }
    }

    /**
     * 显示创建角色对话框
     */
    private void showCreateRoleDialog() {
        JDialog dialog = new JDialog(this, "创建角色", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(350, 200);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(15);
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"男", "女", "其他"});

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("角色名:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("性别:"), gbc);
        gbc.gridx = 1;
        formPanel.add(genderCombo, gbc);

        // 添加提示标签
        JLabel hintLabel = new JLabel("<html><center>提示：创建角色后，可以通过<br/>'装饰角色'功能设置外观</center></html>", JLabel.CENTER);
        hintLabel.setForeground(Color.GRAY);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(hintLabel, gbc);

        JPanel buttonPanel = new JPanel();
        JButton createBtn = new JButton("创建");
        JButton cancelBtn = new JButton("取消");

        createBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String gender = (String) genderCombo.getSelectedItem();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "角色名不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 创建角色时自动设置默认外观
            String defaultAppearance = "默认外观";
            boolean success = roleManager.createRole(currentPlayer, name, gender, defaultAppearance);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "角色创建成功！\n\n现在您可以通过'装饰角色'功能为角色设置外观。", "成功", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "角色创建失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(createBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * 显示角色菜单对话框
     */
    private void showRoleMenuDialog(entity.Role role) {
        String roleInfo = String.format(
            "角色名: %s\n性别: %s\n等级: %d\n经验: %d\n外观: %s\n技能数量: %d",
            role.getName(), role.getGender(), role.getLevel(),
            role.getExperience(), role.getAppearance(), role.getSkills().size()
        );

        String[] options = {"查看技能", "学习技能", "提升经验", "编辑角色", "装饰角色", "关闭"};
        int choice = JOptionPane.showOptionDialog(this,
            roleInfo,
            "角色信息 - " + role.getName(),
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);

        switch (choice) {
            case 0:
                showSkillsDialog(role);
                break;
            case 1:
                showLearnSkillDialog(role);
                break;
            case 2:
                showGainExperienceDialog(role);
                break;
            case 3:
                showEditRoleDialog(role);
                break;
            case 4:
                showDressUpDialog(role);
                break;
        }
    }

    /**
     * 显示技能对话框
     */
    private void showSkillsDialog(entity.Role role) {
        StringBuilder skillsText = new StringBuilder();
        if (role.getSkills().isEmpty()) {
            skillsText.append("暂无技能");
        } else {
            for (entity.Skill skill : role.getSkills()) {
                skillsText.append(skill.getName())
                          .append(" (等级: ")
                          .append(skill.getLevel())
                          .append(")\n");
            }
        }

        JOptionPane.showMessageDialog(this, skillsText.toString(),
            role.getName() + " 的技能", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 显示学习技能对话框
     */
    private void showLearnSkillDialog(entity.Role role) {
        String[] skills = {"挖矿", "钓鱼", "种植", "烹饪"};
        String selectedSkill = (String) JOptionPane.showInputDialog(this,
            "选择要学习的技能",
            "学习技能",
            JOptionPane.QUESTION_MESSAGE,
            null,
            skills,
            skills[0]);

        if (selectedSkill != null) {
            String skillType = getSkillType(selectedSkill);
            String description = getSkillDescription(selectedSkill);
            entity.Skill newSkill = new entity.Skill(selectedSkill, skillType, description);

            boolean success = roleManager.learnSkill(role, newSkill);
            if (success) {
                JOptionPane.showMessageDialog(this, "技能学习成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "技能学习失败！可能已拥有此技能。", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * 显示提升经验对话框
     */
    private void showGainExperienceDialog(entity.Role role) {
        String expStr = JOptionPane.showInputDialog(this,
            "输入要提升的经验值",
            "提升经验",
            JOptionPane.QUESTION_MESSAGE);

        if (expStr != null) {
            try {
                int exp = Integer.parseInt(expStr);
                if (exp <= 0) {
                    JOptionPane.showMessageDialog(this, "经验值必须大于0！", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = roleManager.gainExperience(role, exp);
                if (success) {
                    JOptionPane.showMessageDialog(this,
                        "经验提升成功！当前等级: " + role.getLevel(),
                        "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "经验提升失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * 显示编辑角色对话框
     */
    private void showEditRoleDialog(entity.Role role) {
        JDialog dialog = new JDialog(this, "编辑角色", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 280);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(role.getName(), 15);
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"男", "女", "其他"});
        genderCombo.setSelectedItem(role.getGender());

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("角色名:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("性别:"), gbc);
        gbc.gridx = 1;
        formPanel.add(genderCombo, gbc);

        // 显示当前外观状态（只读）
        JLabel appearanceLabel = new JLabel(parseAppearanceForDisplay(role.getAppearance()));
        appearanceLabel.setBorder(BorderFactory.createTitledBorder("当前外观"));
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(appearanceLabel, gbc);

        // 添加提示
        JLabel hintLabel = new JLabel("<html><center>💡 提示：要修改外观，请使用<br/>'装饰角色'功能</center></html>", JLabel.CENTER);
        hintLabel.setForeground(Color.BLUE);
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(hintLabel, gbc);

        JPanel buttonPanel = new JPanel();
        JButton updateBtn = new JButton("更新");
        JButton cancelBtn = new JButton("取消");

        updateBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String gender = (String) genderCombo.getSelectedItem();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "角色名不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 保持原有的外观设置
            boolean success = roleManager.updateRole(role, name, gender, role.getAppearance());
            if (success) {
                JOptionPane.showMessageDialog(dialog, "角色信息修改成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "角色信息修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(updateBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * 显示家园建设界面
     */
    private void showHomeConstruction() {
        JOptionPane.showMessageDialog(this, "家园建设功能正在开发中，敬请期待！", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 显示虚拟交易界面
     */
    private void showVirtualTrading() {
        JOptionPane.showMessageDialog(this, "虚拟交易功能正在开发中，敬请期待！", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 显示社区交友界面
     */
    private void showCommunityFriends() {
        JOptionPane.showMessageDialog(this, "社区交友功能正在开发中，敬请期待！", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 显示充值系统界面
     */
    private void showRechargeSystem() {
        JOptionPane.showMessageDialog(this, "充值系统功能正在开发中，敬请期待！", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 注销登录
     */
    private void logout() {
        currentPlayer = null;
        showMainMenu();
        JOptionPane.showMessageDialog(this, "已注销登录", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 退出游戏
     */
    private void exitGame() {
        int result = JOptionPane.showConfirmDialog(this,
            "确定要退出游戏吗？",
            "退出游戏",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            DatabaseManager.closeConnection();
            System.exit(0);
        }
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
     * 显示角色装饰对话框
     */
    private void showDressUpDialog(entity.Role role) {
        JDialog dialog = new JDialog(this, "装饰角色 - " + role.getName(), true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        // 创建角色显示面板
        CharacterDisplayPanel characterPanel = new CharacterDisplayPanel();

        // 创建服装选择面板
        ClothingSelectorPanel clothingPanel = new ClothingSelectorPanel(characterPanel);

        // 加载当前角色的外观配置（如果有的话）
        ClothingSelectorPanel.ClothingConfig config = parseAppearanceConfig(role.getAppearance());
        if (config != null) {
            clothingPanel.setConfig(config);
        }

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        JButton saveBtn = new JButton("保存装饰");
        JButton cancelBtn = new JButton("取消");

        saveBtn.addActionListener(e -> {
            // 保存外观配置到角色
            ClothingSelectorPanel.ClothingConfig currentConfig = clothingPanel.getCurrentConfig();
            role.setAppearance(currentConfig.toString());

            // 保存到数据库
            boolean success = roleManager.updateRole(role, role.getName(), role.getGender(), role.getAppearance());
            if (success) {
                JOptionPane.showMessageDialog(dialog, "角色装饰保存成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        // 添加到对话框
        dialog.add(characterPanel, BorderLayout.WEST);
        dialog.add(clothingPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * 解析外观配置字符串
     */
    private ClothingSelectorPanel.ClothingConfig parseAppearanceConfig(String appearance) {
        if (appearance == null || appearance.trim().isEmpty() || appearance.equals("默认外观")) {
            return new ClothingSelectorPanel.ClothingConfig();
        }

        try {
            ClothingSelectorPanel.ClothingConfig config = new ClothingSelectorPanel.ClothingConfig();

            // 解析格式: "肤色:默认,上衣:无,下衣:无,连衣裙:无,鞋子:无,帽子:无,眼镜:无,项链:无"
            String[] parts = appearance.split(",");
            for (String part : parts) {
                String[] keyValue = part.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    switch (key) {
                        case "肤色":
                            config.skinColor = value;
                            break;
                        case "上衣":
                            config.top = value;
                            break;
                        case "下衣":
                            config.bottom = value;
                            break;
                        case "连衣裙":
                            config.dress = value;
                            break;
                        case "鞋子":
                            config.shoes = value;
                            break;
                        case "帽子":
                            config.hat = value;
                            break;
                        case "眼镜":
                            config.glasses = value;
                            break;
                        case "项链":
                            config.necklace = value;
                            break;
                    }
                }
            }

            return config;
        } catch (Exception e) {
            // 解析失败，返回默认配置
            return new ClothingSelectorPanel.ClothingConfig();
        }
    }

    /**
     * 解析外观配置为可显示的文本
     */
    private String parseAppearanceForDisplay(String appearance) {
        if (appearance == null || appearance.trim().isEmpty() || "默认外观".equals(appearance)) {
            return "默认外观（未装饰）";
        }

        try {
            StringBuilder display = new StringBuilder();
            String[] parts = appearance.split(",");
            for (String part : parts) {
                String[] keyValue = part.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    switch (key) {
                        case "肤色":
                            display.append("肤色:").append(value).append(" ");
                            break;
                        case "上衣":
                            if (!"无".equals(value)) display.append("上衣:").append(value).append(" ");
                            break;
                        case "下衣":
                            if (!"无".equals(value)) display.append("下衣:").append(value).append(" ");
                            break;
                        case "连衣裙":
                            if (!"无".equals(value)) display.append("连衣裙:").append(value).append(" ");
                            break;
                        case "鞋子":
                            if (!"无".equals(value)) display.append("鞋子:").append(value).append(" ");
                            break;
                        case "帽子":
                            if (!"无".equals(value)) display.append("帽子:").append(value).append(" ");
                            break;
                        case "眼镜":
                            if (!"无".equals(value)) display.append("眼镜:").append(value).append(" ");
                            break;
                        case "项链":
                            if (!"无".equals(value)) display.append("项链:").append(value).append(" ");
                            break;
                    }
                }
            }

            if (display.length() == 0) {
                return "默认外观（未装饰）";
            }

            return display.toString().trim();
        } catch (Exception e) {
            return "自定义外观";
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
        // 在终端环境中运行Swing应用
        System.setProperty("java.awt.headless", "false");

        SwingUtilities.invokeLater(() -> {
            new PikachuGameSwing().setVisible(true);
        });
    }
}