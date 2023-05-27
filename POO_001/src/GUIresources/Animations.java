package GUIresources;

import javax.swing.*;

public class Animations {

    public static void xRight(final int start, final int stop, final int delay, final int increment, final JComponent component) {
        if (component.getX() == start) {
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    for (int i = start; i < stop; i += increment) {
                        component.setLocation(i, component.getY());
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    component.setLocation(stop, component.getY());
                    return null;
                }
            };
            worker.execute();
        }

    }

    public static void xLeft(final int start, final int stop, final int delay, final int increment, final JComponent component) {
        if (component.getX() == start) {
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    for (int i = start; i > stop; i -= increment) {
                        component.setLocation(i, component.getY());
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    component.setLocation(stop, component.getY());
                    return null;
                }
            };
            worker.execute();
        }
    }

    public static void yUp(final int start, final int stop, final int delay, final int increment, final JComponent component) {
        if (component.getY() == start) {
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    for (int i = start; i > stop; i -= increment) {
                        component.setLocation(component.getX(), i);
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    component.setLocation(component.getX(), stop);
                    return null;
                }
            };
            worker.execute();
        }
    }

    public static void yDown(final int start, final int stop, final int delay, final int increment, final JComponent component) {
        if (component.getY() == start) {
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    for (int i = start; i < stop; i += increment) {
                        component.setLocation(component.getX(), i);
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    component.setLocation(component.getX(), stop);
                    return null;
                }
            };
            worker.execute();
        }
    }
    
}