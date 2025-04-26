package GUI.Components.FinishingPage;

import javax.swing.*;

import Primary.Horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * VictoryGraph is a custom JPanel that visualises the position of a horse over
 * a series of races.
 * The graph is drawn with a line connecting the positions of the horse over
 * time.
 */
public class VictoryGraph extends JPanel {

    private List<Integer> positions; // List to store the horse's positions per race

    /**
     * Constructor that initialises the graph with a horse's race results.
     * 
     * @param horse The horse whose race results are to be visualised.
     */
    VictoryGraph(Horse horse) {
        setLayout(new FlowLayout()); // Set the layout for the panel (FlowLayout here)
        // this.positions = horse.getRecentTrends();

        // Initialise the positions list with hardcoded values for demonstration
        // purposes
        // (This should ideally be replaced with the horse's actual race data)
        this.positions = new ArrayList<Integer>();
        this.positions.add(1); // First race position
        this.positions.add(3); // Second race position
        this.positions.add(1); // Third race position
        this.positions.add(2); // Fourth race position
        this.positions.add(1); // Fifth race position

        repaint(); // Repaint the panel to trigger the painting process
    }

    // Force the size of the graph to a specific preferred dimension
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 75); // Set the preferred size of the graph to 150x75 pixels
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize(); // Set the maximum size of the graph to match the preferred size
    }

    /**
     * Paint the graph on the panel. This method is called automatically by Swing
     * when the panel needs to be rendered.
     * 
     * @param g The Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method to ensure proper rendering

        if (positions == null || positions.isEmpty()) {
            return; // If there are no positions to draw, do nothing
        }

        Graphics2D g2 = (Graphics2D) g; // Cast the Graphics object to Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing

        int width = getWidth(); // Get the width of the panel
        int height = getHeight(); // Get the height of the panel

        int padding = 5; // Padding between the graph and the edges of the panel
        int labelPadding = 10; // Padding for the labels on the axes

        int numberOfPoints = positions.size(); // Get the number of data points (races)
        int maxPosition = positions.stream().max(Integer::compareTo).orElse(1); // Get the maximum position (lowest
                                                                                // value) in the list

        // Calculate the scale factors for both the x and y axes
        double xScale = ((double) (width - 2 * padding - labelPadding)) / (numberOfPoints - 1);
        double yScale = ((double) (height - 2 * padding - labelPadding)) / maxPosition;

        // Draw the background of the graph
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding,
                height - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK); // Set color for drawing the grid and axes

        // --- NEW: Draw Y-axis labels (positions) ---
        int labelCount = Math.min(maxPosition, 5); // Max 5 labels for neatness
        for (int i = 1; i <= labelCount; i++) {
            // Calculate the position of each label and draw it
            int pos = i * maxPosition / labelCount; // Distribute labels evenly
            int y = (int) ((maxPosition - pos) * yScale + padding); // Scale the Y position
            String label = Integer.toString(pos); // Convert the position to a string
            g2.drawString(label, padding, y + 5); // Draw the label
        }

        // Create arrays for the X and Y points that represent the horse's positions in
        // the races
        int[] xPoints = new int[numberOfPoints];
        int[] yPoints = new int[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            // Calculate the X and Y coordinates for each data point
            xPoints[i] = (int) (i * xScale + padding + labelPadding);
            yPoints[i] = (int) ((maxPosition - positions.get(i)) * yScale + padding);
        }

        // Draw lines between each point to form the graph
        g2.setColor(Color.BLUE); // Set color for the line (blue)
        for (int i = 0; i < numberOfPoints - 1; i++) {
            g2.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
        }

        // Draw the individual data points (red dots) on the graph
        g2.setColor(Color.RED); // Set color for the points (red)
        for (int i = 0; i < numberOfPoints; i++) {
            int pointDiameter = 6; // Set the diameter for the points (red dots)
            g2.fillOval(xPoints[i] - pointDiameter / 2, yPoints[i] - pointDiameter / 2, pointDiameter, pointDiameter);
        }

        // Draw the axes (X and Y)
        g2.setColor(Color.BLACK); // Set color for the axes (black)
        g2.drawLine(padding + labelPadding, height - padding, padding + labelPadding, padding); // Draw the Y-axis
        g2.drawLine(padding + labelPadding, height - padding, width - padding, height - padding); // Draw the X-axis
    }
}
