package com.manusunny.pinlock;

public interface PinLock {

    int SUCCESS = 0;
    int CANCELLED = 1;
    int INVALID = 3;
    int FORGOT = 4;

    String TEXT_FIRST_TRY = "Enter PIN";
    String TEXT_PIN_INVALID = "Invalid PIN. Try again";

    String TEXT_FIRST_TRY_NEW = "Enter new PIN";
    String TEXT_CONFIRM_PIN = "Re enter PIN";
    String TEXT_PIN_MISMATCH = "PIN mismatch. Try again";


    void onCompleted(String pin);

    void onPinValueChange(int length);

    void onForgotPin();
}
