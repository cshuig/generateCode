package com.cshuig.utils;

import com.cshuig.model.Database;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cshuig on 15/4/26.
 */
public class VelocityUtils {
    private static final Logger LOGGER = Logger.getLogger(VelocityUtils.class);

    public static void generateCode(Database database) {
        final VelocityContext context = new VelocityContext();
        final VelocityEngine velocityEngine = new VelocityEngine();
        final String tpath = System.getProperty("user.dir") + "/template";
        velocityEngine.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, tpath);
        velocityEngine.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        velocityEngine.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        velocityEngine.init();

        // 暂时这么写死
        final List<String> templateList = new ArrayList<>();
        templateList.add("entity.vm");
        templateList.add("domain.vm");
        templateList.add("request.vm");
        templateList.add("response.vm");
        templateList.add("listResponse.vm");
        templateList.add("controller.vm");
        templateList.add("service.vm");
        templateList.add("APIService.vm");
        templateList.add("repository.vm");

        database.getTableList()
                .stream()
                .filter(t -> database.getInputInfo().getSelectTableList().contains(t.getTableName()))
                .forEach(table -> {
                    context.put("table", table);
                    context.put("packageName", database.getInputInfo().getPackageName());
                    context.put("author", database.getInputInfo().getAuthor());
                    context.put("date", new Date());

                    templateList.stream().forEach(templateName -> {
                        String templatePrefixName = templateName.substring(0, templateName.indexOf("."));
                        Template template = velocityEngine.getTemplate(templateName);
                        StringWriter stringWriter = new StringWriter();
                        template.merge(context, stringWriter);
                        StringBuilder sb = new StringBuilder(100);
                        sb.append(database.getInputInfo().getOurDir()).append(File.separator).append("src/");
                        sb.append(database.getInputInfo().getPackageName().replace(".", "/")).append(File.separator);
                        if (templatePrefixName.toLowerCase().indexOf("response") > 0) {
                            sb.append("response");
                        } else {
                            sb.append(templateName.substring(0, templateName.indexOf(".")));
                        }
                        File pathTemp = new File(sb.toString());
                        if (!pathTemp.exists()) pathTemp.mkdirs();
                        if (templatePrefixName.equals("domain")) {
                            sb.append(File.separator).append(table.getClassName() + ".java");
                        } else {
                            sb.append(File.separator).append(table.getClassName() + StringUtils.upperFirestChar(templatePrefixName) + ".java");
                        }
                        writeFile(sb.toString(), stringWriter.toString());
                    });
                });
    }

    public static void writeFile(String filePathAndName, String fileContent) {
        try {
            File f = new File(filePathAndName);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(fileContent);
            writer.close();
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            e.printStackTrace();
        }
    }
}
