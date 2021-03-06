package MineSweeper;


import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class TimerObject extends Thread {
    private long currentTime;
    private long startTime;
    private long sleepTime = 500;

    boolean running = true;
    private View view;

    public TimerObject(View view) {
        this.view = view;
        startTime = System.currentTimeMillis();
    }

    public void reset() {
        running = true;
        startTime = System.currentTimeMillis();

        for(ImageView iv : view.timerSegment) {
            iv.setEffect(view.red);
            view.timerSegment[2].setImage(view.timerDD[1]);
        }

        setTime(0, 0);
    }

    public void setWin() {
        running = false;
        for(ImageView iv : view.timerSegment) {
            iv.setEffect(view.green);
            view.timerSegment[2].setImage(view.timerDD[1]);
        }
    }

    public void setLoss() {
        running = false;
        view.timerSegment[0].setImage(view.timerLetters[0]);
        view.timerSegment[1].setImage(view.timerLetters[1]);
        view.timerSegment[2].setImage(view.timerDD[0]);
        view.timerSegment[3].setImage(view.timerLetters[2]);
        view.timerSegment[4].setImage(view.timerLetters[0]);
    }

    private void setTime(int mins, int secs) {
        if (view.timerSegment[2].getImage().equals(view.timerDD[0])) {
            view.timerSegment[2].setImage(view.timerDD[1]);
        } else {
            view.timerSegment[2].setImage(view.timerDD[0]);
        }

        view.timerSegment[0].setImage(view.timerDigit[mins / 10]);
        view.timerSegment[1].setImage(view.timerDigit[mins % 10]);
        view.timerSegment[3].setImage(view.timerDigit[secs / 10]);
        view.timerSegment[4].setImage(view.timerDigit[secs % 10]);
    }

    @Override
    public void run() {
        while (running) {
            currentTime = System.currentTimeMillis();
            long runTime = currentTime - startTime;

            int seconds = (int) (runTime / 1000.0);
            int minuets = (int) ((float) seconds / 60.0);

            Platform.runLater(() -> setTime(minuets, seconds % 60));

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
