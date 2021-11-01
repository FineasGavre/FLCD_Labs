package me.fineasgavre.flcd.lab4;

import java.util.ArrayList;
import java.util.List;

public class Step {
    private String p1;
    private String p2;
    private List<String> target;

    Step(String p1, String p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.target = new ArrayList<>();
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public List<String> getTarget() {
        return target;
    }

    public void addTarget(String target) {
        this.target.add(target);
    }

    @Override
    public String toString() {
        return "Step{" +
                "p1='" + p1 + '\'' +
                ", p2='" + p2 + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
