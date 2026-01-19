package gui.desktop;

import entity.Accessory;
import entity.Clothing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色形象显示面板
 * 使用Swing绘制简单的模特和服装
 */
public class CharacterDisplayPanel extends JPanel {
    private Color skinColor = new Color(255, 220, 177); // 默认肤色
    private Clothing top;        // 上衣
    private Clothing bottom;     // 下衣
    private Clothing dress;      // 连衣裙
    private Clothing shoes;      // 鞋子
    private Accessory hat;       // 帽子
    private Accessory glasses;   // 眼镜
    private Accessory necklace;  // 项链

    // 模特身体各部分的颜色
    private Color topColor = Color.WHITE;
    private Color bottomColor = Color.BLUE;
    private Color dressColor = null; // 默认为空，不穿连衣裙
    private Color shoesColor = Color.BLACK;
    private Color hatColor = null;
    private Color glassesColor = null;
    private Color necklaceColor = null;

    public CharacterDisplayPanel() {
        setPreferredSize(new Dimension(300, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("角色形象"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 获取面板尺寸
        int width = getWidth();
        int height = getHeight();

        // 居中绘制模特
        int centerX = width / 2;
        int baseY = height / 4;

        // 绘制模特身体
        drawMannequin(g2d, centerX, baseY);
    }

    /**
     * 绘制模特
     */
    private void drawMannequin(Graphics2D g2d, int centerX, int baseY) {
        // 头部（椭圆）
        Ellipse2D head = new Ellipse2D.Double(centerX - 30, baseY, 60, 70);
        g2d.setColor(skinColor);
        g2d.fill(head);
        g2d.setColor(Color.BLACK);
        g2d.draw(head);

        // 头发（简单的椭圆）
        Ellipse2D hair = new Ellipse2D.Double(centerX - 35, baseY - 10, 70, 40);
        g2d.setColor(Color.BLACK);
        g2d.fill(hair);

        // 眼睛
        g2d.setColor(Color.WHITE);
        Ellipse2D leftEye = new Ellipse2D.Double(centerX - 20, baseY + 20, 8, 6);
        Ellipse2D rightEye = new Ellipse2D.Double(centerX + 12, baseY + 20, 8, 6);
        g2d.fill(leftEye);
        g2d.fill(rightEye);

        g2d.setColor(Color.BLACK);
        Ellipse2D leftPupil = new Ellipse2D.Double(centerX - 18, baseY + 21, 4, 4);
        Ellipse2D rightPupil = new Ellipse2D.Double(centerX + 14, baseY + 21, 4, 4);
        g2d.fill(leftPupil);
        g2d.fill(rightPupil);

        // 嘴巴（微笑）
        g2d.drawArc(centerX - 10, baseY + 30, 20, 10, 0, -180);

        // 脖子
        Rectangle2D neck = new Rectangle2D.Double(centerX - 5, baseY + 65, 10, 15);
        g2d.setColor(skinColor);
        g2d.fill(neck);
        g2d.setColor(Color.BLACK);
        g2d.draw(neck);

        // 身体躯干
        Rectangle2D torso = new Rectangle2D.Double(centerX - 25, baseY + 80, 50, 80);
        g2d.setColor(Color.PINK);
        g2d.fill(torso);
        g2d.setColor(Color.BLACK);
        g2d.draw(torso);

        // 手臂
        Rectangle2D leftArm = new Rectangle2D.Double(centerX - 45, baseY + 85, 20, 60);
        Rectangle2D rightArm = new Rectangle2D.Double(centerX + 25, baseY + 85, 20, 60);
        g2d.setColor(skinColor);
        g2d.fill(leftArm);
        g2d.fill(rightArm);
        g2d.setColor(Color.BLACK);
        g2d.draw(leftArm);
        g2d.draw(rightArm);

        // 绘制服装
        drawClothing(g2d, centerX, baseY);

        // 绘制饰品
        drawAccessories(g2d, centerX, baseY);
    }

    /**
     * 绘制服装
     */
    private void drawClothing(Graphics2D g2d, int centerX, int baseY) {
        // 优先绘制连衣裙（如果有的话）
        if (dressColor != null) {
            // 连衣裙覆盖上衣和下衣区域
            Rectangle2D dress = new Rectangle2D.Double(centerX - 30, baseY + 75, 60, 90);
            g2d.setColor(dressColor);
            g2d.fill(dress);
            g2d.setColor(Color.BLACK);
            g2d.draw(dress);

            // 连衣裙装饰
            g2d.setColor(Color.WHITE);
            for (int i = 0; i < 3; i++) {
                Ellipse2D button = new Ellipse2D.Double(centerX - 2, baseY + 85 + i * 20, 4, 4);
                g2d.fill(button);
            }
        } else {
            // 绘制上衣
            if (topColor != null) {
                Rectangle2D top = new Rectangle2D.Double(centerX - 28, baseY + 78, 56, 45);
                g2d.setColor(topColor);
                g2d.fill(top);
                g2d.setColor(Color.BLACK);
                g2d.draw(top);
            }

            // 绘制下衣（裤子或裙子）
            if (bottomColor != null) {
                Rectangle2D bottom = new Rectangle2D.Double(centerX - 28, baseY + 125, 56, 45);
                g2d.setColor(bottomColor);
                g2d.fill(bottom);
                g2d.setColor(Color.BLACK);
                g2d.draw(bottom);
            }
        }

        // 绘制鞋子
        if (shoesColor != null) {
            Rectangle2D leftShoe = new Rectangle2D.Double(centerX - 35, baseY + 210, 15, 20);
            Rectangle2D rightShoe = new Rectangle2D.Double(centerX + 20, baseY + 210, 15, 20);
            g2d.setColor(shoesColor);
            g2d.fill(leftShoe);
            g2d.fill(rightShoe);
            g2d.setColor(Color.BLACK);
            g2d.draw(leftShoe);
            g2d.draw(rightShoe);
        }
    }

    /**
     * 绘制饰品
     */
    private void drawAccessories(Graphics2D g2d, int centerX, int baseY) {
        // 帽子
        if (hatColor != null) {
            Rectangle2D hat = new Rectangle2D.Double(centerX - 35, baseY - 15, 70, 20);
            g2d.setColor(hatColor);
            g2d.fill(hat);
            g2d.setColor(Color.BLACK);
            g2d.draw(hat);

            // 帽檐
            Rectangle2D brim = new Rectangle2D.Double(centerX - 45, baseY + 5, 90, 8);
            g2d.setColor(hatColor);
            g2d.fill(brim);
            g2d.setColor(Color.BLACK);
            g2d.draw(brim);
        }

        // 眼镜
        if (glassesColor != null) {
            g2d.setColor(glassesColor);
            g2d.setStroke(new BasicStroke(3));
            // 左眼镜框
            g2d.drawOval(centerX - 25, baseY + 18, 12, 10);
            // 右眼镜框
            g2d.drawOval(centerX + 13, baseY + 18, 12, 10);
            // 眼镜桥
            g2d.drawLine(centerX - 13, baseY + 23, centerX + 13, baseY + 23);
            // 眼镜腿
            g2d.drawLine(centerX - 25, baseY + 23, centerX - 35, baseY + 25);
            g2d.drawLine(centerX + 25, baseY + 23, centerX + 35, baseY + 25);
        }

        // 项链
        if (necklaceColor != null) {
            g2d.setColor(necklaceColor);
            g2d.setStroke(new BasicStroke(2));
            // 项链弧线
            g2d.drawArc(centerX - 20, baseY + 65, 40, 15, 0, -180);

            // 吊坠
            Ellipse2D pendant = new Ellipse2D.Double(centerX - 3, baseY + 75, 6, 8);
            g2d.setColor(necklaceColor);
            g2d.fill(pendant);
            g2d.setColor(Color.YELLOW);
            g2d.fill(new Ellipse2D.Double(centerX - 1, baseY + 77, 2, 2));
        }
    }

    // Getter和Setter方法
    public Color getSkinColor() { return skinColor; }
    public void setSkinColor(Color skinColor) {
        this.skinColor = skinColor;
        repaint();
    }

    public void setTop(Clothing top) {
        this.top = top;
        this.topColor = top != null ? getClothingColor(top.getColor()) : null;
        repaint();
    }

    public void setBottom(Clothing bottom) {
        this.bottom = bottom;
        this.bottomColor = bottom != null ? getClothingColor(bottom.getColor()) : null;
        repaint();
    }

    public void setDress(Clothing dress) {
        this.dress = dress;
        this.dressColor = dress != null ? getClothingColor(dress.getColor()) : null;
        // 穿连衣裙时清除上衣和下衣
        if (dress != null) {
            this.topColor = null;
            this.bottomColor = null;
        }
        repaint();
    }

    public void setShoes(Clothing shoes) {
        this.shoes = shoes;
        this.shoesColor = shoes != null ? getClothingColor(shoes.getColor()) : null;
        repaint();
    }

    public void setHat(Accessory hat) {
        this.hat = hat;
        this.hatColor = hat != null ? getAccessoryColor(hat.getColor()) : null;
        repaint();
    }

    public void setGlasses(Accessory glasses) {
        this.glasses = glasses;
        this.glassesColor = glasses != null ? getAccessoryColor(glasses.getColor()) : null;
        repaint();
    }

    public void setNecklace(Accessory necklace) {
        this.necklace = necklace;
        this.necklaceColor = necklace != null ? getAccessoryColor(necklace.getColor()) : null;
        repaint();
    }

    /**
     * 根据服装颜色字符串获取Color对象
     */
    private Color getClothingColor(String colorName) {
        if (colorName == null) return Color.GRAY;
        switch (colorName.toLowerCase()) {
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            case "green": return Color.GREEN;
            case "yellow": return Color.YELLOW;
            case "pink": return Color.PINK;
            case "purple": return Color.MAGENTA;
            case "orange": return Color.ORANGE;
            case "white": return Color.WHITE;
            case "black": return Color.BLACK;
            case "gray": return Color.GRAY;
            default: return Color.LIGHT_GRAY;
        }
    }

    /**
     * 根据饰品颜色字符串获取Color对象
     */
    private Color getAccessoryColor(String colorName) {
        return getClothingColor(colorName);
    }

    /**
     * 清除所有服装
     */
    public void clearAllClothing() {
        this.topColor = null;
        this.bottomColor = null;
        this.dressColor = null;
        this.shoesColor = null;
        this.hatColor = null;
        this.glassesColor = null;
        this.necklaceColor = null;
        repaint();
    }
}