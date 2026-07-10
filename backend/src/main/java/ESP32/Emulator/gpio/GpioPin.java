package ESP32.Emulator.gpio;

public class GpioPin {
    private final int number;

    private PinMode mode;
    private PinState state;

    public GpioPin(int number) {
        this.number = number;
        this.mode = PinMode.INPUT;
        this.state = PinState.LOW;
    }

    public int getNumber() {
        return number;
    }

    public PinMode getMode() {
        return mode;
    }

    public void setMode(PinMode mode) {
        this.mode = mode;
    }

    public PinState getState() {
        return state;
    }

    public void setState(PinState state) {
        this.state = state;
    }
}
