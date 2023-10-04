package homework9.exceptions;

public class ArrayValueCalculator {

    public static int doCalc(String[][] data) throws ArraySizeException, ArrayDataException {

        int sum = 0;
        ArraySizeException wrongSize = new ArraySizeException("Wrong array size. In input must be string array 4x4!");

        if (data.length != 4)
            throw wrongSize;

        for (String[] d : data)
            if (d.length != 4)
                throw wrongSize;

        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[i].length; j++) {
                try {
                    sum += Integer.parseInt(data[i][j]);
                } catch (NumberFormatException e) {
                    throw new ArrayDataException("Can not parse to int value \"" + data[i][j] + "\" from input array at position [" + i + "][" + j + "]");
                }
            }

        return sum;
    }
}
