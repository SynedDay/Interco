import java.util.ArrayList;
import java.util.List;

public class RegulatoryGraph {

    private List<Variable> variables; // V
    private List<String> multiplexes; // m
    private List<String> formulas;
    private List<List<Variable>> inputs;
    private List<Variable> outputs;

    private List<Pair<String, Variable>> em; // Multiplexes + Output
    private List<Trouple<Variable, Integer, String>> ev; // Multiplexes + Inputs

    public RegulatoryGraph(List<List<String>> var, List<List<String>> reg) {
        this.variables = new ArrayList<>();
        for (List list : var) {
            variables.add(new Variable(list.get(0).toString(), Integer.valueOf(list.get(list.size() - 1).toString())));
        }

        this.multiplexes = new ArrayList<>();
        this.formulas = new ArrayList<>();
        this.outputs = new ArrayList<>();
        for (List list : reg) {
            multiplexes.add(list.get(0).toString());
            formulas.add(list.get(1).toString());
            for (Variable v : variables) {
                if (v.name.matches(list.get(2).toString())) outputs.add(v);
            }
        }

        this.inputs = new ArrayList<>();
        List<Variable> input;
        int count = 0;
        for (String s : formulas) {
            input = new ArrayList<>();
            for (Variable v : variables) {
                if (s.contains(v.name))
                    // count the occurrence of v, because the same var can input m multiple times
                    count = (s.length() - s.replaceAll(v.name, "").length()) / v.name.length();
                for (int i = 0; i < count; i++) {
                    input.add(v);
                }
            }
            if (!input.isEmpty()) inputs.add(input);
            else System.out.println("NO INPUT FOUND IN THIS FORMULA" + s);
        }

    }

    public List<Variable> getVariables() {
        return variables;
    }

    class Variable {
        String name;

        public String getName() {
            return name;
        }

        public int getB_v() {
            return b_v;
        }

        int b_v;

        Variable(String name, int b_v) {
            this.name = name;
            this.b_v = b_v;
        }
    }
}