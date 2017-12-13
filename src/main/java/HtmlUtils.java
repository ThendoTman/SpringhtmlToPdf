import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *
 * @author thendo
 */
public class HtmlUtils {
    
    	public static String generateHtmlFromTemplate( Map root) throws IOException, TemplateException {
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(new File("resources/templates/"));
		Template template = config.getTemplate("index.ftl");

		String htmlFullPath = "/home/thendo/testMan_html.html";
		Writer file = new FileWriter (new File(htmlFullPath));
		template.process(root, file);
		file.flush();
		file.close();
         
                return htmlFullPath;
	}
}
