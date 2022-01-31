package com.sparta.pd.pressplaywebsite1;

import java.util.ArrayList;

public class UserBasket {
    private static ArrayList<UserFilm> filmsInBasket = new ArrayList<>();


    public void addFilmToBasket(UserFilm film){
        filmsInBasket.add(film);
    }

    public  void clearBasket(){
        filmsInBasket = new ArrayList<>();
    }

    public ArrayList<UserFilm> getFilmsInBasket() {
        return filmsInBasket;
    }
}
