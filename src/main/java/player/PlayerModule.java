package player;

import com.sun.jna.Native;

public class PlayerModule {

    static Boolean g_bAPIReturn = false;
    static int g_code = 0;
    static String g_sn = "MZTA21430N2060007186"; //"MZTA21430N2060007186"; //BZRA69243W1A40011605

    static void waitAPIReturn() throws InterruptedException
    {
        while (!g_bAPIReturn)
        {
            Thread.sleep(1000);
        }
        g_bAPIReturn = false;
    }

    public static void testApi() throws InterruptedException
    {
        System.setProperty("jna.encoding", "UTF-8");
        ViplexCore instance = (ViplexCore) Native.load("/home/ubuntu/PlayerInstance/lib/libviplexcore.so",ViplexCore.class);
        //当需要集成到项目中时，请修改上面加载库的位置为你下载的sdk库的绝对路径，windows下如：
        //ViplexCore instance = (ViplexCore) Native.loadLibrary("D:\\ViplexCore3.3.0.01_x64\\bin\\viplexcore.dll",ViplexCore.class);

        //当需要集成到项目中时，请修改此处加载库的位置为你下载的sdk库的绝对路径，linux下如：
        //ViplexCore instance = (ViplexCore) Native.loadLibrary("home/user/desktop/ViplexCore_3.3.0.01_CentOS/bin/libviplexcore.so",ViplexCore.class);
        ViplexCore.CallBack callBack = new ViplexCore.CallBack() {

            @Override
            public void dataCallBack(int code, String data) {
                // TODO Auto-generated method stub
                g_code = code;
                String strCode = "\nViplexCore Demo code:" + code;
                String strData = "\nViplexCore Demo data:" + data;
                System.out.println(strCode);
                System.out.println(strData);
                g_bAPIReturn=true;
            }

        };

        ViplexCore.CallBack md5CallBack = new ViplexCore.CallBack() {

            @Override
            public void dataCallBack(int code, String data) {
                // TODO Auto-generated method stub
                g_code = code;
                String strCode = "\nViplexCore Demo code:" + code;
                String strData = "\nViplexCore Demo data:" + data;
                System.out.println(strCode);
                System.out.println(strData);
                g_bAPIReturn=true;
            }

        };

        String rootDir = System.getProperty("user.dir") + "/temp";
        rootDir = rootDir.replaceAll( "\\\\","/");
        String createProgram = "{\"name\":\"Demo\",\"width\":500,\"height\":500,\"tplID\":1,\"winInfo\":{\"height\":100,\"width\":100,\"left\":0,\"top\":0,\"zindex\":0,\"index\":0}}";
        String editProgram = "{\"programID\":1,\"pageID\":1,\"pageInfo\":{\"name\":\"节目\",\"widgetContainers\":[{\"audioGroup\":\"\",\"backgroundColor\":\"#00000000\",\"backgroundDrawable\":\"\",\"contents\":{\"widgetGroups\":[],\"widgets\":[{\"id\":1,\"enable\":true,\"repeatCount\":1,\"layout\":{\"y\":\"0\",\"height\":\"100%\",\"x\":\"0\",\"width\":\"100%\"},\"backgroundColor\":\"#00000000\",\"backgroundDrawable\":\"\",\"backgroundMusic\":\"\",\"zOrder\":0,\"displayRatio\":\"FULL\",\"outAnimation\":{\"type\":0,\"duration\":0},\"dataSource\":\"381de8adc5ac749a903cf81e69b4bdd3.mp4\",\"type\":\"VIDEO\",\"constraints\":[{\"cron\":[],\"endTime\":\"4017-12-30T23:59:59Z+8:00\",\"startTime\":\"1970-01-01T00:00:00Z+8:00\"}],\"border\":{\"borderThickness\":\"2px,3px,5%,6\",\"style\":0,\"backgroundColor\":\"#ff000000\",\"name\":\"border\",\"cornerRadius\":\"2%\",\"effects\":{\"headTailSpacing\":\"\",\"isHeadTail\":false,\"speedByPixelEnable\":true,\"speed\":0,\"animation\":\"CLOCK_WISE\"}},\"inAnimation\":{\"type\":0,\"duration\":1000},\"duration\":3605000,\"name\":\"test2.mp4\",\"originalDataSource\":\"./test2.mp4\",\"functionStorage\":\"\",\"isSupportSpecialEffects\":false}]},\"enable\":true,\"id\":1,\"itemsSource\":\"\",\"layout\":{\"height\":\"1.0\",\"width\":\"1.0\",\"x\":\"0.0\",\"y\":\"0.0\"},\"name\":\"widgetContainers1\",\"pickCount\":0,\"pickPolicy\":\"ORDER\",\"zOrder\":0}]}}";
        String genrateProgram = String.format(
                "{\"programID\":1,\"outPutPath\":\"%s/\",\"mediasPath\":[{\"oldPath\":\"test\",\"newPath\":\"test\"}]}",
                rootDir);
        String trasfromProgram = String.format(
                "{\"sn\": \"%s\",\"iconPath\": \"\",\"iconName\": \"\",\"sendProgramFilePaths\": {\"programPath\": \"%s/program1\",\"mediasPath\": {\"./test2.mp4\": \"test2.mp4\"}},\"programName\": \"program1\",\"deviceIdentifier\": \"Demo\",\"startPlayAfterTransferred\": true,\"insertPlay\": true}",
                g_sn,
                rootDir);


        Boolean bTestVideo = false;
        if (bTestVideo)
        {
            editProgram = "{\"programID\":2,\"pageID\":2,\"pageInfo\":{\"name\":\"Demo\",\"widgetContainers\":[{\"contents\":{\"widgets\":[{\"constraints\":[{\"cron\":[\"0 0 0 ? * 1,2,3,4,5,6,7\"],\"endTime\":\"4017-12-30T23:59:59Z 8:00\",\"startTime\":\"1970-01-01T00:00:00Z 8:00\"}],\"duration\":5000,\"dataSource\":\"760f43400d9b8e5c5812a2f6c0328243.avi\",\"type\":\"VIDEO\",\"name\":\"test2.mp4\",\"originalDataSource\":\"./test2.mp4\"}]},\"id\":1,\"name\":\"widgetContainers1\"}]}}";

            trasfromProgram = String.format(
                    "{\"sn\":\"%s\",\"iconPath\": \"\",\"iconName\": \"\",\"sendProgramFilePaths\": {\"programPath\": \"%s/program1\",\"mediasPath\": {\"./test2.mp4\": \"test2.mp4\"}},\"programName\": \"program1\",\"deviceIdentifier\": \"Demo\",\"startPlayAfterTransferred\": true,\"insertPlay\": true}",
                    g_sn,
                    rootDir);
        }







        Boolean testText = false;
        if (testText) {
            editProgram = "{\"programID\":1,\"pageID\":1,\"pageInfo\":{\"name\":\"jiemu\",\"widgetContainers\":[{\"contents\":{\"widgets\":[{\"constraints\":[{\"cron\":[\"0 0 0 ? * 1,2,3,4,5,6,7\"],\"endTime\":\"4017-12-30T23:59:59Z+8:00\",\"startTime\":\"1970-01-01T00:00:00Z+8:00\"}],\"duration\":5000,\"metadata\":{\"content\":{\"autoPaging\":true,\"backgroundMusic\":{\"duration\":0,\"isTextSync\":false},\"displayStyle\":{\"scrollAttributes\":{\"effects\":{\"animation\":\"MARQUEE_LEFT\",\"speed\":3}},\"type\":\"SCROLL\"},\"paragraphs\":[{\"backgroundColor\":\"#00000000\",\"horizontalAlignment\":\"CENTER\",\"letterSpacing\":0,\"lineSpacing\":0,\"lines\":[{\"segs\":[{\"content\":\"简体繁體日本語한국어.English123\"}]}],\"verticalAlignment\":\"CENTER\"}],\"textAttributes\":[{\"backgroundColor\":\"#ff000000\",\"attributes\":{\"font\":{\"family\":[\"Arial\"],\"isUnderline\":false,\"size\":20,\"style\":\"NORMAL\"},\"letterSpacing\":0,\"textColor\":\"#ffff0000\"}}]}},\"name\":\"text\",\"type\":\"ARCH_TEXT\"}]},\"id\":1,\"name\":\"widgetContainers1\"}]}}";
            trasfromProgram = String.format(
                    "{\"sn\": \"%s\",\"iconPath\": \"\",\"iconName\": \"\",\"sendProgramFilePaths\": {\"programPath\": \"%s/program1\",\"mediasPath\": {}},\"programName\": \"program1\",\"deviceIdentifier\": \"Demo\",\"startPlayAfterTransferred\": true,\"insertPlay\": true}",
                    g_sn,
                    rootDir);
        }

        String companyInfo = "{\"company\":\"NovaStar\",\"phone\":\"029-68216000\",\"email\":\"hr@novastar.tech\"}";
        instance.nvSetDevLang("Java");
        System.out.println("nvInit(sdk 初始化):");
        System.out.println(instance.nvInit(rootDir,companyInfo));
        System.out.println("ViplexCore Demo nvSearchTerminalAsync(搜索) begin... ");
        instance.nvSearchTerminalAsync(callBack);
        waitAPIReturn();
        g_bAPIReturn = false;

        //System.out.println("ViplexCore Demo nvSearchAppointIpAsync(指定IP搜索) begin... ");
        //String requestData = "{\"ip\":\"169.254.235.47\"}";
        //instance.nvSearchAppointIpAsync(requestData, callBack);
        //waitAPIReturn();
        //Thread.sleep(2000);

        System.out.println("ViplexCore Demo nvLoginAsync(登录) begin... ");
        String loginParam = String.format("{\"sn\":\"%s\",\"username\":\"admin\",\"rememberPwd\":1,\"password\":\"123456\",\"loginType\":0}"
                , g_sn);
        instance.nvLoginAsync(loginParam, callBack);
        waitAPIReturn();
        if (g_code != 0) {
            System.out.println("ViplexCore Demo nvLoginAsync(登录) 失败！");
            return;
        }

        System.out.println("ViplexCore Demo nvCreateProgramAsync(创建节目) begin... ");
        instance.nvCreateProgramAsync(createProgram, callBack);
        waitAPIReturn();


        String requestDatapath = "{\"filePath\":\"./test2.mp4\"}";
        System.out.println("ViplexCore Demo nvGetFileMD5Async(获取MD5) begin... ");
        instance.nvGetFileMD5Async(requestDatapath, callBack);
        waitAPIReturn();

        System.out.println("ViplexCore Demo nvSetPageProgramAsync(编辑节目) begin... ");
        instance.nvSetPageProgramAsync(editProgram, callBack);
        waitAPIReturn();

        System.out.println("ViplexCore Demo nvMakeProgramAsync(生成节目) begin... ");
        instance.nvMakeProgramAsync(genrateProgram, callBack);
        waitAPIReturn();

        System.out.println("ViplexCore Demo nvStartTransferProgramAsync(发送节目) begin... ");
        instance.nvStartTransferProgramAsync(trasfromProgram, callBack);
        Thread.sleep(10000);
        g_bAPIReturn = false;

        System.out.println("ViplexCore Demo nvGetProgramInfoAsync(获取节目信息) begin... ");
        String requestDatasn = String.format("{\"sn\":\"%s\"}", g_sn);
        instance.nvGetProgramInfoAsync(requestDatasn, callBack);
        waitAPIReturn();

        System.out.println("ViplexCore Demo nvSetVolumeAsync(设置音量) begin... ");
        String setVolumeParam = String.format("{\"sn\":\"%s\",\"volumeInfo\":{\"ratio\":60.0}}", g_sn);
        instance.nvSetVolumeAsync(setVolumeParam, callBack);
        waitAPIReturn();

        System.out.println("ViplexCore Demo nvGetVolumeAsync(获取音量) begin... ");
        String getVolumeParam = String.format("{\"sn\":\"%s\"}", g_sn);
        instance.nvGetVolumeAsync(getVolumeParam, callBack);
        waitAPIReturn();
    }

    public static void main(String[] args) throws InterruptedException{
        testApi();
    }

}
