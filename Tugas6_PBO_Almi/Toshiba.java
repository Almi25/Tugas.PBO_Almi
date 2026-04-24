package com.alat.elektronik;

/**
 * Class Toshiba
 * Implementasi dari interface Laptop untuk brand Toshiba
 */
public class Toshiba implements Laptop {
    private boolean isOn;
    private int volume;
    private String model;

    public Toshiba(String model) {
        this.model = model;
        this.isOn = false;
        this.volume = 50;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Toshiba " + model + " is now ON");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Toshiba " + model + " is now OFF");
    }

    @Override
    public void increaseVolume() {
        if (isOn) {
            if (volume < 100) {
                volume += 10;
                System.out.println("Volume increased to: " + volume);
            } else {
                System.out.println("Volume is already at maximum!");
            }
        } else {
            System.out.println("Laptop is OFF. Turn it ON first!");
        }
    }

    @Override
    public void decreaseVolume() {
        if (isOn) {
            if (volume > 0) {
                volume -= 10;
                System.out.println("Volume decreased to: " + volume);
            } else {
                System.out.println("Volume is already at minimum!");
            }
        } else {
            System.out.println("Laptop is OFF. Turn it ON first!");
        }
    }

    @Override
    public void displayStatus() {
        System.out.println("=================================");
        System.out.println("Bra