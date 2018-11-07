public class Start {

    static int a = 1; // для бесконечного цикла

    public static void main(String[] args) throws Exception {

        while (a == 1) {


            hyundai();

//            SignIn.selectSQL();

            Thread.sleep(30000);
        }
    }

    public static void hyundai() throws Exception {
        Hyundai gameHyundai = new Hyundai();
        gameHyundai.searchStatusHyundai();

        if (Parameters.resHyundai == "OK") {
            gameHyundai.hyundai();

            if (Parameters.resHyundai == "BAD") {
                gameHyundai.logHyundai();
                gameHyundai.mailHyundai();
                //gameHyundai.writeDB();
            }
        }

        else if (Parameters.resHyundai == "BAD") {
            for (int i = 0; i < 1; i++) {
                if (Parameters.resHyundai == "BAD") {
                    gameHyundai.hyundai();
                    gameHyundai.mailHyundai();
                }

                else if (Parameters.resHyundai == "OK") {
                    i = 10;
                    Hyundai.note = Parameters.resOk;
                    Parameters.resHyundai = "OK";
                    gameHyundai.mailHyundaiOk();
                    gameHyundai.logHyundai();
                    //gameHyundai.writeDB();
                }
            }if (Parameters.mail == "YES") {gameHyundai.logHyundai();}
        }
    }
}