package ru.shorokhova.solution;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Solution {

    private static final String PATH_TO_JSON_FILE = "src/main/resources/tickets.json";

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        try {
            Object obj = new JSONParser()
                    .parse(new FileReader(PATH_TO_JSON_FILE));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray jsonArray = (JSONArray) jsonObject.get("tickets");

            List<Ticket> ticketList = new ArrayList<>();

            List<Integer> minutesOnWayList = new ArrayList<>();

            Iterator iteratorOfJsonArray = jsonArray.iterator();

            while (iteratorOfJsonArray.hasNext()) {
                Ticket ticket = objectMapper.readValue(iteratorOfJsonArray.next().toString(), Ticket.class);

                ticketList.add(ticket);

                LocalDateTime departureDateTime = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime());
                LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime());

                minutesOnWayList.add(timeOnWayInMinutes(departureDateTime, arrivalDateTime));
            }

            System.out.println("Среднее время полета между городами Владивосток\n" +
                    "и Тель-Авив (в минутах): " + averageTimeOnWayInMinutes(minutesOnWayList));

            System.out.println("90-й процентиль времени полета между городами\n" +
                    "Владивосток и Тель-Авив (в минутах): " + findPercentile(minutesOnWayList, 90));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int timeOnWayInMinutes(LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        if (arrivalDateTime.isBefore(departureDateTime)) {
            throw new DateTimeException("Дата прибытия не может быть раньше даты вылета");
        }
        return (int) departureDateTime.until(arrivalDateTime, ChronoUnit.MINUTES);
    }

    public static int averageTimeOnWayInMinutes(List<Integer> minutesOnWayLis) {
        int sum = 0;
        for (int i = 0; i < minutesOnWayLis.size(); i++) {
            sum += minutesOnWayLis.get(i);
        }
        return sum / minutesOnWayLis.size();
    }

    public static int findPercentile(List<Integer> minutesOnWayList, int percentileIndex) {
        Collections.sort(minutesOnWayList);
        int index = minutesOnWayList.size() * percentileIndex / 100;
        return minutesOnWayList.get(index - 1);
    }
}
