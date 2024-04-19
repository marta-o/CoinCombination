package projekt;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class CoinCombinationPanel extends JPanel {
    private JTextField coins_field;
    private JTextField amount_field;
    private JTextArea results_area;
    private JScrollPane scroll;


    public CoinCombinationPanel() {
        setLayout(null);

        JLabel coins_label = new JLabel("Ilość monet:");
        coins_label.setBounds(120, 20, 80, 25);
        add(coins_label);

        coins_field = new JTextField(10);
        coins_field.setBounds(200, 20, 80, 25);
        add(coins_field);

        JLabel amount_label = new JLabel("Kwota:");
        amount_label.setBounds(150, 50, 80, 25);
        add(amount_label);

        amount_field = new JTextField(10);
        amount_field.setBounds(200, 50, 80, 25);
        add(amount_field);

        JButton calculate_button = new JButton("Oblicz");
        calculate_button.setBounds(200, 80, 80, 25);
        add(calculate_button);

        results_area = new JTextArea();
        results_area.setEditable(false);
        results_area.setVisible(false);

        scroll = new JScrollPane(results_area);

        calculate_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate_results();
            }
        });
    }

    private void calculate_results() {
        try {
            int n = Integer.parseInt(coins_field.getText());
            int k = Integer.parseInt(amount_field.getText());
            if (n <= 0 || k < 0) {
                results_area.setText("Ilość monet i kwota muszą być dodatnie.");
                results_area.setBounds(10, 120, 360, 20);
                add(results_area);
                results_area.setVisible(true);
                return;
            }
            ArrayList<int[]> results = combinations(n, k);
            display_results(results);
        } catch (NumberFormatException e) {
            results_area.setText("Podano nieprawidłową liczbę.");
            results_area.setBounds(10, 120, 360, 20);
            add(results_area);
            results_area.setVisible(true);
        }
    }

    private ArrayList<int[]> combinations(int n, int k) {
        ArrayList<int[]> solutions = new ArrayList<>();
        for (int one = 0; one < n + 1; one++) {
            for (int two = 0; two < n + 1 - one; two++) {
                int five = n - one - two;
                int total = one + 2 * two + 5 * five;
                if (total == k) {
                    int [] money = {one, two, five};
                    solutions.add(money);
                }
            }
        }
        return solutions;
    }

    private void display_results(ArrayList<int[]> solutions) {
        if (solutions.isEmpty()) {
            results_area.setText("Sytuacja niemożliwa.");
        } else if (solutions.size() == 1) {
            results_area.setText("W woreczku są następujące monety: " + format_result(solutions.get(0)));
        } else {
            StringBuilder builder = new StringBuilder("W woreczku mogą być następujące monety:\n");
            for (int []sol : solutions) {
                builder.append(format_result(sol));
            }
            results_area.setText(builder.toString());
        }

        int rows = results_area.getLineCount();
        int line_height = results_area.getFontMetrics(results_area.getFont()).getHeight();

        results_area.setBounds(10, 120, 360, rows * line_height);
        add(results_area);
        results_area.setVisible(true);

        if (!((rows + 1) * line_height > 230)) {
            scroll.setVisible(false);
        } else {
            remove(scroll);
            scroll = new JScrollPane(results_area);
            scroll.setBounds(10, 120, 360, 230);
            add(scroll);
            scroll.setVisible(true);
        }
    }

    private String format_result(int[] result) {
        return result[0] + " x 1 zł, " + result[1] + " x 2 zł, " + result[2] + " x 5 zł\n";
    }
}

