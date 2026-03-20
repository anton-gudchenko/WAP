package ru.labs.netapps.spring.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.labs.netapps.spring.model.Variant;
import ru.labs.netapps.spring.util.VariantUtil;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@RequestParam(name = "code", required = false) String code,
                        HttpSession session,
                        Model model) {

        Variant v = VariantUtil.fromCode(code);

        session.setAttribute("code", v.code());
        session.setAttribute("last4", v.last4());
        session.setAttribute("last2", v.last2());
        session.setAttribute("sum", v.sum());

        model.addAttribute("variant", v);
        return "index";
    }
}
