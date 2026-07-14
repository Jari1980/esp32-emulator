package ESP32.Emulator.emulator;

public class EmulatorRunner {
    private final Esp32Emulator emulator;


    public EmulatorRunner(Esp32Emulator emulator) {
        this.emulator = emulator;
    }


    public void start() {
        Thread thread = new Thread(() -> {
            emulator.setup();

            while (!Thread.currentThread().isInterrupted()) {
                emulator.loop();
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    Thread.currentThread()
                            .interrupt();
                }
            }
        });

        thread.setName("esp32-loop");
        thread.start();
    }
}
