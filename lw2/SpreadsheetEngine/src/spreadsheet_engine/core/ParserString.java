package spreadsheet_engine.core;

class ParserString {

    String[] getLineToken(String line) {
        String[] token = line.split(" ");
        if (token.length >= 3) {
            if (token[0].equals("setformula")) {
                StringBuilder formula = new StringBuilder();
                for (int i = 2; i < token.length; ++i) {
                    formula.append(token[i]).append(" ");
                }
                token[2] = formula.toString();
            }
        }
        return token;
    }

    Pair getVariableInfo(String variable) throws Exception {
        Pair pair = new Pair();
        if (variable.length() < 2) {
            throw new Exception("Invalid identifier!!!");
        }

        Character ch = variable.charAt(0);

        if (((int) ch < (int) 'A') || ((int) ch > (int) 'Z')) {
            throw new Exception("Invalid identifier!!!");
        }

        String newStr = variable.substring(1, variable.length());

        Integer num;
        try {
            num = Integer.parseInt(newStr);
        } catch (NumberFormatException ex) {
            throw new Exception("Invalid identifier!!!");
        }

        pair.first = ch;
        pair.second = num;
        return pair;
    }
}
