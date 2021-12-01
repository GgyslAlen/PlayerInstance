import player.PlayerModule;

public class Main {

    public static void main(String[] args) {
        try {
            PlayerModule.testApi();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
