/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.thendo.controller;

import com.itextpdf.text.DocumentException;
import converter.thendo.Model.UserModel;
import converter.thendo.converterService.ConverterService;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thendo
 */
@Controller
public class mainControler {

    private static List<UserModel> userList = new ArrayList<UserModel>();

    @Autowired
    ConverterService converterService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "redirect:/users";
    }

    static {
        userList.add(new UserModel("Thendo", "Mudau", "thendo@gmail.com"));
        userList.add(new UserModel("Venda", "Limpopo", "venda@megafreight.co.za"));
        userList.add(new UserModel("Theo", "Mudau", "t@gmail.com"));
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String init(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("userList", userList);
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCar(@ModelAttribute("user") UserModel user) {
        if (null != user && null != user.getFirstName() && null != user.getLastName() && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty()) {
            userList.add(user);
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String DownloadPage(@ModelAttribute("model") ModelMap model) throws IOException, TemplateException, DocumentException {
        converterService.createPdf();
        model.addAttribute("userList", userList); 
        converterService.convertToPdf(new ModelMap("userlist",userList));
        
        return "test";
    }
}
