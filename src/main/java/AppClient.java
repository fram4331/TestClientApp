import agent.SessionManage;
import config.Config;
import netty.AppClientBootstrap;
import util.FileLogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppClient {

    AppClientBootstrap appClientBootstrap;

    public AppClient() {
    }

    public void initiate() throws Exception {
        try {
            System.out.println("Server.Config.reload()  >>>>>>");
            Config.initialize();

            System.out.println("Server.FileLogger.SetRootPath  >>>>>>");
            FileLogger.SetRootPath(Config.getLogPath() + "/");

            appClientBootstrap = new AppClientBootstrap(Config.getHostIP(), Config.getHostPort());

            System.out.println("Server initiate Complete  >>>>>>");
        } catch (Exception e) {
            System.out.println("======================================================");
            System.out.println("===============AppClient initiate Error===============");
            System.out.println("======================================================");
        }
    }

    public void start()  {
        System.out.println("======================================================");
        System.out.println("===================AppClient initiate=================");
        System.out.println("======================================================");

        try {
            initiate();

            appClientBootstrap.start();
        } catch (Exception e) {
            System.out.println("======================================================");
            System.out.println("===============AppClient start Error==================");
            System.out.println("======================================================");
            System.exit(1); // 정상적으로 서버가 로딩되지 않은 경우 시스템을 죽여 재시작되도록 한다
        }
    }

}
