public class MacBook implements Laptop {
    boolean isOn = false;
    int volume = 50;

    public void turnOn() {
        isOn = true;
        System.out.println("MacBook ON");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("MacBook OFF");
    }

    public void volumeUp() {
        if (isOn) {
            volume += 10;
            System.out.println("Volume: " + volume);
        } else {
            System.out.println("Nyalakan dulu!");
        }
    }

    public void volumeDown() {
        if (isOn) {
            volume -= 10;
            System.out.println("Volume: " + volume);
        } else {
            System.out.println("Nyalakan dulu!");
        }
    }
}