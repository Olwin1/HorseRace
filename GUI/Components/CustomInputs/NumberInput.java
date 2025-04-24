package GUI.Components.CustomInputs;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Creates a custom [JTextField] that will only accept numbers within a certain
 * range.
 */
public class NumberInput extends JTextField {
    /**
     * Create an instance of [NumberInput]
     */
    public NumberInput(int defaultValue, int maxInteger, int minInteger) {
        super(1); // Set visible columns to 1
        setHorizontalAlignment(JTextField.CENTER);
        setText(Integer.toString(defaultValue));

        // Ensure that the text entered cannot be anything but a number in the correct
        // range
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                // Get the current text in the document and append the new text being added
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                // Check if the resulting text will be a valid integer,
                // and the new text being added is within the range of accepted integers
                try {
                    Integer newTextInteger = Integer.parseInt(newText);
                    if (newTextInteger <= maxInteger && newTextInteger >= minInteger) {
                        // If valid, replace the entire content with the new character
                        super.replace(fb, 0, fb.getDocument().getLength(), text, attrs);
                    }
                    // Do nothing
                } catch (NumberFormatException e) {
                    // Do nothing
                }
                // if nothing is done then the new input is ignored since it isn't allowed

            }

            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                // Delegate insert operations to the replace method for consistency
                replace(fb, offset, 0, text, attr);
            }
        });

    }

    public int getIntegerValue() {
        String stringValue = getText();
        // This shouldn't ever throw an error as it would not be allowed to be inputted
        // in the first place.
        return Integer.parseInt(stringValue);
    }
}
