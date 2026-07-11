package ESP32.Emulator.emulator;

public class EmulatorRunner {
    private final Esp32Emulator emulator;


    public EmulatorRunner(Esp32Emulator emulator) {
        this.emulator = emulator;
    }


    public void start() {
        emulator.setup();

        while (true) {
            emulator.loop();
            System.out.println(emulator.getCurrentState());
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                Thread.currentThread()
                        .interrupt();
                break;
            }
        }
    }
}
