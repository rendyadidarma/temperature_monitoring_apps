package com.example.temperaturemontoringapps;

public class Temperature {

    int CURRENT_TEMP, HIGHEST_TEMP, LOWEST_TEMP;

    public Temperature() {
    }

    public Temperature(int CURRENT_TEMP, int HIGHEST_TEMP, int LOWEST_TEMP) {
        this.CURRENT_TEMP = CURRENT_TEMP;
        this.HIGHEST_TEMP = HIGHEST_TEMP;
        this.LOWEST_TEMP = LOWEST_TEMP;
    }

    public int getCURRENT_TEMP() {
        return CURRENT_TEMP;
    }

    public void setCURRENT_TEMP(int CURRENT_TEMP) {
        this.CURRENT_TEMP = CURRENT_TEMP;
    }

    public int getHIGHEST_TEMP() {
        return HIGHEST_TEMP;
    }

    public void setHIGHEST_TEMP(int HIGHEST_TEMP) {
        this.HIGHEST_TEMP = HIGHEST_TEMP;
    }

    public int getLOWEST_TEMP() {
        return LOWEST_TEMP;
    }

    public void setLOWEST_TEMP(int LOWEST_TEMP) {
        this.LOWEST_TEMP = LOWEST_TEMP;
    }

}
