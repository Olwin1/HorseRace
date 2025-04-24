package GUI.Components.CustomInputs;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Creates a custom [JTextField] that will only accept 1 character.
 */
public class SingleCharInput extends JTextField {
    /**
     * Create an instance of [SingleCharInput]
     */
    public SingleCharInput() {
        super(1); // Set visible columns to 1
        setHorizontalAlignment(JTextField.CENTER);

        // Ensure that the text entered cannot exceed 1 character
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                // Get the current text in the document and append the new text being added
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                // Check if the resulting text will be exactly 1 character long,
                // the new text being added is 1 character long,
                // and the character is a letter or digit
                if (newText.length() <= 1 && text.length() == 1 && Character.isLetterOrDigit(text.charAt(0))) {
                    // If valid, replace the entire content with the new character
                    super.replace(fb, 0, fb.getDocument().getLength(), text, attrs);
                }
                // Otherwise, ingore the input
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                // Delegate insert operations to the replace method for consistency
                replace(fb, offset, 0, text, attr);
            }
        });
    }
}
