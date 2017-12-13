
package converter.thendo.converterService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 *
 * @author thendo
 */
public class HtmlUtils {
    
    	public static String generateHtmlFromTemplate(Map root, String htmlFilePath, String htmlFileName) throws IOException, TemplateException {
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(new File("/home/thendo/thendo/src/main/resources/templates/"));
		Template template = config.getTemplate("index.ftl");

		String htmlFullPath = htmlFilePath + htmlFileName + ".html";
		Writer file = new FileWriter (new File(htmlFullPath));
		template.process(root, file);
		file.flush();
		file.close();
         
                return htmlFullPath;
	}
}
