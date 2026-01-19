package gui.web;

import boundary.AccountUI;
import control.AccountManager;
import control.RoleManager;
import entity.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import persistence.DatabaseManager;

import javax.annotation.PostConstruct;

/**
 * 皮卡堂过家家游戏 - Web版本控制器
 * 使用Spring Boot + Thymeleaf提供网页界面
 */
@Controller
@SessionAttributes("currentPlayer")
public class PikachuWebController {

    private AccountManager accountManager;
    private RoleManager roleManager;

    @PostConstruct
    public void init() {
        // 初始化数据库
        DatabaseManager.initializeDatabase();
        this.accountManager = new AccountManager();
        this.roleManager = new RoleManager();
    }

    /**
     * 首页
     */
    @GetMapping("/")
    public String index(Model model, @SessionAttribute(value = "currentPlayer", required = false) Player currentPlayer) {
        model.addAttribute("currentPlayer", currentPlayer);
        return "index";
    }

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /**
     * 处理登录
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       Model model) {
        Player player = accountManager.login(username, password);
        if (player != null) {
            model.addAttribute("currentPlayer", player);
            return "redirect:/";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    /**
     * 注册页面
     */
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    /**
     * 处理注册
     */
    @PostMapping("/register")
    public String register(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email,
                          Model model) {
        boolean success = accountManager.registerAccount(username, password, email);
        if (success) {
            model.addAttribute("success", "注册成功！请登录。");
            return "login";
        } else {
            model.addAttribute("error", "注册失败！用户名可能已存在。");
            return "register";
        }
    }

    /**
     * 账号管理页面
     */
    @GetMapping("/account")
    public String account(@SessionAttribute("currentPlayer") Player player, Model model) {
        model.addAttribute("player", player);
        return "account";
    }

    /**
     * 修改密码
     */
    @PostMapping("/account/change-password")
    public String changePassword(@SessionAttribute("currentPlayer") Player player,
                                @RequestParam String oldPassword,
                                @RequestParam String newPassword,
                                Model model) {
        boolean success = accountManager.changePassword(player, oldPassword, newPassword);
        if (success) {
            model.addAttribute("success", "密码修改成功！");
        } else {
            model.addAttribute("error", "密码修改失败！");
        }
        return "account";
    }

    /**
     * 修改邮箱
     */
    @PostMapping("/account/change-email")
    public String changeEmail(@SessionAttribute("currentPlayer") Player player,
                             @RequestParam String email,
                             Model model) {
        boolean success = accountManager.changeEmail(player, email);
        if (success) {
            model.addAttribute("success", "邮箱修改成功！");
        } else {
            model.addAttribute("error", "邮箱修改失败！");
        }
        return "account";
    }

    /**
     * 角色管理页面
     */
    @GetMapping("/role")
    public String role(@SessionAttribute("currentPlayer") Player player, Model model) {
        entity.Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
        model.addAttribute("role", role);
        model.addAttribute("player", player);
        return "role";
    }

    /**
     * 创建角色
     */
    @PostMapping("/role/create")
    public String createRole(@SessionAttribute("currentPlayer") Player player,
                            @RequestParam String name,
                            @RequestParam String gender,
                            @RequestParam String appearance,
                            Model model) {
        boolean success = roleManager.createRole(player, name, gender, appearance);
        if (success) {
            model.addAttribute("success", "角色创建成功！");
        } else {
            model.addAttribute("error", "角色创建失败！");
        }
        return "redirect:/role";
    }

    /**
     * 编辑角色
     */
    @PostMapping("/role/edit")
    public String editRole(@SessionAttribute("currentPlayer") Player player,
                          @RequestParam String name,
                          @RequestParam String gender,
                          @RequestParam String appearance,
                          Model model) {
        entity.Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
        if (role != null) {
            boolean success = roleManager.updateRole(role, name, gender, appearance);
            if (success) {
                model.addAttribute("success", "角色修改成功！");
            } else {
                model.addAttribute("error", "角色修改失败！");
            }
        }
        return "redirect:/role";
    }

    /**
     * 学习技能
     */
    @PostMapping("/role/learn-skill")
    public String learnSkill(@SessionAttribute("currentPlayer") Player player,
                            @RequestParam String skillName,
                            Model model) {
        entity.Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
        if (role != null) {
            String skillType = getSkillType(skillName);
            String description = getSkillDescription(skillName);
            entity.Skill skill = new entity.Skill(skillName, skillType, description);

            boolean success = roleManager.learnSkill(role, skill);
            if (success) {
                model.addAttribute("success", "技能学习成功！");
            } else {
                model.addAttribute("error", "技能学习失败！可能已拥有此技能。");
            }
        }
        return "redirect:/role";
    }

    /**
     * 提升经验
     */
    @PostMapping("/role/gain-exp")
    public String gainExperience(@SessionAttribute("currentPlayer") Player player,
                                @RequestParam int experience,
                                Model model) {
        entity.Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
        if (role != null) {
            boolean success = roleManager.gainExperience(role, experience);
            if (success) {
                model.addAttribute("success", "经验提升成功！当前等级: " + role.getLevel());
            } else {
                model.addAttribute("error", "经验提升失败！");
            }
        }
        return "redirect:/role";
    }

    /**
     * 注销登录
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    /**
     * 家园建设页面（占位符）
     */
    @GetMapping("/home")
    public String home() {
        return "coming_soon";
    }

    /**
     * 虚拟交易页面（占位符）
     */
    @GetMapping("/trade")
    public String trade() {
        return "coming_soon";
    }

    /**
     * 社区交友页面（占位符）
     */
    @GetMapping("/friends")
    public String friends() {
        return "coming_soon";
    }

    /**
     * 充值系统页面（占位符）
     */
    @GetMapping("/recharge")
    public String recharge() {
        return "coming_soon";
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
}