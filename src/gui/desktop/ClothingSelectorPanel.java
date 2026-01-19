package gui.desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 服装选择面板
 * 提供服装、饰品和肤色的选择界面
 */
public class ClothingSelectorPanel extends JPanel {
    private CharacterDisplayPanel characterPanel;
    private JComboBox<String> skinColorCombo;
    private JComboBox<String> topCombo;
    private JComboBox<String> bottomCombo;
    private JComboBox<String> dressCombo;
    private JComboBox<String> shoesCombo;
    private JComboBox<String> hatCombo;
    private JComboBox<String> glassesCombo;
    private JComboBox<String> necklaceCombo;
    private JButton clearAllBtn;

    // 可选的颜色列表
    private static final String[] COLORS = {
        "无", "白色", "黑色", "红色", "蓝色", "绿色", "黄色", "粉色", "紫色", "橙色", "灰色"
    };

    // 服装选项
    private static final String[] TOPS = {
        "无", "T恤", "衬衫", "毛衣", "夹克", "西装上衣", "卫衣", "连帽衫"
    };

    private static final String[] BOTTOMS = {
        "无", "牛仔裤", "休闲裤", "运动裤", "短裤", "裙子", "短裙"
    };

    private static final String[] DRESSES = {
        "无", "连衣裙", "晚礼服", "小礼服", "长裙", "短裙"
    };

    private static final String[] SHOES = {
        "无", "运动鞋", "皮鞋", "凉鞋", "靴子", "高跟鞋", "拖鞋"
    };

    private static final String[] HATS = {
        "无", "棒球帽", "贝雷帽", "礼帽", "鸭舌帽", "渔夫帽", "牛仔帽"
    };

    private static final String[] GLASSES = {
        "无", "墨镜", "圆框眼镜", "方框眼镜", "细框眼镜", "蛤蟆镜"
    };

    private static final String[] NECKLACES = {
        "无", "项链", "吊坠", "珠链", "锁链", "十字架"
    };

    public ClothingSelectorPanel(CharacterDisplayPanel characterPanel) {
        this.characterPanel = characterPanel;
        initializeComponents();
        setupLayout();
        setupListeners();
    }

    /**
     * 初始化组件
     */
    private void initializeComponents() {
        // 肤色选择
        skinColorCombo = new JComboBox<>(new String[]{"默认", "白色", "浅黄", "小麦色", "古铜色", "深色"});

        // 服装选择
        topCombo = new JComboBox<>(TOPS);
        bottomCombo = new JComboBox<>(BOTTOMS);
        dressCombo = new JComboBox<>(DRESSES);
        shoesCombo = new JComboBox<>(SHOES);

        // 饰品选择
        hatCombo = new JComboBox<>(HATS);
        glassesCombo = new JComboBox<>(GLASSES);
        necklaceCombo = new JComboBox<>(NECKLACES);

        // 清除按钮
        clearAllBtn = new JButton("一键脱光");
        clearAllBtn.setBackground(Color.ORANGE);
        clearAllBtn.setForeground(Color.WHITE);
    }

    /**
     * 设置布局
     */
    private void setupLayout() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("服装选择"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // 第一列
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("肤色:"), gbc);
        gbc.gridy = 1;
        add(new JLabel("上衣:"), gbc);
        gbc.gridy = 2;
        add(new JLabel("下衣:"), gbc);
        gbc.gridy = 3;
        add(new JLabel("连衣裙:"), gbc);
        gbc.gridy = 4;
        add(new JLabel("鞋子:"), gbc);

        // 第二列 - 服装选择
        gbc.gridx = 1; gbc.gridy = 0;
        add(skinColorCombo, gbc);
        gbc.gridy = 1;
        add(topCombo, gbc);
        gbc.gridy = 2;
        add(bottomCombo, gbc);
        gbc.gridy = 3;
        add(dressCombo, gbc);
        gbc.gridy = 4;
        add(shoesCombo, gbc);

        // 第三列
        gbc.gridx = 2; gbc.gridy = 0;
        add(new JLabel("帽子:"), gbc);
        gbc.gridy = 1;
        add(new JLabel("眼镜:"), gbc);
        gbc.gridy = 2;
        add(new JLabel("项链:"), gbc);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(clearAllBtn, gbc);

        // 第四列 - 饰品选择
        gbc.gridwidth = 1;
        gbc.gridx = 3; gbc.gridy = 0;
        add(hatCombo, gbc);
        gbc.gridy = 1;
        add(glassesCombo, gbc);
        gbc.gridy = 2;
        add(necklaceCombo, gbc);
    }

    /**
     * 设置事件监听器
     */
    private void setupListeners() {
        // 肤色选择
        skinColorCombo.addActionListener(e -> updateSkinColor());

        // 服装选择
        topCombo.addActionListener(e -> updateTop());
        bottomCombo.addActionListener(e -> updateBottom());
        dressCombo.addActionListener(e -> updateDress());
        shoesCombo.addActionListener(e -> updateShoes());

        // 饰品选择
        hatCombo.addActionListener(e -> updateHat());
        glassesCombo.addActionListener(e -> updateGlasses());
        necklaceCombo.addActionListener(e -> updateNecklace());

        // 清除按钮
        clearAllBtn.addActionListener(e -> clearAllClothing());
    }

    /**
     * 更新肤色
     */
    private void updateSkinColor() {
        String selectedColor = (String) skinColorCombo.getSelectedItem();
        Color skinColor;

        switch (selectedColor) {
            case "白色":
                skinColor = new Color(255, 235, 235);
                break;
            case "浅黄":
                skinColor = new Color(255, 240, 200);
                break;
            case "小麦色":
                skinColor = new Color(255, 220, 177);
                break;
            case "古铜色":
                skinColor = new Color(210, 180, 140);
                break;
            case "深色":
                skinColor = new Color(139, 101, 69);
                break;
            default:
                skinColor = new Color(255, 220, 177);
        }

        characterPanel.setSkinColor(skinColor);
    }

    /**
     * 更新上衣
     */
    private void updateTop() {
        String selectedTop = (String) topCombo.getSelectedItem();
        if ("无".equals(selectedTop)) {
            characterPanel.setTop(null);
        } else {
            // 创建虚拟服装对象
            characterPanel.setTop(createVirtualClothing("上衣", selectedTop, getRandomColor()));
        }

        // 如果选择了连衣裙，则清除上衣选择
        if (dressCombo.getSelectedIndex() > 0) {
            topCombo.setSelectedIndex(0);
        }
    }

    /**
     * 更新下衣
     */
    private void updateBottom() {
        String selectedBottom = (String) bottomCombo.getSelectedItem();
        if ("无".equals(selectedBottom)) {
            characterPanel.setBottom(null);
        } else {
            characterPanel.setBottom(createVirtualClothing("下衣", selectedBottom, getRandomColor()));
        }

        // 如果选择了连衣裙，则清除下衣选择
        if (dressCombo.getSelectedIndex() > 0) {
            bottomCombo.setSelectedIndex(0);
        }
    }

    /**
     * 更新连衣裙
     */
    private void updateDress() {
        String selectedDress = (String) dressCombo.getSelectedItem();
        if ("无".equals(selectedDress)) {
            characterPanel.setDress(null);
            // 恢复上衣和下衣的选择能力
            topCombo.setEnabled(true);
            bottomCombo.setEnabled(true);
        } else {
            characterPanel.setDress(createVirtualClothing("连衣裙", selectedDress, getRandomColor()));
            // 穿连衣裙时禁用上衣和下衣选择
            topCombo.setSelectedIndex(0);
            bottomCombo.setSelectedIndex(0);
            topCombo.setEnabled(false);
            bottomCombo.setEnabled(false);
        }
    }

    /**
     * 更新鞋子
     */
    private void updateShoes() {
        String selectedShoes = (String) shoesCombo.getSelectedItem();
        if ("无".equals(selectedShoes)) {
            characterPanel.setShoes(null);
        } else {
            characterPanel.setShoes(createVirtualClothing("鞋子", selectedShoes, getRandomColor()));
        }
    }

    /**
     * 更新帽子
     */
    private void updateHat() {
        String selectedHat = (String) hatCombo.getSelectedItem();
        if ("无".equals(selectedHat)) {
            characterPanel.setHat(null);
        } else {
            characterPanel.setHat(createVirtualAccessory("帽子", selectedHat, getRandomColor()));
        }
    }

    /**
     * 更新眼镜
     */
    private void updateGlasses() {
        String selectedGlasses = (String) glassesCombo.getSelectedItem();
        if ("无".equals(selectedGlasses)) {
            characterPanel.setGlasses(null);
        } else {
            characterPanel.setGlasses(createVirtualAccessory("眼镜", selectedGlasses, getRandomColor()));
        }
    }

    /**
     * 更新项链
     */
    private void updateNecklace() {
        String selectedNecklace = (String) necklaceCombo.getSelectedItem();
        if ("无".equals(selectedNecklace)) {
            characterPanel.setNecklace(null);
        } else {
            characterPanel.setNecklace(createVirtualAccessory("项链", selectedNecklace, getRandomColor()));
        }
    }

    /**
     * 清除所有服装
     */
    private void clearAllClothing() {
        // 重置所有选择
        skinColorCombo.setSelectedIndex(0);
        topCombo.setSelectedIndex(0);
        bottomCombo.setSelectedIndex(0);
        dressCombo.setSelectedIndex(0);
        shoesCombo.setSelectedIndex(0);
        hatCombo.setSelectedIndex(0);
        glassesCombo.setSelectedIndex(0);
        necklaceCombo.setSelectedIndex(0);

        // 重新启用控件
        topCombo.setEnabled(true);
        bottomCombo.setEnabled(true);

        // 清除模特服装
        characterPanel.clearAllClothing();
        characterPanel.setSkinColor(new Color(255, 220, 177));
    }

    /**
     * 创建虚拟服装对象
     */
    private entity.Clothing createVirtualClothing(String type, String name, String color) {
        entity.Clothing clothing = new entity.Clothing();
        clothing.setName(name);
        clothing.setType(type);
        clothing.setColor(color);
        return clothing;
    }

    /**
     * 创建虚拟饰品对象
     */
    private entity.Accessory createVirtualAccessory(String type, String name, String color) {
        entity.Accessory accessory = new entity.Accessory();
        accessory.setName(name);
        accessory.setType(type);
        accessory.setColor(color);
        return accessory;
    }

    /**
     * 获取随机颜色
     */
    private String getRandomColor() {
        String[] colors = {"红色", "蓝色", "绿色", "黄色", "粉色", "紫色", "橙色", "白色", "黑色"};
        return colors[(int)(Math.random() * colors.length)];
    }

    /**
     * 获取当前选择的服装配置
     */
    public ClothingConfig getCurrentConfig() {
        ClothingConfig config = new ClothingConfig();

        config.skinColor = (String) skinColorCombo.getSelectedItem();
        config.top = (String) topCombo.getSelectedItem();
        config.bottom = (String) bottomCombo.getSelectedItem();
        config.dress = (String) dressCombo.getSelectedItem();
        config.shoes = (String) shoesCombo.getSelectedItem();
        config.hat = (String) hatCombo.getSelectedItem();
        config.glasses = (String) glassesCombo.getSelectedItem();
        config.necklace = (String) necklaceCombo.getSelectedItem();

        return config;
    }

    /**
     * 设置服装配置
     */
    public void setConfig(ClothingConfig config) {
        if (config.skinColor != null) skinColorCombo.setSelectedItem(config.skinColor);
        if (config.top != null) topCombo.setSelectedItem(config.top);
        if (config.bottom != null) bottomCombo.setSelectedItem(config.bottom);
        if (config.dress != null) dressCombo.setSelectedItem(config.dress);
        if (config.shoes != null) shoesCombo.setSelectedItem(config.shoes);
        if (config.hat != null) hatCombo.setSelectedItem(config.hat);
        if (config.glasses != null) glassesCombo.setSelectedItem(config.glasses);
        if (config.necklace != null) necklaceCombo.setSelectedItem(config.necklace);
    }

    /**
     * 服装配置类
     */
    public static class ClothingConfig {
        public String skinColor = "默认";
        public String top = "无";
        public String bottom = "无";
        public String dress = "无";
        public String shoes = "无";
        public String hat = "无";
        public String glasses = "无";
        public String necklace = "无";

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("肤色:").append(skinColor).append(",");
            sb.append("上衣:").append(top).append(",");
            sb.append("下衣:").append(bottom).append(",");
            sb.append("连衣裙:").append(dress).append(",");
            sb.append("鞋子:").append(shoes).append(",");
            sb.append("帽子:").append(hat).append(",");
            sb.append("眼镜:").append(glasses).append(",");
            sb.append("项链:").append(necklace);
            return sb.toString();
        }
    }
}