public class Main{
    public static void main(String[] args) {

        Thread serverThread = new Thread(() -> {
            System.out.println("Starting Server...");
            Server.main(new String[]{});
        });

        Thread clientThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Starting Client...");
                Client.main(new String[]{});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        serverThread.start();
        clientThread.start();
    }
}