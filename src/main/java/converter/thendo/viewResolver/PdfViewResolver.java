/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.thendo.viewResolver;

import converter.thendo.view.PdfView;
import java.util.Locale;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 *
 * @author thendo
 */
public class PdfViewResolver implements ViewResolver{

    @Override
    public View resolveViewName(String string, Locale locale) throws Exception {
            PdfView view = new PdfView();
        return view;
    }
    
}
