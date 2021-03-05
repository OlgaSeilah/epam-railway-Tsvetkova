package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDBScript extends ConnectionToDB {
    private Statement statement = null;
    String sql = null;
    String url = "jdbc:postgresql://localhost:5432/";


    public void setupDBScript() {
        /** create connection **/
        try {
            conn = DriverManager.getConnection(url, properties);
            if (conn!=null) {
                System.out.println("connection is ok");
            } else {
                System.out.println("connection is failed");
            }

        /** create DB **/
            sql = "CREATE DATABASE new_3new_railway;";
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            System.out.println("DB created");


        /** create tables **/
            String createUsersTable = "CREATE TABLE IF NOT EXISTS public.passengers\n" +
                    "(\n" +
                    "    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    login character varying(100) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    pass character varying(100) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    name character varying(100) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    surname character varying(100) COLLATE pg_catalog.\"default\",\n" +
                    "    CONSTRAINT passengers_pkey PRIMARY KEY (id),\n" +
                    "    CONSTRAINT unique_column_login UNIQUE (login)\n" +
                    ");";
            String createStationsTable = "CREATE TABLE IF NOT EXISTS public.stations\n" +
                    "(\n" +
                    "    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    station_name character varying(200) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    CONSTRAINT stations_pkey PRIMARY KEY (id),\n" +
                    "    CONSTRAINT stations_station_name_key UNIQUE (station_name)\n" +
                    ")";
            String createRequestsTable = "CREATE TABLE IF NOT EXISTS public.requests\n" +
                    "(\n" +
                    "    request_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    start_station_name character varying(200) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    dest_station_name character varying(200) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    passenger_login character varying(200) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    CONSTRAINT requests_pkey1 PRIMARY KEY (request_id),\n" +
                    "    CONSTRAINT fk_dest_station FOREIGN KEY (dest_station_name)\n" +
                    "        REFERENCES public.stations (station_name) MATCH SIMPLE\n" +
                    "        ON UPDATE NO ACTION\n" +
                    "        ON DELETE NO ACTION,\n" +
                    "    CONSTRAINT fk_passenger_login FOREIGN KEY (passenger_login)\n" +
                    "        REFERENCES public.passengers (login) MATCH SIMPLE\n" +
                    "        ON UPDATE NO ACTION\n" +
                    "        ON DELETE NO ACTION,\n" +
                    "    CONSTRAINT fk_start_station FOREIGN KEY (start_station_name)\n" +
                    "        REFERENCES public.stations (station_name) MATCH SIMPLE\n" +
                    "        ON UPDATE NO ACTION\n" +
                    "        ON DELETE NO ACTION\n" +
                    ")";

            statement.executeUpdate(createUsersTable);
            statement.executeUpdate(createStationsTable);
            statement.executeUpdate(createRequestsTable);

            /** add data in tables **/

            String addUsers = "INSERT INTO passengers (login, pass, name, surname) \n" +
                    "VALUES ('ivan', '1234', 'Иван', 'Синичкин'), \n" +
                    "('admin', 'admin', 'admin', 'admin');";
            statement = conn.createStatement();
            statement.executeUpdate(addUsers);
            System.out.println("1st insert");

            String addStations = " INSERT INTO stations (station_name) \n" +
                    " VALUES ('Финляндский вокзал'), ('Кушелевка'), ('Пискаревка'), ('Ручьи'), ('Мурино'), ('Девяткино'), ('Лаврики'),\n" +
                    " ('Капитолово');";
            statement = conn.createStatement();
            statement.executeUpdate(addStations);

            String addRequests = "INSERT INTO requests (start_station_name, dest_station_name, passenger_login) \n" +
                    "VALUES ('Кушелевка', 'Мурино', 'admin'), ('Мурино', 'Лаврики', 'ivan'), \n" +
                    "('Мурино', 'Капитолово', 'ivan'), ('Финляндский вокзал', 'Девяткино', 'admin');";
            statement = conn.createStatement();
            statement.executeUpdate(addRequests);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


}
