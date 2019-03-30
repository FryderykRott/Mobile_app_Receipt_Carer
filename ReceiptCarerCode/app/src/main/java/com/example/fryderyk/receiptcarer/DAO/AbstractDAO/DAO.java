package com.example.fryderyk.receiptcarer.DAO.AbstractDAO;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Abstrakcyjna klasa służaca do przechowywania k
 */
abstract class DAO {
    /**
     * Klasa ta pozwala na wykonywanie zapytań do bazy danych.
     * Dziedziczy po AsyncTast<String,String,Strint> co pozwala na działanie w tle.
     * Głównym zadaniem tej klasy jest możliwość korzystania z niej przez obiekty dziedziczące po DAO.
     */
    protected static class DoQuerry extends AsyncTask<String,String,String>
    {
        /**
         * Pole query służy do  przechowyania zapytania napisanego w SQL.
         * Jest wykorzystywawana do egzekwowania zapytań zapisanych w zawartości tej zmiennej w bazie danych.
         */
        String query;

        /**
         * Pole to służy do przechwyania informacji o wyniku przebiegu zapytania. Czy się powiodło czy też nie
         */
        String z = "";


        /**
         * Pole klasy Connection służy do przechowywania nawiązania między bazą danych, a aplikacją.
         * Jest ona wykorzystywana do wykonywania zapytań.
         */
        Connection con;

        /**
         * @param query przechowuje zapytanie do bazy danych
         * @param con nawiązane połączenie z bazą danych
         */
        public DoQuerry(String query, Connection con){
            this.query = query;
            this.con = con;
        }

        @Override
        protected void onPreExecute() {
            //Stuff on the before query process
        }

        /**
         * @param params Jest to wewnętrzna struktura
         * @return zwraca informacje zapisaną w String'u, określającą powodzenie wykonania zapytania do bazy danych
         */
        @Override
        protected String doInBackground(String... params) {
            try {
                if (con == null) {
                    z = "Please check your internet connection";
                } else {
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);

                    z = "Querry successfull";

                    stmt.clearBatch();
                    stmt.clearWarnings();
                    stmt.close();

                    con.clearWarnings();
                    con.close();
                }
            }
            catch (Exception ex)
            {

                z = "Exceptions"+ex;
            }

            return z;
        }

        @Override
        protected void onPostExecute(String s) {
//            very difficult stuff
        }
    }
}
