import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.*;


public class Hyundai {

    static String[] lines;
    Date date = new Date();

    static String note = " ";
    String logFileName = "Files/log_hyundai.txt";
    String elementHover = "/html/body/div[2]/footer/div";


    public String hyundai() throws Exception {
        System.setProperty(Parameters.webDriver, Parameters.pathFileWebDriverFF);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);
        WebDriverWait wait = new WebDriverWait(driver, Parameters.timeOutWaitSec);
        Actions action = new Actions(driver);

        try{
            driver.get(Parameters.link);
            Thread.sleep(5000);
            driver.findElement(By.cssSelector(Parameters.xPathLoginButton_first)).click();
            Thread.sleep(5000);
            driver.findElement(By.id(Parameters.xPathEmailField)).clear(); // ввод логина
            driver.findElement(By.id(Parameters.xPathEmailField)).sendKeys(Parameters.login); // ввод логина
            driver.findElement(By.id(Parameters.xPathPasswordField)).clear(); // ввод пароля
            driver.findElement(By.id(Parameters.xPathPasswordField)).sendKeys(Parameters.password); //ввод пароля
            Thread.sleep(3000);
            driver.findElement(By.cssSelector(Parameters.xPathLoginButton_second)).click();
            Thread.sleep(3000);
            driver.findElement(By.cssSelector(Parameters.xPathClosePopap)).click();
            Thread.sleep(3000);

            note = Parameters.resOk;
        } // запуск записи лога
        catch (Exception e0) { // исключение, если не получилось try
            Parameters.resHyundai = "BAD";
            driver.quit(); // закрываем драйвер

            return note = Parameters.res; // присвоение нового занчения в переменню для записи в лог
        }

        try {

            Thread.sleep(3000);
            driver.findElement(By.cssSelector(Parameters.xSelectGaraz)).click(); // клик на кнопку Гараж
            Thread.sleep(3000);
            driver.findElement(By.cssSelector(Parameters.xPathButtonVseZapisi)).click();
            driver.findElement(By.className("repair_tr")).click();
            Thread.sleep(3000);
            driver.quit();
            return Parameters.resHyundai = "BAD";


        } catch (Exception e) {
            try {
                Thread.sleep(5000);
                driver.findElement(By.cssSelector(Parameters.xSelectGaraz)).click(); // клик на кнопку Гараж
                Thread.sleep(3000);
                driver.findElement(By.cssSelector(Parameters.xPathButtonVseZapisi)).click();
                driver.findElement(By.className("repair_gr")).click();
                Thread.sleep(3000);
                driver.quit();
                return Parameters.resHyundai = "BAD";

                } catch (Exception e2) {
                    driver.quit();
                    return Parameters.resHyundai = "OK";
            }
        }
    }

    public void logHyundai() throws IOException, MessagingException { // пишем лог в файл
        FileReader fr = new FileReader(logFileName);
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine();
        String result = date.toString() + " Starting is successful " + Parameters.resHyundai +" " + note + " " + Parameters.mail;
        while (str != null) {
            String lineSeparator = System.getProperty("line.separator");
            result += lineSeparator + str;
            str = br.readLine();
        }
        FileWriter fw = new FileWriter(logFileName);
        fw.write(result);
        fw.close();
        fr.close();
        br.close();
    }

    public void searchStatusHyundai() throws IOException {

        FileInputStream fis = new FileInputStream(new File(logFileName)); // путь заменить на нужный
        byte[] content = new byte[fis.available()];
        fis.read(content);
        fis.close();
        lines = new String(content, "UTF-8").split("\n"); // кодировку указать нужную
        if (lines[0].indexOf(" BAD ") > -1) {Parameters.resHyundai = "BAD";}
        if (lines[0].indexOf(" OK ") > -1) {Parameters.resHyundai = "OK";}
    }

    public void logHyundaiBad() throws IOException, MessagingException { // пишем лог, когда все попытки неудачные
        FileReader fr = new FileReader(logFileName);
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine();
        String result = date.toString() + " Найдено Гарантийное обслуживание " + note;
        while (str != null) {
            String lineSeparator = System.getProperty("line.separator");
            result += lineSeparator + str;
            str = br.readLine();
        }
        FileWriter fw = new FileWriter(logFileName);
        fw.write(result);
        fw.close();
        fr.close();
        br.close();
    }

    Properties props = new Properties();
    public void mailHyundai() throws IOException, MessagingException { // отправка уведомления о проблеме на почту

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session s = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Parameters.sendMail, Parameters.sendMailPassword);

                }
            });
            try {
                Message mess = new MimeMessage(s);
                mess.setFrom(new InternetAddress(Parameters.sendMail));
                String[] emails = Parameters.sendToMail;
                InternetAddress dests[] = new InternetAddress[emails.length];
                for (int i = 0; i < emails.length; i++) {
                    dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
                }
                mess.setRecipients(Message.RecipientType.TO, dests);
                mess.setSubject("Найдено гарантийное обслуживание ");
                mess.setText("Найдено гарантийное обслуживание  " + note + date.toString());
                Transport.send(mess);
                Parameters.mail = "YES";
            } catch (Exception ex) {
                System.out.println("что то не то, найдено гарантийное обслуживание");


            }
        }

    public void mailHyundaiOk() throws IOException, MessagingException { // отправка уведомления на почту если работа восстановлена

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session s = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Parameters.sendMail, Parameters.sendMailPassword);
            }
        });
        try {
            Message mess = new MimeMessage(s);
            mess.setFrom(new InternetAddress(Parameters.sendMail));
            String[] emails = Parameters.sendToMail;
            InternetAddress dests[] = new InternetAddress[emails.length];
            for (int i = 0; i < emails.length; i++) {
                dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
            }
            mess.setRecipients(Message.RecipientType.TO, dests);
            mess.setSubject("Не найдено гарантийное обслуживание ");
            mess.setText("Не найдено гарантийное обслуживание  " + date.toString());
            Transport.send(mess);

        } catch (Exception ex) {
            System.out.println("что то не то, не найдено гарантийное обслуживание");
        }
    }

    /*
    public void writeDB() throws IOException { //пишем данные в БД MySQL

        Connection connection;
        PreparedStatement preparedStatement;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(Parameters.URL, Parameters.Username, Parameters.Password);
            preparedStatement = connection.prepareStatement(Parameters.INSERT_NEW);
            preparedStatement.setLong(1, 0);
            preparedStatement.setString(2,"Hyundai");
            preparedStatement.setString(3, Parameters.resHyundai);
            preparedStatement.setString(4, note);
            preparedStatement.setString(5, date.toString());
            preparedStatement.setLong(6, date.getTime()); //преобразование даты и времени в TimeStamp
            preparedStatement.executeUpdate();

        }catch (Exception ex){
            System.out.println("что то не то, не записалось в лог бд");
        }
    }*/
}