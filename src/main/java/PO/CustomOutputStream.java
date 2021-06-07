package PO;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.io.IOException;
import java.io.OutputStream;

public class CustomOutputStream extends OutputStream
{
    private final JTextArea textArea;

    public CustomOutputStream(JTextArea textArea)
    {
        this.textArea = textArea;
    }

    @Override
    public void write(int b)
    {
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.append(String.valueOf((char)b));
    }

}
