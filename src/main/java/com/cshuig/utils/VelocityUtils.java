package com.cshuig.utils;

import com.cshuig.model.Database;
import com.cshuig.model.Table;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * Created by cshuig on 15/4/26.
 */
public class VelocityUtils {
    private Logger logger = Logger.getLogger(VelocityUtils.class);

    public static void generateCode(Database database) {
        VelocityContext context = new VelocityContext();
        VelocityEngine velocityEngine = new VelocityEngine();
        String tpath = System.getProperty("user.dir") + "/template";
        System.out.println("tpath = " + tpath);
        velocityEngine.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, tpath);
        velocityEngine.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        velocityEngine.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        velocityEngine.init();

        List<String> list = new ArrayList<>();
        list.add("entity.vm");
        list.add("controller.vm");
        list.add("service.vm");
        list.add("repository.vm");

        for (Table table : database.getTableList()) {
            if (!database.getInputInfo().getSelectTableList().contains(table.getTableName())) continue;
            context.put("table", table);
            context.put("packageName", database.getInputInfo().getPackageName());
            context.put("author", database.getInputInfo().getAuthor());
            context.put("date", new Date());

            for (String tName : list) {
                Template template = velocityEngine.getTemplate(tName);
                StringWriter stringWriter = new StringWriter();
                template.merge(context, stringWriter);
                StringBuilder sb = new StringBuilder();
                sb.append(database.getInputInfo().getOurDir() + File.separator + "src/");
                sb.append(database.getInputInfo().getPackageName().replace(".", "/")).append(File.separator);
                sb.append(tName.substring(0, tName.indexOf(".")));
                File pathTemp = new File(sb.toString());
                if (!pathTemp.exists()) pathTemp.mkdirs();
                sb.append(File.separator).append(table.getClassName() + StringUtils.upperFirestChar(tName.substring(0, tName.indexOf("."))) + ".java");
                writeFile(sb.toString(), stringWriter.toString());
            }
        }

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
