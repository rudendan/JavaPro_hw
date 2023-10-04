package homework9.exceptions;

public class Main {

    public static void main(String[] args){

        String[][] data = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };

        try {
            System.out.println(ArrayValueCalculator.doCalc(data));
        } catch (ArraySizeException | ArrayDataException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
