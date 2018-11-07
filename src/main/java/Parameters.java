/*
    Все пояснения к коду записаны в классе Fool
 */

public class Parameters {

    // Авторизация
    static String link = "";
    static String login = "";
    static String password = "";
    static String xPathLoginButton_first = "body > div.mainpager.loaded > div.content-wrapper > div.top-block-back > div.header-login_div > a.btn-primary.hover-wave.login-btn.aos-init.aos-animate";
    static String xPathLoginButton_second = "#loginBlock > form > div.box-btn > button";
    static String xPathEmailField = "email";
    static String xPathPasswordField = "password";

    static String xPathClosePopap = "body > div.pop-up > div.wrapper-pop-up > a > i";

    static String xSelectGaraz = "body > div.wrapper > div.block-right-nav > div.scroll-nav > ul > li:nth-child(2) > a";

    // Раздел Все ЗАПИСИ
    static String xPathButtonVseZapisi = "body > div.wrapper > div.main > div.container > div.inner-page > div:nth-child(2) > div > div.row-double-three > div > div.box-btn.clearfix.hide-mobile.garage-flex-block > div:nth-child(1) > div:nth-child(1) > a";

    static String xPathGarObsl = " ";

    //Кнопка Игры на главной
    static String xPathButtonGame = "/html/body/div[2]/div[1]/div[1]/ul/li[2]/a";

    //Driver
    static String webDriver = "webdriver.gecko.driver";
    static String googleDriver = "webdriver.chrome.driver";

    static String pathFileWebDriverFF = "Files/geckodriver_023_win32.exe";
    static String pathFileWebDriverGoogle = "Files/chromedriver.exe";

    static int timeOutWaitSec = 90;


    // Отправка писем
    static String sendMail = "";
    static String sendMailPassword = "";
    static String[] sendToMail = {""}; //
    static String mail = "NO";

    //Note
    static String res = "Failed";
    static String resOk = "Passed";

    //SQL
    static final String URL = "jdbc:mysql://адрес";
    static final String Username = "логин/пароль";
    static final String Password = "логин/пароль";
    static final String INSERT_NEW = "INSERT INTO dvlptest.BondStreet VALUES(?, ?, ? , ? , ?, ?);";
    static final String SELECT = "select * from dvlptest.BondStreet;";

    // Start data
    static String resChat;
    static String resFindBondStreet;
    static String resFool;
    static String resLotto;
    static String resHyundai;

}
