import exceptons.FileDoesntContainThisByteCode;
import exceptons.FileWithThisExtensionDoesntExist;
import exceptons.SameByteCodesException;
import exceptons.WrongFormatByteCodeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    JTextField field1, field2;
    JButton button;
    JLabel l1, l1_1, l2, l3, l3_1, l4, l4_1;
    JFrame frame;
    JTextArea textArea, textArea3, textArea4, textAreaResult, delete;

    Main() {
        Font ft = new Font("Arial", Font.PLAIN, 15);
        Font ft2 = new Font("Arial", Font.PLAIN, 13);

        frame = new JFrame("QBS");

        textArea = new JTextArea(
                "Zmiana ciągu bajtów we wszystkich plikach z odpowiednim rozszerzeniem w podanym katalogu oraz podkatalogach");
        textArea.setFont(new Font("Arial", Font.BOLD, 19));
        textArea.setBounds(160, 20, 600, 80);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        l1 = new JLabel("Podaj ścieżkę do katalogu:");
        l1.setBounds(40, 80, 200, 30);
        l1.setFont(ft);

        field1 = new JTextField();
        field1.setBounds(40, 110, 740, 30);
        field1.setOpaque(true);
        field1.setToolTipText("Podaj ścieżkę do katalogu:");

        l1_1 = new JLabel("*Sprawdź dokładnie nazwę katalogu, ponieważ niepoprawna nazwa katalogu może uszkodzić pliki");
        l1_1.setBounds(40, 140, 700, 30);
        l1_1.setFont(ft2);


        l2 = new JLabel("Podaj rozszerzenie pliku bez kropki:");
        l2.setBounds(40, 180, 300, 30);
        l2.setFont(ft);

        field2 = new JTextField();
        field2.setBounds(40, 210, 240, 30);
        field2.setOpaque(true);
        field2.setToolTipText("Podaj rozszerzenie pliku bez kropki:");

        l3 = new JLabel("Podaj ciąg bajtów, który powinniśmy zmienić:");
        l3.setBounds(40, 260, 300, 30);
        l3.setFont(ft);

        textArea3 = new JTextArea();
        textArea3.setBounds(40, 290, 740, 100);
        textArea3.setLineWrap(true);
        textArea3.setWrapStyleWord(true);
        textArea3.setEditable(true);
        textArea3.setToolTipText("Podaj ciąg bajtów, który powinniśmy zmienić:");

        JScrollPane jScrollPane3 = new JScrollPane(textArea3);
        jScrollPane3.setBounds(40, 290, 740, 100);
        jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        l3_1 = new JLabel("*Wprowadź kod bajtów w systemie dziesiętnym; cyfry są oddzielone przecinkami");
        l3_1.setBounds(40, 390, 700, 30);
        l3_1.setFont(ft2);

        l4 = new JLabel("Podaj ciąg bajtów, na który powinniśmy zmienić:");
        l4.setBounds(40, 430, 350, 30);
        l4.setFont(ft);

        textArea4 = new JTextArea();
        textArea4.setBounds(40, 460, 740, 100);
        textArea4.setLineWrap(true);
        textArea4.setWrapStyleWord(true);
        textArea4.setEditable(true);
        textArea4.setToolTipText("Podaj ciąg bajtów, na który powinniśmy zmienić:");

        JScrollPane jScrollPane4 = new JScrollPane(textArea4);
        jScrollPane4.setBounds(40, 460, 740, 100);
        jScrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        l4_1 = new JLabel("*Wprowadź kod bajtów w systemie dziesiętnym; cyfry są oddzielone przecinkami");
        l4_1.setBounds(40, 560, 700, 30);
        l4_1.setFont(ft2);

        button = new JButton("Wykonaj");
        button.setBounds(350, 600, 100, 40);
        button.setOpaque(true);

        textAreaResult = new JTextArea();
        textAreaResult.setFont(new Font("Arial", Font.BOLD, 16));
        textAreaResult.setBounds(40, 660, 740, 120);
        textAreaResult.setOpaque(false);
        textAreaResult.setLineWrap(true);
        textAreaResult.setWrapStyleWord(true);
        textAreaResult.setEditable(false);

        JScrollPane jScrollPaneResult = new JScrollPane(textAreaResult);
        jScrollPaneResult.setBounds(40, 660, 740, 120);
        jScrollPaneResult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneResult.setOpaque(false);
        jScrollPaneResult.getViewport().setOpaque(false);
        jScrollPaneResult.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        button.addActionListener(this);

        frame.add(textArea);
        frame.add(l1);
        frame.add(field1);
        frame.add(l1_1);
        frame.add(l2);
        frame.add(field2);
        frame.add(l3);
        frame.add(jScrollPane3);
        frame.add(l3_1);
        frame.add(l4);
        frame.add(jScrollPane4);
        frame.add(l4_1);
        frame.add(button);
        frame.add(jScrollPaneResult);

        frame.setSize(820, 820);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (field1.getText().isEmpty() || field2.getText().isEmpty() ||
                textArea3.getText().isEmpty() || textArea4.getText().isEmpty()) {
            textAreaResult.setText("Jedno lub więcej z pól jest pustych, proszę wprowadzić poprawne dane");
            return;
        }

        FileService fileService = new FileService();

        String path = field1.getText();
        String extension = field2.getText();
        String bytesToRemove = textArea3.getText();
        String bytesToAdd = textArea4.getText();

        String message = null;
        try {
            message = fileService.changeByteArrayInFiles(path, extension, bytesToRemove, bytesToAdd);
        } catch (WrongFormatByteCodeException e1) {
            message = e1.getMessage();
        } catch (SameByteCodesException e2) {
            message = e2.getMessage();
        } catch (FileWithThisExtensionDoesntExist e3) {
            message = e3.getMessage();
        } catch (FileDoesntContainThisByteCode e4) {
            message = e4.getMessage();
        }
        textAreaResult.setText(message);
    }

}

