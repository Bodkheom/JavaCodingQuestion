import java.util.*;

public class Solution {
    public String countOfAtoms(String formula) {
        // parse the formula string into a map of elements and their counts
        Map<String, Integer> elementCounts = parseFormula(formula);
        
        // build a string representation of the map, sorted alphabetically
        StringBuilder sb = new StringBuilder();
        List<String> elements = new ArrayList<>(elementCounts.keySet());
        Collections.sort(elements);
        for (String element : elements) {
            sb.append(element);
            if (elementCounts.get(element) > 1) {
                sb.append(elementCounts.get(element));
            }
        }
        
        return sb.toString();
    }
    
    private Map<String, Integer> parseFormula(String formula) {
        Map<String, Integer> elementCounts = new HashMap<>();
        int i = 0;
        while (i < formula.length()) {
            int start = i;
            char c = formula.charAt(i);
            if (c == '(') {
                // find the matching closing parenthesis
                int openCount = 1;
                i++;
                while (openCount > 0) {
                    c = formula.charAt(i);
                    if (c == '(') {
                        openCount++;
                    } else if (c == ')') {
                        openCount--;
                    }
                    i++;
                }
                // process the contents of the parentheses
                Map<String, Integer> subElementCounts = parseFormula(formula.substring(start + 1, i - 1));
                // get the multiplier for the contents of the parentheses
                int multiplier = 1;
                if (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    int end = i;
                    while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                        i++;
                    }
                    multiplier = Integer.parseInt(formula.substring(end, i));
                }
                // update the counts in the map
                for (String element : subElementCounts.keySet()) {
                    elementCounts.put(element, elementCounts.getOrDefault(element, 0) + subElementCounts.get(element) * multiplier);
                }
            } else {
                // process an element
                i++;
                while (i < formula.length() && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                String element = formula.substring(start, i);
                // get the count for the element
                int count = 1;
                if (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    int end = i;
                    while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                        i++;
                    }
                    count = Integer.parseInt(formula.substring(end, i));
                }
                // update the count in the map
                elementCounts.put(element, elementCounts.getOrDefault(element, 0) + count);
            }
        }
        return elementCounts;
    }
}