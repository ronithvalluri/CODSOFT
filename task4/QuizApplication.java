import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton submitButton;
    private JLabel timerLabel;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private ButtonGroup buttonGroup;
    private int[] timeLeftForEachQuestion;

    private String[] questions = {
            "Who was the first Prime Minister of India?",
            "What is the capital city of India?",
            "Which state is also known as the 'Fruit Bowl' of India?",
            "Who is Sachin Tendulkar?",
            "Who discovered India?",
            "Who is popularly known as the 'Iron Man' of India?",
            "Which is the national sport of India?",
            "Who is the current President of India?",
            "Which place in India is also known as the 'Land of Rising Sun'?",
            "Where is Taj Mahal located in India?"
    };
    private String[][] choices = {
            {"Jawaharlal Nehru", "Indira Gandhi", "Narendra Modi", "Rajiv Gandhi"},
            {"New Delhi", "Mumbai", "Kolkata", "Chennai"},
            {"Jammu and Kashmir", "Himachal Pradesh", "Assam", "Meghalaya"},
            {"Indian Hockey player", "Indian Cricketer", "Indian Kabaddi player", "Indian Marathon Runner"},
            {"Vasco da Gama", "Christopher Columbus", "James Cook", "Willem Janszoon"},
            {"Lal Bahadur Shastri", "Sardar Vallabh Bhai Patel", "Mahatma Gandhi", "Dr. B.R Ambedkar"},
            {"Hockey", "Cricket", "Kabaddi", "Football"},
            {"Droupadi Murmu", "Pranab Mukherjee", "A. P. J. Abdul Kalam", "Pratibha Patil"},
            {"Sikkim", "Arunachal Pradesh", "Karnataka", "Gujarat"},
            {"New Delhi", "Kolkata", "Agra", "Lucknow"}
    };
    private int[] correctAnswers = {0, 0, 1, 1, 0, 1, 0, 0, 1, 2};
    private String[] selectedOptions;

    public QuizApplication() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            buttonGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });

        timerLabel = new JLabel();
        add(timerLabel, BorderLayout.EAST);

        currentQuestionIndex = 0;
        score = 0;
        selectedOptions = new String[questions.length];
        timeLeftForEachQuestion = new int[questions.length];
        for (int i = 0; i < questions.length; i++) {
            timeLeftForEachQuestion[i] = 10;
        }
        showQuestion();
        startTimer();
    }

    private void showQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(choices[currentQuestionIndex][i]);
            options[i].setSelected(false);
        }
    }

    private void showNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            showQuestion();
            resetTimer();
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selectedOptions[currentQuestionIndex] = choices[currentQuestionIndex][i];
                if (i == correctAnswers[currentQuestionIndex]) {
                    score++;
                }
                break;
            }
        }
    }

    private void showResult() {
        StringBuilder resultMessage = new StringBuilder("Quiz Completed!\n");
        resultMessage.append("Your Score: ").append(score).append("/").append(questions.length).append("\n\n");

        for (int i = 0; i < questions.length; i++) {
            resultMessage.append("Question ").append(i + 1).append(": ").append(questions[i]).append("\n");
            resultMessage.append("Your Answer: ").append(selectedOptions[i]).append("\n");

            String selectedOption = selectedOptions[i];
            String correctOption = choices[i][correctAnswers[i]];
            if (selectedOption != null && selectedOption.equals(correctOption)) {
                resultMessage.append("Result: Correct\n");
            } else {
                resultMessage.append("Result: Incorrect\n");
                resultMessage.append("Correct Answer: ").append(correctOption).append("\n");
            }
            resultMessage.append("Time Taken: ").append(10 - timeLeftForEachQuestion[i]).append(" seconds").append("\n\n");
        }

        JTextArea resultTextArea = new JTextArea(resultMessage.toString(), 20, 40);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setLineWrap(true);
        resultTextArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Quiz Result", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeftForEachQuestion[currentQuestionIndex] >= 0) {
                    timerLabel.setText("Time left: " + timeLeftForEachQuestion[currentQuestionIndex] + " seconds");
                    timeLeftForEachQuestion[currentQuestionIndex]--;
                } else {
                    timer.stop();
                    checkAnswer();
                    showNextQuestion();
                }
            }
        });
        timer.start();
    }

    private void resetTimer() {
        timer.stop();
        startTimer();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}
