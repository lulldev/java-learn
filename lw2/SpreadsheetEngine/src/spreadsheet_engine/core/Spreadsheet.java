package spreadsheet_engine.core;

import java.text.DecimalFormat;
import java.util.*;

public class Spreadsheet {
    private static final Integer COUNT_SPACES = 15;
    private static TreeMap<Character, TreeMap<Integer, String>> table = new TreeMap<>();

    public Spreadsheet() {
    }

    void setValue(Pair pair, String newValue) {
        if (table.containsKey(pair.first)) {
            TreeMap<Integer, String> row = table.get(pair.first);
            row.put(pair.second, newValue);
        } else {
            TreeMap<Integer, String> row = new TreeMap<Integer, String>();
            row.put(pair.second, newValue);
            table.put(pair.first, row);
        }
    }

    void setFormula(Pair pair, String newValue) {
        if (table.containsKey(pair.first)) {
            TreeMap<Integer, String> row = table.get(pair.first);
            row.put(pair.second, newValue);
        } else {
            TreeMap<Integer, String> row = new TreeMap<Integer, String>();
            row.put(pair.second, newValue);
            table.put(pair.first, row);
        }
    }

    void display() {
        System.out.print("  ");
        for (Map.Entry entry : table.entrySet()) {
            System.out.printf("%15s", entry.getKey());
        }
        System.out.println("");
        Set<Integer> setIndexColumn = new HashSet<Integer>();
        for (Map.Entry<Character, TreeMap<Integer, String>> entry : table.entrySet()) {
            TreeMap<Integer, String> row = entry.getValue();
            for (Map.Entry element : row.entrySet()) {
                if (!setIndexColumn.contains(element.getKey())) {
                    System.out.print(element.getKey() + " ");
                    printValue( (Integer) element.getKey());
                    setIndexColumn.add((Integer) element.getKey());
                    System.out.println();
                }
            }
        }
    }

    boolean isFormula(Pair cell) {
        boolean result = false;
        if(table.containsKey(cell.first)) {
            if(table.get(cell.first).containsKey(cell.second)) {
                String[] token = table.get(cell.first).get(cell.second).split(" ");
                result = token[0].equals("formula");
            }
        }
        return result;
    }

    String getFormula(Pair cell) {
        String formula = "";
        if(table.containsKey(cell.first)) {
            if(table.get(cell.first).containsKey(cell.second)) {
                String[] token = table.get(cell.first).get(cell.second).split(" ");
                formula = token[1];
            }
        }
        return formula;
    }

    private void printValue(Integer rowInteger) {
        int counter = 1;
        for (Map.Entry<Character, TreeMap<Integer, String>> entry : table.entrySet()) {
            TreeMap<Integer, String> row = entry.getValue();
            for (Map.Entry element : row.entrySet()) {
                if (Objects.equals((Integer) element.getKey(), rowInteger)) {
                    String value = (String) element.getValue();
                    String format = "%" + (COUNT_SPACES * counter) + "s";
                    counter = 0;
                    if (value.split(" ")[0].equals("formula")) {
                        String calcResult = new DecimalFormat("#0.00")
                                .format(calculation(value.substring(value.split(" ")[0].length(), value.length())));
                        System.out.printf(format, calcResult);
                        continue;
                    }
                    System.out.printf(format, value);
                }
            }
            ++counter;
        }
    }


    private Double getValueVariable(String variable) {
        Double value;
        if (table.containsKey(variable.charAt(0))) {
            TreeMap<Integer, String> col = table.get(variable.charAt(0));
            if (col.containsKey(Integer.parseInt("" + variable.charAt(1)))) {
                String valueStr = col.get(Integer.parseInt("" + variable.charAt(1)));
                if (valueStr.split(" ")[0].equals("formula")) {
                    return calculation(valueStr.substring(valueStr.split(" ")[0].length(), valueStr.length()));
                }
                value = (!isDouble(valueStr)) ? Double.NaN : Double.parseDouble(valueStr);
            } else {
                value = Double.NaN;
            }
        } else {
            value = Double.NaN;
        }
        return value;
    }

    private double calculation(String formula) {
        String[] prefixStrArray = formula.split(" ");
        Stack<Double> stack = new Stack<Double>();

        Map<String, Calculation> mapOperations = new HashMap<String, Calculation>();
        mapOperations.put("+", (operand1, operand2) -> operand1 + operand2
        );
        mapOperations.put("-", (operand1, operand2) -> operand1 - operand2
        );
        mapOperations.put("*", (operand1, operand2) -> operand1 * operand2
        );
        mapOperations.put("/", (operand1, operand2) -> operand1 / operand2
        );
        for (int i = prefixStrArray.length - 1; i > -1; i--) {
            String prefixStr = prefixStrArray[i];
            if (prefixStr.equals("")) {
                continue;
            }
            if (mapOperations.containsKey(prefixStr)) {
                Double operand1 = stack.pop();
                Double operand2 = stack.pop();
                stack.push((prefixStr.equals("/") && (operand2 == 0)) ?
                        Double.NaN : mapOperations.get(prefixStr).getResult(operand1, operand2));
            } else {
                if (isDouble(prefixStr)) {
                    stack.push(Double.parseDouble(prefixStr));
                    continue;
                }
                stack.push(getValueVariable(prefixStr));
            }
        }
        return stack.pop();
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
