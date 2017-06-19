package simple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
class MainPanel extends JPanel {
    private final Ball ball1, ball2, controlledBall;
    private int time, delay = 100, score, condition;
    private ArrayList<Ball> deathBalls;


    public MainPanel() {
        setLayout(new BorderLayout());
        JLabel result = new JLabel("Score: ");
        add(result, BorderLayout.NORTH);
        setBackground(Color.WHITE);
        score = 0;
        condition = 0;
        ball1 = new Ball(640.0, 360.0, 3.0, 3.0, 120, Color.RED);
        controlledBall = new Ball(640.0, 360.0, 0.0, 0.0, 7, Color.BLACK);
        ball2 = new Ball(100.0, 150.0, 3.0, 3.0, 120, Color.RED);

        Ball deathBall1 = new Ball(300.0, 5.0, 0.0, 20.0, 10, Color.BLUE);
        Ball deathBall2 = new Ball(980.0, 5.0, 0.0, 20.0, 10, Color.BLUE);
        Ball deathBall3 = new Ball(5.0, 180.0, 20.0, 0.0, 10, Color.BLUE);
        Ball deathBall4 = new Ball(5.0, 540.0, 20.0, 0.0, 10, Color.BLUE);
        Ball deathBall1a = new Ball(300.0, 715.0, 10.0, 20.0, 10, Color.BLUE);
        Ball deathBall2a = new Ball(980.0, 715.0, 10.0, 20.0, 10, Color.BLUE);
        Ball deathBall3a = new Ball(1275.0, 180.0, 20.0, 10.0, 10, Color.BLUE);
        Ball deathBall4a = new Ball(1275.0, 540.0, 20.0, 10.0, 10, Color.BLUE);

        deathBalls = new ArrayList<>();
        deathBalls.add(deathBall1);
        deathBalls.add(deathBall1a);
        deathBalls.add(deathBall2);
        deathBalls.add(deathBall2a);
        deathBalls.add(deathBall3);
        deathBalls.add(deathBall3a);
        deathBalls.add(deathBall4);
        deathBalls.add(deathBall4a);

        ActionListener timerListener = e -> {
            if (((ball1.distance(controlledBall) <= ball1.getRadius()) ||
                    (ball2.distance(controlledBall) <= ball2.getRadius())) && condition != 2) condition = 1;
            else if (time > delay && condition == 1) {
                condition = 2;
            }

            for (Ball ball : deathBalls) {
                if (controlledBall.inTouch(ball)) {
                    condition = 2;
                }
            }

            if (condition == 1) {
                if (time > delay) {
                    ball1.step(getWidth(), getHeight());
                    ball2.step(getWidth(), getHeight());
                    for (Ball ball : deathBalls) {
                        ball.step(getWidth(), getHeight());
                    }
                    score++;
                    this.setScore(score);
                    repaint();
                }

            }
            if (time > delay && condition == 2) {
                ball1.stop();
                ball2.stop();
                controlledBall.stop();
                for (Ball ball : deathBalls) {
                    ball.stop();
                }
            }
            controlledBall.step(getWidth(), getHeight());
            repaint();
            if (controlledBall.inside(ball1) ||
                    controlledBall.inside(ball2)) {
                time++;
            } else if (time < delay) time = 0;
            result.setText("Score: " + this.getScore());
        };
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                {
                    controlledBall.setX(e.getX());
                    controlledBall.setY(e.getY());
                }
            }
        });
        Timer timer = new Timer(20, timerListener);
        timer.start();


    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBall(g, ball1);
        paintBall(g, ball2);
        paintBall(g, controlledBall);
        for (Ball ball : deathBalls) {
            paintBall(g, ball);
        }
    }

    private void paintBall(Graphics g, Ball b) {
        g.setColor(b.getColor());
        int radius = b.intRadius();
        g.fillOval(b.intX() - radius, b.intY() - radius, 2 * radius, 2 * radius);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }
}



