package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class FileLogger {
    static private String rootPath = "./log/";
    static private String filePath = "";
    static private String defaultCharSet = "EUC-KR";

    static public void SetRootPath(String rootPath) {
        FileLogger.rootPath = rootPath;
    }

    static public void MakeLogFile(String charSet, String section) {
        FileLogger.defaultCharSet = charSet;
        File file;
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS");
        String createTime = df.format(d);

        try
        {
            if (Objects.equals(section, "")) {
                section = "NONE";
            }
            FileLogger.filePath = FileLogger.rootPath + createTime + "_" + section + ".log";
            file = new File(FileLogger.filePath);
            int i = 0;

            while (file.exists())
            {
                FileLogger.filePath = FileLogger.rootPath + createTime + "_" + section + "(" + Integer.toString(i++) + ")" + ".log";
                file = new File(FileLogger.filePath);
            }

            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(FileLogger.filePath);
            FileChannel outChannel	 = outputStream.getChannel();
            outChannel.close();
            outputStream.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Room:" + "###########Error Log File Open############");
        }
    }

    static public synchronized void writeDump(byte[] bytes)
    {
        try
        {
            Date d = new Date();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            String createTime = df.format(d);

            FileOutputStream 	outputStream = new FileOutputStream(FileLogger.filePath, true);
            OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(outputStream, defaultCharSet);
            BufferedWriter bufferedWriter = new BufferedWriter(OutputStreamWriter);

            bufferedWriter.write(createTime + ">>" + TypeHelper.byteArrayToHexString(bytes) + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
            OutputStreamWriter.close();
            outputStream.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    static public synchronized void writeString(String text)
    {
        try
        {
            Date d = new Date();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            String createTime = df.format(d);

            FileOutputStream 	outputStream = new FileOutputStream(FileLogger.filePath, true);
            OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(outputStream, defaultCharSet);
            BufferedWriter bufferedWriter = new BufferedWriter(OutputStreamWriter);

            bufferedWriter.write(createTime + ">>" + text + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
            OutputStreamWriter.close();
            outputStream.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
