import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class CSVParser {
    private BufferedReader br;

    private void openFile() {
        try {
            String csvPath = "src/main/resources/airports.csv";
            br = new BufferedReader(new FileReader(csvPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeFile() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Airport> getData(int column) {
        openFile();
        ArrayList<Airport> airports = new ArrayList<>();
        String line;
        String splitBy = ",";
        int lineNumber = 0;


        try {
            while ((line = br.readLine()) != null) {
                String[] split = line.split(splitBy);
                airports.add(new Airport(lineNumber, split[column]));
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeFile();
        return airports;
    }

    public void getCertainLines(ArrayList<Airport> searchResult) {
        openFile();
        String line;
        StringBuilder stringBuilder;
        searchResult.sort((Comparator.comparingInt(Airport::getLineNumber)));

        try {
            for (int i = 0, j = 0; j < searchResult.size(); i++) {
                line = br.readLine();
                if (searchResult.get(j).getLineNumber() == i) {
                    stringBuilder = new StringBuilder("[");
                    stringBuilder.append(line);
                    stringBuilder.append("]");
                    searchResult.get(j).setWholeLine(stringBuilder.toString());
                    j++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchResult.sort(Comparator.comparing(Airport::getParameter));

        closeFile();
    }
}
