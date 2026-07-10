package ESP32.Emulator.emulator;

public class EmulatorRunner {
    private final EmulatorLifecycle emulator;


    public EmulatorRunner(EmulatorLifecycle emulator) {
        this.emulator = emulator;
    }


    public void start() {
        emulator.setup();

        while (true) {
            emulator.loop();
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
