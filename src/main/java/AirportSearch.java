import java.util.*;

public class AirportSearch {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new Error("No parameters! Enter an integer between 1 and 14");
        }
        int column = 1;
        try {
            column = Integer.parseInt(args[0]) - 1;
        } catch (Exception e) {
            throw new Error("Enter an integer between 1 and 14");
        }
        if (column > 15 || column < 0) {
            throw new Error("This value is out of bounds, enter an integer between 1 and 14");
        }
        ArrayList<Airport> airports = new ArrayList<>();
        CSVParser parser = new CSVParser();
        airports = parser.getData(column);
        airports.sort((o1, o2) -> o1.getParameter().compareTo(o2.getParameter()));
        String filter = "";
        ArrayList<Airport> searchResult = new ArrayList<>();
        int pointer = 0;
        int mid = 0;
        while (!filter.equals("!quit")) {
            System.out.print("¬ведите строку: ");
            filter = getFilter().toLowerCase(Locale.ROOT);
            long time = System.currentTimeMillis();
            pointer = binarySearch(airports, filter);
            if(pointer < 0) {
                System.out.println("Time spent: " + (System.currentTimeMillis() - time) + " ml" + " No results!");
                continue;
            }
            String airportName = airports.get(pointer).getParameter().toLowerCase(Locale.ROOT).replaceAll("\"", "");
            while(airportName.startsWith(filter) && pointer != airports.size()) {
                searchResult.add(airports.get(pointer));
                pointer++;
                airportName = airports.get(pointer).getParameter().toLowerCase(Locale.ROOT).replaceAll("\"", "");
            }
            parser.getCertainLines(searchResult);

            for (int i = 0; i < searchResult.size(); i++) {
                System.out.println(searchResult.get(i).toString());
            }

            System.out.println("Time spent: " + (System.currentTimeMillis() - time) + " ml" + "  Results: " + searchResult.size());
            searchResult.clear();
        }
    }

    public static String getFilter() {
        Scanner sc = new Scanner(System.in);
        sc.hasNext();
        return sc.next().toLowerCase(Locale.ROOT);
    }

    public static int binarySearch(ArrayList<Airport> airports, String target) {
        int left = 0;
        int right = airports.size() - 1;
        int targetLength = target.length();
        int result = -1;
        int res;
        while (left <= right) {
            int mid = (left + right) / 2;
            String airportName = airports.get(mid).getParameter().toLowerCase(Locale.ROOT).replaceAll("\"", "").substring(0, targetLength);
            res = target.compareTo(airportName);
            if (res == 0) {
                result = mid;
                right = mid - 1;
            }
            else if (res < 0) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return result;
    }
}
